package gregtechlite.gtlitecore.common.block.variant.fusion

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class FusionCasing(private val serializedName: String,
                        private val harvestLevel: Int = 3) : BlockVariant, IStringSerializable, IStateHarvestLevel
{

    MK4("mk4"),
    MK5("mk5");

    override val state: IBlockState
        get() = GTLiteMetaBlocks.FUSION_CASING.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.FUSION_CASING.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}