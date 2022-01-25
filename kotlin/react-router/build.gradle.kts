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
            implementation(project(":kotlin:react"))
         }
      }
   }
}
