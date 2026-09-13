package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.M
import gregtech.api.GTValues.VA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.ingredients.GTRecipeItemInput
import gregtech.api.recipes.ingredients.GTRecipeOreInput
import gregtech.api.unification.FluidUnifier
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.SamariumMagnetic
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.gemFlawless
import gregtech.api.unification.ore.OrePrefix.pipeHugeFluid
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_EV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_HV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LuV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_MV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_OpV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UEV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UHV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UIV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UXV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_EV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_HV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_MV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_OpV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_EV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_HV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_MV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_OpV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UXV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_EV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_HV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_MV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_OpV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UXV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM
import gregtech.common.items.MetaItems.EMITTER_EV
import gregtech.common.items.MetaItems.EMITTER_HV
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.EMITTER_LV
import gregtech.common.items.MetaItems.EMITTER_LuV
import gregtech.common.items.MetaItems.EMITTER_MV
import gregtech.common.items.MetaItems.EMITTER_OpV
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_UIV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.EMITTER_UXV
import gregtech.common.items.MetaItems.EMITTER_ZPM
import gregtech.common.items.MetaItems.FIELD_GENERATOR_EV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_HV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_MV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_OpV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UIV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UXV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.ROBOT_ARM_EV
import gregtech.common.items.MetaItems.ROBOT_ARM_HV
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_LV
import gregtech.common.items.MetaItems.ROBOT_ARM_LuV
import gregtech.common.items.MetaItems.ROBOT_ARM_MV
import gregtech.common.items.MetaItems.ROBOT_ARM_OpV
import gregtech.common.items.MetaItems.ROBOT_ARM_UEV
import gregtech.common.items.MetaItems.ROBOT_ARM_UHV
import gregtech.common.items.MetaItems.ROBOT_ARM_UIV
import gregtech.common.items.MetaItems.ROBOT_ARM_UV
import gregtech.common.items.MetaItems.ROBOT_ARM_UXV
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.SENSOR_EV
import gregtech.common.items.MetaItems.SENSOR_HV
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.SENSOR_LV
import gregtech.common.items.MetaItems.SENSOR_LuV
import gregtech.common.items.MetaItems.SENSOR_MV
import gregtech.common.items.MetaItems.SENSOR_OpV
import gregtech.common.items.MetaItems.SENSOR_UEV
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.SENSOR_UIV
import gregtech.common.items.MetaItems.SENSOR_UV
import gregtech.common.items.MetaItems.SENSOR_UXV
import gregtech.common.items.MetaItems.SENSOR_ZPM
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.collection.obj2LongHashMapOf
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COMPONENT_ASSEMBLY_LINE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromiumGermaniumTellurideMagnetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONVEYOR_MODULE_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_MOTOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PISTON_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PUMP_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EMITTER_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FIELD_GENERATOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ROBOT_ARM_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SENSOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_EV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_HV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_IV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_LV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_LuV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_MV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_ULV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_ZPM
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary

/**
 * Component Assembly Line (CoAL) recipe producer.
 *
 * Rule-driven replacement of the old handwritten 64x-expanded recipes. Instead of
 * maintaining a giant handwritten table, this producer reads the single-unit
 * component recipes directly from [ASSEMBLER_RECIPES] (LV-EV) and
 * [ASSEMBLY_LINE_RECIPES] (LuV+) and scales every input by 64:
 *
 * - other components / meta items: x64, split into stacks of 64;
 * - circuits: packed into WRAP_CIRCUIT (16 circuits = 1 wrap);
 * - "any" ore-dict tags (rubbers): one recipe per concrete material; all
 *   occurrences of the same tag in one recipe share the same material and
 *   are paid as one merged fluid input;
 * - wires / cables: compressed to wireGtHex / cableGtHex, otherwise fluid;
 * - plates: compressed to plateDense (9x) / plateDouble (2x) when the material
 *   supports it and the amount divides evenly, otherwise fluid;
 * - sticks: compressed to stickLong at LV-EV, fluid from LuV+ (magnetic rods stay
 *   solid); small components (ring/round/screw/foil/frame/gear/rotor/wireFine/
 *   pipe) stay items at LV-EV and become fluid from LuV+;
 * - solid inputs that the rules above fluidize keep an item-stack fallback;
 *   when a variant exceeds 12 fluid inputs, the cheapest fallbacks are turned
 *   back into items until the recipe fits the 12/12 input limits.
 * - explicit fluids: x64 and merged per material; Halkonite Steel also pays an
 *   equal amount of Bedrockium.
 *
 * Duration follows the tier table, EUt = VA[tier], the CoAL tier property is set,
 * and every recipe outputs 64 items.
 */
