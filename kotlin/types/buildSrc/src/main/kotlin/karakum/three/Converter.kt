package karakum.three

import java.io.File

internal data class ConversionResult(
   val name: String,
   val body: String,
)

internal fun convertDefinitions(
   definitionsFile: File,
): Sequence<ConversionResult> =
   definitionsFile.readText()
      .splitToSequence("")
      .map {
         ConversionResult("", "")
      }