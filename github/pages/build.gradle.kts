plugins {
   kotlin("multiplatform")
}

kotlin {
   js {
      browser {
         commonWebpackConfig {
            outputFileName = "index.js"
         }
         runTask {
            devServer?.apply {
               open = false
               static?.set(0, "${static?.first()}/io/github/jervnorsk")
            }
         }
      }
      binaries.executable()
   }
   sourceSets {
      val commonMain by getting {
         dependencies {
            implementation(project(":kotlin:mui"))
            
            implementation(project(":kotlin:react"))
            implementation(project(":kotlin:react-dom"))
            
            implementation(project(":kotlin:react-router"))
            implementation(project(":kotlin:react-router-dom"))

//            implementation("io.github.jervnorsk.kotlin.react:docs")
         }
      }
   }
}