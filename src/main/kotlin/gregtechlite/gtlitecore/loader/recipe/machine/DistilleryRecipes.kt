package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.unification.material.Materials.Glue
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resin

internal object DistilleryRecipes
{

    // @formatter:off

    fun init()
    {

        DISTILLERY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Resin.getFluid(100))
            .fluidOutputs(Glue.getFluid(75))
            .EUt(VA[LV])
            .duration(15 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}