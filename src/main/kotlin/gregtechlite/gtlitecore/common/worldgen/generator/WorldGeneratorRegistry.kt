package gregtechlite.gtlitecore.common.worldgen.generator

import gregtechlite.gtlitecore.api.worldgen.CustomWorldGenerator

object WorldGeneratorRegistry
{

    internal val generators: MutableList<CustomWorldGenerator> = mutableListOf()

    fun addGenerator(generator: CustomWorldGenerator)
    {
        this.generators.add(generator)
    }

    fun addGenerators(generators: Array<CustomWorldGenerator>)
    {
        this.generators.addAll(generators)
    }

    fun removeGenerator(generator: CustomWorldGenerator)
    {
        this.generators.remove(generator)
    }

    fun removeGenerators(generators: Array<CustomWorldGenerator>)
    {
        this.generators.removeAll(generators)
    }

    fun clear()
    {
        this.generators.clear()
    }

}