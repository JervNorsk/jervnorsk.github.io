package karakum.three

import java.io.File

internal val IMPORT_SENTENCE =
   Regex("export .* from '(.*)';")

internal data class Import(
   val file: File,
)

internal fun String.splitToImport(
   baseDir: File
) =
   IMPORT_SENTENCE.findAll(this)
      .map { match ->
         match.groupValues[1]
            .replace("./", "")
            .plus(".d.ts")
            .let {
               Import(baseDir.resolve(it))
            }
      }