package gregtechlite.gtlitecore.common.block.variant.aerospace

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class AerospaceCasing(private val serializedName: String,
                           private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
{

    HIGH_STRENGTH_CONCRETE("high_strength_concrete"),
    ELEVATOR_BASE_CASING("elevator_base_casing"),
    SUPPORT_STRUCTURE_CASING("elevator_support_structure"),
    INTERNAL_STRUCTURE_CASING("elevator_internal_structure"),
    DYSON_SWARM_ENERGY_RECEIVER_BASE_CASING("dyson_swarm_energy_receiver_base_casing"),
    DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_BASE_CASING("dyson_swarm_module_deployment_unit_base_casing"),
    DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_CORE("dyson_swarm_module_deployment_unit_core"),
    DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_SUPERCONDUCTING_MAGNET("dyson_swarm_module_deployment_unit_superconducting_magnet"),
    DYSON_SWARM_CONTROL_CENTER_BASE_CASING("dyson_swarm_control_center_base_casing"),
    DYSON_SWARM_CONTROL_CENTER_PRIMARY_WINDINGS("dyson_swarm_control_center_primary_windings"),
    DYSON_SWARM_CONTROL_CENTER_SECONDARY_WINDINGS("dyson_swarm_control_center_secondary_windings"),
    DYSON_SWARM_CONTROL_CENTER_TOROID_CASING("dyson_swarm_control_center_toroid_casing");

    override val state: IBlockState
        get() = GTLiteMetaBlocks.AEROSPACE_CASING.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.AEROSPACE_CASING.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}