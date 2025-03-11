package magicbook.gtlitecore.common.worldgen;

import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.module.BaseModule;
import magicbook.gtlitecore.core.module.GTLiteModules;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Module(moduleId = GTLiteModules.MODULE_WORLDGEN,
        containerId = GTLiteValues.MODID,
        name = "GregTech Lite World Generator Module",
        descriptions = "World Generator Module of GregTech Lite Core Mod.")
public class WorldGenModule extends BaseModule
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " World Generator");

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @NotNull
    @Override
    public Logger getLogger()
    {
        return logger;
    }

}
