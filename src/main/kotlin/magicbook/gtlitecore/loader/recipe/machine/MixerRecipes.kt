package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.LARGE_MIXER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Inconel625
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kovar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MaragingSteel250
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Staballoy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Stellite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Talonite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WatertightSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zeron100
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

class MixerRecipes
{

    companion object
    {

        fun init()
        {
            // Kovar
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Invar, 15)
                .input(dust, Cobalt, 3)
                .output(dust, Kovar, 18)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Iron, 10)
                .input(dust, Nickel, 5)
                .input(dust, Cobalt, 3)
                .output(dust, Kovar, 18)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Maraging Steel 250
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Iron, 16)
                .input(dust, Molybdenum, 1)
                .input(dust, Titanium, 1)
                .input(dust, Nickel, 4)
                .input(dust, Cobalt, 2)
                .output(dust, MaragingSteel250, 24)
                .EUt(VA[EV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Inconel-625
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Nickel, 3)
                .input(dust, Chrome, 7)
                .input(dust, Molybdenum, 10)
                .input(dust, Invar, 10)
                .input(dust, Nichrome, 13)
                .output(dust, Inconel625, 48)
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Staballoy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Uranium, 9)
                .input(dust, Titanium, 1)
                .output(dust, Staballoy, 10)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Talonite
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Cobalt, 4)
                .input(dust, Chrome, 3)
                .input(dust, Phosphorus, 2)
                .input(dust, Molybdenum, 1)
                .output(dust, Talonite, 10)
                .EUt(VA[HV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

            // Zeron-100
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Chrome, 13)
                .input(dust, Nickel, 3)
                .input(dust, Molybdenum, 2)
                .input(dust, Copper, 10)
                .input(dust, Tungsten, 2)
                .input(dust, Steel, 20)
                .output(dust, Zeron100, 60)
                .EUt(VA[IV].toLong())
                .duration(48 * SECOND)
                .buildAndRegister()

            // Watertight Steel
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Iron, 7)
                .input(dust, Aluminium, 4)
                .input(dust, Nickel, 2)
                .input(dust, Chrome, 1)
                .input(dust, Sulfur, 1)
                .output(dust, WatertightSteel, 15)
                .EUt(VA[HV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Stellite
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Cobalt, 9)
                .input(dust, Chrome, 9)
                .input(dust, Manganese, 5)
                .input(dust, Titanium, 2)
                .output(dust, Stellite, 25)
                .EUt(VA[HV].toLong())
                .duration(18 * SECOND)
                .buildAndRegister()

        }

    }

}