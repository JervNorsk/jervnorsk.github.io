package karakum.three

internal enum class Package(
   id: String,
   val moduleName: String,
) {
   THREE("three", "three"),
   ;

//    val body = "package $id"
//    val path = id.replace(".", "/")
   
   fun toContext() : Context =
      Context(this)
}