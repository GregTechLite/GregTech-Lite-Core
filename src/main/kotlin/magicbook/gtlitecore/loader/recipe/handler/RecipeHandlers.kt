package magicbook.gtlitecore.loader.recipe.handler

class RecipeHandlers
{

    companion object
    {

        fun init()
        {
            MaterialRecipeHandler.init()
            BouleRecipeHandler.init()
            PartsRecipeHandler.init()
            OreRecipeHandler.init()
            ToolRecipeHandler.init()
            WireCombinationHandler.init()
            WireRecipeHandler.init()
            NuclearFuelRecipeHandler.init()
        }

    }

}