package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
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
            BANANA -> GTLiteBlocks.BANANA_WOOD_FENCE_GATE.defaultState
            ORANGE -> GTLiteBlocks.ORANGE_WOOD_FENCE_GATE.defaultState
            MANGO -> GTLiteBlocks.MANGO_WOOD_FENCE_GATE.defaultState
            APRICOT -> GTLiteBlocks.APRICOT_WOOD_FENCE_GATE.defaultState
            LEMON -> GTLiteBlocks.LEMON_WOOD_FENCE_GATE.defaultState
            LIME -> GTLiteBlocks.LIME_WOOD_FENCE_GATE.defaultState
            OLIVE -> GTLiteBlocks.OLIVE_WOOD_FENCE_GATE.defaultState
            NUTMEG -> GTLiteBlocks.NUTMEG_WOOD_FENCE_GATE.defaultState
            COCONUT -> GTLiteBlocks.COCONUT_WOOD_FENCE_GATE.defaultState
            RAINBOW -> GTLiteBlocks.RAINBOW_WOOD_FENCE_GATE.defaultState
        }

    override fun getStack(count: Int): ItemStack
    {
        return GameRegistry.makeItemStack("$MOD_ID:wood_fence_gate_${this.name.lowercase()}", 0, count, null)
    }

}