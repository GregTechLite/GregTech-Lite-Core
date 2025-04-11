package magicbook.gtlitecore.loader.recipe

import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.integration.appliedenergistics2.recipe.AppEngCALRecipeProducer
import magicbook.gtlitecore.loader.recipe.chain.AlloysChain
import magicbook.gtlitecore.loader.recipe.chain.AmmoniaChain
import magicbook.gtlitecore.loader.recipe.chain.BZMediumChain
import magicbook.gtlitecore.loader.recipe.chain.CitricAcidChain
import magicbook.gtlitecore.loader.recipe.chain.DyesChain
import magicbook.gtlitecore.loader.recipe.chain.EDTAChain
import magicbook.gtlitecore.loader.recipe.chain.EtchingMaterialsChain
import magicbook.gtlitecore.loader.recipe.chain.EthyleneGlycolChain
import magicbook.gtlitecore.loader.recipe.chain.FEPChain
import magicbook.gtlitecore.loader.recipe.chain.GrapheneChain
import magicbook.gtlitecore.loader.recipe.chain.GrowthMediumChain
import magicbook.gtlitecore.loader.recipe.chain.HydrazineChain
import magicbook.gtlitecore.loader.recipe.chain.HydrogenPeroxideChain
import magicbook.gtlitecore.loader.recipe.chain.KaptonChain
import magicbook.gtlitecore.loader.recipe.chain.LithiumTitanateChain
import magicbook.gtlitecore.loader.recipe.chain.MagnetoResonaticChain
import magicbook.gtlitecore.loader.recipe.chain.MethylamineChain
import magicbook.gtlitecore.loader.recipe.chain.MicaInsulatorChain
import magicbook.gtlitecore.loader.recipe.chain.NanoparticlesChain
import magicbook.gtlitecore.loader.recipe.chain.OilCrackingChain
import magicbook.gtlitecore.loader.recipe.chain.OxidesChain
import magicbook.gtlitecore.loader.recipe.chain.OzoneChain
import magicbook.gtlitecore.loader.recipe.chain.PMMAChain
import magicbook.gtlitecore.loader.recipe.chain.PalladiumAcetateChain
import magicbook.gtlitecore.loader.recipe.chain.PedotChain
import magicbook.gtlitecore.loader.recipe.chain.PhosphorusChain
import magicbook.gtlitecore.loader.recipe.chain.PolymersChain
import magicbook.gtlitecore.loader.recipe.chain.QuantumDotsChain
import magicbook.gtlitecore.loader.recipe.chain.RubbersChain
import magicbook.gtlitecore.loader.recipe.circuit.CrystalCircuits
import magicbook.gtlitecore.loader.recipe.circuit.ElectronicCircuits
import magicbook.gtlitecore.loader.recipe.circuit.GoowareCircuits
import magicbook.gtlitecore.loader.recipe.circuit.IntegratedCircuits
import magicbook.gtlitecore.loader.recipe.circuit.NanoCircuits
import magicbook.gtlitecore.loader.recipe.circuit.ProcessorCircuits
import magicbook.gtlitecore.loader.recipe.circuit.QuantumCircuits
import magicbook.gtlitecore.loader.recipe.circuit.WetwareCircuits
import magicbook.gtlitecore.loader.recipe.machine.AlloyBlastSmelterRecipes
import magicbook.gtlitecore.loader.recipe.machine.AlloySmelterRecipes
import magicbook.gtlitecore.loader.recipe.machine.AssemblerRecipes
import magicbook.gtlitecore.loader.recipe.machine.AssemblyLineRecipes
import magicbook.gtlitecore.loader.recipe.machine.AutoclaveRecipes
import magicbook.gtlitecore.loader.recipe.machine.BathCondenserRecipes
import magicbook.gtlitecore.loader.recipe.machine.BenderRecipes
import magicbook.gtlitecore.loader.recipe.machine.CannerRecipes
import magicbook.gtlitecore.loader.recipe.machine.CentrifugeRecipes
import magicbook.gtlitecore.loader.recipe.machine.CircuitAssemblerRecipes
import magicbook.gtlitecore.loader.recipe.machine.CrystallizationCrucibleRecipes
import magicbook.gtlitecore.loader.recipe.machine.ExtruderRecipes
import magicbook.gtlitecore.loader.recipe.machine.FluidSolidifierRecipes
import magicbook.gtlitecore.loader.recipe.machine.FormingPressRecipes
import magicbook.gtlitecore.loader.recipe.machine.GasCollectorRecipes
import magicbook.gtlitecore.loader.recipe.machine.LaserEngraverRecipes
import magicbook.gtlitecore.loader.recipe.machine.LoomRecipes
import magicbook.gtlitecore.loader.recipe.machine.MixerRecipes
import magicbook.gtlitecore.loader.recipe.machine.MobExtractorRecipes
import magicbook.gtlitecore.loader.recipe.machine.PackerRecipes
import magicbook.gtlitecore.loader.recipe.machine.PolisherRecipes
import magicbook.gtlitecore.loader.recipe.machine.VacuumChamberRecipes
import magicbook.gtlitecore.loader.recipe.machine.WiremillRecipes
import magicbook.gtlitecore.loader.recipe.oreprocessing.AluminiumSodiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.CaesiumRubidiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.GermaniumZincProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.IodineBromineProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.MolybdenumRheniumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.NiobiumTantalumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.PlatinumGroupProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.PotassiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.RareEarthProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.SeleniumTelluriumProcessing
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
            if (Mods.AppliedEnergistics2.isModLoaded())
                AppEngCALRecipeProducer.produce()

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
            PalladiumAcetateChain.init()
            LithiumTitanateChain.init()
            NanoparticlesChain.init()
            QuantumDotsChain.init()
            OzoneChain.init()
            MagnetoResonaticChain.init()
            HydrogenPeroxideChain.init()
            HydrazineChain.init()
            GrapheneChain.init()
            CitricAcidChain.init()
            GrowthMediumChain.init()
            KaptonChain.init()
            PMMAChain.init()
            EthyleneGlycolChain.init()
            PedotChain.init()
            MethylamineChain.init()
            EDTAChain.init()
            EtchingMaterialsChain.init()
            FEPChain.init()
            BZMediumChain.init()
            PhosphorusChain.init()

            AluminiumSodiumProcessing.init()
            PotassiumProcessing.init()
            StrontiumProcessing.init()
            NiobiumTantalumProcessing.init()
            CaesiumRubidiumProcessing.init()
            MolybdenumRheniumProcessing.init()
            UraniumPlutoniumProcessing.init()
            TungstenProcessing.init()
            IodineBromineProcessing.init()
            SeleniumTelluriumProcessing.init()
            RareEarthProcessing.init()
            PlatinumGroupProcessing.init()
            GermaniumZincProcessing.init()

            // Circuit recipes.
            ElectronicCircuits.init()
            IntegratedCircuits.init()
            ProcessorCircuits.init()
            NanoCircuits.init()
            QuantumCircuits.init()
            CrystalCircuits.init()
            WetwareCircuits.init()
            GoowareCircuits.init()

            // Original GregTech machine recipes.
            AlloySmelterRecipes.init()
            AssemblerRecipes.init()
            AssemblyLineRecipes.init()
            AutoclaveRecipes.init()
            BenderRecipes.init()
            CannerRecipes.init()
            CentrifugeRecipes.init()
            CircuitAssemblerRecipes.init()
            ExtruderRecipes.init()
            FluidSolidifierRecipes.init()
            FormingPressRecipes.init()
            GasCollectorRecipes.init()
            LaserEngraverRecipes.init()
            MixerRecipes.init()
            PackerRecipes.init()
            WiremillRecipes.init()

            // Additional machine recipes.
            AlloyBlastSmelterRecipes.init()
            BathCondenserRecipes.init()
            CrystallizationCrucibleRecipes.init()
            LoomRecipes.init()
            MobExtractorRecipes.init()
            PolisherRecipes.init()
            VacuumChamberRecipes.init()

            // Loading override recipes.
            VanillaOverrideRecipeLoader.init()
            GregtechOverrideRecipeLoader.init()
            RecipeConflicts.init()
        }

    }

}