package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockFireboxCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockFireboxCasing
 */
enum class GTFireboxCasing : BlockVariant
{

    BRONZE_FIREBOX,
    STEEL_FIREBOX,
    TITANIUM_FIREBOX,
    TUNGSTENSTEEL_FIREBOX;

    override val state: IBlockState
        get() = MetaBlocks.BOILER_FIREBOX_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(
            BlockFireboxCasing.FireboxCasingType.valueOf(this.name), count)
    }

}