package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtech.api.items.toolitem.ToolClasses
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

object MultiblockCasing
{
    @JvmField
    val SUBSTRATE_CASING = Enum01.SUBSTRATE_CASING
    @JvmField
    val ADVANCED_SUBSTRATE_CASING = Enum01.ADVANCED_SUBSTRATE_CASING
    @JvmField
    val DRILL_HEAD = Enum01.DRILL_HEAD
    @JvmField
    val ADVANCED_FILTER_CASING = Enum01.ADVANCED_FILTER_CASING
    @JvmField
    val REFLECTIVE_SURFACE_CASING = Enum01.REFLECTIVE_SURFACE_CASING
    @JvmField
    val INFINITY_COOLING_CASING = Enum01.INFINITY_COOLING_CASING
    @JvmField
    val STELLAR_CONTAINMENT_CASING = Enum01.STELLAR_CONTAINMENT_CASING
    @JvmField
    val THERMAL_ENERGY_TRANSMISSION_CASING = Enum01.THERMAL_ENERGY_TRANSMISSION_CASING
    @JvmField
    val PARTICLE_CONTAINMENT_CASING = Enum01.PARTICLE_CONTAINMENT_CASING
    @JvmField
    val PARTICLE_EXCITATION_WIRE_COIL = Enum01.PARTICLE_EXCITATION_WIRE_COIL
    @JvmField
    val GRAVITY_STABILIZATION_CASING = Enum01.GRAVITY_STABILIZATION_CASING
    @JvmField
    val PROTOMATTER_ACTIVATION_COIL = Enum01.PROTOMATTER_ACTIVATION_COIL
    @JvmField
    val ANTIMATTER_ANNIHILATION_MATRIX = Enum01.ANTIMATTER_ANNIHILATION_MATRIX
    @JvmField
    val LATTICE_QCD_THERMAL_SHIELDING_CASING = Enum01.LATTICE_QCD_THERMAL_SHIELDING_CASING
    @JvmField
    val HAMILTON_KILLING_FLOW_CONTROL_CASING = Enum01.HAMILTON_KILLING_FLOW_CONTROL_CASING
    @JvmField
    val NANITE_TRANSMISSION_CASING = Enum01.NANITE_TRANSMISSION_CASING

    @JvmField
    val HAWKING_RADIATION_ABSORPTION_CASING = Enum02.HAWKING_RADIATION_ABSORPTION_CASING

    enum class Enum01(private val serializedName: String,
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
        HAMILTON_KILLING_FLOW_CONTROL_CASING("hamilton_killing_flow_control_casing"),
        NANITE_TRANSMISSION_CASING("nanite_transmission_casing");

        override val state: IBlockState
            get() = GTLiteBlocks.MULTIBLOCK_CASING_01.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteBlocks.MULTIBLOCK_CASING_01.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = ToolClasses.WRENCH
    }

    enum class Enum02(private val serializedName: String,
                      private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
    {
        HAWKING_RADIATION_ABSORPTION_CASING("hawking_radiation_absorption_casing");

        override val state: IBlockState
            get() = GTLiteBlocks.MULTIBLOCK_CASING_02.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteBlocks.MULTIBLOCK_CASING_02.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState): Int = harvestLevel

        override fun getHarvestTool(state: IBlockState): String = ToolClasses.WRENCH
    }
}