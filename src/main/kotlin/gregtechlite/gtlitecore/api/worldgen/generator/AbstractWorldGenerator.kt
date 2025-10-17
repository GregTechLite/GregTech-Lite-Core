package gregtechlite.gtlitecore.api.worldgen.generator

import gregtech.api.util.random.XoShiRo256PlusPlusRandom
import gregtechlite.gtlitecore.api.GTLiteLog
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
    private var placedChunkCounter = 0

    open var innerGenerator: CustomWorldGeneratorImpl? = null
        protected set

    init
    {
        // Automatically put all world generators to the collections, will only call and set it in registries.
        WorldGeneratorRegistry.addGenerator(this)
    }

    abstract override fun generate(worldIn: World?,
                                   blockPos: BlockPos.MutableBlockPos?,
                                   rand: Random?,
                                   notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean

    open fun setWorld(worldIn: World)
    {
        this.generatorSimplex = NoiseGeneratorSimplex(XoShiRo256PlusPlusRandom(worldIn.seed + this.seed))
    }

    open fun getRandomStrength(chunkX: Int, chunkZ: Int): Double
    {
        return this.generatorSimplex.getValue(chunkX * this.perlinScale, chunkZ * this.perlinScale)
    }

    open fun updateForPlaced(isPlacedSuccess: Boolean)
    {
        this.chunkCounter++
        if (isPlacedSuccess) this.placedChunkCounter++

        val chunkPercent = (this.placedChunkCounter.toDouble() / (this.chunkCounter / 100))

        if (this.chunkCounter % 1000 == 0)
        {
            GTLiteLog.logger.info("The World Generator '$this' has been placed successfully in chunk '$chunkPercent'" +
                                  " percent of time out of '$chunkCounter' chunks checked")
        }

    }

}