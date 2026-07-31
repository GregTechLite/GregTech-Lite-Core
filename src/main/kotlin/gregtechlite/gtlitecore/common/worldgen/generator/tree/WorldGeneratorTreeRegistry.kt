package gregtechlite.gtlitecore.common.worldgen.generator.tree

/**
 * Will register all generators by corresponding block class automatically.
 */
private val treeGenerators: MutableList<WorldGeneratorTreeBase> = mutableListOf()

object WorldGeneratorTreeRegistry : Iterable<WorldGeneratorTreeBase> by treeGenerators
{
    @JvmStatic
    val size: Int
        get() = treeGenerators.size

    @JvmStatic
    operator fun get(index: Int): WorldGeneratorTreeBase = treeGenerators[index]

    @JvmStatic
    fun add(treeGenerator: WorldGeneratorTreeBase)
    {
        treeGenerators.add(treeGenerator)
    }

    @JvmStatic
    fun remove(treeGenerator: WorldGeneratorTreeBase)
    {
        treeGenerators.remove(treeGenerator)
    }

    @JvmStatic
    fun clear()
    {
        treeGenerators.clear()
    }
}