rootProject.name = "kotlin-js-types"

pluginManagement {
   resolutionStrategy {
      plugins {
         kotlin("multiplatform") version(extra["kotlin.version"] as String)
         id("io.github.jervnorsk.kotlin") version(extra["kotlin.version"] as String)
      }
      repositories {
         mavenLocal()
         mavenCentral()
         gradlePluginPortal()
      }
   }
}

dependencyResolutionManagement {
   repositories {
      mavenCentral()
   }
}

// JetBrains/kotlin-wrappers
//includeBuild("../../wrappers")