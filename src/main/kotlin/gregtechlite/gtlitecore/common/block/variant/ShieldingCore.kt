package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class ShieldingCore(private val serializedName: String,
                         private val harvestLevel: Int = 3) : BlockVariant, IStringSerializable,
                                                              IStateHarvestLevel, StateTier
{

    NEUTRON("neutron"),
    COSMIC_FABRIC("cosmic_fabric"),
    INFINITY_INFUSED("infinity_infused"),
    SPACETIME_BENDING_CORE("spacetime_bending_core");

    override val state: IBlockState
        get() = GTLiteBlocks.SHIELDING_CORE.getState(this)

    override val tier: Int
        get() = ordinal + 1

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.SHIELDING_CORE.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}