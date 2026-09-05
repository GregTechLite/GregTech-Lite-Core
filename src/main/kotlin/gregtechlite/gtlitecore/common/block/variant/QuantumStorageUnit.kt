package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtech.api.items.toolitem.ToolClasses
import gregtechlite.gtlitecore.api.block.QuantumStorageStats
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable
import java.math.BigInteger

enum class QuantumStorageUnit(private val serializedName: String,
                              override val distinctSlots: Int,
                              override val totalCapacity: BigInteger,
                              private val harvestLevel: Int = 2) : BlockVariant, StateTier, IStringSerializable,
                                                                   IStateHarvestLevel, QuantumStorageStats
{
    T1("t1", 256     , BigInteger.TEN.pow(9)),
    T2("t2", 1024    , BigInteger.TEN.pow(12)),
    T3("t3", 4096    , BigInteger.TEN.pow(15)),
    T4("t4", 16384   , BigInteger.TEN.pow(18)),
    T5("t5", 65536   , BigInteger.TEN.pow(21)),
    T6("t6", 262144  , BigInteger.TEN.pow(24)),
    T7("t7", 1048576 , BigInteger.TEN.pow(27)),
    T8("t8", 4194304 , BigInteger.TEN.pow(30)),
    T9("t9", 16777216, BigInteger.TEN.pow(33));

    override val state: IBlockState
        get() = GTLiteBlocks.QUANTUM_STORAGE_UNIT.getState(this)

    override val tier: Int
        get() = ordinal + 1

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.QUANTUM_STORAGE_UNIT.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState): Int = harvestLevel

    override fun getHarvestTool(state: IBlockState): String = ToolClasses.WRENCH
}