package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Glue
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import gregtech.common.items.MetaItems.STICKY_RESIN
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SAP_COLLECTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resin
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import net.minecraft.block.BlockLog
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

@Suppress("MISSING_DEPENDENCY_CLASS")
class WoodRecipeLoader
{

    companion object
    {

        fun init()
        {
            // Sap collector compatibilities.
            registerWoodSapCollections()
            registerWoodSapProcessings()
        }

        private fun registerWoodSapCollections()
        {
            // Vanilla logs.
            SAP_COLLECTOR_RECIPES.recipeBuilder()
                .notConsumable(DistilledWater.getFluid(10))
                .fluidOutputs(Resin.getFluid(100))
                .EUt(VA[ULV].toLong())
                .duration(1 * SECOND)
                .blockStates("common", arrayListOf(Blocks.LOG.defaultState, Blocks.LOG2.defaultState))
                .buildAndRegister()
        }

        private fun registerWoodSapProcessings()
        {
            // Deleted raw rubber processings.

            // Resin processing.
            DISTILLERY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Resin.getFluid(100))
                .fluidOutputs(Glue.getFluid(75))
                .EUt(VA[LV].toLong())
                .duration(15 * TICK)
                .buildAndRegister()

            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BALL)
                .fluidInputs(Resin.getFluid(250))
                .output(STICKY_RESIN)
                .EUt(2) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()
        }

    }

}