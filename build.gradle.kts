import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.compiler
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings

// This gradle settings is based on TemplateEnvDevKt, modified it to provide some features, such
// as language mixed programming support (Java and Kotlin), some annotation processors and packages,
// like JetBrains Annotation and Lombok.

// The author of this TemplateEnvDevKt port version is Magic_Sweepy, and this gradle template is only
// used for this project, thanks for Kyle Lin make the original gradle template.

buildscript { 
    repositories {
        mavenCentral()
    }

    // Used Kotlin 2.1.0 as default setting.
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlinVersion}")
    }
}

plugins {
    id("java")
    id("java-library")
    kotlin("jvm") version libs.versions.kotlinVersion
    id("maven-publish")
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
    id("eclipse")
    id("com.gtnewhorizons.retrofuturagradle") version "1.3.27"
    id("com.matthewprenger.cursegradle") version "1.4.0"
}

// The grouping mod name of project, like "org.authorname.examplemod", and the folder structure
// of the mod will be: folder "org" -> folder "authorname" -> folder "examplemod" -> mod files.
val modGroup: String by project

// The id of mod, used to generate the mod files (dev, source and jar file), different with the
// modId setting of the main class of mod, but ensure two setting be same otherwise you know
// what you want to do in this setting.
val modId: String by project

// The version of mod, used to generate the mod files (dev, source and jar file), different with
// the modVersion setting of the main class of mod, but ensure two setting be same otherwise you
// know what you want to do in this setting.
val modVersion: String by project

// The archive base name of mod, warning: this is not same as the modName setting in main class
// completely, do not confuse two settings.
val modName: String by project

// The version of Minecraft in develop environment, this setting cannot control FML version.
val minecraftVersion: String by project

// The userName setting of develop environment, the UUID will be looked up automatically.
val userName: String by project

// The kotlinVersion setting is only writable in settings.gradle.kts.

// The forgeVersion setting, this option control the supported lib version of Kotlin in Minecraft.
val forgelinVersion: String by project

// The generateTokenPath setting, this option is the path of RFG Tags class.
val generateTokenPath: String by project

// The usesMixins setting.
val usesMixins: String by project

// The mixinBooterVersion setting.
val mixinBooterVersion: String by project

// The usesAccessTransformer setting.
val usesAccessTransformer: String by project

// The usesAssetMover setting, AssetMover is a mod used to downland resources.
val usesAssetMover: String by project

// The assetMoverVersion setting.
val assetMoverVersion: String by project

// The usesCoreMod setting.
val usesCoreMod: String by project

// The includeMod setting.
val includeMod: String by project

// The coreModPluginPath setting.
val coreModPluginPath: String by project

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        // Azul covers the most platforms for Java 8 toolchains, crucially including MacOS arm64.
        vendor.set(JvmVendorSpec.AZUL)
    }
    // Generate sources and Javadocs jars when building and publishing.
    withSourcesJar()
    // withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

configurations {
    val embed = create("embed")
    implementation.configure {
        extendsFrom(embed)
    }
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

    injectedTags.put("VERSION", project.version)
    injectedTags.put("MOD_ID", modId)
    injectedTags.put("MOD_NAME", modName)
}

// Generate a RFG Tags class.
tasks.injectTags.configure {
    outputClassName.set(generateTokenPath)
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
    mavenLocal() // Must be last for caching to work.
}

dependencies {

    // Before the dependencies.gradle script loading, add Forgelin to the dependencies.
    implementation("io.github.chaosunity.forgelin:Forgelin-Continuous:${forgelinVersion}") {
        exclude("net.minecraftforge")
    }

    // Mixins dependency settings.
    if (usesMixins.toBoolean()) {
        implementation("zone.rong:mixinbooter:9.1")

        val mixin = modUtils.enableMixins("zone.rong:mixinbooter:${mixinBooterVersion}", "mixins.${modName}.refmap.json") as String
        api(mixin) {
            isTransitive = true
        }
        annotationProcessor("org.ow2.asm:asm-debug-all:5.2")
        annotationProcessor("com.google.guava:guava:24.1.1-jre")
        annotationProcessor("com.google.code.gson:gson:2.8.6")
        annotationProcessor(mixin) {
            isTransitive = false
        }
    }

    // AssetMover settings.
    if (usesAssetMover.toBoolean()) {
        implementation("com.cleanroommc:assetmover:${assetMoverVersion}")
    }

    // Apply dependencies from gradle scripts.
    apply("gradle/script/dependencies.gradle")

    // Several global dependencies.

    // JetBrains Annotations 24.1.0
    compileOnlyApi("org.jetbrains:annotations:24.1.0")
    annotationProcessor("org.jetbrains:annotations:24.1.0")

    // Lombok 1.18.24
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    // StreamEx 0.8.3
    compileOnly("one.util:streamex:0.8.3")

    // JHeaps 0.14
    compileOnly("org.jheaps:jheaps:0.14")

    // Kotson 2.5.0
    compileOnly("com.github.salomonbrys.kotson:kotson:2.5.0")

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
                attributeMap["ForceLoadAsMod"] = (project.gradle.startParameter.taskNames[0] == "build").toString()
            }
        }
        if (usesAccessTransformer.toBoolean()) {
            attributeMap["FMLAT"] = modName + "_at.cfg"
        }
        attributes(attributeMap)
    }
    // Add all embedded dependencies into the jar.
    from(provider {
        configurations.getByName("embed").map {
            if (it.isDirectory()) it else zipTree(it)
        }
    })
}

idea {
    module {
        inheritOutputDirs = true
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

tasks.named("processIdeaSettings").configure {
    dependsOn("injectTags")
}
