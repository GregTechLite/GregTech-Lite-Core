package magicbook.gtlitecore.loader.recipe

class RecipeManager
{

    companion object
    {

        fun init()
        {
            CraftingRecipeLoader.init()
            MachineRecipeLoader.init()
        }

    }

}