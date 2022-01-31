package karakum.three

import karakum.common.UnionConstant
import karakum.common.unionBodyByConstants
import karakum.typescript.kotlinType
import java.io.File

internal data class ConversionResult(
   val context: Context,
   val body: String
)

internal fun File.convertDefinitions(
   context: Context,
) =
   readText()
      .convertDefinitions(context.with(this))

private fun String.convertDefinitions(
   context: Context,
): Sequence<ConversionResult> {
   var result = replace("\r\n", "\n")
      .splitToSequence("export ")
      .map { it.replace(Regex("//.*\n"), "") }
      .map { it.trim() }
      .map { it.convertDefinition(context) }
      .filterNotNull()
   
   splitToImport(context.file.parentFile)
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
//      "let" to ::convertLet,
      "const" -> ::convertConst
//      "function" -> ::convertFunction
//      "type" to ::convertType,
      "enum"  -> ::convertEnum
//      "interface" to ::convertInterface,
//      "class" to ::convertClass,
//      "namespace" to ::convertNamespace,
      else    -> null
   }?.invoke(name, this.removePrefix("$type ").substringBefore(";")) ?: return null
   
   val body = sequenceOf(content)
      .joinToString("\n")
   
   return ConversionResult(context, body)
}

private fun convertConst(
   name: String,
   source: String,
): String {
   val body = source.substringAfter(": ")
   
   return "external val $name: ${kotlinType(body, name)}"
}

private fun convertEnum(
   name: String,
   source: String,
): String {
   println("############")
   println(name)
   println("----")
   
   val constants = source
      .substringAfter("{")
      .substringBeforeLast("}")
      .trimIndent()
      .splitToSequence(",")
      .onEach {
         it.trim()
      }
      .filter { it.isNotBlank() }
      .map {
         println("CONSTANT: $it")
         val centry = it.substringAfterLast("\n").split(",")
         val cname = it.substringBefore("=").trim()
         val cvalue = it.substringAfter("=").trim()
         UnionConstant(
            kotlinName = cname,
            jsName = cname,
            value = cvalue,
            originalValue = true
         )
         
//         val (cname, cvalue) = it.substringAfterLast("\n").split(" = ")
//         val comment = it.substringBeforeLast("\n", "").ifEmpty { null }
//         UnionConstant(
//            kotlinName = cname,
//            jsName = cname,
//            value = cvalue,
//            originalValue = true,
//            comment = comment,
//         )
      }
      .toList()
   
   if (constants.isNotEmpty()) {
      return unionBodyByConstants(name, constants)
   } else {
      return "external sealed interface $name"
   }
}

//private fun convertEnum(
//   name: String,
//   source: String,
//): String {
//   val constants = source
//      .substringAfter("{\n")
//      .substringBeforeLast("\n}")
//      .trimIndent()
//      .splitToSequence(",\n")
//      .map {
//         val (cname, cvalue) = it.substringAfterLast("\n").split(" = ")
//         val comment = it.substringBeforeLast("\n", "").ifEmpty { null }
//         UnionConstant(
//            kotlinName = cname,
//            jsName = cname,
//            value = cvalue,
//            originalValue = true,
//            comment = comment,
//         )
//      }
//      .toList()
//
//   val result = unionBodyByConstants(name, constants)
//   if (name != "SyntaxKind" && name != "TypePredicateKind" && name != "InvalidatedProjectKind")
//      return result
//
//   return result.replaceFirst(" enum class ", " sealed interface ")
//      .replaceFirst(";\n", "")
//      .splitToSequence("\n")
//      .joinToString("\n") {
//         if (it.endsWith(",")) {
//            "object " + it.removeSuffix(",") + ": $name"
//         } else it
//      }
//}

//private fun convertConst(
//   name: String,
//   source: String,
//): String {
//   if (" = " in source)
//      return "const val $source"
//
//   val body = source.substringAfter(": ")
//
//   if (body.startsWith("(") || body.startsWith("<")) {
//      val functionSource = when (name) {
//         "createTempVariable" -> body.replace(") => Identifier", "): Identifier")
//         else -> body.replace(") => ", "): ")
//      }
//
//      return convertFunction(
//         name = name,
//         source = name + functionSource,
//      )
//   }
//
//   return "external val $name: ${kotlinType(body, name)}"
//}

//private fun convertFunction(
//   name: String,
//   source: String,
//): String {
//   val content = when (name) {
//      "createIncrementalProgram",
//           -> source.replace(
//         "{ rootNames, options, configFileParsingDiagnostics, projectReferences, host, createProgram }",
//         "options",
//      )
//
//      "readConfigFile",
//      "parseConfigFileTextToJson",
//           -> source.replace(CONFIG_PROVIDER_BODY, CONFIG_PROVIDER)
//
//      "convertCompilerOptionsFromJson",
//           -> source.replace(
//         OPTIONS_PROVIDER_BODY.replace(": T", ": CompilerOptions"),
//         "$OPTIONS_PROVIDER<CompilerOptions>",
//      )
//
//      "convertTypeAcquisitionFromJson",
//           -> source.replace(
//         OPTIONS_PROVIDER_BODY.replace(": T", ": TypeAcquisition"),
//         "$OPTIONS_PROVIDER<TypeAcquisition>",
//      )
//
//      else -> source
//   }
//
//   val result = "external ${convertMethod(content)}"
//      .replace(" = EmitAndSemanticDiagnosticsBuilderProgram", " /* = EmitAndSemanticDiagnosticsBuilderProgram */")
//
//   return if (name.endsWith("CommentRange")) {
//      result.replace(": number", ": Int")
//         .replace(": boolean", ": Boolean")
//         .replace(" => U", " -> U")
//   } else result
//}