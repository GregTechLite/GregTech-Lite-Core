package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockComputerCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockComputerCasing
 */
enum class GTComputerCasing : BlockVariant
{

    COMPUTER_CASING,
    COMPUTER_HEAT_VENT,
    HIGH_POWER_CASING,
    ADVANCED_COMPUTER_CASING;

    override val state: IBlockState
        get() = MetaBlocks.COMPUTER_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.COMPUTER_CASING.getItemVariant(
            BlockComputerCasing.CasingType.valueOf(this.name), count)
    }

}