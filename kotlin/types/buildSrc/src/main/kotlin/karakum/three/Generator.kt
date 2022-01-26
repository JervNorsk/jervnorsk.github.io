package karakum.three

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

fun generateKotlinDeclarations(
   threeFile: File,
   sourceDir: File,
) {
   generate(threeFile, sourceDir, Package.THREE)
}

private fun generate(
   definitionsFile: File,
   sourceDir: File,
   pkg: Package
) {
   val targetDir = sourceDir.resolve(pkg.path)
      .also { it.mkdirs() }
   
   for ((name, body) in convertDefinitions(definitionsFile)) {
      val suppresses = mutableListOf<Suppress>().apply {
         if ("JsName(\"\"\"(" in body)
            add(Suppress.NAME_CONTAINS_ILLEGAL_CHARS)
         
         if ("inline operator fun " in body)
            add(Suppress.NOTHING_TO_INLINE)
      }.toTypedArray()
      
      val annotations = when {
         "external val " in body || "external fun " in body
              -> "@file:JsModule(\"${pkg.moduleName}\")\n@file:JsNonModule"
         
         suppresses.isNotEmpty()
              -> fileSuppress(*suppresses)
         
         else -> ""
      }
      
      targetDir.resolve("$name.kt")
         .writeText(fileContent(pkg, annotations, body))
   }
}

private fun fileContent(
   pkg: Package,
   annotations: String,
   body: String,
): String {
   return sequenceOf(
      "// $GENERATOR_COMMENT",
      annotations,
      pkg.pkg,
      body,
   ).filter { it.isNotEmpty() }
      .joinToString("\n\n")
}
