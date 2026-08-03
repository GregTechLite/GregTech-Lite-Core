import org.gradle.api.internal.artifacts.dependencies.DependencyVariant
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.compiler
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings

plugins {
    id("java")
    id("java-library")
    kotlin("jvm") version libs.versions.kotlin.get()
    id("maven-publish")
    id("eclipse")
    alias(libs.plugins.ideaExt)
    alias(libs.plugins.retrofuturaGradle)
    alias(libs.plugins.curseGradle)
    alias(libs.plugins.dokka)
    alias(libs.plugins.shadow)
}

val embed = "embed"

group = modGroup
version = modVersion

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        // Azul covers the most platforms for Java 8 toolchains, crucially including MacOS arm64.
        vendor.set(JvmVendorSpec.AZUL)
    }
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
        kotlin {
            srcDir("src/main/kotlin")
        }
    }
}

configurations {
    val embed = create(embed)
    implementation {
        extendsFrom(embed)
    }
}

minecraft {
    mcVersion.set(minecraftVersion)

    mcpMappingChannel.set("stable")
    mcpMappingVersion.set("39")

    // Set username here, the UUID will be looked up automatically.
    username.set(userName)

    // Add various JVM arguments for runtime.
    val args = mutableListOf<String>()
    // Enable assertions for the mod group.
    args += "-ea:${modGroup}"
    // Initialize core mod and mixins when its enabled.
    if (usesCoreMod.toBoolean()) {
        args += "-Dfml.coreMods.load=$coreModPluginPath"
    }
    if (usesMixins.toBoolean()) {
        args += "-Dmixin.hotSwap=true"
        args += "-Dmixin.checks.interfaces=true"
        args += "-Dmixin.debug.export=true"
    }
    // Add colored line support for the terminal in development environment.
    args += "-Dterminal.jline=true"
    extraRunJvmArguments.addAll(args)

    // Include and use AT files in dependencies.
    useDependencyAccessTransformers.set(true)

    // Inner parameter name and value by RFG injectedTags task.
    injectedTags.put("MOD_VERSION", modVersion)
    injectedTags.put("MOD_ID", modId)
    injectedTags.put("MOD_NAME", modName)
}

