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

//// JervNorsk/kotlin-js-types
//includeBuild("../../tools/gradle/plugin") {
//   name = "kotlin-gradle-plugin"
//}