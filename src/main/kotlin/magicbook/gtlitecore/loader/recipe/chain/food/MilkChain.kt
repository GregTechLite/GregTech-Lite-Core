package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.unification.material.Materials.Milk
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butter
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE

@Suppress("MISSING_DEPENDENCY_CLASS")
class MilkChain
{

    companion object
    {

        fun init()
        {
            // Milk -> Butter
            FERMENTING_RECIPES.recipeBuilder()
                .fluidInputs(Milk.getFluid(100))
                .fluidOutputs(Butter.getFluid(90))
                .EUt(VH[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

    }

}