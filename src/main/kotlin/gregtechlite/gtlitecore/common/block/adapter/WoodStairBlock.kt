package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
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
            BANANA -> GTLiteMetaBlocks.BANANA_WOOD_STAIR.defaultState
            ORANGE -> GTLiteMetaBlocks.ORANGE_WOOD_STAIR.defaultState
            MANGO -> GTLiteMetaBlocks.MANGO_WOOD_STAIR.defaultState
            APRICOT -> GTLiteMetaBlocks.APRICOT_WOOD_STAIR.defaultState
            LEMON -> GTLiteMetaBlocks.LEMON_WOOD_STAIR.defaultState
            LIME -> GTLiteMetaBlocks.LIME_WOOD_STAIR.defaultState
            OLIVE -> GTLiteMetaBlocks.OLIVE_WOOD_STAIR.defaultState
            NUTMEG -> GTLiteMetaBlocks.NUTMEG_WOOD_STAIR.defaultState
            COCONUT -> GTLiteMetaBlocks.COCONUT_WOOD_STAIR.defaultState
            RAINBOW -> GTLiteMetaBlocks.RAINBOW_WOOD_STAIR.defaultState
        }

    override fun getStack(count: Int): ItemStack
    {
        return GameRegistry.makeItemStack("$MOD_ID:gtlite_wood_stair_${this.name.lowercase()}", 0, count, null)
    }

}