package gregtechlite.gtlitecore.integration.appliedenergistics2

import gregtechlite.magicbook.util.SidedLogger
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.core.module.GTLiteModules.Companion.MODULE_AE2
import gregtechlite.gtlitecore.integration.IntegrationSubModule
import org.apache.logging.log4j.Logger

@Module(moduleId = MODULE_AE2,
        containerId = MOD_ID,
        modDependencies = [ "appliedenergistics2" ],
        name = "GregTech Lite Applied Energistics 2 Module",
        descriptions = "Applied Energistics 2 (AE2) Module of GregTech Lite Core Mod.")
class AppEngModule : IntegrationSubModule()
{

    companion object
    {

        @JvmField
        val logger: Logger = SidedLogger("$MOD_ID-ae2-module")
    }

    override fun getLogger(): Logger = Companion.logger

}
