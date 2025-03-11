package magicbook.gtlitecore.loader.recipe.handler

class RecipeHandlers
{

    companion object
    {

        fun init()
        {
            MaterialRecipeHandler.init()
            PartsRecipeHandler.init()
            OreRecipeHandler.init()
            ToolRecipeHandler.init()
        }

    }

}