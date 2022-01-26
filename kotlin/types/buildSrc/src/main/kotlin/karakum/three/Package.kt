package karakum.three

internal enum class Package(
    id: String,
    val moduleName: String,
) {
    THREE("three", "three"),
    ;

    val pkg = "package $id"
    val path = id.replace(".", "/")
}
