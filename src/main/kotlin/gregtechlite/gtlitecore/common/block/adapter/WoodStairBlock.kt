package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

@Suppress("Deprecation")
enum class WoodStairBlock : BlockVariant
{

    BANANA,
    ORANGE,
    MANGO,
    APRICOT,
    LEMON,
    LIME,
    OLIVE,
    NUTMEG,
    COCONUT,
    RAINBOW;

    override val state: IBlockState
        get() = when (this)
        {
            BANANA -> GTLiteBlocks.BANANA_WOOD_STAIR.defaultState
            ORANGE -> GTLiteBlocks.ORANGE_WOOD_STAIR.defaultState
            MANGO -> GTLiteBlocks.MANGO_WOOD_STAIR.defaultState
            APRICOT -> GTLiteBlocks.APRICOT_WOOD_STAIR.defaultState
            LEMON -> GTLiteBlocks.LEMON_WOOD_STAIR.defaultState
            LIME -> GTLiteBlocks.LIME_WOOD_STAIR.defaultState
            OLIVE -> GTLiteBlocks.OLIVE_WOOD_STAIR.defaultState
            NUTMEG -> GTLiteBlocks.NUTMEG_WOOD_STAIR.defaultState
            COCONUT -> GTLiteBlocks.COCONUT_WOOD_STAIR.defaultState
            RAINBOW -> GTLiteBlocks.RAINBOW_WOOD_STAIR.defaultState
        }

    override fun getStack(count: Int): ItemStack
    {
        return GameRegistry.makeItemStack("$MOD_ID:wood_stair_${this.name.lowercase()}", 0, count, null)
    }

}