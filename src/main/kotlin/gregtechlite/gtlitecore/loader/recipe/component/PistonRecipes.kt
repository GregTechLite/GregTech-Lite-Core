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
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_OpV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_OpV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UXV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
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
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_MOTOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PISTON_MAX

internal object PistonRecipes
{

    // @formatter:off

    fun init()
    {
        // LuV
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_LuV.stackForm,
                OreDictUnifier.get(plate, HSSS, 4),
                OreDictUnifier.get(ring, HSSS, 4),
                OreDictUnifier.get(round, HSSS, 16),
                OreDictUnifier.get(stick, HSSS, 4),
                OreDictUnifier.get(gear, HSSS, 1),
                OreDictUnifier.get(gearSmall, HSSS, 2),
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)),
            arrayOf(SolderingAlloy.getFluid(L), Lubricant.getFluid(250)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_LuV)
            .input(plate, HSSS)
            .input(ring, HSSS, 2)
            .input(round, HSSS, 4)
            .input(stick, HSSS, 2)
            .input(gear, HSSS)
            .input(gearSmall, HSSS, 2)
            .input(cableGtSingle, NiobiumTitanium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .fluidInputs(Lubricant.getFluid(250))
            .output(ELECTRIC_PISTON_LUV)
            .EUt(6000) // IV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(ELECTRIC_PISTON_IV)
                    .EUt(VA[HV])
                    .duration(30 * SECOND)
            }
            .buildAndRegister()

        // ZPM
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_ZPM.stackForm,
                OreDictUnifier.get(plate, Osmiridium, 4),
                OreDictUnifier.get(ring, Osmiridium, 4),
                OreDictUnifier.get(round, Osmiridium, 16),
                OreDictUnifier.get(stick, Osmiridium, 4),
                OreDictUnifier.get(gear, Osmiridium, 1),
                OreDictUnifier.get(gearSmall, Osmiridium, 2),
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)),
            arrayOf(SolderingAlloy.getFluid(L * 2), Lubricant.getFluid(500)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_ZPM)
            .input(plate, Osmiridium)
            .input(ring, Osmiridium, 2)
            .input(round, Osmiridium, 4)
            .input(stick, Osmiridium, 2)
            .input(gear, Osmiridium)
            .input(gearSmall, Osmiridium, 2)
            .input(cableGtSingle, VanadiumGallium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .fluidInputs(Lubricant.getFluid(500))
            .output(ELECTRIC_PISTON_ZPM)
            .EUt(24000) // LuV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(ELECTRIC_PISTON_LUV)
                    .EUt(VA[IV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // UV
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            ELECTRIC_MOTOR_ZPM.stackForm, TOOL_DATA_ORB.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(ELECTRIC_MOTOR_UV.stackForm,
                OreDictUnifier.get(plate, Tritanium, 4),
                OreDictUnifier.get(ring, Tritanium, 4),
                OreDictUnifier.get(round, Tritanium, 16),
                OreDictUnifier.get(stick, Tritanium, 4),
                OreDictUnifier.get(gear, NaquadahAlloy, 1),
                OreDictUnifier.get(gearSmall, NaquadahAlloy, 2),
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)),
            arrayOf(SolderingAlloy.getFluid(L * 4), Lubricant.getFluid(1000),
                Naquadria.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UV)
            .input(plate, Tritanium, 2)
            .input(ring, Tritanium, 4)
            .input(round, Tritanium, 8)
            .input(stick, Tritanium, 4)
            .input(gear, Tritanium)
            .input(gearSmall, Tritanium, 2)
            .input(cableGtSingle, YttriumBariumCuprate, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .fluidInputs(Lubricant.getFluid(1000))
            .fluidInputs(Naquadria.getFluid(L))
            .output(ELECTRIC_PISTON_UV)
            .EUt(100_000) // ZPM
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PISTON_ZPM)
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // UHV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UHV)
            .input(plate, Adamantium, 2)
            .input(ring, Adamantium, 4)
            .input(round, Adamantium, 8)
            .input(stick, Adamantium, 4)
            .input(gear, Adamantium)
            .input(gearSmall, Adamantium, 2)
            .input(cableGtSingle, Europium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .fluidInputs(Lubricant.getFluid(2000))
            .fluidInputs(Taranium.getFluid(L * 2))
            .output(ELECTRIC_PISTON_UHV)
            .EUt(400_000) // UV
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PISTON_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UEV)
            .input(plate, CosmicNeutronium, 4)
            .input(ring, CosmicNeutronium, 8)
            .input(round, CosmicNeutronium, 16)
            .input(stick, CosmicNeutronium, 4)
            .input(gear, CosmicNeutronium)
            .input(gearSmall, CosmicNeutronium, 2)
            .input(cableGtSingle, Seaborgium, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .fluidInputs(Fullerene.getFluid(L))
            .output(ELECTRIC_PISTON_UEV)
            .EUt(1_800_000) // UHV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PISTON_UHV)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UIV)
            .input(plate, HeavyQuarkDegenerateMatter, 4)
            .input(ring, HeavyQuarkDegenerateMatter, 8)
            .input(round, HeavyQuarkDegenerateMatter, 16)
            .input(stick, HeavyQuarkDegenerateMatter, 4)
            .input(gear, HeavyQuarkDegenerateMatter)
            .input(gearSmall, HeavyQuarkDegenerateMatter, 2)
            .input(cableGtSingle, SuperheavyAlloyA, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 32))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8))
            .fluidInputs(CarbonNanotube.getFluid(L * 2))
            .output(ELECTRIC_PISTON_UIV)
            .EUt(6_000_000) // UEV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PISTON_UEV)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_UXV)
            .input(plate, TranscendentMetal, 8)
            .input(ring, TranscendentMetal, 16)
            .input(round, TranscendentMetal, 32)
            .input(stick, TranscendentMetal, 8)
            .input(gear, TranscendentMetal, 2)
            .input(gearSmall, TranscendentMetal, 4)
            .input(cableGtSingle, SuperheavyAlloyB, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .fluidInputs(PrimordialMatter.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(ELECTRIC_PISTON_UXV)
            .EUt(20_000_000) // UIV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PISTON_UIV)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_OpV)
            .input(plate, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            .input(ring, MagnetohydrodynamicallyConstrainedStarMatter, 16)
            .input(round, MagnetohydrodynamicallyConstrainedStarMatter, 32)
            .input(stick, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            .input(gear, MagnetohydrodynamicallyConstrainedStarMatter, 2)
            .input(gearSmall, MagnetohydrodynamicallyConstrainedStarMatter, 4)
            .input(cableGtSingle, Periodicium, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(32000))
            .fluidInputs(WhiteDwarfMatter.getFluid(L * 32))
            .fluidInputs(CosmicFabric.getFluid(L * 8))
            .output(ELECTRIC_PISTON_OpV)
            .EUt(50_000_000) // UXV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PISTON_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // MAX
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ELECTRIC_MOTOR_MAX)
            .input(plateDouble, Eternity, 16)
            .input(plateDouble, BlackDwarfMatter, 16)
            .input(plateDouble, Omnium, 16)
            .input(ring, Omnium, 32)
            .input(round, Omnium, 64)
            .input(frameGt, Omnium, 16)
            .input(frameGt, SpaceTime, 16)
            .input(gear, Omnium, 4)
            .input(gear, BlackDwarfMatter, 4)
            .input(gear, WhiteDwarfMatter, 4)
            .input(gearSmall, Omnium, 8)
            .input(gearSmall, Eternity, 8)
            .input(gearSmall, TranscendentMetal, 8)
            .input(cableGtHex, RealizedQuantumFoamShard, 2)
            .input(nanite, MagMatter, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(64000))
            .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 64))
            .fluidInputs(CosmicFabric.getFluid(L * 16))
            .output(ELECTRIC_PISTON_MAX)
            .EUt(300_000_000) // OpV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_PISTON_OpV)
                    .EUt(VA[OpV])
                    .CWUt(256)
            }
            .buildAndRegister()
    }

    // @formatter:on


}