plugins {
   kotlin("multiplatform")
   id("maven-publish")
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
      val jvmMain by getting {
         dependencies {
            implementation(gradleKotlinDsl())
            implementation(kotlin("gradle-plugin"))
         }
      }
   }
}