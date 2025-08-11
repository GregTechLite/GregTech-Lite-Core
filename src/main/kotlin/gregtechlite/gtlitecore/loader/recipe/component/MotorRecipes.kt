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
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.SamariumMagnetic
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
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
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromiumGermaniumTellurideMagnetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENERGISED_TESSERACT

internal object MotorRecipes
{

    // @formatter:off

    fun init()
    {

        // LuV
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(stickLong, SamariumMagnetic),
                OreDictUnifier.get(stickLong, HSSS, 2),
                OreDictUnifier.get(ring, HSSS, 2),
                OreDictUnifier.get(round, HSSS, 4),
                OreDictUnifier.get(wireFine, Ruridit, 64),
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)),
            arrayOf(SolderingAlloy.getFluid(L),
                Lubricant.getFluid(250)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, SamariumMagnetic)
            .input(stickLong, HSSS)
            .input(ring, HSSS, 2)
            .input(round, HSSS, 4)
            .input(wireFine, Ruridit, 64)
            .input(cableGtSingle, NiobiumTitanium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .fluidInputs(Lubricant.getFluid(250))
            .output(ELECTRIC_MOTOR_LuV)
            .EUt(6000) // IV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(ELECTRIC_MOTOR_IV)
                    .EUt(VA[HV])
                    .duration(30 * SECOND)
            }
            .buildAndRegister()

        // ZPM
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(stickLong, SamariumMagnetic),
                OreDictUnifier.get(stickLong, Osmiridium, 4),
                OreDictUnifier.get(ring, Osmiridium, 4),
                OreDictUnifier.get(round, Osmiridium, 8),
                OreDictUnifier.get(wireFine, Europium, 64),
                OreDictUnifier.get(wireFine, Europium, 32),
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)),
            arrayOf(SolderingAlloy.getFluid(L * 2), Lubricant.getFluid(500)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, SamariumMagnetic)
            .input(stickLong, Osmiridium)
            .input(ring, Osmiridium, 2)
            .input(round, Osmiridium, 4)
            .input(wireFine, Europium, 64)
            .input(wireFine, Europium, 32)
            .input(cableGtSingle, VanadiumGallium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .fluidInputs(Lubricant.getFluid(500))
            .output(ELECTRIC_MOTOR_ZPM)
            .EUt(24000) // LuV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(ELECTRIC_MOTOR_LuV)
                    .EUt(VA[IV])
                    .duration(MINUTE)
            }
            .buildAndRegister()

        // UV
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            ELECTRIC_MOTOR_ZPM.stackForm, TOOL_DATA_ORB.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(stickLong, SamariumMagnetic),
                OreDictUnifier.get(stickLong, Tritanium, 4),
                OreDictUnifier.get(ring, Tritanium, 4),
                OreDictUnifier.get(round, Tritanium, 8),
                OreDictUnifier.get(wireFine, Americium, 64),
                OreDictUnifier.get(wireFine, Americium, 64),
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)),
            arrayOf(SolderingAlloy.getFluid(L * 4),
                Lubricant.getFluid(1000), Naquadria.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, SamariumMagnetic)
            .input(stickLong, Tritanium, 2)
            .input(ring, Tritanium, 4)
            .input(round, Tritanium, 8)
            .input(wireFine, Americium, 64)
            .input(wireFine, Americium, 64)
            .input(cableGtSingle, YttriumBariumCuprate, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .fluidInputs(Lubricant.getFluid(1000))
            .fluidInputs(Naquadria.getFluid(L))
            .output(ELECTRIC_MOTOR_UV)
            .EUt(100_000) // ZPM
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_MOTOR_ZPM)
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // UHV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, ChromiumGermaniumTellurideMagnetic)
            .input(stickLong, Adamantium, 2)
            .input(ring, Adamantium, 4)
            .input(round, Adamantium, 8)
            .input(wireFine, Bedrockium, 64)
            .input(wireFine, Bedrockium, 64)
            .input(wireFine, Bedrockium, 32)
            .input(cableGtSingle, Europium, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .fluidInputs(Lubricant.getFluid(2000))
            .fluidInputs(Taranium.getFluid(L * 2))
            .output(ELECTRIC_MOTOR_UHV)
            .EUt(400_000) // UV
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_MOTOR_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, ChromiumGermaniumTellurideMagnetic)
            .input(stickLong, CosmicNeutronium, 4)
            .input(ring, CosmicNeutronium, 8)
            .input(round, CosmicNeutronium, 16)
            .input(wireFine, MetastableHassium, 64)
            .input(wireFine, MetastableHassium, 64)
            .input(wireFine, MetastableHassium, 64)
            .input(cableGtSingle, Seaborgium, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .fluidInputs(Fullerene.getFluid(L))
            .output(ELECTRIC_MOTOR_UEV)
            .EUt(1_800_000) // UHV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_MOTOR_UHV)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, Magnetium)
            .input(stickLong, HeavyQuarkDegenerateMatter, 4)
            .input(ring, HeavyQuarkDegenerateMatter, 8)
            .input(round, HeavyQuarkDegenerateMatter, 16)
            .input(wireFine, Hypogen, 64)
            .input(wireFine, Hypogen, 64)
            .input(wireFine, Hypogen, 64)
            .input(wireFine, Hypogen, 32)
            .input(cableGtSingle, SuperheavyAlloyA, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 32))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8))
            .fluidInputs(CarbonNanotube.getFluid(L * 2))
            .output(ELECTRIC_MOTOR_UIV)
            .EUt(6_000_000) // UEV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_MOTOR_UEV)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ENERGISED_TESSERACT)
            .input(stickLong, Magnetium, 2)
            .input(stickLong, TranscendentMetal, 8)
            .input(ring, TranscendentMetal, 16)
            .input(round, TranscendentMetal, 32)
            .input(wireFine, MagMatter, 64)
            .input(wireFine, MagMatter, 64)
            .input(wireFine, MagMatter, 64)
            .input(wireFine, MagMatter, 64)
            .input(cableGtSingle, SuperheavyAlloyB, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .fluidInputs(PrimordialMatter.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(ELECTRIC_MOTOR_UXV)
            .EUt(20_000_000) // UIV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_MOTOR_UIV)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ENERGISED_TESSERACT)
            .input(stickLong, Magnetium, 2)
            .input(stickLong, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            .input(ring, MagnetohydrodynamicallyConstrainedStarMatter, 16)
            .input(round, MagnetohydrodynamicallyConstrainedStarMatter, 32)
            .input(wireFine, Universium, 64)
            .input(wireFine, Universium, 64)
            .input(wireFine, Universium, 64)
            .input(wireFine, Universium, 64)
            .input(wireFine, Universium, 64)
            .input(wireFine, Universium, 64)
            .input(cableGtSingle, Periodicium, 2)
            .input(nanite, Neutronium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(32000))
            .fluidInputs(WhiteDwarfMatter.getFluid(L * 32))
            .fluidInputs(CosmicFabric.getFluid(L * 8))
            .output(ELECTRIC_MOTOR_OpV)
            .EUt(50_000_000) // UXV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ELECTRIC_MOTOR_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // TODO MAX
        // ASSEMBLY_LINE_RECIPES.recipeBuilder()
        //     .input(ENERGISED_TESSERACT)
        //     .input(frameGt, Magnetium, 64)
        //     .input(frameGt, Eternity, 64)
        //     .input(frameGt, BlackDwarfMatter, 64)
        //     .input(frameGt, Omnium, 64)
        //     .input(ring, Omnium, 32)
        //     .input(round, Omnium, 48)
        //     .input(wireFine, SpaceTime, 64)
        //     .input(wireFine, SpaceTime, 64)
        //     .input(wireFine, Universium, 64)
        //     .input(wireFine, Universium, 64)
        //     .input(wireFine, OpVSuperconductors, 64)
        //     .input(wireFine, OpVSuperconductors, 64)
        //     .input(wireFine, WhiteDwarfMatter, 64)
        //     .input(wireFine, WhiteDwarfMatter, 64)
        //     .input(cableGtHex, , 2)
        //     .input(nanite, MagMatter, 4)
    }

    // @formatter:on

}