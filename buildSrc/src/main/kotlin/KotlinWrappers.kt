import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun KotlinDependencyHandler.kotlinw(module: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$module:${project.extra["kotlin-wrappers.version"]}"
