package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Water
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydrazine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class HydrazineChain
{

    companion object
    {

        fun init()
        {
            // 2NH3 + H2O2 -> N2H4 + 2H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(Hydrazine.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()
        }

    }

}