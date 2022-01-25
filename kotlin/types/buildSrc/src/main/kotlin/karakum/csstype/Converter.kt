package karakum.csstype

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionsFile: File,
): Sequence<ConversionResult> {
    var types = definitionsFile.readText()
        .removePrefix("export {};\n")
        .inlineTypes()
        .replace("DeprecatedSystemColor | ", "")
        .splitToSequence("\nexport ", "\ndeclare ")
        .drop(1)
        .flatMap { content ->
            val name = content.substringAfter(" ")
                .substringBefore(" ")
                .substringBefore("<")

            when {
                name == "PropertyValue" -> emptySequence()
                name.startsWith("Svg") -> emptySequence()
                name.startsWith("Obsolete") -> emptySequence()
                name.startsWith("Vendor") -> emptySequence()
                name.contains("Hyphen") -> emptySequence()
                name.contains("Fallback") -> emptySequence()
                name == "Pseudos" -> sequenceOf(convertMetaPseudos(name, content))
                name == "SimplePseudos" || name == "AdvancedPseudos" -> sequenceOf(convertPseudos(name, content))
                content.startsWith("namespace AtRule ") -> convertNamespace(content) + convertNamespaceTypes(content, AT_RULE_TYPES)
                content.startsWith("namespace DataType ") -> convertNamespaceTypes(content)
                content.startsWith("namespace ") -> convertNamespace(content)
                else -> sequenceOf(convertDefinition(name, content))
            }
        }
        .toList()

    val globalsContext = ParentContext("Globals")
    val lengthContext = ParentContext(LENGTH_PROPERTY)
    val timeContext = ParentContext(TIME_PROPERTY)

    types = sequenceOf(
        LengthTypeConsumer(),
        PropertyConsumer(),
        globalsContext,
        lengthContext,
        timeContext,
        ColorConsumer(),
    ).fold(types) { t, context ->
        context.apply(t)
    }

    val globalsParentTypes = globalsContext.parentTypes
        .asSequence()
        .plus(LENGTH_PROPERTY)
        .plus(GRID_LENGTH_PROPERTY)
        .plus(TIME_PROPERTY)
        .plus(COLOR_PROPERTY)
        .plus(GRID_LINE_PROPERTY)
        .plus(LINE_STYLE_PROPERTY)
        .plus(LINE_WIDTH_PROPERTY)
        .plus(BLEND_MODE_PROPERTY)
        .sorted()
        .joinToString(",\n")

    val globalsType = ConversionResult(
        "GlobalsType",
        "sealed external interface GlobalsType:\n$globalsParentTypes",
    )

    val propertyTypes = listOf(
        Length(),
        LengthProperty(lengthContext),
        AutoLengthProperty(),
        GridLength(),
        GridLengthProperty(),
        Time(),
        TimeProperty(timeContext),
        GridLineProperty(),
        LineStyleProperty(),
        LineWidthProperty(),
        BlendModeProperty(),
    )

    val builderTypes = listOf(
        CssDsl(),
        RuleBuilder(),
    )

    return types.asSequence()
        .plus(propertyTypes)
        .plus(globalsType)
        .plus(builderTypes)
}

private fun convertNamespace(
    source: String,
): Sequence<ConversionResult> {
    return source
        .substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .let { "\n" + it }
        .splitToSequence("\nexport ")
        .drop(1)
        .mapNotNull { content ->
            val name = content.substringAfter(" ")
                .substringBefore(" ")
                .substringBefore("<")

            when {
                name.startsWith("Moz") -> null
                name.startsWith("Ms") -> null
                name.startsWith("Webkit") -> null
                name.contains("Hyphen") && name != "Hyphens" -> null
                name.contains("Fallback") -> null
                name in DEPRECATED_TYPES -> null
                name in SVG_TYPES -> null
                else -> convertDefinition(name, content)
            }
        }
}

