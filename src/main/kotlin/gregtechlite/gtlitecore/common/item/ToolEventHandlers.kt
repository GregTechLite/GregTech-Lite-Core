package gregtechlite.gtlitecore.common.item

import baubles.api.BaublesApi
import com.morphismmc.morphismlib.integration.Mods
import gregtechlite.gtlitecore.api.collection.openHashSetOf
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.common.item.behavior.LaserDestroyerBehavior
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.relauncher.Side
import java.util.UUID

@Suppress("unused")
object ToolEventHandlers
{
    private val affectedPlayerIds = openHashSetOf<UUID>()

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
            val energyCost = if (isSilkMode) 8L else 4L

            event.isCanceled = true
            LaserDestroyerBehavior.breakBlock(item, player, world, pos, isSilkMode, energyCost)
        }
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent)
    {
        if (event.phase != TickEvent.Phase.START || event.side == Side.CLIENT) return
        val player = event.player as? EntityPlayerMP ?: return
        if (canGrantFlyAbilities(player))
        {
            grantFlyAbilities(player)
            affectedPlayerIds.add(player.uniqueID)
        }
        else if (affectedPlayerIds.remove(player.uniqueID))
        {
            revokeFlyAbilities(player)
        }
    }

    private fun canGrantFlyAbilities(player: EntityPlayerMP): Boolean
    {
        val item = GTLiteMetaItems.MAGICBOOK.stack()
        // Hand
        if (player.heldItemMainhand.isItemEqual(item)) return true
        if (player.heldItemOffhand.isItemEqual(item)) return true

        // Inventory
        for (i in 0 until player.inventory.inventoryStackLimit)
        {
            if (player.inventory.getStackInSlot(i).isItemEqual(item)) return true
        }

        // Bauble Inventory
        if (Mods.Baubles.isActive)
        {
            val baubleInventory = BaublesApi.getBaublesHandler(player) ?: return false
            for (i in 0 until baubleInventory.slots)
            {
                if (baubleInventory.getStackInSlot(i).isItemEqual(item)) return true
            }
        }

        return false
    }

    private fun grantFlyAbilities(player: EntityPlayerMP)
    {
        player.capabilities.allowFlying = true
        player.capabilities.disableDamage = true
        player.sendPlayerAbilities()
    }

    private fun revokeFlyAbilities(player: EntityPlayerMP)
    {
        if (player.isCreative) return
        player.capabilities.allowFlying = false
        player.capabilities.isFlying = false
        player.capabilities.disableDamage = false
        player.sendPlayerAbilities()
    }
}