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

// GitHub Pages
include(":github:pages")

// Kotlin/JS React
include(":kotlin:react")
include(":kotlin:react:docs")

// Kotlin/JS React DOM
include(":kotlin:react-dom")

// Kotlin/JS React Router
include(":kotlin:react-router")
include(":kotlin:react-router-dom")