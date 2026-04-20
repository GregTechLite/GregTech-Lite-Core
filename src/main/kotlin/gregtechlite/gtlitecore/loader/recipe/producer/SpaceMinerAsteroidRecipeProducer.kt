package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_MINER_RECIPES

object SpaceMinerAsteroidRecipeProducer
{

    fun produce()
    {
        SPACE_MINER_RECIPES.addRecipe {
            input(dust, Iron)
            output(ingot, Iron)
            EUt(1)
            duration(1)
            tier(1)
            CWUt(1)
        }
    }

}