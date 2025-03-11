package magicbook.gtlitecore.loader.recipe

import magicbook.gtlitecore.loader.recipe.machine.PolisherRecipes

class RecipeManager
{

    companion object
    {

        fun init()
        {
            CraftingRecipeLoader.init()
            MachineRecipeLoader.init()

            PolisherRecipes.init()
        }

    }

}