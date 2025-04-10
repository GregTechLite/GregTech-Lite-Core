package magicbook.gtlitecore.integration.appliedenergistics2;

import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.module.GTLiteModules;
import magicbook.gtlitecore.integration.IntegrationSubModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Module(moduleId = GTLiteModules.MODULE_AE2,
        containerId = GTLiteValues.MODID,
        modDependencies = { "appliedenergistics2" },
        name = "GregTech Lite Applied Energistics 2 Module",
        descriptions = "Applied Energistics 2 (AE2) Module of GregTech Lite Core Mod.")
public class AppEngModule extends IntegrationSubModule
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " AE2 Module");

    @NotNull
    @Override
    public Logger getLogger()
    {
        return logger;
    }

}
