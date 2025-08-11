package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockBoilerCasing
 */
enum class GTBoilerCasing : BlockVariant
{

    BRONZE_PIPE,
    STEEL_PIPE,
    TITANIUM_PIPE,
    TUNGSTENSTEEL_PIPE,
    POLYTETRAFLUOROETHYLENE_PIPE;

    override val state: IBlockState
        get() = MetaBlocks.BOILER_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.BOILER_CASING.getItemVariant(
            BlockBoilerCasing.BoilerCasingType.valueOf(this.name), count)
    }

}