package gregtechlite.gtlitecore.api.recipe

import com.morphismmc.morphismlib.integration.Mods
import crafttweaker.annotations.ZenRegister
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.RecipeMapBuilder
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.builders.BlastRecipeBuilder
import gregtech.api.recipes.builders.FuelRecipeBuilder
import gregtech.api.recipes.builders.PrimitiveRecipeBuilder
import gregtech.api.recipes.builders.SimpleRecipeBuilder
import gregtech.api.unification.material.Materials
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.duration
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures
import gregtechlite.gtlitecore.api.recipe.builder.AccelerationTrackRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.AdvancedFusionRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.CircuitAssemblyLineRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.ComponentAssemblyLineRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.MinimumHeightRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.MobProximityRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.NanoForgeRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.NoCoilTemperatureRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.PCBFactoryRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.PseudoMultiRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.builder.QuantumForceTransformerRecipeBuilder
import gregtechlite.gtlitecore.api.recipe.map.PseudoGroupRecipeMapBuilder
import gregtechlite.gtlitecore.api.recipe.ui.AntiGravityAssemblyChamberUI
import gregtechlite.gtlitecore.api.recipe.ui.AntimatterForgeUI
import gregtechlite.gtlitecore.api.recipe.ui.ComponentAssemblyLineUI
import gregtechlite.gtlitecore.api.recipe.ui.LargeMixerUI
import gregtechlite.gtlitecore.api.recipe.ui.MiningDroneAirportUI
import gregtechlite.gtlitecore.api.recipe.ui.NanoAssemblyMatrixUI
import gregtechlite.gtlitecore.api.recipe.ui.SpaceAssemblerUI
import gregtechlite.gtlitecore.api.recipe.ui.StellarForgeUI
import gregtechlite.gtlitecore.core.sound.GTLiteSoundEvents
import net.minecraft.init.SoundEvents
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenProperty
import kotlin.math.max
import kotlin.math.min

/**
 * @zenClass mods.gtlitecore.recipe.RecipeMaps
 *
 * @see [RecipeMaps]
 */
@Suppress("UnstableApiUsage")
@ZenClass("mods.gtlitecore.recipe.RecipeMaps")
@ZenRegister
object GTLiteRecipeMaps
{

    // @formatter:off

    // region Single Machine RecipeMaps

    @ZenProperty
    @JvmField
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
        .onBuild(GTLiteMod.id("polisher_fluid")) { recipeBuilder ->
            // Same like cutting machine logic, but polisher used more water as coolant, confirm three cutting function
            // machines, the water required of each recipe is slicer < this < cutter.
            if (recipeBuilder.fluidInputs.isEmpty())
            {
                val eut = recipeBuilder.eUt
                val duration = recipeBuilder.duration

                // Common water recipes.
                recipeBuilder.copy()
                    .fluidInputs(Materials.Water.getFluid(max(4, min(500, duration * eut / 320)).toInt()))
                    .duration(duration * 2)
                    .buildAndRegister()

                // Distilled water recipes, faster than water recipes.
                recipeBuilder.copy()
                    .fluidInputs(Materials.DistilledWater.getFluid(max(3, min(250, duration * eut / 426)).toInt()))
                    .duration(duration * 1.5)
                    .buildAndRegister()

                // Do not call buildAndRegister() as we are mutating the original recipe and already in the middle of a
                // buildAndRegister() copy call. Adding a second call will result in duplicate recipe generation attempts.
                recipeBuilder.fluidInputs(Materials.Lubricant.getFluid(max(1, min(125, duration * eut / 1280)).toInt()))
                    .duration(max(1, duration))
                }
            }
        .build()

    /**
     * @zenProp slicer
     */
    @ZenProperty
    @JvmField
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
        .onBuild(GTLiteMod.id("slicer_fluid")) { recipeBuilder ->
            // Same like cutting machine logic, but slicer used quite little water as coolant, confirm three cutting
            // function machines, the water required of each recipe is this < polisher < cutter.
            if (recipeBuilder.fluidInputs.isEmpty())
            {
                val eut = recipeBuilder.eUt
                val duration = recipeBuilder.duration

                // Common water recipes.
                recipeBuilder.copy()
                    .fluidInputs(Materials.Water.getFluid(max(4, min(200, duration * eut / 320)).toInt()))
                    .duration(duration * 2)
                    .buildAndRegister()

                // Distilled water recipes, faster than water recipes.
                recipeBuilder.copy()
                    .fluidInputs(Materials.DistilledWater.getFluid(max(3, min(100, duration * eut / 426)).toInt()))
                    .duration(duration * 1.5)
                    .buildAndRegister()

                // Do not call buildAndRegister() as we are mutating the original recipe and already in the middle of a
                // buildAndRegister() copy call. Adding a second call will result in duplicate recipe generation attempts.
                recipeBuilder.fluidInputs(Materials.Lubricant.getFluid(max(1, min(50, duration * eut / 1280)).toInt()))
                    .duration(max(1, duration))
            }
        }
    .build()

