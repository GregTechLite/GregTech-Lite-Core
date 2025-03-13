package magicbook.gtlitecore.api.recipe

import crafttweaker.annotations.ZenRegister
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.recipes.RecipeMapBuilder
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.builders.PrimitiveRecipeBuilder
import gregtech.api.recipes.builders.SimpleRecipeBuilder
import gregtech.api.unification.material.Materials
import gregtech.api.util.GTUtility
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import magicbook.gtlitecore.api.recipe.builder.PseudoMultiRecipeBuilder
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

        /**
         * @apiNote At first, the function of this recipe maps is designed in forge hammer
         *          recipe map, and this we find this is not a good choice to build these
         *          producer for disposable tools, so we create a new machine and its recipe
         *          map for these producer. The original idea of this machine is from
         *          GT:NH modpack, but we redo logic part to let these recipes look finely.
         * @zenProp tool_caster
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("TOOL_CASTER_RECIPES")
        val TOOL_CASTER_RECIPES = RecipeMapBuilder("tool_caster", SimpleRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(9)
            .fluidInputs(1)
            .itemSlotOverlay(GuiTextures.MOLD_OVERLAY, false)
            .progressBar(GuiTextures.PROGRESS_BAR_UNPACKER)
            .sound(GTSoundEvents.FORGE_HAMMER)
            .build()

        /**
         * @apiNote Loom is a steam age romantic style machine which will contain some works
         *          from hand-crafting wire combination, packer and wiremill. This machine can
         *          also make plant ball to vanilla grasses, like grass and tall grass,
         *          make string to net, e.t.c. Loom and laminator split wiremill related
         *          recipes, just like slicer and polisher.
         * @zenProp loom
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("LOOM_RECIPES")
        val LOOM_RECIPES = RecipeMapBuilder("loom", SimpleRecipeBuilder())
            .itemInputs(3)
            .itemOutputs(2)
            .fluidInputs(1)
            .fluidOutputs(0)
            .itemSlotOverlay(GuiTextures.STRING_SLOT_OVERLAY, false)
            .fluidSlotOverlay(GTLiteGuiTextures.STRING_SLOT_OVERLAY_2, false)
            .progressBar(GuiTextures.PROGRESS_BAR_MAGNET, ProgressWidget.MoveType.HORIZONTAL)
            .sound(GTSoundEvents.COMPRESSOR)
            .build()

        /**
         * @apiNote Just like assembler, laminator used to wrap wire with rubbers, it is
         *          separated from assembler recipes (this is useful for resolved conflicts
         *          in assembler). Laminator and loom is the container of original wiremill
         *          recipes both. The original idea of this machine is from GregTech6, and
         *          vulcanizing press is another rubber industry machine.
         * @zenProp laminator
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("LAMINATOR_RECIPES")
        val LAMINATOR_RECIPES = RecipeMapBuilder("laminator", SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(1)
            .fluidInputs(2)
            .fluidOutputs(0)
            .itemSlotOverlay(GTLiteGuiTextures.CHOPPING_BLOCK_OVERLAY, false)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
            .sound(GTSoundEvents.COOLING)
            .build()

        /**
         * @apiNote This recipe map merged dryer and chemical dehydrator two recipe maps
         *          actually, so it can also call by chemical dryer or food dryer; similar
         *          to this, coagulate processing is also used this recipe map (means
         *          it contained some functions of autoclave yet).
         * @zenProp chemical_dehydrator
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("CHEMICAL_DEHYDRATOR_RECIPES")
        val CHEMICAL_DEHYDRATOR_RECIPES = RecipeMapBuilder("chemical_dehydrator", SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(6)
            .fluidInputs(2)
            .fluidOutputs(3)
            .itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false)
            .progressBar(GuiTextures.PROGRESS_BAR_SIFT)
            .sound(GTSoundEvents.FURNACE)
            .build()

        /**
         * @apiNote Vulcanization processing used to process rubber with sulfur or other
         *          fluids, for some synthetic rubber, this recipe needs other chemistry
         *          components. This machine has steam version, the first rubber is from
         *          this recipe map.
         * @zenProp vulcanizing_press
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("VULCANIZATION_RECIPES")
        val VULCANIZATION_RECIPES = RecipeMapBuilder("vulcanizing_press", SimpleRecipeBuilder())
            .itemInputs(4)
            .itemOutputs(2)
            .fluidInputs(2)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.MOLD_OVERLAY, false, true)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
            .sound(GTSoundEvents.COMBUSTION)
            .build()

        /**
         * @apiNote Vacuum Chamber can be a chemical reactor, also can be an assembler,
         *          this is a very simple container of chemistry in reality world which
         *          will called by vacuum generate processing yet. This machine has steam
         *          machines, it is the prepare work of vacuum tube, which is your first
         *          circuits in total GregTech circuits.
         * @zenProp vacuum_chamber
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("VACUUM_CHAMBER_RECIPES")
        val VACUUM_CHAMBER_RECIPES = RecipeMapBuilder("vacuum_chamber", SimpleRecipeBuilder())
            .itemInputs(4)
            .itemOutputs(1)
            .fluidInputs(2)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false)
            .progressBar(GuiTextures.PROGRESS_BAR_COMPRESS)
            .sound(GTSoundEvents.ASSEMBLER)
            .build()

        /**
         * @zenProp sap_collector
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("SAP_COLLECTOR_RECIPES")
        val SAP_COLLECTOR_RECIPES = RecipeMapBuilder("sap_collector", PseudoMultiRecipeBuilder())
            .itemOutputs(2)
            .fluidInputs(1)
            .fluidOutputs(2)
            .progressBar(GTLiteGuiTextures.PROGRESS_BAR_EXTRACTION, ProgressWidget.MoveType.VERTICAL_DOWNWARDS)
            .sound(GTSoundEvents.DRILL_TOOL)
            .build()

        /**
         * @zenProp coagulation_tank
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("COAGULATION_RECIPES")
        val COAGULATION_RECIPES = RecipeMapBuilder("coagulation_tank", PrimitiveRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(1)
            .fluidInputs(2)
            .sound(GTSoundEvents.COOLING)
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