repositories {
    maven {
        name = "CleanroomMC Maven"
        url = uri("https://maven.cleanroommc.com")
    }
    maven {
        name = "SpongePowered Maven"
        url = uri("https://repo.spongepowered.org/maven")
    }
    maven {
        name = "CurseMaven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
    maven {
        name = "BlameJared Maven"
        url = uri("https://maven.blamejared.com")
    }
    maven {
        name = "GTCEu Maven"
        url = uri("https://maven.gtceu.com")
    }
}

dependencies {
    if (usesMixins.toBoolean()) {
        annotationProcessor(libs.asm)
        annotationProcessor(libs.guava)
        annotationProcessor(libs.gson)
        val mixinBooter = modUtils.enableMixins(libs.mixinBooter, "mixins.${modId}.refmap.json") as Provider<*>
        api(mixinBooter) {
            isTransitive = false
        }
        annotationProcessor(mixinBooter) {
            isTransitive = false
        }
    }

    implementation(libs.forgelin) {
        exclude("net.minecraftforge")
    }

    implementation(libs.modularui) {
        isTransitive = false
    }

    api(libs.codeChickenLib)
    api(libs.ctm)
    implementation(deobf(files("libs/morphismlib-1.12.2-1.0.0.jar")))
    implementation(deobf(files("libs/gregtech-1.12.2-master-#2901.jar")))
    implementation(deobf(libs.ae2ExtendedLife))
    implementation(libs.jei)
    implementation(libs.theOneProbe)

    compileOnly(libs.groovyScript) {
        isTransitive = false
    }

    compileOnly(libs.craftTweaker2) {
        exclude("com.google.code.gson", "gson")
        exclude("org.ow2.asm", "asm-debug-all")
    }

    compileOnly(libs.baubles)

    runtimeOnly(deobf(libs.catalogue))

    compileOnlyApi(libs.jetbrainsAnnotations)
    annotationProcessor(libs.jetbrainsAnnotations)
}

configurations {
    compileOnly {
        // For collections, we use kotlin native or fastutil and guava but not trove4j.
        exclude(group = "net.sf.trove4j", module = "trove4j")
        // For java part, we use jetbrains annotations for nullability mark but not javax.annotation.
        exclude(group = "com.google.code.findbugs", module = "jsr305")
        // We don't use scala in this repository, so we just exclude those forge native groups.
        exclude(group = "org.scala-lang")
        exclude(group = "org.scala-lang.modules")
        exclude(group = "org.scala-lang.plugins")
    }
}

fun DependencyHandler.deobf(dependencyNotation: Any): Any {
    if (dependencyNotation is Provider<*>) {
        return deobf(dependencyNotation.get())
    }

    var depSpec = dependencyNotation
    if (dependencyNotation is Dependency) {
        depSpec = "${dependencyNotation.group}:${dependencyNotation.name}:${dependencyNotation.version}"
        if (dependencyNotation is DependencyVariant) {
            depSpec += ":${dependencyNotation.classifier}"
        }
    }
    return rfg.deobf(depSpec)
}

if (usesAccessTransformer.toBoolean()) {
    for (at in sourceSets.getByName("main").resources.files) {
        if (at.name.lowercase().endsWith("_at.cfg")) {
            tasks.deobfuscateMergedJarToSrg.get().accessTransformerFiles.from(at)
            tasks.srgifyBinpatchedJar.get().accessTransformerFiles.from(at)
        }
    }
}

tasks {
    injectTags {
        outputClassName.set(generateTokenPath)
    }

    processIdeaSettings {
        dependsOn(injectTags)
    }
}

tasks.processResources {
    // Ensure that this task is redone when the versions change.
    val props = mutableMapOf<String, String>()
    props.put("mod_id"     , modId)
    props.put("mod_name"   , modName)
    props.put("mod_version", modVersion)
    props.put("mc_version" , minecraftVersion)
    inputs.properties(props)
    // Replace various properties in mcmod.info and pack.mcmeta if applicable.
    filesMatching(listOf("mcmod.info", "pack.mcmeta")) {
        expand(props)
    }

    // Make sure AT files are in correct folder ("META-INF/..." by default).
    if (usesAccessTransformer.toBoolean()) {
        rename("(.+_at.cfg)", "META-INF/$1")
    }
}

tasks.withType<Jar> {
    manifest {
        val attributes = mutableMapOf<String, String>()
        if (usesCoreMod.toBoolean()) {
            attributes["FMLCorePlugin"] = coreModPluginPath
            if (includeMod.toBoolean()) {
                attributes["FMLCorePluginContainsFMLMod"] = true.toString()
                attributes["ForceLoadAsMod"] = (project.gradle.startParameter.taskNames.getOrNull(0) == "build").toString()
            }
        }
        if (usesAccessTransformer.toBoolean()) {
            attributes["FMLAT"] = modId + "_at.cfg"
        }
        attributes(attributes)
    }
}

// Shadowed external packages to internal packages to resolved class not found when
// the mod is running at other environments.
if (usesShadowJar.toBoolean()) {
    tasks {
        shadowJar {
            configurations = listOf(project.configurations["embed"])
            mergeServiceFiles()
            mergeGroovyExtensionModules()
            minimize()
        }

        reobfJar {
            inputJar.set(shadowJar.get().archiveFile)
        }
    }

    // Remove shadow jar from java component
    val javaComponent = components["java"] as AdhocComponentWithVariants
    javaComponent.withVariantsFromConfiguration(configurations.shadowRuntimeElements.get()) {
        skip()
    }
}

// Add JavaDocs/KDocs generate merger in Java/Kotlin mixed programming environment.
tasks.withType<DokkaTask> {
    outputDirectory.set(projectDir.resolve("docs"))
    dokkaSourceSets {
        configureEach {
            // Allowed Dokka read two sourceSets.
            sourceRoots.from(file("src/main/java"), file("src/main/kotlin"))
        }
    }
}

idea {
    module {
        inheritOutputDirs = true
        // IDEA no longer automatically downloads sources/javadoc jars for dependencies,
        // so we need to explicitly enable the behavior.
        isDownloadSources = true
        isDownloadJavadoc = true
    }
    project {
        settings {
            runConfigurations {
                add(Gradle("1. Run Client").apply {
                    setProperty("taskNames", listOf("runClient"))
                })
                add(Gradle("2. Run Server").apply {
                    setProperty("taskNames", listOf("runServer"))
                })
                add(Gradle("3. Run Obfuscated Client").apply {
                    setProperty("taskNames", listOf("runObfClient"))
                })
                add(Gradle("4. Run Obfuscated Server").apply {
                    setProperty("taskNames", listOf("runObfServer"))
                })
            }
            compiler.javac {
                afterEvaluate {
                    javacAdditionalOptions = "-encoding utf8"
                    moduleJavacAdditionalOptions = mutableMapOf(
                        (project.name + ".main") to tasks.compileJava.get().options.compilerArgs.joinToString(" ") { "\"$it\"" }
                    )
                }
            }
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}