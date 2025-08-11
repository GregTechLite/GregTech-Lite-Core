package gregtechlite.gtlitecore.common.block.variant.aerospace

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class AccelerationTrack(private val serializedName: String,
                             private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable,
                                                                  IStateHarvestLevel, StateTier
{

    MK1("mk1"),
    MK2("mk2"),
    MK3("mk3"),
    MK4("mk4"),
    MK5("mk5");

    override val state: IBlockState
        get() = GTLiteMetaBlocks.ACCELERATION_TRACK.getState(this)

    override val tier: Int
        get() = ordinal + 1

    override fun getStack(count: Int): ItemStack = GTLiteMetaBlocks.ACCELERATION_TRACK.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}