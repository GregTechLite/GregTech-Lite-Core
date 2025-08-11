package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockCleanroomCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockCleanroomCasing
 */
enum class GTCleanroomCasing : BlockVariant
{

    PLASCRETE,
    FILTER_CASING,
    FILTER_CASING_STERILE;

    override val state: IBlockState
        get() = MetaBlocks.CLEANROOM_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.CLEANROOM_CASING.getItemVariant(
            BlockCleanroomCasing.CasingType.valueOf(this.name), count)
    }

}