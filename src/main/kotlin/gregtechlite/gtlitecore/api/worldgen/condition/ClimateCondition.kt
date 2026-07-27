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
        val biomeTemperature = biome.defaultTemperature - temperature
        val biomeRainfall = biome.rainfall - rainfall
        return (range - sqrt(biomeTemperature.pow(2) + biomeRainfall.pow(2))) > 0
    }

    override fun getPerlinCutoff(biome: Biome): Double
    {
        val biomeTemperature = biome.defaultTemperature - temperature
        val biomeRainfall = biome.rainfall - rainfall
        return 1 - (range - sqrt(biomeTemperature.pow(2) + biomeRainfall.pow(2)) * commonality)
    }
}