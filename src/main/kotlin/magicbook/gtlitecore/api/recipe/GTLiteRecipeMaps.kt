package magicbook.gtlitecore.api.recipe

import crafttweaker.annotations.ZenRegister
import gregtech.api.GTValues.EV
import gregtech.api.GTValues.V
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.recipes.RecipeMapBuilder
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.builders.BlastRecipeBuilder
import gregtech.api.recipes.builders.FuelRecipeBuilder
import gregtech.api.recipes.builders.PrimitiveRecipeBuilder
import gregtech.api.recipes.builders.SimpleRecipeBuilder
import gregtech.api.unification.material.Materials
import gregtech.api.util.GTUtility
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import magicbook.gtlitecore.api.recipe.builder.AdvancedFusionRecipeBuilder
import magicbook.gtlitecore.api.recipe.builder.CircuitAssemblyLineRecipeBuilder
import magicbook.gtlitecore.api.recipe.builder.ComponentAssemblyLineRecipeBuilder
import magicbook.gtlitecore.api.recipe.builder.MinimumHeightRecipeBuilder
import magicbook.gtlitecore.api.recipe.builder.MobProximityRecipeBuilder
import magicbook.gtlitecore.api.recipe.builder.NoCoilTemperatureRecipeBuilder
import magicbook.gtlitecore.api.recipe.builder.PseudoMultiRecipeBuilder
import magicbook.gtlitecore.api.recipe.ui.ComponentAssemblyLineUI
import magicbook.gtlitecore.api.recipe.ui.LargeMixerUI
import magicbook.gtlitecore.api.recipe.ui.MiningDroneAirportUI
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.core.sound.GTLiteSoundEvents
import net.minecraft.init.SoundEvents
import net.minecraftforge.fluids.FluidStack
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenProperty
import kotlin.math.max
import kotlin.math.min

/**
 * @zenClass mods.gtlitecore.recipe.RecipeMaps
 *
 * @see [gregtech.api.recipes.RecipeMaps]
 */
@Suppress("MISSING_DEPENDENCY_CLASS", "UnstableApiUsage")
@ZenClass("mods.gtlitecore.recipe.RecipeMaps")
@ZenRegister
class GTLiteRecipeMaps
{

    companion object
    {

        private var COOLANTS = mutableMapOf<FluidStack, FluidStack>().apply {
            put(Materials.Steam.getFluid(570), GTLiteMaterials.SupercriticalSteam.getFluid(570))
            put(GTLiteMaterials.SodiumPotassiumEutatic.getFluid(120), GTLiteMaterials.SupercriticalSodiumPotassiumEutatic.getFluid(120))
            put(GTLiteMaterials.LeadBismuthEutatic.getFluid(60), GTLiteMaterials.SupercriticalLeadBismuthEutatic.getFluid(60))
            put(GTLiteMaterials.LithiumBerylliumFluorides.getFluid(55), GTLiteMaterials.SupercriticalLithiumBerylliumFluorides.getFluid(55))
            put(GTLiteMaterials.LithiumSodiumPotassiumFluorides.getFluid(50), GTLiteMaterials.SupercriticalLithiumSodiumPotassiumFluorides.getFluid(50))
        }
        // -------------------------------------------------------------------------------------------------------------

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
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, false, false)
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

