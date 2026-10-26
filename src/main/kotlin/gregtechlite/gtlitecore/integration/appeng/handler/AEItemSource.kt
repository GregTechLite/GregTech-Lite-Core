package gregtechlite.gtlitecore.integration.appeng.handler

import appeng.api.config.Actionable
import appeng.api.config.PowerMultiplier
import appeng.api.features.IWirelessTermHandler
import appeng.api.networking.security.IActionHost
import appeng.core.AEConfig
import appeng.helpers.WirelessTerminalGuiObject
import appeng.me.helpers.PlayerSource
import appeng.util.item.AEItemStack
import com.morphismmc.morphismlib.integration.Mods
import gregtechlite.gtlitecore.integration.baubles.BaubleModule
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack

object AEItemSource
{
    @JvmStatic
    fun extract(player: EntityPlayer, candidates: List<ItemStack>): ItemStack?
    {
        for (stack in sourceStacks(player))
        {
            if (stack.isEmpty || stack.item !is IWirelessTermHandler) continue
            val handler = stack.item as IWirelessTermHandler
            if (!handler.canHandle(stack)) continue
            try
            {
                val terminal = WirelessTerminalGuiObject(handler, stack, player, player.world, -1, 0, 0)
                if (!terminal.rangeCheck()) continue
                val cost = powerCost(terminal)
                if (terminal.extractAEPower(cost, Actionable.SIMULATE, PowerMultiplier.CONFIG) < cost) continue
                for (candidate in candidates)
                {
                    val request = AEItemStack.fromItemStack(candidate) ?: continue
                    request.stackSize = 1L
                    val extracted = terminal.extractItems(request, Actionable.MODULATE,
                                                          PlayerSource(player, null as IActionHost?)) ?: continue
                    if (extracted.stackSize <= 0L) continue
                    terminal.extractAEPower(cost, Actionable.MODULATE, PowerMultiplier.CONFIG)
                    return extracted.createItemStack().apply { count = 1 }
                }
            }
            catch (_: Exception) {}
        }
        return null
    }

    @JvmStatic
    fun insert(player: EntityPlayer, stack: ItemStack): Boolean
    {
        if (stack.isEmpty)
            return true
        for (source in sourceStacks(player))
        {
            if (source.isEmpty || source.item !is IWirelessTermHandler) continue
            val handler = source.item as IWirelessTermHandler
            if (!handler.canHandle(source)) continue
            try
            {
                val terminal = WirelessTerminalGuiObject(handler, source, player, player.world, -1, 0, 0)
                if (!terminal.rangeCheck()) continue
                val request = AEItemStack.fromItemStack(stack) ?: return false
                request.stackSize = stack.count.toLong()
                val inserted = terminal.injectItems(request, Actionable.MODULATE,
                                                    PlayerSource(player, null as IActionHost?))
                if (inserted == null || inserted.stackSize <= 0L)
                    return true
                stack.count = inserted.stackSize.toInt()
                return false
            }
            catch (_: Exception) {}
        }
        return false
    }

    private fun powerCost(terminal: WirelessTerminalGuiObject): Double
        = AEConfig.instance().wireless_getDrainRate(terminal.range.coerceAtLeast(1.0))

    private fun sourceStacks(player: EntityPlayer): List<ItemStack>
    {
        val sources = ArrayList<ItemStack>(player.inventory.mainInventory.size + 16)
        sources.addAll(player.inventory.mainInventory)
        if (Mods.Baubles.isActive)
            sources.addAll(BaubleModule.getEquippedStacks(player))
        return sources
    }
}
