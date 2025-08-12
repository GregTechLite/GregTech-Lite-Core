package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.M
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.UnificationEntry
import gregtech.api.util.GTUtility
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROD
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtech.common.items.MetaItems.SHAPE_MOLD_NUGGET
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps
import gregtechlite.gtlitecore.api.unification.material.properties.AlloyBlastProperty
import gregtechlite.gtlitecore.api.unification.material.properties.GTLitePropertyKey
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.duration
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_FLAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_OCTAGONAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_STRIPES
import net.minecraft.item.ItemStack
import kotlin.math.max

object MaterialRecipeHandler
{

    // @formatter:off

    private val GEM_ORDER = listOf(OrePrefix.gemChipped,
                                   OrePrefix.gemFlawed,
                                   OrePrefix.gem,
                                   OrePrefix.gemFlawless,
                                   OrePrefix.gemExquisite,
                                   GTLiteOrePrefix.gemSolitary)

    fun init()
    {
        // Callback original registrate of material processing handler and post new processing handler from new recipe
        // handler container.
        OrePrefix.ingot.addProcessingHandler(PropertyKey.INGOT, this::processIngot)
        OrePrefix.block.addProcessingHandler(PropertyKey.DUST, this::processBlock)

        // Rewrite gem conversions with new gem orders, this is hard dependency with the new gem quality ore prefix and
        // enabled GregTech settings in modifier.
        for (i in GEM_ORDER.indices)
        {
            val gemPrefix = GEM_ORDER[i]
            val prevGemPrefix = if (i == 0) null else GEM_ORDER[i - 1]
            gemPrefix.addProcessingHandler(PropertyKey.GEM) { p, m, _ ->
                processGemConversion(p, prevGemPrefix, m)
            }
        }

        // -------------------------------------------------------------------------------------------------------------
        OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, this::processDust)
        OrePrefix.ingot.addProcessingHandler(GTLitePropertyKey.ALLOY_BLAST, this::generateABSRecipes)
        OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, this::generateMBFRecipes)
    }

    /**
     * Transformed from [gregtech.loaders.recipe.handlers.MaterialRecipeHandler.processIngot],
     * required [magicbook.gtlitecore.mixins.gregtech.MixinMaterialRecipeHandler.callbackRegistrate].
     */
    private fun processIngot(ingotPrefix: OrePrefix, material: Material, property: IngotProperty)
    {
        val workingTier = material.workingTier
        if (material.hasFlag(MaterialFlags.MORTAR_GRINDABLE) && workingTier <= HV)
        {
            ModHandler.addShapedRecipe(String.format("mortar_grind_%s", material),
                OreDictUnifier.get(OrePrefix.dust, material),
                "X", "m",
                'X', UnificationEntry(ingotPrefix, material))
        }

        if (material.hasFlag(MaterialFlags.GENERATE_ROD))
        {
            // Common hand-crafting recipes.
            if (workingTier <= HV)
            {
                ModHandler.addShapedRecipe(String.format("stick_%s", material),
                    OreDictUnifier.get(OrePrefix.stick, material),
                    "f ", " X",
                    'X', UnificationEntry(ingotPrefix, material))
            }
            if (!material.hasFlag(MaterialFlags.NO_WORKING))
            {
                // Confirm extruder can process any materials.
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_EXTRUDER_ROD)
                    .input(ingotPrefix, material)
                    .output(OrePrefix.stick, material, 2)
                    .EUt(GTUtility.scaleVoltage(6 * getVoltageMultiplier(material), workingTier))
                    .duration((material.mass * 2).toInt())
                    .buildAndRegister()

                // ingotX -> stickX (soft: slicer, hard: cutting machine).
                if (material.hasFlag(MaterialFlags.NO_SMASHING))
                {
                    GTLiteRecipeMaps.SLICER_RECIPES.recipeBuilder()
                        .notConsumable(SLICER_BLADE_STRIPES)
                        .input(ingotPrefix, material)
                        .output(OrePrefix.stick, material, 2)
                        .EUt(GTUtility.scaleVoltage(6 * getVoltageMultiplier(material), workingTier))
                        .duration((material.mass * 2).toInt())
                        .buildAndRegister()
                }
            }
        }
        // Fluid solidification.
        if (material.hasFluid() && !((material.getProperty(PropertyKey.FLUID).solidifiesFrom())!!.equals(null)))
        {
            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(material.getProperty(PropertyKey.FLUID).solidifiesFrom(L))
                .output(ingotPrefix, material)
                .EUt(GTUtility.scaleVoltage(VA[ULV].toLong(), workingTier))
                .duration(1 * SECOND)
                .buildAndRegister()
        }
        // Plastic extruding dustX -> ingotX.
        if (material.hasFlag(MaterialFlags.NO_SMASHING))
        {
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .input(OrePrefix.dust, material)
                .output(ingotPrefix, material)
                .EUt(GTUtility.scaleVoltage(4 * getVoltageMultiplier(material), workingTier))
                .duration(10 * TICK)
                .buildAndRegister()
        }
        // Universal ingotX -> nuggetX alloy smelting.
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_NUGGET)
            .input(ingotPrefix, material)
            .output(OrePrefix.nugget, material)
            .EUt(GTUtility.scaleVoltage(VA[ULV].toLong(), workingTier))
            .duration(material.mass.toInt())
            .buildAndRegister()
        // Universal blockX <-> ingotX converts.
        if (!(OreDictUnifier.get(OrePrefix.block, material))!!.isEmpty)
        {
            RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_INGOT)
                .input(OrePrefix.block, material)
                .output(ingotPrefix, material, 9)
                .EUt(GTUtility.scaleVoltage(VA[ULV].toLong(), workingTier))
                .duration((material.mass * 9).toInt())
                .buildAndRegister()

            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(ingotPrefix, material, (OrePrefix.block.getMaterialAmount(material) / M).toInt())
                .output(OrePrefix.block, material)
                .buildAndRegister()
        }
        // Bending ingotX -> plateX.
        if (material.hasFlag(MaterialFlags.GENERATE_PLATE) && !material.hasFlag(MaterialFlags.NO_WORKING))
        {
            // Common bending processing for hard materials.
            if (!material.hasFlag(MaterialFlags.NO_SMASHING))
            {
                val plateStack = OreDictUnifier.get(OrePrefix.plate, material)
                if (!(plateStack)!!.isEmpty)
                {
                    // 1:1 Bending.
                    RecipeMaps.BENDER_RECIPES.recipeBuilder()
                        .circuitMeta(1)
                        .input(ingotPrefix, material)
                        .outputs(plateStack)
                        .EUt(GTUtility.scaleVoltage(24, workingTier))
                        .duration((material.mass).toInt())
                        .buildAndRegister()
                    // 3:2 Hamming.
                    RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                        .input(ingotPrefix, material, 3)
                        .outputs(GTUtility.copyFirst(2, plateStack))
                        .EUt(GTUtility.scaleVoltage(VH[LV].toLong(), workingTier))
                        .duration((material.mass).toInt())
                        .buildAndRegister()
                    // Default hand-crafting recipes.
                    if (workingTier <= HV)
                    {
                        ModHandler.addShapedRecipe(String.format("plate_%s", material), plateStack,
                            "h", "I", "I",
                            'I', UnificationEntry(ingotPrefix, material))
                    }

                }
            }
            // Another safety filtered checking for filter all gem materials.
            if (!(OreDictUnifier.get(OrePrefix.plate, material))!!.isEmpty)
            {
                if (material.hasFlag(MaterialFlags.NO_SMASHING))
                {
                    // Slicing ingotX -> plateX.
                    GTLiteRecipeMaps.SLICER_RECIPES.recipeBuilder()
                        .notConsumable(SLICER_BLADE_FLAT)
                        .input(ingotPrefix, material)
                        .output(OrePrefix.plate, material)
                        .EUt(GTUtility.scaleVoltage(8 * getVoltageMultiplier(material), workingTier))
                        .duration((material.mass).toInt())
                        .buildAndRegister()
                    // Special extruding dustX -> plateX.
                    RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .notConsumable(SHAPE_EXTRUDER_PLATE)
                        .input(OrePrefix.dust, material)
                        .output(OrePrefix.plate, material)
                        .EUt(GTUtility.scaleVoltage(8 * getVoltageMultiplier(material), workingTier))
                        .duration((material.mass).toInt())
                        .buildAndRegister()
                }
                else
                {
                    // Hard extruding.
                    RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .notConsumable(SHAPE_EXTRUDER_PLATE)
                        .input(ingotPrefix, material)
                        .output(OrePrefix.plate, material)
                        .EUt(GTUtility.scaleVoltage(8 * getVoltageMultiplier(material), workingTier))
                        .duration((material.mass).toInt())
                        .buildAndRegister()
                }
            }
        }
    }

    /**
     * Transformed from [gregtech.loaders.recipe.handlers.MaterialRecipeHandler.processBlock],
     * required [magicbook.gtlitecore.mixins.gregtech.MixinMaterialRecipeHandler.callbackRegistrate].
     */
    private fun processBlock(blockPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        val blockStack = OreDictUnifier.get(blockPrefix, material)
        val workingTier = material.workingTier
        val materialAmount = blockPrefix.getMaterialAmount(material)
        // Fluid solidification.
        if (material.hasFluid() && !(material.getProperty(PropertyKey.FLUID).solidifiesFrom())!!.equals(null))
        {
            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(material.getProperty(PropertyKey.FLUID).solidifiesFrom((materialAmount * L / M).toInt()))
                .outputs(blockStack)
                .EUt(GTUtility.scaleVoltage(VA[ULV].toLong(), workingTier))
                .duration((material.mass).toInt())
                .buildAndRegister()
        }
        // blockX -> plateX.
        if (material.hasFlag(MaterialFlags.GENERATE_PLATE))
        {
            val plateStack = OreDictUnifier.get(OrePrefix.plate, material)
            if (!(plateStack)!!.isEmpty)
            {
                // Plastic blockX -> plateX.
                if (material.hasFlag(MaterialFlags.NO_SMASHING) && !material.hasProperty(PropertyKey.GEM))
                {
                    GTLiteRecipeMaps.SLICER_RECIPES.recipeBuilder()
                        .notConsumable(SLICER_BLADE_OCTAGONAL)
                        .input(blockPrefix, material)
                        .outputs(GTUtility.copyFirst((materialAmount / M).toInt(), plateStack))
                        .EUt(GTUtility.scaleVoltage(VA[LV].toLong(), workingTier))
                        .duration((material.mass * 8).toInt())
                        .buildAndRegister()
                }
                else // Common cutting.
                {
                    RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                        .input(blockPrefix, material)
                        .outputs(GTUtility.copyFirst((materialAmount / M).toInt(), plateStack))
                        .EUt(GTUtility.scaleVoltage(VA[LV].toLong(), workingTier))
                        .duration((material.mass * 8).toInt())
                        .buildAndRegister()
                }
            }
        }
        // Preventable conversion recipes for (block) <=> (dust, ingot, gem).
        val blockEntry: UnificationEntry
        if (material.hasProperty(PropertyKey.GEM))
            blockEntry = UnificationEntry(OrePrefix.gem, material)
        else if (material.hasProperty(PropertyKey.INGOT))
            blockEntry = UnificationEntry(OrePrefix.ingot, material)
        else
            blockEntry = UnificationEntry(OrePrefix.dust, material)
        val result = arrayListOf<Any>()
        for (i in 0 until materialAmount / M)
            result.add(blockEntry)
        // Do not allow hand-crafting or uncrafting, extruding or alloy smelting of blacklisted block.
        if (!material.hasFlag(MaterialFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES))
        {
            // Do not allow hand-crafting or uncrafting of blacklisted blocks.
            if (!material.hasFlag(MaterialFlags.EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES)
                    && !ConfigHolder.recipes.disableManualCompression)
            {
                ModHandler.addShapelessRecipe(String.format("block_compress_%s", material), blockStack,
                    *result.toTypedArray())
                ModHandler.addShapelessRecipe(String.format("block_decompress_%s", material),
                    GTUtility.copyFirst((materialAmount / M).toInt(), OreDictUnifier.get(blockEntry)),
                    UnificationEntry(blockPrefix, material))
            }
            // Ingot converts.
            if (material.hasProperty(PropertyKey.INGOT))
            {
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_EXTRUDER_BLOCK)
                    .input(OrePrefix.ingot, material, (materialAmount / M).toInt())
                    .outputs(blockStack)
                    .EUt(GTUtility.scaleVoltage(8 * getVoltageMultiplier(material), workingTier))
                    .duration(10 * TICK)
                    .buildAndRegister()

                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_BLOCK)
                    .input(OrePrefix.ingot, material, (materialAmount / M).toInt())
                    .outputs(blockStack)
                    .EUt(GTUtility.scaleVoltage(4 * getVoltageMultiplier(material), workingTier))
                    .duration(5 * TICK)
                    .buildAndRegister()
            }
            else if (material.hasProperty(PropertyKey.GEM))
            {
                RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, (OrePrefix.block.getMaterialAmount(material) / M).toInt())
                    .outputs(blockStack)
                    .EUt(GTUtility.scaleVoltage(2, workingTier)) // ULV
                    .duration(15 * SECOND)
                    .buildAndRegister()

                RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(blockPrefix, material)
                    .output(OrePrefix.gem, material, (OrePrefix.block.getMaterialAmount(material) / M).toInt())
                    .EUt(GTUtility.scaleVoltage(24, workingTier)) // LV
                    .duration(5 * SECOND)
                    .buildAndRegister()

            }

        }
    }

    /**
     * Transformed from [gregtech.loaders.recipe.handlers.MaterialRecipeHandler.processGemConversion],
     * required [magicbook.gtlitecore.mixins.gregtech.MixinMaterialRecipeHandler.callbackRegistrate].
     */
    private fun processGemConversion(gemPrefix: OrePrefix, prevPrefix: OrePrefix?, material: Material)
    {
        val materialAmount = gemPrefix.getMaterialAmount(material)
        val workingTier = material.workingTier
        val crushedStack = OreDictUnifier.getDust(material, materialAmount)

        if (material.hasFlag(MaterialFlags.MORTAR_GRINDABLE) && workingTier <= HV)
        {
            ModHandler.addShapedRecipe(String.format("gem_to_dust_%s_%s", material, gemPrefix), crushedStack,
                "X", "m",
                'X', UnificationEntry(gemPrefix, material))
        }
        val prevStack = if (prevPrefix == null) ItemStack.EMPTY else OreDictUnifier.get(prevPrefix, material, 2)
        if (!prevStack.isEmpty)
        {
            ModHandler.addShapelessRecipe(String.format("gem_to_gem_%s_%s", prevPrefix, material), prevStack,
                "h",
                UnificationEntry(gemPrefix, material))

            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(gemPrefix, material)
                .outputs(prevStack)
                .EUt(VH[LV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.White)
                .inputs(prevStack)
                .output(gemPrefix, material)
                .EUt(GTUtility.scaleVoltage(VHA[HV].toLong(), workingTier))
                .duration(15 * SECOND)
                .buildAndRegister()
        }

    }

    /**
     * Add dust explosion to gem recipes to Electric Implosion Compressor. Just like handlers
     * in [gregtech.loaders.recipe.handlers.MaterialRecipeHandler.processDust].
     */
    private fun processDust(dustPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        val workingTier = material.workingTier
        val dustStack = OreDictUnifier.get(dustPrefix, material)
        if (material.hasProperty(PropertyKey.GEM))
        {
            val gemStack = OreDictUnifier.get(OrePrefix.gem, material)
            if (!material.hasFlag(MaterialFlags.EXPLOSIVE) && !material.hasFlag(MaterialFlags.FLAMMABLE))
            {
                GTLiteRecipeMaps.ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
                    .inputs(dustStack.copy(4))
                    .outputs(gemStack.copy(3))
                    .chancedOutput(OrePrefix.dust, Materials.DarkAsh, 2500, 0)
                    .EUt(GTUtility.scaleVoltage(VA[LV].toLong(), workingTier))
                    .duration(1 * SECOND)
                    .buildAndRegister()
            }

        }

    }

    private fun generateABSRecipes(ingotPrefix: OrePrefix, material: Material, property: AlloyBlastProperty)
    {
        if (material.hasProperty(PropertyKey.BLAST))
            property.recipeProducer.produce(material, material.getProperty(PropertyKey.BLAST))
    }

    private fun generateMBFRecipes(dustPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        if (material.hasProperty(PropertyKey.INGOT))
        {
            if (!material.hasAnyOfFlags(MaterialFlags.FLAMMABLE, MaterialFlags.NO_SMELTING))
            {
                val ingotStack = OreDictUnifier.get(OrePrefix.ingot, material)
                var blastTemp = material.blastTemperature

                if (!ingotStack.isEmpty)
                {
                    if (blastTemp == 0)
                        blastTemp += 1200

                    val blastProp = material.getProperty(PropertyKey.BLAST)
                    if (blastProp != null)
                    {
                        var duration = blastProp.durationOverride
                        if (duration <= 0)
                        {
                            duration = max(1, material.mass * blastTemp / 50).toInt()
                        }

                        var eut = blastProp.eUtOverride
                        if (eut <= 0) eut = VA[MV]

                        // Still output ingotStack now, otherwise ingot has or not ingotHot.
                        GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
                           .circuitMeta(1)
                           .input(dustPrefix, material)
                           .outputs(ingotStack)
                           .EUt(eut)
                           .duration(duration * 0.5)
                           .blastFurnaceTemp(blastTemp)
                           .buildAndRegister()

                        if (material.hasFluid())
                        {
                            // If material has fluid, then allowed to get molten fluid.
                            GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
                                .circuitMeta(2)
                                .input(dustPrefix, material)
                                .fluidOutputs(material.getFluid(L))
                                .EUt(eut)
                                .duration(duration * 0.5)
                                .blastFurnaceTemp(blastTemp)
                                .buildAndRegister()

                            // Another choice, if player has too many ingotStacks, then MBF can blast material like fluid
                            // extractor, i.e. ingotStack -> fluidStack.
                            GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
                                .circuitMeta(4)
                                .inputs(ingotStack)
                                .fluidOutputs(material.getFluid(L))
                                .EUt(eut)
                                .duration(duration * 0.5)
                                .blastFurnaceTemp(blastTemp)
                                .buildAndRegister()

                            // If material has plasma, then plasma is also.
                            if (material.getFluid(FluidStorageKeys.PLASMA) != null)
                            {
                                GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
                                    .circuitMeta(3)
                                    .input(dustPrefix, material)
                                    .fluidOutputs(material.getPlasma(L))
                                    .EUt(eut)
                                    .duration(duration * 0.5)
                                    .blastFurnaceTemp(blastTemp)
                                    .buildAndRegister()

                                // Just like fluid secondary choice, plasma is also.
                                GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
                                    .circuitMeta(5)
                                    .inputs(ingotStack)
                                    .fluidOutputs(material.getPlasma(L))
                                    .EUt(eut)
                                    .duration(duration * 0.5)
                                    .blastFurnaceTemp(blastTemp)
                                    .buildAndRegister()

                            }
                        }
                    }
                }
            }
        }

    }

    private fun getVoltageMultiplier(material: Material): Long = if (material.blastTemperature >= 2800) VA[LV].toLong() else VA[ULV].toLong()

    // @formatter:on

}