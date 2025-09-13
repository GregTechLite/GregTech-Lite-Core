package gregtechlite.gtlitecore.common.worldgen.generator.tree

object WorldGeneratorTreeRegistry
{

    val generators: MutableList<WorldGeneratorTreeBase> = arrayListOf()

    fun addGenerator(treeGenerator: WorldGeneratorTreeBase)
    {
        this.generators.add(treeGenerator)
    }

    fun addGenerators(treeGenerators: Array<WorldGeneratorTreeBase>)
    {
        this.generators.addAll(treeGenerators)
    }

    fun removeGenerator(treeGenerator: WorldGeneratorTreeBase)
    {
        this.generators.remove(treeGenerator)
    }

    fun removeGenerators(treeGenerators: Array<WorldGeneratorTreeBase>)
    {
        this.generators.removeAll(treeGenerators)
    }

    fun clear()
    {
        this.generators.clear()
    }

}