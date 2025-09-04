package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MOX
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlutoniumDioxide

object FissionFuelChain
{

    // @formatter:off

    fun init()
    {
        moxProcess()
    }

    private fun moxProcess()
    {
        // Pu241 + 2O -> PuO2
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Plutonium241)
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, PlutoniumDioxide, 3)
            .EUt(VA[LV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // PuO2 + 2UO2 -> (PuO2)(UO2)2
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, PlutoniumDioxide, 3)
            .input(dust, Uraninite, 6)
            .output(dust, MOX, 9)
            .EUt(VA[HV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}