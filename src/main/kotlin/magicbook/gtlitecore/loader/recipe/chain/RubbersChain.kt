package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Magnesia
import gregtech.api.unification.material.Materials.RawRubber
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zincite
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BOLT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_FOIL
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_GEAR
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_RING
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROD
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SAP_COLLECTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.VULCANIZATION_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Latex
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTRecipeUtility
import net.minecraft.block.BlockLog

@Suppress("MISSING_DEPENDENCY_CLASS")
class RubbersChain
{

    companion object
    {

        fun init()
        {
            rubberProcessing()
            // TODO
            // syntheticRubberProcessing()
            // advancedSyntheticRubberProcessing()
        }

        private fun rubberProcessing()
        {
            // Extraction latex from a rubber planks, used sap collector with water.
            SAP_COLLECTOR_RECIPES.recipeBuilder()
                .notConsumable(Water.getFluid(10))
                .fluidOutputs(Latex.getFluid(100))
                .EUt(VA[ULV].toLong())
                .duration(1 * SECOND)
                .blockStates("rubber", arrayListOf((MetaBlocks.RUBBER_LOG as? BlockLog)!!.defaultState))
                .buildAndRegister()

            // TODO Add a steam age drying machine (as ULV Chemical Dehydrator).
            GTRecipeHandler.removeRecipesByInputs(EXTRACTOR_RECIPES,
                OreDictUnifier.get(dust, Latex))

            // Disabled vanilla Alloy Smelter recipes of Rubber ingot, only allowed used
            // vulcanization processing to get rubbers, same as this, chemical reactor is
            // also not allowed yet.
            GTRecipeHandler.removeRecipesByInputs(ALLOY_SMELTER_RECIPES,
                OreDictUnifier.get(dust, Sulfur),
                OreDictUnifier.get(dust, RawRubber, 3))
            GTRecipeUtility.removeChemicalRecipes(arrayOf(
                OreDictUnifier.get(dust, RawRubber, 9),
                OreDictUnifier.get(dust, Sulfur)))

            // Used shape extruders to confirm recipes clearly.

            // Add rubber component recipes.
            for (catalyst in arrayOf(Zincite, Magnesia))
            {
                // Rubber Ingot
                VULCANIZATION_RECIPES.recipeBuilder()
                    .notConsumable(dust, catalyst, 1)
                    .notConsumable(SHAPE_EXTRUDER_INGOT)
                    .input(dust, Latex, 4)
                    .input(dust, Sulfur, 1)
                    .output(ingot, Rubber, 4)
                    .EUt(VA[ULV].toLong())
                    .duration(20 * SECOND)
                    .buildAndRegister()

                // Rubber Plate
                VULCANIZATION_RECIPES.recipeBuilder()
                    .notConsumable(dust, catalyst, 1)
                    .notConsumable(SHAPE_EXTRUDER_PLATE)
                    .input(dust, Latex, 4)
                    .input(dust, Sulfur, 1)
                    .output(plate, Rubber, 4)
                    .EUt(VA[ULV].toLong())
                    .duration(20 * SECOND)
                    .buildAndRegister()

                // Rubber Rod
                VULCANIZATION_RECIPES.recipeBuilder()
                    .notConsumable(dust, catalyst, 1)
                    .notConsumable(SHAPE_EXTRUDER_ROD)
                    .input(dust, Latex, 4)
                    .input(dust, Sulfur, 1)
                    .output(stick, Rubber, 8)
                    .EUt(VA[ULV].toLong())
                    .duration(20 * SECOND)
                    .buildAndRegister()

                // Rubber Ring
                VULCANIZATION_RECIPES.recipeBuilder()
                    .notConsumable(dust, catalyst, 1)
                    .notConsumable(SHAPE_EXTRUDER_RING)
                    .input(dust, Latex, 4)
                    .input(dust, Sulfur, 1)
                    .output(ring, Rubber, 16)
                    .EUt(VA[ULV].toLong())
                    .duration(20 * SECOND)
                    .buildAndRegister()

                // Rubber Foil
                VULCANIZATION_RECIPES.recipeBuilder()
                    .notConsumable(dust, catalyst, 1)
                    .notConsumable(SHAPE_EXTRUDER_FOIL)
                    .input(dust, Latex, 4)
                    .input(dust, Sulfur, 1)
                    .output(foil, Rubber, 16)
                    .EUt(VA[ULV].toLong())
                    .duration(20 * SECOND)
                    .buildAndRegister()

                // Rubber Bolt
                VULCANIZATION_RECIPES.recipeBuilder()
                    .notConsumable(dust, catalyst, 1)
                    .notConsumable(SHAPE_EXTRUDER_BOLT)
                    .input(dust, Latex, 4)
                    .input(dust, Sulfur, 1)
                    .output(bolt, Rubber, 32)
                    .EUt(VA[ULV].toLong())
                    .duration(20 * SECOND)
                    .buildAndRegister()

                // Rubber Gear
                VULCANIZATION_RECIPES.recipeBuilder()
                    .notConsumable(dust, catalyst, 1)
                    .notConsumable(SHAPE_EXTRUDER_GEAR)
                    .input(dust, Latex, 4)
                    .input(dust, Sulfur, 1)
                    .output(gear, Rubber, 4)
                    .EUt(VA[ULV].toLong())
                    .duration(20 * SECOND)
                    .buildAndRegister()
            }

            // TODO Add higher raw materials to replace latex in high tier,
            //      because sap collector hasn't large machine or high tier machines,
            //      so we should add buff recipes to these vulcanizing press recipes.

        }

    }

}