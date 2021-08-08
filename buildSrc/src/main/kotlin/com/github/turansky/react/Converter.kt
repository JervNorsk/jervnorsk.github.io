package com.github.turansky.react

import java.io.File

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    definitionFile: File,
): Sequence<ConversionResult> {
    val content = definitionFile.readText()
        .replace("HTMLTableHeaderCellElement", "HTMLTableCellElement")
        .replace("HTMLTableDataCellElement", "HTMLTableCellElement")
        .replace("HTMLWebViewElement", "HTMLElement")
        .replace("\r\n", "\n")

    val reactContent = content
        .substringAfter("declare namespace React {\n")
        .substringBefore("\n}\n")
        .trimIndent()

    return reactContent.splitToSequence("\ninterface ")
        .drop(1)
        .map { it.substringBefore("\n}\n") }
        .mapNotNull {
            convertInterface(
                name = it.substringBefore(" ")
                    .substringBefore("<"),
                source = it,
            )
        }
        .plus(convertNativeEvents(content))
}

private val NATIVE_EVENT_REPLACEMENT = mapOf(
    "AnimationEvent" to "Event",
    "ClipboardEvent" to "Event",
    "DragEvent" to "MouseEvent",
    "TouchEvent" to "MouseEvent",
    "PointerEvent" to "MouseEvent",
    "TransitionEvent" to "Event",
)

private fun convertNativeEvents(
    source: String,
): ConversionResult {
    val body = source.splitToSequence("\n")
        .filter { it.startsWith("type Native") }
        .joinToString("\n\n") { line ->
            val name = line.removePrefix("type ")
                .substringBefore(" = ")

            val alias = line.substringAfter(" = ")
                .removeSuffix(";")
                .let { NATIVE_EVENT_REPLACEMENT[it] ?: it }

            "typealias $name = org.w3c.dom.events.$alias"
        }


    return ConversionResult("NativeEvents", body)
}

private fun convertInterface(
    name: String,
    source: String,
): ConversionResult? =
    when {
        name.endsWith("Event") -> convertEventInterface(name, source)
        name.endsWith("Attributes") -> convertAttributesInterface(name, source)
        name == "ReactHTML" -> convertIntrinsicTypes(source)
        else -> null
    }

private const val DEFAULT_EVENT_IMPORTS = """
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
"""

private fun convertEventInterface(
    name: String,
    source: String,
): ConversionResult {
    val declaration = source.substringBefore(" {")
        .replaceFirst(" extends ", " : ")
        .replace(" = ", " : ")
        .replace(": object", ": Any")
        .replace(": any", ": Any")
        .replace("EventTarget & T", "T")
        .replace("SyntheticEvent<T>", "SyntheticEvent<T, Event>")

    val members = convertMembers(source)

    val body = DEFAULT_EVENT_IMPORTS.removePrefix("\n") +
            "\nexternal interface $declaration {\n" +
            members +
            "\n}\n"

    return ConversionResult(name, body)
}

private fun convertAttributesInterface(
    name: String,
    source: String,
): ConversionResult {
    var declaration = source.substringBefore(" {")
        .replace(" extends ", " : ")

    if (name == "DOMAttributes")
        declaration += ": react.PropsWithChildren"

    return ConversionResult(name, "external interface $declaration")
}

private fun convertMembers(
    source: String,
): String {
    val content = source
        .substringAfter("{\n", "")
        .trimIndent()

    if (content.isEmpty())
        return ""

    return content.splitToSequence(";\n")
        .joinToString("\n") {
            convertMember(it)
        }
}

private fun convertMember(
    source: String,
): String {
    return source
}

private fun convertIntrinsicTypes(
    source: String,
): ConversionResult {
    val body = source.substringAfter("{\n")
        .trimIndent()
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertIntrinsicType(it) }
        .joinToString("\n\n")

    return ConversionResult("IntrinsicTypes", "import react.IntrinsicType\n\n" + body)
}

private fun convertIntrinsicType(
    source: String,
): String {
    val name = source.substringBefore(": ")
        .removeSurrounding("\"")

    val propsType = source.substringAfter(": DetailedHTMLFactory<")
        .substringBefore(",")
        .replaceFirst("<", "<org.w3c.dom.")
    val type = "IntrinsicType<$propsType>"

    val id = when (name) {
        "object", "var" -> "`$name`"
        else -> name
    }

    return """
        inline val $id: $type
            get() = "$name".unsafeCast<$type>()
    """.trimIndent()
}
