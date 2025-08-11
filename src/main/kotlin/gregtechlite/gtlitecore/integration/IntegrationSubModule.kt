package gregtechlite.gtlitecore.integration

import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.core.module.BaseModule
import gregtechlite.gtlitecore.core.module.GTLiteModules
import net.minecraft.util.ResourceLocation
import org.apache.logging.log4j.Logger

abstract class IntegrationSubModule : BaseModule()
{

    override fun getLogger(): Logger = IntegrationModule.logger

    override fun getDependencyUids(): MutableSet<ResourceLocation>
    {
        return mutableSetOf(GTLiteMod.id(GTLiteModules.MODULE_INTEGRATION))
    }

}
