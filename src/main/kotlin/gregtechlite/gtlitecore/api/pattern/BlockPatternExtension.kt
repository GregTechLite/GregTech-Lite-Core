package gregtechlite.gtlitecore.api.pattern

import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.util.BlockInfo

interface BlockPatternExtension
{
    val previewPages: Int

    val blockMatches: Array<Array<Array<TraceabilityPredicate>>>

    val centerOffset: IntArray

    fun getPreview(repetition: IntArray, candidateIndex: Int): Array<Array<Array<BlockInfo>>>
}
