package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.BENDER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.BorosilicateGlass
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MATTER_RESHAPING_RECIPES

internal object BorosilicateGlassChain
{
    // @formatter:off

    fun init()
    {
        ModHandler.removeRecipeByName("gregtech:plate_borosilicate_glass")
        FORGE_HAMMER_RECIPES.removeRecipe(OreDictUnifier.get(ingot, BorosilicateGlass, 3))
        MATTER_RESHAPING_RECIPES.removeRecipe(OreDictUnifier.get(ingot, BorosilicateGlass, 3))
        EXTRUDER_RECIPES.removeRecipe(OreDictUnifier.get(ingot, BorosilicateGlass),
                                      SHAPE_EXTRUDER_PLATE.stack())
        BENDER_RECIPES.removeRecipe(OreDictUnifier.get(ingot, BorosilicateGlass),
                                    IntCircuitIngredient.getIntegratedCircuit(1))
    }

    // @formatter:on
}