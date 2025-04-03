package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.SamariumMagnetic
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
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class AssemblyLineRecipes
{

    companion object
    {

        fun init()
        {
            componentRecipes()
            energyHatchesRecipes()
            laserHatchesRecipes()
        }

        private fun componentRecipes()
        {
            electricMotorRecipes()
            // ...
        }

        private fun electricMotorRecipes()
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
                    r.researchStack(ELECTRIC_MOTOR_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(MINUTE)
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
                    r.researchStack(ELECTRIC_MOTOR_LuV.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(MINUTE)
                }
                .buildAndRegister()

            // UV
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
                    r.researchStack(ELECTRIC_MOTOR_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(32)
                }
                .buildAndRegister()

            // UHV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(stickLong, ChromiumGermaniumTellurideMagnetic)
            //     .input(stickLong, Adamantium, 2)
            //     .input(ring, Adamantium, 4)
            //     .input(round, Adamantium, 8)
            //     .input(wireFine, ElectrumFluxed, 64)
            //     .input(wireFine, ElectrumFluxed, 64)
            //     .input(wireFine, ElectrumFluxed, 32)
            //     .input(cableGtSingle, Europium, 2)
            //     .fluidInputs(SolderingAlloy.getFluid(L * 8))
            //     .fluidInputs(Lubricant.getFluid(2000))
            //     .fluidInputs(Taranium.getFluid(L * 2))
            //     .output(ELECTRIC_MOTOR_UHV)
            //     .EUt(400_000) // UV
            //     .duration(20 * SECOND)
            //     .stationResearch { r ->
            //         r.researchStack(ELECTRIC_MOTOR_UV.stackForm)
            //             .EUt(VA[UV].toLong())
            //             .CWUt(48)
            //     }
            //     .buildAndRegister()

            // UEV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(stickLong, ChromiumGermaniumTellurideMagnetic)
            //     .input(stickLong, HeavyQuarkDegenerateMatter, 4)
            //     .input(ring, HeavyQuarkDegenerateMatter, 8)
            //     .input(round, HeavyQuarkDegenerateMatter, 16)
            //     .input(wireFine, QuantumAlloy, 64)
            //     .input(wireFine, QuantumAlloy, 64)
            //     .input(wireFine, QuantumAlloy, 64)
            //     .input(cableGtSingle, Seaborgium, 2)
            //     .fluidInputs(MutatedLivingSolder.gteFluid(L * 16))
            //     .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            //     .fluidInputs(MetastableOganesson.getFluid(L * 4))
            //     .fluidInputs(Fullerene.getFluid(L))
            //     .output(ELECTRIC_MOTOR_UEV)
            //     .EUt(1_800_000) // UHV
            //     .duration(40 * SECOND)
            //     .stationResearch { r ->
            //         r.researchStack(ELECTRIC_MOTOR_UHV.stackForm)
            //             .EUt(VA[UHV].toLong())
            //             .CWUt(64)
            //     }
            //     .buildAndRegister()

            // UIV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(stickLong, TengamAttuned, 2)
            //     .input(stickLong, QuantumchromodynamicallyConfinedMatter, 8)
            //     .input(ring, QuantumchromodynamicallyConfinedMatter, 16)
            //     .input(round, QuantumchromodynamicallyConfinedMatter, 32)
            //     .input(wireFine, Hypogen, 64)
            //     .input(wireFine, Hypogen, 64)
            //     .input(wireFine, Hypogen, 64)
            //     .input(wireFine, Hypogen, 32)
            //     .input(cableGtSingle, Meitnerium, 2)

            // UXV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(ENERGISED_TESSERACT)
            //     .input(stickLong, TengamAttuned, 2)
            //     .input(stickLong, TranscendentMetal, 4)
            //     .input(stickLong, MagnetoHydrodynamicallyConstrainedStarMatter, 4)
            //     .input(ring, TranscendentMetal, 8)
            //     .input(ring, MagnetoHydrodynamicallyConstrainedStarMatter, 8)
            //     .input(round, TranscendentMetal, 16)
            //     .input(round, MagnetoHydrodynamicallyConstrainedStarMatter, 16)
            //     .input(wireFine, SpaceTime, 64)
            //     .input(wireFine, SpaceTime, 64)
            //     .input(wireFine, MagMatter, 64)
            //     .input(wireFine, MagMatter, 64)
            //     .input(cableGtSingle, Periodicium, 2)

            // OpV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(ENERGISED_TESSERACT)
            //     .input(stickLong, TengamAttuned, 2)
            //     .input(stickLong, CosmicNeutronium, 4)
            //     .input(stickLong, Eternity)
            // ...

            // MAX
        }

        private fun energyHatchesRecipes()
        {
            // TODO UHV-OpV
        }

        private fun laserHatchesRecipes()
        {
            // TODO

            // 4194304A IV Laser Target Hatch

            // 4194304A IV Laser Source Hatch

            // ---------------------------------------------------------------------------------------------------------

            // 16777216A IV Laser Target Hatch

            // 16777216A IV Laser Source Hatch

        }

    }

}