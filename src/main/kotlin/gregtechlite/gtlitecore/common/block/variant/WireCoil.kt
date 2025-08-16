package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IHeatingCoilBlockStats
import gregtech.api.block.IStateHarvestLevel
import gregtech.api.unification.material.Material
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class WireCoil(private val serializedName: String,
                    private val coilTemperature: Int,
                    private val level: Int,
                    private val energyDiscount: Int,
                    private val material: Material,
                    private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable,
                                                         IStateHarvestLevel, IHeatingCoilBlockStats
{

    // UHV: Adamantium, 12600K (10800K + 1800K)
    ADAMANTIUM("adamantium", 12600, 16, 16, GTLiteMaterials.Adamantium),
    // UEV: Infinity, 14400K (12600K + 1800K)
    INFINITY("infinity", 14400, 32, 16, GTLiteMaterials.Infinity),
    // UIV: Halkonite Steel, 16201K (14400K + 1801K)
    HALKONITE_STEEL("halkonite_steel", 16201, 32, 32, GTLiteMaterials.HalkoniteSteel),
    // UXV: Space Time, 18000K (16201K + 1799K)
    SPACE_TIME("space_time", 18000, 64, 32, GTLiteMaterials.SpaceTime),
    // OpV: Eternity, 19800K (18000K + 1800K)
    ETERNITY("eternity", 19800, 64, 64, GTLiteMaterials.Eternity);

    override val state: IBlockState
        get() = GTLiteBlocks.WIRE_COIL.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.WIRE_COIL.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

    override fun getCoilTemperature() = coilTemperature

    override fun getLevel() = level

    override fun getEnergyDiscount() = energyDiscount

    override fun getMaterial() = material

    override fun getTier() = this.ordinal + 9 // After Tritanium Wire Coil (8).

}