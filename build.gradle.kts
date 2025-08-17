import org.gradle.api.internal.artifacts.dependencies.DependencyVariant
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.compiler
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings

/*
 * This gradle settings is based on TemplateEnvDevKt, modified it to provide some features, such
 * as language mixed programming support (Java and Kotlin), some annotation processors and packages,
 * like JetBrains Annotation and Lombok.
 *
 * The author of this TemplateEnvDevKt port version is Magic_Sweepy and Gate Guardian, and this gradle
 * template is only used for this project, thanks for Kyle Lin make the original gradle template.
 */

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

group = "magicbook"
version = modVersion

// Mixed programming environment not supported Jabel or other same functional dependencies, we used Java 8
// as default. Do not change this settings otherwise you know what you are doing.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        // Azul covers the most platforms for Java 8 toolchains, crucially including MacOS arm64.
        vendor.set(JvmVendorSpec.AZUL)
    }
    // Generate sources and Javadocs jars when building and publishing.
    // withSourcesJar()
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

// Used specified sourceSet of two languages as default, should put all Java class in 'java' and all Kotlin
// class to 'kotlin' sourceSets. Do not change these settings, this is only for javaCompiler and kotlinCompiler.
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

    // Add any additional tweaker classes here.
    // extraTweakClasses.add("org.spongepowered.asm.launch.MixinTweaker")

    // Add various JVM arguments here for runtime.
    val args = mutableListOf("-ea:${group}")
    if (usesCoreMod.toBoolean()) {
        args += "-Dfml.coreMods.load=$coreModPluginPath"
    }
    if (usesMixins.toBoolean()) {
        args += "-Dmixin.hotSwap=true"
        args += "-Dmixin.checks.interfaces=true"
        args += "-Dmixin.debug.export=true"
    }
    // args += "-XXaltjvm=dcevm"
    // args += "-XX:+AllowEnhancedClassRedefinition"
    args += "-Dterminal.jline=true"
    extraRunJvmArguments.addAll(args)

    // Include and use dependencies' Access Transformer files
    useDependencyAccessTransformers.set(true)

    // Add any properties you want to swap out for a dynamic value at build time here.
    // Any properties here will be added to a class at build time, the name can be configured below.

    injectedTags.put("MOD_VERSION", modVersion)
    injectedTags.put("MOD_ID", modId)
    injectedTags.put("MOD_NAME", modName)
}

repositories()

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
    implementation(deobf(libs.modularui))
    api(libs.codeChickenLib) // Schedule removal this dependencies when GTCEu update next version.
    implementation(deobf(files("libs/magicbook-1.12.2-1.0.0.jar")))
    implementation(deobf(files("libs/gregtech-1.12.2-master-#2612.jar")))
    implementation(deobf(libs.ae2ExtendedLife))
    implementation(libs.jei)
    implementation(libs.theOneProbe)

    runtimeOnly(deobf(libs.ctm))
    runtimeOnly(deobf(libs.smoothFonts))
    runtimeOnly(deobf(libs.betterQuestingUnofficial))

    compileOnly(libs.groovyScript) {
        isTransitive = false
    }

    compileOnly(libs.craftTweaker2)

    compileOnlyApi(libs.jetbrainsAnnotations)
    annotationProcessor(libs.jetbrainsAnnotations)

    compileOnlyApi(libs.lombok)
    annotationProcessor(libs.lombok)

    embed(libs.streamex)
    embed(libs.jheaps)
    embed(libs.joml)
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

// Adds Access Transformer files to tasks.
@Suppress("Deprecation")
if (usesAccessTransformer.toBoolean()) {
    for (at in sourceSets.getByName("main").resources.files) {
        if (at.name.toLowerCase().endsWith("_at.cfg")) {
            tasks.deobfuscateMergedJarToSrg.get().accessTransformerFiles.from(at)
            tasks.srgifyBinpatchedJar.get().accessTransformerFiles.from(at)
        }
    }
}

tasks {
    // Generate a RFG Tags class.
    injectTags {
        outputClassName.set(generateTokenPath)
    }

    processIdeaSettings {
        dependsOn(injectTags)
    }
}

@Suppress("UnstableApiUsage")
tasks.withType<ProcessResources> {
    // This will ensure that this task is redone when the versions change.
    inputs.property("version", modVersion)
    inputs.property("mcversion", minecraft.mcVersion)

    // Replace various properties in mcmod.info and pack.mcmeta if applicable.
    filesMatching(arrayListOf("mcmod.info", "pack.mcmeta", "mixins.${modId}.early.json", "mixins.${modId}.late.json")) {
        expand(
            "version" to modVersion,
            "mcversion" to minecraft.mcVersion,
            "modid" to modId
        )
    }

    if (usesAccessTransformer.toBoolean()) {
        rename("(.+_at.cfg)", "META-INF/$1") // Make sure Access Transformer files are in META-INF folder.
    }
}

tasks.withType<Jar> {
    manifest {
        val attributeMap = mutableMapOf<String, String>()
        if (usesCoreMod.toBoolean()) {
            attributeMap["FMLCorePlugin"] = coreModPluginPath
            if (includeMod.toBoolean()) {
                attributeMap["FMLCorePluginContainsFMLMod"] = true.toString()
                attributeMap["ForceLoadAsMod"] =
                    (project.gradle.startParameter.taskNames.getOrNull(0) == "build").toString()
            }
        }
        if (usesAccessTransformer.toBoolean()) {
            attributeMap["FMLAT"] = modId + "_at.cfg"
        }
        attributes(attributeMap)
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