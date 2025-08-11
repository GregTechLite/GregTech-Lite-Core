package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockTurbineCasing
 */
enum class GTTurbineCasing : BlockVariant
{

    BRONZE_GEARBOX,
    STEEL_GEARBOX,
    STAINLESS_STEEL_GEARBOX,
    TITANIUM_GEARBOX,
    TUNGSTENSTEEL_GEARBOX,
    STEEL_TURBINE_CASING,
    TITANIUM_TURBINE_CASING,
    STAINLESS_TURBINE_CASING,
    TUNGSTENSTEEL_TURBINE_CASING;

    override val state: IBlockState
        get() = MetaBlocks.TURBINE_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.TURBINE_CASING.getItemVariant(
            BlockTurbineCasing.TurbineCasingType.valueOf(this.name), count)
    }

}