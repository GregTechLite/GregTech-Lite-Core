package gregtechlite.gtlitecore.common.block.variant.science

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class ScienceCasing(private val serializedName: String,
                         private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
{

    MOLECULAR_CASING("molecular_casing"),
    MOLECULAR_COIL("molecular_coil"),
    HOLLOW_CASING("hollow_casing"),
    REINFORCED_TEMPORAL_STRUCTURE_CASING("reinforced_temporal_structure_casing"),
    REINFORCED_SPATIAL_STRUCTURE_CASING("reinforced_spatial_structure_casing"),
    INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING("infinite_spacetime_energy_boundary_casing"),
    DIMENSIONAL_BRIDGE_CASING("dimensional_bridge_casing");

    override val state: IBlockState
        get() = GTLiteMetaBlocks.SCIENCE_CASING_01.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.SCIENCE_CASING_01.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}