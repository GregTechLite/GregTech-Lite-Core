package gregtechlite.gtlitecore.common.metatileentity.multiblock.generator

import com.cleanroommc.modularui.value.sync.IntSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.StringSyncValue
import gregtech.api.GTValues.V
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.IRotorHolder
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic
import gregtech.api.metatileentity.ITieredMetaTileEntity
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.ROTOR_HOLDER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.ProgressBarMultiblock
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.sync.FixedIntArraySyncValue
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.GTUtility
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.capability.RotorHandler
import gregtechlite.gtlitecore.api.capability.RotorMode
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import java.util.function.UnaryOperator
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.pow

class MultiblockMegaTurbine(id: ResourceLocation,
                            recipeMap: RecipeMap<*>,
                            val _tier: Int,
                            val casingState: IBlockState,
                            val gearboxState: IBlockState,
                            val casingRenderer: ICubeRenderer,
                            val frontRenderer: ICubeRenderer,
                            val hasMufflerHatch: Boolean)
    : FuelMultiblockController(id, recipeMap, _tier), ITieredMetaTileEntity, RotorHandler, ProgressBarMultiblock
{
    override val mode: RotorMode = RotorMode.COMMON

    override val rotorHolders: List<IRotorHolder>?
        get() = getAbilities(ROTOR_HOLDER).takeIf { it.isNotEmpty() }

    internal var exportFluidHandler: IFluidHandler? = null

    init
    {
        recipeMapWorkable = MegaTurbineWorkableHandler(this, tier)
        recipeMapWorkable.maximumOverclockVoltage = V[tier]
    }

    companion object
    {
        private const val MIN_DURABILITY_TO_WARN = 10
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockMegaTurbine(metaTileEntityId, recipeMap, tier, casingState, gearboxState, casingRenderer, frontRenderer, hasMufflerHatch)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        exportFluidHandler = FluidTankList(true, getAbilities(EXPORT_FLUIDS))
        (recipeMapWorkable as MegaTurbineWorkableHandler).updateTanks()
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        exportFluidHandler = null
    }

    override fun initializeAbilities()
    {
        inputInventory = ItemHandlerList(getAbilities(IMPORT_ITEMS))
        inputFluidInventory = FluidTankList(allowSameFluidFillForOutputs(), getAbilities(IMPORT_FLUIDS))
        outputInventory = ItemHandlerList(getAbilities(EXPORT_ITEMS))
        outputFluidInventory = FluidTankList(allowSameFluidFillForOutputs(), getAbilities(EXPORT_FLUIDS))
        val outputEnergy = ArrayList(getAbilities(OUTPUT_ENERGY))
        outputEnergy.addAll(getAbilities(SUBSTATION_OUTPUT_ENERGY))
        outputEnergy.addAll(getAbilities(OUTPUT_LASER))
        energyContainer = EnergyContainerList(outputEnergy)
    }

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
        = super.checkRecipe(recipe, consumeIfSuccess) && checkRotors()

    override fun getMaxVoltage(): Long
    {
        val maxProduction = recipeMapWorkable.maxVoltage
        val currentProduction = (recipeMapWorkable as MegaTurbineWorkableHandler).boostProduction(maxProduction)
        return if (isActive && currentProduction < maxProduction) recipeMapWorkable.maxVoltage else 0
    }

    override fun getTier(): Int = _tier

    private fun checkRotors(): Boolean
    {
        val holders = rotorHolders ?: return false
        for (holder in holders)
            if (!holder.hasRotor()) return false
        return true
    }

    private fun isRotorFaceFree(): Boolean
    {
        if (!isStructureFormed || rotorHolders == null) return false
        for (rotorHolder in rotorHolders!!)
            if (!rotorHolder.isFrontFaceFree) return false
        return true
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCCCC", "CRCACRC", "CCCACCC", "CCCACCC", "CRCMCRC", "CCCACCC", "CCCACCC", "CRCACRC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CRCACRC", "CCCACCC", "CCCACCC", "CRCSCRC", "CCCACCC", "CCCACCC", "CRCACRC", "CCCCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState))
        .where('G', states(gearboxState))
        .where('R', abilities(ROTOR_HOLDER))
        .where('M', states(casingState)
            .or(abilities(MUFFLER_HATCH))
            .setPreviewCount(1))
        .where('A', states(casingState)
            .or(abilities(OUTPUT_ENERGY)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(1))
            .or(abilities(OUTPUT_LASER)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(0))
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(IMPORT_ITEMS)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(1))
            .or(abilities(IMPORT_FLUIDS)
                    .setMinGlobalLimited(1)
                    .setPreviewCount(1))
            .or(abilities(EXPORT_FLUIDS)
                    .setMinGlobalLimited(1)
                    .setPreviewCount(1)))
        .build()

    // @formatter:on

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        val recipeLogic = recipeMapWorkable as MultiblockFuelRecipeLogic
        builder.setWorkingStatus(recipeLogic.isWorkingEnabled, recipeLogic.isActive)
            .addEnergyProductionLine(maxVoltage, recipeLogic.recipeEUt)
            .addFuelNeededLine(recipeLogic.recipeFluidInputInfo, recipeLogic.previousRecipeDuration)
            .addWorkingStatusLine()
    }

    override fun configureWarningText(builder: MultiblockUIBuilder)
    {
        super.configureWarningText(builder)
        builder.addCustom { keyList, syncer ->
            if (!isStructureFormed) return@addCustom
            val holder = rotorHolders?.firstOrNull() ?: return@addCustom
            val efficiency = syncer.syncInt(holder::getRotorEfficiency)
            val durability = syncer.syncInt(holder::getRotorDurabilityPercent)
            if (efficiency > 0 && durability <= MIN_DURABILITY_TO_WARN)
                keyList.add(KeyUtil.lang(TextFormatting.YELLOW, "gregtech.multiblock.turbine.rotor_durability_low"))
        }
    }

    override fun configureErrorText(builder: MultiblockUIBuilder)
    {
        super.configureErrorText(builder)
        builder.addCustom { keyList, syncer ->
            if (isStructureFormed && syncer.syncBoolean(!isRotorFaceFree()))
                keyList.add(KeyUtil.lang(TextFormatting.RED, "gregtech.multiblock.turbine.obstructed"))
        }
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = casingRenderer

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = frontRenderer

    override fun hasMufflerMechanics(): Boolean = hasMufflerHatch

    override fun isStructureObstructed(): Boolean = super.isStructureObstructed() || !isRotorFaceFree()

    override fun canVoidRecipeItemOutputs(): Boolean = true

    override fun canVoidRecipeFluidOutputs(): Boolean = true

    override fun shouldShowVoidingModeButton(): Boolean = false

    override fun getProgressBarCount(): Int = 3

    @Suppress("UnstableApiUsage")
    override fun registerBars(templateBars: MutableList<UnaryOperator<TemplateBarBuilder>>, guiSyncManager: PanelSyncManager)
    {
        val fuelSync = FixedIntArraySyncValue(::getFuelAmount, null)
        guiSyncManager.syncValue("fuel_amount", fuelSync)

        val fuelNameSync = StringSyncValue {
            val stack = (recipeMapWorkable as MultiblockFuelRecipeLogic).inputFluidStack
            if (stack == null) return@StringSyncValue null
            val fluid = stack.fluid
            if (fluid == null) return@StringSyncValue null
            return@StringSyncValue fluid.name
        }
        guiSyncManager.syncValue("fuel_name", fuelNameSync)

        val speedSync = IntSyncValue { rotorHolders?.firstOrNull()?.rotorSpeed ?: 0 }
        val maxSpeedSync = IntSyncValue { rotorHolders?.firstOrNull()?.maxRotorHolderSpeed ?: 0 }
        guiSyncManager.syncValue("rotor_speed", speedSync)
        guiSyncManager.syncValue("rotor_max_speed", maxSpeedSync)

        val durabilitySync = IntSyncValue { rotorHolders?.firstOrNull()?.rotorDurabilityPercent ?: 0 }
        val efficiencySync = IntSyncValue { rotorHolders?.firstOrNull()?.rotorEfficiency ?: 0 }
        guiSyncManager.syncValue("rotor_durability", durabilitySync)
        guiSyncManager.syncValue("rotor_efficiency", efficiencySync)

        templateBars.add {
            it.progress {
                if (fuelSync.getValue(1) == 0) return@progress 0.0
                else return@progress 1.0 * fuelSync.getValue(0) / fuelSync.getValue(1)
            }
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_FUEL)
                .tooltipBuilder { tooltip -> createFuelTooltip(tooltip, fuelSync, fuelNameSync) }
        }

        templateBars.add {
            it.progress {
                if (maxSpeedSync.intValue == 0) return@progress 0.0
                else return@progress 1.0 * speedSync.intValue / maxSpeedSync.intValue
            }
                .texture(GTGuiTextures.PROGRESS_BAR_TURBINE_ROTOR_SPEED)
                .tooltipBuilder { tooltip ->
                    if (isStructureFormed)
                        tooltip.addLine(KeyUtil.lang("gregtech.multiblock.turbine.rotor_speed", speedSync.intValue, maxSpeedSync.intValue))
                    else
                        tooltip.addLine(KeyUtil.lang("gregtech.multiblock.invalid_structure"))
                }
        }

        templateBars.add {
            it.progress { durabilitySync.intValue / 100.0 }
                .texture(GTGuiTextures.PROGRESS_BAR_TURBINE_ROTOR_DURABILITY)
                .tooltipBuilder { tooltip ->
                    if (efficiencySync.intValue <= 0)
                        tooltip.addLine(KeyUtil.lang("gregtech.multiblock.turbine.no_rotor"))
                    else
                        tooltip.addLine(KeyUtil.lang("gregtech.multiblock.turbine.rotor_durability", durabilitySync.intValue))
                }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gregtech.universal.tooltip.base_production_eut", V[tier] * 2 * 16))
    }

    private fun getFuelAmount(): IntArray
    {
        val recipeLogic = recipeMapWorkable as MultiblockFuelRecipeLogic
        val testStack = recipeLogic.inputFluidStack?.copy() ?: return IntArray(2)
        testStack.amount = Int.MAX_VALUE
        return getTotalFluidAmount(testStack, getInputFluidInventory())
    }

    private inner class MegaTurbineWorkableHandler(private val mte: RecipeMapMultiblockController, tier: Int) : MultiblockFuelRecipeLogic(mte)
    {
        private val outputEnergy = V[tier] * 2 * 16
        private var excessVoltage: Long = 0

        override fun updateRecipeProgress()
        {
            if (canRecipeProgress)
            {
                drawEnergy(recipeEUt, false)
                if (++progressTime > maxProgressTime) completeRecipe()
            }
        }

        override fun getInputFluidStack(): FluidStack?
        {
            if (previousRecipe == null)
            {
                val recipe = findRecipe(Int.MAX_VALUE.toLong(), inputInventory, inputTank)
                return if (recipe == null) null else inputTank.drain(FluidStack(recipe.fluidInputs[0].inputFluidStack.fluid, Int.MAX_VALUE), false)
            }
            val fuelStack = previousRecipe.fluidInputs[0].inputFluidStack
            return inputTank.drain(FluidStack(fuelStack.fluid, Int.MAX_VALUE), false)
        }

        override fun getMaxVoltage(): Long
        {
            val rotorHolders = (mte as RotorHandler).rotorHolders
            if (rotorHolders != null && rotorHolders[0].hasRotor())
            {
                if (mte.mode == RotorMode.COMMON)
                    return outputEnergy * rotorHolders[0].totalPower / 100
                return (outputEnergy * 3) * rotorHolders[0].totalPower / 100
            }
            return 0
        }

        public override fun boostProduction(production: Long): Long
        {
            val rotorHolders = (mte as RotorHandler).rotorHolders
            if (rotorHolders != null && rotorHolders[0].hasRotor())
            {
                val maxSpeed = rotorHolders[0].maxRotorHolderSpeed
                val currentSpeed = rotorHolders[0].rotorSpeed
                if (currentSpeed >= maxSpeed) return production
                return (production * (1.0 * currentSpeed / maxSpeed).pow(2)).toLong()
            }
            return 0
        }

        override fun checkPreviousRecipe(): Boolean
        {
            val recipe = previousRecipe ?: return false
            return super.checkPreviousRecipe() && canDoParallelRecipe(recipe)
        }

        override fun findRecipe(maxVoltage: Long, inputs: IItemHandlerModifiable?, fluidInputs: IMultipleTankHandler?): Recipe?
        {
            val map = recipeMap ?: return null
            if (!isRecipeMapValid(map)) return null

            val items = GTUtility.itemHandlerToList(inputs).filterNotNull().toList()
            val fluids = GTUtility.fluidHandlerToList(fluidInputs).filter { it != null && it.amount != 0 }.toList()
            return map.find(items, fluids) {
                if (it.eUt > maxVoltage) return@find false
                it.matches(false, inputs, fluidInputs) && canDoParallelRecipe(it)
            }
        }

        override fun prepareRecipe(recipe: Recipe): Boolean
        {
            var recipe = recipe
            val rotorHolders = (mte as RotorHandler).rotorHolders
            if (rotorHolders == null || !rotorHolders[0].hasRotor()) return false

            val turbineMaxVoltage = getMaxVoltage()
            val recipeFluidStack = recipe.fluidInputs[0].inputFluidStack
            var parallel = 0

            if (excessVoltage >= turbineMaxVoltage)
            {
                excessVoltage -= turbineMaxVoltage
            }
            else
            {
                val efficiency = rotorHolders[0].totalEfficiency / 100.0
                parallel = ceil((turbineMaxVoltage - excessVoltage) / (abs(recipe.eUt) * efficiency)).toInt()

                val inputFluid = getInputFluidStack()
                if (inputFluid == null || inputFluid.amount < recipeFluidStack.amount * parallel)
                    return false

                excessVoltage += (parallel * abs(recipe.eUt) * efficiency - turbineMaxVoltage).toInt()
            }

            val recipeBuilder = recipeMap!!.recipeBuilder()
            recipeBuilder.append(recipe, parallel, false)
                .EUt(turbineMaxVoltage)
            applyParallelBonus(recipeBuilder)
            recipe = recipeBuilder.build().result

            recipe = setupAndConsumeRecipeInputs(recipe, inputInventory) ?: return false
            setupRecipe(recipe)
            return true
        }

        override fun invalidate()
        {
            excessVoltage = 0
            super.invalidate()
        }

        fun updateTanks()
        {
            val controller = mte as FuelMultiblockController
            val tanks = controller.notifiedFluidInputList
            for (tank in controller.getAbilities(IMPORT_FLUIDS))
            {
                tanks.add(tank as IFluidHandler)
            }
        }

        private fun getParallel(recipe: Recipe, coefficient: Double, turbineMaxVoltage: Long): Int
            = ceil((turbineMaxVoltage - excessVoltage) / (abs(recipe.eUt) * coefficient)).toInt()

        private fun canDoParallelRecipe(recipe: Recipe): Boolean
        {
            val rotorHolders = (mte as RotorHandler).rotorHolders
            if (rotorHolders != null && rotorHolders[0].hasRotor())
            {
                val coefficient = rotorHolders[0].totalEfficiency / 100.0
                val turbineMaxVoltage = getMaxVoltage()
                val parallel = getParallel(recipe, coefficient, turbineMaxVoltage)
                val recipeFluidStack = recipe.fluidInputs[0].inputFluidStack
                val inputFluid = inputTank.drain(FluidStack(recipeFluidStack.fluid, Int.MAX_VALUE), false)
                return inputFluid != null && inputFluid.amount >= recipeFluidStack.amount * parallel
            }
            return false
        }
    }
}
