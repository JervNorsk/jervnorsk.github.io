import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

fun Project.ktor(module: String): String =
    "io.ktor:ktor-$module:${extra["ktor.version"]}"