internal object ComponentAssemblyLineRecipeProducer
{
    // @formatter:off

    private const val MAX_ITEM_INPUTS = 12
    private const val MAX_FLUID_INPUTS = 12
    private const val MAX_FLUID_AMOUNT = Int.MAX_VALUE.toLong()

    private val DURATION_BY_TIER = intArrayOf(0, 15 * TICK, 1 * SECOND + 10 * TICK, 1 * SECOND + 10 * TICK,
        2 * SECOND + 5 * TICK, 2 * SECOND + 5 * TICK, 3 * SECOND, 3 * SECOND, 3 * SECOND + 15 * TICK,
        3 * SECOND + 15 * TICK, 4 * SECOND + 10 * TICK, 4 * SECOND + 10 * TICK, 5 * SECOND + 5 * TICK,
        5 * SECOND + 5 * TICK, 6 * SECOND)

    private val WRAP_CIRCUIT_BY_TIER = arrayOf( // TODO: Use CraftingComponent API?
        WRAP_CIRCUIT_ULV, WRAP_CIRCUIT_LV, WRAP_CIRCUIT_MV, WRAP_CIRCUIT_HV, WRAP_CIRCUIT_EV,
        WRAP_CIRCUIT_IV, WRAP_CIRCUIT_LuV, WRAP_CIRCUIT_ZPM, WRAP_CIRCUIT_UV, WRAP_CIRCUIT_UHV,
        WRAP_CIRCUIT_UEV, WRAP_CIRCUIT_UIV, WRAP_CIRCUIT_UXV, WRAP_CIRCUIT_OpV, WRAP_CIRCUIT_MAX)

    private val CIRCUIT_MARKER_BY_TIER = arrayOf( // TODO: Use TierBridge API?
        Tier.ULV, Tier.LV, Tier.MV, Tier.HV, Tier.EV,
        Tier.IV, Tier.LuV, Tier.ZPM, Tier.UV, Tier.UHV,
        Tier.UEV, Tier.UIV, Tier.UXV, Tier.OpV, Tier.MAX)

    // Magnetic rods stay solid even at LuV+, everything else rod-like melts.
    private val MAGNETIC_STICK_LONG_MATERIALS = setOf(SamariumMagnetic, ChromiumGermaniumTellurideMagnetic, Magnetium)

    private val PLATE_PREFIXES = setOf(plate, plateDouble, plateDense)
    private val WIRE_PREFIXES = setOf(wireGtSingle, wireGtDouble, wireGtQuadruple, wireGtOctal, wireGtHex)
    private val CABLE_PREFIXES = setOf(cableGtSingle, cableGtDouble, cableGtQuadruple, cableGtOctal, cableGtHex)
    private val PIPE_PREFIXES = setOf(pipeSmallFluid, pipeNormalFluid, pipeLargeFluid, pipeHugeFluid)
    private val GEM_PREFIXES = setOf(gem, gemFlawless)
    // Heavy base wire forms are always paid as molten material (field generator coils).
    private val FLUID_WIRE_PREFIXES = setOf(wireGtQuadruple, wireGtOctal)

