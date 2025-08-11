package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockMetalCasing
 */
enum class GTMetalCasing : BlockVariant
{
    BRONZE_BRICKS,
    PRIMITIVE_BRICKS,
    INVAR_HEATPROOF,
    ALUMINIUM_FROSTPROOF,
    STEEL_SOLID,
    STAINLESS_CLEAN,
    TITANIUM_STABLE,
    TUNGSTENSTEEL_ROBUST,
    COKE_BRICKS,
    PTFE_INERT_CASING,
    HSSE_STURDY,
    PALLADIUM_SUBSTATION;

    override val state: IBlockState
        get() = MetaBlocks.METAL_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.METAL_CASING.getItemVariant(
            BlockMetalCasing.MetalCasingType.valueOf(this.name), count)
    }

}