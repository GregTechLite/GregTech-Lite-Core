package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.CalciumChloride
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Wood
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.common.items.MetaItems.PHENOLIC_BOARD
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.COAGULATION_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resin
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class ElectronicCircuits
{

    companion object
    {

        fun init()
        {
            circuitBoardRecipes()
        }

        private fun circuitBoardRecipes()
        {
            // Deleted vanilla recipes of Phenolic board.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1),
                        OreDictUnifier.get(dust, Wood)),
                arrayOf(Glue.getFluid(50)))

            GTRecipeHandler.removeRecipesByInputs(EXTRACTOR_RECIPES,
                OreDictUnifier.get(dust, Resin))

            // Coagulate resin liquid to dust.
            COAGULATION_RECIPES.recipeBuilder()
                .notConsumable(stick, Iron)
                .fluidInputs(Resin.getFluid(1000))
                .output(dust, Resin)
                .duration(5 * SECOND)
                .buildAndRegister()

            COAGULATION_RECIPES.recipeBuilder()
                .notConsumable(stick, Iron)
                .notConsumable(dust, CalciumChloride)
                .fluidInputs(Resin.getFluid(1000))
                .output(dust, Resin)
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            COAGULATION_RECIPES.recipeBuilder()
                .notConsumable(stick, Iron)
                .fluidInputs(Resin.getFluid(1000))
                .notConsumable(SulfuricAcid.getFluid(1))
                .output(dust, Resin)
                .duration(1 * SECOND)
                .buildAndRegister()

            COAGULATION_RECIPES.recipeBuilder()
                .notConsumable(stick, Iron)
                .fluidInputs(Resin.getFluid(1000))
                .notConsumable(AceticAcid.getFluid(1))
                .output(dust, Resin)
                .duration(5 * TICK)
                .buildAndRegister()

            // New phenolic board recipes.
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Resin)
                .fluidInputs(Glue.getFluid(50))
                .output(PHENOLIC_BOARD)
                .EUt(VA[LV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

        }

    }

}