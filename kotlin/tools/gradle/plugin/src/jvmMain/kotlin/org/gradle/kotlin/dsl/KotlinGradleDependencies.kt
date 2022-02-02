package org.gradle.kotlin.dsl

import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.plugin.mpp.DefaultKotlinDependencyHandler

fun KotlinDependencyHandler.version(name: String) =
   when (this) {
      is DefaultKotlinDependencyHandler -> project.version(name)
      else                              -> TODO("Implementation not supported yet!")
   }

fun KotlinDependencyHandler.kotlinWrappers(name: String) =
   kotlinWrappers(name, version("kotlin-wrappers"))

fun KotlinDependencyHandler.kotlinWrappers(name: String, version: String?) =
   "org.jetbrains.kotlin-wrappers:kotlin-$name" + version?.let { ":$it" }.orEmpty()