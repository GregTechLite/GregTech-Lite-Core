package magicbook.gtlitecore.loader.recipe

import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.integration.appliedenergistics2.recipe.AppEngCALRecipeProducer
import magicbook.gtlitecore.loader.recipe.chain.AcetyleneChain
import magicbook.gtlitecore.loader.recipe.chain.ActiniumSuperhydrideChain
import magicbook.gtlitecore.loader.recipe.chain.AlloysChain
import magicbook.gtlitecore.loader.recipe.chain.AmmoniaChain
import magicbook.gtlitecore.loader.recipe.chain.BETSPerrhenateChain
import magicbook.gtlitecore.loader.recipe.chain.BSCCOChain
import magicbook.gtlitecore.loader.recipe.chain.BZMediumChain
import magicbook.gtlitecore.loader.recipe.chain.BoronNitrideChain
import magicbook.gtlitecore.loader.recipe.chain.ButyllithiumChain
import magicbook.gtlitecore.loader.recipe.chain.CarbonNanotubeChain
import magicbook.gtlitecore.loader.recipe.chain.ChloridesChain
import magicbook.gtlitecore.loader.recipe.chain.CitricAcidChain
import magicbook.gtlitecore.loader.recipe.chain.CosmicNeutroniumChain
import magicbook.gtlitecore.loader.recipe.chain.DegenerateRheniumChain
import magicbook.gtlitecore.loader.recipe.chain.DiethylEtherChain
import magicbook.gtlitecore.loader.recipe.chain.DyesChain
import magicbook.gtlitecore.loader.recipe.chain.EDTAChain
import magicbook.gtlitecore.loader.recipe.chain.EtchingMaterialsChain
import magicbook.gtlitecore.loader.recipe.chain.EthyleneGlycolChain
import magicbook.gtlitecore.loader.recipe.chain.FEPChain
import magicbook.gtlitecore.loader.recipe.chain.FullereneChain
import magicbook.gtlitecore.loader.recipe.chain.FullerenePolymerMatrixChain
import magicbook.gtlitecore.loader.recipe.chain.GalliumNitrideChain
import magicbook.gtlitecore.loader.recipe.chain.GrapheneChain
import magicbook.gtlitecore.loader.recipe.chain.GrowthMediumChain
import magicbook.gtlitecore.loader.recipe.chain.HMXChain
import magicbook.gtlitecore.loader.recipe.chain.HNIWChain
import magicbook.gtlitecore.loader.recipe.chain.HalkoniteSteelChain
import magicbook.gtlitecore.loader.recipe.chain.HeatExchangeChain
import magicbook.gtlitecore.loader.recipe.chain.HydrazineChain
import magicbook.gtlitecore.loader.recipe.chain.HydrogenPeroxideChain
import magicbook.gtlitecore.loader.recipe.chain.HypogenChain
import magicbook.gtlitecore.loader.recipe.chain.InfinityChain
import magicbook.gtlitecore.loader.recipe.chain.KaptonChain
import magicbook.gtlitecore.loader.recipe.chain.KevlarChain
import magicbook.gtlitecore.loader.recipe.chain.LeadSiliconGlassChain
import magicbook.gtlitecore.loader.recipe.chain.LithiumNiobateChain
import magicbook.gtlitecore.loader.recipe.chain.LithiumTitanateChain
import magicbook.gtlitecore.loader.recipe.chain.LuTmYVOChain
import magicbook.gtlitecore.loader.recipe.chain.MagnetiumChain
import magicbook.gtlitecore.loader.recipe.chain.MagnetoResonaticChain
import magicbook.gtlitecore.loader.recipe.chain.MethylamineChain
import magicbook.gtlitecore.loader.recipe.chain.MicaInsulatorChain
import magicbook.gtlitecore.loader.recipe.chain.MutatedLivingSolderChain
import magicbook.gtlitecore.loader.recipe.chain.NanitesChain
import magicbook.gtlitecore.loader.recipe.chain.NanoparticlesChain
import magicbook.gtlitecore.loader.recipe.chain.NdYAGChain
import magicbook.gtlitecore.loader.recipe.chain.OctaazacubaneChain
import magicbook.gtlitecore.loader.recipe.chain.OilsChain
import magicbook.gtlitecore.loader.recipe.chain.OxidesChain
import magicbook.gtlitecore.loader.recipe.chain.OzoneChain
import magicbook.gtlitecore.loader.recipe.chain.PEEKChain
import magicbook.gtlitecore.loader.recipe.chain.PETChain
import magicbook.gtlitecore.loader.recipe.chain.PMMAChain
import magicbook.gtlitecore.loader.recipe.chain.PalladiumAcetateChain
import magicbook.gtlitecore.loader.recipe.chain.ParticlesChain
import magicbook.gtlitecore.loader.recipe.chain.PedotChain
import magicbook.gtlitecore.loader.recipe.chain.PhosphorusChain
import magicbook.gtlitecore.loader.recipe.chain.PolymersChain
import magicbook.gtlitecore.loader.recipe.chain.PotassiumPermanganateChain
import magicbook.gtlitecore.loader.recipe.chain.QuantumDotsChain
import magicbook.gtlitecore.loader.recipe.chain.QuarksChain
import magicbook.gtlitecore.loader.recipe.chain.ReactorModeratorChain
import magicbook.gtlitecore.loader.recipe.chain.RocketFuelsChain
import magicbook.gtlitecore.loader.recipe.chain.RubbersChain
import magicbook.gtlitecore.loader.recipe.chain.RubidiumTitanateChain
import magicbook.gtlitecore.loader.recipe.chain.ShirabonChain
import magicbook.gtlitecore.loader.recipe.chain.SilicaGelChain
import magicbook.gtlitecore.loader.recipe.chain.SiliconNitrideChain
import magicbook.gtlitecore.loader.recipe.chain.SuperheavyElementsChain
import magicbook.gtlitecore.loader.recipe.chain.ThoriumYttriumGlassChain
import magicbook.gtlitecore.loader.recipe.chain.YBCOChain
import magicbook.gtlitecore.loader.recipe.chain.ZylonChain
import magicbook.gtlitecore.loader.recipe.chain.food.AdditivesChain
import magicbook.gtlitecore.loader.recipe.chain.food.ChloroauricAcidChain
import magicbook.gtlitecore.loader.recipe.chain.food.CoffeeChain
import magicbook.gtlitecore.loader.recipe.chain.food.CornChain
import magicbook.gtlitecore.loader.recipe.chain.food.GrahamCrackersChain
import magicbook.gtlitecore.loader.recipe.chain.food.MilkChain
import magicbook.gtlitecore.loader.recipe.chain.food.PurpleDrinkChain
import magicbook.gtlitecore.loader.recipe.chain.food.SugarChain
import magicbook.gtlitecore.loader.recipe.chain.food.UtensilsChain
import magicbook.gtlitecore.loader.recipe.chain.food.VinegarChain
import magicbook.gtlitecore.loader.recipe.chain.food.WinesChain
import magicbook.gtlitecore.loader.recipe.circuit.CrystalCircuits
import magicbook.gtlitecore.loader.recipe.circuit.ElectronicCircuits
import magicbook.gtlitecore.loader.recipe.circuit.GoowareCircuits
import magicbook.gtlitecore.loader.recipe.circuit.IntegratedCircuits
import magicbook.gtlitecore.loader.recipe.circuit.NanoCircuits
import magicbook.gtlitecore.loader.recipe.circuit.OpticalCircuits
import magicbook.gtlitecore.loader.recipe.circuit.ProcessorCircuits
import magicbook.gtlitecore.loader.recipe.circuit.QuantumCircuits
import magicbook.gtlitecore.loader.recipe.circuit.SpintronicCircuits
import magicbook.gtlitecore.loader.recipe.circuit.WetwareCircuits
import magicbook.gtlitecore.loader.recipe.machine.AlloyBlastSmelterRecipes
import magicbook.gtlitecore.loader.recipe.machine.AlloySmelterRecipes
import magicbook.gtlitecore.loader.recipe.machine.AssemblerRecipes
import magicbook.gtlitecore.loader.recipe.machine.AssemblyLineRecipes
import magicbook.gtlitecore.loader.recipe.machine.AutoclaveRecipes
import magicbook.gtlitecore.loader.recipe.machine.BathCondenserRecipes
import magicbook.gtlitecore.loader.recipe.machine.BenderRecipes
import magicbook.gtlitecore.loader.recipe.machine.BioSimulatorRecipes
import magicbook.gtlitecore.loader.recipe.machine.CannerRecipes
import magicbook.gtlitecore.loader.recipe.machine.CentrifugeRecipes
import magicbook.gtlitecore.loader.recipe.machine.CircuitAssemblerRecipes
import magicbook.gtlitecore.loader.recipe.machine.CrystallizationCrucibleRecipes
import magicbook.gtlitecore.loader.recipe.machine.CutterRecipes
import magicbook.gtlitecore.loader.recipe.machine.ExtruderRecipes
import magicbook.gtlitecore.loader.recipe.machine.FluidSolidifierRecipes
import magicbook.gtlitecore.loader.recipe.machine.FormingPressRecipes
import magicbook.gtlitecore.loader.recipe.machine.FusionReactorRecipes
import magicbook.gtlitecore.loader.recipe.machine.GasCollectorRecipes
import magicbook.gtlitecore.loader.recipe.machine.LaserEngraverRecipes
import magicbook.gtlitecore.loader.recipe.machine.LoomRecipes
import magicbook.gtlitecore.loader.recipe.machine.MixerRecipes
import magicbook.gtlitecore.loader.recipe.machine.MobExtractorRecipes
import magicbook.gtlitecore.loader.recipe.machine.PackerRecipes
import magicbook.gtlitecore.loader.recipe.machine.PolisherRecipes
import magicbook.gtlitecore.loader.recipe.machine.StellarForgeRecipes
import magicbook.gtlitecore.loader.recipe.machine.VacuumChamberRecipes
import magicbook.gtlitecore.loader.recipe.machine.WiremillRecipes
import magicbook.gtlitecore.loader.recipe.oreprocessing.AdamantiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.AluminiumSodiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.BedrockiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.CaesiumRubidiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.GermaniumZincProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.IodineBromineProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.MolybdenumRheniumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.NaquadahProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.NiobiumTantalumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.PlatinumGroupProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.PoloniumRadiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.PotassiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.ProtactiniumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.RareEarthProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.SeleniumTelluriumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.StrontiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.TechnetiumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.ThalliumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.TungstenProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.UraniumPlutoniumProcessing
import magicbook.gtlitecore.loader.recipe.oreprocessing.VibraniumProcessing
import magicbook.gtlitecore.loader.recipe.producer.CircuitAssemblyLineRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.ComponentAssemblyLineRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.DisposableToolRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.GreenhouseRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.MassReplicationRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.MetalCasingRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.MiningDroneAsteroidRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.NuclearFissionRecipeProducer
import magicbook.gtlitecore.loader.recipe.producer.PCBFactoryRecipeProducer
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
            ComponentAssemblyLineRecipeProducer.produce()
            NuclearFissionRecipeProducer.produce()
            PCBFactoryRecipeProducer.produce()

            // Loading chains and ore processings.
            OilsChain.init()
            RubbersChain.init()
            PolymersChain.init()
            AlloysChain.init()
            BSCCOChain.init()
            YBCOChain.init()
            DyesChain.init()
            MicaInsulatorChain.init()
            OxidesChain.init()
            AmmoniaChain.init()
            PalladiumAcetateChain.init()
            LithiumTitanateChain.init()
            RubidiumTitanateChain.init()
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
            ButyllithiumChain.init()
            EtchingMaterialsChain.init()
            FEPChain.init()
            BZMediumChain.init()
            PhosphorusChain.init()
            RocketFuelsChain.init()
            ReactorModeratorChain.init()
            SuperheavyElementsChain.init()
            PEEKChain.init()
            ZylonChain.init()
            PotassiumPermanganateChain.init()
            BoronNitrideChain.init()
            AcetyleneChain.init()
            KevlarChain.init()
            GalliumNitrideChain.init()
            PETChain.init()
            LithiumNiobateChain.init()
            ChloridesChain.init()
            NdYAGChain.init()
            DiethylEtherChain.init()
            ParticlesChain.init()
            CosmicNeutroniumChain.init()
            HalkoniteSteelChain.init()
            LeadSiliconGlassChain.init()
            ThoriumYttriumGlassChain.init()
            HeatExchangeChain.init()
            MutatedLivingSolderChain.init()
            FullereneChain.init()
            DegenerateRheniumChain.init()
            ActiniumSuperhydrideChain.init()
            BETSPerrhenateChain.init()
            QuarksChain.init()
            SilicaGelChain.init()
            HNIWChain.init()
            HMXChain.init()
            OctaazacubaneChain.init()
            CarbonNanotubeChain.init()
            InfinityChain.init()
            MagnetiumChain.init()
            HypogenChain.init()
            ShirabonChain.init()
            FullerenePolymerMatrixChain.init()
            LuTmYVOChain.init()
            SiliconNitrideChain.init()
            NanitesChain.init()

            AdditivesChain.init()
            UtensilsChain.init()
            WinesChain.init()
            PurpleDrinkChain.init()
            VinegarChain.init()
            CornChain.init()
            CoffeeChain.init()
            MilkChain.init()
            GrahamCrackersChain.init()
            SugarChain.init()
            ChloroauricAcidChain.init()

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
            NaquadahProcessing.init()
            TechnetiumProcessing.init()
            BedrockiumProcessing.init()
            AdamantiumProcessing.init()
            VibraniumProcessing.init()
            ThalliumProcessing.init()
            PoloniumRadiumProcessing.init()
            ProtactiniumProcessing.init()

            // Circuit recipes.
            ElectronicCircuits.init()
            IntegratedCircuits.init()
            ProcessorCircuits.init()
            NanoCircuits.init()
            QuantumCircuits.init()
            CrystalCircuits.init()
            WetwareCircuits.init()
            GoowareCircuits.init()
            OpticalCircuits.init()
            SpintronicCircuits.init()

            // Original GregTech machine recipes.
            AlloySmelterRecipes.init()
            AssemblerRecipes.init()
            AssemblyLineRecipes.init()
            AutoclaveRecipes.init()
            BenderRecipes.init()
            CannerRecipes.init()
            CentrifugeRecipes.init()
            CircuitAssemblerRecipes.init()
            CutterRecipes.init()
            ExtruderRecipes.init()
            FluidSolidifierRecipes.init()
            FormingPressRecipes.init()
            FusionReactorRecipes.init()
            GasCollectorRecipes.init()
            LaserEngraverRecipes.init()
            MixerRecipes.init()
            PackerRecipes.init()
            WiremillRecipes.init()

            // Additional machine recipes.
            AlloyBlastSmelterRecipes.init()
            BathCondenserRecipes.init()
            BioSimulatorRecipes.init()
            CrystallizationCrucibleRecipes.init()
            LoomRecipes.init()
            MobExtractorRecipes.init()
            PolisherRecipes.init()
            StellarForgeRecipes.init()
            VacuumChamberRecipes.init()

            // Loading override recipes.
            VanillaOverrideRecipeLoader.init()
            GregtechOverrideRecipeLoader.init()
            RecipeConflicts.init()
        }

    }

}