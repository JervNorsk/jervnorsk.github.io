plugins {
    id("com.github.turansky.kfc.library")
    `three-declarations`
}

val threeVersion = property("three.version") as String

dependencies {
    implementation(npm("@types/three", threeVersion))
}
