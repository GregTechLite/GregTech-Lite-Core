package gregtechlite.gtlitecore.api.block

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

class TranslatableVariantItemBlock<T>(block: T) : ItemBlock(block) where T : Block, T : TranslatableBlock
{

    init
    {
        this.setHasSubtypes(true)
    }

    override fun getMetadata(damage: Int): Int = damage

    override fun getTranslationKey(stack: ItemStack): String
    {
        return (block as TranslatableBlock).getTranslation(getBlockState(stack))
    }

    @Suppress("Deprecation")
    fun getBlockState(stack: ItemStack): IBlockState
    {
        return this.block.getStateFromMeta(stack.getItemDamage())
    }

}