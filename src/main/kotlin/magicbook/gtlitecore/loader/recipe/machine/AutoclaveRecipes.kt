package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.common.items.MetaItems.GRAVI_STAR
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ZENITH_STAR

@Suppress("MISSING_DEPENDENCY_CLASS")
class AutoclaveRecipes
{

    companion object
    {

        fun init()
        {
            // Zenith Star
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(GRAVI_STAR)
                .fluidInputs(Infinity.getFluid(L * 2))
                .output(ZENITH_STAR)
                .EUt(VA[UV].toLong())
                .duration(48 * SECOND)
                .buildAndRegister()
        }

    }

}