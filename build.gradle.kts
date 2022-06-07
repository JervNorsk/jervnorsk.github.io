import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    kotlin("multiplatform")
}

rootProject.plugins.withType<NodeJsRootPlugin> {
    rootProject.the<NodeJsRootExtension>().download = false
}

rootProject.plugins.withType<YarnPlugin> {
    rootProject.the<YarnRootExtension>().lockFileDirectory = project.projectDir
    rootProject.the<YarnRootExtension>().lockFileName = "yarn.lock"
}

kotlin {
    js {
        nodejs {
            binaries.executable()
        }
        compilations {
            getByName("main") {
                defaultSourceSet {
                    resources.srcDirs("src/jsMain/typescript")

                    dependencies {
                        // Language Framework
                        implementation(devNpm("typescript", "latest"))

                        // React
                        implementation(kotlinw("react"))
                        implementation(kotlinw("react-dom"))

                        implementation(devNpm("@types/react", "latest"))
                        implementation(devNpm("@types/react-dom", "latest"))
                    }
                }
            }
        }
        tasks {
            val build by getting {}
            val start by creating {
                group = "Project"

                dependsOn(build)
            }
        }
    }
}
