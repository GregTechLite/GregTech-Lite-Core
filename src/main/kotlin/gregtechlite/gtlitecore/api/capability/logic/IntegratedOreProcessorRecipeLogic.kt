package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.RNG
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.util.GTUtility
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.INTEGRATED_ORE_PROCESSOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.util.OreProcessorRecipeWrapper
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockIntegratedOreProcessor
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

open class IntegratedOreProcessorRecipeLogic(val metaTileEntity: MultiblockIntegratedOreProcessor)
    : MultiblockRecipeLogic(metaTileEntity, true)
{

    override fun update()
    {
        val world: World? = metaTileEntity.world
        if (world != null && !world.isRemote)
        {
            if (isWorkingEnabled)
            {
                if (progressTime > 0)
                {
                    setActive(true)
                    updateRecipeProgress()
                }
                if (progressTime == 0 && shouldProcessRecipe())
                {
                    recipeProcessing()
                }
            }
            if (wasActiveAndNeedsUpdate)
            {
                this.wasActiveAndNeedsUpdate = false
                setActive(false)
            }
        }
    }

    private fun recipeProcessing()
    {
        var processedCount = 0
        var remainingVoltage = maxVoltage
        val inputFluid = GTUtility.fluidHandlerToList(metaTileEntity.inputFluidInventory)

        var mode = -1
        val inputInventory = metaTileEntity.inputInventory
        if (inputInventory == null)
            return

        for (i in 0 until inputInventory.slots)
        {
            val stack = inputInventory.getStackInSlot(i)
            if (stack.isEmpty)
                continue

            if (IntCircuitIngredient.isIntegratedCircuit(stack))
            {
                mode = IntCircuitIngredient.getCircuitConfiguration(stack)
                break
            }
        }

        if (mode <= 0)
            return

        val inputStacks = mutableListOf<ItemStack>()
        for (i in 0 until inputInventory.slots)
        {
            val stack = inputInventory.getStackInSlot(i)
            if (!stack.isEmpty)
                inputStacks.add(stack.copy())
        }

        val compressedInputStacks: List<ItemStack> = OreProcessorRecipeWrapper.compressOutput(inputStacks)
        inputStacks.clear()
        inputStacks.addAll(compressedInputStacks)

        val outputStacks = mutableListOf<ItemStack>()
        val consumedItemStacks = mutableListOf<ItemStack>()
        val consumedFluidStacks = mutableListOf<FluidStack>()

        for (inputStack in inputStacks)
        {
            if (processedCount > parallelLimit)
                break

            val recipe = INTEGRATED_ORE_PROCESSOR_RECIPES.findRecipe(V[LV],
                listOf<ItemStack>(inputStack, IntCircuitIngredient.getIntegratedCircuit(mode)),
                inputFluid.toList())
            if (recipe != null)
            {
                val countToProcess = inputStack.count.toLong()
                    .coerceAtMost(parallelLimit - processedCount + 0L)
                    .coerceAtMost(remainingVoltage / recipe.eUt).toInt()

                val fluidInputStacks = mutableListOf<FluidStack>()
                for (fluidInput in recipe.fluidInputs)
                    fluidInputStacks.add(fluidInput.inputFluidStack)

                countToProcess.coerceAtMost(getMaxFluidDeductionMultiplier(fluidInputStacks))

                if (countToProcess == 0)
                    return

                remainingVoltage -= countToProcess * recipe.eUt
                processedCount += countToProcess

                val consumedItemStack = inputStack.copy()
                consumedItemStack.count = countToProcess

                consumedItemStacks.add(inputStack)
                for (inputFluidStack in fluidInputStacks)
                {
                    val consumedFluidStack = inputFluidStack.copy()
                    consumedFluidStack.amount *= countToProcess
                    consumedFluidStacks.add(consumedFluidStack)
                }

                for (recipeOutputStack in recipe.outputs)
                {
                    val outputStack = recipeOutputStack.copy()
                    outputStack.count *= countToProcess
                    outputStacks.add(outputStack)
                }

                val random = RNG
                for (recipeChancedOutput in recipe.chancedOutputs.chancedEntries)
                {
                    val amount = (recipeChancedOutput.chance * countToProcess).coerceAtLeast(10000)
                    val stack = recipeChancedOutput.ingredient.copy(
                        (amount / 10000) + if (random.nextInt(10000) < amount % 10000) 1 else 0)
                    outputStacks.add(stack)
                }
            }
        }
        if (outputStacks.isEmpty())
            return

        for (itemStack in consumedItemStacks)
        {
            var remainingToExtract = itemStack.count
            for (i in 0 until inputInventory.slots)
            {
                val inputStack = inputInventory.getStackInSlot(i)
                if (inputStack.isEmpty || !inputStack.isItemEqual(itemStack))
                    continue

                val countToExtract = min(remainingToExtract, inputStack.count)
                if (countToExtract > 0)
                {
                    inputInventory.extractItem(i, countToExtract, false)
                    remainingToExtract -= countToExtract
                    if (remainingToExtract == 0)
                        break
                }
            }
        }

        for (fluidStack in consumedFluidStacks)
        {
            metaTileEntity.inputFluidInventory?.drain(fluidStack, true)
        }

        val ocMult = floor(1.0 * maxVoltage / (maxVoltage - remainingVoltage)).pow(0.5).toInt().coerceAtLeast(1)

        progressTime = 1
        maxProgress = (100 / ocMult).coerceAtLeast(1)
        recipeEUt = (maxVoltage - remainingVoltage) * (10000 / maxProgress / maxProgress)

        val compressedOutputStacks: List<ItemStack> = OreProcessorRecipeWrapper.compressOutput(outputStacks)
        itemOutputs = NonNullList.create()
        for (itemStack in compressedOutputStacks)
            itemOutputs.add(itemStack)
    }

    /**
     * The parallel limit of IOP is dependent with its [maxVoltage], the following table is
     * the result for all voltage tier in GTCEu:
     *
     * | Voltage | Parallel Limit | Voltage | Parallel Limit | Voltage | Parallel Limit |
     * |---------|----------------|---------|----------------|---------|----------------|
     * | ULV-IV  | 1024           | MAX+1   | 59049          | MAX+11  | 3405063        |
     * | LuV     | 1536           | MAX+2   | 88574          | MAX+12  | 5107595        |
     * | ZPM     | 2304           | MAX+3   | 132861         | MAX+13  | 7661392        |
     * | UV      | 3456           | MAX+4   | 199291         | MAX+14  | 11492088       |
     * | UHV     | 5184           | MAX+5   | 298936         | MAX+15  | 17238132       |
     * | UEV     | 7776           | MAX+6   | 448404         | MAX+16  | 25857198       |
     * | UIV     | 11664          | MAX+7   | 672606         |
     * | UXV     | 17496          | MAX+8   | 1008908        |
     * | OpV     | 26244          | MAX+9   | 1513362        |
     * | MAX     | 39366          | MAX+10  | 2270042        |
     */
    override fun getParallelLimit() : Int
    {
        return if (maxVoltage <= V[IV]) 1024 else
            ceil(1024 * (1.0 * maxVoltage / V[IV]).pow(0.292481)).toInt()
    }

    private fun getMaxFluidDeductionMultiplier(inputFluids: List<FluidStack>) : Int
    {
        var maxMultiplier = parallelLimit.toLong()
        for (fluidStack in inputFluids)
        {
            var remainingCount : Long = 0
            for (existingFluidStack in GTUtility.fluidHandlerToList(metaTileEntity.inputFluidInventory))
            {
                if (existingFluidStack != null && existingFluidStack.isFluidEqual(fluidStack))
                {
                    remainingCount += existingFluidStack.amount
                }
            }
            maxMultiplier = maxMultiplier.coerceAtMost(remainingCount / fluidStack.amount)
        }
        return maxMultiplier.toInt()
    }

    private fun shouldProcessRecipe() : Boolean = true

    override fun getProgressPercent(): Double = if (maxProgress == 0) 0.0 else progress / (maxProgress * 1.0)

}
