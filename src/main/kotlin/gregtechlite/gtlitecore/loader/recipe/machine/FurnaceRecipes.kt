package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.unification.material.Materials.Sulfur
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler

internal object FurnaceRecipes
{

    // @formatter:off

    fun init()
    {
        GTLiteRecipeHandler.removeOreSmelting(Sulfur)
        GTLiteRecipeHandler.addOreSmelting(Sulfur)
    }

    // @formatter:on

}