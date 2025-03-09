package magicbook.gtlitecore.core;

import lombok.NoArgsConstructor;
import magicbook.gtlitecore.api.module.IModule;
import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.common.CommonProxy;
import magicbook.gtlitecore.core.module.GTLiteModules;
import net.minecraftforge.fml.common.SidedProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
@NoArgsConstructor
@Module(moduleId = GTLiteModules.MODULE_CORE,
        containerId = GTLiteValues.MODID,
        name = GTLiteValues.NAME,
        descriptions = "Core Module of GregTech Lite Core Mod.",
        isCore = true)
public class CoreModule implements IModule
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " API");

    @SidedProxy(modId = GTLiteValues.MODID,
                clientSide = "magicbook.gtlitecore.client.ClientProxy",
                serverSide = "magicbook.gtlitecore.common.CommonProxy")
    public static CommonProxy proxy;

    /* -------------------------------- FML Life cycle Events -------------------------------- */

    /* --------------------------------------------------------------------------------------- */

    @NotNull
    @Override
    public Logger getLogger()
    {
        return logger;
    }

}
