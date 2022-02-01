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

// JervNorsk/kotlin
includeBuild("kotlin") {
   name = "kotlin"
}

// JervNorsk/kotlin-js-types
includeBuild("kotlin/js/types") {
   name = "kotlin-js-types"
}

// JervNorsk/kotlin-js-three
includeBuild("kotlin/js/three") {
   name = "kotlin-js-three"
}