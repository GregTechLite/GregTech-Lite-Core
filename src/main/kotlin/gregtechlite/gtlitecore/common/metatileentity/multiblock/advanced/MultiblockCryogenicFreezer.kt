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
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GelidCryotheum
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockCryogenicFreezer(id: ResourceLocation)
    : RecipeMapMultiblockController(id, VACUUM_RECIPES)
{

    private var pumpCasingTier = 0
    private var motorCasingTier = 0
    private var tier = 0

    init
    {
        this.recipeMapWorkable = CryogenicFreezerRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.HASTELLOY_X.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCryogenicFreezer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        this.motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        this.tier = minOf(pumpCasingTier, motorCasingTier)
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
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.HASTELLOY_X_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.VACUUM_FREEZER_OVERLAY

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.6"))
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

    private inner class CryogenicFreezerRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        private val mte = mte as MultiblockCryogenicFreezer

        override fun updateRecipeProgress()
        {
            if (canRecipeProgress && drawEnergy(recipeEUt, true))
            {
                val inputTank = this@CryogenicFreezerRecipeLogic.mte.getInputFluidInventory()
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

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(pumpCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * motorCasingTier

    }

}
