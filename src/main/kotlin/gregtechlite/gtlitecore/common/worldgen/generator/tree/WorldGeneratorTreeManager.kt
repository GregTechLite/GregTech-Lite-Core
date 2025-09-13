package gregtechlite.gtlitecore.common.worldgen.generator.tree

import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeBase

object WorldGeneratorTreeManager
{

    lateinit var BANANA_TREE: WorldGeneratorTreeBase
    lateinit var ORANGE_TREE: WorldGeneratorTreeBase
    lateinit var MANGO_TREE: WorldGeneratorTreeBase
    lateinit var APRICOT_TREE: WorldGeneratorTreeBase
    lateinit var LEMON_TREE: WorldGeneratorTreeBase
    lateinit var LIME_TREE: WorldGeneratorTreeBase
    lateinit var OLIVE_TREE: WorldGeneratorTreeBase
    lateinit var NUTMEG_TREE: WorldGeneratorTreeBase
    lateinit var COCONUT_TREE: WorldGeneratorTreeBase
    lateinit var RAINBOW_TREE: WorldGeneratorTreeBase

    internal fun init()
    {
        BANANA_TREE = WorldGeneratorTreeBanana()
        ORANGE_TREE = WorldGeneratorTreeOrange()
        MANGO_TREE = WorldGeneratorTreeMango()
        APRICOT_TREE = WorldGeneratorTreeApricot()
        LEMON_TREE = WorldGeneratorTreeLemon()
        LIME_TREE = WorldGeneratorTreeLime()
        OLIVE_TREE = WorldGeneratorTreeOlive()
        NUTMEG_TREE = WorldGeneratorTreeNutmeg()
        COCONUT_TREE = WorldGeneratorTreeCoconut()
        RAINBOW_TREE = WorldGeneratorTreeRainbow()
    }

}