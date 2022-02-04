plugins {
   kotlin("multiplatform")
}

buildscript {
   dependencies {
      classpath("io.github.jervnorsk:kotlin-gradle-plugin")
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
      nodejs()
   }
   jvm()
   sourceSets {
      val jvmMain by getting {
         dependencies {
            api("io.github.jervnorsk:kotlin-gradle-plugin")
   
            implementation(gradleKotlinDsl())
            implementation(kotlin("gradle-plugin"))
         }
      }
      val jsMain by getting {
         dependencies {
//            implementation(peerNpm("dukat", "next"))
         }
      }
   }
}