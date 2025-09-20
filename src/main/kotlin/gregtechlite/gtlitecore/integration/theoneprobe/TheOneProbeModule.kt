package gregtechlite.gtlitecore.integration.theoneprobe

import com.morphismmc.morphismlib.util.SidedLogger
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.core.module.GTLiteModules.Companion.MODULE_TOP
import gregtechlite.gtlitecore.integration.IntegrationSubModule
import gregtechlite.gtlitecore.integration.theoneprobe.provider.DelegatorInfoProvider
import mcjty.theoneprobe.TheOneProbe
import mcjty.theoneprobe.api.ITheOneProbe
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.Logger

@Module(moduleId = MODULE_TOP,
        containerId = MOD_ID,
        modDependencies = [ "theoneprobe" ],
        name = "GregTech Lite TOP Module",
        descriptions = "The One Probe (TOP) Module of GregTech Lite Core Mod.")
class TheOneProbeModule : IntegrationSubModule()
{

    companion object
    {

        @JvmField
        val logger: Logger = SidedLogger("$MOD_ID-top-module")
    }

    override fun init(event: FMLInitializationEvent?)
    {
        logger.info("Registering TheOneProbe Providers...")
        val top: ITheOneProbe = TheOneProbe.theOneProbeImp
        top.registerProvider(DelegatorInfoProvider())
    }

    override fun getLogger(): Logger = Companion.logger

}
