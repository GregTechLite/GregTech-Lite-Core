package gregtechlite.gtlitecore.common.block.variant.fusion

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class FusionCryostat(private val serializedName: String,
                          private val harvestLevel: Int = 4) : BlockVariant, IStringSerializable,
                                                               IStateHarvestLevel, StateTier
{

    MK1("mk1"),
    MK2("mk2"),
    MK3("mk3"),
    MK4("mk4"),
    MK5("mk5");

    override val state: IBlockState
        get() = GTLiteMetaBlocks.FUSION_CRYOSTAT.getState(this)

    override val tier: Int
        get() = ordinal + 1

    override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.FUSION_CRYOSTAT.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}


