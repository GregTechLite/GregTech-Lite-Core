package gregtechlite.gtlitecore.api.pattern

import gregtech.api.util.BlockInfo

interface BlockPatternExtension
{

    val previewPages: Int

    fun getPreview(repetition: IntArray, candidateIndex: Int): Array<Array<Array<BlockInfo>>>

}