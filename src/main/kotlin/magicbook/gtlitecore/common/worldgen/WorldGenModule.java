package magicbook.gtlitecore.common.worldgen;

import gregtech.api.GTValues;
import gregtech.api.util.FileUtility;
import magicbook.gtlitecore.GTLiteMod;
import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.module.BaseModule;
import magicbook.gtlitecore.core.module.GTLiteModules;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import one.util.streamex.StreamEx;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Module(moduleId = GTLiteModules.MODULE_WORLDGEN,
        containerId = GTLiteValues.MODID,
        name = "GregTech Lite World Generator Module",
        descriptions = "World Generator Module of GregTech Lite Core Mod.")
public class WorldGenModule extends BaseModule
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " World Generator");

    @NotNull
    @Override
    public List<Class<?>> getTerrainGenBusSubscribers()
    {
        return Collections.singletonList(WorldGenModule.class);
    }

    @NotNull
    @Override
    public List<Class<?>> getOreGenBusSubscribers()
    {
        return Collections.singletonList(WorldGenModule.class);
    }

    /**
     * Add an ordered initialized to override the {@link gregtech.api.worldgen.config.WorldGenRegistry}.
     */
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        // Order 1: Extracted all worldgen settings from original gregtech folder.
        FileUtility.extractJarFiles("/assets/gregtech/worldgen/",
                getWorldGenPath(GTValues.MODID).toFile(), false);
        // Order 2: Extracted all secondary worldgen settings from gtlitecore folder, and enabled
        // replaced setting to override some configurations like salt water files (these files
        // contains same name but not same contents).
        extractWorldGenFiles("/assets/gtlitecore/worldgen/",
                getWorldGenPath(GTValues.MODID).toFile(), true);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(GTLiteWorldGenerator.INSTANCE, 1);
    }

    private static Path getWorldGenPath(String modId)
    {
        return getConfigPath(modId).resolve("worldgen");
    }

    private static Path getConfigPath(String modId)
    {
        return Loader.instance().getConfigDir().toPath().resolve(modId);
    }

    @NotNull
    @Override
    public Logger getLogger()
    {
        return logger;
    }

    /**
     * Extract world generator setting files from jar to configurations.
     * <p>
     * This is an inner version of {@link FileUtility#extractJarFiles} which used for extracted
     * inner folder files to configuration folder.
     */
    protected static void extractWorldGenFiles(String resource, File targetPath, boolean replace)
    {
        FileSystem zipFileSystem = null;
        try
        {
            URL sampleUrl = GTLiteMod.class.getResource("/assets/gtlitecore/LICENSE");
            if (sampleUrl == null)
            {
                GTLiteLog.logger.warn("Could not find LICENSE resource.");
                return;
            }
            URI sampleUri = sampleUrl.toURI();
            Path resourcePath;
            if (sampleUri.getScheme().equals("jar") || sampleUri.getScheme().equals("zip"))
            {
                zipFileSystem = FileSystems.newFileSystem(sampleUri, Collections.emptyMap());
                resourcePath = zipFileSystem.getPath(resource);
            }
            else if (sampleUri.getScheme().equals("file"))
            {
                URL resourceURL = GTLiteMod.class.getResource(resource);
                if (resourceURL == null)
                {
                    GTLiteLog.logger.warn("Could not find resource file for {}.", resource);
                    return;
                }
                resourcePath = Paths.get(resourceURL.toURI());
            }
            else
            {
                throw new IllegalStateException("Unable to locate absolute path to directory: " + sampleUri);
            }

            List<Path> jarFiles;
            try (Stream<Path> stream = Files.walk(resourcePath))
            {
                jarFiles = StreamEx.of(stream).filter(Files::isRegularFile).toList();
            }

            for (Path jarFile : jarFiles)
            {
                Path genPath = targetPath.toPath().resolve(resourcePath.relativize(jarFile).toString());
                Files.createDirectories(genPath.getParent());
                if (replace || !genPath.toFile().isFile())
                {
                    Files.copy(jarFile, genPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        catch (URISyntaxException impossible)
        {
            throw new RuntimeException(impossible);
        }
        catch (IOException exception)
        {
            GTLiteLog.logger.error("error while extracting jar files: {} {}", resource, targetPath, exception);
        }
        finally
        {
            if (zipFileSystem != null)
            {
                // close zip file system to avoid issues
                IOUtils.closeQuietly(zipFileSystem);
            }
        }
    }

}
