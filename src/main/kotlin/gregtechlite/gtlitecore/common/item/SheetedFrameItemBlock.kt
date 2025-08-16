package gregtechlite.gtlitecore.common.item

import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix
import gregtechlite.gtlitecore.common.block.BlockSheetedFrame
import net.minecraft.block.state.IBlockState
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.world.World

@Suppress("Deprecation")
class SheetedFrameItemBlock(private val frameBlock: BlockSheetedFrame) : ItemBlock(frameBlock)
{

    init
    {
        this.setHasSubtypes(true)
    }

    override fun getMetadata(damage: Int) = damage

    fun getBlockState(stack: ItemStack): IBlockState
    {
        return frameBlock.getStateFromMeta(getMetadata(stack.getItemDamage()))
    }

    override fun getItemStackDisplayName(stack: ItemStack): String
    {
        val material = getBlockState(stack).getValue(frameBlock.VARIANT)
        return GTLiteOrePrefix.sheetedFrame.getLocalNameForItem(material)
    }

    override fun addInformation(stack: ItemStack,
                                worldIn: World?,
                                tooltip: MutableList<String?>,
                                flagIn: ITooltipFlag)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        if (ConfigHolder.misc.debug)
        {
            tooltip.add("MetaItem Id: sheetedFrame${frameBlock.getGTMaterial(stack.metadata).toCamelCaseString()}")
        }
    }

}
