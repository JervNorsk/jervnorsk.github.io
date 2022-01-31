package karakum.three

import java.io.File

internal enum class Package(
   val baseName: String,
) {
   THREE("three"),
   ;
   
   fun createTargetDir(sourceDir: File) =
      sourceDir.resolve(baseName).also { it.mkdirs() }
   
   val body = "package $baseName"
//    val path = id.replace(".", "/")
   
   fun toContext(file: File): Context =
      Context(this, file)
}