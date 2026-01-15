abstract class GenerateLocalPropertiesTask : DefaultTask() {
    @get:InputFile
    abstract val localPropertiesFile: RegularFileProperty

    @get:Input
    abstract val packageName: Property<String>

    @get:Input
    abstract val className: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val props = java.util.Properties()
        localPropertiesFile.get().asFile.takeIf { it.exists() }?.inputStream()?.use { props.load(it) }
        val baseUrl = props.getProperty("base.url", "")

        val file = outputDir.file("${packageName.get().replace('.', '/')}/${className.get()}.kt").get().asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            package ${packageName.get()}
            object ${className.get()} {
                const val BASE_URL = "$baseUrl"
            }
            """.trimIndent()
        )
    }
}

val generateLocalProperties =
    tasks.register<GenerateLocalPropertiesTask>("generateLocalProperties") {
        localPropertiesFile.set(rootProject.file("local.properties"))
        packageName.set("fr.ab_dev.data")
        className.set("LocalProperties")
        outputDir.set(file("$buildDir/generated/localProperties"))
    }

tasks.named("preBuild") {
    dependsOn(generateLocalProperties)
}

tasks.configureEach {
    if (name.contains("ksp", ignoreCase = true) || name.contains("ktlint", ignoreCase = true)) {
        dependsOn(generateLocalProperties)
    }
}