    private val COMPONENTS = arrayOf( // TODO: Use CraftingComponentAPI?
        arrayOf(ELECTRIC_MOTOR_LV, ELECTRIC_MOTOR_MV, ELECTRIC_MOTOR_HV, ELECTRIC_MOTOR_EV,
            ELECTRIC_MOTOR_IV, ELECTRIC_MOTOR_LuV, ELECTRIC_MOTOR_ZPM, ELECTRIC_MOTOR_UV,
            ELECTRIC_MOTOR_UHV, ELECTRIC_MOTOR_UEV, ELECTRIC_MOTOR_UIV, ELECTRIC_MOTOR_UXV,
            ELECTRIC_MOTOR_OpV, ELECTRIC_MOTOR_MAX),
        arrayOf(ELECTRIC_PISTON_LV, ELECTRIC_PISTON_MV, ELECTRIC_PISTON_HV, ELECTRIC_PISTON_EV,
            ELECTRIC_PISTON_IV, ELECTRIC_PISTON_LUV, ELECTRIC_PISTON_ZPM, ELECTRIC_PISTON_UV,
            ELECTRIC_PISTON_UHV, ELECTRIC_PISTON_UEV, ELECTRIC_PISTON_UIV, ELECTRIC_PISTON_UXV,
            ELECTRIC_PISTON_OpV, ELECTRIC_PISTON_MAX),
        arrayOf(ELECTRIC_PUMP_LV, ELECTRIC_PUMP_MV, ELECTRIC_PUMP_HV, ELECTRIC_PUMP_EV,
            ELECTRIC_PUMP_IV, ELECTRIC_PUMP_LuV, ELECTRIC_PUMP_ZPM, ELECTRIC_PUMP_UV,
            ELECTRIC_PUMP_UHV, ELECTRIC_PUMP_UEV, ELECTRIC_PUMP_UIV, ELECTRIC_PUMP_UXV,
            ELECTRIC_PUMP_OpV, ELECTRIC_PUMP_MAX),
        arrayOf(CONVEYOR_MODULE_LV, CONVEYOR_MODULE_MV, CONVEYOR_MODULE_HV, CONVEYOR_MODULE_EV,
            CONVEYOR_MODULE_IV, CONVEYOR_MODULE_LuV, CONVEYOR_MODULE_ZPM, CONVEYOR_MODULE_UV,
            CONVEYOR_MODULE_UHV, CONVEYOR_MODULE_UEV, CONVEYOR_MODULE_UIV, CONVEYOR_MODULE_UXV,
            CONVEYOR_MODULE_OpV, CONVEYOR_MODULE_MAX),
        arrayOf(ROBOT_ARM_LV, ROBOT_ARM_MV, ROBOT_ARM_HV, ROBOT_ARM_EV,
            ROBOT_ARM_IV, ROBOT_ARM_LuV, ROBOT_ARM_ZPM, ROBOT_ARM_UV,
            ROBOT_ARM_UHV, ROBOT_ARM_UEV, ROBOT_ARM_UIV, ROBOT_ARM_UXV,
            ROBOT_ARM_OpV, ROBOT_ARM_MAX),
        arrayOf(EMITTER_LV, EMITTER_MV, EMITTER_HV, EMITTER_EV,
            EMITTER_IV, EMITTER_LuV, EMITTER_ZPM, EMITTER_UV,
            EMITTER_UHV, EMITTER_UEV, EMITTER_UIV, EMITTER_UXV,
            EMITTER_OpV, EMITTER_MAX),
        arrayOf(SENSOR_LV, SENSOR_MV, SENSOR_HV, SENSOR_EV,
            SENSOR_IV, SENSOR_LuV, SENSOR_ZPM, SENSOR_UV,
            SENSOR_UHV, SENSOR_UEV, SENSOR_UIV, SENSOR_UXV,
            SENSOR_OpV, SENSOR_MAX),
        arrayOf(FIELD_GENERATOR_LV, FIELD_GENERATOR_MV, FIELD_GENERATOR_HV, FIELD_GENERATOR_EV,
            FIELD_GENERATOR_IV, FIELD_GENERATOR_LuV, FIELD_GENERATOR_ZPM, FIELD_GENERATOR_UV,
            FIELD_GENERATOR_UHV, FIELD_GENERATOR_UEV, FIELD_GENERATOR_UIV, FIELD_GENERATOR_UXV,
            FIELD_GENERATOR_OpV, FIELD_GENERATOR_MAX))

