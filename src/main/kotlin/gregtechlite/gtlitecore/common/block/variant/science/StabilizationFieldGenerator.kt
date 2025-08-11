package gregtechlite.gtlitecore.common.block.variant.science

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class StabilizationFieldGenerator(private val serializedName: String,
                                       private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
{

    CRUDE("crude"),
    PRIMITIVE("primitive"),
    STABLE("stable"),
    ADVANCED("advanced"),
    SUPERB("superb"),
    EXOTIC("exotic"),
    PERFECT("perfect"),
    TIPLER("tipler"),
    GALLIFREYAN("gallifreyan");

    override val state: IBlockState
        get() = GTLiteMetaBlocks.STABILIZATION_FIELD_GENERATOR.getState(this)

    override fun getStack(count: Int): ItemStack =
        GTLiteMetaBlocks.STABILIZATION_FIELD_GENERATOR.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}