package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Glycerol
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.PotassiumDichromate
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CitricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FormicAcid

internal object CitricAcidChain
{

    // @formatter:off

    fun init()
    {
        // C3H8O3 + HCOOH + 2CO -> C6H8O7 + 2H
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, PotassiumDichromate)
            .fluidInputs(Glycerol.getFluid(1000))
            .fluidInputs(FormicAcid.getFluid(1000))
            .fluidInputs(CarbonMonoxide.getFluid(2000))
            .fluidOutputs(CitricAcid.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[EV])
            .duration(12 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}