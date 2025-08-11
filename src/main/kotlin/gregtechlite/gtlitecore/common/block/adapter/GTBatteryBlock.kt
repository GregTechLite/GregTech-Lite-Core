package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockBatteryPart
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockBatteryPart
 */
enum class GTBatteryBlock : BlockVariant
{

    EMPTY_TIER_I,
    LAPOTRONIC_EV,
    LAPOTRONIC_IV,
    EMPTY_TIER_II,
    LAPOTRONIC_LuV,
    LAPOTRONIC_ZPM,
    EMPTY_TIER_III,
    LAPOTRONIC_UV,
    ULTIMATE_UHV;

    override val state: IBlockState
        get() = MetaBlocks.BATTERY_BLOCK.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.BATTERY_BLOCK.getItemVariant(
            BlockBatteryPart.BatteryPartType.valueOf(this.name), count)
    }

}