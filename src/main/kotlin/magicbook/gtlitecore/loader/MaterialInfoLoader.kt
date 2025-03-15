package magicbook.gtlitecore.loader

import magicbook.gtlitecore.loader.recipe.WoodRecipeLoader

class MaterialInfoLoader
{

    companion object
    {

        fun init()
        {
            WoodRecipeLoader.initUnificationInfos()
        }

    }

}