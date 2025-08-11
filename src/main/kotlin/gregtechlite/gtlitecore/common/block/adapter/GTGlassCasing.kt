package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see BlockGlassCasing
 */
enum class GTGlassCasing : BlockVariant
{

    TEMPERED_GLASS,
    FUSION_GLASS,
    LAMINATED_GLASS,
    CLEANROOM_GLASS;

    override val state: IBlockState
        get() = MetaBlocks.TRANSPARENT_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.TRANSPARENT_CASING.getItemVariant(
            BlockGlassCasing.CasingType.valueOf(this.name), count)
    }

}