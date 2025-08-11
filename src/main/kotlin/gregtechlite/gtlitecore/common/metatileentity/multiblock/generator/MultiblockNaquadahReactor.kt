package gregtechlite.gtlitecore.common.metatileentity.multiblock.generator

import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.StringSyncValue
import gregtech.api.GTValues.MAX_TRUE
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VOC
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.ProgressBarMultiblock
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.sync.FixedIntArraySyncValue
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.NAQUADAH_REACTOR_FUELS
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.UnaryOperator

class MultiblockNaquadahReactor(id: ResourceLocation)
    : FuelMultiblockController(id, NAQUADAH_REACTOR_FUELS, UV), ProgressBarMultiblock
{

    init
    {
        recipeMapWorkable = LargeNaquadahReactorWorkableHandler(this)
        recipeMapWorkable.maximumOverclockVoltage = VOC[MAX_TRUE] // Long.MAX_VALUE
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.NAQUADAH_ALLOY.state
        private val secondCasingState
            get() = GTMultiblockCasing.GRATE_CASING.state
        private val uniqueCasingState
            get() = ActiveUniqueCasing.HEAT_VENT.state
        private val pipeCasingState
            get() = BoilerCasing.POLYBENZIMIDAZOLE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockNaquadahReactor(metaTileEntityId)

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val outputEnergy = ArrayList(getAbilities(OUTPUT_ENERGY))
        outputEnergy.addAll(getAbilities(SUBSTATION_OUTPUT_ENERGY))
        outputEnergy.addAll(getAbilities(OUTPUT_LASER))
        energyContainer = EnergyContainerList(outputEnergy)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CUC ", " CCC ", "  C  ", "  C  ", " CCC ", " CUC ", " CCC ")
        .aisle("CCCCC", "CP#PC", "CG#GC", " P#P ", " P#P ", "CG#GC", "CP#PC", " CCC ")
        .aisle("CCCCC", "U#F#U", "C#F#C", "C#F#C", "C#F#C", "C#F#C", "U#F#U", " COC ")
        .aisle("CCCCC", "CP#PC", "CG#GC", " P#P ", " P#P ", "CG#GC", "CP#PC", " CCC ")
        .aisle(" CCC ", " CSC ", " CCC ", "  C  ", "  C  ", " CCC ", " CUC ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(70)
            .or(abilities(IMPORT_FLUIDS)
                .setPreviewCount(2))
            .or(abilities(MAINTENANCE_HATCH)
                .setExactLimit(1))
            .or(abilities(OUTPUT_ENERGY, OUTPUT_LASER)
                .setExactLimit(1)))
        .where('U', states(uniqueCasingState))
        .where('G', states(secondCasingState))
        .where('F', frames(Naquadria))
        .where('P', states(pipeCasingState))
        .where('O', abilities(MUFFLER_HATCH))
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.NAQUADAH_ALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.POWER_SUBSTATION_OVERLAY

    override fun getMufflerParticle(): EnumParticleTypes = EnumParticleTypes.SPELL_WITCH

    override fun hasMufflerMechanics() = true

    override fun shouldShowVoidingModeButton(): Boolean = false

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        val recipeLogic = recipeMapWorkable as LargeNaquadahReactorWorkableHandler
        builder.setWorkingStatus(recipeLogic.isWorkingEnabled, recipeLogic.isActive && !isDynamoFull())
            .addEnergyProductionLine(V[UHV], recipeLogic.recipeEUt)
            .addFuelNeededLine(recipeLogic.recipeFluidInputInfo, recipeLogic.previousRecipeDuration)
            .addCustom { keyManager, syncer ->
                if (isStructureFormed && syncer.syncBoolean(recipeLogic.isPlasmaOxygenBoosted))
                {
                    keyManager.add(KeyUtil.lang(TextFormatting.AQUA,
                                                "gtlitecore.machine.large_naquadah_reactor.plasma_oxygen_boosted"))
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

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gregtech.universal.tooltip.base_production_eut", V[UHV]))
        tooltip.add(I18n.format("gtlitecore.machine.large_naquadah_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_naquadah_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_naquadah_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_naquadah_reactor.tooltip.4"))
    }

    override fun getProgressBarCount(): Int = 2

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

        val boostedValue = FixedIntArraySyncValue(::getPlasmaOxygenAmount, null)
        guiSyncManager.syncValue("plasma_oxygen_amount", boostedValue)

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
                if (boostedValue.getValue(1) == 0) return@progress 0.0
                else return@progress 1.0 * boostedValue.getValue(0) / boostedValue.getValue(1)
            }
            .texture(GTLiteMuiTextures.PROGRESS_BAR_LNR_PLASMA_OXYGEN)
            .tooltipBuilder { tooltip ->
                if (isStructureFormed)
                {
                    if ((recipeLogic as LargeNaquadahReactorWorkableHandler).isPlasmaOxygenBoosted)
                    {
                        if (boostedValue.getValue(0) == 0)
                            tooltip.addLine(KeyUtil.lang(TextFormatting.RED,
                                                         "gtlitecore.machine.large_naquadah_reactor.plasma_oxygen_none"))
                        else
                            tooltip.addLine(KeyUtil.lang("gtlitecore.machine.large_naquadah_reactor.plasma_oxygen_amount",
                                                         boostedValue.getValue(0), boostedValue.getValue(1)))
                    }
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
        if (getInputFluidInventory() != null)
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

    private fun getPlasmaOxygenAmount(): IntArray
    {
        if (getInputFluidInventory() != null)
        {
            val boostedStack = Oxygen.getPlasma(Int.MAX_VALUE)
            return getTotalFluidAmount(boostedStack, getInputFluidInventory())
        }
        return IntArray(2)
    }

    private inner class LargeNaquadahReactorWorkableHandler(metaTileEntity: RecipeMapMultiblockController?) : MultiblockFuelRecipeLogic(metaTileEntity)
    {

        private var naquadahReactor: MultiblockNaquadahReactor? = null

        var isPlasmaOxygenBoosted = false

        private val PLASMA_OXYGEN_STACK: FluidStack = Oxygen.getPlasma(50)

        init
        {
            naquadahReactor = metaTileEntity as MultiblockNaquadahReactor
        }

        override fun updateRecipeProgress()
        {
            if (canRecipeProgress && drawEnergy(recipeEUt, true))
            {
                drainPlasmaOxygen()

                drawEnergy(recipeEUt, false)
                if (++progressTime > maxProgressTime)
                    completeRecipe()
            }
        }

        private fun checkPlasmaOxygen()
        {
            isPlasmaOxygenBoosted = PLASMA_OXYGEN_STACK.isFluidStackIdentical((naquadahReactor!!.inputFluidInventory as IFluidHandler)
                .drain(PLASMA_OXYGEN_STACK, false))
        }

        private fun drainPlasmaOxygen()
        {
            if (isPlasmaOxygenBoosted && totalContinuousRunningTime % SECOND == 0L)
            {
                (naquadahReactor!!.inputFluidInventory as IFluidHandler).drain(PLASMA_OXYGEN_STACK, true)
            }
        }

        override fun shouldSearchForRecipes(): Boolean
        {
            checkPlasmaOxygen()
            return super.shouldSearchForRecipes()
        }

        override fun getMaxVoltage(): Long
        {
            return if (isPlasmaOxygenBoosted) V[UV] * 16 else V[UV] * 4
        }

        override fun boostProduction(production: Long): Long
        {
            if (isPlasmaOxygenBoosted)
                return production * 2 // 200% efficiency
            return production
        }

        override fun invalidate()
        {
            super.invalidate()
            isPlasmaOxygenBoosted = false
        }

        override fun isAllowOverclocking() = true

    }

}