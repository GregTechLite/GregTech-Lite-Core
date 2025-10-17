package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.PYROLYSE_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Tetrafluoroethylene
import gregtech.api.unification.ore.OrePrefix.stick
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FluorinatedEthylenePropylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexafluoropropylene

internal object FEPChain
{

    // @formatter:off

    fun init()
    {
        // 3C2F4 -> 2C3F6
        PYROLYSE_RECIPES.recipeBuilder()
            .notConsumable(stick, Steel)
            .fluidInputs(Tetrafluoroethylene.getFluid(3000))
            .fluidOutputs(Hexafluoropropylene.getFluid(2000))
            .EUt(VA[HV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // C2F4 + C3F6 -> C5F10
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Tetrafluoroethylene.getFluid(1000))
            .fluidInputs(Hexafluoropropylene.getFluid(1000))
            .fluidOutputs(FluorinatedEthylenePropylene.getFluid(1000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}