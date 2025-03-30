package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTRecipeUtility

@Suppress("MISSING_DEPENDENCY_CLASS")
class AlloysChain
{

    companion object
    {

        fun init()
        {
            rhodiumPlatedPalladiumProcessing()
        }

        private fun rhodiumPlatedPalladiumProcessing()
        {
            // Change rhodium plated palladium smelting recipes to arc furnace but not EBF,
            // player can get liquid by common mixer and ABS.
            GTRecipeUtility.removeMixerRecipes(
                arrayOf(OreDictUnifier.get(dust, Palladium, 3),
                    OreDictUnifier.get(dust, Rhodium),
                    IntCircuitIngredient.getIntegratedCircuit(1)))

            // 3Pd + Rh -> Pd3Rh
            ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, Palladium, 3)
                .fluidInputs(Rhodium.getFluid(L))
                .fluidInputs(Oxygen.getFluid(600))
                .output(ingotHot, RhodiumPlatedPalladium, 4)
                .EUt(VA[IV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

        }

    }

}