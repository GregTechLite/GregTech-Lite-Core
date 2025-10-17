package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GelidCryotheum
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockCryogenicFreezer(id: ResourceLocation) : RecipeMapMultiblockController(id, VACUUM_RECIPES)
{

    private var pumpCasingTier = 0
    private var motorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = CryogenicFreezerRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.HASTELLOY_X.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCryogenicFreezer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        tier = minOf(pumpCasingTier, motorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.pumpCasingTier = 0
        this.motorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC")
        .aisle("CMC", "CPC", "CCC")
        .aisle("CCC", "CSC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(4)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('P', pumpCasings())
        .where('M', motorCasings())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.HASTELLOY_X_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.VACUUM_FREEZER_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.cryogenic_freezer.tooltip.1",
                               "gtlitecore.machine.cryogenic_freezer.tooltip.2")
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addParallelInfo(UpgradeMode.MOTOR_CASING, 16)
            addDurationInfo(UpgradeMode.PUMP_CASING, 300)
            addEnergyInfo(20)
        }
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addCustom { keyManager, syncer ->
                if (isStructureFormed)
                {
                    if (getInputFluidInventory() != null)
                    {
                        val coolantStack = getInputFluidInventory()
                            .drain(GelidCryotheum.getFluid(Int.MAX_VALUE), false)
                        val coolantAmount = coolantStack?.amount ?: 0
                        val amountKey = KeyUtil.number(TextFormatting.GREEN,
                            coolantAmount.toLong(), "L")
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                            "gtlitecore.machine.cryogenic_freezer.cryotheum_amount", amountKey))
                    }
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addRecipeOutputLine(recipeMapWorkable)
    }

    override fun configureWarningText(builder: MultiblockUIBuilder)
    {
        super.configureWarningText(builder)
        builder.addCustom { keyManager, syncer ->
            if (isStructureFormed)
            {
                val coolantStack = getInputFluidInventory()
                    .drain(GelidCryotheum.getFluid(Int.MAX_VALUE), false)
                if (coolantStack == null || coolantStack.amount == 0)
                {
                    val warnKey = KeyUtil.lang(TextFormatting.YELLOW,
                        "gtlitecore.machine.cryogenic_freezer_cryotheum_warning")
                    keyManager.add(warnKey)
                }
            }
        }
    }

    override fun canBeDistinct() = true

    private inner class CryogenicFreezerRecipeLogic(private val mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun updateRecipeProgress()
        {
            if (canRecipeProgress && drawEnergy(recipeEUt, true))
            {
                val inputTank = (mte as MultiblockCryogenicFreezer).getInputFluidInventory()
                val cryotheumStack = GelidCryotheum.getFluid(2)
                if (cryotheumStack.isFluidStackIdentical(inputTank.drain(cryotheumStack, false)))
                {
                    inputTank.drain(cryotheumStack, true)
                    if (++progressTime > maxProgressTime)
                        completeRecipe()
                }
                else
                {
                    return
                }
                drawEnergy(this.recipeEUt, false)
            }
        }

        override fun getOverclockingDurationFactor()
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -20%
            ocResult.setEut(max(1, (ocResult.eut() * 0.8).toLong()))

            // +300% / pump casing tier | D' = D / (1 + 3.0 * (T - 1)) = D / (3.0 * T - 2.0), where k = 3.0
            if (pumpCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (3.0 * pumpCasingTier - 2.0)).toInt()))
        }

        override fun getParallelLimit() = 16 * motorCasingTier

    }

}
