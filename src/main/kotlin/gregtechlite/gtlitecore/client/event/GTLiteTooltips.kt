package gregtechlite.gtlitecore.client.event

import gregtechlite.gtlitecore.client.util.ItemStackMap
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.oredict.OreDictionary

object GTLiteTooltips
{

    // (ItemStack, Supplier<String>) => ItemStack.tooltip(String)
    private val tooltipLookup = ItemStackMap<() -> String>(false)

    @SubscribeEvent
    internal fun renderTooltip(event: ItemTooltipEvent)
    {
        tooltipLookup[event.itemStack]?.also { tooltip ->
            event.toolTip.addAll(tooltip().split("\n"))
        }
    }

    /**
     * Add static `tooltip` to all items with `oreDictName`, this method is
     * useful for some grouped items like Gregtech materials.
     *
     * The items must be registered in the [net.minecraftforge.oredict.OreDictionary] when this method is
     * called, and items with equal registry name and meta but different NBT are
     * considered equal.
     *
     * @param oreDictName Ore Dictionary name.
     * @param tooltip     Static string supplier.
     * @author glowredman
     */
    fun addOreDictTooltip(oreDictName: String, tooltip: () -> String)
    {
        OreDictionary.getOres(oreDictName).forEach { addItemTooltip(it, tooltip) }
    }

    /**
     * Add `tooltip` to specific item with `modId`, `name` and `meta`.
     *
     * For default, if `modId` is `null`, then will search `name` and
     * `meta` in the mod.
     *
     * @param location    The item name.
     * @param meta    The item meta.
     * @param tooltip Static string supplier.
     *
     * @author glowredman
     */
    fun addItemTooltip(location: ResourceLocation, meta: Int, tooltip: () -> String)
    {
        ForgeRegistries.ITEMS.getValue(location)?.also {
            tooltipLookup.merge(ItemStack(it, 1, meta), tooltip) { a, b ->
                { a() + "\n" + b() }
            }
        }
    }

    /**
     * Add `tooltip` to `item`.
     *
     * Items with equal registry name and meta but different NBT are considered equal,
     * using [OreDictionary.WILDCARD_VALUE] or [gregtech.api.GTValues.W] as meta is allowed.
     *
     * @param item    The item stack.
     * @param tooltip Static string supplier.
     *
     * @author glowredman
     */
    fun addItemTooltip(item: ItemStack, tooltip: () -> String)
    {
        tooltipLookup.merge(item, tooltip) { a, b -> { a() + "\n" + b() } }
    }

}