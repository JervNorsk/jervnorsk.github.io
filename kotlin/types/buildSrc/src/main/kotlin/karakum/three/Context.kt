package karakum.three

import java.io.File

internal data class Context(
    val pkg: Package,
    val file: File
) {
    
    fun with(file: File): Context =
        Context(pkg, file)
}