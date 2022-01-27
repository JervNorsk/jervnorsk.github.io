plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/main/kotlin")
        
        delete(sourceDir)
    
        rootProject.buildDir.resolve("js/node_modules").apply {
            
            karakum.three.generateKotlinDeclarations(
                sourceDir = sourceDir,
                threeFile = resolve("@types/three/index.d.ts"),
            )
        }
    }
}