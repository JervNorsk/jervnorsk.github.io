plugins {
    id("com.github.turansky.kfc.library")
    `react-router-dom-declarations`
}

val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation(npmv("react-router-dom"))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
}