    private data class Target(val item: MetaItem<*>.MetaValueItem, val tier: Int, val circuit: Int)

    private class FluidContribution(val material: Material, val amount: Long,
                                    val extraMaterial: Material?, val extraAmount: Long,
                                    val itemStacks: List<ItemStack>)
    {
        val itemSlots: Int
            get() = itemStacks.size
    }

    private class Variant
    {
        val items = mutableListOf<ItemStack>()
        val fluids = obj2LongHashMapOf<Material>()
        val genericFluids = obj2LongHashMapOf<Fluid>()
        val contributions = mutableListOf<FluidContribution>()

        val itemSlots: Int
            get() = items.size

        val fluidSlots: Int
            get() = fluids.size + genericFluids.size

        fun fitsWithinLimits(): Boolean = itemSlots <= MAX_ITEM_INPUTS && fluidSlots <= MAX_FLUID_INPUTS

        fun duplicate(): Variant = Variant().apply {
            items.addAll(this@Variant.items)
            fluids.putAll(this@Variant.fluids)
            genericFluids.putAll(this@Variant.genericFluids)
            contributions.addAll(this@Variant.contributions)
        }

        fun replaceWith(snapshot: VariantSnapshot)
        {
            items.clear()
            items.addAll(snapshot.items)
            fluids.clear()
            fluids.putAll(snapshot.fluids)
            genericFluids.clear()
            genericFluids.putAll(snapshot.genericFluids)
        }
    }

    /**
     * Immutable snapshot of a [Variant]'s payable inputs. The repair search
     * keeps the best candidate as one value, so the items and the two fluid
     * maps can never drift out of sync.
     */
    private data class VariantSnapshot(val items: List<ItemStack>, val fluids: Map<Material, Long>,
                                       val genericFluids: Map<Fluid, Long>)
    {
        val itemSlots: Int
            get() = items.size
    }

    fun produce()
    {
        val targetByItem = COMPONENTS.flatMapIndexed { familyIdx, family ->
            family.mapIndexed { tierIdx, item -> item to Target(item, tierIdx + 1, familyIdx + 1) }
        }.toMap()

        val recipes = ASSEMBLER_RECIPES.recipeList.asSequence() + ASSEMBLY_LINE_RECIPES.recipeList.asSequence()

        var dropped = 0
        for (base in recipes)
        {
            if (base.isHidden) continue
            val output = base.outputs.firstOrNull() ?: continue
            val metaItem = (output.item as? MetaItem<*>)?.getItem(output) ?: continue
            val target = targetByItem[metaItem] ?: continue
            dropped += generateCoALRecipe(base, target, output)
        }

        if (dropped > 0)
            LOGGER.error("Dropped {} CoAL recipe variant(s); see the messages above for the affected components",
                dropped)
    }

