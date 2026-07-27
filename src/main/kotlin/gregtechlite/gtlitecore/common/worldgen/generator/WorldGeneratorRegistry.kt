package gregtechlite.gtlitecore.common.worldgen.generator

import gregtechlite.gtlitecore.api.worldgen.CustomWorldGenerator

/**
 * Will register all generators by [WorldGeneratorManager] automatically.
 */
private val generators: MutableList<CustomWorldGenerator> = mutableListOf()

object WorldGeneratorRegistry : Iterable<CustomWorldGenerator> by generators
{
    @JvmStatic
    val size: Int
        get() = generators.size

    @JvmStatic
    operator fun get(index: Int): CustomWorldGenerator = generators[index]

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