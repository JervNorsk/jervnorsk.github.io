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
            api("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")
   
            implementation(project(":kotlin:react"))
            implementation(project(":kotlin:react-dom"))
   
            implementation(project(":kotlin:react-router"))
         }
      }
   }
}
