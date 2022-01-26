plugins {
    id("com.github.turansky.kfc.library")
    `three-declarations`
}

val threeTypesVersion = property("three-types.version") as String

dependencies {
    implementation(npm("@types/three", threeTypesVersion))
}
