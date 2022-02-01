rootProject.name = "kotlin"

pluginManagement {
   resolutionStrategy {
      plugins {
         kotlin("multiplatform") version(extra["kotlin.version"] as String)
      }
   }
}

dependencyResolutionManagement {
   repositories {
      mavenCentral()
   }
}