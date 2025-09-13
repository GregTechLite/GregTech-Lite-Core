package gregtechlite.gtlitecore.api.worldgen.condition

import net.minecraft.world.biome.Biome
import kotlin.math.pow
import kotlin.math.sqrt

class ClimateCondition(maxAmount: Int,
                       private val temperature: Double,
                       private val rainfall: Double,
                       private val range: Double,
                       private val commonality: Double) : GenerateCondition(maxAmount)
{

    override fun canGenerateIn(biome: Biome): Boolean
    {
        val biomeTemperature = biome.defaultTemperature - this.temperature
        val biomeRainfall = biome.rainfall - this.rainfall
        return (this.range - sqrt(biomeTemperature.pow(2) + biomeRainfall.pow(2))) > 0
    }

    override fun getPerlinCutoff(biome: Biome): Double
    {
        val biomeTemperature = biome.defaultTemperature - this.temperature
        val biomeRainfall = biome.rainfall - this.rainfall
        return 1 - (this.range - sqrt(biomeTemperature.pow(2) + biomeRainfall.pow(2)) * this.commonality)
    }

}