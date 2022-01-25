plugins {
   kotlin("multiplatform")
}

kotlin {
   js {
      browser()
   }
   sourceSets {
      val commonMain by getting {
         dependencies {
            api("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
   
            implementation(project(":kotlin:react"))
         }
      }
   }
}
