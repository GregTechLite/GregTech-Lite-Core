package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.ingredients.GTRecipeInput
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.ResearchProperty
import gregtech.api.util.GTTransferUtils
import gregtech.api.util.GTUtility
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockAdvancedAssemblyLine
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.IFluidTank
import net.minecraftforge.items.IItemHandlerModifiable
import kotlin.math.max
import kotlin.math.min

class AdvancedAssemblyLineRecipeLogic(private val controller: MultiblockAdvancedAssemblyLine)
    : MultiblockRecipeLogic(controller)
{
    private val slices = Array(MAX_SLICES) { Slice(it) }

    private var currentRecipe: Recipe? = null
    private var sliceEUt: Long = 0L
    private var sliceDuration: Int = 0
    private var inputCount: Int = 0
    private var pipelineStuck: Boolean = false

    companion object
    {
        private const val MAX_SLICES = 16

        // Slice status: -1: idle, 0: stuck, >0: working
        private const val IDLE = -1
        private const val STUCK = 0
    }

    override fun updateWorkable()
    {
        if (!controller.isPipelineMode)
        {
            resetPipeline()
            super.updateWorkable()
            return
        }
        updatePipeline()
    }

    override fun invalidate()
    {
        super.invalidate()
        resetPipeline()
    }

    override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
    {
        super.modifyOverclockPost(ocResult, storage)

        // -5% / processor casing tier
        ocResult.setEut(max(1, (ocResult.eut() * (1.0 - controller.processorCasingTier * 0.05)).toLong()))

        // +200% / robot arm casing + conveyor casing tier | D' = D / (1 + 2.0 * (T - 1.0)) = D / (2.0 * T - 1.0), where k = 2.0
        ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (2.0 * min(controller.conveyorCasingTier, controller.robotArmCasingTier)) - 1.0).toInt()))
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

        if (slices[0].progress < 0)
        {
            if (currentRecipe == null || !recipeInputsPresent())
            {
                unlockRecipe()
                tryLockRecipe()
            }
            if (currentRecipe != null && slices[0].start())
                drainAllFluids()
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

        for (recipe in RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeList)
        {
            if (ConfigHolder.machines.enableResearch && recipe.hasProperty(ResearchProperty.getInstance())
                && dataHatches.none { it.isCreative || it.isRecipeAvailable(recipe) }) continue

            val itemInputs = recipe.inputs
            if (itemInputs.isEmpty() || buses.size < itemInputs.size) continue

            var itemsOk = true
            for (i in itemInputs.indices)
            {
                val stack = firstValidStack(buses[i]) ?: run { itemsOk = false; null }
                if (stack == null) break
                if (!itemInputs[i].acceptsStack(stack) || stack.count < itemInputs[i].amount)
                {
                    itemsOk = false
                    break
                }
            }
            if (!itemsOk) continue

            if (!hasAllFluids(recipe.fluidInputs, tanks)) continue

            if (recipe.eUt > voltage) continue

            val oc = (GTUtility.getOCTierByVoltage(voltage).toInt()
                    - GTUtility.getOCTierByVoltage(recipe.eUt).toInt()).coerceAtLeast(0)
            val multiplier = 1L shl (2 * oc).coerceAtMost(30)
            val eut = (recipe.eUt * multiplier).coerceAtLeast(1L)
            val ocDuration = (recipe.duration shr oc.coerceAtMost(30)).coerceAtLeast(1)
            val duration = (ocDuration / itemInputs.size).coerceAtLeast(1)

            if (itemInputs.size.toLong() * eut > energyContainer.energyCapacity) continue

            currentRecipe = recipe
            sliceEUt = eut
            sliceDuration = duration
            inputCount = itemInputs.size
            for (slice in slices)
                slice.reset()
            return true
        }

        return false
    }

    private fun unlockRecipe()
    {
        if (currentRecipe == null) return
        currentRecipe = null
        sliceEUt = 0L
        sliceDuration = 0
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
            if (!itemInputs[i].acceptsStack(stack) || stack.count < itemInputs[i].amount)
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
            val requiredAmount = input.amount

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
            var remainingAmount = input.amount

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

    private fun drainFrom(tank: IFluidTank, input: GTRecipeInput, amount: Int): Int
    {
        val fluid = tank.fluid ?: return 0
        if (!input.acceptsFluid(fluid))
            return 0
        val drained = tank.drain(minOf(amount, fluid.amount), false) ?: return 0
        return drained.amount
    }

    private fun outputProduct(): Boolean
    {
        val recipe = currentRecipe ?: return false

        val items = recipe.outputs.filter { !it.isEmpty }.map { it.copy() }
        val fluids = recipe.fluidOutputs.map { it.copy() }

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

    private fun resetPipeline()
    {
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
                if (!stack.isEmpty && input.acceptsStack(stack) && stack.count >= input.amount)
                {
                    slot = s
                    break
                }
            }
            if (slot < 0) return false

            val stack = bus.getStackInSlot(slot)
            val residual = stack.copy()
            residual.shrink(input.amount)
            bus.setStackInSlot(slot, if (residual.isEmpty) ItemStack.EMPTY else residual)

            progress = sliceDuration
            return true
        }

        override fun toString(): String = "Slice{id=$id, progress=$progress}"
    }
}