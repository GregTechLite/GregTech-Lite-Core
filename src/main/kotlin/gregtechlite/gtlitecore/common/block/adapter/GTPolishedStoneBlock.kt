package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.MetaBlocks
import gregtech.common.blocks.StoneVariantBlock
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.StoneVariantBlock
 */
enum class GTPolishedStoneBlock : BlockVariant
{

    BLACK_GRANITE,
    RED_GRANITE,
    MARBLE,
    BASALT,
    CONCRETE_LIGHT,
    CONCRETE_DARK;

    override val state: IBlockState
        get() = MetaBlocks.STONE_BLOCKS[StoneVariantBlock.StoneVariant.POLISHED]!!
            .getState(StoneVariantBlock.StoneType.valueOf(this.name))

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.STONE_BLOCKS[StoneVariantBlock.StoneVariant.POLISHED]!!
            .getItemVariant(StoneVariantBlock.StoneType.valueOf(this.name))
    }

}