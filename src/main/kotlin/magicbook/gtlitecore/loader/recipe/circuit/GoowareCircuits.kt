package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Glycerol
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Nitrobenzene
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.SamariumMagnetic
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.FLUID_CELL_LARGE_STAINLESS_STEEL
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aminophenol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BZMedium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CopperGalliumIndiumSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicZirconia
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FluorinatedEthylenePropylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydroselenicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydroxyquinoline
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydroxyquinolineAluminium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.KaptonE
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.KaptonK
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PedotPSS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PedotTMA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SamariumCobalt
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SelenousAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZBLANGlass
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BZ_REACTION_CHAMBER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NONLINEAR_CHEMICAL_OSCILLATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTIMATE_CIRCUIT_BOARD

/**
 * @see [magicbook.gtlitecore.loader.recipe.chain.BZMediumChain]
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class GoowareCircuits
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
            // Gooware Board
            CVD_RECIPES.recipeBuilder()
                .input(plate, KaptonE)
                .input(foil, YttriumBariumCuprate, 24)
                .fluidInputs(FluorinatedEthylenePropylene.getFluid(L))
                .output(GOOWARE_BOARD)
                .EUt(VA[UV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Gooware Circuit Board
            for (etchingLiquid in arrayOf(
                // SodiumPersulfate.getFluid(16000),
                // Iron3Chloride.getFluid(8000) // 4000, 2000
                TetramethylammoniumHydroxide.getFluid(16000),
                EthylenediaminePyrocatechol.getFluid(8000)))
            {
                CHEMICAL_RECIPES.recipeBuilder()
                    .input(GOOWARE_BOARD)
                    .input(foil, YttriumBariumCuprate, 32)
                    .fluidInputs(etchingLiquid)
                    .output(ULTIMATE_CIRCUIT_BOARD)
                    .EUt(VA[EV].toLong())
                    .duration(1 * MINUTE + 45 * SECOND)
                    .cleanroom(CleanroomType.CLEANROOM)
                    .buildAndRegister()
            }

            // BZ Reactor Chamber
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(FLUID_CELL_LARGE_STAINLESS_STEEL)
                .input(plate, Naquadah, 4)
                .input(plate, Ruridit, 2)
                .input(bolt, Trinium, 12)
                .input(stick, SamariumMagnetic)
                .input(rotor, Iridium)
                .input(ELECTRIC_MOTOR_LuV)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(BZ_REACTION_CHAMBER)
                .EUt(VA[UV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Non-linear Chemical Oscillator
            CANNER_RECIPES.recipeBuilder()
                .input(BZ_REACTION_CHAMBER)
                .fluidInputs(BZMedium.getFluid(500))
                .output(NONLINEAR_CHEMICAL_OSCILLATOR)
                .EUt(VA[IV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()
        }

        private fun smdRecipes()
        {
            // C6H4(OH)(NH2) + C3H8O3 + O -> C9H7NO + 4H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Aminophenol, 15)
                .notConsumable(Nitrobenzene.getFluid(1))
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, Hydroxyquinoline, 18)
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[IV].toLong())
                .duration(13 * SECOND)
                .buildAndRegister()

            // Al + C9H7NO -> Al(C9H7NO)
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Aluminium, 1)
                .input(dust, Hydroxyquinoline, 18)
                .output(dust, HydroxyquinolineAluminium, 19)
                .EUt(VA[ZPM].toLong())
                .duration(8 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Another recipe for H2SeO3 for these chemistry processing.
            // Se + H2O + 2O -> H2SeO3
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Selenium)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, SelenousAcid, 6)
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // H2SeO3 + O -> H2SeO4
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, SelenousAcid, 6)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(HydroselenicAcid.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Cu + Ga + In + 2H2SeO4 -> CuGaInSe2 + 2H2O + 6O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Copper)
                .input(dust, Gallium)
                .input(dust, Indium)
                .fluidInputs(HydroselenicAcid.getFluid(2000))
                .output(dust, CopperGalliumIndiumSelenide, 5)
                .fluidOutputs(Oxygen.getFluid(6000))
                .fluidOutputs(Steam.getFluid(2000))
                .EUt(VA[LuV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Gooware SMD Transistor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(foil, HydroxyquinolineAluminium)
                .input(wireFine, CopperGalliumIndiumSelenide, 8)
                .fluidInputs(KaptonK.getFluid(L))
                .output(GOOWARE_SMD_TRANSISTOR, 16)
                .EUt(VA[ZPM].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Gooware SMD Resistor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, ZBLANGlass)
                .input(wireFine, Osmiridium, 4)
                .fluidInputs(KaptonK.getFluid(L * 2))
                .output(GOOWARE_SMD_RESISTOR, 16)
                .EUt(VA[ZPM].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Ba + 2H2O -> Ba(OH)2 + 2H
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Barium)
                .fluidInputs(Water.getFluid(2000))
                .output(dust, BariumHydroxide, 5)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(4 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Ba(OH)2 + TiCl4 + H2O -> BaTiO3 + 4HCl
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, BariumHydroxide, 5)
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .output(dust, BariumTitanate, 5)
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Gooware SMD Capacitor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(foil, PedotPSS)
                .input(foil, BariumTitanate)
                .fluidInputs(KaptonK.getFluid(L / 2))
                .output(GOOWARE_SMD_CAPACITOR, 16)
                .EUt(VA[ZPM].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Gooware SMD Diode
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, CubicZirconia)
                .input(wireFine, PedotTMA, 8)
                .fluidInputs(KaptonK.getFluid(L * 2))
                .output(GOOWARE_SMD_DIODE, 64)
                .EUt(VA[ZPM].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Gooware SMD Inductor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(ring, SamariumCobalt)
                .input(wireFine, Europium, 4)
                .fluidInputs(KaptonK.getFluid(L))
                .output(GOOWARE_SMD_INDUCTOR, 16)
                .EUt(VA[ZPM].toLong())
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