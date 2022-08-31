import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsExec
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

interface KotlinJsExpoDsl {
    fun useWeb()
}

fun KotlinJsTargetDsl.expo() =
    expo {}

fun KotlinJsTargetDsl.expo(body: KotlinJsExpoDsl.() -> Unit) {
    val expo = object : KotlinJsExpoDsl {
        var web: Boolean = false

        override fun useWeb() {
            web = true
        }
    }.apply(body)

    with(project) {
        // Disable NodeJs Download
        rootProject.plugins.withType(NodeJsRootPlugin::class.java) {
            rootProject.the(NodeJsRootExtension::class).download = false
        }

        // Change Yarn lock file location
        rootProject.plugins.withType(YarnPlugin::class.java) {
            rootProject.the(YarnRootExtension::class).run {
                download = false
                lockFileDirectory = projectDir
                lockFileName = "yarn.lock"
            }
        }

        // Links essentials files for trigger JS Project
        Runtime.getRuntime().exec(
            arrayOf(
                "ln", "-sf",
                rootProject.buildDir
                    .resolve("js")
                    .resolve("node_modules")
                    .relativeTo(projectDir)
                    .path,
                projectDir.path
            )
        )
        Runtime.getRuntime().exec(
            arrayOf(
                "ln", "-sf",
                rootProject.buildDir
                    .resolve("js")
                    .resolve("packages")
                    .resolve("${rootProject.name}-${project.name}")
                    .resolve("package.json")
                    .path,
                projectDir.path
            )
        )

        // Gradle Task
        with(tasks) {
            afterEvaluate {
                getByName("nodeDevelopmentRun") {
                    dependsOn.clear()
                    actions.clear()

                    dependsOn("expoRun")
                }
                getByName("nodeProductionRun") {
                    dependsOn.clear()
                    actions.clear()

                    dependsOn("expoRun")
                }

                getByName("nodeRun") {
                    dependsOn.clear()
                    actions.clear()

                    dependsOn("expoRun")
                }
            }

            // Add task for start expo
            NodeJsExec.create(compilations.getByName("main"), "expoRun") {
                group = "NodeJs Expo"

                dependsOn("build")

                args(
                    nodeJs.rootPackageDir
                        .resolve("node_modules")
                        .resolve("expo-cli/bin/expo.js")
                        .path,
                    "start",
                    if(expo.web) "--web" else null
                )
            }
        }

        // Kotlin/JS Target Setup
        nodejs {

            // Enable binaries generation
            binaries.executable()
        }

        // Kotlin/JS Compilation Setup
        with(compilations) {
            getByName("main") {
                defaultSourceSet {

                    // Allow typescript code works
                    resources.srcDir("src/main/typescript")

                    // Add default devDependencies
                    dependencies {
                        implementation(kotlinw("react"))
                        implementation(kotlinw("react-dom"))

                        implementation(devNpm("react", "17.0.2"))
                        implementation(devNpm("react-dom", "17.0.2"))
                        implementation(devNpm("react-native", "0.68.2"))

                        implementation(devNpm("@types/react", "17.0.2"))
                        implementation(devNpm("@types/react-dom", "17.0.2"))
                        implementation(devNpm("@types/react-native", "^0.67.8"))
                    }
                    // Add default dependencies
                    dependencies {
                        implementation(npm("expo", "45.0.5"))
                        implementation(npm("expo-cli", "latest"))
                        implementation(npm("expo-font", "latest"))

                        implementation(npm("@expo/config", "latest"))

                        implementation(npm("react-native-web", "0.17.7"))
                        implementation(npm("react-native-svg", "12.3.0"))
                        implementation(npm("react-native-screens", "3.11.1"))
                        implementation(npm("react-native-gesture-handler", "2.1.2"))
                        implementation(npm("react-native-safe-area-context", "4.2.4"))
                    }
                    // Custom package.json
                    packageJson {

                    }
                }
            }
        }
    }
}