    private fun generateCoALRecipe(base: Recipe, target: Target, output: ItemStack): Int
    {
        var variants = listOf(Variant())

        val groupedAnyInputs = linkedMapOf<Int, MutableList<GTRecipeOreInput>>()

        for (input in base.inputs)
        {
            if (input.isNonConsumable) continue
            when (input)
            {
                is GTRecipeOreInput  ->
                {
                    if (OreDictionary.getOreName(input.oreDict).contains("Any"))
                        groupedAnyInputs.getOrPut(input.oreDict) { mutableListOf() }.add(input)
                    else
                        variants = expandOreInput(variants, input, target.tier)
                }
                is GTRecipeItemInput ->
                {
                    variants = applyItemInput(variants, input)
                }
                else                 -> {}
            }
        }

        for (anyInputs in groupedAnyInputs.values)
        {
            variants = expandGroupedAnyInput(variants, anyInputs, target.tier)
        }

        for (fluidInput in base.fluidInputs)
        {
            val fluid = fluidInput.inputFluidStack ?: continue
            variants.forEach { addFluidInput(it, fluid) }
        }

        var dropped = 0
        for (variant in variants)
        {
            repairVariant(variant)
            if (!variant.fitsWithinLimits())
            {
                LOGGER.warn("Skipped CoAL recipe for {}: {} item inputs (max {}), {} fluid inputs (max {})",
                    output.displayName, variant.itemSlots, MAX_ITEM_INPUTS, variant.fluidSlots, MAX_FLUID_INPUTS)
                dropped++
                continue
            }

            val maxFluidAmount = (variant.fluids.values + variant.genericFluids.values).maxOrNull() ?: 0L
            if (maxFluidAmount > MAX_FLUID_AMOUNT)
            {
                LOGGER.error("Skipped CoAL recipe for {}: fluid amount {} exceeds the Int limit {}",
                    output.displayName, maxFluidAmount, MAX_FLUID_AMOUNT)
                dropped++
                continue
            }

            COMPONENT_ASSEMBLY_LINE_RECIPES.addRecipe {
                circuitMeta(target.circuit)
                EUt(VA[target.tier])
                duration(DURATION_BY_TIER[target.tier] * SECOND)
                tier(target.tier)
                variant.items.forEach { inputs(it) }
                variant.fluids.forEach { (material, amount) -> fluidInputs(material.getFluid(amount.toInt())) }
                variant.genericFluids.forEach { (fluid, amount) -> fluidInputs(FluidStack(fluid, amount.toInt())) }
                output(target.item, 64)
            }
        }
        return dropped
    }

    private fun expandOreInput(variants: List<Variant>, input: GTRecipeOreInput, tier: Int): List<Variant>
    {
        val stacks = input.inputStacks
        if (stacks.isEmpty()) return variants

        val oreName = OreDictionary.getOreName(input.oreDict) // TODO: Use TierBridge API?
        if (oreName.startsWith("circuit"))
        {
            val wrapIdx = CIRCUIT_MARKER_BY_TIER.indexOfFirst { oreName == "circuit" + it.toCamelCaseString() }
            if (wrapIdx >= 0)
            {
                val wraps = input.amount * 64L / 16
                variants.forEach { addItemStack(it.items, WRAP_CIRCUIT_BY_TIER[wrapIdx].stackForm, wraps) }
            }
            return variants
        }

        val first = stacks.first()
        val prefix = OreDictUnifier.getPrefix(first) ?: return variants

        val materials = distinctMaterials(stacks)
        if (materials.isEmpty()) return variants

        return if (materials.size == 1)
        {
            val material = materials.first()
            variants.forEach { addOrePart(it, prefix, material, input.amount, tier, false) }
            variants
        }
        else
        {
            variants.flatMap { base ->
                materials.map { material ->
                    val variant = base.duplicate()
                    addOrePart(variant, prefix, material, input.amount, tier, false)
                    variant
                }
            }
        }
    }

    /**
     * Collects the distinct materials of [stacks] in first-seen order, keeping
     * the generated variants deterministic.
     */
    private fun distinctMaterials(stacks: Array<ItemStack>): Set<Material>
    {
        val materials = linkedSetOf<Material>()
        for (stack in stacks)
        {
            val material = OreDictUnifier.getMaterial(stack)?.material ?: continue
            materials.add(material)
        }
        return materials
    }

