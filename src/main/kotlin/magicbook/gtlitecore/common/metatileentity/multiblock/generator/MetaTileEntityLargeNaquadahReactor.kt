package magicbook.gtlitecore.common.metatileentity.multiblock.generator

import gregtech.api.GTValues.MAX_TRUE
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VOC
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic
import gregtech.api.gui.resources.TextureArea
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.IProgressBarMultiblock
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.util.TextComponentUtil.stringWithColor
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.NAQUADAH_REACTOR_FUELS
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockActiveUniqueCasing01
import magicbook.gtlitecore.common.block.blocks.BlockBoilerCasing01
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityLargeNaquadahReactor(metaTileEntityId: ResourceLocation?) : FuelMultiblockController(metaTileEntityId, NAQUADAH_REACTOR_FUELS, UV), IProgressBarMultiblock
{

    init
    {
        recipeMapWorkable = LargeNaquadahReactorWorkableHandler(this)
        recipeMapWorkable.maximumOverclockVoltage = VOC[MAX_TRUE] // Long.MAX_VALUE
    }

    companion object
    {
        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.NAQUADAH_ALLOY)
        private val secondCasingState
            get() = MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)
        private val uniqueCasingState
            get() = GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01.getState(BlockActiveUniqueCasing01.UniqueCasingType.HEAT_VENT)
        private val pipeCasingState
            get() = GTLiteMetaBlocks.BOILER_CASING_01.getState(BlockBoilerCasing01.BoilerCasingType.POLYBENZIMIDAZOLE)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntityLargeNaquadahReactor(metaTileEntityId)

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

    override fun addDisplayText(textList: MutableList<ITextComponent>?)
    {
        val recipeLogic = recipeMapWorkable as LargeNaquadahReactorWorkableHandler
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .setWorkingStatus(recipeLogic.isWorkingEnabled, recipeLogic.isActive)
            .addEnergyProductionLine(V[UHV], recipeLogic.recipeEUt)
            .addFuelNeededLine(recipeLogic.recipeFluidInputInfo, recipeLogic.previousRecipeDuration)
            .addCustom { tl ->
                if (isStructureFormed && recipeLogic.isPlasmaOxygenBoosted)
                    tl.add(translationWithColor(TextFormatting.AQUA,
                        "gtlitecore.machine.large_naquadah_reactor.plasma_oxygen_boosted") as ITextComponent)
            }
            .addWorkingStatusLine()
    }

    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gregtech.universal.tooltip.base_production_eut", V[UHV]))
        tooltip.add(I18n.format("gtlitecore.machine.large_naquadah_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_naquadah_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_naquadah_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_naquadah_reactor.tooltip.4"))
    }

    override fun getNumProgressBars() = 1

    override fun getFillPercentage(index: Int): Double
    {
        var plasmaOxygenAmount = IntArray(2)
        if (inputFluidInventory != null)
        {
            val plasmaOxygenStack = Materials.Oxygen.getPlasma(Int.MAX_VALUE)
            plasmaOxygenAmount = getTotalFluidAmount(plasmaOxygenStack, inputFluidInventory)
        }
        return if (plasmaOxygenAmount[1] != 0) 1.0 * plasmaOxygenAmount[0] / plasmaOxygenAmount[1] else 0.0
    }

    override fun addBarHoverText(hoverList: MutableList<ITextComponent>, index: Int)
    {
        var plasmaOxygenStored = 0
        var plasmaOxygenCapacity = 0
        if (isStructureFormed && inputFluidInventory != null)
        {
            val plasmaOxygenStack = Materials.Oxygen.getPlasma(Int.MAX_VALUE)
            val plasmaOxygenAmount = getTotalFluidAmount(plasmaOxygenStack, inputFluidInventory)
            plasmaOxygenStored = plasmaOxygenAmount[0]
            plasmaOxygenCapacity = plasmaOxygenAmount[1]
        }
        val plasmaOxygenInfo = stringWithColor(TextFormatting.AQUA,
            formatNumbers(plasmaOxygenStored) + " / " + formatNumbers(plasmaOxygenCapacity) + " L")

        hoverList.add(translationWithColor(TextFormatting.GRAY,
            "gtlitecore.machine.large_naquadah_reactor.plasma_oxygen_amount", plasmaOxygenInfo) as ITextComponent)
    }

    override fun getProgressBarTexture(index: Int): TextureArea = GTLiteGuiTextures.PROGRESS_BAR_LNR_PLASMA_OXYGEN

    inner class LargeNaquadahReactorWorkableHandler(metaTileEntity: RecipeMapMultiblockController?) : MultiblockFuelRecipeLogic(metaTileEntity)
    {

        private var naquadahReactor: MetaTileEntityLargeNaquadahReactor? = null

        var isPlasmaOxygenBoosted = false

        private val PLASMA_OXYGEN_STACK: FluidStack = Materials.Oxygen.getPlasma(50)

        init
        {
            naquadahReactor = metaTileEntity as MetaTileEntityLargeNaquadahReactor
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