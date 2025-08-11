package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockHermeticCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockHermeticCasing
 */
enum class GTHermeticCasing : BlockVariant
{

    HERMETIC_LV,
    HERMETIC_MV,
    HERMETIC_HV,
    HERMETIC_EV,
    HERMETIC_IV,
    HERMETIC_LUV,
    HERMETIC_ZPM,
    HERMETIC_UV,
    HERMETIC_UHV;

    override val state: IBlockState
        get() = MetaBlocks.HERMETIC_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.HERMETIC_CASING.getItemVariant(
            BlockHermeticCasing.HermeticCasingsType.valueOf(this.name), count)
    }

}