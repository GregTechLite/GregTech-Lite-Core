package gregtechlite.gtlitecore.common.item

import baubles.api.BaublesApi
import com.morphismmc.morphismlib.integration.Mods
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.api.collection.openHashSetOf
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.common.item.behavior.LaserDestroyerBehavior
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
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

    private const val LASER_ITEM_KEY = "laser_destroyer"
    private const val MINING_TICKS = 10 * TICK
    private val miningSessions = openHashMapOf<UUID, MiningSession>()

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onLeftClickBlock(event: PlayerInteractEvent.LeftClickBlock)
    {
        val player = event.entityPlayer
        val world = event.world
        if (world.isRemote) return

        val item = player.heldItemMainhand
        if (!item.translationKey.contains(LASER_ITEM_KEY)) return

        event.isCanceled = true

        val pos = event.pos
        val isSilk = LaserDestroyerBehavior.isSilkMode(item)
        val neededTicks = LaserDestroyerBehavior.getMiningTicks(item)

        if (neededTicks <= 0)
        {
            val old = miningSessions.remove(player.uniqueID)
            if (old != null) world.sendBlockBreakProgress(player.entityId, old.pos, -1)
            world.sendBlockBreakProgress(player.entityId, pos, -1)
            LaserDestroyerBehavior.breakBlock(item, player, world, pos, isSilk, energyCost(isSilk))
            return
        }

        val now = world.totalWorldTime
        val session = miningSessions[player.uniqueID]

        if (session == null || session.pos != pos || session.dimension != world.provider.dimension)
        {
            if (session != null) world.sendBlockBreakProgress(player.entityId, session.pos, -1)
            miningSessions[player.uniqueID] = MiningSession(pos, world.provider.dimension).apply {
                this.neededTicks = neededTicks
                progress = 1
                lastEventTick = now
            }
        }
        else
        {
            session.progress++
            session.lastEventTick = now
        }

        val current = miningSessions[player.uniqueID] ?: return
        if (current.progress >= current.neededTicks)
        {
            miningSessions.remove(player.uniqueID)
            world.sendBlockBreakProgress(player.entityId, pos, -1)
            LaserDestroyerBehavior.breakBlock(item, player, world, pos, isSilk, energyCost(isSilk))
        }
        else
        {
            val stage = (current.progress * 9 / current.neededTicks).coerceIn(1, 9)
            if (stage != current.lastStage)
            {
                world.sendBlockBreakProgress(player.entityId, pos, stage)
                current.lastStage = stage
            }
        }
    }

    @SubscribeEvent
    fun onLaserMiningTick(event: TickEvent.PlayerTickEvent)
    {
        if (event.phase != TickEvent.Phase.START || event.side == Side.CLIENT) return
        val player = event.player as? EntityPlayerMP ?: return
        val session = miningSessions[player.uniqueID] ?: return

        if (player.world.provider.dimension != session.dimension
            || player.world.getBlockState(session.pos).block === Blocks.AIR)
        {
            miningSessions.remove(player.uniqueID)
            player.world.sendBlockBreakProgress(player.entityId, session.pos, -1)
            return
        }

        // Reset when the button was released.
        if (player.world.totalWorldTime - session.lastEventTick > MINING_TICKS)
        {
            miningSessions.remove(player.uniqueID)
            player.world.sendBlockBreakProgress(player.entityId, session.pos, -1)
            return
        }

        // Reset when the laser is no longer in the main hand.
        if (!player.heldItemMainhand.translationKey.contains(LASER_ITEM_KEY))
        {
            miningSessions.remove(player.uniqueID)
            player.world.sendBlockBreakProgress(player.entityId, session.pos, -1)
        }
    }

    private fun energyCost(isSilk: Boolean): Long = if (isSilk) 8L else 4L

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

    private data class MiningSession(val pos: BlockPos, val dimension: Int)
    {
        var progress = 0
        var neededTicks = 0
        var lastEventTick = 0L
        var lastStage = -1
    }
}