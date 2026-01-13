abstract class GenerateLocalPropertiesTask : DefaultTask() {
    @get:Input
    abstract val baseUrl: Property<String>

    @get:Input
    abstract val packageName: Property<String>

    @get:Input
    abstract val className: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val file = outputDir.file("${packageName.get().replace('.', '/')}/${className.get()}.kt").get().asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            package ${packageName.get()}
            object ${className.get()} {
                const val BASE_URL = "${baseUrl.get()}"
            }
            """.trimIndent()
        )
    }
}

val generateLocalProperties =
    tasks.register<GenerateLocalPropertiesTask>("generateLocalProperties") {
        baseUrl.set(providers.gradleProperty("base.url").orElse(""))
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
