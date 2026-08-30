package gregtechlite.gtlitecore.api.metatileentity.wireless

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.SECOND
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent

@Suppress("unused")
@EventBusSubscriber(modid = MOD_ID)
object WirelessTickHandler
{
    @SubscribeEvent
    @JvmStatic
    fun onWorldTick(event: WorldTickEvent)
    {
        val world = event.world ?: return
        if (world.isRemote) return
        if (world.totalWorldTime % (5 * SECOND) != 0L) return

        WirelessNetworkManager.update()
    }
}
