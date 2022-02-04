package org.gradle.plugins.jervnorsk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.NpmVersions
import org.jetbrains.kotlin.gradle.targets.js.dukat.IntegratedDukatTask
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

abstract class KotlinJsTypesPlugin : Plugin<Project> {
   
   override fun apply(target: Project) {
      with(target) {
         plugins.apply(KotlinGradlePlugin::class.java)
   
         val registry = findProperty("dukat.npm.registry")
         val owner = findProperty("dukat.npm.registry.owner")
         val token = findProperty("dukat.npm.registry.auth.token")
         
         buildDir.resolve("js")
            .apply {
               if (!exists()) {
                  mkdirs()
               }
            }
            .resolve(".npmrc")
            .apply {
               createNewFile()
               writeText("""
                  //$registry/:_authToken="$token"
                  
                  @$owner:registry=https://$registry
               """.trimIndent())
            }
   
         afterEvaluate {
            extensions.configure(KotlinMultiplatformExtension::class.java) {
               with(it) {
                  sourceSets.findByName("jsMain")?.apply {
                     dependencies {
                        implementation(devNpm("@$owner/dukat", "next"))
                     }
//                  packageJson {
//                     devDependencies["@$owner/dukat"] = "next"
//                  }
                  }
               }
            }
         }
   
         tasks.withType<KotlinNpmInstallTask> {
            doLast {
               buildDir.resolve("js/node_modules").apply {
                     resolve("dukat").let {
                           if (it.exists()) {
                              it.deleteRecursively()
                           }
                     
                           resolve("@$owner/dukat").copyRecursively(it)
                     
                           it.resolve("package.json").apply {
                                 readText().replace("@$owner/", "").apply(::writeText)
                              }
                        }
                  }
            }
         }
   
         tasks.withType<IntegratedDukatTask> {
            destinationDir.deleteRecursively()
            doLast { task ->
               buildDir.apply {
                  resolve("js/node_modules/dukat/package.json").run {
                        Regex("\\s+\"version\":\\s+\"(.*)\"").findAll(readText()).first().groups[1]?.value ?: "undefined"
                     }.also { version ->
                        resolve("externals/${project.name}/version.txt").apply {
                              createNewFile()
                              readText().replace(NpmVersions().dukat.version, version).also { content ->
                                    writeText(content)
                                 }
                           }
                     }
               }
            }
         }
      }
   }
}