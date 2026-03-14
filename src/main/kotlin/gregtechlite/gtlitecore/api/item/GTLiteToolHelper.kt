package gregtechlite.gtlitecore.api.item

import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.PropertyKey
import net.minecraft.item.ItemStack

object GTLiteToolHelper
{

    /**
     * Gets maximum durability of the material from its `ToolProperty`.
     *
     * This method is just a short-circuit method about `ToolProperty.getToolDurability`, it avoid the situation which
     * the material not has `ToolProperty`.
     *
     * @param material The material which has `ToolProperty`.
     */
    @JvmStatic
    fun getMaxDurability(material: Material): Int
    {
        if (!material.hasProperty(PropertyKey.TOOL))
            throw IllegalArgumentException("Material ${material.name} does not have ToolProperty!")
        return material.getProperty(PropertyKey.TOOL).toolDurability
    }

    /**
     * Gets maximum crafting durability of the material from its `ToolProperty`.
     *
     * @param material The material which has `ToolProperty`.
     */
    @JvmStatic
    fun getMaxCraftingDurability(material: Material): Int
    {
        if (!material.hasProperty(PropertyKey.TOOL))
            throw IllegalArgumentException("Material ${material.name} does not have ToolProperty!")
        return material.getProperty(PropertyKey.TOOL).toolDurability / 2
    }

    /**
     * Checks if the [ItemStack] has full durability (a.k.a. not damaged).
     *
     * The [ItemStack] must be not repairable at first for pending check, otherwise returns `false` directly.
     *
     * @param stack The [ItemStack] which will be checked.
     */
    @JvmStatic
    fun isItemHasFullDurability(stack: ItemStack): Boolean
    {
        val item = stack.item
        return !item.isRepairable || item.getDamage(stack) <= 0
    }

}