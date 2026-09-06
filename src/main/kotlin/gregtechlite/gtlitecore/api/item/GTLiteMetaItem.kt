package gregtechlite.gtlitecore.api.item

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class GTLiteMetaItem() : StandardMetaItem(0)
{
    override fun createItemModelPath(metaValueItem: MetaItem<*>.MetaValueItem, postfix: String): ResourceLocation
        = GTLiteMod.id(formatModelPath(metaValueItem) + postfix)

    override fun getHarvestLevel(stack: ItemStack, toolClass: String, player: EntityPlayer?, blockState: IBlockState?): Int
    {
        val valueItem = (stack.item as MetaItem<*>).getItem(stack)
        return if (valueItem?.unlocalizedName == GTLiteMetaItems.LASER_DESTROYER.unlocalizedName) Int.MAX_VALUE
               else super.getHarvestLevel(stack, toolClass, player, blockState)
    }


}