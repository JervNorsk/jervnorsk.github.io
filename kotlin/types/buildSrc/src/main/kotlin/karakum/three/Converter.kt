package karakum.three

import java.io.File

internal data class ConversionResult(
   val pkg: Package,
   val name: String,
   val body: String
)

internal fun convertDefinitions(
   pkg: Package,
   definitionsFile: File,
) =
   definitionsFile.readText()
      .splitToSequence("export ")
      .map { it.replace(Regex("//.*\n"), "") }
      .map { it.trim() }
      .map { it.convertDefinition(pkg) }
      .filterNotNull()
      .onEach {
         println("######")
         println(it)
      }

private fun String.convertDefinition(
   pkg: Package
): ConversionResult? {
   println("######")
//   println(this)
   
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
   
   //   println("------")
   val body = sequenceOf(content)
      .filterNotNull()
      .joinToString("\n")
   
   return ConversionResult(pkg, name, body)
}

private fun convertConst(
   name: String,
   source: String,
) =
   "external val $name: dynamic"