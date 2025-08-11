package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

object MetalCasing
{
    @JvmField
    val MARAGING_STEEL_250 = Enum01.MARAGING_STEEL_250
    @JvmField
    val INCONEL_625 = Enum01.INCONEL_625
    @JvmField
    val BLUE_STEEL = Enum01.BLUE_STEEL
    @JvmField
    val STABALLOY = Enum01.STABALLOY
    @JvmField
    val TALONITE = Enum01.TALONITE
    @JvmField
    val IRIDIUM = Enum01.IRIDIUM
    @JvmField
    val ZERON_100 = Enum01.ZERON_100
    @JvmField
    val WATERTIGHT_STEEL = Enum01.WATERTIGHT_STEEL
    @JvmField
    val STELLITE = Enum01.STELLITE
    @JvmField
    val TUMBAGA = Enum01.TUMBAGA
    @JvmField
    val EGLIN_STEEL = Enum01.EGLIN_STEEL
    @JvmField
    val POTIN = Enum01.POTIN
    @JvmField
    val GRISIUM = Enum01.GRISIUM
    @JvmField
    val BABBIT_ALLOY = Enum01.BABBIT_ALLOY
    @JvmField
    val SILICON_CARBIDE = Enum01.SILICON_CARBIDE
    @JvmField
    val RED_STEEL = Enum01.RED_STEEL

    @JvmField
    val HSLA_STEEL = Enum02.HSLA_STEEL
    @JvmField
    val KOVAR = Enum02.KOVAR
    @JvmField
    val BLACK_STEEL = Enum02.BLACK_STEEL
    @JvmField
    val INCOLOY_MA813 = Enum02.INCOLOY_MA813
    @JvmField
    val MONEL_500 = Enum02.MONEL_500
    @JvmField
    val INCOLOY_MA956 = Enum02.INCOLOY_MA956
    @JvmField
    val ZIRCONIUM_CARBIDE = Enum02.ZIRCONIUM_CARBIDE
    @JvmField
    val HASTELLOY_C276 = Enum02.HASTELLOY_C276
    @JvmField
    val HASTELLOY_X = Enum02.HASTELLOY_X
    @JvmField
    val POLYBENZIMIDAZOLE = Enum02.POLYBENZIMIDAZOLE
    @JvmField
    val ALUMINIUM_BRONZE = Enum02.ALUMINIUM_BRONZE
    @JvmField
    val HASTELLOY_N = Enum02.HASTELLOY_N
    @JvmField
    val RENE_N5 = Enum02.RENE_N5
    @JvmField
    val BISMUTH_BRONZE = Enum02.BISMUTH_BRONZE
    @JvmField
    val BRASS = Enum02.BRASS
    @JvmField
    val TITANIUM_TUNGSTEN_CARBIDE = Enum02.TITANIUM_TUNGSTEN_CARBIDE

    @JvmField
    val COBALT_BRASS = Enum03.COBALT_BRASS
    @JvmField
    val TRINAQUADALLOY = Enum03.TRINAQUADALLOY
    @JvmField
    val OSMIRIDIUM = Enum03.OSMIRIDIUM
    @JvmField
    val NEUTRONIUM = Enum03.NEUTRONIUM
    @JvmField
    val NAQUADAH_ALLOY = Enum03.NAQUADAH_ALLOY
    @JvmField
    val QUANTUM_ALLOY = Enum03.QUANTUM_ALLOY
    @JvmField
    val INCONEL_718 = Enum03.INCONEL_718
    @JvmField
    val NITINOL_60 = Enum03.NITINOL_60

    enum class Enum01(private val serializedName: String,
                      private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
    {
        MARAGING_STEEL_250("maraging_steel_250"),
        INCONEL_625("inconel_625"),
        BLUE_STEEL("blue_steel"),
        STABALLOY("staballoy"),
        TALONITE("talonite"),
        IRIDIUM("iridium"),
        ZERON_100("zeron_100"),
        WATERTIGHT_STEEL("watertight_steel"),
        STELLITE("stellite"),
        TUMBAGA("tumbaga"),
        EGLIN_STEEL("eglin_steel"),
        POTIN("potin"),
        GRISIUM("grisium"),
        BABBIT_ALLOY("babbit_alloy"),
        SILICON_CARBIDE("silicon_carbide"),
        RED_STEEL("red_steel");

        override val state: IBlockState
            get() = GTLiteMetaBlocks.METAL_CASING_01.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.METAL_CASING_01.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "wrench"
    }

    enum class Enum02(private val serializedName: String,
                      private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
    {

        HSLA_STEEL("hsla_steel"),
        KOVAR("kovar"),
        BLACK_STEEL("black_steel"),
        INCOLOY_MA813("incoloy_ma813"),
        MONEL_500("monel_500"),
        INCOLOY_MA956("incoloy_ma956"),
        ZIRCONIUM_CARBIDE("zirconium_carbide"),
        HASTELLOY_C276("hastelloy_c276"),
        HASTELLOY_X("hastelloy_x"),
        POLYBENZIMIDAZOLE("polybenzimidazole"),
        ALUMINIUM_BRONZE("aluminium_bronze"),
        HASTELLOY_N("hastelloy_n"),
        RENE_N5("rene_n5"),
        BISMUTH_BRONZE("bismuth_bronze"),
        BRASS("brass"),
        TITANIUM_TUNGSTEN_CARBIDE("titanium_tungsten_carbide");

        override val state: IBlockState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.METAL_CASING_02.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "wrench"

    }

    enum class Enum03(private val serializedName: String,
                      private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
    {

        COBALT_BRASS("cobalt_brass"),
        TRINAQUADALLOY("trinaquadalloy"),
        OSMIRIDIUM("osmiridium"),
        NEUTRONIUM("neutronium"),
        NAQUADAH_ALLOY("naquadah_alloy"),
        QUANTUM_ALLOY("quantum_alloy"),
        INCONEL_718("inconel_718"),
        NITINOL_60("nitinol_60");

        override val state: IBlockState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.METAL_CASING_03.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "wrench"

    }

}