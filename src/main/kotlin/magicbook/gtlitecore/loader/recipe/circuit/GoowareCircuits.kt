package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Glycerol
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Nitrobenzene
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.wireFine
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aminophenol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CopperGalliumIndiumSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydroselenicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydroxyquinoline
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydroxyquinolineAluminium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.KaptonK
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SelenousAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZBLANGlass
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_TRANSISTOR

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
        }

        private fun circuitComponentsRecipes()
        {

        }

        private fun circuitRecipes()
        {

        }

    }

}