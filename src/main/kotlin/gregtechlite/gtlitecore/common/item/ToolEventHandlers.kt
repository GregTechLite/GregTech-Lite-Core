package gregtechlite.gtlitecore.common.item

import gregtechlite.gtlitecore.common.item.behavior.LaserDestroyerBehavior
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Suppress("unused")
object ToolEventHandlers
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onLeftClickBlock(event: PlayerInteractEvent.LeftClickBlock)
    {
        val player = event.entityPlayer
        val world = event.world
        val item = player.getHeldItem(event.hand)

        if (!world.isRemote && item.translationKey.contains("laser_destroyer"))
        {
            val pos = event.pos
            val isSilkMode = LaserDestroyerBehavior.isSilkMode(item)
            val energyCost = if (isSilkMode) 64L else 16L

            event.isCanceled = true
            LaserDestroyerBehavior.breakBlock(item, player, world, pos, isSilkMode, energyCost)
        }
    }

}