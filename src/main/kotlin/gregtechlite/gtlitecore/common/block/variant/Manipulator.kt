package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class Manipulator(private val serializedName: String,
                       private val harvestLevel: Int = 3) : BlockVariant, IStringSerializable,
                                                            IStateHarvestLevel, StateTier
{

    NEUTRON_PULSE("neutron_pulse"),
    COSMIC_FABRIC("cosmic_fabric"),
    INFINITY_INFUSED("infinity_infused"),
    SPACETIME_CONTINUUM_RIPPER("spacetime_continuum_ripper");

    override val state: IBlockState
        get() = GTLiteBlocks.MANIPULATOR.getState(this)

    override val tier: Int
        get() = ordinal + 1

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.MANIPULATOR.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}