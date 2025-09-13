package gregtechlite.gtlitecore.api.worldgen.condition

import net.minecraft.world.biome.Biome

class BiomeCondition(private val biomes: Array<Biome>,
                     maxAmount: Int,
                     private val perlinCutoff: Double) : GenerateCondition(maxAmount)
{

    constructor(biome: Biome, maxAmount: Int, perlinCutoff: Double) : this(arrayOf(biome), maxAmount, perlinCutoff)

    override fun canGenerateIn(biome: Biome): Boolean = this.biomes.any { it === biome }

    override fun getPerlinCutoff(biome: Biome): Double = this.perlinCutoff

}