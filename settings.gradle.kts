with(rootProject) {
   fileTree(projectDir)
      .filter {
         it.path.matches(Regex("${projectDir.path}/(\\w+/){1,2}settings[.]gradle.*"))
      }
      .forEach {
         apply(from = it)
      }
}
