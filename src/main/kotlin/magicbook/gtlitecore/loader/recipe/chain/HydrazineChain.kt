package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Water
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Formaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydrazine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methylhydrazine
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

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

            // N2H4 + CH2O -> CH6N2 + O (drop)
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidOutputs(Methylhydrazine.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

        }

    }

}