package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumHydroxide
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object PotassiumProcessing
{

    // @formatter:off

    fun init()
    {
        // Common rock salt decompose.
        ELECTROLYZER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, RockSalt, 2)
            .output(dust, Potassium)
            .fluidOutputs(Chlorine.getFluid(1000))
            .EUt(VA[LV])
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
            .EUt(VA[LV])
            .duration(6 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}