package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

enum class WoodFenceGateBlock : BlockVariant
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
            BANANA -> GTLiteMetaBlocks.BANANA_WOOD_FENCE_GATE.defaultState
            ORANGE -> GTLiteMetaBlocks.ORANGE_WOOD_FENCE_GATE.defaultState
            MANGO -> GTLiteMetaBlocks.MANGO_WOOD_FENCE_GATE.defaultState
            APRICOT -> GTLiteMetaBlocks.APRICOT_WOOD_FENCE_GATE.defaultState
            LEMON -> GTLiteMetaBlocks.LEMON_WOOD_FENCE_GATE.defaultState
            LIME -> GTLiteMetaBlocks.LIME_WOOD_FENCE_GATE.defaultState
            OLIVE -> GTLiteMetaBlocks.OLIVE_WOOD_FENCE_GATE.defaultState
            NUTMEG -> GTLiteMetaBlocks.NUTMEG_WOOD_FENCE_GATE.defaultState
            COCONUT -> GTLiteMetaBlocks.COCONUT_WOOD_FENCE_GATE.defaultState
            RAINBOW -> GTLiteMetaBlocks.RAINBOW_WOOD_FENCE_GATE.defaultState
        }

    override fun getStack(count: Int): ItemStack
    {
        return GameRegistry.makeItemStack("$MOD_ID:gtlite_wood_fence_gate_${this.name.lowercase()}", 0, count, null)
    }

}