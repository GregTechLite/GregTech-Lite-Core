package gregtechlite.gtlitecore.loader.recipe.component

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
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
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_OpV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtech.common.items.MetaItems.QUANTUM_STAR
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.SENSOR_LuV
import gregtech.common.items.MetaItems.SENSOR_OpV
import gregtech.common.items.MetaItems.SENSOR_UEV
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.SENSOR_UIV
import gregtech.common.items.MetaItems.SENSOR_UV
import gregtech.common.items.MetaItems.SENSOR_UXV
import gregtech.common.items.MetaItems.SENSOR_ZPM
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HarmonicPhononMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ZENITH_STAR

internal object SensorRecipes
{

    // @formatter:off

    fun init()
    {
        // LuV
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, HSSS),
                ELECTRIC_MOTOR_LuV.stackForm,
                OreDictUnifier.get(plate, Ruridit, 4),
                QUANTUM_STAR.stackForm,
                OreDictUnifier.get(circuit, Tier.LuV, 2),
                OreDictUnifier.get(foil, Palladium, 64),
                OreDictUnifier.get(foil, Palladium, 32),
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 2)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HSSS)
            .input(ELECTRIC_MOTOR_LuV)
            .input(plate, Ruridit, 2)
            .input(QUANTUM_STAR)
            .input(circuit, Tier.LuV, 2)
            .input(foil, Palladium, 64)
            .input(cableGtSingle, NiobiumTitanium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .output(SENSOR_LuV)
            .EUt(6000) // IV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(SENSOR_IV)
                    .EUt(VA[HV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // ZPM
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, NaquadahAlloy),
                ELECTRIC_MOTOR_ZPM.stackForm,
                OreDictUnifier.get(plate, Osmiridium, 4),
                QUANTUM_STAR.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.ZPM, 2),
                OreDictUnifier.get(foil, Trinium, 64),
                OreDictUnifier.get(foil, Trinium, 32),
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Osmiridium)
            .input(ELECTRIC_MOTOR_ZPM)
            .input(plate, NaquadahAlloy, 2)
            .input(QUANTUM_STAR, 2)
            .input(circuit, Tier.ZPM, 2)
            .input(foil, NaquadahEnriched, 64)
            .input(foil, NaquadahEnriched, 32)
            .input(cableGtSingle, VanadiumGallium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .output(SENSOR_ZPM)
            .EUt(24000) // LuV
            .duration(20 * SECOND)
            .stationResearch { r -> r
                .researchStack(SENSOR_LuV)
                .EUt(VA[LuV])
                .CWUt(4)
            }
            .buildAndRegister()

        // UV
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            SENSOR_ZPM.stackForm, TOOL_DATA_MODULE.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, Tritanium),
                ELECTRIC_MOTOR_UV.stackForm,
                OreDictUnifier.get(plate, Tritanium, 4),
                GRAVI_STAR.stackForm,
                OreDictUnifier.get(circuit, Tier.UV, 2),
                OreDictUnifier.get(foil, Naquadria, 64),
                OreDictUnifier.get(foil, Naquadria, 32),
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 8), Naquadria.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Tritanium)
            .input(ELECTRIC_MOTOR_UV)
            .input(plate, EnrichedNaquadahAlloy, 4)
            .input(QUANTUM_STAR, 4)
            .input(circuit, Tier.UV, 2)
            .input(foil, Rutherfordium, 64)
            .input(foil, Rutherfordium, 64)
            .input(cableGtSingle, YttriumBariumCuprate, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .fluidInputs(Naquadria.getFluid(L))
            .output(SENSOR_UV)
            .EUt(100_000) // ZPM
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(SENSOR_ZPM)
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // UHV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Adamantium)
            .input(ELECTRIC_MOTOR_UHV)
            .input(plate, Dubnium, 4)
            .input(GRAVI_STAR)
            .input(circuit, Tier.UHV, 2)
            .input(foil, Neutronium, 64)
            .input(foil, Neutronium, 64)
            .input(foil, Neutronium, 32)
            .input(cableGtSingle, Europium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 12))
            .fluidInputs(Taranium.getFluid(L * 2))
            .output(SENSOR_UHV)
            .EUt(400_000) // UV
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(SENSOR_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, CosmicNeutronium)
            .input(ELECTRIC_MOTOR_UEV)
            .input(plate, Bohrium, 8)
            .input(GRAVI_STAR, 2)
            .input(circuit, Tier.UEV, 2)
            .input(foil, Infinity, 64)
            .input(foil, Infinity, 64)
            .input(foil, Infinity, 64)
            .input(cableGtSingle, Seaborgium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .fluidInputs(Fullerene.getFluid(L))
            .output(SENSOR_UEV)
            .EUt(1_800_000) // UHV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(SENSOR_UHV)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HeavyQuarkDegenerateMatter)
            .input(ELECTRIC_MOTOR_UIV)
            .input(plate, Shirabon, 8)
            .input(GRAVI_STAR, 4)
            .input(circuit, Tier.UIV, 2)
            .input(foil, HalkoniteSteel, 64)
            .input(foil, HalkoniteSteel, 64)
            .input(foil, HalkoniteSteel, 64)
            .input(foil, HalkoniteSteel, 32)
            .input(cableGtSingle, SuperheavyAlloyA, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 32))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8))
            .fluidInputs(CarbonNanotube.getFluid(L * 2))
            .output(SENSOR_UIV)
            .EUt(6_000_000) // UEV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(SENSOR_UEV)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, TranscendentMetal)
            .input(ELECTRIC_MOTOR_UXV)
            .input(plate, Mellion, 16)
            .input(ZENITH_STAR)
            .input(circuit, Tier.UXV, 2)
            .input(foil, HarmonicPhononMatter, 64)
            .input(foil, HarmonicPhononMatter, 64)
            .input(foil, HarmonicPhononMatter, 64)
            .input(foil, HarmonicPhononMatter, 64)
            .input(cableGtSingle, SuperheavyAlloyB, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(PrimordialMatter.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(SENSOR_UXV)
            .EUt(20_000_000) // UIV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(SENSOR_UIV)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, MagnetohydrodynamicallyConstrainedStarMatter)
            .input(ELECTRIC_MOTOR_OpV)
            .input(plate, BlackDwarfMatter, 16)
            .input(ZENITH_STAR, 2)
            .input(circuit, Tier.OpV, 2)
            .input(foil, Eternity, 64)
            .input(foil, Eternity, 64)
            .input(foil, Eternity, 64)
            .input(foil, Eternity, 64)
            .input(foil, Eternity, 32)
            .input(cableGtSingle, Periodicium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(WhiteDwarfMatter.getFluid(L * 32))
            .fluidInputs(CosmicFabric.getFluid(L * 8))
            .output(SENSOR_OpV)
            .EUt(50_000_000) // UXV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(SENSOR_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // TODO MAX
    }

    // @formatter:on

}