    /**
     * Expands a group of identical Any ore-dict inputs as a single choice.
     *
     * All occurrences of the same Any tag inside one recipe must use the same
     * concrete material. Their amounts are summed and paid once, avoiding the
     * N^occurrences Cartesian product produced by expanding every input separately.
     */
    private fun expandGroupedAnyInput(variants: List<Variant>, inputs: List<GTRecipeOreInput>, tier: Int): List<Variant>
    {
        val first = inputs.firstOrNull() ?: return variants
        val stacks = first.inputStacks
        if (stacks.isEmpty()) return variants

        val prefix = OreDictUnifier.getPrefix(stacks.first()) ?: return variants

        val materials = distinctMaterials(stacks)
        if (materials.isEmpty()) return variants

        val totalCount = inputs.sumOf { it.amount }

        return variants.flatMap { base ->
            materials.map { material ->
                val variant = base.duplicate()
                addOrePart(variant, prefix, material, totalCount, tier, true)
                variant
            }
        }
    }

    private fun addOrePart(variant: Variant, prefix: OrePrefix, material: Material,
                           count: Int, tier: Int, forceFluid: Boolean)
    {
        if (forceFluid)
        {
            addFluid(variant, material, toFluidAmount(prefix.getMaterialAmount(material) * count * 64L))
            return
        }

        val amountPerItem = prefix.getMaterialAmount(material)
        if (amountPerItem <= 0)
        {
            // Prefix without a material amount (e.g. nanite): keep it as an item.
            addItemStack(variant.items, OreDictUnifier.get(prefix, material), count * 64L)
            return
        }

        val total = amountPerItem * count * 64L
        val itemAlternative = buildItemAlternative(prefix, material, count)

        if (prefix == stickLong && material in MAGNETIC_STICK_LONG_MATERIALS)
        {
            addItemStack(variant.items, OreDictUnifier.get(stickLong, material), count * 64L)
            return
        }

        when
        {
            prefix == stick || prefix == stickLong ->
            {
                if (tier >= LuV)
                    addFluid(variant, material, toFluidAmount(total), itemAlternative)
                else
                {
                    val longCount = total / stickLong.getMaterialAmount(material)
                    if (longCount > 0)
                        addItemStack(variant.items, OreDictUnifier.get(stickLong, material), longCount)
                    else
                        addFluid(variant, material, toFluidAmount(total), itemAlternative)
                }
            }
            prefix in PLATE_PREFIXES -> addPlate(variant, material, total, itemAlternative)
            prefix in WIRE_PREFIXES ->
            {
                if (prefix in FLUID_WIRE_PREFIXES)
                    addFluid(variant, material, toFluidAmount(total), itemAlternative)
                else
                    compressToHex(variant, material, total, wireGtHex, itemAlternative)
            }
            prefix in CABLE_PREFIXES -> compressToHex(variant, material, total, cableGtHex, itemAlternative)
            prefix in PIPE_PREFIXES ->
            {
                if (tier >= LuV)
                    addFluid(variant, material, toFluidAmount(total), itemAlternative)
                else
                {
                    val hugeAmount = pipeHugeFluid.getMaterialAmount(material)
                    if (hugeAmount > 0 && total % hugeAmount == 0L)
                    {
                        val hugeCount = total / hugeAmount
                        if (hugeCount <= 64)
                        {
                            addItemStack(variant.items, OreDictUnifier.get(pipeHugeFluid, material), hugeCount)
                            return
                        }
                    }
                    addFluid(variant, material, toFluidAmount(total), itemAlternative)
                }
            }
            prefix in GEM_PREFIXES ->
            {
                if (count <= 1)
                    addItemStack(variant.items, OreDictUnifier.get(prefix, material), count * 64L)
                else
                    addFluid(variant, material, toFluidAmount(total), itemAlternative)
            }
            tier >= LuV -> addFluid(variant, material, toFluidAmount(total), itemAlternative)
            else -> addItemStack(variant.items, OreDictUnifier.get(prefix, material), count * 64L)
        }
    }

