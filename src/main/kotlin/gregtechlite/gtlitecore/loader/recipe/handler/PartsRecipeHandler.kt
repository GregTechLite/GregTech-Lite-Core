package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.LATHE_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Color
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_BOLT_SCREW
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.GemProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gemExquisite
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.turbineBlade
import gregtech.api.unification.stack.UnificationEntry
import gregtech.api.util.DyeUtil.determineDyeColor
import gregtech.api.util.GTUtility.scaleVoltage
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems.SHAPE_MOLD_ROD
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.duration
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.POLISHER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SLICER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Albite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Baddeleyite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Celestine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cryolite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Heterodiamond
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HexagonalSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jade
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nephelite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tanzanite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.gemSolitary
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.sheetedFrame
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.wallGt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_ROUND
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_SCREW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_OCTAGONAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_STRIPES
import kotlin.math.max

@Suppress("unused")
object PartsRecipeHandler
{

    // @formatter:off

    fun init()
    {
        stick.addProcessingHandler(PropertyKey.DUST, ::processStick)
        lens.addProcessingHandler(PropertyKey.GEM, ::processLens)

        screw.addProcessingHandler(PropertyKey.DUST, ::processScrew)
        round.addProcessingHandler(PropertyKey.INGOT, ::processRound)
        toolHeadDrill.addProcessingHandler(PropertyKey.INGOT, ::processDrillHead)
        turbineBlade.addProcessingHandler(PropertyKey.INGOT, ::processTurbineBlade)
        sheetedFrame.addProcessingHandler(PropertyKey.DUST, ::processSheetedFrame)
        wallGt.addProcessingHandler(PropertyKey.DUST, ::processWall)
    }

