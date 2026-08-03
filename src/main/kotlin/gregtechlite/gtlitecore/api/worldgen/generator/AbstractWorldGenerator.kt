package gregtechlite.gtlitecore.api.worldgen.generator

import gregtech.api.util.random.XoShiRo256PlusPlusRandom
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.worldgen.CustomWorldGenerator
import gregtechlite.gtlitecore.api.worldgen.condition.GenerateCondition
import gregtechlite.gtlitecore.common.worldgen.generator.WorldGeneratorRegistry
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.gen.NoiseGeneratorSimplex
import java.util.*

abstract class AbstractWorldGenerator(private val seed: Int) : CustomWorldGenerator
{
    open val conditions: MutableList<GenerateCondition> = arrayListOf()

    private lateinit var generatorSimplex: NoiseGeneratorSimplex

    open val perlinScale: Double = 0.04

    private var chunkCounter: Int = 0
    private var placedChunkCounter: Int = 0

    open var innerGenerator: CustomWorldGeneratorImpl? = null
        protected set

    init
    {
        WorldGeneratorRegistry.add(this)
    }

    abstract override fun generate(worldIn: World?,
                                   blockPos: BlockPos.MutableBlockPos?,
                                   rand: Random?,
                                   notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean

    open fun setWorld(worldIn: World)
    {
        generatorSimplex = NoiseGeneratorSimplex(XoShiRo256PlusPlusRandom(worldIn.seed + seed))
    }

    open fun getRandomStrength(chunkX: Int, chunkZ: Int): Double
        = generatorSimplex.getValue(chunkX * perlinScale, chunkZ * perlinScale)

    open fun updateForPlaced(isPlacedSuccess: Boolean)
    {
        chunkCounter++
        if (isPlacedSuccess) placedChunkCounter++

        val chunkPercent = (placedChunkCounter.toDouble() / (chunkCounter / 100))
        if (chunkCounter % 1000 == 0)
        {
            LOGGER.info("The World Generator '$this' has been placed successfully in chunk '$chunkPercent' percent of"
                                + "time out of '$chunkCounter' chunks checked")
        }
    }
}