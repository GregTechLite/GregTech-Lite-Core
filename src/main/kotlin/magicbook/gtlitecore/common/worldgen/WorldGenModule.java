package magicbook.gtlitecore.common.worldgen;

import gregtech.api.GTValues;
import gregtech.api.util.FileUtility;
import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.core.module.BaseModule;
import magicbook.gtlitecore.core.module.GTLiteModules;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Module(moduleId = GTLiteModules.MODULE_WORLDGEN,
        containerId = GTLiteValues.MODID,
        name = "GregTech Lite World Generator Module",
        descriptions = "World Generator Module of GregTech Lite Core Mod.")
public class WorldGenModule extends BaseModule
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " World Generator");

    private static final String resourceWorldGenPath = "/assets/gregtech/worldgen/";

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

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        // Extracted all deposit settings from zipFileSystem in jar files
        // to original GregTech folders, and then when WorldGenRegistry working,
        // new veins can be added finely.
        FileUtility.extractJarFiles(resourceWorldGenPath, getWorldGenPath(GTValues.MODID).toFile(), true);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(GTLiteWorldGenerator.INSTANCE, 1);
        if (GTValues.isClientSide())
            GTLiteMetaBlocks.registerColors();
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
