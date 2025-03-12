package magicbook.gtlitecore.loader.recipe

import magicbook.gtlitecore.loader.recipe.chain.RubbersChain
import magicbook.gtlitecore.loader.recipe.machine.AssemblerRecipes
import magicbook.gtlitecore.loader.recipe.machine.BenderRecipes
import magicbook.gtlitecore.loader.recipe.machine.CentrifugeRecipes
import magicbook.gtlitecore.loader.recipe.machine.ExtruderRecipes
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

            // Loading recipe producers.
            StoneVariantRecipeProducer.init()
            DisposableToolRecipeProducer.init()

            // Loading chains and ore processings.
            RubbersChain.init()

            // Original GregTech machine recipes.
            AssemblerRecipes.init()
            BenderRecipes.init()
            CentrifugeRecipes.init()
            ExtruderRecipes.init()
            FormingPressRecipes.init()
            // Additional machine recipes.
            LoomRecipes.init()
            PolisherRecipes.init()
        }

    }

}