private val AT_RULE_TYPES = setOf(
    "FontDisplay",
    "Size",
    "Inherits",
    "AlignContent",
    "Hyphens",
    "Range",
    "SpeakAs",
    "System",

    "AscentOverride",
    "DescentOverride",
    "LineGapOverride",
    "Bleed",
    "Marks",
)

private val EXCLUDED_DATA_TYPES = setOf(
    "Paint",
    "Position",

    "Dasharray",
    "ViewportLength",

    "DeprecatedSystemColor",
)

private fun convertNamespaceTypes(
    source: String,
    enabledTypes: Set<String>? = null,
): Sequence<ConversionResult> {
    val typeEnabled: (String) -> Boolean = when {
        enabledTypes != null
        -> enabledTypes::contains

        else -> { type: String -> type !in EXCLUDED_DATA_TYPES }
    }

    return source
        .substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .let { "\n" + it }
        .splitToSequence("\ntype ")
        .drop(1)
        .map { it.replace("<TLength>", "") }
        .map { it.replace("<TTime>", "") }
        .mapNotNull { content ->
            val name = content
                .substringBefore(" ")
                .substringBefore("<")

            if (typeEnabled(name)) {
                convertUnion(name, content, true)
            } else null
        }
}

private fun convertDefinition(
    name: String,
    source: String,
): ConversionResult {
    val content = source
        .replace("TLength = (string & {}) | 0", "TLength")
        .replace("TTime = string & {}", "TTime")
        .replace("<TLength, TTime>", "")
        .replace("<TLength>", "")
        .replace("<TTime>", "")

    if (content.startsWith("type "))
        return convertUnion(name, content)

    return convertInterface(name, content)
}

private val ENUMS = setOf(
    "Globals",
    "Marks",
    "SpeakAs",
    "System",

    "AtRules",
    "HtmlAttributes",
    "AdvancedPseudos",
    "SimplePseudos",
)

private val ENUM_TAIL = " | (string & {})"