    /**
     * @zenProp tool_caster
     */
    @ZenProperty
    @JvmField
    val TOOL_CASTER_RECIPES = RecipeMapBuilder("tool_caster", SimpleRecipeBuilder())
        .itemInputs(1)
        .itemOutputs(9)
        .fluidInputs(1)
        .itemSlotOverlay(GuiTextures.MOLD_OVERLAY, false)
        .progressBar(GuiTextures.PROGRESS_BAR_UNPACKER)
        .sound(GTSoundEvents.FORGE_HAMMER)
        .build()

    /**
     * @zenProp loom
     */
    @ZenProperty
    @JvmField
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
     * @zenProp laminator
     */
    @ZenProperty
    @JvmField
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
     * @zenProp chemical_dehydrator
     */
    @ZenProperty
    @JvmField
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
     * @zenProp vulcanizing_press
     */
    @ZenProperty
    @JvmField
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
     * @zenProp vacuum_chamber
     */
    @ZenProperty
    @JvmField
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
    @JvmField
    val SAP_COLLECTOR_RECIPES = RecipeMapBuilder("sap_collector", PseudoMultiRecipeBuilder())
        .itemOutputs(2)
        .fluidInputs(1)
        .fluidOutputs(2)
        .progressBar(GTLiteGuiTextures.PROGRESS_BAR_EXTRACTION, ProgressWidget.MoveType.VERTICAL_DOWNWARDS)
        .sound(GTSoundEvents.DRILL_TOOL)
        .build()

    /**
     * @zenProp greenhouse
     */
    @ZenProperty
    @JvmField
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
    @JvmField
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
    @JvmField
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
    @JvmField
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
    @JvmField
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
    @JvmField
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
     * @zenProp food_processor
     */
    @ZenProperty
    @JvmField
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
    @JvmField
    val MULTICOOKER_RECIPES = RecipeMapBuilder("multicooker", SimpleRecipeBuilder())
        .itemInputs(6)
        .itemOutputs(3)
        .fluidInputs(3)
        .fluidOutputs(2)
        .itemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
        .fluidSlotOverlay(GuiTextures.HEATING_OVERLAY_2, false)
        .progressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
        .sound(GTSoundEvents.MIXER)
        .build()

