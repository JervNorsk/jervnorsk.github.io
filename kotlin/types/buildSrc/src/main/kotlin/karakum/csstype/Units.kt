package karakum.csstype

fun unitsExtension(
    type: String,
    name: String,
    suffix: String,
): String =
    """
    inline val Number.$name: $type
        get() = "${'$'}{this}$suffix".unsafeCast<$type>()
    """.trimIndent()
