package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
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
            BANANA -> GTLiteMetaBlocks.BANANA_WOOD_FENCE.defaultState
            ORANGE -> GTLiteMetaBlocks.ORANGE_WOOD_FENCE.defaultState
            MANGO -> GTLiteMetaBlocks.MANGO_WOOD_FENCE.defaultState
            APRICOT -> GTLiteMetaBlocks.APRICOT_WOOD_FENCE.defaultState
            LEMON -> GTLiteMetaBlocks.LEMON_WOOD_FENCE.defaultState
            LIME -> GTLiteMetaBlocks.LIME_WOOD_FENCE.defaultState
            OLIVE -> GTLiteMetaBlocks.OLIVE_WOOD_FENCE.defaultState
            NUTMEG -> GTLiteMetaBlocks.NUTMEG_WOOD_FENCE.defaultState
            COCONUT -> GTLiteMetaBlocks.COCONUT_WOOD_FENCE.defaultState
            RAINBOW -> GTLiteMetaBlocks.RAINBOW_WOOD_FENCE.defaultState
        }

    override fun getStack(count: Int): ItemStack
    {
        return GameRegistry.makeItemStack("$MOD_ID:gtlite_wood_fence_${this.name.lowercase()}", 0, count, null)
    }

}