    /**
     * @see gregtech.loaders.recipe.handlers.PartsRecipeHandler.processStick
     */
    private fun processStick(stickPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        val workingTier = material.workingTier

        // Split original predicate (ingot, gem), because we want to add special processing of plastic stick (this
        // processing required to check NO_SMASHING tag of material).
        if (material.hasProperty(PropertyKey.GEM))
        {
            // Delete safety checking of original processing, because this is unused at this time.
            val builder = LATHE_RECIPES.recipeBuilder()
                .input(ingot, material)
                .EUt(scaleVoltage(VH[LV], workingTier))
                .duration(max(material.mass * 2, 1))

            if (ConfigHolder.recipes.harderRods)
            {
                builder.output(stickPrefix, material)
                    .output(dustSmall, material, 2)
            }
            else
            {
                builder.output(stick, material, 2)
            }

            builder.buildAndRegister()
        }

        if (material.hasProperty(PropertyKey.INGOT))
        {
            // For common hard materials, the processing is same as gem.
            if (!material.hasFlag(NO_SMASHING))
            {
                val builder = LATHE_RECIPES.recipeBuilder()
                    .input(ingot, material)
                    .duration(max(material.mass * 2, 1))
                    .EUt(scaleVoltage(VH[LV], workingTier))

                if (ConfigHolder.recipes.harderRods)
                {
                    builder.output(stickPrefix, material)
                        .output(dustSmall, material, 2)
                }
                else
                {
                    builder.output(stickPrefix, material, 2)
                }

                builder.buildAndRegister()
            }
            else // Used slicer to cut soft material with stripes blade.
            {
                val builder = SLICER_RECIPES.recipeBuilder()
                    .notConsumable(SLICER_BLADE_STRIPES)
                    .input(ingot, material)
                    .duration(max(material.mass * 2, 1))
                    .EUt(VH[LV])

                if (ConfigHolder.recipes.harderRods)
                {
                    builder.output(stick, material)
                        .output(dustSmall, material, 2)
                }
                else
                {
                    builder.output(stick, material, 2)
                }

                builder.buildAndRegister()
            }
        }
        // Bolt processing is same as ingot predicate, declare it has hard and soft two properties, hard part used
        // cutting machine, and soft part used slicer with octagonal blade.
        if (material.hasFlag(GENERATE_BOLT_SCREW))
        {
            val boltStack = OreDictUnifier.get(bolt, material)
            if (!material.hasFlag(NO_SMASHING))
            {
                CUTTER_RECIPES.recipeBuilder()
                    .input(stickPrefix, material)
                    .outputs(boltStack.copy(4))
                    .duration(max(material.mass * 2, 1))
                    .EUt(scaleVoltage(4, workingTier))
                    .buildAndRegister()
            }
            else if (!material.hasProperty(PropertyKey.GEM))
            {
                SLICER_RECIPES.recipeBuilder()
                    .notConsumable(SLICER_BLADE_OCTAGONAL)
                    .input(stickPrefix, material)
                    .outputs(boltStack.copy(4))
                    .duration(max(material.mass * 2, 1))
                    .EUt(scaleVoltage(4, workingTier))
                    .buildAndRegister()
            }

            if (workingTier <= HV)
            {
                ModHandler.addShapedRecipe(String.format("bolt_saw_%s", material), boltStack.copy(2),
                    "s ", " X",
                    'X', UnificationEntry(stick, material))
            }
        }

        // Add fluid solidification recipes to rod via GTLiteMetaItems#SHAPE_MOLD_ROD.
        if (material.hasFluid())
        {
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_ROD)
                .fluidInputs(material.getFluid(L / 2))
                .output(stickPrefix, material)
                .EUt(scaleVoltage(VA[LV], workingTier))
                .duration(7 * SECOND + 5 * TICK)
                .buildAndRegister()
        }
    }

    /**
     * @see gregtech.loaders.recipe.handlers.PartsRecipeHandler.processLens
     */
    private fun processLens(lensPrefix: OrePrefix, material: Material, property: GemProperty)
    {
        val workingTier = material.workingTier

        // plateX -> craftingLensX
        if (!(OreDictUnifier.get(plate, material))!!.isEmpty)
        {
            POLISHER_RECIPES.recipeBuilder()
                .input(plate, material)
                .output(lensPrefix, material)
                .output(dustSmall, material)
                .EUt(scaleVoltage(VA[MV], workingTier))
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

        // gemExquisiteX -> craftingLensX.
        if (!(OreDictUnifier.get(gemExquisite, material))!!.isEmpty)
        {
            POLISHER_RECIPES.recipeBuilder()
                .input(gemExquisite, material)
                .output(lensPrefix, material)
                .output(dust, material, 2)
                .EUt(scaleVoltage(VA[LV], workingTier))
                .duration(2 * MINUTE)
                .buildAndRegister()
        }

        // gemSolitaryX -> craftingLensX.
        if (!(OreDictUnifier.get(gemSolitary, material))!!.isEmpty)
        {
            POLISHER_RECIPES.recipeBuilder()
                .input(gemSolitary, material)
                .output(lensPrefix, material, 2)
                .output(dust, material, 4)
                .EUt(scaleVoltage(VA[MV], workingTier))
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

        // MarkerMaterials#Color processing.
        val lensStack = OreDictUnifier.get(lensPrefix, material)
        when (material)
        {
            // GTCEu colored lens modifications.
            Diamond -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.LightBlue)
            Ruby -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Red)
            Emerald -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Green)
            Glass -> OreDictUnifier.registerOre(lensStack, craftingLens.name() + material.toCamelCaseString())
            Uvarovite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Lime)
            Malachite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Green)
            Andradite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Brown)
            GreenSapphire -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Lime)
            Pyrope -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Purple)
            Rutile -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Pink)
            Spessartine -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Red)
            Olivine -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Lime)
            Amethyst -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Purple)

            // Mod colored lens modifications.
            Tanzanite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Purple)
            Albite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Pink)
            Fluorite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Green)
            Celestine -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Cyan)
            Baddeleyite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Cyan)
            Nephelite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Red)
            Cryolite -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.LightBlue)
            Jade -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Green)
            Heterodiamond -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Purple)
            HexagonalSiliconNitride -> OreDictUnifier.registerOre(lensStack, craftingLens, Color.Purple)

            // Default behaviour for determining lens color, left for CrT.
            else -> {

                val dyeColor = determineDyeColor(material.materialRGB)
                val colorMaterial = Color.COLORS[dyeColor]
                OreDictUnifier.registerOre(lensStack, craftingLens, colorMaterial)
            }
        }
    }

    private fun processScrew(screwPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        val workingTier = material.workingTier
        if (material.hasFluid())
        {
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_SCREW)
                .fluidInputs(material.getFluid(L / 8))
                .output(screwPrefix, material)
                .EUt(scaleVoltage(max(VA[MV].toLong(), 4 * getVoltageMultiplier(material)), workingTier))
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()
        }
    }

    private fun processRound(roundPrefix: OrePrefix, material: Material, property: IngotProperty)
    {
        val workingTier = material.workingTier

        if (!(OreDictUnifier.get(ingot, material).isEmpty))
        {
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_ROUND)
                .input(ingot, material)
                .output(roundPrefix, material, 4)
                .EUt(scaleVoltage(max(VA[MV].toLong(), 4 * getVoltageMultiplier(material)), workingTier))
                .duration(2 * SECOND)
                .buildAndRegister()
        }
        else
        {
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_ROUND)
                .input(dust, material)
                .output(roundPrefix, material, 4)
                .EUt(scaleVoltage(max(VA[MV].toLong(), 4 * getVoltageMultiplier(material)), workingTier))
                .duration(2 * SECOND)
                .buildAndRegister()
        }
    }

    private fun processDrillHead(drillHeadPrefix: OrePrefix, material: Material, property: IngotProperty)
    {
        val workingTier = material.workingTier
        if (material.hasFluid())
        {
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_DRILL_HEAD)
                .fluidInputs(material.getFluid(L * 4)) // Cost less material than hand-crafting recipes.
                .output(drillHeadPrefix, material)
                .EUt(scaleVoltage(VA[MV].toLong(), workingTier))
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_DRILL_HEAD)
            .input(ingot, material, 4) // Cost less material than hand-crafting recipes.
            .output(drillHeadPrefix, material, 1)
            .EUt(scaleVoltage(VA[MV].toLong(), workingTier))
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun processTurbineBlade(turbineBladePrefix: OrePrefix, material: Material, property: IngotProperty)
    {
        val workingTier = material.workingTier
        if (material.hasFluid())
        {
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_TURBINE_BLADE)
                .fluidInputs(material.getFluid(L * 6))
                .output(turbineBladePrefix, material)
                .EUt(scaleVoltage(max(VA[MV].toLong(), 6 * getVoltageMultiplier(material)), workingTier))
                .duration(20 * SECOND)
                .buildAndRegister()
        }

        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_TURBINE_BLADE)
            .input(ingot, material, 6)
            .output(turbineBladePrefix, material)
            .EUt(scaleVoltage(VA[MV].toLong(), workingTier))
            .duration(20 * SECOND)
            .buildAndRegister()
    }

    fun processSheetedFrame(sheetedFramePrefix: OrePrefix, material: Material, property: DustProperty)
    {

        if (!material.hasFlag(MaterialFlags.GENERATE_FRAME))
            return

        ModHandler.addShapedRecipe(String.format("%s_sheeted_frame", material), OreDictUnifier.get(sheetedFramePrefix, material, 12),
            "PFP", "PhP", "PFP",
            'P', UnificationEntry(plate, material),
            'F', UnificationEntry(frameGt, material))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(10)
            .input(plate, material, 3)
            .input(frameGt, material, 1)
            .output(sheetedFramePrefix, material, 6)
            .EUt(VA[ULV])
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

    }

    fun processWall(wallGtPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        if (!material.hasFlag(MaterialFlags.GENERATE_PLATE) || !material.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW))
            return

        ModHandler.addShapedRecipe(String.format("%s_wall_gt", material), OreDictUnifier.get(wallGtPrefix, material, 6),
            "hPS", "P P", "SPd",
            'P', UnificationEntry(plate, material),
            'S', UnificationEntry(screw, material))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(11)
            .input(plate, material, 2)
            .input(screw, material, 1)
            .output(wallGtPrefix, material, 3)
            .EUt(VA[ULV])
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()
    }

    private fun getVoltageMultiplier(material: Material): Long = if (material.blastTemperature >= 2800) VA[LV].toLong() else VA[ULV].toLong()

    private fun scaleVoltage(voltage: Int, workingTier: Int) = scaleVoltage(voltage.toLong(), workingTier)

    // @formatter:on

}