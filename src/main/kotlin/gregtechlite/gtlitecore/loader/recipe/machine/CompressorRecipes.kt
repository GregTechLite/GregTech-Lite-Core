package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.common.items.MetaItems.PLANT_BALL
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.s

internal object CompressorRecipes
{
    // @formatter:off

    fun init()
    {
        COMPRESSOR_RECIPES.addRecipe {
            input("treeLeaves", 8)
            output(PLANT_BALL)
            EUt(2)
            duration(15.s)
        }
    }

    // @formatter:on
}