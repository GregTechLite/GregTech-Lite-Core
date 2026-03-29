package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.MetaBlocks
import gregtech.common.blocks.wood.BlockGregPlanks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.wood.BlockGregPlanks
 */
enum class GTPlankBlock : BlockVariant
{

    RUBBER,
    TREATED;

    override val state: IBlockState
        get() = MetaBlocks.PLANKS.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.PLANKS.getItemVariant(
            BlockGregPlanks.BlockType.valueOf(this.name), count)
    }

}