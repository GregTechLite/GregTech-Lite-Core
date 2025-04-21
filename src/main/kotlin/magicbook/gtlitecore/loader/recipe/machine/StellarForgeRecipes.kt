package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.unification.material.Materials.Water
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES

@Suppress("MISSING_DEPENDENCY_CLASS")
class StellarForgeRecipes
{

    companion object
    {

        fun init()
        {
            // Testing recipe
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidOutputs(Water.getFluid(1))
                .EUt(1)
                .duration(1)
                .buildAndRegister()

        }

    }

}