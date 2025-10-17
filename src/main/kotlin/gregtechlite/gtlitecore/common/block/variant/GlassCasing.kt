package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.ZPM
import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

object GlassCasing
{

    @JvmField
    val BOROSILICATE = Enum01.BOROSILICATE
    @JvmField
    val TITANIUM_BOROSILICATE = Enum01.TITANIUM_BOROSILICATE
    @JvmField
    val TUNGSTEN_STEEL_BOROSILICATE = Enum01.TUNGSTEN_STEEL_BOROSILICATE
    @JvmField
    val RHODIUM_PLATED_PALLADIUM_BOROSILICATE = Enum01.RHODIUM_PLATED_PALLADIUM_BOROSILICATE
    @JvmField
    val OSMIRIDIUM_BOROSILICATE = Enum01.OSMIRIDIUM_BOROSILICATE
    @JvmField
    val TRITANIUM_BOROSILICATE = Enum01.TRITANIUM_BOROSILICATE
    @JvmField
    val NEUTRONIUM_BOROSILICATE = Enum01.NEUTRONIUM_BOROSILICATE
    @JvmField
    val COSMIC_NEUTRONIUM_BOROSILICATE = Enum01.COSMIC_NEUTRONIUM_BOROSILICATE
    @JvmField
    val INFINITY_BOROSILICATE = Enum01.INFINITY_BOROSILICATE
    @JvmField
    val TRANSCENDENT_METAL_BOROSILICATE = Enum01.TRANSCENDENT_METAL_BOROSILICATE

    @JvmField
    val PMMA = Enum02.PMMA
    @JvmField
    val BPA_POLYCARBONATE = Enum02.BPA_POLYCARBONATE
    @JvmField
    val CBDO_POLYCARBONATE = Enum02.CBDO_POLYCARBONATE
    @JvmField
    val ZBLAN = Enum02.ZBLAN
    @JvmField
    val ERBIUM_ZBLAN = Enum02.ERBIUM_ZBLAN
    @JvmField
    val PRASEODYMIUM_ZBLAN = Enum02.PRASEODYMIUM_ZBLAN
    @JvmField
    val GST = Enum02.GST
    @JvmField
    val THORIUM_YTTRIUM = Enum02.THORIUM_YTTRIUM
    @JvmField
    val SILICON_CARBIDE = Enum02.SILICON_CARBIDE
    @JvmField
    val WOODS = Enum02.WOODS
    @JvmField
    val LEAD_SILICON = Enum02.LEAD_SILICON
    @JvmField
    val QUANTUM = Enum02.QUANTUM
    @JvmField
    val FORCE_FIELD = Enum02.FORCE_FIELD
    @JvmField
    val ANTIMATTER_CONTAINMENT = Enum02.ANTIMATTER_CONTAINMENT
    @JvmField
    val GREENHOUSE = Enum02.GREENHOUSE
    @JvmField
    val CHROMATIC = Enum02.CHROMATIC

    @JvmField
    val NANO_SHIELDING_FRAME = Enum03.NANO_SHIELDING_FRAME

    enum class Enum01(private val serializedName: String,
                      override val tier: Int,
                      private val harvestLevel: Int = 1) : BlockVariant, IStringSerializable, IStateHarvestLevel, StateTier
    {

        BOROSILICATE("borosilicate", HV),
        TITANIUM_BOROSILICATE("titanium_borosilicate", EV),
        TUNGSTEN_STEEL_BOROSILICATE("tungsten_steel_borosilicate", IV),
        RHODIUM_PLATED_PALLADIUM_BOROSILICATE("rhodium_plated_palladium_borosilicate", LuV),
        OSMIRIDIUM_BOROSILICATE("osmiridium_borosilicate", ZPM),
        TRITANIUM_BOROSILICATE("tritanium_borosilicate", UV),
        NEUTRONIUM_BOROSILICATE("neutronium_borosilicate", UHV),
        COSMIC_NEUTRONIUM_BOROSILICATE("cosmic_neutronium_borosilicate", UEV),
        INFINITY_BOROSILICATE("infinity_borosilicate", UIV),
        TRANSCENDENT_METAL_BOROSILICATE("transcendent_metal_borosilicate", UXV); // TODO OpV-MAX

        override val state: IBlockState
            get() = GTLiteBlocks.TRANSPARENT_CASING_01.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteBlocks.TRANSPARENT_CASING_01.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "pickaxe"

    }

    enum class Enum02(private val serializedName: String,
                      override val tier: Int,
                      private val harvestLevel: Int = 1) : BlockVariant, IStringSerializable, IStateHarvestLevel, StateTier
    {

        PMMA("pmma", UHV),
        BPA_POLYCARBONATE("bpa_polycarbonate", ZPM),
        CBDO_POLYCARBONATE("cbdo_polycarbonate", UEV),
        ZBLAN("zblan", UV),
        ERBIUM_ZBLAN("erbium_zblan", UHV),
        PRASEODYMIUM_ZBLAN("praseodymium_zblan", UHV),
        GST("gst", UV),
        THORIUM_YTTRIUM("thorium_yttrium", IV),
        SILICON_CARBIDE("silicon_carbide", EV),
        WOODS("woods", LuV),
        LEAD_SILICON("lead_silicon", EV),
        QUANTUM("quantum", UHV),
        FORCE_FIELD("force_field", UEV),
        ANTIMATTER_CONTAINMENT("antimatter_containment", UIV),
        GREENHOUSE("greenhouse", HV),
        CHROMATIC("chromatic", UEV);

        override val state: IBlockState
            get() = GTLiteBlocks.TRANSPARENT_CASING_02.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteBlocks.TRANSPARENT_CASING_02.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "pickaxe"

    }

    enum class Enum03(private val serializedName: String,
                      override val tier: Int,
                      private val harvestLevel: Int = 1) : BlockVariant, IStringSerializable, IStateHarvestLevel, StateTier
    {

        NANO_SHIELDING_FRAME("nano_shielding_frame", UXV);

        override val state: IBlockState
            get() = GTLiteBlocks.TRANSPARENT_CASING_03.getState(this)

        override fun getStack(count: Int): ItemStack = GTLiteBlocks.TRANSPARENT_CASING_03.getItemVariant(this, count)

        override fun getName(): String = serializedName

        override fun getHarvestLevel(state: IBlockState) = harvestLevel

        override fun getHarvestTool(state: IBlockState) = "pickaxe"

    }

}