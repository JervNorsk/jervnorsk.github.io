plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/main/kotlin")
        
        delete(sourceDir)
    
        val nodeModules = rootProject.buildDir.resolve("js/node_modules")
        val threeFile = nodeModules.resolve("@types/three/index.d.ts")
        
        karakum.three.generateKotlinDeclarations(
            threeFile = threeFile,
            sourceDir = sourceDir
        )
    }
}
