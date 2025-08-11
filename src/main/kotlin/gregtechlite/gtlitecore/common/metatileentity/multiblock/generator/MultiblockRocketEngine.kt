package gregtechlite.gtlitecore.common.metatileentity.multiblock.generator

import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.StringSyncValue
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.ProgressBarMultiblock
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.sync.FixedIntArraySyncValue
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.LiquidAir
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROCKET_ENGINE_FUELS
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.airCounter
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.energyOutputPredicate
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.UnaryOperator

class MultiblockRocketEngine(id: ResourceLocation?)
    : FuelMultiblockController(id, ROCKET_ENGINE_FUELS, IV), ProgressBarMultiblock
{

    private var size = 0

    init
    {
        recipeMapWorkable = LargeRocketEngineWorkableHandler(this)
        recipeMapWorkable.maximumOverclockVoltage = V[UHV]
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.NITINOL_60.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockRocketEngine(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        size = context.getOrDefault("length", 1)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC")
        .aisle("DDD", "O*O", "DDD").setRepeatable(2, 16)
        .aisle("CCC", "CSC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(8)
            .or(abilities(IMPORT_FLUIDS)
                .setPreviewCount(4))
            .or(abilities(MAINTENANCE_HATCH)
                .setExactLimit(1))
            .or(energyOutputPredicate(IV)
                .setExactLimit(1)))
        .where('D', states(casingState))
        .where('O', abilities(MUFFLER_HATCH))
        .where('*', airCounter())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.NITINOL_60_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_ROCKET_ENGINE_OVERLAY

    override fun hasMufflerMechanics() = true

    override fun shouldShowVoidingModeButton() = false

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        val recipeLogic = recipeMapWorkable as LargeRocketEngineWorkableHandler
        builder.setWorkingStatus(recipeLogic.isWorkingEnabled, recipeLogic.isActive && !isDynamoFull())
            .addEnergyProductionLine(V[LuV], recipeLogic.recipeEUt)
            .addFuelNeededLine(recipeLogic.recipeFluidInputInfo, recipeLogic.previousRecipeDuration)
            .addCustom { keyManager, syncer ->
                if (isStructureFormed && syncer.syncBoolean(recipeLogic.isHydrogenBoosted))
                {
                    keyManager.add(KeyUtil.lang(TextFormatting.AQUA,
                                                "gtlitecore.machine.large_rocket_engine.hydrogen_boosted"))
                }
            }
            .addWorkingStatusLine()
    }

    override fun configureWarningText(builder: MultiblockUIBuilder)
    {
        super.configureWarningText(builder)
        builder.addCustom { keyManager, syncer ->
            if (syncer.syncBoolean(::isDynamoFull))
            {
                keyManager.add(KeyUtil.lang(TextFormatting.YELLOW,
                                            "gtlitecore.tooltip.multiblock.dynamo_hatch_full"))
            }
        }
    }

    private fun isDynamoFull(): Boolean
    {
        return energyContainer.energyCanBeInserted < recipeMapWorkable.recipeEUt
    }

    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gregtech.universal.tooltip.base_production_eut", V[IV]))
        tooltip.add(I18n.format("gtlitecore.machine.large_rocket_engine.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_rocket_engine.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_rocket_engine.tooltip.3"))
    }

    override fun getProgressBarCount(): Int = 4

    @Suppress("UnstableApiUsage")
    override fun registerBars(templateBars: MutableList<UnaryOperator<TemplateBarBuilder>>, guiSyncManager: PanelSyncManager)
    {
        val fuelValue = FixedIntArraySyncValue(::getFuelAmount, null)
        guiSyncManager.syncValue("fuel_amount", fuelValue)

        val fuelNameValue = StringSyncValue {
            val stack = (recipeMapWorkable as MultiblockFuelRecipeLogic).inputFluidStack
            if (stack == null) return@StringSyncValue null
            val fluid = stack.fluid
            if (fluid == null) return@StringSyncValue null
            return@StringSyncValue fluid.name
        }
        guiSyncManager.syncValue("fuel_name", fuelNameValue)

        val liquidAirValue = FixedIntArraySyncValue(::getLiquidAirAmount, null)
        guiSyncManager.syncValue("liquid_air_amount", liquidAirValue)

        val carbonDioxideValue = FixedIntArraySyncValue(::getCarbonDioxideAmount, null)
        guiSyncManager.syncValue("carbon_dioxide_amount", carbonDioxideValue)

        val hydrogenValue = FixedIntArraySyncValue(::getHydrogenAmount, null)
        guiSyncManager.syncValue("hydrogen_amount", hydrogenValue)

        templateBars.add { templateBar -> templateBar
            .progress {
                if (fuelValue.getValue(1) == 0) return@progress 0.0
                else return@progress 1.0 * fuelValue.getValue(0) / fuelValue.getValue(1)
            }
            .texture(GTGuiTextures.PROGRESS_BAR_LCE_FUEL)
            .tooltipBuilder { tooltip ->
                createFuelTooltip(tooltip, fuelValue, fuelNameValue)
            }
        }

        templateBars.add { templateBar -> templateBar
            .progress {
                if (hydrogenValue.getValue(1) == 0) return@progress 0.0
                else return@progress 1.0 * hydrogenValue.getValue(0) / hydrogenValue.getValue(1)
            }
            .texture(GTLiteMuiTextures.PROGRESS_BAR_LRE_HYDROGEN)
            .tooltipBuilder { tooltip ->
                if (isStructureFormed)
                {
                    if ((recipeLogic as LargeRocketEngineWorkableHandler).isHydrogenBoosted)
                    {
                        if (hydrogenValue.getValue(0) == 0)
                            tooltip.addLine(KeyUtil.lang(TextFormatting.RED,
                                                         "gtlitecore.machine.large_rocket_engine.hydrogen_none"))
                        else
                            tooltip.addLine(KeyUtil.lang("gtlitecore.machine.large_rocket_engine.hydrogen_amount",
                                                         hydrogenValue.getValue(0), hydrogenValue.getValue(1)))
                    }
                }
                else
                {
                    tooltip.addLine(KeyUtil.lang("gregtech.multiblock.invalid_structure"))
                }
            }
        }

        templateBars.add { templateBar -> templateBar
            .progress {
                if (liquidAirValue.getValue(1) == 0) return@progress 0.0
                else return@progress 1.0 * liquidAirValue.getValue(0) / liquidAirValue.getValue(1)
            }
            .texture(GTLiteMuiTextures.PROGRESS_BAR_LRE_LIQUID_AIR)
            .tooltipBuilder { tooltip ->
                if (isStructureFormed)
                {
                    if (liquidAirValue.getValue(0) == 0)
                        tooltip.addLine(KeyUtil.lang(TextFormatting.RED,
                                                     "gtlitecore.machine.large_rocket_engine.liquid_air_none"))
                    else
                        tooltip.addLine(KeyUtil.lang("gtlitecore.machine.large_rocket_engine.liquid_air_amount",
                                                     liquidAirValue.getValue(0), liquidAirValue.getValue(1)))
                }
                else
                {
                    tooltip.addLine(KeyUtil.lang("gregtech.multiblock.invalid_structure"))
                }
            }
        }

        templateBars.add { templateBar -> templateBar
            .progress {
                if (carbonDioxideValue.getValue(1) == 0) return@progress 0.0
                else return@progress 1.0 * carbonDioxideValue.getValue(0) / carbonDioxideValue.getValue(1)
            }
            .texture(GTLiteMuiTextures.PROGRESS_BAR_LRE_CARBON_DIOXIDE)
            .tooltipBuilder { tooltip ->
                if (isStructureFormed)
                {
                    if (carbonDioxideValue.getValue(0) == 0)
                        tooltip.addLine(KeyUtil.lang(TextFormatting.RED,
                                                     "gtlitecore.machine.large_rocket_engine.carbon_dioxide_none"))
                    else
                        tooltip.addLine(KeyUtil.lang("gtlitecore.machine.large_rocket_engine.carbon_dioxide_amount",
                                                     carbonDioxideValue.getValue(0), carbonDioxideValue.getValue(1)))
                }
                else
                {
                    tooltip.addLine(KeyUtil.lang("gregtech.multiblock.invalid_structure"))
                }
            }
        }
    }

    private fun getFuelAmount(): IntArray
    {
        if (getFluidInventory() != null)
        {
            val recipeLogic = recipeMapWorkable as MultiblockFuelRecipeLogic
            if (recipeLogic.inputFluidStack != null)
            {
                val testStack = recipeLogic.inputFluidStack.copy()
                testStack.amount = Int.MAX_VALUE
                return getTotalFluidAmount(testStack, getInputFluidInventory())
            }
        }
        return IntArray(2)
    }

    private fun getLiquidAirAmount(): IntArray
    {
        if (getInputFluidInventory() != null)
        {
            return getTotalFluidAmount(LiquidAir.getFluid(Int.MAX_VALUE), getInputFluidInventory())
        }
        return IntArray(2)
    }

    private fun getCarbonDioxideAmount(): IntArray
    {
        if (getInputFluidInventory() != null)
        {
            return getTotalFluidAmount(CarbonDioxide.getFluid(Int.MAX_VALUE), getInputFluidInventory())
        }
        return IntArray(2)
    }

    private fun getHydrogenAmount(): IntArray
    {
        if (getInputFluidInventory() != null)
        {
            return getTotalFluidAmount(Hydrogen.getFluid(Int.MAX_VALUE), getInputFluidInventory())
        }
        return IntArray(2)
    }

    inner class LargeRocketEngineWorkableHandler(metaTileEntity: RecipeMapMultiblockController?) : MultiblockFuelRecipeLogic(metaTileEntity)
    {

        private var rocketEngine: MultiblockRocketEngine? = null

        var isHydrogenBoosted = false

        private val HYDROGEN_STACK: FluidStack = Hydrogen.getFluid(80)
        private val LIQUID_AIR_STACK: FluidStack = LiquidAir.getFluid(8000)
        private val CARBON_DIOXIDE_STACK: FluidStack = CarbonDioxide.getFluid(200)

        init
        {
            rocketEngine = metaTileEntity as MultiblockRocketEngine
        }

        override fun updateRecipeProgress()
        {
            if (canRecipeProgress && drawEnergy(recipeEUt, true))
            {
                drainLiquidAir()
                drainCarbonDioxide()
                drainHydrogen()

                drawEnergy(recipeEUt, false)
                if (++progressTime > maxProgressTime)
                    completeRecipe()
            }
        }

        private fun checkHydrogen()
        {
            isHydrogenBoosted = HYDROGEN_STACK.isFluidStackIdentical((rocketEngine!!.inputFluidInventory as IFluidHandler)
                .drain(HYDROGEN_STACK, false))
        }

        private fun drainHydrogen()
        {
            if (isHydrogenBoosted && totalContinuousRunningTime % SECOND == 0L)
            {
                (rocketEngine!!.inputFluidInventory as IFluidHandler).drain(HYDROGEN_STACK, true)
            }
        }

        private fun checkLiquidAir(): Boolean
        {
            if (LIQUID_AIR_STACK.isFluidStackIdentical((rocketEngine!!.inputFluidInventory as IFluidHandler)
                    .drain(LIQUID_AIR_STACK, false)))
            {
                return true
            }
            else
            {
                invalidate()
                return false
            }
        }

        private fun drainLiquidAir()
        {
            if (totalContinuousRunningTime % SECOND == 0L)
                (rocketEngine!!.inputFluidInventory as IFluidHandler).drain(LIQUID_AIR_STACK, true)
        }

        private fun checkCarbonDioxide(): Boolean
        {
            if (CARBON_DIOXIDE_STACK.isFluidStackIdentical((rocketEngine!!.inputFluidInventory as IFluidHandler)
                    .drain(CARBON_DIOXIDE_STACK, false)))
            {
                return true
            }
            else
            {
                invalidate()
                return false
            }
        }

        private fun drainCarbonDioxide()
        {
            if (totalContinuousRunningTime % SECOND == 0L)
                (rocketEngine!!.inputFluidInventory as IFluidHandler).drain(CARBON_DIOXIDE_STACK, true)
        }

        override fun shouldSearchForRecipes(): Boolean
        {
            checkHydrogen()
            return super.shouldSearchForRecipes() && checkLiquidAir() && checkCarbonDioxide()
        }

        override fun canProgressRecipe(): Boolean
        {
            return super.canProgressRecipe() && checkLiquidAir() && checkCarbonDioxide()
        }

        override fun getMaxVoltage(): Long
        {
            return if (isHydrogenBoosted) V[IV] * 3 else V[IV]
        }

        override fun boostProduction(production: Long): Long
        {
            if (isHydrogenBoosted)
                return production * 2 // 200% efficiency
            return production
        }

        override fun invalidate()
        {
            super.invalidate()
            isHydrogenBoosted = false
        }

        override fun isAllowOverclocking() = size >= 10

    }

}