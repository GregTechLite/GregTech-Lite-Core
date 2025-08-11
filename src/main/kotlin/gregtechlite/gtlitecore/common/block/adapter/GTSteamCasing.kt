package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockSteamCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockSteamCasing
 */
enum class GTSteamCasing : BlockVariant
{

    BRONZE_HULL,
    BRONZE_BRICKS_HULL,
    STEEL_HULL,
    STEEL_BRICKS_HULL,
    PUMP_DECK,
    WOOD_WALL;

    override val state: IBlockState
        get() = MetaBlocks.STEAM_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.STEAM_CASING.getItemVariant(
            BlockSteamCasing.SteamCasingType.valueOf(this.name), count)
    }

}