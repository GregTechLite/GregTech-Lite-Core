package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class BoilerCasing(private val serializedName: String,
                        private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
{

    POLYBENZIMIDAZOLE("polybenzimidazole");

    override val state: IBlockState
        get() = GTLiteMetaBlocks.BOILER_CASING_01.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.BOILER_CASING_01.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}