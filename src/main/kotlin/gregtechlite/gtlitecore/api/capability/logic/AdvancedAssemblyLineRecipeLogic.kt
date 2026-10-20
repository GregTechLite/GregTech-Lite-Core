package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.capability.IDataAccessHatch
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.ingredients.GTRecipeInput
import gregtech.api.recipes.logic.OCParams
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.ResearchProperty
import gregtech.api.util.GTTransferUtils
import gregtech.api.util.ItemStackHashStrategy
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockAdvancedAssemblyLine
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.IFluidTank
import net.minecraftforge.items.IItemHandlerModifiable
import kotlin.math.max
import kotlin.math.min

class AdvancedAssemblyLineRecipeLogic(private val controller: MultiblockAdvancedAssemblyLine)
    : MultiblockRecipeLogic(controller)
{
    private val slices = Array(MAX_SLICES) { Slice(it) }

    // RecipeMap#getRecipeList() rebuilds the map on every call.
    private var recipeSnapshot: Collection<Recipe>? = null

    // Last locked recipe, re-validated before any scan.
    private var lastRecipe: Recipe? = null

    // Input fingerprint of the last full scan.
    private var lastInputSignature: Int = 0
    private var lastScanTime: Long = Long.MIN_VALUE / 2

    private var currentRecipe: Recipe? = null
    private var sliceEUt: Long = 0L
    private var sliceDuration: Int = 0

    private var parallel: Int = 1
    private var inputCount: Int = 0
    private var pipelineStuck: Boolean = false
    private var pipelineActive: Boolean = false

    companion object
    {
        private const val MAX_SLICES = 16

        private val ITEM_HASH_STRATEGY = ItemStackHashStrategy.comparingAllButCount()

        // Only for changes that do not notify, e.g. a runtime recipe map reload.
        private const val FALLBACK_RESCAN_TICKS = 200L

        // Slice status: -1: idle, 0: stuck, >0: working
        private const val IDLE = -1
        private const val STUCK = 0
    }

    override fun updateWorkable()
    {
        if (!controller.isPipelineMode)
        {
            if (pipelineActive)
            {
                resetPipeline()
                pipelineActive = false
            }
            super.updateWorkable()
            return
        }
        pipelineActive = true
        updatePipeline()
    }

    override fun invalidate()
    {
        super.invalidate()
        resetPipeline()
        // Recipe maps can be edited at runtime, so a rebuild is also a snapshot refresh.
        recipeSnapshot = null
        lastRecipe = null
    }

    override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
    {
        super.modifyOverclockPost(ocResult, storage)

        // -5% / processor casing tier
        ocResult.setEut(max(1, (ocResult.eut() * (1.0 - controller.processorCasingTier * 0.05)).toLong()))

        // +200% / robot arm casing + conveyor casing tier | D' = D / (1 + 2.0 * (T - 1.0)) = D / (2.0 * T - 1.0), where k = 2.0
        ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (2.0 * min(controller.conveyorCasingTier, controller.robotArmCasingTier)) - 1.0).toInt()))
    }

    private fun maxParallelByOutputs(recipe: Recipe): Int
    {
        if (controller.canVoidRecipeItemOutputs())
            return Int.MAX_VALUE

        val inventory = controller.outputInventory
        var capacity = Int.MAX_VALUE
        for (output in recipe.outputs)
        {
            if (output.isEmpty) continue
            var space = 0L
            for (slot in 0 until inventory.slots)
            {
                val current = inventory.getStackInSlot(slot)
                space += when
                {
                    current.isEmpty -> inventory.getSlotLimit(slot).toLong()
                    ITEM_HASH_STRATEGY.equals(current, output) ->
                        (inventory.getSlotLimit(slot) - current.count).coerceAtLeast(0).toLong()
                    else -> 0L
                }
            }
            val perRun = output.count.coerceAtLeast(1).toLong()
            capacity = minOf(capacity, (space / perRun).coerceAtMost(Int.MAX_VALUE.toLong()).toInt())
            if (capacity <= 0) return 0
        }
        return capacity
    }

    private fun updatePipeline()
    {
        if (!workingEnabled)
        {
            resetPipeline()
            controller.stuck = false
            controller.activeSlices = 0
            return
        }

        val working = slices.count { it.progress > 0 }
        val requiredEUt = working.toLong() * sliceEUt
        if (requiredEUt > 0L && energyContainer.energyStored < requiredEUt)
        {
            hasNotEnoughEnergy = true
            controller.stuck = pipelineStuck
            controller.activeSlices = working
            setActive(true)
            syncProgress()
            return
        }
        hasNotEnoughEnergy = false
        if (requiredEUt > 0L)
            energyContainer.changeEnergy(-requiredEUt)

        pipelineStuck = false

        for (i in slices.indices.reversed())
            slices[i].tick()

        if (slices.any { it.progress >= 0 })
        {
            if (slices[0].progress < 0)
                startCycle()
        }
        else
        {
            if (currentRecipe == null || !recipeInputsPresent())
            {
                unlockRecipe()
                // An empty machine can never match.
                if (hasAnyInputStack())
                {
                    // Research data changes what is craftable without changing the fingerprint.
                    if (consumeInputNotifications()) invalidateScanMemo()
                    tryLockRecipe()
                }
            }
            startCycle()
        }

        val workingSlices = slices.count { it.progress > 0 }
        val hasActiveSlice = slices.any { it.progress >= 0 }

        setActive(hasActiveSlice)
        controller.stuck = pipelineStuck
        controller.activeSlices = workingSlices
        syncProgress()
    }

    private fun tryLockRecipe(): Boolean
    {
        val buses = controller.getAbilities(MultiblockAbility.IMPORT_ITEMS)
        val tanks = controller.getAbilities(MultiblockAbility.IMPORT_FLUIDS)
        val dataHatches = controller.getAbilities(MultiblockAbility.DATA_ACCESS_HATCH)
        val voltage = maxVoltage
        val checkResearch = ConfigHolder.machines.enableResearch

        // Try the last locked recipe first.
        val previous = lastRecipe
        if (previous != null
                && previous.eUt <= voltage
                && researchAvailable(previous, dataHatches, checkResearch)
                && fitsOrderedBuses(previous, buses, tanks))
        {
            applyRecipe(previous)
            return true
        }

        // Skip unchanged input states; FALLBACK_RESCAN_TICKS still forces a scan.
        val signature = inputSignature(buses, tanks, dataHatches, voltage)
        val now = controller.offsetTimer
        if (now - lastScanTime >= FALLBACK_RESCAN_TICKS)
        {
            // Recipe maps can be edited at runtime; neither the snapshot nor the recipe locked
            // last time may outlive the fallback, or a removed recipe would still be started.
            recipeSnapshot = null
            lastRecipe = null
        }
        else if (signature == lastInputSignature)
        {
            return false
        }
        lastInputSignature = signature
        lastScanTime = now

        for (recipe in assemblyLineRecipes())
        {
            if (recipe.inputs.isEmpty() || buses.size < recipe.inputs.size) continue
            if (!researchAvailable(recipe, dataHatches, checkResearch)) continue
            if (recipe.eUt > voltage) continue
            if (!fitsOrderedBuses(recipe, buses, tanks)) continue
            applyRecipe(recipe)
            return true
        }

        return false
    }

    private fun researchAvailable(recipe: Recipe,
                                  dataHatches: List<IDataAccessHatch>,
                                  checkResearch: Boolean): Boolean
    {
        if (!checkResearch || !recipe.hasProperty(ResearchProperty.getInstance()))
            return true
        for (hatch in dataHatches)
            if (hatch.isCreative || hatch.isRecipeAvailable(recipe))
                return true
        return false
    }

    private fun fitsOrderedBuses(recipe: Recipe,
                                 buses: List<IItemHandlerModifiable>,
                                 tanks: List<IFluidTank>): Boolean
    {
        val itemInputs = recipe.inputs
        if (itemInputs.isEmpty() || buses.size < itemInputs.size)
            return false

        for (i in itemInputs.indices)
        {
            val stack = firstValidStack(buses[i]) ?: return false
            if (!itemInputs[i].acceptsStack(stack) || stack.count < itemInputs[i].amount * parallel)
                return false
        }
        return hasAllFluids(recipe.fluidInputs, tanks)
    }

    private fun applyRecipe(recipe: Recipe)
    {
        val itemInputs = recipe.inputs
        val ocAmount = getNumberOfOCs(recipe.eUt)
        val ocParams = OCParams()
        val ocResult = OCResult()
        val baseDuration = (recipe.duration / itemInputs.size).coerceAtLeast(1)
        if (ocAmount <= 0)
        {
            ocResult.init(recipe.eUt, baseDuration)
        }
        else
        {
            val inputPower = energyContainer.inputVoltage * energyContainer.inputAmperage
            val perSliceBudget = (inputPower / itemInputs.size).coerceAtLeast(recipe.eUt)
            ocParams.initialize(recipe.eUt, baseDuration, ocAmount)
            modifyOverclockPre(ocParams, recipe.propertyStorage())
            OverclockingLogic.subTickParallelOC(ocParams, ocResult, perSliceBudget,
                OverclockingLogic.STD_DURATION_FACTOR, OverclockingLogic.STD_VOLTAGE_FACTOR)
        }
        modifyOverclockPost(ocResult, recipe.propertyStorage())

        currentRecipe = recipe
        lastRecipe = recipe
        parallel = minOf(ocResult.parallel().coerceAtLeast(1), maxParallelByOutputs(recipe)).coerceAtLeast(1)
        sliceEUt = ocResult.parallelEUt().coerceAtLeast(1L)
        sliceDuration = ocResult.duration().coerceAtLeast(1)
        inputCount = itemInputs.size
        for (slice in slices)
            slice.reset()
    }

    /** Cheap fingerprint of the input state a lock depends on. */
    private fun inputSignature(buses: List<IItemHandlerModifiable>,
                               tanks: List<IFluidTank>,
                               dataHatches: List<IDataAccessHatch>,
                               voltage: Long): Int
    {
        var hash = 1
        for (bus in buses)
        {
            var signature = 0
            for (slot in 0 until bus.slots)
            {
                val stack = bus.getStackInSlot(slot)
                if (!stack.isEmpty)
                {
                    signature = stackSignature(stack)
                    break
                }
            }
            hash = 31 * hash + signature
        }
        for (tank in tanks)
        {
            val fluid = tank.fluid
            hash = 31 * hash + if (fluid == null) 0 else 31 * fluid.hashCode() + fluid.amount
        }
        hash = 31 * hash + dataHatches.size
        hash = 31 * hash + (voltage xor (voltage ushr 32)).toInt()
        return hash
    }

    private fun stackSignature(stack: ItemStack): Int
        = 31 * ITEM_HASH_STRATEGY.hashCode(stack) + stack.count

    private fun assemblyLineRecipes(): Collection<Recipe> =
        recipeSnapshot ?: RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeList.also { recipeSnapshot = it }

    /** True when GT reported an input change since the last call. */
    private fun consumeInputNotifications(): Boolean
    {
        val items = controller.notifiedItemInputList
        val fluids = controller.notifiedFluidInputList
        if (items.isEmpty() && fluids.isEmpty()) return false
        items.clear()
        fluids.clear()
        return true
    }

    private fun hasAnyInputStack(): Boolean
    {
        for (bus in controller.getAbilities(MultiblockAbility.IMPORT_ITEMS))
            for (slot in 0 until bus.slots)
                if (!bus.getStackInSlot(slot).isEmpty) return true
        return false
    }

    private fun unlockRecipe()
    {
        if (currentRecipe == null) return
        // Job switched; never let a stale fingerprint skip the rescan.
        invalidateScanMemo()
        currentRecipe = null
        sliceEUt = 0L
        sliceDuration = 0
        parallel = 1
        inputCount = 0
        for (slice in slices)
            slice.reset()
    }

    private fun recipeInputsPresent(): Boolean
    {
        val recipe = currentRecipe ?: return false
        val buses = controller.getAbilities(MultiblockAbility.IMPORT_ITEMS)
        val itemInputs = recipe.inputs
        if (buses.size < itemInputs.size)
            return false

        for (i in itemInputs.indices)
        {
            val stack = firstValidStack(buses[i]) ?: return false
            if (!itemInputs[i].acceptsStack(stack) || stack.count < itemInputs[i].amount * parallel)
                return false
        }
        return hasAllFluids(recipe.fluidInputs, controller.getAbilities(MultiblockAbility.IMPORT_FLUIDS))
    }

    private fun firstValidStack(bus: IItemHandlerModifiable): ItemStack?
    {
        for (slot in 0 until bus.slots)
        {
            val stack = bus.getStackInSlot(slot)
            if (!stack.isEmpty)
                return stack
        }
        return null
    }

    private fun hasAllFluids(fluidInputs: List<GTRecipeInput>, tanks: List<IFluidTank>): Boolean
    {
        for (i in fluidInputs.indices)
        {
            val input = fluidInputs[i]
            val requiredAmount = input.amount * parallel

            val preferredFluid = tanks.getOrNull(i)
            if (preferredFluid != null && isFluidSufficient(preferredFluid, input, requiredAmount)) continue

            var found = false
            for (tank in tanks)
            {
                if (isFluidSufficient(tank, input, requiredAmount))
                {
                    found = true
                    break
                }
            }
            if (!found)
                return false
        }
        return true
    }

    private fun isFluidSufficient(tank: IFluidTank, input: GTRecipeInput, needed: Int): Boolean
    {
        val fluid = tank.fluid ?: return false
        return input.acceptsFluid(fluid) && fluid.amount >= needed
    }

    private fun drainAllFluids()
    {
        val recipe = currentRecipe ?: return
        val tanks = controller.getAbilities(MultiblockAbility.IMPORT_FLUIDS)
        val fluidInputs = recipe.fluidInputs

        for (i in fluidInputs.indices)
        {
            val input = fluidInputs[i]
            var remainingAmount = input.amount * parallel

            val preferredFluid = tanks.getOrNull(i)
            if (preferredFluid != null && remainingAmount > 0)
                remainingAmount -= drainFrom(preferredFluid, input, remainingAmount)

            for (tank in tanks)
            {
                if (remainingAmount <= 0) break
                if (tank === preferredFluid) continue
                remainingAmount -= drainFrom(tank, input, remainingAmount)
            }
        }
    }

    private fun startCycle(): Boolean
    {
        val recipe = currentRecipe ?: return false

        if (!hasAllFluids(recipe.fluidInputs, controller.getAbilities(MultiblockAbility.IMPORT_FLUIDS)))
            return false

        if (!slices[0].start())
            return false

        drainAllFluids()
        return true
    }

    private fun drainFrom(tank: IFluidTank, input: GTRecipeInput, amount: Int): Int
    {
        val fluid = tank.fluid ?: return 0
        if (!input.acceptsFluid(fluid))
            return 0
        val drained = tank.drain(minOf(amount, fluid.amount), true) ?: return 0
        return drained.amount
    }

    private fun outputProduct(): Boolean
    {
        val recipe = currentRecipe ?: return false

        val count = parallel.coerceAtLeast(1)
        val items = ArrayList<ItemStack>()
        for (output in recipe.outputs)
        {
            if (output.isEmpty) continue
            var remaining = output.count.coerceAtLeast(1).toLong() * count
            while (remaining > 0)
            {
                val size = minOf(remaining, output.maxStackSize.toLong()).toInt()
                items.add(output.copy().also { it.count = size })
                remaining -= size
            }
        }
        val fluids = recipe.fluidOutputs.map { output: FluidStack ->
            val amount = (output.amount.toLong() * count).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
            FluidStack(output.fluid, amount, output.tag)
        }

        val outputInventory = controller.outputInventory
        val outputTanks = controller.outputFluidInventory

        if (!GTTransferUtils.addItemsToItemHandler(outputInventory, true, items)
            && !controller.canVoidRecipeItemOutputs())
        {
            return false
        }

        GTTransferUtils.addItemsToItemHandler(outputInventory, false, items)
        GTTransferUtils.addFluidsToFluidHandler(outputTanks, false, fluids)
        return true
    }

    private fun syncProgress()
    {
        val recipe = currentRecipe
        if (recipe == null)
        {
            progressTime = 0
            maxProgressTime = 0
            recipeEUt = 0L
            return
        }
        maxProgressTime = sliceDuration * inputCount
        recipeEUt = sliceEUt
        val first = slices.firstOrNull { it.progress >= 0 }
        progressTime = if (first == null) 0 else (first.id + 1) * sliceDuration - first.progress
    }

    private fun invalidateScanMemo()
    {
        lastScanTime = Long.MIN_VALUE / 2
    }

    private fun resetPipeline()
    {
        // Force a rescan once the pipeline is dropped.
        invalidateScanMemo()
        currentRecipe = null
        sliceEUt = 0L
        sliceDuration = 0
        inputCount = 0
        pipelineStuck = false
        for (slice in slices)
            slice.reset()
        controller.stuck = false
        controller.activeSlices = 0
        progressTime = 0
        maxProgressTime = 0
        recipeEUt = 0L
        setActive(false)
        // The memo consumed GT's notified inputs; hand one back so GT's own search can resume.
        controller.getAbilities(MultiblockAbility.IMPORT_ITEMS).firstOrNull()
            ?.let(controller::addNotifiedInput)
    }

    private inner class Slice(val id: Int)
    {
        var progress = IDLE

        fun reset()
        {
            progress = IDLE
        }

        fun tick()
        {
            if (progress < STUCK) return
            if (progress > STUCK)
                progress--

            if (progress == STUCK)
            {
                if (id + 1 >= inputCount)
                {
                    if (outputProduct())
                        reset()
                    else
                        pipelineStuck = true
                }
                else
                {
                    if (slices[id + 1].start())
                        reset()
                    else
                        pipelineStuck = true
                }
            }
        }

        fun start(): Boolean
        {
            if (progress >= 0)
                return false

            val recipe = currentRecipe ?: return false
            if (id >= inputCount) return false

            val bus = controller.getAbilities(MultiblockAbility.IMPORT_ITEMS).getOrNull(id) ?: return false
            val input = recipe.inputs[id]

            var slot = -1
            for (s in 0 until bus.slots)
            {
                val stack = bus.getStackInSlot(s)
                if (!stack.isEmpty && input.acceptsStack(stack) && stack.count >= input.amount * parallel)
                {
                    slot = s
                    break
                }
            }
            if (slot < 0) return false

            val stack = bus.getStackInSlot(slot)
            val residual = stack.copy()
            residual.shrink(input.amount * parallel)
            bus.setStackInSlot(slot, if (residual.isEmpty) ItemStack.EMPTY else residual)

            progress = sliceDuration
            return true
        }

        override fun toString(): String = "Slice{id=$id, progress=$progress}"
    }
}