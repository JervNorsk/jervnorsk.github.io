package org.gradle.plugins.jervnorsk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin

abstract class KotlinGradlePlugin : Plugin<Project> {
   
   override fun apply(target: Project) {
      with(target) {
         plugins.withType<KotlinMultiplatformPlugin> {
            the<KotlinMultiplatformExtension>().apply {}
         }
      }
   }
}