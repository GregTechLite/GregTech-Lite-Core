package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Tetrafluoroethylene
import gregtech.api.unification.ore.OrePrefix.stick
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COMPLEX_PYROLYSIS_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FluorinatedEthylenePropylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexafluoropropylene

internal object FEPChain
{

    // @formatter:off

    fun init()
    {
        // 3C2F4 -> 2C3F6
        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            notConsumable(stick, Steel)
            fluidInputs(Tetrafluoroethylene.getFluid(3000))
            fluidOutputs(Hexafluoropropylene.getFluid(2000))
            EUt(VA[HV])
            duration(20 * SECOND)
            blastFurnaceTemp(4000)
        }

        // C2F4 + C3F6 -> C5F10
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(Tetrafluoroethylene.getFluid(1000))
            fluidInputs(Hexafluoropropylene.getFluid(1000))
            fluidOutputs(FluorinatedEthylenePropylene.getFluid(1000))
            EUt(VA[EV])
            duration(5 * SECOND)
        }
    }

    // @formatter:on

}