    private fun addPlate(variant: Variant, material: Material, total: Long,
                         itemAlternative: List<ItemStack>?)
    {
        for (compressedPrefix in arrayOf(plateDense, plateDouble))
        {
            val compressedAmount = compressedPrefix.getMaterialAmount(material)
            if (compressedAmount <= 0 || total % compressedAmount != 0L) continue
            val stack = OreDictUnifier.get(compressedPrefix, material)
            if (stack.isEmpty) continue
            val count = total / compressedAmount
            if (count <= 64)
            {
                addItemStack(variant.items, stack, count)
                return
            }
        }
        addFluid(variant, material, toFluidAmount(total), itemAlternative)
    }

    private fun compressToHex(variant: Variant, material: Material, total: Long, hexPrefix: OrePrefix,
                              itemAlternative: List<ItemStack>?)
    {
        val hexAmount = hexPrefix.getMaterialAmount(material)
        if (hexAmount > 0 && total % hexAmount == 0L)
        {
            val hexCount = total / hexAmount
            if (hexCount <= 64)
            {
                addItemStack(variant.items, OreDictUnifier.get(hexPrefix, material), hexCount)
                return
            }
        }
        addFluid(variant, material, toFluidAmount(total), itemAlternative)
    }

    /**
     * Builds the item-stack representation of a solid input that the normal
     * rules decided to fluidize. The repair pass can use it to trade item slots
     * for fluid slots while keeping the same material amount.
     */
    private fun buildItemAlternative(prefix: OrePrefix, material: Material, count: Int): List<ItemStack>?
    {
        val total = prefix.getMaterialAmount(material) * count * 64L
        if (total <= 0) return null

        val candidates = when (prefix) {
            in PLATE_PREFIXES -> listOf(plateDense, plateDouble, prefix)
            in WIRE_PREFIXES  -> listOf(wireGtHex, prefix)
            in CABLE_PREFIXES -> listOf(cableGtHex, prefix)
            in PIPE_PREFIXES  -> listOf(pipeHugeFluid, prefix)
            stick             -> listOf(stickLong, stick)
            else              -> listOf(prefix)
        }

        for (candidate in candidates)
        {
            val candidateAmount = candidate.getMaterialAmount(material)
            if (candidateAmount <= 0 || total % candidateAmount != 0L) continue
            val stack = OreDictUnifier.get(candidate, material)
            if (stack.isEmpty) continue

            val itemStacks = mutableListOf<ItemStack>()
            addItemStack(itemStacks, stack, total / candidateAmount)
            if (itemStacks.isNotEmpty()) return itemStacks
        }
        return null
    }

    /**
     * Converts a GTCEu internal material amount into millibuckets of fluid.
     */
    private fun toFluidAmount(materialAmount: Long): Long = materialAmount * L / M

    /**
     * Adds [input] to every existing variant. Unlike the expand* helpers this
     * cannot fork a variant, so it hands the same list back; the uniform
     * `variants = step(variants)` shape in [generateCoALRecipe] is what makes the two
     * kinds of step read the same.
     */
    private fun applyItemInput(variants: List<Variant>, input: GTRecipeItemInput): List<Variant>
    {
        val stack = input.inputStacks.firstOrNull() ?: return variants
        variants.forEach { addItemStack(it.items, stack, stack.count.toLong() * 64) }
        return variants
    }

    private fun addItemStack(items: MutableList<ItemStack>, stack: ItemStack, count: Long)
    {
        if (stack.isEmpty) return
        var remaining = count
        while (remaining > 0)
        {
            val copy = stack.copy()
            copy.count = minOf(remaining, 64L).toInt()
            items.add(copy)
            remaining -= copy.count
        }
    }

