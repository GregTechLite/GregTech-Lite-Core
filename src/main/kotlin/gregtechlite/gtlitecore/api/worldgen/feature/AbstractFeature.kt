package gregtechlite.gtlitecore.api.worldgen.feature

import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.worldgen.AbstractWorldGenerator
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.gen.NoiseGeneratorSimplex
import java.util.*

abstract class AbstractFeature(private val seed: Int)
{

    val featureConditions = mutableListOf<FeatureCondition>()

    // Instance for a worldgen feature.
    protected var featureGrowInstance: AbstractWorldGenerator? = null

    var worldGenInstance: AbstractWorldGenerator? = null
        protected set

    private var generatorSimplex: NoiseGeneratorSimplex? = null
    private var totalChunksChecked = 0
    private var totalChunksPlaced = 0
    open val perlinScale: Double
        get() = 0.04

    companion object
    {
        val features = ArrayList<AbstractFeature?>()
    }

    init
    {
        features.add(this)
    }

    abstract fun generate(worldIn: World?,
                          blockPos: BlockPos.MutableBlockPos?,
                          random: Random?,
                          notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean

    fun setWorld(worldIn: World)
    {
        this.generatorSimplex = NoiseGeneratorSimplex(Random(worldIn.seed + this.seed))
    }

    fun getRandomStrength(chunkX: Int, chunkZ: Int): Double
    {
        return this.generatorSimplex!!.getValue(chunkX * this.perlinScale,
                                                chunkZ * this.perlinScale)
    }

    fun updatePlacePercentage(disSucceed: Boolean)
    {
        this.totalChunksChecked++
        val totalChunks = (this.totalChunksPlaced.toDouble() / (this.totalChunksChecked / 100))
        if (disSucceed) this.totalChunksPlaced++

        if (this.totalChunksChecked % 1000 == 0)
        {
            GTLiteLog.logger.info("Feature {} has been placed successfully"
                + " in chunks {} percent of the time out of {} chunks checked",
                this, totalChunks, this.totalChunksChecked)
        }
    }

    open fun addCondition(condition: FeatureCondition): AbstractFeature
    {
        this.featureConditions.add(condition)
        return this
    }

}
