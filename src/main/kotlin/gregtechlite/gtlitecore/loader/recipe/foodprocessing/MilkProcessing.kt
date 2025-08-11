package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.unification.material.Materials.Milk
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butter
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.extension.EUt

internal object MilkProcessing
{

    // @formatter:off

    fun init()
    {
        // Milk -> Butter
        FERMENTING_RECIPES.recipeBuilder()
            .fluidInputs(Milk.getFluid(100))
            .fluidOutputs(Butter.getFluid(90))
            .EUt(VH[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()
    }

    // @formatter:on

}