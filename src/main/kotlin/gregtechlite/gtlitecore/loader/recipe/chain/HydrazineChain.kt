package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Water
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Formaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydrazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylhydrazine
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object HydrazineChain
{

    // @formatter:off

    fun init()
    {
        // 2NH3 + H2O2 -> N2H4 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Ammonia.getFluid(2000))
            .fluidInputs(HydrogenPeroxide.getFluid(1000))
            .fluidOutputs(Hydrazine.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[HV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // N2H4 + CH2O -> CH6N2 + O (drop)
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Hydrazine.getFluid(1000))
            .fluidInputs(Formaldehyde.getFluid(1000))
            .fluidOutputs(Methylhydrazine.getFluid(1000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}