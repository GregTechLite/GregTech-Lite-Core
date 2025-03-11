package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.recipes.RecipeMaps.BENDER_RECIPES
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.ore.OrePrefix.plate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_EMPTY

class BenderRecipes
{

    companion object
    {

        fun init()
        {
            // Casting Mold (Empty)
            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(plate, VanadiumSteel, 4)
                .output(CASTING_MOLD_EMPTY)
                .EUt(12) // LV
                .duration(9 * SECOND)
                .buildAndRegister()

        }
    }

}