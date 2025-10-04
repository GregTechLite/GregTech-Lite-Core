import org.gradle.api.Project
import kotlin.reflect.KProperty

// region Mod Info

/**
 * The archive package name of the mod, should same as the folder structure of the mod, e.g. if the source code folder
 * structure is "src/main/java/com/authorname/modname", then the `modGroup` setting should be "com.authorname.modname".
 */
val Project.modGroup: String by Delegate()

/**
 * The id of the mod, used to generate `injectTags` properties, mixins refmap file, mcmod and info files, if the string
 * pattern be founded in these files, then it will be instead of the `modId` setting.
 */
val Project.modId: String by Delegate()

/**
 * The human-readable name of the mod, used to generate `injectTags` properties.
 */
val Project.modName: String by Delegate()

/**
 * The version of the mod, used to generate `injectTags` properties and compiled files of the mod.
 */
val Project.modVersion: String by Delegate()

// endregion

// region Minecraft Settings

/**
 * The version of Minecraft in the development environment, this setting cannot control Forge Mod Loader (FML) version.
 * If you want to change FML version, please see: [FG5 ForgeDevEnv](https://github.com/CleanroomMC/ForgeDevEnv/tree/fg5).
 */
val Project.minecraftVersion: String by Delegate()

/**
 * The username of the player in the development environment, the uuid will be locked up automatically. Well... if you
 * want to see your skin in the development environment (although it is unnecessary to do so, if you must, put this mod
 * to `runtimeOnly` dep), please see: [CustomSkinLoader](https://www.curseforge.com/minecraft/mc-mods/customskinloader).
 */
val Project.userName: String by Delegate()

/**
 * The `injectTags` class path, using "com.authorname.modname.ModNameTags" (or maybe you want this class in api package)
 * as default (this is not immutable, but should put in the mod package).
 */
val Project.generateTokenPath: String by Delegate()

/**
 * Enabled Mixins to the mod.
 *
 * If this option is enabled, then the [MixinBooter](https://www.curseforge.com/minecraft/mc-mods/mixin-booter) mod will
 * put in dependencies.
 */
val Project.usesMixins: String by Delegate()

/**
 * Enabled Accessor Transformer (AT) to the mod.
 *
 * If you don't know what is AT, please see: [Forge Wiki](https://forge.gemwire.uk/wiki/Access_Transformers).
 */
val Project.usesAccessTransformer: String by Delegate()

/**
 * Enabled Core Mod to the mod.
 *
 * If you don't know what is Core mod, please see: [Blog](https://blog.techno.fish/minecraft-forge-coremod-tutorial/).
 */
val Project.usesCoreMod: String by Delegate()

/**
 * Marked will load mod when build the repository, this option is dependent with [usesCoreMod] option.
 */
val Project.includeMod: String by Delegate()

/**
 * The Core Mod plugin class name, using "com.authorname.modname.core.ModNameLoadingPlugin" as default.
 */
val Project.coreModPluginPath: String by Delegate()

// endregion

val Project.usesShadowJar: String by Delegate()

class Delegate {

    operator fun getValue(thisRef: Project, property: KProperty<*>): String {
        return thisRef.findProperty(property.name).toString()
    }

}