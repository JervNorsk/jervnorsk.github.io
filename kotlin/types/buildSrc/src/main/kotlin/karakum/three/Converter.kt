package karakum.three

import java.io.File

internal data class ConversionResult(
   val context: Context,
   val name: String,
   val body: String
)

internal fun File.convertDefinitions(
   context: Context,
) =
   readText()
      .convertDefinitions(context, parentFile)

private fun String.convertDefinitions(
   context: Context,
   baseDir: File,
): Sequence<ConversionResult> {
   var result = replace("\r\n", "\n")
      .splitToSequence("export ")
      .map { it.replace(Regex("//.*\n"), "") }
      .map { it.trim() }
      .map { it.convertDefinition(context) }
      .filterNotNull()
   
   splitToImport(baseDir)
      .toList()
//      .apply {
//         if(this.isNotEmpty())
//            println("#### Detected Imports")
//         forEach { println(it) }
//      }
      .forEach { import ->
         import.file.convertDefinitions(context)
            .forEach {
               result = result.plus(it)
            }
      }
   
   return result
}

private fun String.convertDefinition(
   context: Context
): ConversionResult? {
   val name = substringAfter(" ")
      .substringBefore(" ")
      .substringBefore("<")
      .substringBefore("(")
      .substringBefore(":")
   
   val type = substringBefore(" ")
   val content = when (type) {
      "const" -> ::convertConst
      else    -> null
   }?.invoke(name, this.removePrefix("$type ")) ?: return null
   
   val body = sequenceOf(content)
      .joinToString("\n")
   
   return ConversionResult(context, name, body)
}

private fun convertConst(
   name: String,
   source: String,
) =
   "external val $name: dynamic"