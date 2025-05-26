import org.gradle.api.Project
import kotlin.reflect.KProperty

// The grouping mod name of project, like "org.authorname.examplemod", and the folder structure
// of the mod will be: folder "org" -> folder "authorname" -> folder "examplemod" -> mod files.
val Project.modGroup: String by Delegate()

// The id of mod, used to generate the mod files (dev, source and jar file), different with the
// modId setting of the main class of mod, but ensure two setting be same otherwise you know
// what you want to do in this setting.
val Project.modId: String by Delegate()

// The version of mod, used to generate the mod files (dev, source and jar file), different with
// the modVersion setting of the main class of mod, but ensure two setting be same otherwise you
// know what you want to do in this setting.
val Project.modVersion: String by Delegate()

// The archive base name of mod, warning: this is not same as the modName setting in main class
// completely, do not confuse two settings.
val Project.modName: String by Delegate()

// The version of Minecraft in develop environment, this setting cannot control FML version.
val Project.minecraftVersion: String by Delegate()

// The userName setting of develop environment, the UUID will be looked up automatically.
val Project.userName: String by Delegate()

// The generateTokenPath setting, this option is the path of RFG Tags class.
val Project.generateTokenPath: String by Delegate()

// The usesMixins setting.
val Project.usesMixins: String by Delegate()

// The usesAccessTransformer setting.
val Project.usesAccessTransformer: String by Delegate()

// The usesCoreMod setting.
val Project.usesCoreMod: String by Delegate()

// The includeMod setting.
val Project.includeMod: String by Delegate()

// The coreModPluginPath setting.
val Project.coreModPluginPath: String by Delegate()

// The shadowJar settings.
val Project.usesShadowJar: String by Delegate()

class Delegate {
    operator fun getValue(thisRef: Project, property: KProperty<*>): String {
        return thisRef.findProperty(property.name).toString()
    }
}