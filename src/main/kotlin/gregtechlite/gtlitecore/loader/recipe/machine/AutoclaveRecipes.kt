package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ZENITH_STAR

internal object AutoclaveRecipes
{

    // @formatter:off

    fun init()
    {
        // Zenith Star
        AUTOCLAVE_RECIPES.recipeBuilder()
            .input(GRAVI_STAR)
            .fluidInputs(Infinity.getFluid(L * 2))
            .output(ZENITH_STAR)
            .EUt(VA[UV])
            .duration(48 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}