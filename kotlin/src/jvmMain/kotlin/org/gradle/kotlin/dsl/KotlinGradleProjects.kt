package org.gradle.kotlin.dsl

import org.gradle.api.Project

fun Project.version(name: String) =
   project.extra["$name.version"] as String