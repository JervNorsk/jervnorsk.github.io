package karakum.typescript

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.Suppress.*
import karakum.common.fileSuppress
import java.io.File

fun generateKotlinDeclarations(
    definitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir
        .resolve("typescript")
        .also { it.mkdirs() }

    for ((name, body) in convertDefinitions(definitionsFile)) {
        val suppresses = mutableListOf<Suppress>().apply {
            if ("JsName(\"\"\"(" in body)
                add(NAME_CONTAINS_ILLEGAL_CHARS)

            if ("companion object" in body || name == "SyntaxKind" || name == "TypePredicateKind" || name == "InvalidatedProjectKind")
                add(NESTED_CLASS_IN_EXTERNAL_INTERFACE)

            if ("override var kind: TypePredicateKind." in body || name == "TupleTypeReference" || name == "CompletionEntryDataResolved" || name == "DiagnosticWithLocation")
                add(VAR_TYPE_MISMATCH_ON_OVERRIDE)

            if ("inline operator fun " in body)
                add(DECLARATION_CANT_BE_INLINED)

            if (name == "ReadonlySet" || name == "Set") {
                add(PARAMETER_NAME_CHANGED_ON_OVERRIDE)
                add(DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES)
            }

            when (name) {
                "NodeArray",
                "SortedArray",
                "SortedReadonlyArray",
                -> {
                    add(EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE)
                    add(INTERFACE_WITH_SUPERCLASS)
                    add(FINAL_SUPERTYPE)
                }
            }
        }.toTypedArray()

        val annotations = when {
            "external val " in body || "external fun " in body || "external class " in body || "external object " in body
            -> "@file:JsModule(\"typescript\")\n@file:JsNonModule"

            suppresses.isNotEmpty() ->
                fileSuppress(*suppresses)

            else -> ""
        }

        val extension = when (name) {
            "CreateProgram" -> "alias.kt"
            else -> "kt"
        }

        val file = targetDir.resolve("$name.$extension")
        if (file.exists()) {
            if (name[0].isLowerCase()) {
                file.appendText("\n$body")
            } else {
                println("Duplicated file: $name")
            }
        } else {
            file.writeText(fileContent(annotations, body))
        }
    }

    targetDir.resolve("ReadonlyArray.kt")
        .writeText(fileContent(body = "typealias ReadonlyArray<T> = Array<out T>"))
}

private fun fileContent(
    annotations: String = "",
    body: String,
): String {
    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        "package typescript",
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
