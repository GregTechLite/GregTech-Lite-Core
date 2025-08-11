package gregtechlite.gtlitecore.loader.recipe

import gregtech.loaders.recipe.WoodRecipeLoader
import gregtechlite.gtlitecore.loader.WoodTypeEntryLoader

internal object GTWoodRecipeLoader
{

    // @formatter:off

    fun init()
    {
        for (woodEntry in arrayOf(WoodTypeEntryLoader.BANANA, WoodTypeEntryLoader.ORANGE,
             WoodTypeEntryLoader.MANGO, WoodTypeEntryLoader.APRICOT, WoodTypeEntryLoader.LEMON,
             WoodTypeEntryLoader.LIME, WoodTypeEntryLoader.OLIVE, WoodTypeEntryLoader.NUTMEG,
             WoodTypeEntryLoader.COCONUT, WoodTypeEntryLoader.RAINBOW))
        {
            WoodRecipeLoader.registerWoodUnificationInfo(woodEntry)
            WoodRecipeLoader.registerWoodTypeRecipe(woodEntry)
        }
    }

    // @formatter:on

}