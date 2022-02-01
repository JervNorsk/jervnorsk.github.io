rootProject.name = "kotlin-js-three"

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

// JervNorsk/kotlin-js-types
includeBuild("../types") {
   name = "kotlin-js-types"
}