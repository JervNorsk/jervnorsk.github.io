@file:Suppress("FINAL_UPPER_BOUND")

import org.gradle.api.artifacts.ExternalModuleDependency
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.plugin.mpp.DefaultKotlinDependencyHandler

fun KotlinDependencyHandler.kotlinWrappers(name: String, version: String? = null): ExternalModuleDependency =
   when (this) {
      is DefaultKotlinDependencyHandler -> {
         project.dependencies.create(
            "org.jetbrains.kotlin-wrappers:kotlin-$name:" + (version ?: project.propVersion("kotlin-wrappers"))
         ) as ExternalModuleDependency
      }
      else                              -> TODO("Implement ${this::class}.kotlinWrappers(...)")
   }
