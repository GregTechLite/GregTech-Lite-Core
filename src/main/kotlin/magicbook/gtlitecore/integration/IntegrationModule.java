package magicbook.gtlitecore.integration;

import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.module.BaseModule;
import magicbook.gtlitecore.core.module.GTLiteModules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

@Module(moduleId = GTLiteModules.MODULE_INTEGRATION,
        containerId = GTLiteValues.MODID,
        name = "GregTech Lite Integration Module",
        descriptions = "General GregTech Lite Integration Module. Disabling this disables all integration modules.")
public class IntegrationModule extends BaseModule
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " Mod Integration");

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers()
    {
        return Collections.singletonList(IntegrationModule.class);
    }

    @NotNull
    @Override
    public Logger getLogger()
    {
        return logger;
    }

}
