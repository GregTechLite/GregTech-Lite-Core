package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bromobutane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butanol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butyllithium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class ButyllithiumChain
{

    companion object
    {

        fun init()
        {
            // Anti-Markovnikov Reaction

            // C4H10O + HBr -> C4H9Br + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butanol.getFluid(1000))
                .fluidInputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(Bromobutane.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Li + C4H9Br -> C4H9Li + Br
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Lithium)
                .fluidInputs(Bromobutane.getFluid(1000))
                .fluidOutputs(Butyllithium.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

}