    /**
     * @zenProp mob_extractor
     */
    @ZenProperty
    @JvmField
    val MOB_EXTRACTOR_RECIPES = RecipeMapBuilder("mob_extractor", MobProximityRecipeBuilder())
        .itemInputs(1)
        .itemOutputs(1)
        .fluidOutputs(1)
        .itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false)
        .progressBar(GuiTextures.PROGRESS_BAR_EXTRACT)
        .sound(GTSoundEvents.COMPRESSOR)
        .build()

    /**
     * @zenProp bio_simulator
     */
    @ZenProperty
    @JvmField
    val BIO_SIMULATOR_RECIPES = RecipeMapBuilder("bio_simulator", SimpleRecipeBuilder())
        .itemInputs(2)
        .itemOutputs(1)
        .itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false, true)
        .progressBar(GTLiteGuiTextures.PROGRESS_BAR_SWORD)
        .sound(GTSoundEvents.ELECTROLYZER)
        .build()

    // endregion

    // region Multiblock Machine RecipeMaps

    /**
     * @zenProp coagulation_tank
     */
    @ZenProperty
    @JvmField
    val COAGULATION_RECIPES = RecipeMapBuilder("coagulation_tank", PrimitiveRecipeBuilder())
        .itemInputs(2)
        .itemOutputs(1)
        .fluidInputs(2)
        .sound(GTSoundEvents.COOLING)
        .build()

    /**
     * @zenProp large_mixer
     */
    @ZenProperty
    @JvmField
    val LARGE_MIXER_RECIPES: RecipeMap<SimpleRecipeBuilder> = RecipeMapBuilder("large_mixer", SimpleRecipeBuilder())
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
    @JvmField
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
    @JvmField
    val LARGE_GAS_COLLECTOR_RECIPES: RecipeMap<SimpleRecipeBuilder> = RecipeMapBuilder("large_gas_collector", SimpleRecipeBuilder())
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
    @JvmField
    val ELECTRIC_IMPLOSION_RECIPES: RecipeMap<SimpleRecipeBuilder> = RecipeMapBuilder("electric_implosion_compressor", SimpleRecipeBuilder())
        .itemInputs(6)
        .fluidInputs(1)
        .itemOutputs(2)
        .fluidOutputs(1)
        .itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_1, false)
        .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
        .fluidSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false)
        .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
        .sound(SoundEvents.ENTITY_GENERIC_EXPLODE)
        .build()
        .setSmallRecipeMap(RecipeMaps.IMPLOSION_RECIPES)

    /**
     * @zenProp alloy_blast_smelter
     *
     * @see gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES
     * @see gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags.DISABLE_ALLOY_PROPERTY
     * @see gregtechlite.gtlitecore.api.unification.material.properties.GTLitePropertyKey.ALLOY_BLAST
     * @see gregtechlite.gtlitecore.api.unification.material.properties.AlloyBlastProperty
     * @see gregtechlite.gtlitecore.api.unification.material.properties.AlloyBlastPropertyAdder
     * @see gregtechlite.gtlitecore.loader.recipe.producer.AlloyBlastRecipeProducer
     * @see gregtechlite.gtlitecore.loader.recipe.producer.CustomAlloyBlastRecipeProducer
     * @see gregtechlite.gtlitecore.loader.recipe.handler.MaterialRecipeHandler.generateABSRecipes
     * @see gregtechlite.gtlitecore.loader.recipe.machine.AlloyBlastSmelterRecipes
     */
    @ZenProperty
    @JvmField
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
    @JvmField
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
    @JvmField
    val CIRCUIT_ASSEMBLY_LINE_RECIPES = RecipeMapBuilder("circuit_assembly_line", CircuitAssemblyLineRecipeBuilder())
        .itemInputs(6)
        .itemOutputs(6)
        .fluidInputs(1)
        .itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false)
        .progressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, ProgressWidget.MoveType.HORIZONTAL)
        .sound(GTSoundEvents.ASSEMBLER)
        .onBuild(GTLiteMod.id("circ_ass_copy")) { builder ->

            // Mode 1: Original CAL recipes.
            ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(*builder.inputs.toTypedArray())
                .fluidInputs(builder.fluidInputs)
                .outputs(*builder.outputs.toTypedArray())
                .chancedOutputs(builder.chancedOutputs)
                .fluidOutputs(builder.fluidOutputs)
                .chancedFluidOutputs(builder.chancedFluidOutputs)
                .cleanroom(builder.cleanroom)
                .duration(builder.duration)
                .EUt(builder.eUt)
                .buildAndRegister()


            // Mode 2: 64x base buff with original CAL recipes (the original buff is 16, so it is actual 1024x buff).
            val inputs = builder.inputs
                .filterNotNull()
                .map { it.copyWithAmount(it.amount * 64) }
                .toTypedArray()

            val outputs = builder.outputs
                .filterNotNull()
                .map { it.copy(it.count * 64) }
                .toTypedArray()

            val fluidInputs = builder.fluidInputs
                .filterNotNull()
                .map { it.copyWithAmount(it.amount * 64) }
                .toList()

            ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(*inputs)
                .fluidInputs(fluidInputs)
                .outputs(*outputs)
                .chancedOutputs(builder.chancedOutputs)
                .fluidOutputs(builder.fluidOutputs)
                .chancedFluidOutputs(builder.chancedFluidOutputs)
                .cleanroom(builder.cleanroom)
                .duration(builder.duration * 64)
                .EUt(builder.eUt)
                .buildAndRegister()
        }
        .build()

    /**
     * @zenProp mining_drone_airport
     */
    @ZenProperty
    @JvmField
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
    @JvmField
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
    @JvmField
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
    @JvmField
    val MOLECULAR_BEAM_RECIPES = RecipeMapBuilder("nanoscale_fabricator", NoCoilTemperatureRecipeBuilder())
        .itemInputs(6)
        .itemOutputs(2)
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
    @JvmField
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
    @JvmField
    val LASER_CVD_RECIPES: RecipeMap<NoCoilTemperatureRecipeBuilder> = RecipeMapBuilder("laser_induced_cvd_unit", NoCoilTemperatureRecipeBuilder())
        .itemInputs(2)
        .itemOutputs(3)
        .fluidInputs(4)
        .fluidOutputs(3)
        .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
        .sound(GTSoundEvents.COOLING)
        .build()
        .setSmallRecipeMap(CVD_RECIPES)

    /**
     * @zenProp plasma_enhanced_cvd_unit
     */
    @ZenProperty
    @JvmField
    val PLASMA_CVD_RECIPES: RecipeMap<NoCoilTemperatureRecipeBuilder> = RecipeMapBuilder("plasma_enhanced_cvd_unit", NoCoilTemperatureRecipeBuilder())
        .itemInputs(3)
        .itemOutputs(3)
        .fluidInputs(3)
        .fluidOutputs(3)
        .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
        .sound(GTSoundEvents.COOLING)
        .build()
        .setSmallRecipeMap(CVD_RECIPES)

    /**
     * @zenProp bedrock_drilling_rig
     */
    @ZenProperty
    @JvmField
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
    @JvmField
    val ADVANCED_FUSION_RECIPES: RecipeMap<AdvancedFusionRecipeBuilder> = RecipeMapBuilder("advanced_fusion_reactor", AdvancedFusionRecipeBuilder())
        .fluidInputs(3)
        .fluidOutputs(2)
        .progressBar(GuiTextures.PROGRESS_BAR_FUSION)
        .sound(GTSoundEvents.ARC)
        .build()

    /**
     * @zenProp component_assembly_line
     */
    @ZenProperty
    @JvmField
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
    @JvmField
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
    @JvmField
    val STELLAR_FORGE_RECIPES = RecipeMapBuilder("stellar_forge", SimpleRecipeBuilder())
        .ui { StellarForgeUI(it) }
        .itemInputs(9)
        .itemOutputs(9)
        .fluidInputs(9)
        .fluidOutputs(9)
        .sound(GTLiteSoundEvents.STELLAR_FORGE)
        .build()

    /**
     * @zenProp pcb_factory
     */
    @ZenProperty
    @JvmField
    val PCB_FACTORY_RECIPES = RecipeMapBuilder("pcb_factory", PCBFactoryRecipeBuilder())
        .itemInputs(6)
        .itemOutputs(9)
        .fluidInputs(3)
        .itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false)
        .itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, true)
        .progressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER)
        .sound(GTSoundEvents.ASSEMBLER)
        .build()

    /**
     * @zenProp nano_forge
     */
    @ZenProperty
    @JvmField
    val NANO_FORGE_RECIPES = RecipeMapBuilder("nano_forge", NanoForgeRecipeBuilder())
        .itemInputs(6)
        .itemOutputs(1)
        .fluidInputs(3)
        .itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false)
        .itemSlotOverlay(GuiTextures.CENTRIFUGE_OVERLAY, true)
        .sound(GTSoundEvents.FURNACE)
        .build()

    /**
     * @zenProp quantum_force_transformer
     */
    @ZenProperty
    @JvmField
    val QUANTUM_FORCE_TRANSFORMER_RECIPES = RecipeMapBuilder("quantum_force_transformer", QuantumForceTransformerRecipeBuilder())
        .itemInputs(6)
        .itemOutputs(6)
        .fluidInputs(6)
        .fluidOutputs(6)
        .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
        .sound(GTSoundEvents.ARC)
        .build()

    /**
     * @zenProp antimatter_forge
     */
    @ZenProperty
    @JvmField
    val ANTIMATTER_FORGE_RECIPES = RecipeMapBuilder("antimatter_forge", SimpleRecipeBuilder())
        .ui { AntimatterForgeUI(it) }
        .itemInputs(1)
        .fluidInputs(21)
        .fluidOutputs(1)
        .sound(GTLiteSoundEvents.STELLAR_FORGE)
        .build()

    /**
     * @zenProp space_assembler
     */
    @ZenProperty
    @JvmField
    val SPACE_ASSEMBLER_RECIPES = RecipeMapBuilder("space_assembler", AccelerationTrackRecipeBuilder())
        .ui { SpaceAssemblerUI(it) }
        .itemInputs(16)
        .itemOutputs(1)
        .fluidInputs(4)
        .sound(GTSoundEvents.ASSEMBLER)
        .build()

    /**
     * @zenProp entrodynamically_phase_changer
     */
    @ZenProperty
    @JvmField
    val TOPOLOGICAL_ORDER_CHANGING_RECIPES: RecipeMap<BlastRecipeBuilder> = RecipeMapBuilder("entrodynamically_phase_changer", BlastRecipeBuilder())
        .itemInputs(6)
        .itemOutputs(1)
        .fluidInputs(1)
        .fluidOutputs(1)
        .progressBar(GTLiteGuiTextures.PROGRESS_BAR_PHASE_CHANGE)
        .sound(GTSoundEvents.FURNACE)
        .build()
        .setSmallRecipeMap(RecipeMaps.BLAST_RECIPES)

    /**
     * @zenProp plasma_arc_transmitter
     */
    @ZenProperty
    @JvmField
    val PLASMA_ARC_TRANSMITTER_RECIPES = PseudoGroupRecipeMapBuilder("plasma_arc_transmitter", SimpleRecipeBuilder())
        .group(RecipeMaps.ARC_FURNACE_RECIPES, RecipeMaps.ALLOY_SMELTER_RECIPES)
        .itemInputs(4)
        .itemOutputs(9)
        .fluidInputs(2)
        .fluidOutputs(1)
        .build()

    /**
     * @zenProp nano_assembly_matrix
     */
    @ZenProperty
    @JvmField
    val NANO_ASSEMBLY_MATRIX_RECIPES: RecipeMap<SimpleRecipeBuilder> = RecipeMapBuilder("nano_assembly_matrix", SimpleRecipeBuilder())
        .ui { NanoAssemblyMatrixUI(it) }
        .itemInputs(16)
        .itemOutputs(1)
        .fluidInputs(4)
        .sound(GTSoundEvents.ASSEMBLER)
        .build() // AssLine smallRecipeMap actual

    /**
     * @zenProp anti_gravity_assembly_chamber
     */
    @ZenProperty
    @JvmField
    val ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES: RecipeMap<SimpleRecipeBuilder> = RecipeMapBuilder("anti_gravity_assembly_chamber", SimpleRecipeBuilder())
        .ui { AntiGravityAssemblyChamberUI(it) }
        .itemInputs(16)
        .itemOutputs(4)
        .fluidInputs(12)
        .sound(GTSoundEvents.ASSEMBLER)
        .build() // CircAssLine smallRecipeMap actual

    // endregion

    // region Generator Fuel RecipeMaps

    /**
     * @zenProp high_pressure_steam_turbine
     */
    @ZenProperty
    @JvmField
    val HOT_COOLANT_TURBINE_FUELS = RecipeMapBuilder("hot_coolant_turbine", FuelRecipeBuilder())
        .fluidInputs(1)
        .fluidOutputs(1)
        .fluidSlotOverlay(GuiTextures.DARK_CANISTER_OVERLAY, false, true)
        .progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR)
        .sound(GTSoundEvents.TURBINE)
        .generator()
        .allowEmptyOutputs()
        .build()

    /**
     * @zenProp supercritical_fluid_turbine
     */
    @ZenProperty
    @JvmField
    val SUPERCRITICAL_FLUID_TURBINE_FUELS = RecipeMapBuilder("supercritical_fluid_turbine", FuelRecipeBuilder())
        .fluidInputs(1)
        .fluidOutputs(1)
        .fluidSlotOverlay(GuiTextures.DARK_CANISTER_OVERLAY, false, true)
        .progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR)
        .sound(GTSoundEvents.TURBINE)
        .generator()
        .allowEmptyOutputs()
        .build()

    /**
     * @zenProp nuclear_reactor
     */
    @ZenProperty
    @JvmField
    val NUCLEAR_FUELS = RecipeMapBuilder("nuclear_reactor", FuelRecipeBuilder())
        .itemInputs(1)
        .itemOutputs(1)
        .fluidInputs(1)
        .fluidOutputs(1)
        .sound(GTSoundEvents.FIRE)
        .generator()
        .build()

    /**
     * @zenProp rocket_engine
     */
    @ZenProperty
    @JvmField
    val ROCKET_ENGINE_FUELS = RecipeMapBuilder("rocket_engine", FuelRecipeBuilder())
        .fluidInputs(1)
        .allowEmptyOutputs()
        .sound(GTSoundEvents.COMBUSTION)
        .generator()
        .build()

    /**
     * @zenProp naquadah_reactor
     */
    @ZenProperty
    @JvmField
    val NAQUADAH_REACTOR_FUELS = RecipeMapBuilder("naquadah_reactor", FuelRecipeBuilder())
        .fluidInputs(1)
        .allowEmptyOutputs()
        .sound(GTSoundEvents.ARC)
        .generator()
        .build()

    /**
     * @zenProp antimatter_generator
     */
    @ZenProperty
    @JvmField
    val ANTIMATTER_GENERATOR_FUELS = RecipeMapBuilder("antimatter_generator", FuelRecipeBuilder())
        .fluidInputs(2)
        .allowEmptyOutputs()
        .sound(GTSoundEvents.ARC)
        .generator()
        .build()

    /**
     * @zenProp acid_generator
     */
    @ZenProperty
    @JvmField
    val ACID_GENERATOR_FUELS = RecipeMapBuilder("acid_generator", FuelRecipeBuilder())
        .fluidInputs(1)
        .allowEmptyOutputs()
        .sound(GTSoundEvents.BATH)
        .generator()
        .build()

    // endregion

    @JvmStatic
    fun preInit()
    {
        RecipeMaps.ELECTROLYZER_RECIPES.maxFluidInputs = 2

        RecipeMaps.SIFTER_RECIPES.maxInputs = 2
        RecipeMaps.SIFTER_RECIPES.maxFluidInputs = 2
        RecipeMaps.SIFTER_RECIPES.maxOutputs = 9
        RecipeMaps.SIFTER_RECIPES.maxFluidOutputs = 2
        RecipeMaps.SIFTER_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false)

        RecipeMaps.FORGE_HAMMER_RECIPES.maxInputs = 2
        RecipeMaps.FORGE_HAMMER_RECIPES.maxFluidInputs = 2
        RecipeMaps.FORGE_HAMMER_RECIPES.maxOutputs = 2
        RecipeMaps.FORGE_HAMMER_RECIPES.maxFluidOutputs = 2

        RecipeMaps.MASS_FABRICATOR_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false)

        RecipeMaps.REPLICATOR_RECIPES.maxFluidInputs = 3
        RecipeMaps.REPLICATOR_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
        RecipeMaps.REPLICATOR_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, true)

        RecipeMaps.ARC_FURNACE_RECIPES.maxInputs = 4
        RecipeMaps.ARC_FURNACE_RECIPES.maxFluidInputs = 2
        RecipeMaps.ARC_FURNACE_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.LIGHTNING_OVERLAY_1, false)
        RecipeMaps.ARC_FURNACE_RECIPES.recipeMapUI.setFluidSlotOverlay(GuiTextures.LIGHTNING_OVERLAY_2, false)

        RecipeMaps.LASER_ENGRAVER_RECIPES.maxInputs = 4
        RecipeMaps.LASER_ENGRAVER_RECIPES.maxOutputs = 4
        RecipeMaps.LASER_ENGRAVER_RECIPES.maxFluidInputs = 2
        RecipeMaps.LASER_ENGRAVER_RECIPES.maxFluidOutputs = 2
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.LENS_OVERLAY, false)

        RecipeMaps.BREWING_RECIPES.maxOutputs = 1

        RecipeMaps.CUTTER_RECIPES.maxOutputs = 4

        RecipeMaps.FERMENTING_RECIPES.maxInputs = 2
        RecipeMaps.FERMENTING_RECIPES.maxFluidInputs = 2
        RecipeMaps.FERMENTING_RECIPES.recipeMapUI.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false, false)

        RecipeMaps.THERMAL_CENTRIFUGE_RECIPES.maxOutputs = 4

        RecipeMaps.VACUUM_RECIPES.maxFluidOutputs = 2

        RecipeMaps.POLARIZER_RECIPES.maxFluidInputs = 1

        RecipeMaps.COMPRESSOR_RECIPES.maxFluidInputs = 1
        RecipeMaps.COMPRESSOR_RECIPES.maxFluidOutputs = 1

        // If EnderIO is loaded, then add a fluid slots for XP juice outputs.
        if (Mods.EnderIO.isActive)
        {
            BIO_SIMULATOR_RECIPES.maxFluidOutputs = 1
        }

        RecipeMaps.CHEMICAL_BATH_RECIPES.maxInputs = 2
    }

    // @formatter:on

}