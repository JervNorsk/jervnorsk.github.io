pluginManagement {
    resolutionStrategy {
        plugins {
            kotlin("multiplatform") version (extra["kotlin.version"] as String)
        }
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}


fun includeProjects(baseDir: String) =
    includeProjects(file(baseDir))

fun includeProjects(baseDir: File, parentPath: String = "") {
    include("$parentPath:${baseDir.name}")
    project("$parentPath:${baseDir.name}")
        .apply { projectDir = baseDir }
        .run {
            fileTree(baseDir) {
                exclude {
                    it.name.matches(Regex("node_modules|package.json"))
                }
            }
                .filter { it.name.matches(Regex("(build|settings).gradle([.]kts?)")) }
                .filter { it.parentFile.parentFile == baseDir}
                .forEach {
                    if(it.name.startsWith("build")) {
                        includeProjects(it.parentFile, path)
                    }
                    else {
                        apply(from = it)
                    }
                }
        }
}

//includeProjects("pages")
