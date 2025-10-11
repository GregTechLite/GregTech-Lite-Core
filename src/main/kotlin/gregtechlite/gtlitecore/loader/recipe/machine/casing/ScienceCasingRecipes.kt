package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.common.blocks.BlockBatteryPart
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.ENERGY_CLUSTER
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.GRAVITATION_ENGINE
import gregtech.common.metatileentities.MetaTileEntities.ACTIVE_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.CHARGER
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.HPCA_ACTIVE_COOLER_COMPONENT
import gregtech.common.metatileentities.MetaTileEntities.HPCA_ADVANCED_COMPUTATION_COMPONENT
import gregtech.common.metatileentities.MetaTileEntities.HPCA_BRIDGE_COMPONENT
import gregtech.common.metatileentities.MetaTileEntities.POWER_SUBSTATION
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_CHEST
import gregtech.common.metatileentities.MetaTileEntities.WORLD_ACCELERATOR
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronFranciumCarbideSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GSTGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HarmonicPhononMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Legendarium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PreciousMetalAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ReneN5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpatiallyEnlargedFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TachyonRichTemporalFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTComputerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTWireCoil
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENERGISED_TESSERACT
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_65536

internal object ScienceCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // region Level 1: High Power (LuV-UV)

        // Molecular Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(GTComputerCasing.HIGH_POWER_CASING.stack)
            .input(plateDense, Osmiridium, 6)
            .input(foil, Trinium, 12)
            .input(screw, TungstenSteel, 24)
            .input(ring, TungstenSteel, 24)
            .input(FIELD_GENERATOR_IV)
            .fluidInputs(Osmium.getFluid(L * 9))
            .outputs(ScienceCasing.MOLECULAR_CASING.getStack(2))
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Quantum Glass
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_CASING.stack)
            .inputs(GTGlassCasing.LAMINATED_GLASS.stack)
            .fluidInputs(Trinium.getFluid(L * 4))
            .outputs(GlassCasing.QUANTUM.stack)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Hollow Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_CASING.stack)
            .inputs(GTBoilerCasing.TUNGSTENSTEEL_PIPE.getStack(2))
            .input(plate, Europium, 2)
            .input(plateDouble, Plutonium239, 4)
            .input(plateDouble, Lead, 8)
            .input(plate, Uranium238, 16)
            .input(screw, Uranium235, 16)
            .fluidInputs(Trinium.getFluid(L * 9))
            .fluidInputs(Osmium.getFluid(L * 9))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .fluidInputs(Argon.getFluid(1000))
            .outputs(ScienceCasing.HOLLOW_CASING.getStack(4))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(ScienceCasing.MOLECULAR_CASING.stack)
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // Molecular Coil
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.HOLLOW_CASING.stack)
            .inputs(GTFusionCasing.FUSION_COIL.getStack(2))
            .inputs(GTWireCoil.TRINIUM.getStack(2))
            .input(wireFine, Europium, 64)
            .input(foil, Europium, 64)
            .fluidInputs(GSTGlass.getFluid(L * 16))
            .fluidInputs(SiliconeRubber.getFluid(L * 13))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .fluidInputs(Trinium.getFluid(L * 9))
            .outputs(ScienceCasing.MOLECULAR_COIL.getStack(8))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(ScienceCasing.HOLLOW_CASING.stack)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // endregion

        // region Level 2: Dimensionally Transcendent (UHV-UEV)

        // Ultimate Molecular Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_CASING.getStack(4))
            .input(plate, Osmiridium, 4)
            .input(plateDense, Seaborgium, 2)
            .input(plateDense, Mendelevium, 2)
            .input(circuit, Tier.UV)
            .input(screw, ReneN5, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(NaquadahEnriched.getFluid(L * 12))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .outputs(ScienceCasing.ULTIMATE_MOLECULAR_CASING.getStack(16))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(block, Vibranium)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Dimensional Bridge Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.ULTIMATE_MOLECULAR_CASING.stack)
            .input(frameGt, HDCS, 8)
            .input(plateDense, MetastableOganesson, 2)
            .input(EMITTER_UHV)
            .input(plate, Roentgenium, 6)
            .input(gear, Roentgenium, 4)
            .input(gearSmall, Roentgenium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(NaquadahEnriched.getFluid(L * 12))
            .fluidInputs(Darmstadtium.getFluid(L * 4))
            .outputs(ScienceCasing.DIMENSIONAL_BRIDGE_CASING.getStack(16))
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(HPCA_BRIDGE_COMPONENT)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Containment Field Generator
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.ULTIMATE_MOLECULAR_CASING.getStack(4))
            .inputs(ScienceCasing.MOLECULAR_COIL.getStack(8))
            .input(frameGt, Naquadria, 8)
            .input(frameGt, Dubnium, 8)
            .input(frameGt, Curium, 8)
            .input(frameGt, PreciousMetalAlloy, 8)
            .input(FIELD_GENERATOR_UHV, 2)
            .input(plateDense, Meitnerium, 2)
            .input(screw, YttriumBariumCuprate, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(NaquadahEnriched.getFluid(L * 12))
            .fluidInputs(Neutronium.getFluid(L * 4))
            .outputs(ScienceCasing.CONTAINMENT_FIELD_GENERATOR.getStack(16))
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(HPCA_ACTIVE_COOLER_COMPONENT)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Spacetime Altering Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.ULTIMATE_MOLECULAR_CASING.getStack(4))
            .inputs(ScienceCasing.HOLLOW_CASING.getStack(8))
            .input(frameGt, Rutherfordium, 8)
            .input(plateDense, Copernicium, 2)
            .input(ENERGY_CLUSTER)
            .input(foil, Californium, 16)
            .input(screw, EnrichedNaquadahAlloy, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(NaquadahEnriched.getFluid(L * 12))
            .fluidInputs(Curium.getFluid(L * 4))
            .outputs(ScienceCasing.SPACETIME_ALTERING_CASING.getStack(16))
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(HPCA_ADVANCED_COMPUTATION_COMPONENT)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // endregion

        // region Level 3: Truth of the Universe (UIV-OpV)

        // Reinforced Temporal Structure Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.ULTIMATE_MOLECULAR_CASING.getStack(32))
            .input(block, CosmicNeutronium, 64)
            .input(block, TranscendentMetal, 64)
            .input(nanite, Neutronium, 48)
            .input(plateDense, Bedrockium, 6)
            .input(plateDense, HarmonicPhononMatter, 6)
            .input(plateDense, Shirabon, 6)
            .input(plateDense, Infinity, 6)
            .input(WORLD_ACCELERATOR[UV], 4)
            .input(GRAVITATION_ENGINE, 64)
            .input(ENERGISED_TESSERACT)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(256_000))
            .fluidInputs(Creon.getFluid(L * 40))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 10))
            .outputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.getStack(64))
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(WORLD_ACCELERATOR[UV].stackForm)
                    .EUt(VA[UXV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // Reinforced Spatial Structure Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.ULTIMATE_MOLECULAR_CASING.getStack(32))
            .input(block, SpaceTime, 64)
            .input(block, MagMatter, 64)
            .input(nanite, Neutronium, 48)
            .input(plateDense, CosmicNeutronium, 6)
            .input(plateDense, HalkoniteSteel, 6)
            .input(plateDense, TranscendentMetal, 6)
            .input(plateDense, Legendarium, 6)
            .input(QUANTUM_CHEST[UHV])
            .input(GRAVITATION_ENGINE, 64)
            .input(ENERGISED_TESSERACT)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(256_000))
            .fluidInputs(Creon.getFluid(L * 40))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 10))
            .outputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.getStack(64))
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_BASE_CASING.stack)
                    .EUt(VA[UXV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // Infinite Spacetime Energy Boundary Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(POWER_SUBSTATION)
            .input(CHARGER[UXV])
            .inputs(MetaBlocks.BATTERY_BLOCK.getItemVariant(BlockBatteryPart.BatteryPartType.ULTIMATE_UHV))
            .input(wireGtHex, BoronFranciumCarbideSuperconductor, 4)
            .input(ACTIVE_TRANSFORMER, 16)
            .input(ENERGY_INPUT_HATCH[UXV], 4) // TODO UXV Wireless Energy Hatch
            .input(LASER_INPUT_HATCH_65536[UXV - IV])
            .input(circuit, Tier.UXV, 64)
            .input(plate, MetastableHassium, 6)
            .input(plate, Nihonium, 6)
            .input(plate, Seaborgium, 6)
            .input(plate, Mendelevium, 6)
            .input(screw, MetastableHassium, 6)
            .input(screw, Nihonium, 6)
            .input(screw, Seaborgium, 6)
            .input(screw, Mendelevium, 6)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(256_000))
            .fluidInputs(Creon.getFluid(L * 40))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.getStack(32))
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(POWER_SUBSTATION.stackForm)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // endregion

    }

    // @formatter:on

}