package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.cleanroom
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScheelesGreen
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing

internal object ChemicalBathRecipes
{

    // @formatter:off

    fun init()
    {
        // Greenhouse Glass
        CHEMICAL_BATH_RECIPES.addRecipe {
            inputs(GTGlassCasing.TEMPERED_GLASS.stack)
            fluidInputs(ScheelesGreen.getFluid(L * 4))
            outputs(GlassCasing.GREENHOUSE.stack)
            EUt(VA[HV])
            duration(10 * SECOND)
            cleanroom()
        }
    }

    // @formatter:on

}