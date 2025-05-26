import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.internal.artifacts.dependencies.DependencyVariant
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.compiler
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings
import java.net.URI

/**
 * This gradle settings is based on TemplateEnvDevKt, modified it to provide some features, such
 * as language mixed programming support (Java and Kotlin), some annotation processors and packages,
 * like JetBrains Annotation and Lombok.
 *
 * The author of this TemplateEnvDevKt port version is Magic_Sweepy, and this gradle template is only
 * used for this project, thanks for Kyle Lin make the original gradle template.
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

// Java settings.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        // Azul covers the most platforms for Java 8 toolchains, crucially including MacOS arm64.
        vendor.set(JvmVendorSpec.AZUL)
    }
    // Generate sources and Javadocs jars when building and publishing.
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

// These sourceSet settings allowed javaCompiler read Kotlin source,
// but kotlinCompiler cannot read Java source. As default, we build
// programming structures in Kotlin source.
sourceSets {
    main {
        java {
            srcDirs("src/main/java", "src/main/kotlin")
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

// Starting to initialize Minecraft settings.
minecraft {
    // Set minecraftVersion to mcVersion setting.
    mcVersion.set(minecraftVersion)

    // MCP Mappings.
    mcpMappingChannel.set("stable")
    mcpMappingVersion.set("39")

    // Set username here, the UUID will be looked up automatically.
    username.set(userName)

    // Add any additional tweaker classes here.
    // extraTweakClasses.add("org.spongepowered.asm.launch.MixinTweaker")

    // Add various JVM arguments here for runtime
    val args = mutableListOf("-ea:${group}")
    if (usesCoreMod.toBoolean()) {
        args += "-Dfml.coreMods.load=$coreModPluginPath"
    }
    if (usesMixins.toBoolean()) {
        args += "-Dmixin.hotSwap=true"
        args += "-Dmixin.checks.interfaces=true"
        args += "-Dmixin.debug.export=true"
    }
    extraRunJvmArguments.addAll(args)

    // Include and use dependencies' Access Transformer files
    useDependencyAccessTransformers.set(true)

    // Add any properties you want to swap out for a dynamic value at build time here.
    // Any properties here will be added to a class at build time, the name can be configured below.

    injectedTags.put("VERSION", modVersion)
    injectedTags.put("MOD_ID", modId)
    injectedTags.put("MOD_NAME", modName)
}

repositories()

dependencies {
    // Mixins dependency settings.
    if (usesMixins.toBoolean()) {
        annotationProcessor(libs.asm)
        annotationProcessor(libs.guava)
        annotationProcessor(libs.gson)
        val mixinBooter = modUtils.enableMixins(libs.mixinBooter, "mixins.${modName}.refmap.json") as Provider<*>
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
    api(libs.groovyScript) {
        isTransitive = false
    }
    api(libs.craftTweaker2)
    api(deobf(libs.ctm))
    implementation(deobf(files("libs/gregtech-1.12.2-master.jar")))
    implementation(deobf(libs.ae2ExtendedLife))
    implementation(libs.jei) // Transformed JEI dependencies from buildscripts option.
    implementation(libs.theOneProbe) // Transformed TOP dependencies from buildscripts option.
    runtimeOnly(deobf(libs.smoothFonts))
    runtimeOnly(deobf(libs.betterQuestingUnofficial))

    // Several global dependencies.
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
    filesMatching(arrayListOf("mcmod.info", "pack.mcmeta")) {
        expand(
            "version" to modVersion,
            "mcversion" to minecraft.mcVersion
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
            attributeMap["FMLAT"] = modName + "_at.cfg"
        }
        attributes(attributeMap)
    }
}

// Shadowed external packages to internal packages to resolved class not found when
// the mod is running at other environments.
if (usesShadowJar.toBoolean()) {
    tasks.withType<ShadowJar> {
        configurations = listOf(project.configurations["embed"])
        mergeServiceFiles()
        mergeGroovyExtensionModules()
        minimize()
    }

    // Add shadowJar to processing assemble of the mod process.
    tasks.assemble {
        dependsOn(tasks.shadowJar)
    }
}

// Add JavaDocs/KDocs generate merger in Java/Kotlin mixed programming environment.
tasks.withType<DokkaTask> {
    outputDirectory.set(buildDir.resolve("docs"))
    dokkaSourceSets {
        configureEach {
            // Allowed Dokka read two sourceSets.
            sourceRoots.from(file("src/main/java"), file("src/main/kotlin"))
            // Java 8 External Docs.
            externalDocumentationLink(
                url = URI("https://docs.oracle.com/en/java/javase/8/docs/api/").toURL()
            )
            // Kotlin StdLib External Docs.
            externalDocumentationLink(
                url = URI("https://kotlinlang.org/api/latest/jvm/stdlib/").toURL()
            )
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