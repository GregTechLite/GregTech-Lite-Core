package gregtechlite.gtlitecore.api.worldgen.condition

import net.minecraft.world.biome.Biome

abstract class GenerateCondition(var maxAmount: Int)
{

    abstract fun canGenerateIn(biome: Biome): Boolean

    abstract fun getPerlinCutoff(biome: Biome): Double

}