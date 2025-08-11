package gregtechlite.gtlitecore.integration

import gregtechlite.magicbook.util.SidedLogger
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.core.module.BaseModule
import gregtechlite.gtlitecore.core.module.GTLiteModules.Companion.MODULE_INTEGRATION
import org.apache.logging.log4j.Logger

@Module(moduleId = MODULE_INTEGRATION,
        containerId = MOD_ID,
        name = "GregTech Lite Integration Module",
        descriptions = "General GregTech Lite Integration Module. Disabling this disables all integration modules.")
class IntegrationModule : BaseModule()
{

    companion object
    {

        @JvmField
        val logger: Logger = SidedLogger("$MOD_ID-integration")

    }

    override fun getEventBusSubscribers(): MutableList<Class<*>?>
    {
        return mutableListOf(IntegrationModule::class.java)
    }

    override fun getLogger(): Logger = Companion.logger

}
