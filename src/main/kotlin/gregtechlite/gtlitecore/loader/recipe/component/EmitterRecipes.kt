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
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Ruridit
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
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_OpV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.EMITTER_LuV
import gregtech.common.items.MetaItems.EMITTER_OpV
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_UIV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.EMITTER_UXV
import gregtech.common.items.MetaItems.EMITTER_ZPM
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtech.common.items.MetaItems.QUANTUM_STAR
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.TOOL_DATA_ORB
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableFlerovium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ZENITH_STAR

internal object EmitterRecipes
{

    // @formatter:off

    fun init()
    {
        // LuV
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, HSSS),
                ELECTRIC_MOTOR_LuV.stackForm,
                OreDictUnifier.get(stickLong, Ruridit, 4),
                QUANTUM_STAR.stackForm,
                OreDictUnifier.get(circuit, Tier.LuV, 2),
                OreDictUnifier.get(foil, Palladium, 64),
                OreDictUnifier.get(foil, Palladium, 32),
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 2)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HSSS)
            .input(ELECTRIC_MOTOR_LuV)
            .input(stickLong, Ruridit, 2)
            .input(QUANTUM_STAR)
            .input(circuit, Tier.LuV, 2)
            .input(foil, Osmium, 64)
            .input(cableGtSingle, NiobiumTitanium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .output(EMITTER_LuV)
            .EUt(6000) // IV
            .duration(20 * SECOND)
            .scannerResearch {
                it.researchStack(EMITTER_IV)
                    .EUt(VA[HV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // ZPM
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            EMITTER_LuV.stackForm, TOOL_DATA_ORB.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, NaquadahAlloy),
                ELECTRIC_MOTOR_ZPM.stackForm,
                OreDictUnifier.get(stickLong, Osmiridium, 4),
                QUANTUM_STAR.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.ZPM, 2),
                OreDictUnifier.get(foil, Trinium, 64),
                OreDictUnifier.get(foil, Trinium, 32),
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Osmiridium)
            .input(ELECTRIC_MOTOR_ZPM)
            .input(stickLong, NaquadahAlloy, 2)
            .input(QUANTUM_STAR, 2)
            .input(circuit, Tier.ZPM, 2)
            .input(foil, Trinium, 64)
            .input(foil, Trinium, 32)
            .input(cableGtSingle, VanadiumGallium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .output(EMITTER_ZPM)
            .EUt(24000) // LuV
            .duration(20 * SECOND)
            .stationResearch {
                it.researchStack(EMITTER_LuV)
                    .EUt(VA[LuV])
                    .CWUt(4)
            }
            .buildAndRegister()

        // UV
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            EMITTER_ZPM.stackForm, TOOL_DATA_MODULE.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, Tritanium),
                ELECTRIC_MOTOR_UV.stackForm,
                OreDictUnifier.get(stickLong, Tritanium, 4),
                GRAVI_STAR.stackForm,
                OreDictUnifier.get(circuit, Tier.UV, 2),
                OreDictUnifier.get(foil, Naquadria, 64),
                OreDictUnifier.get(foil, Naquadria, 32),
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 8), Naquadria.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Tritanium)
            .input(ELECTRIC_MOTOR_UV)
            .input(stickLong, EnrichedNaquadahAlloy, 4)
            .input(QUANTUM_STAR, 4)
            .input(circuit, Tier.UV, 2)
            .input(foil, Naquadria, 64)
            .input(foil, Naquadria, 64)
            .input(cableGtSingle, YttriumBariumCuprate, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .fluidInputs(Naquadria.getFluid(L))
            .output(EMITTER_UV)
            .EUt(100_000) // ZPM
            .duration(20 * SECOND)
            .stationResearch {
                it.researchStack(EMITTER_ZPM)
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // UHV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Adamantium)
            .input(ELECTRIC_MOTOR_UHV)
            .input(stickLong, Dubnium, 4)
            .input(GRAVI_STAR)
            .input(circuit, Tier.UHV, 2)
            .input(foil, Vibranium, 64)
            .input(foil, Vibranium, 64)
            .input(foil, Vibranium, 32)
            .input(cableGtSingle, Europium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 12))
            .fluidInputs(Taranium.getFluid(L * 2))
            .output(EMITTER_UHV)
            .EUt(400_000) // UV
            .duration(20 * SECOND)
            .stationResearch {
                it.researchStack(EMITTER_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, CosmicNeutronium)
            .input(ELECTRIC_MOTOR_UEV)
            .input(stickLong, Bohrium, 8)
            .input(GRAVI_STAR, 2)
            .input(circuit, Tier.UEV, 2)
            .input(foil, MetastableFlerovium, 64)
            .input(foil, MetastableFlerovium, 64)
            .input(foil, MetastableFlerovium, 64)
            .input(cableGtSingle, Seaborgium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .fluidInputs(Fullerene.getFluid(L))
            .output(EMITTER_UEV)
            .EUt(1_800_000) // UHV
            .duration(40 * SECOND)
            .stationResearch {
                it.researchStack(EMITTER_UHV)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HeavyQuarkDegenerateMatter)
            .input(ELECTRIC_MOTOR_UIV)
            .input(stickLong, Shirabon, 8)
            .input(GRAVI_STAR, 4)
            .input(circuit, Tier.UIV, 2)
            .input(foil, Rhugnor, 64)
            .input(foil, Rhugnor, 64)
            .input(foil, Rhugnor, 64)
            .input(foil, Rhugnor, 32)
            .input(cableGtSingle, SuperheavyAlloyA, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 32))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8))
            .fluidInputs(CarbonNanotube.getFluid(L * 2))
            .output(EMITTER_UIV)
            .EUt(6_000_000) // UEV
            .duration(40 * SECOND)
            .stationResearch {
                it.researchStack(EMITTER_UEV)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, TranscendentMetal)
            .input(ELECTRIC_MOTOR_UXV)
            .input(stickLong, Mellion, 16)
            .input(ZENITH_STAR)
            .input(circuit, Tier.UXV, 2)
            .input(foil, SpaceTime, 64)
            .input(foil, SpaceTime, 64)
            .input(foil, SpaceTime, 64)
            .input(foil, SpaceTime, 64)
            .input(cableGtSingle, SuperheavyAlloyB, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(PrimordialMatter.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(EMITTER_UXV)
            .EUt(20_000_000) // UIV
            .duration(1 * MINUTE)
            .stationResearch {
                it.researchStack(EMITTER_UIV)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, MagnetohydrodynamicallyConstrainedStarMatter)
            .input(ELECTRIC_MOTOR_OpV)
            .input(stickLong, BlackDwarfMatter, 16)
            .input(ZENITH_STAR, 2)
            .input(circuit, Tier.OpV, 2)
            .input(foil, WhiteDwarfMatter, 64)
            .input(foil, WhiteDwarfMatter, 64)
            .input(foil, WhiteDwarfMatter, 64)
            .input(foil, WhiteDwarfMatter, 64)
            .input(foil, WhiteDwarfMatter, 32)
            .input(cableGtSingle, Periodicium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(WhiteDwarfMatter.getFluid(L * 32))
            .fluidInputs(CosmicFabric.getFluid(L * 8))
            .output(EMITTER_OpV)
            .EUt(50_000_000) // UXV
            .duration(1 * MINUTE)
            .stationResearch {
                it.researchStack(EMITTER_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // TODO MAX
    }

    // @formatter:on

}