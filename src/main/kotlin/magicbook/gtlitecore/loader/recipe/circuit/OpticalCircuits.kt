package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Potash
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.wireFine
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumStrontiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthStrontiumCalciumCuprate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FranciumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FranciumCaesiumCadmiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.KaptonE
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumGalliumManganate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadScandiumTantalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LutetiumManganeseGermanium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyethyleneTerephthalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumManganate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RubidiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ScandiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumSeaborgate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumPentoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumBariumCalciumCuprate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PERFECT_CIRCUIT_BOARD

@Suppress("MISSING_DEPENDENCY_CLASS")
class OpticalCircuits
{

    companion object
    {

        fun init()
        {
            circuitBoardRecipes()
            smdRecipes()
            circuitComponentsRecipes()
            circuitRecipes()
        }

        private fun circuitBoardRecipes()
        {
            // Optical Board
            CVD_RECIPES.recipeBuilder()
                .input(plate, GalliumNitride)
                .input(foil, ThalliumBariumCalciumCuprate, 32)
                .fluidInputs(PolyethyleneTerephthalate.getFluid(L * 2))
                .output(OPTICAL_BOARD)
                .EUt(VA[UHV].toLong())
                .duration(2 * SECOND)
                .temperature(980)
                .buildAndRegister()

            // Optical Circuit Board
            for (etchingLiquid in arrayOf(
                // SodiumPersulfate.getFluid(32000),
                // Iron3Chloride.getFluid(16000) // 8000, 4000
                TetramethylammoniumHydroxide.getFluid(32000),
                EthylenediaminePyrocatechol.getFluid(16000)))
            {
                CHEMICAL_RECIPES.recipeBuilder()
                    .input(OPTICAL_BOARD)
                    .input(foil, Americium, 48)
                    .fluidInputs(etchingLiquid)
                    .output(PERFECT_CIRCUIT_BOARD)
                    .EUt(VA[EV].toLong())
                    .duration(1 * MINUTE + 45 * SECOND)
                    .cleanroom(CleanroomType.CLEANROOM)
                    .buildAndRegister()
            }
        }

        private fun smdRecipes()
        {
            // La2O3 + 2K2MnO4 + 2GaO2 -> 2LaGaMnO4 + 2K2O + 5O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, LanthanumOxide, 5)
                .input(dust, PotassiumManganate, 14)
                .input(dust, GalliumDioxide, 6)
                .output(dust, LanthanumGalliumManganate, 14)
                .output(dust, Potash, 6)
                .fluidOutputs(Oxygen.getFluid(5000))
                .EUt(VA[ZPM].toLong())
                .duration(14 * SECOND)
                .buildAndRegister()

            // La2O3 + 2K2MnO4 + Ga2O3 -> 2LaGaMnO4 + 2K2O + 4O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, LanthanumOxide, 5)
                .input(dust, PotassiumManganate, 14)
                .input(dust, GalliumTrioxide, 5)
                .output(dust, LanthanumGalliumManganate, 14)
                .output(dust, Potash, 6)
                .fluidOutputs(Oxygen.getFluid(4000))
                .EUt(VA[ZPM].toLong())
                .duration(7 * SECOND)
                .buildAndRegister()

            // BaTiO3 + SrO -> BaSrTiO4
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, BariumTitanate, 5)
                .input(dust, StrontiumOxide, 2)
                .output(dust, BariumStrontiumTitanate, 7)
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Optical SMD Transistor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(foil, LanthanumGalliumManganate)
                .input(wireFine, BariumStrontiumTitanate, 8)
                .fluidInputs(KaptonE.getFluid(L))
                .output(OPTICAL_SMD_TRANSISTOR, 32)
                .EUt(VA[UV].toLong())
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

            // Optical SMD Resistor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, SodiumSeaborgate)
                .input(wireFine, BismuthStrontiumCalciumCuprate, 4)
                .fluidInputs(KaptonE.getFluid(L * 2))
                .output(OPTICAL_SMD_RESISTOR, 32)
                .EUt(VA[UV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Cs + Br -> CsBr
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Caesium)
                .fluidInputs(Bromine.getFluid(1000))
                .output(dust, CaesiumBromide, 2)
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Fr + HBr -> FrBr + H
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, Francium)
                .fluidInputs(HydrobromicAcid.getFluid(1000))
                .output(dust, FranciumBromide, 2)
                .fluidOutputs(Hydrogen.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // FrBr + CsBr + 2CdBr2 -> FrCsCd2Br6
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, FranciumBromide, 2)
                .input(dust, CaesiumBromide, 2)
                .input(dust, CadmiumBromide, 6)
                .output(dust, FranciumCaesiumCadmiumBromide, 10)
                .EUt(VA[UV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Optical SMD Capacitor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(foil, FranciumCaesiumCadmiumBromide)
                .input(foil, RubidiumTitanate)
                .fluidInputs(KaptonE.getFluid(L / 2))
                .output(OPTICAL_SMD_CAPACITOR, 32)
                .EUt(VA[UV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Ta2O5 + Sc2O3 + 2Pb(NO3)2 -> 2PbScTaO3 + 2N2O4 + 6O
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .input(dust, TantalumPentoxide, 7)
                .input(dust, ScandiumOxide, 5)
                .input(dust, LeadNitrate, 18)
                .output(dust, LeadScandiumTantalate, 12)
                .fluidOutputs(DinitrogenTetroxide.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .EUt(VA[ZPM].toLong())
                .duration(7 * SECOND + 15 * TICK)
                .buildAndRegister()

            // Tl + 2Ba + 2Ca + 3Cu + 10O -> TlBa2Ca2Cu3O10
            GTRecipeHandler.removeRecipesByInputs(ARC_FURNACE_RECIPES,
                OreDictUnifier.get(foil, Thallium))
            GTRecipeHandler.removeRecipesByInputs(ARC_FURNACE_RECIPES,
                OreDictUnifier.get(foil, Barium))
            GTRecipeHandler.removeRecipesByInputs(ARC_FURNACE_RECIPES,
                OreDictUnifier.get(foil, Calcium))
            GTRecipeHandler.removeRecipesByInputs(ARC_FURNACE_RECIPES,
                OreDictUnifier.get(foil, Copper))

            ARC_FURNACE_RECIPES.recipeBuilder()
                .input(foil, Thallium, 2)
                .input(foil, Barium, 2)
                .input(foil, Calcium, 2)
                .input(foil, Copper, 3)
                .fluidInputs(Oxygen.getFluid(5000))
                .output(ingotHot, ThalliumBariumCalciumCuprate, 9)
                .EUt(VA[ZPM].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Optical SMD Diode
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, LeadScandiumTantalate)
                .input(wireFine, ThalliumBariumCalciumCuprate, 4)
                .fluidInputs(KaptonE.getFluid(288))
                .output(OPTICAL_SMD_DIODE, 64)
                .EUt(VA[UV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Optical SMD Inductor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(ring, LutetiumManganeseGermanium)
                .input(wireFine, Curium, 4)
                .fluidInputs(KaptonE.getFluid(L))
                .output(OPTICAL_SMD_INDUCTOR, 32)
                .EUt(VA[UV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

        }

        private fun circuitComponentsRecipes()
        {

        }

        private fun circuitRecipes()
        {

        }

    }

}