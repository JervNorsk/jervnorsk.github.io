package org.gradle.plugins.jervnorsk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.jervnorsk.KotlinGradlePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin
import org.jetbrains.kotlin.gradle.targets.js.dukat.IntegratedDukatTask
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

abstract class KotlinJsTypesPlugin : Plugin<Project> {
   
   override fun apply(target: Project) {
      with(target) {
         plugins.apply(KotlinGradlePlugin::class.java)
         
         tasks.withType<KotlinNpmInstallTask> {}
         tasks.withType<IntegratedDukatTask> {
            actions.clear()
            destinationDir.deleteRecursively()
            doLast {
            
            }
         }
      }
   }
}