    @Suppress("UnstableApiUsage")
    private fun addFluidInput(variant: Variant, fluid: FluidStack)
    {
        val amount = fluid.amount.toLong() * 64
        val material = FluidUnifier.getMaterialFromFluid(fluid.fluid)
        if (material != null)
            addFluid(variant, material, amount)
        else
            variant.genericFluids.merge(fluid.fluid, amount, Long::plus)
    }

    private fun addFluid(variant: Variant, material: Material, amount: Long,
                         itemAlternative: List<ItemStack>? = null)
    {
        if (amount <= 0) return
        variant.fluids.merge(material, amount, Long::plus)

        val extraMaterial = if (material == HalkoniteSteel) Bedrockium else null
        if (extraMaterial != null)
            variant.fluids.merge(extraMaterial, amount, Long::plus)

        if (!itemAlternative.isNullOrEmpty())
        {
            variant.contributions.add(
                FluidContribution(material, amount, extraMaterial,
                                  if (extraMaterial != null) amount else 0L, itemAlternative))
        }
    }

    /**
     * Slot-aware repair pass. When the default compression rules produce too
     * many fluid inputs, search solid-to-fluid contributions that can be paid
     * as items again and pick the combination with the smallest item-slot cost.
     */
    private fun repairVariant(variant: Variant)
    {
        if (variant.fluidSlots <= MAX_FLUID_INPUTS) return
        if (variant.contributions.isEmpty()) return

        val working = variant.duplicate()
        val candidates = variant.contributions.sortedBy { it.itemSlots }

        var best: VariantSnapshot? = null

        fun search(index: Int, itemSlots: Int)
        {
            val previous = best
            if (itemSlots <= MAX_ITEM_INPUTS && working.fluidSlots <= MAX_FLUID_INPUTS
                && (previous == null || itemSlots < previous.itemSlots))
            {
                best = VariantSnapshot(working.items.toList(),
                    LinkedHashMap(working.fluids), LinkedHashMap(working.genericFluids))
            }

            if (index >= candidates.size) return

            // No branch below can lower itemSlots, so stop once we are no better.
            val recorded = best
            if (recorded != null && itemSlots >= recorded.itemSlots) return

            // Leave this contribution fluidized.
            search(index + 1, itemSlots)

            val contribution = candidates[index]
            if (itemSlots + contribution.itemSlots <= MAX_ITEM_INPUTS)
            {
                applyContribution(working, contribution)
                search(index + 1, itemSlots + contribution.itemSlots)
                revertContribution(working, contribution)
            }
        }

        search(0, working.itemSlots)

        val snapshot = best ?: return
        variant.replaceWith(snapshot)
    }

    /**
     * Pays [contribution] as items again: its fluid is dropped and its item
     * stacks are appended to [variant].
     */
    private fun applyContribution(variant: Variant, contribution: FluidContribution)
    {
        removeFluid(variant.fluids, contribution.material, contribution.amount)
        if (contribution.extraMaterial != null)
            removeFluid(variant.fluids, contribution.extraMaterial, contribution.extraAmount)

        variant.items.addAll(contribution.itemStacks)
    }

    /**
     * Undoes [applyContribution], restoring the fluid and dropping the item
     * stacks it appended.
     */
    private fun revertContribution(variant: Variant, contribution: FluidContribution)
    {
        variant.fluids.merge(contribution.material, contribution.amount, Long::plus)
        if (contribution.extraMaterial != null)
            variant.fluids.merge(contribution.extraMaterial, contribution.extraAmount, Long::plus)

        val from = variant.items.size - contribution.itemStacks.size
        variant.items.subList(from, variant.items.size).clear()
    }

    private fun removeFluid(fluids: MutableMap<Material, Long>, material: Material, amount: Long)
    {
        val remaining = (fluids[material] ?: 0L) - amount
        if (remaining <= 0) fluids.remove(material) else fluids[material] = remaining
    }

    // @formatter:on
}
