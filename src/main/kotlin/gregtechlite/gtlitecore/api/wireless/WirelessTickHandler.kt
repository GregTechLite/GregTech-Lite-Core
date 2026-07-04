package gregtechlite.gtlitecore.api.wireless

import gregtechlite.gtlitecore.api.MOD_ID
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent

/**
 * Handles server tick events for wireless energy networks.
 * Updates all wireless channels periodically to perform energy transfer.
 */
@EventBusSubscriber(modid = MOD_ID)
object WirelessTickHandler {

    @SubscribeEvent
    @JvmStatic
    fun onWorldTick(event: WorldTickEvent) {
        val world = event.world ?: return
        if (world.isRemote) return
        if (world.totalWorldTime % 100 != 0L) return

        WirelessNetworkManager.update()
    }
}
