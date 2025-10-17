package gregtechlite.gtlitecore.loader.recipe.component

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.RESEARCH_STATION_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LuV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_OpV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UEV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UHV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UIV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UXV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_OpV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.TOOL_DATA_ORB
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Omnium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RealizedQuantumFoamShard
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONVEYOR_MODULE_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_MOTOR_MAX

internal object ConveyorRecipes
{

    // @formatter:off

    fun init()
    {
        // LuV
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_LuV.getStackForm(2),
                OreDictUnifier.get(plate, HSSS, 2),
                OreDictUnifier.get(ring, HSSS, 4),
                OreDictUnifier.get(round, HSSS, 16),
                OreDictUnifier.get(screw, HSSS, 4),
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)),
            arrayOf(SolderingAlloy.getFluid(L), Lubricant.getFluid(250),
                StyreneButadieneRubber.getFluid(L * 8)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_LuV, 2)
            .input(plate, HSSS)
            .input(ring, HSSS, 4)
            .input(round, HSSS, 8)
            .input(screw, HSSS, 4)
            .input(cableGtSingle, NiobiumTitanium, 2)
            .input("plateAnySyntheticRubber", 8)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .fluidInputs(Lubricant.getFluid(250))
            .output(CONVEYOR_MODULE_LuV)
            .EUt(6000) // IV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(CONVEYOR_MODULE_IV)
                    .EUt(VA[HV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // ZPM
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_ZPM.getStackForm(2),
                OreDictUnifier.get(plate, Osmiridium, 2),
                OreDictUnifier.get(ring, Osmiridium, 4),
                OreDictUnifier.get(round, Osmiridium, 16),
                OreDictUnifier.get(screw, Osmiridium, 4),
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)),
            arrayOf(SolderingAlloy.getFluid(L * 2), Lubricant.getFluid(500), StyreneButadieneRubber.getFluid(L * 16)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_ZPM, 2)
            .input(plate, Osmiridium)
            .input(ring, Osmiridium, 4)
            .input(round, Osmiridium, 8)
            .input(screw, Osmiridium, 4)
            .input(cableGtSingle, VanadiumGallium, 2)
            .input("plateAnySyntheticRubber", 10)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .fluidInputs(Lubricant.getFluid(500))
            .output(CONVEYOR_MODULE_ZPM)
            .EUt(24000) // LuV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(CONVEYOR_MODULE_LuV)
                    .EUt(VA[IV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // UV
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            CONVEYOR_MODULE_ZPM.stackForm, TOOL_DATA_ORB.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_UV.getStackForm(2),
                OreDictUnifier.get(plate, Tritanium, 2),
                OreDictUnifier.get(ring, Tritanium, 4),
                OreDictUnifier.get(round, Tritanium, 16),
                OreDictUnifier.get(screw, Tritanium, 4),
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)),
            arrayOf(SolderingAlloy.getFluid(L * 4), Lubricant.getFluid(1000),
                StyreneButadieneRubber.getFluid(L * 24), Naquadria.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UV, 2)
            .input(plate, Tritanium, 2)
            .input(ring, Tritanium, 8)
            .input(round, Tritanium, 16)
            .input(screw, Tritanium, 8)
            .input(cableGtSingle, YttriumBariumCuprate, 2)
            .input("plateAnySyntheticRubber", 12)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .fluidInputs(Lubricant.getFluid(1000))
            .fluidInputs(Naquadria.getFluid(L))
            .output(CONVEYOR_MODULE_UV)
            .EUt(100_000) // ZPM
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(CONVEYOR_MODULE_ZPM)
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // UHV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UHV, 2)
            .input(plate, Adamantium, 2)
            .input(ring, Adamantium, 8)
            .input(round, Adamantium, 16)
            .input(screw, Adamantium, 8)
            .input(cableGtSingle, Europium, 2)
            .input("plateAnyAdvancedSyntheticRubber", 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .fluidInputs(Lubricant.getFluid(2000))
            .fluidInputs(Taranium.getFluid(L * 2))
            .output(CONVEYOR_MODULE_UHV)
            .EUt(400_000) // UV
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(CONVEYOR_MODULE_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UEV, 2)
            .input(plate, CosmicNeutronium, 4)
            .input(ring, CosmicNeutronium, 16)
            .input(round, CosmicNeutronium, 32)
            .input(screw, CosmicNeutronium, 8)
            .input(cableGtSingle, Seaborgium, 2)
            .input("plateAnyAdvancedSyntheticRubber", 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .fluidInputs(Fullerene.getFluid(L))
            .output(CONVEYOR_MODULE_UEV)
            .EUt(1_800_000) // UHV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(CONVEYOR_MODULE_UHV)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UIV, 2)
            .input(plate, HeavyQuarkDegenerateMatter, 4)
            .input(ring, HeavyQuarkDegenerateMatter, 16)
            .input(round, HeavyQuarkDegenerateMatter, 32)
            .input(screw, HeavyQuarkDegenerateMatter, 8)
            .input(cableGtSingle, SuperheavyAlloyA, 2)
            .input("plateAnyAdvancedSyntheticRubber", 48)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 32))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8))
            .fluidInputs(CarbonNanotube.getFluid(L * 2))
            .output(CONVEYOR_MODULE_UIV)
            .EUt(6_000_000) // UEV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(CONVEYOR_MODULE_UEV)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UXV, 2)
            .input(plate, TranscendentMetal, 8)
            .input(ring, TranscendentMetal, 32)
            .input(round, TranscendentMetal, 64)
            .input(screw, TranscendentMetal, 16)
            .input(cableGtSingle, SuperheavyAlloyB, 2)
            .input("plateAnyAdvancedSyntheticRubber", 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .fluidInputs(PrimordialMatter.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(CONVEYOR_MODULE_UXV)
            .EUt(20_000_000) // UIV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(CONVEYOR_MODULE_UIV)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_OpV, 2)
            .input(plate, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            .input(ring, MagnetohydrodynamicallyConstrainedStarMatter, 32)
            .input(round, MagnetohydrodynamicallyConstrainedStarMatter, 64)
            .input(screw, MagnetohydrodynamicallyConstrainedStarMatter, 16)
            .input(cableGtSingle, Periodicium, 2)
            .input("plateAnyAdvancedSyntheticRubber", 64)
            .input("plateAnyAdvancedSyntheticRubber", 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(32000))
            .fluidInputs(WhiteDwarfMatter.getFluid(L * 32))
            .fluidInputs(CosmicFabric.getFluid(L * 8))
            .output(CONVEYOR_MODULE_OpV)
            .EUt(50_000_000) // UXV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(CONVEYOR_MODULE_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // MAX
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_MAX, 2)
            .input(plateDouble, Omnium, 16)
            .input(plateDouble, Eternity, 16)
            .input(plateDouble, Universium, 16)
            .input(plateDouble, SpaceTime, 16)
            .input(ring, Omnium, 64)
            .input(ring, WhiteDwarfMatter, 64)
            .input(round, Omnium, 64)
            .input(round, BlackDwarfMatter, 64)
            .input(screw, Omnium, 32)
            .input(cableGtHex, RealizedQuantumFoamShard, 2)
            .input(nanite, MagMatter, 4)
            .input("plateAnyAdvancedSyntheticRubber", 64)
            .input("plateAnyAdvancedSyntheticRubber", 64)
            .input("plateAnyAdvancedSyntheticRubber", 64)
            .input("plateAnyAdvancedSyntheticRubber", 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(64000))
            .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 64))
            .fluidInputs(CosmicFabric.getFluid(L * 16))
            .output(CONVEYOR_MODULE_MAX)
            .EUt(300_000_000) // OpV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(CONVEYOR_MODULE_OpV)
                    .EUt(VA[OpV])
                    .CWUt(256)
            }
            .buildAndRegister()

    }

    // @formatter:on

}