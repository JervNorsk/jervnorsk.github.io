plugins {
   kotlin("multiplatform")
   id("io.github.jervnorsk.kotlin")
}

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
//            api("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")
         }
      }
   }
}
