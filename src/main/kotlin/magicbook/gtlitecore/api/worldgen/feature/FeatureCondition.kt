package magicbook.gtlitecore.api.worldgen.feature

import net.minecraft.world.biome.Biome

abstract class FeatureCondition(private var maxAmount: Int)
{

    // Check if [biome] is satisfied.
    abstract fun isSatisfied(biome: Biome): Boolean

    // Perlin cutoff (noise) of WorldGenerator.
    //  0.15 for 1/3
    //  0.85 for 1/100
    abstract fun getPerlinCutoff(biome: Biome): Double

    // Get [maxAmount] as feature count.
    fun getMaxFeatures(): Double = this.maxAmount.toDouble()

}