plugins {
    kotlin("js")
}

kotlin {
    js {
        expo {
            useWeb()
        }
    }
    sourceSets {
        val main by getting {
            dependencies {
                implementation(npm("native-base", "latest"))

                implementation(npm("@expo-google-fonts/outfit", "latest"))
            }
        }
    }
}
