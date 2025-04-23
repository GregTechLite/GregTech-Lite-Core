package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AminooxyaceticAcid
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class AdditivesChain
{

    companion object
    {

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
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

}