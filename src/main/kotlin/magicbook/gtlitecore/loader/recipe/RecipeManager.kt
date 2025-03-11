package magicbook.gtlitecore.loader.recipe

import magicbook.gtlitecore.loader.recipe.machine.PolisherRecipes
import magicbook.gtlitecore.loader.recipe.producer.StoneVariantRecipeProducer

class RecipeManager
{

    companion object
    {

        fun init()
        {
            CraftingRecipeLoader.init()
            MachineRecipeLoader.init()
            StoneVariantRecipeProducer.init()

            PolisherRecipes.init()
        }

    }

}