package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtechlite.gtlitecore.api.recipe.util.CIRCUIT_INFO_NBT
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

class CircuitPatternBehavior : IItemBehaviour
{

    override fun addInformation(stack: ItemStack,
                                lines: MutableList<String>)
    {
        if (stack.hasTagCompound())
        {
            lines.add(I18n.format("metaitem.tool.circuit_pattern.type",
                I18n.format("metaitem.tool.circuit_pattern.type.${stack.tagCompound?.getString(CIRCUIT_INFO_NBT)}")))
        }
        else
        {
            lines.add(I18n.format("metaitem.tool.circuit_pattern.type.empty"))
        }
    }

}