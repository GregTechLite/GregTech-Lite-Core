package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AminooxyaceticAcid
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object AdditivesProcessing
{

    // @formatter:off

    fun init()
    {
        aminooxyaceticAcidProcess()
    }

    private fun aminooxyaceticAcidProcess()
    {
        // C2H4O2 + HNO3 -> C2H5NO3 + 2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(AceticAcid.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(1000))
            .fluidOutputs(AminooxyaceticAcid.getFluid(1000))
            .fluidOutputs(Oxygen.getFluid(2000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}