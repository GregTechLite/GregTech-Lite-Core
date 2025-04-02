package magicbook.gtlitecore.loader.recipe

import magicbook.gtlitecore.loader.recipe.chain.AlloysChain
import magicbook.gtlitecore.loader.recipe.chain.AmmoniaChain
import magicbook.gtlitecore.loader.recipe.chain.DyesChain
import magicbook.gtlitecore.loader.recipe.chain.MicaInsulatorChain
import magicbook.gtlitecore.loader.recipe.chain.OilCrackingChain
import magicbook.gtlitecore.loader.recipe.chain.OxidesChain
import magicbook.gtlitecore.loader.recipe.chain.PolymersChain
import magicbook.gtlitecore.loader.recipe.chain.RubbersChain
import magicbook.gtlitecore.loader.recipe.circuit.ElectronicCircuits
import magicbook.gtlitecore.loader.recipe.circuit.IntegratedCircuits
import magicbook.gtlitecore.loader.recipe.circuit.NanoCircuits
import magicbook.gtlitecore.loader.recipe.circuit.ProcessorCircuits
import magicbook.gtlitecore.loader.recipe.circuit.QuantumCircuits
import magicbook.gtlitecore.loader.recipe.machine.AlloyBlastSmelterRecipes
import magicbook.gtlitecore.loader.recipe.machine.AssemblerRecipes
import magicbook.gtlitecore.loader.recipe.machine.BathCondenserRecipes
import magicbook.gtlitecore.loader.recipe.machine.BenderRecipes
import magicbook.gtlitecore.loader.recipe.machine.CannerRecipes
import magicbook.gtlitecore.loader.recipe.machine.CentrifugeRecipes
import magicbook.gtlitecore.loader.recipe.machine.ExtruderRecipes
import magicbook.gtlitecore.loader.recipe.machine.FormingPressRecipes
import magicbook.gtlitecore.loader.recipe.machine.GasCollectorRecipes
import magicbook.gtlitecore.loader.recipe.machine.LoomRecipes
import magicbook.gtlitecore.loader.recipe.machine.MixerRecipes
import magicbook.gtlitecore.loader.recipe.machine.PackerRecipes
import magicbook.gtlitecore.loader.recipe.machine.PolisherRecipes
import magicbook.gtlitecore.loader.recipe.machine.WiremillRecipes
import magicbook.gtlitecore.loader.recipe.oreprocessing.AluminiumSodiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.CaesiumRubidiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.MolybdenumRheniumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.NiobiumTantalumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.StrontiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.TungstenProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.UraniumPlutoniumProcessing
import magicbook.gtlitecore.loader.recipe.producer.CircuitAssemblyLineRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.DisposableToolRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.GreenhouseRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.MassReplicationRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.MetalCasingRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.MiningDroneAsteroidRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.StoneVariantRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.WrapItemRecipeProducer

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
            StoneVariantRecipeProducer.produce()
            DisposableToolRecipeProducer.produce()
            MetalCasingRecipeProducer.produce()
            MassReplicationRecipeProducer.produce()
            CircuitAssemblyLineRecipeProducer.produce()
            WrapItemRecipeProducer.produce()
            GreenhouseRecipeProducer.produce()
            MiningDroneAsteroidRecipeProducer.produce()

            // Loading chains and ore processings.
            OilCrackingChain.init()
            RubbersChain.init()
            PolymersChain.init()
            AlloysChain.init()
            DyesChain.init()
            MicaInsulatorChain.init()
            OxidesChain.init()
            AmmoniaChain.init()

            AluminiumSodiumProcessing.init()
            StrontiumProcessing.init()
            NiobiumTantalumProcessing.init()
            CaesiumRubidiumProcessing.init()
            MolybdenumRheniumProcessing.init()
            UraniumPlutoniumProcessing.init()
            TungstenProcessing.init()

            // Circuit recipes.
            ElectronicCircuits.init()
            IntegratedCircuits.init()
            ProcessorCircuits.init()
            NanoCircuits.init()
            QuantumCircuits.init()

            // Original GregTech machine recipes.
            AssemblerRecipes.init()
            BenderRecipes.init()
            CannerRecipes.init()
            CentrifugeRecipes.init()
            ExtruderRecipes.init()
            FormingPressRecipes.init()
            GasCollectorRecipes.init()
            MixerRecipes.init()
            PackerRecipes.init()
            WiremillRecipes.init()
            // Additional machine recipes.
            AlloyBlastSmelterRecipes.init()
            BathCondenserRecipes.init()
            LoomRecipes.init()
            PolisherRecipes.init()

            // Loading override recipes.
            VanillaOverrideRecipeLoader.init()
            GregtechOverrideRecipeLoader.init()
        }

    }

}