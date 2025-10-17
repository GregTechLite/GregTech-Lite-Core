package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Ethylene
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiethylEther

internal object DiethylEtherChain
{

    // @formatter:off

    fun init()
    {
        // C2H6O + C2H4 -> (C2H5)2O
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .fluidInputs(Ethylene.getFluid(1000))
            .fluidInputs(Ethanol.getFluid(1000))
            .fluidOutputs(DiethylEther.getFluid(1000))
            .EUt(VA[MV])
            .duration(14 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}