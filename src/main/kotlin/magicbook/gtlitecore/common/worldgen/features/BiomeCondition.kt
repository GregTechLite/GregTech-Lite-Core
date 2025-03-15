package magicbook.gtlitecore.common.worldgen.features

import magicbook.gtlitecore.api.worldgen.feature.FeatureCondition
import net.minecraft.world.biome.Biome

class BiomeCondition : FeatureCondition
{

    private val biomes: Array<Biome>
    private val perlinCutoff: Double

    constructor(biomes: Array<Biome>, maxAmount: Int, perlinCutoff: Double) : super(maxAmount)
    {
        this.biomes = biomes
        this.perlinCutoff = perlinCutoff
    }

    constructor(biome: Biome, maxAmount: Int, perlinCutoff: Double) : this(arrayOf(biome), maxAmount, perlinCutoff)

    override fun isSatisfied(biome: Biome): Boolean = biomes.any { it === biome }

    override fun getPerlinCutoff(biome: Biome): Double = perlinCutoff

}