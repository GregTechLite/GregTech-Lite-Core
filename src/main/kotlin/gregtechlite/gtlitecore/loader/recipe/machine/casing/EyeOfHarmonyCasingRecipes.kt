package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.common.items.MetaItems.GRAVITATION_ENGINE
import gregtech.common.metatileentities.MetaTileEntities.FUSION_REACTOR
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_TANK
import gregtech.common.metatileentities.MetaTileEntities.WORLD_ACCELERATOR
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpatiallyEnlargedFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TachyonRichTemporalFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.common.block.adapter.GTBatteryBlock
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.variant.Manipulator
import gregtechlite.gtlitecore.common.block.variant.component.FieldGenCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.block.variant.science.SpacetimeCompressionFieldGenerator
import gregtechlite.gtlitecore.common.block.variant.science.StabilizationFieldGenerator
import gregtechlite.gtlitecore.common.block.variant.science.TimeAccelerationFieldGenerator
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UV
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.FUSION_REACTOR_MK4

internal object EyeOfHarmonyCasingRecipes
{

    // @formatter:off
    fun init()
    {

        // region Spacetime Compression

        // T1
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.EMPTY_TIER_I.stack)
            .input(QUANTUM_TANK[UHV], 4)
            .inputs(Manipulator.COSMIC_FABRIC.stack)
            .input(circuit, Tier.UXV)
            .input(bolt, Shirabon, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 20))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 10))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.CRUDE.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T2
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.EMPTY_TIER_II.getStack(2))
            .input(QUANTUM_TANK[UHV], 8)
            .inputs(Manipulator.COSMIC_FABRIC.getStack(2))
            .input(circuit, Tier.UXV)
            .input(bolt, WhiteDwarfMatter, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 20))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.PRIMITIVE.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(SpacetimeCompressionFieldGenerator.CRUDE.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T3
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.EMPTY_TIER_III.getStack(4))
            .input(QUANTUM_TANK[UHV], 12)
            .inputs(Manipulator.COSMIC_FABRIC.getStack(3))
            .input(circuit, Tier.UXV)
            .input(bolt, WhiteDwarfMatter, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 30))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.STABLE.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(SpacetimeCompressionFieldGenerator.PRIMITIVE.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T4
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.LAPOTRONIC_EV.stack)
            .input(QUANTUM_TANK[UHV], 16)
            .inputs(Manipulator.INFINITY_INFUSED.stack)
            .input(circuit, Tier.UXV, 2)
            .input(bolt, WhiteDwarfMatter, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 160))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 40))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.ADVANCED.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(SpacetimeCompressionFieldGenerator.STABLE.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T5
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.LAPOTRONIC_IV.stack)
            .input(QUANTUM_TANK[UHV], 20)
            .inputs(Manipulator.INFINITY_INFUSED.getStack(2))
            .input(circuit, Tier.UXV, 2)
            .input(bolt, BlackDwarfMatter, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 320))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 50))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.SUPERB.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(SpacetimeCompressionFieldGenerator.ADVANCED.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T6
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.LAPOTRONIC_LuV.stack)
            .input(QUANTUM_TANK[UHV], 24)
            .inputs(Manipulator.INFINITY_INFUSED.getStack(3))
            .input(circuit, Tier.UXV, 2)
            .input(bolt, BlackDwarfMatter, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 640))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 60))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.EXOTIC.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(SpacetimeCompressionFieldGenerator.SUPERB.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T7
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.LAPOTRONIC_ZPM.stack)
            .input(QUANTUM_TANK[UHV], 28)
            .inputs(Manipulator.SPACETIME_CONTINUUM_RIPPER.stack)
            .input(circuit, Tier.UXV, 3)
            .input(bolt, BlackDwarfMatter, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 1280))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 70))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.PERFECT.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(SpacetimeCompressionFieldGenerator.EXOTIC.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T8
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.LAPOTRONIC_UV.stack)
            .input(QUANTUM_TANK[UHV], 32)
            .inputs(Manipulator.SPACETIME_CONTINUUM_RIPPER.getStack(2))
            .input(circuit, Tier.UXV, 3)
            .input(bolt, Universium, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 2560))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 80))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.TIPLER.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(SpacetimeCompressionFieldGenerator.PERFECT.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T9
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_SPATIAL_STRUCTURE_CASING.stack)
            .inputs(GTBatteryBlock.ULTIMATE_UHV.stack)
            .input(QUANTUM_TANK[UHV], 36)
            .inputs(Manipulator.SPACETIME_CONTINUUM_RIPPER.getStack(3))
            .input(circuit, Tier.UXV, 3)
            .input(bolt, Universium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 5120))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 90))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(SpacetimeCompressionFieldGenerator.GALLIFREYAN.getStack(4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(SpacetimeCompressionFieldGenerator.TIPLER.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // endregion

        // region Time Acceleration

        // T1
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR[1])
            .inputs(GTFusionCasing.FUSION_COIL.stack)
            .input(WORLD_ACCELERATOR[UV], 4)
            .inputs(FieldGenCasing.EV.getStack(4))
            .input(circuit, Tier.UXV)
            .input(bolt, Shirabon, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 20))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 10))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.CRUDE.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T2
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR[1], 2)
            .inputs(GTFusionCasing.FUSION_COIL.getStack(2))
            .input(WORLD_ACCELERATOR[UV], 8)
            .inputs(FieldGenCasing.IV.getStack(8))
            .input(circuit, Tier.UXV)
            .input(bolt, WhiteDwarfMatter, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 20))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.PRIMITIVE.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(TimeAccelerationFieldGenerator.CRUDE.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T3
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR[1], 3)
            .inputs(GTFusionCasing.FUSION_COIL.getStack(3))
            .input(WORLD_ACCELERATOR[UV], 12)
            .inputs(FieldGenCasing.LuV.getStack(12))
            .input(circuit, Tier.UXV)
            .input(bolt, WhiteDwarfMatter, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 30))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.STABLE.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(TimeAccelerationFieldGenerator.PRIMITIVE.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T4
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR[2])
            .inputs(FusionCoil.ADVANCED.stack)
            .input(WORLD_ACCELERATOR[UV], 16)
            .inputs(FieldGenCasing.ZPM.getStack(16))
            .input(circuit, Tier.UXV, 2)
            .input(bolt, WhiteDwarfMatter, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 160))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 40))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.ADVANCED.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(TimeAccelerationFieldGenerator.STABLE.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T5
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR[2], 2)
            .inputs(FusionCoil.ADVANCED.getStack(2))
            .input(WORLD_ACCELERATOR[UV], 20)
            .inputs(FieldGenCasing.UV.getStack(20))
            .input(circuit, Tier.UXV, 2)
            .input(bolt, BlackDwarfMatter, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 320))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 50))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.SUPERB.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(TimeAccelerationFieldGenerator.ADVANCED.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T6
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR[2], 3)
            .inputs(FusionCoil.ADVANCED.getStack(3))
            .input(WORLD_ACCELERATOR[UV], 24)
            .inputs(FieldGenCasing.UHV.getStack(24))
            .input(circuit, Tier.UXV, 2)
            .input(bolt, BlackDwarfMatter, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 640))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 60))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.EXOTIC.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(TimeAccelerationFieldGenerator.SUPERB.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T7
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR_MK4)
            .inputs(FusionCoil.ULTIMATE.stack)
            .input(WORLD_ACCELERATOR[UV], 28)
            .inputs(FieldGenCasing.UEV.getStack(28))
            .input(circuit, Tier.UXV, 3)
            .input(bolt, BlackDwarfMatter, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 1280))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 70))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.PERFECT.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(TimeAccelerationFieldGenerator.EXOTIC.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T8
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR_MK4, 2)
            .inputs(FusionCoil.ULTIMATE.getStack(2))
            .input(WORLD_ACCELERATOR[UV], 32)
            .inputs(FieldGenCasing.UIV.getStack(32))
            .input(circuit, Tier.UXV, 3)
            .input(bolt, Universium, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 2560))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 80))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.TIPLER.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(TimeAccelerationFieldGenerator.PERFECT.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // T9
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.REINFORCED_TEMPORAL_STRUCTURE_CASING.stack)
            .input(FUSION_REACTOR_MK4, 3)
            .inputs(FusionCoil.ULTIMATE.getStack(3))
            .input(WORLD_ACCELERATOR[UV], 36)
            .inputs(FieldGenCasing.UXV.getStack(36))
            .input(circuit, Tier.UXV, 3)
            .input(bolt, Universium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 5120))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 90))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(TimeAccelerationFieldGenerator.GALLIFREYAN.getStack(8))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(TimeAccelerationFieldGenerator.TIPLER.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // endregion

        // region Stabilization

        // T1
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.CRUDE.stack)
            .inputs(SpacetimeCompressionFieldGenerator.CRUDE.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 4) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 4)
            .input(frameGt, SuperheavyAlloyB, 4)
            .input(frameGt, Mellion, 4)
            .input(frameGt, MetastableHassium, 4)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime)
            .input(gearSmall, SpaceTime)
            .input(circuit, Tier.UXV)
            .input(bolt, Shirabon, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 20))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 10))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 10))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.CRUDE.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // T2
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.PRIMITIVE.stack)
            .inputs(SpacetimeCompressionFieldGenerator.PRIMITIVE.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 8) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 8)
            .input(frameGt, SuperheavyAlloyB, 8)
            .input(frameGt, Mellion, 8)
            .input(frameGt, MetastableHassium, 8)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime, 2)
            .input(gearSmall, SpaceTime, 2)
            .input(circuit, Tier.UXV)
            .input(bolt, WhiteDwarfMatter, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 20))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 20))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.PRIMITIVE.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(StabilizationFieldGenerator.CRUDE.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // T3
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.STABLE.stack)
            .inputs(SpacetimeCompressionFieldGenerator.STABLE.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 12) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 12)
            .input(frameGt, SuperheavyAlloyB, 12)
            .input(frameGt, Mellion, 12)
            .input(frameGt, MetastableHassium, 12)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime, 3)
            .input(gearSmall, SpaceTime, 3)
            .input(circuit, Tier.UXV)
            .input(bolt, WhiteDwarfMatter, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 30))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 30))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.STABLE.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(StabilizationFieldGenerator.PRIMITIVE.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // T4
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.ADVANCED.stack)
            .inputs(SpacetimeCompressionFieldGenerator.ADVANCED.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 16) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 16)
            .input(frameGt, SuperheavyAlloyB, 16)
            .input(frameGt, Mellion, 16)
            .input(frameGt, MetastableHassium, 16)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime, 4)
            .input(gearSmall, SpaceTime, 4)
            .input(circuit, Tier.UXV, 2)
            .input(bolt, WhiteDwarfMatter, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 160))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 40))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 40))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.ADVANCED.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(StabilizationFieldGenerator.STABLE.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // T5
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.SUPERB.stack)
            .inputs(SpacetimeCompressionFieldGenerator.SUPERB.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 20) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 20)
            .input(frameGt, SuperheavyAlloyB, 20)
            .input(frameGt, Mellion, 20)
            .input(frameGt, MetastableHassium, 20)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime, 5)
            .input(gearSmall, SpaceTime, 5)
            .input(circuit, Tier.UXV, 2)
            .input(bolt, BlackDwarfMatter, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 320))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 50))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 50))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.SUPERB.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(StabilizationFieldGenerator.ADVANCED.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // T6
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.EXOTIC.stack)
            .inputs(SpacetimeCompressionFieldGenerator.EXOTIC.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 24) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 24)
            .input(frameGt, SuperheavyAlloyB, 24)
            .input(frameGt, Mellion, 24)
            .input(frameGt, MetastableHassium, 24)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime, 6)
            .input(gearSmall, SpaceTime, 6)
            .input(circuit, Tier.UXV, 2)
            .input(bolt, BlackDwarfMatter, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 640))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 60))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 60))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.EXOTIC.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(StabilizationFieldGenerator.SUPERB.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // T7
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.PERFECT.stack)
            .inputs(SpacetimeCompressionFieldGenerator.PERFECT.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 28) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 28)
            .input(frameGt, SuperheavyAlloyB, 28)
            .input(frameGt, Mellion, 28)
            .input(frameGt, MetastableHassium, 28)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime, 7)
            .input(gearSmall, SpaceTime, 7)
            .input(circuit, Tier.UXV, 2)
            .input(bolt, BlackDwarfMatter, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 1280))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 70))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 70))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.PERFECT.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(StabilizationFieldGenerator.EXOTIC.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // T8
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.TIPLER.stack)
            .inputs(SpacetimeCompressionFieldGenerator.TIPLER.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 32) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 32)
            .input(frameGt, SuperheavyAlloyB, 32)
            .input(frameGt, Mellion, 32)
            .input(frameGt, MetastableHassium, 32)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime, 8)
            .input(gearSmall, SpaceTime, 8)
            .input(circuit, Tier.UXV, 3)
            .input(bolt, Universium, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 2560))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 80))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 80))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.TIPLER.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(StabilizationFieldGenerator.PERFECT.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // T9
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(TimeAccelerationFieldGenerator.GALLIFREYAN.stack)
            .inputs(SpacetimeCompressionFieldGenerator.GALLIFREYAN.stack)
            .inputs(ScienceCasing.INFINITE_SPACETIME_ENERGY_BOUNDARY_CASING.stack)
            .input(MINING_DRONE_UV, 36) // TODO Dyson Swarm Module
            .input(frameGt, Rhugnor, 36)
            .input(frameGt, SuperheavyAlloyB, 36)
            .input(frameGt, Mellion, 36)
            .input(frameGt, MetastableHassium, 36)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(GRAVITATION_ENGINE, 64)
            .input(gear, SpaceTime, 9)
            .input(gearSmall, SpaceTime, 9)
            .input(circuit, Tier.UXV, 3)
            .input(bolt, Universium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 5120))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 90))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 90))
            .fluidInputs(SpaceTime.getFluid(L * 10))
            .outputs(StabilizationFieldGenerator.GALLIFREYAN.getStack(16))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .stationResearch {
                it.researchStack(StabilizationFieldGenerator.TIPLER.stack)
                    .EUt(VA[UXV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // endregion

    }

    // @formatter:on

}