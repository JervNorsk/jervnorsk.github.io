pluginManagement {
   resolutionStrategy {
      plugins {
         val kotlinVersion = extra["kotlin.version"] as String
         
         kotlin("multiplatform") version kotlinVersion
         kotlin("plugin.serialization") version kotlinVersion
      }
   }
}

dependencyResolutionManagement {
   repositories {
      mavenCentral()
   }
}

// Kotlin/JS: React
include("react")