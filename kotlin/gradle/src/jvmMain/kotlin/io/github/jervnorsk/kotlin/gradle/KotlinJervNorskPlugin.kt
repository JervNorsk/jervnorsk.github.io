package io.github.jervnorsk.kotlin.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin

abstract class KotlinJervNorskPlugin : Plugin<Project> {
   
   override fun apply(target: Project) {
      with(target) {
         plugins.findPlugin(KotlinMultiplatformPlugin::class.java)?.apply {
         
         }
      }
   }
}