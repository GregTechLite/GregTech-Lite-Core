package gregtechlite.gtlitecore.common.block.variant.component

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class FieldGenCasing(private val serializedName: String,
                          private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable,
                                                               IStateHarvestLevel, StateTier
{

    LV("lv"),
    MV("mv"),
    HV("hv"),
    EV("ev"),
    IV("iv"),
    LuV("luv"),
    ZPM("zpm"),
    UV("uv"),
    UHV("uhv"),
    UEV("uev"),
    UIV("uiv"),
    UXV("uxv"),
    OpV("opv"),
    MAX("max");

    override val state: IBlockState
        get() = GTLiteMetaBlocks.FIELD_GEN_CASING.getState(this)

    override val tier: Int
        get() = ordinal + 1

    override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}