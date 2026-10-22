package gregtechlite.gtlitecore.integration.appeng.handler

import appeng.api.config.Actionable
import appeng.api.features.IWirelessTermHandler
import appeng.api.networking.security.IActionHost
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
        val sources = ArrayList<ItemStack>(player.inventory.mainInventory.size + 16)
        sources.addAll(player.inventory.mainInventory)

        if (Mods.Baubles.isActive)
        {
            sources.addAll(BaubleModule.getEquippedStacks(player))
        }

        for (stack in sources)
        {
            if (stack.isEmpty || stack.item !is IWirelessTermHandler) continue
            val handler = stack.item as IWirelessTermHandler
            if (!handler.canHandle(stack)) continue
            try
            {
                val terminal = WirelessTerminalGuiObject(handler, stack, player, player.world, -1, 0, 0)
                if (!terminal.rangeCheck()) continue
                for (candidate in candidates)
                {
                    val request = AEItemStack.fromItemStack(candidate) ?: continue
                    request.stackSize = 1L
                    val extracted = terminal.extractItems(request, Actionable.MODULATE,
                                                          PlayerSource(player, null as IActionHost?)) ?: continue
                    if (extracted.stackSize <= 0L) continue
                    return extracted.createItemStack().apply { count = 1 }
                }
            }
            catch (_: Exception) {}
        }
        return null
    }
}