        /**
         * @zenProp greenhouse
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("GREENHOUSE_RECIPES")
        val GREENHOUSE_RECIPES = RecipeMapBuilder("greenhouse", SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(4)
            .fluidInputs(2)
            .fluidOutputs(1)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
            .sound(SoundEvents.BLOCK_CHORUS_FLOWER_GROW)
            .build()

        /**
         * @zenProp bio_reactor
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("BIO_REACTOR_RECIPES")
        val BIO_REACTOR_RECIPES = RecipeMapBuilder("bio_reactor", SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(1)
            .fluidInputs(3)
            .fluidOutputs(2)
            .itemSlotOverlay(GTLiteGuiTextures.DISH_OVERLAY, false)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
            .fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, false, false)
            .fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_4, false, true)
            .fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, true)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
            .sound(GTSoundEvents.CHEMICAL_REACTOR)
            .build()

        /**
         * @zenProp roaster
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("ROASTER_RECIPES")
        val ROASTER_RECIPES = RecipeMapBuilder("roaster", SimpleRecipeBuilder())
            .itemInputs(3)
            .itemOutputs(3)
            .fluidInputs(3)
            .fluidOutputs(3)
            .itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false)
            .itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, true)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true)
            .sound(GTSoundEvents.FURNACE)
            .build()

        /**
         * @zenProp burner_reactor
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("BURNER_REACTOR_RECIPES")
        val BURNER_REACTOR_RECIPES = RecipeMapBuilder("burner_reactor", SimpleRecipeBuilder())
            .itemInputs(3)
            .itemOutputs(3)
            .fluidInputs(3)
            .fluidOutputs(3)
            .itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_1, false, false)
            .itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_2, false, true)
            .itemSlotOverlay(GuiTextures.VIAL_OVERLAY_1, true)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true)
            .progressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE)
            .sound(GTSoundEvents.ARC)
            .build()

        /**
         * @zenProp bath_condenser
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("BATH_CONDENSER_RECIPES")
        val BATH_CONDENSER_RECIPES = RecipeMapBuilder("bath_condenser", SimpleRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(1)
            .fluidInputs(2)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
            .progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR)
            .sound(GTSoundEvents.COOLING)
            .build()

        /**
         * @zenProp cryogenic_reactor
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("CRYOGENIC_REACTOR_RECIPES")
        val CRYOGENIC_REACTOR_RECIPES = RecipeMapBuilder("cryogenic_reactor", SimpleRecipeBuilder())
            .itemInputs(3)
            .itemOutputs(3)
            .fluidInputs(3)
            .fluidOutputs(3)
            .itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_1, false, false)
            .itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_2, false, true)
            .itemSlotOverlay(GuiTextures.VIAL_OVERLAY_1, true, false)
            .itemSlotOverlay(GuiTextures.VIAL_OVERLAY_2, true, true)
            .sound(GTSoundEvents.COOLING)
            .build()

        /**
         * @zenProp large_mixer
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("LARGE_MIXER_RECIPES")
        val LARGE_MIXER_RECIPES = RecipeMapBuilder("large_mixer", SimpleRecipeBuilder())
            .ui { LargeMixerUI(it) }
            .itemInputs(9)
            .itemOutputs(1)
            .fluidInputs(6)
            .fluidOutputs(1)
            .progressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
            .sound(GTSoundEvents.MIXER)
            .build()
            .setSmallRecipeMap(RecipeMaps.MIXER_RECIPES)

        /**
         * @zenProp catalytic_reformer
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("CATALYTIC_REFORMER_RECIPES")
        val CATALYTIC_REFORMER_RECIPES = RecipeMapBuilder("catalytic_reformer", SimpleRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(0)
            .fluidInputs(1)
            .fluidOutputs(4)
            .itemSlotOverlay(GTLiteGuiTextures.PLATE_OVERLAY, false)
            .progressBar(GuiTextures.PROGRESS_BAR_CRACKING)
            .sound(GTSoundEvents.FURNACE)
            .build()

        /**
         * @zenProp large_gas_collector
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("LARGE_GAS_COLLECTOR_RECIPES")
        val LARGE_GAS_COLLECTOR_RECIPES = RecipeMapBuilder("large_gas_collector", SimpleRecipeBuilder())
            .itemInputs(2)
            .fluidOutputs(4)
            .itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false, true)
            .fluidSlotOverlay(GuiTextures.CENTRIFUGE_OVERLAY, true)
            .progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR)
            .sound(GTSoundEvents.COOLING)
            .build()
            .setSmallRecipeMap(RecipeMaps.GAS_COLLECTOR_RECIPES)

        /**
         * @zenProp electric_implosion_compressor
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("ELECTRIC_IMPLOSION_RECIPES")
        val ELECTRIC_IMPLOSION_RECIPES = RecipeMapBuilder("electric_implosion_compressor", SimpleRecipeBuilder())
            .itemInputs(6)
            .fluidInputs(1)
            .itemOutputs(2)
            .itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_1, false)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
            .fluidSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
            .sound(SoundEvents.ENTITY_GENERIC_EXPLODE)
            .build()
            .setSmallRecipeMap(RecipeMaps.IMPLOSION_RECIPES)

        /**
         * Example:
         *
         * ```
         *     GTLiteRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
         *             .circuitMeta(4)
         *             .input(OrePrefix.dust, Materials.Iridium, 2)
         *             .input(OrePrefix.dust, Materials.Platinum, 3)
         *             .input(OrePrefix.dust, Materials.Naquadah, 1)
         *             .fluidInputs(Materials.Oxygen.getFluid(14000))
         *             .fluidOutputs(GTLiteMaterials.IridiumPlatinumNaquadate.getFluid(GTValues.L * 20))
         *             .EUt(GTValues.VA[GTValues.UV].toLong())
         *             .duration(30 * GTLiteValues.SECOND)
         *             .blastFurnaceTemp(10800) // Tritanium
         *             .buildAndRegister()
         * ```
         *
         * Alloy Blast Smelter (ABS) is merged from Gregicality Multiblocks (GCYM), but has many
         * different. The original idea of this RecipeMap is from same name machine in GregTech++
         * and Gregicality (Legacy), and ABS recipes. Different with GCYM's ABS recipes, in these
         * recipes, fluid outputs will be fluid of material but not be molten fluid (which means
         * player do not need to input molten fluids to Vacuum Freezer again, this is ridiculous
         * for player experience by I think ^^). Implementation of ABS recipe automatically
         * generator is too complex, so we just list all classes we used in this step. Pay
         * attention, this internal API will automatically generate all materials which is allowed
         * by `AlloyBlastProducer` and do not have `NO_ALLOY_BLAST_RECIPES` flag or `DISABLE_ALLOY
         * _PROPERTY` for other addition mods.
         *
         * @zenProp alloy_blast_smelter
         *
         * @see magicbook.gtlitecore.api.unification.material.GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES
         * @see magicbook.gtlitecore.api.unification.material.GTLiteMaterialFlags.DISABLE_ALLOY_PROPERTY
         * @see magicbook.gtlitecore.api.unification.material.GTLitePropertyKey.ALLOY_BLAST
         * @see magicbook.gtlitecore.api.unification.material.properties.AlloyBlastProperty
         * @see magicbook.gtlitecore.api.unification.material.properties.AlloyBlastPropertyAdder
         * @see magicbook.gtlitecore.loader.recipe.producer.AlloyBlastRecipeProducer
         * @see magicbook.gtlitecore.loader.recipe.producer.CustomAlloyBlastRecipeProducer
         * @see magicbook.gtlitecore.loader.recipe.handler.MaterialRecipeHandler.generateABSRecipes
         * @see magicbook.gtlitecore.loader.recipe.machine.AlloyBlastSmelterRecipes
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("ALLOY_BLAST_RECIPES")
        val ALLOY_BLAST_RECIPES = RecipeMapBuilder("alloy_blast_smelter", BlastRecipeBuilder())
            .itemInputs(9)
            .fluidInputs(3)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false)
            .itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, true)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true)
            .sound(GTSoundEvents.FURNACE)
            .build()

        /**
         * @zenProp chemical_plant
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("CHEMICAL_PLANT_RECIPES")
        val CHEMICAL_PLANT_RECIPES = RecipeMapBuilder("chemical_plant", SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(4)
            .fluidInputs(5)
            .fluidOutputs(4)
            .itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_1, false, false)
            .itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_2, false, true)
            .itemSlotOverlay(GuiTextures.VIAL_OVERLAY_1, true)
            .fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, false)
            .fluidSlotOverlay(GuiTextures.VIAL_OVERLAY_2, true)
            .progressBar(GTLiteGuiTextures.PROGRESS_BAR_REACTION, ProgressWidget.MoveType.CIRCULAR)
            .sound(GTSoundEvents.CHEMICAL_REACTOR)
            .build()

        /**
         * @zenProp circuit_assembly_line
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("CIRCUIT_ASSEMBLY_LINE_RECIPES")
        val CIRCUIT_ASSEMBLY_LINE_RECIPES = RecipeMapBuilder("circuit_assembly_line", CircuitAssemblyLineRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(6)
            .fluidInputs(1)
            .itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false)
            .progressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, ProgressWidget.MoveType.HORIZONTAL)
            .sound(GTSoundEvents.ASSEMBLER)
            .build()

        /**
         * @zenProp food_processor
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("FOOD_PROCESSOR_RECIPES")
        val FOOD_PROCESSOR_RECIPES = RecipeMapBuilder("food_processor", SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(2)
            .fluidInputs(3)
            .fluidOutputs(1)
            .itemSlotOverlay(GTLiteGuiTextures.DISK_OVERLAY, false)
            .fluidSlotOverlay(GuiTextures.HEATING_OVERLAY_2, false)
            .progressBar(GTLiteGuiTextures.PROGRESS_BAR_FOOD_PROCESSING)
            .sound(GTSoundEvents.ASSEMBLER)
            .build()

        /**
         * @zenProp multicooker
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("MULTICOOKER_RECIPES")
        val MULTICOOKER_RECIPES = RecipeMapBuilder("multicooker", SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(3)
            .fluidInputs(3)
            .fluidOutputs(2)
            .itemSlotOverlay(GuiTextures.HEATING_OVERLAY_1, false)
            .fluidSlotOverlay(GuiTextures.HEATING_OVERLAY_2, false)
            .progressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
            .sound(GTSoundEvents.MIXER)
            .build()

        /**
         * @zenProp mining_drone_airport
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("MINING_DRONE_RECIPES")
        val MINING_DRONE_RECIPES = RecipeMapBuilder("mining_drone_airport", SimpleRecipeBuilder())
            .ui { MiningDroneAirportUI(it) }
            .itemInputs(4)
            .itemOutputs(16)
            .fluidInputs(1)
            .sound(GTSoundEvents.COMPRESSOR)
            .build()

        /**
         * @zenProp cvd_unit
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("CVD_RECIPES")
        val CVD_RECIPES = RecipeMapBuilder("cvd_unit", NoCoilTemperatureRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(3)
            .fluidInputs(3)
            .fluidOutputs(3)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
            .sound(GTSoundEvents.COOLING)
            .build()

        /**
         * @zenProp crystallization_crucible
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("CRYSTALLIZATION_RECIPES")
        val CRYSTALLIZATION_RECIPES = RecipeMapBuilder("crystallization_crucible", BlastRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(1)
            .fluidInputs(3)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
            .itemSlotOverlay(GuiTextures.CRYSTAL_OVERLAY, true)
            .progressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION)
            .sound(GTSoundEvents.FURNACE)
            .build()

        /**
         * @zenProp nanoscale_fabricator
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("MOLECULAR_BEAM_RECIPES")
        val MOLECULAR_BEAM_RECIPES = RecipeMapBuilder("nanoscale_fabricator", NoCoilTemperatureRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(1)
            .fluidInputs(2)
            .itemSlotOverlay(GTLiteGuiTextures.NANOSCALE_OVERLAY_1, false)
            .itemSlotOverlay(GTLiteGuiTextures.NANOSCALE_OVERLAY_1, true)
            .fluidSlotOverlay(GTLiteGuiTextures.NANOSCALE_OVERLAY_2, false)
            .fluidSlotOverlay(GTLiteGuiTextures.NANOSCALE_OVERLAY_2, true)
            .progressBar(GTLiteGuiTextures.PROGRESS_BAR_NANOSCALE)
            .sound(GTSoundEvents.ELECTROLYZER)
            .build()

        /**
         * @zenProp sonicator
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("SONICATION_RECIPES")
        val SONICATION_RECIPES = RecipeMapBuilder("sonicator", SimpleRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(1)
            .fluidInputs(2)
            .fluidOutputs(2)
            .itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false)
            .itemSlotOverlay(GTLiteGuiTextures.FOIL_OVERLAY, true)
            .fluidSlotOverlay(GuiTextures.BREWER_OVERLAY, false, false)
            .fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, false, true)
            .fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_4, true)
            .progressBar(GuiTextures.PROGRESS_BAR_EXTRACT)
            .sound(GTSoundEvents.CENTRIFUGE)
            .build()

        /**
         * @zenProp laser_induced_cvd_unit
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("LASER_CVD_RECIPES")
        val LASER_CVD_RECIPES = RecipeMapBuilder("laser_induced_cvd_unit", NoCoilTemperatureRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(3)
            .fluidInputs(4)
            .fluidOutputs(3)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
            .sound(GTSoundEvents.COOLING)
            .build()
            .setSmallRecipeMap(CVD_RECIPES)

        /**
         * @zenProp mob_extractor
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("MOB_EXTRACTOR_RECIPES")
        val MOB_EXTRACTOR_RECIPES = RecipeMapBuilder("mob_extractor", MobProximityRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(1)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false)
            .progressBar(GuiTextures.PROGRESS_BAR_EXTRACT)
            .sound(GTSoundEvents.COMPRESSOR)
            .build()

        /**
         * @zenProp bedrock_drilling_rig
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("DRILLING_RECIPES")
        val DRILLING_RECIPES = RecipeMapBuilder("bedrock_drilling_rig", SimpleRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(1)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.CRUSHED_ORE_OVERLAY, false, true)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
            .sound(GTSoundEvents.MACERATOR)
            .build()

        /**
         * @zenProp advanced_fusion_reactor
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("ADVANCED_FUSION_RECIPES")
        val ADVANCED_FUSION_RECIPES = RecipeMapBuilder("advanced_fusion_reactor", AdvancedFusionRecipeBuilder())
            .fluidInputs(3)
            .fluidOutputs(2)
            .progressBar(GuiTextures.PROGRESS_BAR_FUSION)
            .sound(GTSoundEvents.ARC)
            .build()
            .setSmallRecipeMap(RecipeMaps.FUSION_RECIPES)

        /**
         * @zenProp component_assembly_line
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("COMPONENT_ASSEMBLY_LINE_RECIPES")
        val COMPONENT_ASSEMBLY_LINE_RECIPES = RecipeMapBuilder("component_assembly_line", ComponentAssemblyLineRecipeBuilder())
            .ui { ComponentAssemblyLineUI(it) }
            .itemInputs(12)
            .itemOutputs(1)
            .fluidInputs(12)
            .sound(GTSoundEvents.ASSEMBLER)
            .build()

        /**
         * @zenProp cosmic_ray_detector
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("COSMIC_RAY_DETECTING_RECIPES")
        val COSMIC_RAY_DETECTING_RECIPES = RecipeMapBuilder("cosmic_ray_detector", MinimumHeightRecipeBuilder())
            .itemInputs(1)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false)
            .sound(GTSoundEvents.ARC)
            .build()

        /**
         * @zenProp stellar_forge
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("STELLAR_FORGE_RECIPES")
        val STELLAR_FORGE_RECIPES = RecipeMapBuilder("stellar_forge", SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(6)
            .fluidInputs(6)
            .fluidOutputs(6)
            .itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false)
            .itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, true)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false)
            .fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true)
            .progressBar(GTLiteGuiTextures.PROGRESS_BAR_NOVA_EXPLOSION)
            .sound(GTLiteSoundEvents.STELLAR_FORGE)
            .generator()
            .build()

        // -------------------------------------------------------------------------------------------------------------

        /**
         * @zenProp nuclear_reactor
         */
        @ZenProperty
        @JvmStatic
        @get:JvmName("NUCLEAR_FUELS")
        val NUCLEAR_FUELS = RecipeMapBuilder("nuclear_reactor", FuelRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(1)
            .fluidInputs(1)
            .fluidOutputs(1)
            .sound(GTSoundEvents.FIRE)
            .build()

        // -------------------------------------------------------------------------------------------------------------
        @JvmStatic
        fun preInit()
        {
            // Copying mixer recipes to large mixer recipe map.
            RecipeMaps.MIXER_RECIPES.onRecipeBuild(GTLiteUtility.gtliteId("mixer_copy")) { recipeBuilder ->
                LARGE_MIXER_RECIPES.recipeBuilder()
                    .inputs(*recipeBuilder.inputs.toTypedArray())
                    .fluidInputs(recipeBuilder.fluidInputs)
                    .outputs(recipeBuilder.outputs)
                    .chancedOutputs(recipeBuilder.chancedOutputs)
                    .fluidOutputs(recipeBuilder.fluidOutputs)
                    .chancedFluidOutputs(recipeBuilder.chancedFluidOutputs)
                    .cleanroom(recipeBuilder.cleanroom)
                    .duration(recipeBuilder.duration)
                    .EUt(recipeBuilder.eUt)
                    .buildAndRegister()
            }

            // Copying cvd recipes to laser-induced/plasma-enriched cvd recipe map.
            CVD_RECIPES.onRecipeBuild(GTLiteUtility.gtliteId("advanced_cvd_copy")) { recipeBuilder ->

                val inputsCopied = recipeBuilder.inputs.toTypedArray()
                val fluidInputsCopied = recipeBuilder.fluidInputs
                val outputsCopied = recipeBuilder.outputs
                val chancedOutputsCopied = recipeBuilder.chancedOutputs
                val fluidOutputsCopied = recipeBuilder.fluidOutputs
                val chancedFluidOutputsCopied = recipeBuilder.chancedFluidOutputs
                val cleanroomCopied = recipeBuilder.cleanroom
                val eUtCopied = recipeBuilder.eUt
                val durationCopied = recipeBuilder.duration

                // Laser-Induced CVD recipes required laser-induced gas for protective
                // and ensure transmissive correct.
                for (laserInducedGas in arrayOf(Materials.Helium.getFluid(16000),
                    Materials.Neon.getFluid(16000),
                    Materials.Argon.getFluid(16000),
                    Materials.Krypton.getFluid(16000),
                    Materials.Xenon.getFluid(16000)))
                {
                    LASER_CVD_RECIPES.recipeBuilder()
                        .inputs(*inputsCopied)
                        .fluidInputs(fluidInputsCopied)
                        .notConsumable(laserInducedGas)
                        .outputs(outputsCopied)
                        .chancedOutputs(chancedOutputsCopied)
                        .fluidOutputs(fluidOutputsCopied)
                        .chancedFluidOutputs(chancedFluidOutputsCopied)
                        .cleanroom(cleanroomCopied)
                        .EUt(eUtCopied)
                        .duration(durationCopied)
                        .hidden()
                        .buildAndRegister()
                } // TODO Maybe another choice is lasers (laser emitters).

                // TODO PLASMA_CVD_RECIPES
            }

            // Add advanced fusion reactor recipes from fusion reactor recipes.
            RecipeMaps.FUSION_RECIPES.onRecipeBuild(GTLiteUtility.gtliteId("adv_fusion_moderator")) { recipeBuilder ->
                val euStart = recipeBuilder.euToStart + recipeBuilder.eUt * recipeBuilder.duration // (euToStart + EUt * duration) * euReturn / 100
                val tier = when (recipeBuilder.euToStart)
                {
                    in 0L .. 160_000_000L -> 1 // MK1
                    in 160_000_001L .. 320_000_000L -> 2 // MK2
                    in 320_000_001L .. 640_000_000L -> 3 // MK3
                    in 640_000_001L .. 1_280_000_000L -> 4 // MK4
                    in 1_280_000_001L .. 2_560_000_000L -> 5 // MK5
                    else -> 0 // Error
                }
                COOLANTS.forEach { (inputFluid, outputFluid) ->
                    val amount = max((euStart / (V[EV] * 10000)) * inputFluid.amount, 1).toInt()
                    ADVANCED_FUSION_RECIPES.recipeBuilder()
                        .inputs(*recipeBuilder.inputs.toTypedArray())
                        .fluidInputs(recipeBuilder.fluidInputs)
                        .fluidInputs(FluidStack(inputFluid, amount))
                        .outputs(recipeBuilder.outputs)
                        .chancedOutputs(recipeBuilder.chancedOutputs)
                        .fluidOutputs(recipeBuilder.fluidOutputs)
                        .fluidOutputs(FluidStack(outputFluid, amount))
                        .chancedFluidOutputs(recipeBuilder.chancedFluidOutputs)
                        .cleanroom(recipeBuilder.cleanroom)
                        .duration(recipeBuilder.duration)
                        .EUt(recipeBuilder.eUt)
                        .tier(tier)
                        .buildAndRegister()
                }

            }

            // (2,6,1,6) -> (2,6,2,6)
            RecipeMaps.ELECTROLYZER_RECIPES.maxFluidInputs = 2

            // (1,6,0,0) -> (1,9,2,2)
            RecipeMaps.SIFTER_RECIPES.maxFluidInputs = 2
            RecipeMaps.SIFTER_RECIPES.maxOutputs = 9
            RecipeMaps.SIFTER_RECIPES.maxFluidOutputs = 2

            // (1,1,0,0) -> (2,2,2,2)
            RecipeMaps.FORGE_HAMMER_RECIPES.maxInputs = 2
            RecipeMaps.FORGE_HAMMER_RECIPES.maxFluidInputs = 2
            RecipeMaps.FORGE_HAMMER_RECIPES.maxOutputs = 2
            RecipeMaps.FORGE_HAMMER_RECIPES.maxFluidOutputs = 2

            // (1,1,2,0)
            RecipeMaps.MASS_FABRICATOR_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false)

            // (1,1,2,1) -> (1,1,3,1)
            RecipeMaps.REPLICATOR_RECIPES.maxFluidInputs = 3
            RecipeMaps.REPLICATOR_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
            RecipeMaps.REPLICATOR_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, true)

