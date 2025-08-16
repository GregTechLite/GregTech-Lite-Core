package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class ActiveUniqueCasing(private val serializedName: String,
                              private val harvestLevel: Int = 2) : BlockVariant, IStringSerializable, IStateHarvestLevel
{

    HEAT_VENT("heat_vent"),
    TEMPERATURE_CONTROLLER("temperature_controller");

    override val state: IBlockState
        get() = GTLiteBlocks.ACTIVE_UNIQUE_CASING_01.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.ACTIVE_UNIQUE_CASING_01.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "wrench"

}