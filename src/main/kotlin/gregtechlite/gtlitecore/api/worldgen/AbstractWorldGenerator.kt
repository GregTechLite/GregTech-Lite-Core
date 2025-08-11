package gregtechlite.gtlitecore.api.worldgen

import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.worldgen.feature.AbstractFeature
import gregtechlite.gtlitecore.api.worldgen.feature.FeatureCondition
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.gen.feature.WorldGenerator
import java.util.*
import kotlin.math.ceil

abstract class AbstractWorldGenerator protected constructor(notify: Boolean,
                                                            val feature: AbstractFeature) : WorldGenerator(notify)
{

    // This method is different with WorldGenerator#generate,
    // we used BlockPos.MutableBlockPos as blockPos in this method, and this
    // method will be overridden to the original method.
    abstract fun generateMutable(worldIn: World?,
                                 random: Random?,
                                 blockPos: BlockPos.MutableBlockPos?): Boolean

    // This method control the WorldGen enabled/disabled by config system,
    // when overridden the method in extends class, then the correspondenced
    // method in ConfigHolder or other Forge @Config API holder can control
    // enabled/disabled of the WorldGen of this extends class.
    abstract fun enabledGenerator(): Boolean

    override fun generate(worldIn: World,
                          random: Random,
                          blockPos: BlockPos): Boolean
    {
        return generateMutable(worldIn, random, BlockPos.MutableBlockPos(blockPos))
    }

    // This method is a safety transform of WorldGenerator#setBlockAndNotifyAdequately().
    fun setBlock(worldIn: World?,
                 blockPos: BlockPos?,
                 blockState: IBlockState?)
    {
        if (worldIn?.getBlockState(blockPos!!)?.getBlock()!!.canBeReplacedByLeaves(
                worldIn.getBlockState(blockPos), worldIn, blockPos))
        {
            setBlockAndNotifyAdequately(worldIn, blockPos, blockState!!)
        }
    }

    // Get maximum generate amount of blocks which will be generated in a chunk.
    fun getAmountInChunk(conditions: MutableList<FeatureCondition>,
                         chunkX: Int, chunkZ: Int,
                         worldIn: World, blockPos: BlockPos): Int
    {
        val biome = worldIn.getBiome(blockPos)
        val relevantCondition = conditions.first { it.isSatisfied(biome) }
        val treeStrength = this.feature.getRandomStrength(chunkX, chunkZ)

        if (ConfigHolder.misc.debug) // Debug mode.
        {
            if (relevantCondition.getPerlinCutoff(biome) < treeStrength)
            {
                this.feature.updatePlacePercentage(true)
                val perlinCutoff = relevantCondition.getPerlinCutoff(biome)
                val maxFeatures = relevantCondition.getMaxFeatures()
                return (ceil(maxFeatures - perlinCutoff * maxFeatures)).toInt()
            }
            else
            {
                feature.updatePlacePercentage(false)
            }
        }
        else // Common mode.
        {
            if (relevantCondition.getPerlinCutoff(biome) < treeStrength)
            {
                val perlinCutoff = relevantCondition.getPerlinCutoff(biome)
                val maxFeatures = relevantCondition.getMaxFeatures()
                return (ceil(maxFeatures - perlinCutoff * maxFeatures)).toInt()
            }
        }
        return 0
    }

    fun generateInChunk(worldIn: World,
                        random: Random,
                        chunkX: Int, chunkZ: Int): Boolean
    {
        if (!enabledGenerator())  // Disabled generator by #enabledGenerator().
            return false

        // Common processing of WorldGen in a chunk.
        val chunk = worldIn.getChunk(chunkX, chunkZ)
        val seaLevel = chunk.world.seaLevel
        val blockPos = BlockPos.MutableBlockPos(chunk.pos.getBlock(8, seaLevel, 8))

        var featureCount = getAmountInChunk(this.feature.featureConditions, chunkX, chunkZ, worldIn, blockPos)
        if (featureCount > 0)
        {
            var i = 0
            while (i < featureCount)
            {
                // Set up #blockPos for tree spawn offset by 8 to prevent cascading.
                blockPos.setPos(chunk.x * 16 + random.nextInt(16) + 8,
                                255,
                                chunk.z * 16 + random.nextInt(16) + 8)

                // Air checking and set Y-axis.
                while (worldIn.isAirBlock(blockPos) && blockPos.getY() != 0)
                {
                    blockPos.setY(blockPos.getY() - 1)
                }
                blockPos.setY(blockPos.getY() + 1)

                if (!generateMutable(chunk.world, random, blockPos)) featureCount -= 1
                i++
            }
            return true
        }
        return false
    }

}