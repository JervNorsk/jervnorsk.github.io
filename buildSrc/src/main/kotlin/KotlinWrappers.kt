import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

fun Project.kotlinw(module: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$module:${extra["kotlin-wrappers.version"]}"
