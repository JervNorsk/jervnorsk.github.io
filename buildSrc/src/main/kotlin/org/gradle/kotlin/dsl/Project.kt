import org.gradle.api.Project

fun Project.propVersion(target: String): String =
   prop("$target.version")