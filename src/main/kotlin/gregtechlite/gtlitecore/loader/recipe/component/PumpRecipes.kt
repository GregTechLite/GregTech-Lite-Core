package gregtechlite.gtlitecore.loader.recipe.component

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
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
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_OpV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_OpV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UXV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM
import gregtech.common.items.MetaItems.TOOL_DATA_ORB
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter

internal object PumpRecipes
{

    // @formatter:off

    fun init()
    {
        // LuV
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_LuV.stackForm,
                OreDictUnifier.get(pipeSmallFluid, NiobiumTitanium),
                OreDictUnifier.get(plate, HSSS, 2),
                OreDictUnifier.get(screw, HSSS, 8),
                OreDictUnifier.get(ring, SiliconeRubber, 4),
                OreDictUnifier.get(rotor, HSSS),
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)),
            arrayOf(SolderingAlloy.getFluid(L), Lubricant.getFluid(250)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_LuV)
            .input(pipeNormalFluid, NiobiumTitanium)
            .input(plate, HSSS)
            .input(screw, HSSS, 4)
            .input("ringAnySyntheticRubber", 4)
            .input(rotor, HSSS)
            .input(cableGtSingle, NiobiumTitanium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .fluidInputs(Lubricant.getFluid(250))
            .output(ELECTRIC_PUMP_LuV)
            .EUt(6000) // IV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(ELECTRIC_PUMP_IV)
                    .EUt(VA[HV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // ZPM
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_ZPM.stackForm,
                OreDictUnifier.get(pipeNormalFluid, Polybenzimidazole),
                OreDictUnifier.get(plate, Osmiridium, 2),
                OreDictUnifier.get(screw, Osmiridium, 8),
                OreDictUnifier.get(ring, SiliconeRubber, 8),
                OreDictUnifier.get(rotor, Osmiridium),
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)),
            arrayOf(SolderingAlloy.getFluid(L * 2), Lubricant.getFluid(500)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_ZPM)
            .input(pipeNormalFluid, Iridium)
            .input(plate, Osmiridium)
            .input(screw, Osmiridium, 4)
            .input("ringAnySyntheticRubber", 6)
            .input(rotor, Osmiridium)
            .input(cableGtSingle, VanadiumGallium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .fluidInputs(Lubricant.getFluid(500))
            .output(ELECTRIC_PUMP_ZPM)
            .EUt(24000) // LuV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(ELECTRIC_PUMP_LuV)
                    .EUt(VA[IV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // UV
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            ELECTRIC_PUMP_ZPM.stackForm, TOOL_DATA_ORB.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_UV.stackForm,
                OreDictUnifier.get(pipeLargeFluid, Naquadah),
                OreDictUnifier.get(plate, Tritanium, 2),
                OreDictUnifier.get(screw, Tritanium, 8),
                OreDictUnifier.get(ring, SiliconeRubber, 16),
                OreDictUnifier.get(rotor, NaquadahAlloy),
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)),
            arrayOf(SolderingAlloy.getFluid(L * 4), Lubricant.getFluid(1000), Naquadria.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UV)
            .input(pipeLargeFluid, Naquadah)
            .input(plate, Tritanium, 2)
            .input(screw, Tritanium, 8)
            .input("ringAnySyntheticRubber", 8)
            .input(rotor, Tritanium)
            .input(cableGtSingle, YttriumBariumCuprate, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .fluidInputs(Lubricant.getFluid(1000))
            .fluidInputs(Naquadria.getFluid(L))
            .output(ELECTRIC_PUMP_UV)
            .EUt(100_000) // ZPM
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PUMP_ZPM)
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // UHV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UHV)
            .input(pipeLargeFluid, Europium)
            .input(plate, Adamantium, 2)
            .input(screw, Adamantium, 8)
            .input("ringAnyAdvancedSyntheticRubber", 12)
            .input(rotor, Adamantium)
            .input(cableGtSingle, Europium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .fluidInputs(Lubricant.getFluid(2000))
            .fluidInputs(Taranium.getFluid(L * 2))
            .output(ELECTRIC_PUMP_UHV)
            .EUt(400_000) // UV
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PUMP_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UEV)
            .input(pipeLargeFluid, Duranium, 2)
            .input(plate, CosmicNeutronium, 4)
            .input(screw, CosmicNeutronium, 8)
            .input("ringAnyAdvancedSyntheticRubber", 24)
            .input(rotor, CosmicNeutronium)
            .input(cableGtSingle, Seaborgium, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .fluidInputs(Fullerene.getFluid(L))
            .output(ELECTRIC_PUMP_UEV)
            .EUt(1_800_000) // UHV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PUMP_UHV)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UIV)
            .input(pipeLargeFluid, Neutronium, 2)
            .input(plate, HeavyQuarkDegenerateMatter, 4)
            .input(screw, HeavyQuarkDegenerateMatter, 8)
            .input("ringAnyAdvancedSyntheticRubber", 32)
            .input(rotor, HeavyQuarkDegenerateMatter)
            .input(cableGtSingle, SuperheavyAlloyA, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 32))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8))
            .fluidInputs(CarbonNanotube.getFluid(L * 2))
            .output(ELECTRIC_PUMP_UIV)
            .EUt(6_000_000) // UEV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PUMP_UEV)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UXV)
            .input(pipeLargeFluid, HeavyQuarkDegenerateMatter, 4)
            .input(plate, TranscendentMetal, 8)
            .input(screw, TranscendentMetal, 16)
            .input("ringAnyAdvancedSyntheticRubber", 64)
            .input(rotor, TranscendentMetal, 2)
            .input(cableGtSingle, SuperheavyAlloyB, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .fluidInputs(PrimordialMatter.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(ELECTRIC_PUMP_UXV)
            .EUt(20_000_000) // UIV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PUMP_UIV)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_OpV)
            .input(pipeLargeFluid, QuantumchromodynamicallyConfinedMatter, 4)
            .input(plate, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            .input(screw, MagnetohydrodynamicallyConstrainedStarMatter, 16)
            .input("ringAnyAdvancedSyntheticRubber", 64)
            .input("ringAnyAdvancedSyntheticRubber", 64)
            .input(rotor, MagnetohydrodynamicallyConstrainedStarMatter, 2)
            .input(cableGtSingle, Periodicium, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(32000))
            .fluidInputs(WhiteDwarfMatter.getFluid(L * 32))
            .fluidInputs(CosmicFabric.getFluid(L * 8))
            .output(ELECTRIC_PUMP_OpV)
            .EUt(50_000_000) // UXV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PUMP_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // TODO MAX
    }

    // @formatter:on

}