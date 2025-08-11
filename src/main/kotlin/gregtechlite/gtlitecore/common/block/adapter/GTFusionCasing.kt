package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockFusionCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockFusionCasing
 */
enum class GTFusionCasing : BlockVariant
{

    SUPERCONDUCTOR_COIL,
    FUSION_COIL,
    FUSION_CASING,
    FUSION_CASING_MK2,
    FUSION_CASING_MK3;

    override val state: IBlockState
        get() = MetaBlocks.FUSION_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.FUSION_CASING.getItemVariant(
            BlockFusionCasing.CasingType.valueOf(this.name), count)
    }

}