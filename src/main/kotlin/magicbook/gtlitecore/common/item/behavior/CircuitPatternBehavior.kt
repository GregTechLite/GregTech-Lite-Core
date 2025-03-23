package magicbook.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.stats.IItemBehaviour
import magicbook.gtlitecore.loader.recipe.producer.CircuitAssemblyLineRecipeProducer
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class CircuitPatternBehavior : IItemBehaviour
{

    override fun addInformation(stack: ItemStack,
                                lines: MutableList<String>)
    {
        if (stack.hasTagCompound())
        {
            lines.add(I18n.format("metaitem.tool.circuit_pattern.type",
                I18n.format("metaitem.tool.circuit_pattern.type.${stack.tagCompound?.getString(CircuitAssemblyLineRecipeProducer.INFO_NBT_NAME)}")))
        }
        else
        {
            lines.add(I18n.format("metaitem.tool.circuit_pattern.type.empty"))
        }
    }

}