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

// JervNorsk/kotlin-js-types
includeBuild("kotlin/js/types")

// GitHub Pages
//include(":github:pages")

// JetBrains/kotlin-wrappers
//includeBuild("kotlin/wrappers")

// MUI
//include(":kotlin:mui")
//
//// Kotlin/JS React
//include(":kotlin:react")
//include(":kotlin:react-dom")
//
//// Kotlin/JS React Router
//include(":kotlin:react-router")
//include(":kotlin:react-router-dom")