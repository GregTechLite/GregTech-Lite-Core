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
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.UraniumRhodiumDinaquadide
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.EMITTER_LuV
import gregtech.common.items.MetaItems.EMITTER_OpV
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_UIV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.EMITTER_UXV
import gregtech.common.items.MetaItems.EMITTER_ZPM
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_OpV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UIV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UXV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtech.common.items.MetaItems.QUANTUM_STAR
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronFranciumCarbideSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutroniumSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ZENITH_STAR

internal object FieldGenRecipes
{

    // @formatter:off

    fun init()
    {
        // LuV
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, HSSS),
                OreDictUnifier.get(plate, HSSS, 6),
                QUANTUM_STAR.stackForm,
                EMITTER_LuV.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.LuV, 2),
                OreDictUnifier.get(wireFine, IndiumTinBariumTitaniumCuprate, 64),
                OreDictUnifier.get(wireFine, IndiumTinBariumTitaniumCuprate, 64),
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HSSS)
            .input(plateDouble, HSSS, 4)
            .input(QUANTUM_STAR)
            .input(EMITTER_LuV, 2)
            .input(circuit, Tier.LuV, 2)
            .input(wireFine, IndiumTinBariumTitaniumCuprate, 64)
            .input(cableGtSingle, NiobiumTitanium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .output(FIELD_GENERATOR_LuV)
            .EUt(6000) // IV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(FIELD_GENERATOR_IV)
                    .EUt(VA[HV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // ZPM
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, NaquadahAlloy),
                OreDictUnifier.get(plate, NaquadahAlloy, 6),
                QUANTUM_STAR.stackForm,
                EMITTER_ZPM.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.ZPM, 2),
                OreDictUnifier.get(wireFine, UraniumRhodiumDinaquadide, 64),
                OreDictUnifier.get(wireFine, UraniumRhodiumDinaquadide, 64),
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 8)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Osmiridium)
            .input(plateDouble, Osmiridium, 4)
            .input(QUANTUM_STAR, 2)
            .input(EMITTER_ZPM, 2)
            .input(circuit, Tier.ZPM, 2)
            .input(wireFine, UraniumRhodiumDinaquadide, 64)
            .input(wireFine, UraniumRhodiumDinaquadide, 32)
            .input(cableGtSingle, VanadiumGallium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .output(FIELD_GENERATOR_ZPM)
            .EUt(24000) // LuV
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(FIELD_GENERATOR_LuV)
                    .EUt(VA[LuV])
                    .CWUt(8)
            }
            .buildAndRegister()

        // UV
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            FIELD_GENERATOR_ZPM.stackForm, TOOL_DATA_MODULE.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, Tritanium),
                OreDictUnifier.get(plate, Tritanium, 6),
                GRAVI_STAR.stackForm,
                EMITTER_UV.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.UV, 2),
                OreDictUnifier.get(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 64),
                OreDictUnifier.get(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 64),
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 12), Naquadria.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Tritanium)
            .input(plateDouble, Tritanium, 4)
            .input(QUANTUM_STAR, 4)
            .input(EMITTER_UV, 2)
            .input(circuit, Tier.UV, 2)
            .input(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 64)
            .input(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 64)
            .input(cableGtSingle, YttriumBariumCuprate, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 12))
            .fluidInputs(Naquadria.getFluid(L))
            .output(FIELD_GENERATOR_UV)
            .EUt(100_000) // ZPM
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(FIELD_GENERATOR_ZPM)
                    .EUt(VA[ZPM])
                    .CWUt(48)
            }
            .buildAndRegister()

        // UHV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Adamantium)
            .input(plateDouble, Adamantium, 4)
            .input(GRAVI_STAR)
            .input(EMITTER_UHV, 2)
            .input(circuit, Tier.UHV, 2)
            .input(wireFine, RutheniumTriniumAmericiumNeutronate, 64)
            .input(wireFine, RutheniumTriniumAmericiumNeutronate, 64)
            .input(wireFine, RutheniumTriniumAmericiumNeutronate, 32)
            .input(cableGtSingle, Europium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .fluidInputs(Taranium.getFluid(L * 2))
            .output(FIELD_GENERATOR_UHV)
            .EUt(400_000) // UV
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(FIELD_GENERATOR_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, CosmicNeutronium)
            .input(plateDouble, CosmicNeutronium, 8)
            .input(GRAVI_STAR, 2)
            .input(EMITTER_UEV, 2)
            .input(circuit, Tier.UEV, 2)
            .input(wireFine, VibraniumTritaniumActiniumIronSuperhydride, 64)
            .input(wireFine, VibraniumTritaniumActiniumIronSuperhydride, 64)
            .input(wireFine, VibraniumTritaniumActiniumIronSuperhydride, 64)
            .input(cableGtSingle, Seaborgium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 32))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .fluidInputs(Fullerene.getFluid(L))
            .output(FIELD_GENERATOR_UEV)
            .EUt(1_800_000) // UHV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(FIELD_GENERATOR_UHV)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HeavyQuarkDegenerateMatter)
            .input(plateDouble, HeavyQuarkDegenerateMatter, 8)
            .input(GRAVI_STAR, 4)
            .input(EMITTER_UIV, 2)
            .input(circuit, Tier.UIV, 2)
            .input(wireFine, FullereneSuperconductor, 64)
            .input(wireFine, FullereneSuperconductor, 64)
            .input(wireFine, FullereneSuperconductor, 64)
            .input(wireFine, FullereneSuperconductor, 32)
            .input(cableGtSingle, SuperheavyAlloyA, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8))
            .fluidInputs(CarbonNanotube.getFluid(L * 2))
            .output(FIELD_GENERATOR_UIV)
            .EUt(6_000_000) // UEV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(FIELD_GENERATOR_UEV)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, TranscendentMetal)
            .input(plateDouble, TranscendentMetal, 16)
            .input(ZENITH_STAR)
            .input(EMITTER_UXV, 2)
            .input(circuit, Tier.UXV, 2)
            .input(wireFine, BoronFranciumCarbideSuperconductor, 64)
            .input(wireFine, BoronFranciumCarbideSuperconductor, 64)
            .input(wireFine, BoronFranciumCarbideSuperconductor, 64)
            .input(wireFine, BoronFranciumCarbideSuperconductor, 64)
            .input(cableGtSingle, SuperheavyAlloyB, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(PrimordialMatter.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(FIELD_GENERATOR_UXV)
            .EUt(20_000_000) // UIV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(FIELD_GENERATOR_UIV)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, MagnetohydrodynamicallyConstrainedStarMatter)
            .input(plateDouble, MagnetohydrodynamicallyConstrainedStarMatter, 16)
            .input(ZENITH_STAR, 2)
            .input(EMITTER_OpV, 2)
            .input(circuit, Tier.OpV, 2)
            .input(wireFine, NeutroniumSuperconductor, 64)
            .input(wireFine, NeutroniumSuperconductor, 64)
            .input(wireFine, NeutroniumSuperconductor, 64)
            .input(wireFine, NeutroniumSuperconductor, 64)
            .input(wireFine, NeutroniumSuperconductor, 32)
            .input(cableGtSingle, Periodicium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(WhiteDwarfMatter.getFluid(L * 32))
            .fluidInputs(CosmicFabric.getFluid(L * 8))
            .output(FIELD_GENERATOR_OpV)
            .EUt(50_000_000) // UXV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(FIELD_GENERATOR_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // TODO MAX
    }

    // @formatter:on

}