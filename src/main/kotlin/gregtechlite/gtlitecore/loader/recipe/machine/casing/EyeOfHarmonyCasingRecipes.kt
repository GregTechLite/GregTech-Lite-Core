package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.common.blocks.BlockBatteryPart
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.GRAVITATION_ENGINE
import gregtech.common.metatileentities.MetaTileEntities.ACTIVE_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.CHARGER
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH
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
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HarmonicPhononMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Legendarium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpatiallyEnlargedFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TachyonRichTemporalFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENERGISED_TESSERACT
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_65536

internal object EyeOfHarmonyCasingRecipes
{

    // @formatter:off
    fun init()
    {

        // Reinforced Temporal Structure Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_CASING.getStack(32))
            .input(block, CosmicNeutronium, 64)
            .input(block, TranscendentMetal, 64)
            .input(nanite, Neutronium, 48)
            .input(plateDense, Bedrockium, 6)
            .input(plateDense, HarmonicPhononMatter, 6)
            .input(plateDense, Shirabon, 6)
            .input(plateDense, Infinity, 6)
            .input(WORLD_ACCELERATOR[ZPM], 4)
            .input(GRAVITATION_ENGINE, 64)
            .input(ENERGISED_TESSERACT)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(256_000))
            .fluidInputs(Creon.getFluid(L * 40))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 10))
            .outputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.getStack(64))
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(WORLD_ACCELERATOR[ZPM].stackForm)
                    .EUt(VA[UXV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // Reinforced Spatial Structure Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_CASING.getStack(32))
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
            .stationResearch { r ->
                r.researchStack(AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_BASE_CASING.stack)
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
            .stationResearch { r ->
                r.researchStack(POWER_SUBSTATION.stackForm)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

    }

    // @formatter:on

}