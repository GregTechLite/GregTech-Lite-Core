package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Ash
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.CharcoalByproducts
import gregtech.api.unification.material.Materials.Creosote
import gregtech.api.unification.material.Materials.OilHeavy
import gregtech.api.unification.material.Materials.WoodGas
import gregtech.api.unification.material.Materials.WoodTar
import gregtech.api.unification.material.Materials.WoodVinegar
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COMPLEX_PYROLYSIS_RECIPES

internal object ConcentratedCokingClusterRecipes
{
    // @formatter:off

    fun init()
    {
        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            input("logWood", 64)
            output(gem, Charcoal, 64)
            fluidOutputs(Creosote.getFluid(4000))
            fluidOutputs(CharcoalByproducts.getFluid(4000))
            fluidOutputs(WoodVinegar.getFluid(3000))
            fluidOutputs(WoodTar.getFluid(1500))
            fluidOutputs(WoodGas.getFluid(1500))
            fluidOutputs(OilHeavy.getFluid(200))
            EUt(VA[HV])
            duration(10 * SECOND)
        }
    }

    // @formatter:on
}