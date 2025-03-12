package magicbook.gtlitecore.loader.recipe

import magicbook.gtlitecore.loader.recipe.machine.BenderRecipes
import magicbook.gtlitecore.loader.recipe.machine.CentrifugeRecipes
import magicbook.gtlitecore.loader.recipe.machine.FormingPressRecipes
import magicbook.gtlitecore.loader.recipe.machine.LoomRecipes
import magicbook.gtlitecore.loader.recipe.machine.PolisherRecipes
import magicbook.gtlitecore.loader.recipe.producer.DisposableToolRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.StoneVariantRecipeProducer

class RecipeManager
{

    companion object
    {

        fun init()
        {
            CraftingRecipeLoader.init()
            MachineRecipeLoader.init()

            // Loading Recipe Producers.
            StoneVariantRecipeProducer.init()
            DisposableToolRecipeProducer.init()

            // Original GregTech Machine Recipes.
            BenderRecipes.init()
            CentrifugeRecipes.init()
            FormingPressRecipes.init()
            // Additional Machine Recipes.
            LoomRecipes.init()
            PolisherRecipes.init()
        }

    }

}