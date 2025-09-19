package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.*
import gregtech.api.block.IHeatingCoilBlockStats
import gregtech.api.capability.IHeatingCoil
import gregtech.api.capability.impl.HeatingCoilRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockWireCoil
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.GTLiteAPI.COIL_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.coils
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlazingPyrotheum
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.pow

class MultiblockVolcanus(id: ResourceLocation) :
    RecipeMapMultiblockController(id, BLAST_RECIPES), IHeatingCoil
{

    private var motorCasingTier = 0
    private var coilTier = 0
    private var temperature = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = VolcanusRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.HASTELLOY_C276.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockVolcanus(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        coilTier = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).tier
        tier = minOf(motorCasingTier, coilTier)

        temperature = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).coilTemperature
        temperature += 100 * max(0, getTierByVoltage(getEnergyContainer().inputVoltage) - MV)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        motorCasingTier = 0
        coilTier = 0
        temperature = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("DDD", "CCC", "CCC", "DDD")
        .aisle("DDD", "CMC", "CMC", "DOD")
        .aisle("DSD", "CCC", "CCC", "DDD")
        .where('S', selfPredicate())
        .where('D', states(casingState)
            .setMinGlobalLimited(9)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('O', abilities(MUFFLER_HATCH))
        .where('M', motorCasings())
        .where('C', coils())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.HASTELLOY_C276_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.BLAST_FURNACE_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("Vol, AEBF")
            description(true,
                        "gtlitecore.machine.volcanus.tooltip.1",
                        "gtlitecore.machine.volcanus.tooltip.2")
            overclockInfo(UV)
            durationInfo(UpgradeType.WIRE_COIL, 80)
            parallelInfo(UpgradeType.MOTOR_CASING, 16)
            description(false,
                        "gregtech.machine.electric_blast_furnace.tooltip.1",
                        "gregtech.machine.electric_blast_furnace.tooltip.2",
                        "gregtech.machine.electric_blast_furnace.tooltip.3")
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
                    val temperatureKey = KeyUtil.number(TextFormatting.RED,
                        syncer.syncInt(currentTemperature).toLong(), "K")
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                        "gregtech.multiblock.blast_furnace.max_temperature", temperatureKey))

                    if (getInputFluidInventory() != null)
                    {
                        val promoterStack = getInputFluidInventory()
                            .drain(BlazingPyrotheum.getFluid(Int.MAX_VALUE), false)
                        val promoterAmount = promoterStack?.amount ?: 0
                        val amountKey = KeyUtil.number(TextFormatting.GREEN,
                            promoterAmount.toLong(), "L")
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                            "gtlitecore.machine.volcanus.pyrotheum_amount", amountKey))
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
            val promoterStack = getInputFluidInventory()
                .drain(BlazingPyrotheum.getFluid(Int.MAX_VALUE), false)
            if (promoterStack == null || promoterStack.amount == 0)
            {
                val warnKey = KeyUtil.lang(TextFormatting.YELLOW,
                    "gtlitecore.machine.volcanus.pyrotheum_warning")
                keyManager.add(warnKey)
            }
        }
    }

    override fun getDataInfo(): List<ITextComponent>
    {
        val textList = super.getDataInfo()
        val temperatureInfo = translationWithColor(
            TextFormatting.RED,
            formatNumbers(temperature) + " K"
        )

        textList.add(
            translationWithColor(
                TextFormatting.GRAY,
                "gregtech.multiblock.blast_furnace.max_temperature", temperatureInfo
            )
        )
        return textList
    }

    override fun canBeDistinct() = true

    override fun hasMufflerMechanics() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    override fun getCurrentTemperature() = temperature

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean =
        temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0)!!

    private inner class VolcanusRecipeLogic(metaTileEntity: RecipeMapMultiblockController) :
        HeatingCoilRecipeLogic(metaTileEntity)
    {

        private val mte = metaTileEntity as MultiblockVolcanus

        override fun updateRecipeProgress()
        {
            if (canRecipeProgress && drawEnergy(recipeEUt, true))
            {
                val inputTank = mte.getInputFluidInventory()
                val pyrotheumStack = BlazingPyrotheum.getFluid(2)
                if (pyrotheumStack.isFluidStackIdentical(inputTank.drain(pyrotheumStack, false)))
                {
                    inputTank.drain(pyrotheumStack, true)
                    if (++progressTime > maxProgressTime)
                        completeRecipe()
                } else
                {
                    return
                }
                drawEnergy(recipeEUt, false)
            }
        }

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(motorCasingTier)).toInt())
        }

        override fun getParallelLimit() = 16 * coilTier

    }

}
