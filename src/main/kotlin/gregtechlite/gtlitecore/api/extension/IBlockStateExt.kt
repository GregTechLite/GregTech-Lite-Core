package gregtechlite.gtlitecore.api.extension

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * Transformed a [IBlockState] to [ItemStack] with [amount].
 */
fun IBlockState.toItem(amount: Int = 1): ItemStack
{
    return ItemStack(block, amount, block.getMetaFromState(this))
}