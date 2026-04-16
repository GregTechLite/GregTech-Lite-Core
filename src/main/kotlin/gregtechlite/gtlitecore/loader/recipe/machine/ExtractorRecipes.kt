package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.EnderPearl
import gregtech.api.unification.ore.OrePrefix.dust

internal object ExtractorRecipes
{

    // @formatter:off

    fun init()
    {
        // Remove duplicated endear pearl dust extraction.
        GTRecipeHandler.removeRecipesByInputs(EXTRACTOR_RECIPES,
            OreDictUnifier.get(dust, EnderPearl))
    }

    // @formatter:on

}