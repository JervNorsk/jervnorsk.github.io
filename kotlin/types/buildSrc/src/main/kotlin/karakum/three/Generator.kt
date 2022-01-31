package karakum.three

import karakum.common.GENERATOR_COMMENT
import java.io.File

fun generateKotlinDeclarations(
   sourceDir: File,
   threeFile: File,
) {
//   threeFile.generateKotlinDeclarations(sourceDir, Package.THREE)
}

private fun File.generateKotlinDeclarations(
   sourceDir: File,
   pkg: Package
) {
   val targetDir = pkg.createTargetDir(sourceDir)
   
   convertDefinitions(pkg.toContext(parentFile))
      .forEach { (context, body) ->
         val annotations = when {
            "external " in body -> "@file:JsModule(\"three\")\n@file:JsNonModule"
            else                -> ""
         }
         
         val filePath = context.file.relativeTo(this)
            .path
            .replace("../src/", "")
            .replace(".d.ts", ".kt")
         
         val file = targetDir.resolve(filePath)
            .also { it.parentFile.mkdirs() }
         
         if (file.exists()) {
            if (name[0].isLowerCase()) {
               file.appendText("\n$body")
            } else {
               println("Duplicated file: $name")
            }
         } else {
            file.fileContent(pkg, annotations, body)
         }
      }
}

private fun File.fileContent(
   pkg: Package,
   annotations: String,
   body: String,
) {
   var result = sequenceOf(
      "// $GENERATOR_COMMENT",
      annotations,
      pkg.body + parentFile.path
         .replace(Regex(".*/${pkg.baseName}"), "")
         .replace("/", "."),
      body,
   ).filter { it.isNotEmpty() }
      .joinToString("\n\n")
   
   if (!result.endsWith("\n"))
      result += "\n"
   
   writeText(result)
}
