plugins {
   kotlin("multiplatform")
   `kotlin-wrappers`
}

kotlin {
   js(IR) {
      nodejs()
   }
   sourceSets {
      val commonMain by getting {
         dependencies {
//            api(kotlinw("react"))
         }
      }
   }
}