            // (1,1,9,1) -> (4,2,9,1)
            RecipeMaps.ARC_FURNACE_RECIPES.maxInputs = 4
            RecipeMaps.ARC_FURNACE_RECIPES.maxFluidInputs = 2
            RecipeMaps.ARC_FURNACE_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.LIGHTNING_OVERLAY_1, false)
            RecipeMaps.ARC_FURNACE_RECIPES.recipeMapUI.setFluidSlotOverlay(GuiTextures.LIGHTNING_OVERLAY_2, false)

            // (2,1,0,0) -> (4,4,2,2)
            RecipeMaps.LASER_ENGRAVER_RECIPES.maxInputs = 4
            RecipeMaps.LASER_ENGRAVER_RECIPES.maxOutputs = 4
            RecipeMaps.LASER_ENGRAVER_RECIPES.maxFluidInputs = 2
            RecipeMaps.LASER_ENGRAVER_RECIPES.maxFluidOutputs = 2
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.LENS_OVERLAY, false)

            // (1,0,1,1) -> (1,1,1,1)
            RecipeMaps.BREWING_RECIPES.maxOutputs = 1

            // (1,1,2,0) -> (1,1,4,0)
            RecipeMaps.CUTTER_RECIPES.maxOutputs = 4

            // (2,1,1,1) -> (2,1,2,1)
            RecipeMaps.FERMENTING_RECIPES.maxInputs = 2
            RecipeMaps.FERMENTING_RECIPES.maxFluidInputs = 2
            RecipeMaps.FERMENTING_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false, false)

            RecipeMaps.THERMAL_CENTRIFUGE_RECIPES.maxOutputs = 4
        }

    }

}