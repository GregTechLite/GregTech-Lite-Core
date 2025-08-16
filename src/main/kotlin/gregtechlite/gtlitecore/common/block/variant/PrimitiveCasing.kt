package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class PrimitiveCasing(private val serializedName: String,
                           private val harvestLevel: Int = 0) : BlockVariant, IStringSerializable, IStateHarvestLevel
{

    REINFORCED_TREATED_WOOD_WALL("reinforced_treated_wood_wall");

    override val state: IBlockState
        get() = GTLiteBlocks.PRIMITIVE_CASING.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.PRIMITIVE_CASING.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState): Int = harvestLevel

    override fun getHarvestTool(state: IBlockState?): String = "wrench"

}
