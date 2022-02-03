package org.gradle.plugins.jervnorsk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.dukat.IntegratedDukatTask
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import org.jetbrains.kotlin.gradle.targets.js.yarn.yarn

abstract class KotlinJsTypesPlugin : Plugin<Project> {
   
   override fun apply(target: Project) {
      with(target) {
         plugins.apply(KotlinGradlePlugin::class.java)
   
         val registry = findProperty("dukat.npm.registry")
         val owner = findProperty("dukat.npm.registry.owner")
         val token = findProperty("dukat.npm.registry.auth.token")
         
         buildDir.resolve("js")
            .apply { println(this) }
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
   
         extensions.configure(KotlinMultiplatformExtension::class.java) {
            with(it) {
               js {
                  compilations["main"].packageJson {
                     devDependencies["@$owner/dukat"] = "latest"
                  }
               }
            }
         }
   
         tasks.withType<KotlinNpmInstallTask> {
         
         }
         tasks.withType<IntegratedDukatTask> {
            destinationDir.deleteRecursively()
            actions.clear()
         }
      }
   }
}