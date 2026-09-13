package gregtechlite.gtlitecore.common.worldgen

import com.morphismmc.morphismlib.util.SidedLogger
import gregtech.api.GTValues
import gregtech.api.util.FileUtility
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.extension.extractTo
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.common.worldgen.generator.WorldGeneratorManager
import gregtechlite.gtlitecore.core.module.BaseModule
import gregtechlite.gtlitecore.core.module.GTLiteModules.Companion.MODULE_WORLDGEN
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import java.io.File
import java.io.IOException
import java.net.URISyntaxException
import java.nio.file.FileSystems
import kotlin.io.path.toPath

@Module(moduleId = MODULE_WORLDGEN,
        containerId = MOD_ID,
        name = "GregTech Lite World Generator Module",
        descriptions = "World Generator Module of GregTech Lite Core Mod.")
class WorldGenModule : BaseModule()
{
    companion object
    {
        @JvmField
        val logger: Logger = SidedLogger("$MOD_ID-world-generator")
    }

    override fun preInit(event: FMLPreInitializationEvent)
    {
        val worldGenPath = Loader.instance().configDir.toPath().resolve(GTValues.MODID).resolve("worldgen").toFile()
        FileUtility.extractJarFiles("/assets/gregtech/worldgen/", worldGenPath, false)
        extractWorldGenFiles("/assets/gtlitecore/worldgen/", worldGenPath, true)
    }

    override fun init(event: FMLInitializationEvent)
    {
        WorldGeneratorManager.init()
    }

    override val terrainGenBusSubscribers = listOf(WorldGenModule::class.java)

    override val oreGenBusSubscribers = listOf(WorldGenModule::class.java)

    /**
     * An internal files extract methods, this method is used "LICENSE" file as token in the mod folder,
     * and will extract all files in [resource] and put them to [targetPath].
     *
     * If [replace] is enabled, then files will be overridable.
     */
    private fun extractWorldGenFiles(resource: String, targetPath: File, replace: Boolean)
    {
        runCatching {
            val sampleURL = GTLiteMod::class.java.getResource("/assets/gtlitecore/LICENSE")
                ?: run {
                    LOGGER.warn("Could not find LICENSE resource in assets")
                    return
                }
            val sampleURI = sampleURL.toURI()
            when (sampleURI.scheme)
            {
                "jar", "zip" -> {
                    FileSystems.newFileSystem(sampleURI, emptyMap<String, Any?>()).use {
                        it.getPath(resource).extractTo(targetPath, replace)
                    }
                }
                "file" ->
                {
                    val resourceURL = GTLiteMod::class.java.getResource(resource)
                        ?: run {
                            LOGGER.warn("Could not find resource file for $resource")
                            return
                        }
                    resourceURL.toURI().toPath().extractTo(targetPath, replace)
                }
                else -> throw IllegalStateException("Unable to locate absolute path to directory: $sampleURI")
            }
        }.onFailure {
            when (it)
            {
                is URISyntaxException -> throw RuntimeException(it)
                is IOException        -> LOGGER.error("Error while extracting jar files: $resource $targetPath", it)
                else                  -> throw it
            }
        }
    }

    override val logger: Logger = Companion.logger
}