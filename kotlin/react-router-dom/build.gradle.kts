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
            api(kotlinWrappers("react-router-dom"))
         }
      }
   }
}