private fun convertUnion(
    name: String,
    source: String,
    enumMode: Boolean = false,
): ConversionResult {
    if (source.startsWith("type Color ="))
        return convertUnion(
            name = COLOR_PROPERTY,
            source = source.replaceFirst("Color", COLOR_PROPERTY)
        )

    if (name == BACKGROUND && "| Color " in source)
        return convertUnion(
            name = name,
            source = source.replaceFirst("| Color ", "")
        )

    if (name in ENUMS && (!enumMode || ENUM_TAIL in source))
        return convertUnion(
            name = name,
            source = source.replaceFirst(ENUM_TAIL, ""),
            enumMode = true,
        )

    val declaration = source.removePrefix("type ")
        .substringBefore(" =")

    val body = source.substringAfter(" =")
        .removePrefix("\n")
        .trimIndent()
        .removeSuffix(";")

    when (body) {
        "Globals | (number & {})",
        "Globals | (number & {}) | (string & {})",
        "Globals | \"auto\" | (number & {})",
        "Globals | \"auto\" | (number & {}) | (string & {})",
        "Globals | \"none\" | (number & {})",
        "Globals | \"none\" | (number & {}) | (string & {})",
        -> return convertNumberType(name, source)
    }

    if ("(number & {})" in source) {
        when (name) {
            "FontWeight",
            "Opacity",
            "Scale",
            "Zoom",
            -> return convertNumberType(name, source.replace(" | (string & {})", ""))
        }
    }

    if (name == "FontWeight" && "(number & {})" in source)
        return convertNumberType(name, source)

    if (body == """"false" | "true"""")
        return ConversionResult(name, "typealias $name = Boolean")

    tryToUnion(name, body, enumMode)
        ?.let { return it }

    when (name) {
        "ColumnGap",
        "RowGap",
        -> return ConversionResult(name, "typealias $name = Gap")

        "Outline",
        -> return tryToUnion(
            name = name,
            body = body.replace("DataType.Color | DataType.LineStyle | DataType.LineWidth | ", ""),
            enumMode = false,
        )!!
    }

    val values = body
        .splitToSequence("\n", " | ")
        .map { it.removePrefix("| ") }
        .filter { !it.startsWith("\"-moz-") }
        .filter { !it.startsWith("\"-ms-") }
        .filter { !it.startsWith("\"-webkit-") }
        .joinToString(" | ")

    tryToAlias(name, values)
        ?.let { return it }

    return ConversionResult(name, "// $values\nsealed external interface $declaration")
}

private val INT_TYPES = setOf(
    "Order",
    "Orphans",
    "Widows",

    "ColumnCount",
    "LineClamp",
    "MaxLines",
    "ZIndex",

    "FontWeight",
)

private val DOUBLE_TYPES = setOf(
    "FlexGrow",
    "FlexShrink",

    "Opacity",
    "Scale",
    "Zoom",
)

private fun convertNumberType(
    name: String,
    source: String,
): ConversionResult {
    val type = when (name) {
        in INT_TYPES -> "Int"
        in DOUBLE_TYPES -> "Double"
        else -> TODO("Support number type for `$name`")
    }

    val bodySource = source
        .replace(" | (number & {}) | (string & {})", "")
        .replace(" | (number & {})", "")

    val mainBody = convertUnion(name, bodySource).body

    val body = """
        $mainBody
        
        inline fun $name(value: $type): $name =
            value.unsafeCast<$name>()
    """.trimIndent()

    return ConversionResult(
        name = name,
        body = body,
    )
}

private val LINK_REGEX = Regex("( * @see )(.+)")

private val BOLD_1_REGEX = Regex("""(\|\s+)\*\*""")
private val BOLD_2_REGEX = Regex("""\*\*(\s+\|)""")
private val BOLD_3_REGEX = Regex("""\*\*( _\()""")

private val PRE_1_REGEX = Regex("""([\d.]+) _-x-_""")
private val PRE_2_REGEX = Regex("""([\d.]+)\*\* _-x-_""")

private fun convertInterface(
    name: String,
    source: String,
): ConversionResult {
    val declaration = source
        .removePrefix("interface ")
        .substringBefore("\n")
        .substringBefore(" {")

    val extends = source
        .substringBefore("{")
        .substringAfter(" extends ", "")

    if (extends.isNotEmpty()) {
        val annotations = when (name) {
            "Properties" -> "@$CSS_DSL\n"
            else -> ""
        }

        val parentTypes = extends.replace("\n", "")
            .splitToSequence(",")
            .map { it.trim() }
            .filter { "Vendor" !in it && "Obsolete" !in it && "Svg" !in it }
            .joinToString(", ")

        return ConversionResult(name, "$annotations external interface $declaration: $parentTypes")
    }

    val body = source.substringAfter("{\n")
        .substringBefore("\n}")
        .trimIndent()
        .replace("\n * | ", "\n *    | ")
        .replace(" ", " ")
        .replace(LINK_REGEX, """$1<a href="$2">MDN</a>""")
        .replace(BOLD_1_REGEX, "$1  ")
        .replace(BOLD_2_REGEX, "  $1")
        .replace(BOLD_3_REGEX, "  $1")
        .replace(PRE_1_REGEX, "   $1′  ")
        .replace(PRE_2_REGEX, "    $1′   ")
        .splitToSequence("\n")
        .map {
            if ("?: " in it) {
                var (pname, ptype) = it.split("?: ")
                ptype = ptype
                    .replace(" | undefined;", ";")
                    .replace("Property.Color;", COLOR_PROPERTY)
                    .removePrefix("Property.")
                    .removeSuffix(";")

                if (ptype == "string") ptype = "String"
                "var $pname: $ptype?"
            } else it
        }
        .joinToString("\n")
        .replaceIndent("    ")

    return ConversionResult(name, "external interface $declaration{\n$body\n}")
}
