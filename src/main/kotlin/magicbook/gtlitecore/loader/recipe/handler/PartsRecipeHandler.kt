package magicbook.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
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
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.UnificationEntry
import gregtech.api.util.DyeUtil
import gregtech.api.util.GTUtility
import gregtech.common.ConfigHolder
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
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
                if (!material.hasFlags(MaterialFlags.NO_SMASHING))
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
                if (!material.hasFlags(MaterialFlags.NO_SMASHING))
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
                // Addition gem colored lens in gtlitecore.
                // ...
                else -> {
                    // Default behaviour for determining lens color, left for addons and CraftTweaker.
                    val dyeColor = DyeUtil.determineDyeColor(material.materialRGB)
                    val colorMaterial = MarkerMaterials.Color.COLORS[dyeColor]
                    OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, colorMaterial)
                }
            }
        }

    }

}