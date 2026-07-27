package gregtechlite.gtlitecore.common.worldgen.generator

import gregtechlite.gtlitecore.api.worldgen.CustomWorldGenerator

/**
 * Will register all generators by [WorldGeneratorManager] automatically.
 */
private val generators: MutableList<CustomWorldGenerator> = mutableListOf()

object WorldGeneratorRegistry : Iterable<CustomWorldGenerator> by generators
{
    @JvmStatic
    fun add(generator: CustomWorldGenerator)
    {
        generators.add(generator)
    }

    @JvmStatic
    fun remove(generator: CustomWorldGenerator)
    {
        generators.remove(generator)
    }

    @JvmStatic
    fun clear()
    {
        generators.clear()
    }
}