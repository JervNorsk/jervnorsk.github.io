plugins {
   kotlin("multiplatform")
}

buildscript {
   dependencies {
      classpath("io.github.jervnorsk:kotlin-gradle")
   }
}

apply(plugin = "io.github.jervnorsk.kotlin")

allprojects {
   version = kotlin.coreLibrariesVersion
   
   afterEvaluate {
      println("$group:$name:$version")
   }
}

kotlin {
   js {
      browser()
   }
   sourceSets {
      val commonMain by getting {
         dependencies {
            api(kotlinWrappers("typescript"))
         }
      }
   }
}