rootProject.name = "kotlin-js-types"

pluginManagement {
   resolutionStrategy {
      plugins {
         val kotlinVersion = extra["kotlin.version"] as String
         
         kotlin("multiplatform") version kotlinVersion
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