package org.gradle.plugins.jervnorsk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.closureOf
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.jervnorsk.KotlinGradlePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin
import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget
import org.jetbrains.kotlin.gradle.targets.js.dukat.IntegratedDukatTask
import org.jetbrains.kotlin.gradle.targets.js.npm.KotlinNpmResolutionManager
import org.jetbrains.kotlin.gradle.targets.js.npm.resolved.KotlinProjectNpmResolution
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

abstract class KotlinJsTypesPlugin : Plugin<Project> {
   
   override fun apply(target: Project) {
      with(target) {
         plugins.apply(KotlinGradlePlugin::class.java)
   
         println("TEST 2")
         
         plugins.withType<KotlinMultiplatformPlugin> {
            kotlinExtension.apply {
               println("TEST 3")
            }
            the<KotlinMultiplatformExtension>().apply {
               println("TEST")
//               js {
//                  compilations["main"].packageJson {
//                     devDependencies["dukat"] = "test"
//                  }
//               }
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