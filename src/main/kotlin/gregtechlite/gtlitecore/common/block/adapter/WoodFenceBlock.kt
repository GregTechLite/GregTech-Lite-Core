package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

enum class WoodFenceBlock : BlockVariant
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
            BANANA -> GTLiteBlocks.BANANA_WOOD_FENCE.defaultState
            ORANGE -> GTLiteBlocks.ORANGE_WOOD_FENCE.defaultState
            MANGO -> GTLiteBlocks.MANGO_WOOD_FENCE.defaultState
            APRICOT -> GTLiteBlocks.APRICOT_WOOD_FENCE.defaultState
            LEMON -> GTLiteBlocks.LEMON_WOOD_FENCE.defaultState
            LIME -> GTLiteBlocks.LIME_WOOD_FENCE.defaultState
            OLIVE -> GTLiteBlocks.OLIVE_WOOD_FENCE.defaultState
            NUTMEG -> GTLiteBlocks.NUTMEG_WOOD_FENCE.defaultState
            COCONUT -> GTLiteBlocks.COCONUT_WOOD_FENCE.defaultState
            RAINBOW -> GTLiteBlocks.RAINBOW_WOOD_FENCE.defaultState
        }

    override fun getStack(count: Int): ItemStack
    {
        return GameRegistry.makeItemStack("$MOD_ID:wood_fence_${this.name.lowercase()}", 0, count, null)
    }

}