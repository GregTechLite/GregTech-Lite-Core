package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bromobutane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butyllithium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrobromicAcid
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object ButyllithiumChain
{

    // @formatter:off

    fun init()
    {
        // Anti-Markovnikov Reaction

        // C4H10O + HBr -> C4H9Br + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Butanol.getFluid(1000))
            .fluidInputs(HydrobromicAcid.getFluid(1000))
            .fluidOutputs(Bromobutane.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Li + C4H9Br -> C4H9Li + Br
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Lithium)
            .fluidInputs(Bromobutane.getFluid(1000))
            .fluidOutputs(Butyllithium.getFluid(1000))
            .fluidOutputs(Bromine.getFluid(1000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}