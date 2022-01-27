package karakum.common

import java.io.File

internal fun File.getImports() =
   readText()
      .replace("\r\n", "\n")
      .split("\n")
      .flatMap { Regex("export \\* from '(.*)'").findAll(it) }
      .map {
         it.groupValues[1]
            .replace("./", "")
            .plus(".d.ts")
            .let {
               parentFile.resolve(it)
            }
      }