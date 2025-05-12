package magicbook.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.GemProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.UnificationEntry
import gregtech.api.util.DyeUtil
import gregtech.api.util.GTUtility
import gregtech.common.ConfigHolder
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_EXTRUDER_DRILL_HEAD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_EXTRUDER_ROUND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_EXTRUDER_TURBINE_BLADE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_BOLT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_DRILL_HEAD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_RING
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROD_LONG
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROUND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_SCREW
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_TURBINE_BLADE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_OCTAGONAL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_STRIPES
import net.minecraft.item.ItemStack
import kotlin.math.max

@Suppress("MISSING_DEPENDENCY_CLASS")
class PartsRecipeHandler
{

    companion object
    {

        fun init()
        {
            // Callback original registrate of material processing handler and post new processing handler
            // from new recipe handler container.
            OrePrefix.stick.addProcessingHandler(PropertyKey.DUST, this::processStick)
            OrePrefix.lens.addProcessingHandler(PropertyKey.GEM, this::processLens)
            // ===========================================================================================
            OrePrefix.stickLong.addProcessingHandler(PropertyKey.DUST, this::processStickLong)
            OrePrefix.bolt.addProcessingHandler(PropertyKey.DUST, this::processBolt)
            OrePrefix.screw.addProcessingHandler(PropertyKey.DUST, this::processScrew)
            OrePrefix.ring.addProcessingHandler(PropertyKey.DUST, this::processRing)
            OrePrefix.round.addProcessingHandler(PropertyKey.INGOT, this::processRound)
            OrePrefix.toolHeadDrill.addProcessingHandler(PropertyKey.INGOT, this::processDrillHead)
            OrePrefix.turbineBlade.addProcessingHandler(PropertyKey.INGOT, this::processTurbineBlade)
            GTLiteOrePrefix.sheetedFrame.addProcessingHandler(PropertyKey.DUST, this::processSheetedFrame)
            GTLiteOrePrefix.wallGt.addProcessingHandler(PropertyKey.DUST, this::processWall)
        }

        /**
         * Transformed from [gregtech.loaders.recipe.handlers.PartsRecipeHandler.processStick],
         * required [magicbook.gtlitecore.mixin.gregtech.PartsRecipeHandlerMixin.callbackRegistrate].
         */
        private fun processStick(stickPrefix: OrePrefix, material: Material, property: DustProperty)
        {
            val workingTier = material.workingTier
            // Split original predicate (ingot, gem), because we want to add special processing of plastic
            // stick (this processing required to check [MaterialFlags.NO_SMASHING] tag of material).
            if (material.hasProperty(PropertyKey.GEM))
            {
                val builder = RecipeMaps.LATHE_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material) // Delete safety checking of original processing, because this is unused at this time.
                    .EUt(GTUtility.scaleVoltage(VH[LV].toLong(), workingTier))
                    .duration(max(material.mass * 2, 1).toInt())
                if (ConfigHolder.recipes.harderRods)
                {
                    builder.output(stickPrefix, material)
                        .output(OrePrefix.dustSmall, material, 2);
                }
                else
                {
                    builder.output(OrePrefix.stick, material, 2)
                }
                builder.buildAndRegister()
            }

            if (material.hasProperty(PropertyKey.INGOT))
            {
                // For common hard materials, the processing is same as gem.
                if (!material.hasFlag(MaterialFlags.NO_SMASHING))
                {
                    val builder = RecipeMaps.LATHE_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material)
                        .duration(max(material.mass * 2, 1).toInt())
                        .EUt(GTUtility.scaleVoltage(VH[LV].toLong(), workingTier))
                    if (ConfigHolder.recipes.harderRods)
                    {
                        builder.output(stickPrefix, material)
                            .output(OrePrefix.dustSmall, material, 2)
                    }
                    else
                    {
                        builder.output(stickPrefix, material, 2)
                    }
                    builder.buildAndRegister()
                }
                else // Used slicer to cut soft material with stripes blade.
                {
                    val builder = GTLiteRecipeMaps.SLICER_RECIPES.recipeBuilder()
                        .notConsumable(SLICER_BLADE_STRIPES)
                        .input(OrePrefix.ingot, material)
                        .duration(max(material.mass * 2, 1).toInt())
                        .EUt(VH[LV].toLong())
                    if (ConfigHolder.recipes.harderRods)
                    {
                        builder.output(OrePrefix.stick, material);
                        builder.output(OrePrefix.dustSmall, material, 2);
                    }
                    else
                    {
                        builder.output(OrePrefix.stick, material, 2);
                    }
                    builder.buildAndRegister()
                }
            }
            // Bolt processing is same as ingot predicate, declare it has hard and soft two properties,
            // hard part used cutting machine, and soft part used slicer with octagonal blade.
            if (material.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW))
            {
                val boltStack = OreDictUnifier.get(OrePrefix.bolt, material);
                if (!material.hasFlag(MaterialFlags.NO_SMASHING))
                {
                    RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                        .input(stickPrefix, material)
                        .outputs(GTUtility.copy(4, boltStack))
                        .duration(max(material.mass * 2, 1).toInt())
                    .EUt(GTUtility.scaleVoltage(4, workingTier))
                    .buildAndRegister()
                }
                else if (!material.hasProperty(PropertyKey.GEM))
                {
                    GTLiteRecipeMaps.SLICER_RECIPES.recipeBuilder()
                        .notConsumable(SLICER_BLADE_OCTAGONAL)
                        .input(stickPrefix, material)
                        .outputs(GTUtility.copy(4, boltStack))
                        .duration(max(material.mass * 2, 1).toInt())
                        .EUt(GTUtility.scaleVoltage(4, workingTier))
                        .buildAndRegister()
                }

                if (workingTier <= HV)
                {
                    ModHandler.addShapedRecipe(String.format("bolt_saw_%s", material),
                        GTUtility.copy(2, boltStack),
                        "s ", " X",
                        'X', UnificationEntry(OrePrefix.stick, material))
                }
            }

            // Add fluid solidification recipes to rod via GTLiteMetaItems#SHAPE_MOLD_ROD.
            if (material.hasFluid())
            {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_ROD)
                    .fluidInputs(material.getFluid(L / 2))
                    .output(stickPrefix, material)
                    .EUt(GTUtility.scaleVoltage(VA[LV].toLong(), workingTier))
                    .duration(7 * SECOND + 5 * TICK)
                    .buildAndRegister()
            }
        }

        /**
         * Transformed from [gregtech.loaders.recipe.handlers.PartsRecipeHandler.processLens],
         * required [magicbook.gtlitecore.mixin.gregtech.PartsRecipeHandlerMixin.callbackRegistrate].
         */
        private fun processLens(lensPrefix: OrePrefix, material: Material, property: GemProperty)
        {
            val workingTier = material.workingTier
            // plateX -> craftingLensX
            if (!(OreDictUnifier.get(OrePrefix.plate, material) as? ItemStack)!!.isEmpty)
            {
                GTLiteRecipeMaps.POLISHER_RECIPES.recipeBuilder()
                    .input(OrePrefix.plate, material)
                    .output(lensPrefix, material)
                    .output(OrePrefix.dustSmall, material)
                    .EUt(GTUtility.scaleVoltage(VA[MV].toLong(), workingTier))
                    .duration(1 * MINUTE)
                    .buildAndRegister()
            }
            // gemExquisiteX -> craftingLensX.
            if (!(OreDictUnifier.get(OrePrefix.gemExquisite, material) as? ItemStack)!!.isEmpty)
            {
                GTLiteRecipeMaps.POLISHER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gemExquisite, material)
                    .output(lensPrefix, material)
                    .output(OrePrefix.dust, material, 2)
                    .EUt(GTUtility.scaleVoltage(VA[LV].toLong(), workingTier))
                    .duration(2 * MINUTE)
                    .buildAndRegister()
            }
            // gemSolitaryX -> craftingLensX.
            if (!(OreDictUnifier.get(GTLiteOrePrefix.gemSolitary, material) as? ItemStack)!!.isEmpty)
            {
                GTLiteRecipeMaps.POLISHER_RECIPES.recipeBuilder()
                    .input(GTLiteOrePrefix.gemSolitary, material)
                    .output(lensPrefix, material, 2)
                    .output(OrePrefix.dust, material, 4)
                    .EUt(GTUtility.scaleVoltage(VA[MV].toLong(), workingTier))
                    .duration(1 * MINUTE)
                    .buildAndRegister()
            }

            // MarkerMaterials#Color processing.
            val lensStack = OreDictUnifier.get(lensPrefix, material)
            when (material)
            {
                // Original gem colored lens modifications.
                Materials.Diamond -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.LightBlue)
                }
                Materials.Ruby -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Red)
                }
                Materials.Emerald -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Green)
                }
                Materials.Glass -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens.name() + material.toCamelCaseString())
                }
                Materials.Uvarovite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Lime)
                }
                Materials.Malachite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Green)
                }
                Materials.Andradite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Brown)
                }
                Materials.GreenSapphire -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Lime)
                }
                Materials.Pyrope -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Purple)
                }
                Materials.Rutile -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Pink)
                }
                Materials.Spessartine -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Red)
                }
                Materials.Olivine -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Lime)
                }
                Materials.Amethyst -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Purple)
                }
                // Addition gem colored lens in gtlitecore.
                GTLiteMaterials.Tanzanite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Purple)
                }
                GTLiteMaterials.Albite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Pink)
                }
                GTLiteMaterials.Fluorite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Green)
                }
                GTLiteMaterials.Celestine -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Cyan)
                }
                GTLiteMaterials.Baddeleyite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Cyan)
                }
                GTLiteMaterials.Nephelite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Red)
                }
                GTLiteMaterials.Cryolite -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.LightBlue)
                }
                GTLiteMaterials.Jade -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Green)
                }
                GTLiteMaterials.Heterodiamond -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Purple)
                }
                GTLiteMaterials.HexagonalSiliconNitride -> {
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens,
                        MarkerMaterials.Color.Purple)
                }
                else -> {
                    // Default behaviour for determining lens color, left for addons and CraftTweaker.
                    val dyeColor = DyeUtil.determineDyeColor(material.materialRGB)
                    val colorMaterial = MarkerMaterials.Color.COLORS[dyeColor]
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, colorMaterial)
                }
            }
        }

        /**
         * Add fluid solidification processing via [SHAPE_MOLD_ROD_LONG].
         */
        private fun processStickLong(stickLongPrefix: OrePrefix, material: Material, property: DustProperty)
        {
            val workingTier = material.workingTier
            if (material.hasFluid())
            {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_ROD_LONG)
                    .fluidInputs(material.getFluid(L))
                    .output(stickLongPrefix, material)
                    .EUt(GTUtility.scaleVoltage(max(VA[MV].toLong(), 16 * getVoltageMultiplier(material)), workingTier))
                    .duration(15 * SECOND)
                    .buildAndRegister()
            }
        }

        /**
         * Add fluid solidification processing via [SHAPE_MOLD_BOLT].
         */
        private fun processBolt(boltPrefix: OrePrefix, material: Material, property: DustProperty)
        {
            val workingTier = material.workingTier
            if (material.hasFluid())
            {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_BOLT)
                    .fluidInputs(material.getFluid(L / 8))
                    .output(boltPrefix, material)
                    .EUt(GTUtility.scaleVoltage(VA[LV].toLong(), workingTier))
                    .duration(2 * SECOND + 5 * TICK)
                    .buildAndRegister()
            }
        }

        /**
         * Add fluid solidification processing via [SHAPE_MOLD_SCREW].
         */
        private fun processScrew(screwPrefix: OrePrefix, material: Material, property: DustProperty)
        {
            val workingTier = material.workingTier
            if (material.hasFluid())
            {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_SCREW)
                    .fluidInputs(material.getFluid(L / 8))
                    .output(screwPrefix, material)
                    .EUt(GTUtility.scaleVoltage(max(VA[MV].toLong(), 4 * getVoltageMultiplier(material)), workingTier))
                    .duration(2 * SECOND + 5 * TICK)
                    .buildAndRegister()
            }
        }

        /**
         * Add fluid solidification processing via [SHAPE_MOLD_RING].
         */
        private fun processRing(ringPrefix: OrePrefix, material: Material, property: DustProperty)
        {
            val workingTier = material.workingTier
            if (material.hasFluid())
            {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_RING)
                    .fluidInputs(material.getFluid(L / 4))
                    .output(ringPrefix, material)
                    .EUt(GTUtility.scaleVoltage(VA[LV].toLong(), workingTier))
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }
        }

        /**
         * Add fluid solidification and extruding processing via [SHAPE_MOLD_ROUND]
         * and [SHAPE_EXTRUDER_ROUND].
         */
        private fun processRound(roundPrefix: OrePrefix, material: Material, property: IngotProperty)
        {
            val workingTier = material.workingTier
            if (material.hasFluid())
            {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_ROUND)
                    .fluidInputs(material.getFluid(L / 8))
                    .output(roundPrefix, material)
                    .EUt(GTUtility.scaleVoltage(VA[LV].toLong(), workingTier))
                    .duration(2 * SECOND + 5 * TICK)
                    .buildAndRegister()
            }
            if (!(OreDictUnifier.get(OrePrefix.ingot, material) as? ItemStack)!!.isEmpty)
            {
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_EXTRUDER_ROUND)
                    .input(OrePrefix.ingot, material)
                    .output(roundPrefix, material, 4)
                    .EUt(GTUtility.scaleVoltage(max(VA[MV].toLong(), 4 * getVoltageMultiplier(material)), workingTier))
                    .duration(2 * SECOND)
                    .buildAndRegister()
            }
            else // TODO Should there needs a extra checking of dustStack not null?
            {
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_EXTRUDER_ROUND)
                    .input(OrePrefix.dust, material)
                    .output(roundPrefix, material, 4)
                    .EUt(GTUtility.scaleVoltage(max(VA[MV].toLong(), 4 * getVoltageMultiplier(material)), workingTier))
                    .duration(2 * SECOND)
                    .buildAndRegister()
            }
        }

        /**
         * Add fluid solidification and extruding processing via [SHAPE_MOLD_DRILL_HEAD]
         * and [SHAPE_EXTRUDER_DRILL_HEAD].
         */
        private fun processDrillHead(drillHeadPrefix: OrePrefix, material: Material, property: IngotProperty)
        {
            val workingTier = material.workingTier
            if (material.hasFluid())
            {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_DRILL_HEAD)
                    .fluidInputs(material.getFluid(L * 4)) // Cost less material than hand-crafting recipes.
                    .output(drillHeadPrefix, material)
                    .EUt(GTUtility.scaleVoltage(VA[MV].toLong(), workingTier))
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_DRILL_HEAD)
                .input(OrePrefix.ingot, material, 4) // Cost less material than hand-crafting recipes.
                .output(drillHeadPrefix, material, 1)
                .EUt(GTUtility.scaleVoltage(VA[MV].toLong(), workingTier))
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        /**
         * Add fluid solidification and extruding processing via [SHAPE_MOLD_TURBINE_BLADE]
         * and [SHAPE_EXTRUDER_TURBINE_BLADE].
         */
        private fun processTurbineBlade(turbineBladePrefix: OrePrefix, material: Material, property: IngotProperty)
        {
            val workingTier = material.workingTier
            if (material.hasFluid())
            {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_TURBINE_BLADE)
                    .fluidInputs(material.getFluid(L * 6))
                    .output(turbineBladePrefix, material)
                    .EUt(GTUtility.scaleVoltage(max(VA[MV].toLong(), 6 * getVoltageMultiplier(material)), workingTier))
                    .duration(20 * SECOND)
                    .buildAndRegister()
            }
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_TURBINE_BLADE)
                .input(OrePrefix.ingot, material, 6)
                .output(turbineBladePrefix, material)
                .EUt(GTUtility.scaleVoltage(VA[MV].toLong(), workingTier))
                .duration(20 * SECOND)
                .buildAndRegister()
        }

        fun processSheetedFrame(sheetedFramePrefix: OrePrefix, material: Material, property: DustProperty)
        {

            if (!material.hasFlag(MaterialFlags.GENERATE_FRAME))
                return
            ModHandler.addShapedRecipe(String.format("%s_sheeted_frame", material), OreDictUnifier.get(sheetedFramePrefix, material, 12),
                "PFP", "PhP", "PFP",
                'P', UnificationEntry(OrePrefix.plate, material),
                'F', UnificationEntry(OrePrefix.frameGt, material))

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(10)
                .input(OrePrefix.plate, material, 3)
                .input(OrePrefix.frameGt, material, 1)
                .output(sheetedFramePrefix, material, 6)
                .EUt(VA[ULV].toLong())
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

        }

        fun processWall(wallGtPrefix: OrePrefix, material: Material, property: DustProperty)
        {
            if (!material.hasFlag(MaterialFlags.GENERATE_PLATE) || !material.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW))
                return
            ModHandler.addShapedRecipe(String.format("%s_wall_gt", material), OreDictUnifier.get(wallGtPrefix, material, 6),
                "hPS", "P P", "SPd",
                'P', UnificationEntry(OrePrefix.plate, material),
                'S', UnificationEntry(OrePrefix.screw, material))

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(11)
                .input(OrePrefix.plate, material, 2)
                .input(OrePrefix.screw, material, 1)
                .output(wallGtPrefix, material, 3)
                .EUt(VA[ULV].toLong())
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()
        }

        private fun getVoltageMultiplier(material: Material): Long = if (material.blastTemperature >= 2800) VA[LV].toLong() else VA[ULV].toLong()

    }

}