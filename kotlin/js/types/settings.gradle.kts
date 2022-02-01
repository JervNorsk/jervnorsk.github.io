rootProject.name = "kotlin-js-types"

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

// JervNorsk/kotlin-gradle
includeBuild("../../gradle")

// JetBrains/kotlin-wrappers
//includeBuild("../../wrappers")