package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.Materials.Sulfur
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COOKED_HORSE_MEAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HORSE_MEAT

internal object FurnaceRecipes
{

    // @formatter:off

    fun init()
    {
        GTLiteRecipeHandler.removeOreSmelting(Sulfur)
        GTLiteRecipeHandler.addOreSmelting(Sulfur)

        ModHandler.addSmeltingRecipe(HORSE_MEAT.stack(), COOKED_HORSE_MEAT.stack())
    }

    // @formatter:on

}