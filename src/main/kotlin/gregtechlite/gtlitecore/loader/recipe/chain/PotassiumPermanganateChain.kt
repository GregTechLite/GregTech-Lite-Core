package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumManganate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumPermanganate
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object PotassiumPermanganateChain
{

    // @formatter:off

    fun init()
    {
        // MnO2 + 2KOH + O -> K2MnO4 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Pyrolusite, 3)
            .fluidInputs(PotassiumHydroxide.getFluid(L * 6))
            .fluidInputs(Oxygen.getFluid(1000))
            .output(dust, PotassiumManganate, 7)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VHA[MV] / 2L)
            .duration(8 * SECOND + 10 * TICK)
            .buildAndRegister()

        // 3K2MnO4 + 2H2O -> 2KMnO4 + MnO2 (cycle) + 4KOH (cycle)
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, PotassiumManganate, 21)
            .fluidInputs(Water.getFluid(2000))
            .output(dust, PotassiumPermanganate, 12)
            .output(dust, Pyrolusite, 3)
            .fluidOutputs(PotassiumHydroxide.getFluid(L * 12))
            .EUt(VA[MV])
            .duration(12 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}