package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockMachineCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockMachineCasing
 */
enum class GTMachineCasing : BlockVariant
{

    ULV,
    LV,
    MV,
    HV,
    EV,
    IV,
    LuV,
    ZPM,
    UV,
    UHV,
    UEV,
    UIV,
    UXV,
    OpV,
    MAX;

    override val state: IBlockState
        get() = MetaBlocks.MACHINE_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.MACHINE_CASING.getItemVariant(
            BlockMachineCasing.MachineCasingType.valueOf(this.name), count)
    }

}