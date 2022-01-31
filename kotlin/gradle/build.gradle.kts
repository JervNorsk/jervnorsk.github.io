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
   jvm {
      mavenPublication {
//         artifactId = artifactId.replace("jvm", "plugin")
         artifactId = "$groupId.kotlin.gradle.plugin"
      }
   }
   sourceSets {
      val commonMain by getting {
         dependencies {
            implementation(gradleKotlinDsl())
         }
      }
   }
}