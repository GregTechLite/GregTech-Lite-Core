package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

object TurbineCasing
{

    @JvmField
    val RHODIUM_PLATED_PALLADIUM_TURBINE = Enum01.RHODIUM_PLATED_PALLADIUM_TURBINE

    @JvmField
    val RHODIUM_PLATED_PALLADIUM_GEARBOX = Enum02.RHODIUM_PLATED_PALLADIUM_GEARBOX

    enum class Enum01(private val serializedName: String,
                      private val harvestLevel: Int = 4) : BlockVariant, IStringSerializable, IStateHarvestLevel
    {

        RHODIUM_PLATED_PALLADIUM_TURBINE("rhodium_plated_palladium_turbine");

        override val state: IBlockState
            get() = GTLiteBlocks.TURBINE_CASING_01.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteBlocks.TURBINE_CASING_01.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "wrench"

    }

    enum class Enum02(private val serializedName: String,
                      private val harvestLevel: Int = 4) : BlockVariant, IStringSerializable, IStateHarvestLevel
    {

        RHODIUM_PLATED_PALLADIUM_GEARBOX("rhodium_plated_palladium_gearbox");

        override val state: IBlockState
            get() = GTLiteBlocks.TURBINE_CASING_02.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteBlocks.TURBINE_CASING_02.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "wrench"

    }

}