package gregtechlite.gtlitecore.api.block

import net.minecraft.block.state.IBlockState

interface TranslatableBlock
{
    fun getTranslation(blockState: IBlockState): String
}