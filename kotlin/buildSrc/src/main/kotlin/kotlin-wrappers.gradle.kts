import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

val kotlinWrappersVersion = extra["kotlinw.version"] as String

println("TEST")

fun KotlinDependencyHandler.kotlinw(module: String): ExternalModuleDependency =
   project.dependencies.create(
      "org.jetbrains.kotlin-wrappers:kotlin-$module:$kotlinWrappersVersion"
   ) as ExternalModuleDependency