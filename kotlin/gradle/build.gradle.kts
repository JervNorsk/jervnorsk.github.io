plugins {
   kotlin("multiplatform")
}

allprojects {
   version = kotlin.coreLibrariesVersion
   
   afterEvaluate {
      println("$group:$name:$version")
   }
}

kotlin {
   jvm()
   sourceSets {
      val commonMain by getting {
         dependencies {
            implementation(gradleKotlinDsl())
         }
      }
   }
}