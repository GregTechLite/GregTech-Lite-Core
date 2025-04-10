package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumHydroxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class PotassiumProcessing
{

    companion object
    {

        fun init()
        {
            // Common rock salt decompose.
            ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, RockSalt, 2)
                .output(dust, Potassium)
                .fluidOutputs(Chlorine.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND + 12 * TICK)
                .buildAndRegister()

            // KCl + H2O -> KOH + Cl + H
            ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, RockSalt, 2)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, PotassiumHydroxide, 3)
                .fluidOutputs(Chlorine.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()
        }

    }

}