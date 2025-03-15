package magicbook.gtlitecore.common.worldgen.trees

class WorldGenTrees
{

    companion object
    {

        lateinit var BANANA_TREE: AbstractTree

        lateinit var ORANGE_TREE: AbstractTree

        lateinit var MANGO_TREE: AbstractTree

        lateinit var APRICOT_TREE: AbstractTree

        lateinit var LEMON_TREE: AbstractTree

        lateinit var LIME_TREE: AbstractTree

        lateinit var OLIVE_TREE: AbstractTree

        lateinit var NUTMEG_TREE: AbstractTree

        lateinit var COCONUT_TREE: AbstractTree

        lateinit var RAINBOW_TREE: AbstractTree

        fun init()
        {

            BANANA_TREE = WorldGenBananaTree()
            ORANGE_TREE = WorldGenOrangeTree()
            MANGO_TREE = WorldGenMangoTree()
            APRICOT_TREE = WorldGenApricotTree()
            LEMON_TREE = WorldGenLemonTree()
            LIME_TREE = WorldGenLimeTree()
            OLIVE_TREE = WorldGenOliveTree()
            NUTMEG_TREE = WorldGenNutmegTree()
            COCONUT_TREE = WorldGenCoconutTree()
            RAINBOW_TREE = WorldGenRainbowTree()

        }

    }

}