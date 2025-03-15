package magicbook.gtlitecore.common.worldgen.features

import magicbook.gtlitecore.api.worldgen.feature.FeatureCondition
import net.minecraft.world.biome.Biome
import kotlin.math.pow
import kotlin.math.sqrt

class TemperatureRainfallCondition : FeatureCondition
{

    private val optimalTemperature: Double
    private val optimalRain: Double
    private val range: Double
    private val commonality: Double

    constructor(maxAmount: Int, commonality: Double, optimalTemperature: Double, optimalRain: Double, range: Double) : super(maxAmount)
    {
        this.optimalTemperature = optimalTemperature
        this.optimalRain = optimalRain
        this.range = range
        this.commonality = commonality
    }

    override fun isSatisfied(biome: Biome): Boolean = getHabitation(biome) > 0

    override fun getPerlinCutoff(biome: Biome): Double = 1 - (getHabitation(biome) * this.commonality)

    private fun getHabitation(biome: Biome) = this.range - sqrt(
        (biome.defaultTemperature - optimalTemperature).pow(2)
                + (biome.rainfall - optimalRain).pow(2))

}