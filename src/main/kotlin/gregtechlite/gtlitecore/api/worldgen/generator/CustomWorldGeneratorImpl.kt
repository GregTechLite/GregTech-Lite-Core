package gregtechlite.gtlitecore.api.worldgen.generator

import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.worldgen.condition.GenerateCondition
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.gen.feature.WorldGenerator
import java.util.Random
import kotlin.math.ceil

abstract class CustomWorldGeneratorImpl(isNotified: Boolean,
                                        val generator: AbstractWorldGenerator) : WorldGenerator(isNotified)
{

    abstract fun scatter(worldIn: World?,
                         blockPos: BlockPos.MutableBlockPos?,
                         rand: Random?): Boolean

    abstract fun configure(): Boolean

    override fun generate(worldIn: World, rand: Random, pos: BlockPos): Boolean
    {
        return scatter(worldIn, BlockPos.MutableBlockPos(pos), rand)
    }

    open fun setBlockAndUpdate(worldIn: World?,
                               blockPos: BlockPos?,
                               blockState: IBlockState?)
    {
        val blockWorldState = worldIn?.getBlockState(blockPos!!)
        if (blockWorldState!!.block.canBeReplacedByLeaves(blockWorldState, worldIn, blockPos))
            setBlockAndNotifyAdequately(worldIn, blockPos, blockState!!)
    }

    open fun getAmountInChunk(conditions: MutableList<GenerateCondition>,
                              chunkX: Int, chunkZ: Int,
                              worldIn: World,
                              blockPos: BlockPos): Int
    {
        val biome = worldIn.getBiome(blockPos)

        val relevantCondition = conditions.firstOrNull { it.canGenerateIn(biome) }
        if (relevantCondition == null) return 0

        val treeStrength = this.generator.getRandomStrength(chunkX, chunkZ)

        if (ConfigHolder.misc.debug)
        {
            if (relevantCondition.getPerlinCutoff(biome) < treeStrength)
            {
                this.generator.updateForPlaced(true)
                val perlinCutoff = relevantCondition.getPerlinCutoff(biome)
                val maxFeatures = relevantCondition.maxAmount
                return ceil(maxFeatures - perlinCutoff * maxFeatures).toInt()
            }
            else
            {
                this.generator.updateForPlaced(false)
            }
        }
        else
        {
            if (relevantCondition.getPerlinCutoff(biome) < treeStrength)
            {
                val perlinCutoff = relevantCondition.getPerlinCutoff(biome)
                val maxFeatures = relevantCondition.maxAmount
                return ceil(maxFeatures - perlinCutoff * maxFeatures).toInt()
            }
        }
        return 0
    }

    fun generateInChunk(worldIn: World,
                        rand: Random,
                        chunkX: Int, chunkZ: Int): Boolean
    {
        if (!configure()) return false

        val chunk = worldIn.getChunk(chunkX, chunkZ)
        val seaLevel = chunk.world.seaLevel
        val blockPos = BlockPos.MutableBlockPos(chunk.pos.getBlock(8, seaLevel, 8))

        var count = getAmountInChunk(this.generator.conditions, chunkX, chunkZ, worldIn, blockPos)
        if (count > 0)
        {
            var i = 0
            while (i < count)
            {
                // Setup blockPos for spawn offset by 8 to prevent cascading.
                blockPos.setPos(chunk.x * 16 + rand.nextInt(16) + 8,
                                255,
                                chunk.z * 16 + rand.nextInt(16) + 8)

                // Air checking and set Y-axis.
                while (worldIn.isAirBlock(blockPos) && blockPos.getY() != 0)
                    blockPos.setY(blockPos.getY() - 1)
                blockPos.setY(blockPos.getY() + 1)

                if (!scatter(chunk.world, blockPos, rand)) count -= 1
                i++
            }
            return true
        }
        return false
    }

}