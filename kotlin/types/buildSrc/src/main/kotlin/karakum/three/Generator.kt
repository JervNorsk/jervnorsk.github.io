package karakum.three

import java.io.File

fun generateKotlinDeclarations(
   sourceDir: File,
   threeFile: File,
) {
   threeFile.generateKotlinDeclarations(sourceDir, Package.THREE)
}

private fun File.generateKotlinDeclarations(
   sourceDir: File,
   pck: Package
) {
   readText()
}

//fun generateKotlinDeclarations(
//   definitionsFile: File,
//   sourceDir: File,
//) {
//   val targetDir = sourceDir.resolve("three")
//      .also { it.mkdirs() }
//
//   definitionsFile.getImports()
//      .flatMap { it.getImports() }
//      .filter {
//         it.name.startsWith("constants")
//      }
//      .flatMap { convertDefinitions(Package.THREE, it) }
//      .onEach { (pkg, name, body) ->
//         val annotations = when {
//            "external " in body -> "@file:JsModule(\"three\")\n@file:JsNonModule"
//            else                -> ""
//         }
//
//         val file = targetDir.resolve("$name.kt")
//         if (file.exists()) {
//            if (name[0].isLowerCase()) {
//               file.appendText("\n$body")
//            } else {
//               println("Duplicated file: $name")
//            }
//         } else {
//            file.writeText(fileContent(pkg, annotations, body))
//         }
//      }
//}
//
//private fun fileContent(
//   pkg: Package,
//   annotations: String,
//   body: String,
//): String {
//   var result = sequenceOf(
//      "// $GENERATOR_COMMENT",
//      annotations,
//      pkg.body,
//      body,
//   ).filter { it.isNotEmpty() }
//      .joinToString("\n\n")
//
//   if (!result.endsWith("\n"))
//      result += "\n"
//
//   return result
//}
//
//private fun File.getImports() =
//   readText()
//      .replace("\r\n", "\n")
//      .split("\n")
//      .flatMap { Regex("export \\* from '(.*)'").findAll(it) }
//      .map {
//         it.groupValues[1]
//            .replace("./", "")
//            .plus(".d.ts")
//            .let {
//               parentFile.resolve(it)
//            }
//      }