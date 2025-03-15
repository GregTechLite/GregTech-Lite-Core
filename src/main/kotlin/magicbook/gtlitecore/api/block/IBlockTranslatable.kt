package magicbook.gtlitecore.api.block

import net.minecraft.block.state.IBlockState

/**
 * Lazy translation getter for blocks which has many meta variants.
 */
interface IBlockTranslatable
{

    fun getTranslation(blockState: IBlockState): String

}