plugins {
   kotlin("multiplatform")
}

buildscript {
   dependencies {
      classpath("io.github.jervnorsk:kotlin-js-types")
   }
}

apply(plugin = "io.github.jervnorsk.kotlin-js-types")

allprojects {
   version = version("three")

   afterEvaluate {
      println("$group:$name:$version")
   }
}

kotlin {
   js {
      browser()
   }
   sourceSets {
      val jsMain by getting {
         dependencies {
            api(npm("three", version("three")))

            implementation(npm("@types/three", version("three"), generateExternals = true))
         }
      }
   }
}