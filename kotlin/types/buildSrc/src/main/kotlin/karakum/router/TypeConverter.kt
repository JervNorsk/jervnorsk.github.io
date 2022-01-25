package karakum.router

internal fun convertType(
    name: String,
    source: String,
): String {
    val body = source.substringAfter(" = ")
        .removeSuffix(";")

    val alias = when {
        name == "Params" -> "kotlinext.js.Record<String, String>"
        name == "URLSearchParamsInit" -> "kotlinext.js.Record<String, String> // $body"

        body == "string" -> "String"
        body == "unknown" -> "Any?"
        body == "object | null" -> "Any?"
        body == "[string, string]" -> "kotlinext.js.JsPair<String, String>"

        body.startsWith("Partial<") -> "Any // $body"
        body.startsWith("Omit<") -> "Any // $body"
        body.startsWith("Pick<") -> "Any // $body"
        body.startsWith("string | ") -> "String // $body"

        else -> null
    }

    if (alias != null)
        return "typealias $name = $alias"

    if (" = {\n" in source)
        return convertInterface(
            name = name,
            source = source
                .replaceFirst("type ", "interface ")
                .replaceFirst(" = {", " {")
        )

    return source
}
