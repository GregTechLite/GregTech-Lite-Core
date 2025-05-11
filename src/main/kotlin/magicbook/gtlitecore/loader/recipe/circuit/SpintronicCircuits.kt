package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Mercury
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Phosphate
import gregtech.api.unification.material.Materials.PhosphoricAcid
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.wireFine
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmorphousBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumCeriumCobaltIndium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CeriumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CobaltAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DegenerateRhenium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullerenePolymerMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HafniumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IndiumPhosphate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MercuryCadmiumTelluride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phosphorene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaborgiumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumSeaborgate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumHafniumSeaborgiumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumRoentgeniumChloride
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.INFINITE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_TRANSISTOR

@Suppress("MISSING_DEPENDENCY_CLASS")
class SpintronicCircuits
{

    companion object
    {

        fun init()
        {
            circuitBoardRecipes()
            smdRecipes()
            circuitRecipes()
        }

        private fun circuitBoardRecipes()
        {
            // Spintronic Board
            CVD_RECIPES.recipeBuilder()
                .input(plate, CarbonNanotube)
                .input(foil, Phosphorene, 64)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
                .output(SPINTRONIC_BOARD)
                .EUt(VA[UEV].toLong())
                .duration(2 * SECOND)
                .temperature(3580)
                .buildAndRegister()

            // Infinite Circuit Board
            for (etchingLiquid in arrayOf(
                // SodiumPersulfate.getFluid(64000),
                // Iron3Chloride.getFluid(32000), 16000, 8000
                TetramethylammoniumHydroxide.getFluid(64000),
                EthylenediaminePyrocatechol.getFluid(32000)
            ))
            {
                CHEMICAL_RECIPES.recipeBuilder()
                    .input(SPINTRONIC_BOARD)
                    .input(foil, Dubnium, 64)
                    .fluidInputs(etchingLiquid)
                    .output(INFINITE_CIRCUIT_BOARD)
                    .EUt(VA[IV].toLong())
                    .duration(2 * MINUTE)
                    .cleanroom(CleanroomType.CLEANROOM)
                    .buildAndRegister()
            }
        }

        private fun smdRecipes()
        {
            // Cd + 2Te + 2Hg -> Hg2CdTe2
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Cadmium)
                .input(dust, Tellurium, 2)
                .fluidInputs(Mercury.getFluid(2000))
                .output(dust, MercuryCadmiumTelluride, 5)
                .EUt(VA[UHV].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Spintronic SMD Transistor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(foil, AmorphousBoronNitride)
                .input(wireFine, MercuryCadmiumTelluride, 16)
                .fluidInputs(Kevlar.getFluid(L))
                .output(SPINTRONIC_SMD_TRANSISTOR, 32)
                .EUt(VA[UHV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Add another recipes for InPO4 because this is a byproduct in Nq processing.
            // In + H3PO4 -> InPO4 + 3H
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Indium)
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .output(dust, IndiumPhosphate, 6)
                .fluidOutputs(Hydrogen.getFluid(3000))
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND + 16 * TICK)
                .buildAndRegister()

            // CsOH + CeO2 + 2CoAl2O4 + 10InPO4 + 9H -> CsCeCo2In10 + 2Al2O3 + 10PO4 + 5H2O
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .input(dust, CaesiumHydroxide, 3)
                .input(dust, CeriumOxide, 3)
                .input(dust, CobaltAluminate, 14)
                .input(dust, IndiumPhosphate, 60)
                .fluidInputs(Hydrogen.getFluid(9000))
                .output(dust, CaesiumCeriumCobaltIndium, 14)
                .output(dust, Alumina, 10)
                .output(dust, Phosphate, 50)
                .fluidOutputs(Water.getFluid(5000))
                .EUt(VA[UHV].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Spintronic SMD Resistor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(dust, DegenerateRhenium)
                .input(wireFine, CaesiumCeriumCobaltIndium, 8)
                .fluidInputs(Kevlar.getFluid(L * 2))
                .output(SPINTRONIC_SMD_RESISTOR, 32)
                .EUt(VA[UHV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // 12TaC + 3HfC + SgC -> Ta12Hf3SgC16
            ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, TantalumCarbide, 24)
                .input(dust, HafniumCarbide, 6)
                .input(dust, SeaborgiumCarbide, 2)
                .output(ingotHot, TantalumHafniumSeaborgiumCarbide, 32)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 12 * SECOND)
                .buildAndRegister()

            // Spintronic SMD Capacitor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(foil, TantalumHafniumSeaborgiumCarbide)
                .input(foil, MetastableFlerovium, 2)
                .fluidInputs(Kevlar.getFluid(L / 2))
                .output(SPINTRONIC_SMD_CAPACITOR, 32)
                .EUt(VA[UHV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // 2NaOH + Sg + 6F + 2H2O -> Na2SgO4 + 6HF
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 6)
                .input(dust, Seaborgium)
                .fluidInputs(Fluorine.getFluid(6000))
                .fluidInputs(Water.getFluid(2000))
                .output(dust, SodiumSeaborgate, 7)
                .fluidOutputs(HydrofluoricAcid.getFluid(6000))
                .EUt(VA[UV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // Spintronic SMD Diode
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(dust, ActiniumSuperhydride)
                .input(wireFine, SodiumSeaborgate, 8)
                .fluidInputs(Kevlar.getFluid(L * 2))
                .output(SPINTRONIC_SMD_DIODE, 64)
                .EUt(VA[UHV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Tl + Rg + 3Cl -> TlRgCl3
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Thallium)
                .input(dust, Roentgenium)
                .fluidInputs(Chlorine.getFluid(3000))
                .output(dust, ThalliumRoentgeniumChloride, 5)
                .EUt(VA[UHV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

            // Spintronic SMD Inductor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(ring, ThalliumRoentgeniumChloride)
                .input(wireFine, Moscovium, 8)
                .fluidInputs(Kevlar.getFluid(L))
                .output(SPINTRONIC_SMD_INDUCTOR, 32)
                .EUt(VA[UHV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

        }

        private fun circuitRecipes()
        {
            // Spintronic Processor

            // Spintronic Assembly

            // Spintronic Computer

            // ASSEMBLY_LINE_RECIPES.recipeBuilder() // template
            //     .input(PERFECT_CIRCUIT_BOARD)
            //     .input(OPTICAL_PROCESSOR_UV, 4)
            //     .input(OPTICAL_SMD_CAPACITOR, 16)
            //     .input(OPTICAL_SMD_INDUCTOR, 16)
            //     .input(ADVANCED_SYSTEM_ON_CHIP, 32)
            //     .input(OPTICAL_FIBER, 16)
            //     .input("foilAnySyntheticRubber", 32)
            //     .fluidInputs(SolderingAlloy.getFluid(L * 4))
            //     .output(OPTICAL_ASSEMBLY_UHV, 3)
            //     .EUt(VA[UHV].toLong())
            //     .duration(20 * SECOND)
            //     .stationResearch { r ->
            //         r.researchStack(OPTICAL_PROCESSOR_UV.stackForm)
            //             .EUt(VA[UHV].toLong())
            //             .CWUt(32)
            //     }
            //     .buildAndRegister()

            // Spintronic Mainframe

        }

    }

}