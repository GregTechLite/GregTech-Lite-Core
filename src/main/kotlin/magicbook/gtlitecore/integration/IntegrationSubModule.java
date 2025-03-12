package magicbook.gtlitecore.integration;

import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.core.module.BaseModule;
import magicbook.gtlitecore.core.module.GTLiteModules;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public abstract class IntegrationSubModule extends BaseModule
{

    @NotNull
    @Override
    public Logger getLogger()
    {
        return IntegrationModule.logger;
    }

    @NotNull
    @Override
    public Set<ResourceLocation> getDependencyUids()
    {
        return Collections.singleton(GTLiteUtility.gtliteId(GTLiteModules.MODULE_INTEGRATION));
    }

}
