import org.gradle.api.Project
import kotlin.reflect.KProperty

val Project.modGroup by StringDelegator()
val Project.modId by StringDelegator()
val Project.modName by StringDelegator()
val Project.modVersion by StringDelegator()

val Project.minecraftVersion: String by StringDelegator()
val Project.userName: String by StringDelegator()

val Project.usesMixins: String by StringDelegator()
val Project.usesAccessTransformer: String by StringDelegator()
val Project.usesCoreMod: String by StringDelegator()
val Project.includeMod: String by StringDelegator()
val Project.coreModPluginPath: String by StringDelegator()

val Project.generateTokenPath: String by StringDelegator()

class StringDelegator {
    operator fun getValue(thisRef: Project, property: KProperty<*>): String
        = thisRef.findProperty(property.name)?.toString() ?: error("Property '${property.name}' not found in gradle.properties")
}