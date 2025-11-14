package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockWireCoil
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockWireCoil
 */
enum class GTWireCoil : BlockVariant
{

    CUPRONICKEL,
    KANTHAL,
    NICHROME,
    RTM_ALLOY,
    HSS_G,
    NAQUADAH,
    TRINIUM,
    TRITANIUM;

    override val state: IBlockState
        get() = MetaBlocks.WIRE_COIL.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
        = MetaBlocks.WIRE_COIL.getItemVariant(getCoilTypeByName(), count)

    private fun getCoilTypeByName() = when (this)
    {
        CUPRONICKEL -> BlockWireCoil.CoilType.CUPRONICKEL
        KANTHAL -> BlockWireCoil.CoilType.KANTHAL
        NICHROME -> BlockWireCoil.CoilType.NICHROME
        RTM_ALLOY -> BlockWireCoil.CoilType.RTM_ALLOY
        HSS_G -> BlockWireCoil.CoilType.HSS_G
        NAQUADAH -> BlockWireCoil.CoilType.NAQUADAH
        TRINIUM -> BlockWireCoil.CoilType.TRINIUM
        TRITANIUM -> BlockWireCoil.CoilType.TRITANIUM
    }

}