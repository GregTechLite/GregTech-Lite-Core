package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chloroform
import gregtech.api.unification.material.Materials.Chloromethane
import gregtech.api.unification.material.Materials.Methane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChlorinatedSolvents
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dichloromethane
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object ChloridesChain
{

    // @formatter:off

    fun init()
    {
        // 2CH4 + 5Cl -> 7(CH4)2Cl5
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Methane.getFluid(2000))
            .fluidInputs(Chlorine.getFluid(5000))
            .fluidOutputs(ChlorinatedSolvents.getFluid(7000))
            .EUt(VA[EV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // 14(CH4)2Cl5 -> CH3Cl + CH2Cl2 + CHCl3 + CCl4
        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(ChlorinatedSolvents.getFluid(14000))
            .fluidOutputs(Chloromethane.getFluid(1330))
            .fluidOutputs(Dichloromethane.getFluid(2170))
            .fluidOutputs(Chloroform.getFluid(2170))
            .fluidOutputs(CarbonTetrachloride.getFluid(1330))
            .EUt(VA[IV])
            .duration(18 * SECOND)
            .disableDistilleryRecipes()
            .buildAndRegister()
    }

    // @formatter:on

}