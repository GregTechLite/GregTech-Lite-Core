package magicbook.gtlitecore.api.recipe

import crafttweaker.annotations.ZenRegister
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.recipes.RecipeMapBuilder
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.builders.SimpleRecipeBuilder
import gregtech.api.unification.material.Materials
import gregtech.api.util.GTUtility
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import magicbook.gtlitecore.api.utils.GTLiteUtility
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenProperty
import kotlin.math.max
import kotlin.math.min

/**
 * @zenClass mods.gtlitecore.recipe.RecipeMaps
 *
 * @see [gregtech.api.recipes.RecipeMaps]
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
@ZenClass("mods.gtlitecore.recipe.RecipeMaps")
@ZenRegister
class GTLiteRecipeMaps
{

    companion object
    {

        /**
         * @apiNote The original design of polisher is to separate some "less typical"
         *          cutting machine/lathe recipes (because the input of these recipes may
         *          be blown off by cutting blade or crushed by the lathe!). For example,
         *          lathing gem to lens is a typical processing for this design described,
         *          it is a little alien, polishing is natural than lathing.
         *
         * @zenProp polisher
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("POLISHER_RECIPES")
        val POLISHER_RECIPES = RecipeMapBuilder("polisher", SimpleRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(2)
            .fluidInputs(1)
            .itemSlotOverlay(GuiTextures.SAWBLADE_OVERLAY, false)
            .itemSlotOverlay(GuiTextures.CUTTER_OVERLAY, true, false)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true, true)
            .fluidSlotOverlay(GuiTextures.HEATING_OVERLAY_2, false)
            .progressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.CIRCULAR)
            .sound(GTSoundEvents.CUT)
            .onBuild(GTLiteUtility.gtliteId("polisher_fluid")) { recipeBuilder ->
                // Same like cutting machine logic, but polisher used more water as coolant,
                // confirm three cutting function machines, the water required of each recipe
                // is slicer < this < cutter.
                if (recipeBuilder.fluidInputs.isEmpty())
                {
                    val eut = recipeBuilder.eUt
                    val duration = recipeBuilder.duration
                    // Common water recipes.
                    recipeBuilder.copy()
                        .fluidInputs(Materials.Water.getFluid(GTUtility.safeCastLongToInt(
                            max(4, min(500, duration * eut / 320)))))
                        .duration(duration * 2)
                        .buildAndRegister()

                    // Distilled water recipes, faster than water recipes.
                    recipeBuilder.copy()
                        .fluidInputs(Materials.DistilledWater.getFluid(GTUtility.safeCastLongToInt(
                            max(3, min(250, duration * eut / 426)))))
                        .duration((duration * 1.5).toInt())
                        .buildAndRegister()

                    // Do not call buildAndRegister() as we are mutating the original recipe and
                    // already in the middle of a buildAndRegister() copy call. Adding a second
                    // call will result in duplicate recipe generation attempts.
                    recipeBuilder.fluidInputs(Materials.Lubricant.getFluid(GTUtility.safeCastLongToInt(
                        max(1, min(125, duration * eut / 1280)))))
                        .duration(max(1, duration))

                }
            }
            .build()

        /**
         * @apiNote The original design of slicer is same as polisher, this machine can use
         *          metal blade to cutting some soft materials like plastic or fruit. However,
         *          it is also contained some functions from bender and lathe, e.g. plate or
         *          stick of some plastics, this is natural for reality world.
         *
         * @zenProp slicer
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("SLICER_RECIPES")
        val SLICER_RECIPES = RecipeMapBuilder("slicer", SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(2)
            .fluidInputs(1)
            .fluidOutputs(1)
            .itemSlotOverlay(GTLiteGuiTextures.CHOPPING_BLOCK_OVERLAY, false, false)
            .itemSlotOverlay(GTLiteGuiTextures.SLICED_CONTAINER_OVERLAY, false, true)
            .itemSlotOverlay(GTLiteGuiTextures.SLICED_MATTER_OVERLAY, true, false)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true, true)
            .progressBar(GTLiteGuiTextures.PROGRESS_BAR_SLICING)
            .sound(GTSoundEvents.CUT)
            .onBuild(GTLiteUtility.gtliteId("slicer_fluid")) { recipeBuilder ->
                // Same like cutting machine logic, but slicer used quite little water as coolant,
                // confirm three cutting function machines, the water required of each recipe
                // is this < polisher < cutter.
                if (recipeBuilder.fluidInputs.isEmpty())
                {
                    val eut = recipeBuilder.eUt
                    val duration = recipeBuilder.duration
                    // Common water recipes.
                    recipeBuilder.copy()
                        .fluidInputs(Materials.Water.getFluid(GTUtility.safeCastLongToInt(
                            max(4, min(200, duration * eut / 320)))))
                        .duration(duration * 2)
                        .buildAndRegister()

                    // Distilled water recipes, faster than water recipes.
                    recipeBuilder.copy()
                        .fluidInputs(Materials.DistilledWater.getFluid(GTUtility.safeCastLongToInt(
                            max(3, min(100, duration * eut / 426)))))
                        .duration((duration * 1.5).toInt())
                        .buildAndRegister()

                    // Do not call buildAndRegister() as we are mutating the original recipe and
                    // already in the middle of a buildAndRegister() copy call. Adding a second
                    // call will result in duplicate recipe generation attempts.
                    recipeBuilder.fluidInputs(Materials.Lubricant.getFluid(GTUtility.safeCastLongToInt(
                        max(1, min(50, duration * eut / 1280)))))
                        .duration(max(1, duration))

                }
            }
            .build()

        @JvmStatic
        fun postRecipeMaps() // Used to post RecipeMap changing.
        {
            // (1,6,0,0) -> (1,9,2,2)
            RecipeMaps.SIFTER_RECIPES.maxFluidInputs = 2
            RecipeMaps.SIFTER_RECIPES.maxOutputs = 9
            RecipeMaps.SIFTER_RECIPES.maxFluidOutputs = 2
        }

    }

}