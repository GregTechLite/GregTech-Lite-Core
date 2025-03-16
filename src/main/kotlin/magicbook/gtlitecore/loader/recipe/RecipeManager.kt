package magicbook.gtlitecore.loader.recipe

import magicbook.gtlitecore.loader.recipe.chain.MicaInsulatorChain
import magicbook.gtlitecore.loader.recipe.chain.OilCrackingChain
import magicbook.gtlitecore.loader.recipe.chain.OxidesChain
import magicbook.gtlitecore.loader.recipe.chain.PolymersChain
import magicbook.gtlitecore.loader.recipe.chain.RubbersChain
import magicbook.gtlitecore.loader.recipe.circuit.ElectronicCircuits
import magicbook.gtlitecore.loader.recipe.machine.AssemblerRecipes
import magicbook.gtlitecore.loader.recipe.machine.BenderRecipes
import magicbook.gtlitecore.loader.recipe.machine.CannerRecipes
import magicbook.gtlitecore.loader.recipe.machine.CentrifugeRecipes
import magicbook.gtlitecore.loader.recipe.machine.ExtruderRecipes
import magicbook.gtlitecore.loader.recipe.machine.FormingPressRecipes
import magicbook.gtlitecore.loader.recipe.machine.GreenhouseRecipes
import magicbook.gtlitecore.loader.recipe.machine.LoomRecipes
import magicbook.gtlitecore.loader.recipe.machine.MixerRecipes
import magicbook.gtlitecore.loader.recipe.machine.PolisherRecipes
import magicbook.gtlitecore.loader.recipe.oreprocessing.StrontiumProcessing
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
            WoodRecipeLoader.init()

            // Loading recipe producers.
            StoneVariantRecipeProducer.init()
            DisposableToolRecipeProducer.init()

            // Loading chains and ore processings.
            OilCrackingChain.init()
            RubbersChain.init()
            PolymersChain.init()
            MicaInsulatorChain.init()
            OxidesChain.init()

            StrontiumProcessing.init()

            // Circuit recipes.
            ElectronicCircuits.init()

            // Original GregTech machine recipes.
            AssemblerRecipes.init()
            BenderRecipes.init()
            CannerRecipes.init()
            CentrifugeRecipes.init()
            ExtruderRecipes.init()
            FormingPressRecipes.init()
            MixerRecipes.init()
            // Additional machine recipes.
            GreenhouseRecipes.init()
            LoomRecipes.init()
            PolisherRecipes.init()
        }

    }

}