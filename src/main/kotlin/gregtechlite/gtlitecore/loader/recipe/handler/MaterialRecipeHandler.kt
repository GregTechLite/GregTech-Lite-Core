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
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.api.recipes.RecipeMaps.BENDER_RECIPES
import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Color
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.info.MaterialFlags.EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES
import gregtech.api.unification.material.info.MaterialFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES
import gregtech.api.unification.material.info.MaterialFlags.EXPLOSIVE
import gregtech.api.unification.material.info.MaterialFlags.FLAMMABLE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.MORTAR_GRINDABLE
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING
import gregtech.api.unification.material.info.MaterialFlags.NO_WORKING
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.gemChipped
import gregtech.api.unification.ore.OrePrefix.gemExquisite
import gregtech.api.unification.ore.OrePrefix.gemFlawed
import gregtech.api.unification.ore.OrePrefix.gemFlawless
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.nugget
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.stack.UnificationEntry
import gregtech.api.util.GTUtility.copyFirst
import gregtech.api.util.GTUtility.scaleVoltage
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROD
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtech.common.items.MetaItems.SHAPE_MOLD_NUGGET
import gregtechlite.gtlitecore.api.unification.material.properties.AlloyBlastProperty
import gregtechlite.gtlitecore.api.unification.material.properties.GTLitePropertyKey
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.duration
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ELECTRIC_IMPLOSION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SLICER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.gemSolitary
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_FLAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_OCTAGONAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_STRIPES
import net.minecraft.item.ItemStack
import kotlin.math.max

@Suppress("unused")
object MaterialRecipeHandler
{

    // @formatter:off

    private val GEM_ORDER = listOf(gemChipped,
                                   gemFlawed,
                                   gem,
                                   gemFlawless,
                                   gemExquisite,
                                   gemSolitary)

    fun init()
    {
        ingot.addProcessingHandler(PropertyKey.INGOT, ::processIngot)
        block.addProcessingHandler(PropertyKey.DUST, ::processBlock)

        /*
         * We force enabled the low quality gem generation, because we add a high quality gem, this is a fine balancing
         * about gem sifting outputs.
         */
        for (i in GEM_ORDER.indices)
        {
            val gemPrefix = GEM_ORDER[i]
            val prevGemPrefix = if (i == 0) null else GEM_ORDER[i - 1]
            gemPrefix.addProcessingHandler(PropertyKey.GEM) { gemPrefix, material, _ ->
                processGemConversion(gemPrefix, prevGemPrefix, material)
            }
        }

        dust.addProcessingHandler(PropertyKey.DUST, ::generateImplosionRecipes)
        ingot.addProcessingHandler(GTLitePropertyKey.ALLOY_BLAST, ::generateABSRecipes)
        dust.addProcessingHandler(PropertyKey.DUST, ::generateMBFRecipes)
    }

    /**
     * @see gregtech.loaders.recipe.handlers.MaterialRecipeHandler.processIngot
     */
    private fun processIngot(ingotPrefix: OrePrefix, material: Material, property: IngotProperty)
    {
        val workingTier = material.workingTier

        // Hand-craft recipes for mortar grindable materials.
        if (material.hasFlag(MORTAR_GRINDABLE) && workingTier <= HV)
        {
            ModHandler.addShapedRecipe(String.format("mortar_grind_%s", material), OreDictUnifier.get(dust, material),
                "X", "m",
                'X', UnificationEntry(ingotPrefix, material))
        }

        if (material.hasFlag(GENERATE_ROD))
        {
            // Common hand-crafting recipes.
            if (workingTier <= HV)
            {
                ModHandler.addShapedRecipe(String.format("stick_%s", material), OreDictUnifier.get(stick, material),
                    "f ", " X",
                    'X', UnificationEntry(ingotPrefix, material))
            }

            if (!material.hasFlag(NO_WORKING))
            {
                // Confirm extruder can process any materials.
                EXTRUDER_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_EXTRUDER_ROD)
                    .input(ingotPrefix, material)
                    .output(stick, material, 2)
                    .EUt(scaleVoltage(6 * getVoltageMultiplier(material), workingTier))
                    .duration(material.mass * 2)
                    .buildAndRegister()

                // ingotX -> stickX (soft: slicer, hard: cutting machine).
                if (material.hasFlag(NO_SMASHING))
                {
                    SLICER_RECIPES.recipeBuilder()
                        .notConsumable(SLICER_BLADE_STRIPES)
                        .input(ingotPrefix, material)
                        .output(stick, material, 2)
                        .EUt(scaleVoltage(6 * getVoltageMultiplier(material), workingTier))
                        .duration(material.mass * 2)
                        .buildAndRegister()
                }
            }
        }

