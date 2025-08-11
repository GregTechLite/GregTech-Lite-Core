package gregtechlite.gtlitecore.api.pattern

import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import java.util.Stack

object BlockPatterns
{

    fun getMatchingShapes(blockPattern: BlockPattern): List<MultiblockShapeInfo>
    {
        val aisleRepetitions = blockPattern.aisleRepetitions
        val maxPages = (blockPattern as BlockPatternExtension).previewPages
        val pages = repetitionDFS(blockPattern, mutableListOf(), aisleRepetitions, Stack())
        if (pages.size < maxPages)
        {
            val repetition = IntArray(aisleRepetitions.size)
            for (i in repetition.indices)
            {
                repetition[i] = aisleRepetitions[i]!![1]
            }
            for (i in pages.size ..< maxPages)
            {
                pages.add(MultiblockShapeInfo(blockPattern.getPreview(repetition, i)))
            }
        }
        return pages
    }

    fun getMatchingShape(blockPattern: BlockPattern, index: Int): List<MultiblockShapeInfo>
    {
        val aisleRepetitions = blockPattern.aisleRepetitions
        return repetitionDFS(blockPattern, mutableListOf(), aisleRepetitions, Stack(), index)
    }

    private fun repetitionDFS(pattern: BlockPattern,
                              pages: MutableList<MultiblockShapeInfo>,
                              aisleRepetitions: Array<IntArray>,
                              repetitionStack: Stack<Int>,
                              index: Int): MutableList<MultiblockShapeInfo>
    {
        if (repetitionStack.size == aisleRepetitions.size)
        {
            val repetition = IntArray(repetitionStack.size)
            repetitionStack.forEachIndexed { index, i -> repetition[index] = i }
            pages.add(MultiblockShapeInfo((pattern as BlockPatternExtension).getPreview(repetition, index)))
        }
        else
        {
            for (i in aisleRepetitions[repetitionStack.size][0] .. aisleRepetitions[repetitionStack.size][1])
            {
                repetitionStack.push(i)
                repetitionDFS(pattern, pages, aisleRepetitions, repetitionStack, index)
                repetitionStack.pop()
            }
        }
        return pages
    }

    private fun repetitionDFS(pattern: BlockPattern,
                              pages: MutableList<MultiblockShapeInfo>,
                              aisleRepetitions: Array<IntArray>,
                              repetitionStack: Stack<Int>): MutableList<MultiblockShapeInfo>
    {
        if (repetitionStack.size == aisleRepetitions.size)
        {
            val repetition = IntArray(repetitionStack.size)
            repetitionStack.forEachIndexed { index, i -> repetition[index] = i }
            pages.add(MultiblockShapeInfo((pattern as BlockPatternExtension).getPreview(repetition, pages.size)))
        }
        else
        {
            for (i in aisleRepetitions[repetitionStack.size][0] .. aisleRepetitions[repetitionStack.size][1])
            {
                repetitionStack.push(i)
                repetitionDFS(pattern, pages, aisleRepetitions, repetitionStack)
                repetitionStack.pop()
            }
        }
        return pages
    }

}