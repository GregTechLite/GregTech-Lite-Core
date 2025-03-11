package magicbook.gtlitecore.common.worldgen;

import gregtech.api.GTValues;
import gregtech.api.util.FileUtility;
import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.module.BaseModule;
import magicbook.gtlitecore.core.module.GTLiteModules;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import one.util.streamex.StreamEx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Module(moduleId = GTLiteModules.MODULE_WORLDGEN,
        containerId = GTLiteValues.MODID,
        name = "GregTech Lite World Generator Module",
        descriptions = "World Generator Module of GregTech Lite Core Mod.")
public class WorldGenModule extends BaseModule
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " World Generator");

    private static final String resourceWorldGenPath = "/assets/gregtech/worldgen/";

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        // Extracted all deposit settings from zipFileSystem in jar files
        // to original GregTech folders, and then when WorldGenRegistry working,
        // new veins can be added finely.
        FileUtility.extractJarFiles(resourceWorldGenPath, getWorldGenPath(GTValues.MODID).toFile(), false);
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

}
