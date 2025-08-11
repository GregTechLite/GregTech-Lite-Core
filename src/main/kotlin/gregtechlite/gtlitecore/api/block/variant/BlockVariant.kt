package gregtechlite.gtlitecore.api.block.variant

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

interface BlockVariant
{

    val state: IBlockState

    val stack: ItemStack
        get() = getStack(1)

    fun getStack(count: Int): ItemStack

}