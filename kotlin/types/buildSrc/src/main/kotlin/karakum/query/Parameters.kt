package karakum.query

fun parseParameters(source: String): List<String> {
    val params = source.substringAfter("(")
        .substringBefore("):")
        .takeIf { it.isNotEmpty() }
        ?: return emptyList()

    when (params) {
        "setup: (setFocused: (focused?: boolean) => void) => () => void",
        "setup: (setOnline: (online?: boolean) => void) => () => void",
        -> {
            val parameter = params
                .replace(" => ", " -> ")
                .replace("?: boolean", ": Boolean?")
                .replace("void", "Unit")

            return listOf(parameter)
        }

        "suspense: boolean | undefined, _useErrorBoundary: boolean | ((err: TError) => boolean) | undefined, error: TError",
        -> {
            return params
                .replace(": boolean | ((", ": ((")
                .replace(" => ", " -> ")
                .replace(" boolean", " Boolean")
                .replace(" | undefined, ", "?, ")
                .split(", ")
        }
    }

    val parts = params.split(": ")
    return sequenceOf(parts.first())
        .plus(
            parts.drop(1).dropLast(1).flatMap {
                sequenceOf(
                    it.substringBeforeLast(", "),
                    it.substringAfterLast(", ")
                )
            }
        )
        .plus(parts.last())
        .windowed(2, 2)
        .map { (nameSource, typeSource) ->
            val name = nameSource.removeSuffix("?")
            val type = kotlinType(typeSource, name).fixDefaultOptions()
            val de = if (nameSource.endsWith("?")) {
                " = definedExternally"
            } else ""

            "$name: $type $de"
        }
        .toList()
}
