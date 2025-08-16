package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

object NuclearReactorCore
{

    val THORIUM = Enum01.THORIUM
    val PROTACTINIUM = Enum01.PROTACTINIUM
    val URANIUM = Enum01.URANIUM
    val NEPTUNIUM = Enum01.NEPTUNIUM
    val PLUTONIUM = Enum01.PLUTONIUM
    val AMERICIUM = Enum01.AMERICIUM
    val CURIUM = Enum01.CURIUM
    val BERKELIUM = Enum01.BERKELIUM
    val CALIFORNIUM = Enum02.CALIFORNIUM
    val EINSTEINIUM = Enum02.EINSTEINIUM
    val FERMIUM = Enum02.FERMIUM
    val MENDELEVIUM = Enum02.MENDELEVIUM

    enum class Enum01(private val serializedName: String,
                      private val harvestLevel: Int = 3) : BlockVariant, IStringSerializable,
                                                           IStateHarvestLevel, StateTier
    {

        THORIUM("thorium"),
        PROTACTINIUM("protactinium"),
        URANIUM("uranium"),
        NEPTUNIUM("neptunium"),
        PLUTONIUM("plutonium"),
        AMERICIUM("americium"),
        CURIUM("curium"),
        BERKELIUM("berkelium");

        override val state: IBlockState
            get() = GTLiteBlocks.NUCLEAR_REACTOR_CORE_01.getState(this)

        override val tier: Int
            get() = ordinal + 1

        override fun getStack(count: Int): ItemStack =
            GTLiteBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "wrench"

    }

    enum class Enum02(private val serializedName: String,
                      private val harvestLevel: Int = 3) : BlockVariant, IStringSerializable,
                                                           IStateHarvestLevel, StateTier
    {

        CALIFORNIUM("californium"),
        EINSTEINIUM("einsteinium"),
        FERMIUM("fermium"),
        MENDELEVIUM("mendelevium");

        override val state: IBlockState
            get() = GTLiteBlocks.NUCLEAR_REACTOR_CORE_02.getState(this)

        override val tier: Int
            get() = ordinal + 9

        override fun getStack(count: Int): ItemStack =
            GTLiteBlocks.NUCLEAR_REACTOR_CORE_02.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "wrench"

    }

}