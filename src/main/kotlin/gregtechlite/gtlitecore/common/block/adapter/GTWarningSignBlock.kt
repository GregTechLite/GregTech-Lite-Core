package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockWarningSign
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockWarningSign
 */
enum class GTWarningSignBlock : BlockVariant
{

    YELLOW_STRIPES,
    SMALL_YELLOW_STRIPES,
    RADIOACTIVE_HAZARD,
    BIO_HAZARD,
    EXPLOSION_HAZARD,
    FIRE_HAZARD,
    ACID_HAZARD,
    MAGIC_HAZARD,
    FROST_HAZARD,
    NOISE_HAZARD,
    GENERIC_HAZARD,
    HIGH_VOLTAGE_HAZARD,
    MAGNETIC_HAZARD,
    ANTIMATTER_HAZARD,
    HIGH_TEMPERATURE_HAZARD,
    VOID_HAZARD;

    override val state: IBlockState
        get() = MetaBlocks.WARNING_SIGN.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.WARNING_SIGN.getItemVariant(
            BlockWarningSign.SignType.valueOf(this.name), count)
    }

}