        // Fluid solidification.
        if (material.hasFluid() && !((material.getProperty(PropertyKey.FLUID).solidifiesFrom())!!.equals(null)))
        {
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(material.getProperty(PropertyKey.FLUID).solidifiesFrom(L))
                .output(ingotPrefix, material)
                .EUt(scaleVoltage(VA[ULV], workingTier))
                .duration(1 * SECOND)
                .buildAndRegister()
        }

        // Plastic extruding dustX -> ingotX.
        if (material.hasFlag(NO_SMASHING))
        {
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .input(dust, material)
                .output(ingotPrefix, material)
                .EUt(scaleVoltage(4 * getVoltageMultiplier(material), workingTier))
                .duration(10 * TICK)
                .buildAndRegister()
        }

        // Universal ingotX -> nuggetX alloy smelting.
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_NUGGET)
            .input(ingotPrefix, material)
            .output(nugget, material, 9)
            .EUt(scaleVoltage(VA[ULV], workingTier))
            .duration(material.mass)
            .buildAndRegister()

        // Universal blockX <-> ingotX converts.
        if (!(OreDictUnifier.get(block, material))!!.isEmpty)
        {
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_INGOT)
                .input(block, material)
                .output(ingotPrefix, material, 9)
                .EUt(scaleVoltage(VA[ULV], workingTier))
                .duration(material.mass * 9)
                .buildAndRegister()

            COMPRESSOR_RECIPES.recipeBuilder()
                .input(ingotPrefix, material, (block.getMaterialAmount(material) / M).toInt())
                .output(block, material)
                .buildAndRegister()
        }

        // Bending ingotX -> plateX.
        if (material.hasFlag(GENERATE_PLATE) && !material.hasFlag(NO_WORKING))
        {
            // Common bending processing for hard materials.
            if (!material.hasFlag(NO_SMASHING))
            {
                val plateStack = OreDictUnifier.get(plate, material)
                if (!(plateStack)!!.isEmpty)
                {
                    // Default hand-crafting recipes.
                    if (workingTier <= HV)
                    {
                        ModHandler.addShapedRecipe(String.format("plate_%s", material), plateStack,
                            "h", "I", "I",
                            'I', UnificationEntry(ingotPrefix, material))
                    }

                    // 1:1 Bending.
                    BENDER_RECIPES.recipeBuilder()
                        .circuitMeta(1)
                        .input(ingotPrefix, material)
                        .outputs(plateStack)
                        .EUt(scaleVoltage(24, workingTier))
                        .duration(material.mass)
                        .buildAndRegister()

                    // 3:2 Hamming.
                    FORGE_HAMMER_RECIPES.recipeBuilder()
                        .input(ingotPrefix, material, 3)
                        .outputs(copyFirst(2, plateStack))
                        .EUt(scaleVoltage(VH[LV], workingTier))
                        .duration(material.mass)
                        .buildAndRegister()
                }
            }

            // Another safety filtered checking for filter all gem materials.
            if (!(OreDictUnifier.get(plate, material))!!.isEmpty)
            {
                if (material.hasFlag(NO_SMASHING))
                {
                    // Slicing ingotX -> plateX.
                    SLICER_RECIPES.recipeBuilder()
                        .notConsumable(SLICER_BLADE_FLAT)
                        .input(ingotPrefix, material)
                        .output(plate, material)
                        .EUt(scaleVoltage(8 * getVoltageMultiplier(material), workingTier))
                        .duration(material.mass)
                        .buildAndRegister()

                    // Special extruding dustX -> plateX.
                    EXTRUDER_RECIPES.recipeBuilder()
                        .notConsumable(SHAPE_EXTRUDER_PLATE)
                        .input(dust, material)
                        .output(plate, material)
                        .EUt(scaleVoltage(8 * getVoltageMultiplier(material), workingTier))
                        .duration(material.mass)
                        .buildAndRegister()
                }
                else
                {
                    // Hard extruding.
                    EXTRUDER_RECIPES.recipeBuilder()
                        .notConsumable(SHAPE_EXTRUDER_PLATE)
                        .input(ingotPrefix, material)
                        .output(plate, material)
                        .EUt(scaleVoltage(8 * getVoltageMultiplier(material), workingTier))
                        .duration(material.mass)
                        .buildAndRegister()
                }
            }
        }
    }

    /**
     * @see gregtech.loaders.recipe.handlers.MaterialRecipeHandler.processBlock
     */
    private fun processBlock(blockPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        val blockStack = OreDictUnifier.get(blockPrefix, material)
        val workingTier = material.workingTier
        val materialAmount = blockPrefix.getMaterialAmount(material)

        // Fluid solidification.
        if (material.hasFluid() && !(material.getProperty(PropertyKey.FLUID).solidifiesFrom())!!.equals(null))
        {
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(material.getProperty(PropertyKey.FLUID).solidifiesFrom((materialAmount * L / M).toInt()))
                .outputs(blockStack)
                .EUt(scaleVoltage(VA[ULV], workingTier))
                .duration(material.mass)
                .buildAndRegister()
        }

        // blockX -> plateX.
        if (material.hasFlag(GENERATE_PLATE))
        {
            val plateStack = OreDictUnifier.get(plate, material)
            if (!(plateStack)!!.isEmpty)
            {
                // Plastic blockX -> plateX.
                if (material.hasFlag(NO_SMASHING) && !material.hasProperty(PropertyKey.GEM))
                {
                    SLICER_RECIPES.recipeBuilder()
                        .notConsumable(SLICER_BLADE_OCTAGONAL)
                        .input(blockPrefix, material)
                        .outputs(copyFirst((materialAmount / M).toInt(), plateStack))
                        .EUt(scaleVoltage(VA[LV], workingTier))
                        .duration(material.mass * 8)
                        .buildAndRegister()
                }
                else // Common cutting.
                {
                    CUTTER_RECIPES.recipeBuilder()
                        .input(blockPrefix, material)
                        .outputs(copyFirst((materialAmount / M).toInt(), plateStack))
                        .EUt(scaleVoltage(VA[LV], workingTier))
                        .duration(material.mass * 8)
                        .buildAndRegister()
                }
            }
        }

        // Preventable conversion recipes for (block) <=> (dust, ingot, gem).
        val blockEntry = if (material.hasProperty(PropertyKey.GEM))
            UnificationEntry(gem, material)
        else if (material.hasProperty(PropertyKey.INGOT))
            UnificationEntry(ingot, material)
        else
            UnificationEntry(dust, material)

        val result = arrayListOf<Any>()
        repeat((materialAmount / M).toInt()) {
            result.add(blockEntry)
        }

        // Do not allow hand-crafting or uncrafting, extruding or alloy smelting of blacklisted block.
        if (!material.hasFlag(EXCLUDE_BLOCK_CRAFTING_RECIPES))
        {
            // Do not allow hand-crafting or uncrafting of blacklisted blocks.
            if (!material.hasFlag(EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES)
                    && !ConfigHolder.recipes.disableManualCompression)
            {
                ModHandler.addShapelessRecipe(String.format("block_compress_%s", material), blockStack,
                    *result.toTypedArray())

                ModHandler.addShapelessRecipe(String.format("block_decompress_%s", material),
                    copyFirst((materialAmount / M).toInt(), OreDictUnifier.get(blockEntry)),
                    UnificationEntry(blockPrefix, material))
            }

            // Ingot converts.
            if (material.hasProperty(PropertyKey.INGOT))
            {
                EXTRUDER_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_EXTRUDER_BLOCK)
                    .input(ingot, material, (materialAmount / M).toInt())
                    .outputs(blockStack)
                    .EUt(scaleVoltage(8 * getVoltageMultiplier(material), workingTier))
                    .duration(10 * TICK)
                    .buildAndRegister()

                ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_BLOCK)
                    .input(ingot, material, (materialAmount / M).toInt())
                    .outputs(blockStack)
                    .EUt(scaleVoltage(4 * getVoltageMultiplier(material), workingTier))
                    .duration(5 * TICK)
                    .buildAndRegister()
            }
            else if (material.hasProperty(PropertyKey.GEM))
            {
                COMPRESSOR_RECIPES.recipeBuilder()
                    .input(gem, material, (block.getMaterialAmount(material) / M).toInt())
                    .outputs(blockStack)
                    .EUt(scaleVoltage(2, workingTier)) // ULV
                    .duration(15 * SECOND)
                    .buildAndRegister()

                FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(blockPrefix, material)
                    .output(gem, material, (block.getMaterialAmount(material) / M).toInt())
                    .EUt(scaleVoltage(24, workingTier)) // LV
                    .duration(5 * SECOND)
                    .buildAndRegister()

            }

        }
    }

    /**
     * @see gregtech.loaders.recipe.handlers.MaterialRecipeHandler.processGemConversion
     */
    private fun processGemConversion(gemPrefix: OrePrefix, prevPrefix: OrePrefix?, material: Material)
    {
        val materialAmount = gemPrefix.getMaterialAmount(material)
        val workingTier = material.workingTier
        val crushedStack = OreDictUnifier.getDust(material, materialAmount)

        // Hand-craft gem grinding.
        if (material.hasFlag(MORTAR_GRINDABLE) && workingTier <= HV)
        {
            ModHandler.addShapedRecipe(String.format("gem_to_dust_%s_%s", material, gemPrefix), crushedStack,
                "X", "m",
                'X', UnificationEntry(gemPrefix, material))
        }

        val prevStack = if (prevPrefix == null) ItemStack.EMPTY else OreDictUnifier.get(prevPrefix, material, 2)
        if (!prevStack.isEmpty)
        {
            ModHandler.addShapelessRecipe(String.format("gem_to_gem_%s_%s", prevPrefix, material), prevStack,
                "h", UnificationEntry(gemPrefix, material))

            CUTTER_RECIPES.recipeBuilder()
                .input(gemPrefix, material)
                .outputs(prevStack)
                .EUt(VH[LV])
                .duration(1 * SECOND)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.White)
                .inputs(prevStack)
                .output(gemPrefix, material)
                .EUt(scaleVoltage(VHA[HV], workingTier))
                .duration(15 * SECOND)
                .buildAndRegister()
        }

    }

    private fun generateImplosionRecipes(dustPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        val workingTier = material.workingTier
        val dustStack = OreDictUnifier.get(dustPrefix, material)

        if (material.hasProperty(PropertyKey.GEM))
        {
            val gemStack = OreDictUnifier.get(gem, material)
            if (!material.hasFlag(EXPLOSIVE) && !material.hasFlag(FLAMMABLE))
            {
                ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
                    .inputs(dustStack.copy(4))
                    .outputs(gemStack.copy(3))
                    .chancedOutput(dust, DarkAsh, 2500, 0)
                    .EUt(scaleVoltage(VA[LV], workingTier))
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
            if (!material.hasAnyOfFlags(FLAMMABLE, NO_SMELTING))
            {
                val ingotStack = OreDictUnifier.get(ingot, material)
                var blastTemp = material.blastTemperature

                if (!ingotStack.isEmpty)
                {
                    if (blastTemp == 0) blastTemp += 1200

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
                        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
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
                            TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
                                .circuitMeta(2)
                                .input(dustPrefix, material)
                                .fluidOutputs(material.getFluid(L))
                                .EUt(eut)
                                .duration(duration * 0.5)
                                .blastFurnaceTemp(blastTemp)
                                .buildAndRegister()

                            // Another choice, if player has too many ingotStacks, then MBF can blast material like fluid
                            // extractor, i.e. ingotStack -> fluidStack.
                            TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
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
                                TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
                                    .circuitMeta(3)
                                    .input(dustPrefix, material)
                                    .fluidOutputs(material.getPlasma(L))
                                    .EUt(eut)
                                    .duration(duration * 0.5)
                                    .blastFurnaceTemp(blastTemp)
                                    .buildAndRegister()

                                // Just like fluid secondary choice, plasma is also.
                                TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
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

    private fun scaleVoltage(voltage: Int, workingTier: Int) = scaleVoltage(voltage.toLong(), workingTier)

    // @formatter:on

}