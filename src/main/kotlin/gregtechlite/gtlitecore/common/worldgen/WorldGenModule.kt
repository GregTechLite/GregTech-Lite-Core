package gregtechlite.gtlitecore.common.worldgen

import com.morphismmc.morphismlib.util.SidedLogger
import gregtech.api.GTValues
import gregtech.api.util.FileUtility
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.common.worldgen.generator.WorldGeneratorManager
import gregtechlite.gtlitecore.core.module.BaseModule
import gregtechlite.gtlitecore.core.module.GTLiteModules.Companion.MODULE_WORLDGEN
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.commons.io.IOUtils
import org.apache.logging.log4j.Logger
import java.io.File
import java.io.IOException
import java.net.URISyntaxException
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.streams.asSequence

@Module(moduleId = MODULE_WORLDGEN,
        containerId = MOD_ID,
        name = "GregTech Lite World Generator Module",
        descriptions = "World Generator Module of GregTech Lite Core Mod.")
class WorldGenModule : BaseModule()
{

    companion object
    {

        @get:JvmName("_logger")
        @get:JvmSynthetic
        @JvmStatic
        val logger: Logger = SidedLogger("$MOD_ID-world-generator")
    }


    override fun preInit(event: FMLPreInitializationEvent)
    {
        // Ordered initialized all worldgen definitions to override GTCEu WorldGenRegistry.

        // Order 1: Extracted all worldgen definitions from original gregtech folder.
        FileUtility.extractJarFiles("/assets/gregtech/worldgen/",
                                    Loader.instance().configDir.toPath().resolve(GTValues.MODID).toFile(), false)

        // Order 2: Extracted all worldgen definitions from gtlitecore folder.
        // This step is enabled "replace" setting to override some configurations, for example,
        // the fluid is salt water in the original definition "salt_water_deposit.json", and
        // seawater as an instanceof in new definition.
        extractWorldGenFiles("/assets/gtlitecore/worldgen/",
                             Loader.instance().configDir.toPath().resolve(GTValues.MODID).toFile(), false)
    }

    override fun init(event: FMLInitializationEvent)
    {
        WorldGeneratorManager.init()
    }

    override fun getTerrainGenBusSubscribers() = listOf(WorldGenModule::class.java)

    override fun getOreGenBusSubscribers() = listOf(WorldGenModule::class.java)

    /**
     * An internal files extract methods, this method is used "LICENSE" file as token in the mod folder,
     * and will extract all files in [resource] and put them to [targetPath].
     *
     * If [replace] is enabled, then files will be overridable.
     */
    @Suppress("SameParameterValue")
    private fun extractWorldGenFiles(resource: String, targetPath: File, replace: Boolean)
    {
        var zipFileSystem: FileSystem? = null
        try
        {
            val sampleURL = GTLiteMod::class.java.getResource("/assets/gtlitecore/LICENSE")
            if (sampleURL == null)
            {
                GTLiteLog.logger.warn("Could not find LICENSE resource.")
                return
            }

            val sampleURI = sampleURL.toURI()
            val resourcePath: Path?
            when (sampleURI.scheme)
            {
                "jar", "zip" ->
                {
                    zipFileSystem = FileSystems.newFileSystem(sampleURI, mutableMapOf<String?, Any?>())
                    resourcePath = zipFileSystem.getPath(resource)
                }
                "file" ->
                {
                    val resourceURL = GTLiteMod::class.java.getResource(resource)
                    if (resourceURL == null)
                    {
                        GTLiteLog.logger.warn("Could not find resource file for $resource.")
                        return
                    }
                    resourcePath = Paths.get(resourceURL.toURI())
                }
                else ->
                {
                    throw IllegalStateException("Unable to locate absolute path to directory: $sampleURI")
                }
            }

            Files.walk(resourcePath)
                .use {
                    it.asSequence()
                        .filter { path -> Files.isRegularFile(path) }
                        .toMutableList()
                }
                .forEach {
                    val generatePath = targetPath.toPath().resolve(resourcePath.relativize(it).toString())
                    Files.createDirectories(generatePath.parent)
                    if (replace || !generatePath.toFile().isFile)
                    {
                        Files.copy(it, generatePath, StandardCopyOption.REPLACE_EXISTING)
                    }
                }
        }
        catch (impossible: URISyntaxException)
        {
            throw RuntimeException(impossible)
        }
        catch (exception: IOException)
        {
            GTLiteLog.logger.error("error while extracting jar files: $resource $targetPath", exception)
        }
        finally
        {
            if (zipFileSystem != null)
            {
                // close zip file system to avoid issues
                IOUtils.closeQuietly(zipFileSystem)
            }
        }
    }

    override fun getLogger(): Logger = Companion.logger

}