package magicbook.gtlitecore.common.metatileentity.multiblock.generator

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic
import gregtech.api.gui.resources.TextureArea
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.IProgressBarMultiblock
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.LiquidAir
import gregtech.api.util.TextComponentUtil.stringWithColor
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import magicbook.gtlitecore.api.pattern.GTLiteTraceabilityPredicate.Companion.energyOutputPredicate
import magicbook.gtlitecore.api.pattern.GTLiteTraceabilityPredicate.Companion.scaleIndicatorPredicate
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROCKET_ENGINE_FUELS
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MetaTileEntityLargeRocketEngine(metaTileEntityId: ResourceLocation?) : FuelMultiblockController(metaTileEntityId, ROCKET_ENGINE_FUELS, IV), IProgressBarMultiblock
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
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.NITINOL_60)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntityLargeRocketEngine(metaTileEntityId)

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
        .where('*', scaleIndicatorPredicate())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.NITINOL_60_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_ROCKET_ENGINE_OVERLAY

    override fun hasMufflerMechanics() = true

    override fun shouldShowVoidingModeButton() = false

    override fun addDisplayText(textList: MutableList<ITextComponent>?)
    {
        val recipeLogic = recipeMapWorkable as LargeRocketEngineWorkableHandler
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .setWorkingStatus(recipeLogic.isWorkingEnabled, recipeLogic.isActive)
            .addEnergyProductionLine(V[LuV], recipeLogic.recipeEUt)
            .addFuelNeededLine(recipeLogic.recipeFluidInputInfo, recipeLogic.previousRecipeDuration)
            .addCustom { tl ->
                if (isStructureFormed && recipeLogic.isHydrogenBoosted)
                    tl.add(translationWithColor(TextFormatting.AQUA,
                        "gtlitecore.machine.large_rocket_engine.hydrogen_boosted"))
            }
            .addWorkingStatusLine()
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

    override fun getNumProgressBars() = 3

    override fun getFillPercentage(index: Int): Double
    {
        when (index)
        {
            0 -> {
                var hydrogenAmount = IntArray(2)
                if (inputFluidInventory != null)
                {
                    val hydrogenStack = Hydrogen.getFluid(Int.MAX_VALUE)
                    hydrogenAmount = getTotalFluidAmount(hydrogenStack, inputFluidInventory)
                }
                return if (hydrogenAmount[1] != 0) 1.0 * hydrogenAmount[0] / hydrogenAmount[1] else 0.0
            }
            1 -> {
                var liquidAirAmount = IntArray(2)
                if (inputFluidInventory != null)
                {
                    val liquidAirStack = LiquidAir.getFluid(Int.MAX_VALUE)
                    liquidAirAmount = getTotalFluidAmount(liquidAirStack, inputFluidInventory)
                }
                return if (liquidAirAmount[1] != 0) 1.0 * liquidAirAmount[0] / liquidAirAmount[1] else 0.0
            }
            else -> {
                var carbonDioxideAmount = IntArray(2)
                if (inputFluidInventory != null)
                {
                    val carbonDioxideStack = CarbonDioxide.getFluid(Int.MAX_VALUE)
                    carbonDioxideAmount = getTotalFluidAmount(carbonDioxideStack, inputFluidInventory)
                }
                return if (carbonDioxideAmount[1] != 0) 1.0 * carbonDioxideAmount[0] / carbonDioxideAmount[1] else 0.0
            }
        }
    }

    override fun addBarHoverText(hoverList: MutableList<ITextComponent>, index: Int)
    {
        when (index)
        {
            0 -> {
                var hydrogenStored = 0
                var hydrogenCapacity = 0
                if (isStructureFormed && inputFluidInventory != null)
                {
                    val hydrogenStack = Hydrogen.getFluid(Int.MAX_VALUE)
                    val hydrogenAmount = getTotalFluidAmount(hydrogenStack, inputFluidInventory)
                    hydrogenStored = hydrogenAmount[0]
                    hydrogenCapacity = hydrogenAmount[1]
                }
                val hydrogenInfo = stringWithColor(TextFormatting.BLUE,
                    formatNumbers(hydrogenStored) + " / " + formatNumbers(hydrogenCapacity) + " L")

                hoverList.add(translationWithColor(TextFormatting.GRAY,
                    "gtlitecore.machine.large_rocket_engine.hydrogen_amount", hydrogenInfo))
            }
            1 -> {
                var liquidAirStored = 0
                var liquidAirCapacity = 0
                if (isStructureFormed && inputFluidInventory != null)
                {
                    val liquidAirStack = LiquidAir.getFluid(Int.MAX_VALUE)
                    val liquidAirAmount = getTotalFluidAmount(liquidAirStack, inputFluidInventory)
                    liquidAirStored = liquidAirAmount[0]
                    liquidAirCapacity = liquidAirAmount[1]
                }
                val liquidAirInfo = stringWithColor(TextFormatting.AQUA,
                    formatNumbers(liquidAirStored) + " / " + formatNumbers(liquidAirCapacity) + " L")

                hoverList.add(translationWithColor(TextFormatting.GRAY,
                    "gtlitecore.machine.large_rocket_engine.liquid_air_amount", liquidAirInfo))
            }
            else -> {
                var carbonDioxideStored = 0
                var carbonDioxideCapacity = 0
                if (isStructureFormed && inputFluidInventory != null)
                {
                    val carbonDioxideStack = CarbonDioxide.getFluid(Int.MAX_VALUE)
                    val carbonDioxideAmount = getTotalFluidAmount(carbonDioxideStack, inputFluidInventory)
                    carbonDioxideStored = carbonDioxideAmount[0]
                    carbonDioxideCapacity = carbonDioxideAmount[1]
                }
                val carbonDioxideInfo = stringWithColor(TextFormatting.DARK_AQUA,
                    formatNumbers(carbonDioxideStored) + " / " + formatNumbers(carbonDioxideCapacity) + " L")

                hoverList.add(translationWithColor(TextFormatting.GRAY,
                    "gtlitecore.machine.large_rocket_engine.carbon_dioxide_amount", carbonDioxideInfo))
            }
        }
    }

    override fun getProgressBarTexture(index: Int): TextureArea = when (index)
    {
        0 -> GTLiteGuiTextures.PROGRESS_BAR_LRE_HYDROGEN
        1 -> GTLiteGuiTextures.PROGRESS_BAR_LRE_LIQUID_AIR
        else -> GTLiteGuiTextures.PROGRESS_BAR_LRE_CARBON_DIOXIDE
    }

    inner class LargeRocketEngineWorkableHandler(metaTileEntity: RecipeMapMultiblockController?) : MultiblockFuelRecipeLogic(metaTileEntity)
    {

        private var rocketEngine: MetaTileEntityLargeRocketEngine? = null

        var isHydrogenBoosted = false

        private val HYDROGEN_STACK: FluidStack = Hydrogen.getFluid(80)
        private val LIQUID_AIR_STACK: FluidStack = LiquidAir.getFluid(8000)
        private val CARBON_DIOXIDE_STACK: FluidStack = CarbonDioxide.getFluid(200)

        init
        {
            rocketEngine = metaTileEntity as MetaTileEntityLargeRocketEngine
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