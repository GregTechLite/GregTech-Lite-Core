package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.stats.IItemBehaviour
import net.minecraft.item.ItemStack

class BottlecrateBehavior(val color: Int = 0xFFFFFFFF.toInt()) : IItemBehaviour
{
    companion object
    {
        fun canInsertInto(stack: ItemStack): Boolean
        {
            if (stack.isEmpty) return false
            val item = stack.item
            if (item is MetaItem<*>)
            {
                val valueItem = item.getItem(stack)
                return valueItem != null && valueItem.behaviours.any { it is BottlecrateBehavior }
            }
            return false
        }

        fun getColor(stack: ItemStack): Int
        {
            if (stack.isEmpty) return 0xFFFFFFFF.toInt()
            val item = stack.item
            if (item is MetaItem<*>)
            {
                val valueItem = item.getItem(stack)
                val behavior = valueItem?.behaviours?.filterIsInstance<BottlecrateBehavior>()?.firstOrNull()
                return behavior?.color ?: 0xFFFFFFFF.toInt()
            }
            return 0xFFFFFFFF.toInt()
        }
    }
}
