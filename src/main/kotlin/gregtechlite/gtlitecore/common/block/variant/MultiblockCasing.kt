package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class MultiblockCasing(private val serializedName: String,
                            private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
{

    SUBSTRATE_CASING("substrate_casing"),
    ADVANCED_SUBSTRATE_CASING("advanced_substrate_casing"),
    DRILL_HEAD("drill_head"),
    ADVANCED_FILTER_CASING("advanced_filter_casing"),
    REFLECTIVE_SURFACE_CASING("reflective_surface_casing"),
    INFINITY_COOLING_CASING("infinity_cooling_casing"),
    STELLAR_CONTAINMENT_CASING("stellar_containment_casing"),
    THERMAL_ENERGY_TRANSMISSION_CASING("thermal_energy_transmission_casing"),
    PARTICLE_CONTAINMENT_CASING("particle_containment_casing"),
    PARTICLE_EXCITATION_WIRE_COIL("particle_excitation_wire_coil"),
    GRAVITY_STABILIZATION_CASING("gravity_stabilization_casing"),
    PROTOMATTER_ACTIVATION_COIL("protomatter_activation_coil"),
    ANTIMATTER_ANNIHILATION_MATRIX("antimatter_annihilation_matrix"),
    LATTICE_QCD_THERMAL_SHIELDING_CASING("lattice_qcd_thermal_shielding_casing"),
    HAMILTON_KILLING_FLOW_CONTROL_CASING("hamilton_killing_flow_control_casing");

    override val state: IBlockState
        get() = GTLiteBlocks.MULTIBLOCK_CASING_01.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.MULTIBLOCK_CASING_01.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}