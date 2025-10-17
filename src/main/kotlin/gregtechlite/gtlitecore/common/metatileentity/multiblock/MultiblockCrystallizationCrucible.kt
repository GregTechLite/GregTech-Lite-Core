package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
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
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI.COIL_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.coils
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockCrystallizationCrucible(id: ResourceLocation) : RecipeMapMultiblockController(id, CRYSTALLIZATION_RECIPES), IHeatingCoil
{

    private var motorCasingTier = 0
    private var coilTier = 0
    private var temperature = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = CrystallizationCrucibleRecipeLogic(this)
    }

    companion object
    {
        private val casingState = GTMetalCasing.TITANIUM_STABLE.state
        private val uniqueCasingState = ActiveUniqueCasing.HEAT_VENT.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCrystallizationCrucible(metaTileEntityId)

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
        temperature = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "F   F", "F   F", "CCCCC")
        .aisle("CCCCC", " UHU ", " UHU ", "CCCCC")
        .aisle("CCCCC", " HMH ", " HMH ", "CCOCC")
        .aisle("CCCCC", " UHU ", " UHU ", "CCCCC")
        .aisle("CCSCC", "F   F", "F   F", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(32)
            .or(autoAbilities(true, true, true, true, true, false, false)))
        .where('U', states(uniqueCasingState))
        .where('O', abilities(MUFFLER_HATCH))
        .where('F', frames(Titanium))
        .where('M', motorCasings())
        .where('H', coils())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.STABLE_TITANIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.CRYSTALLIZATION_CRUCIBLE_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.crystallization_crucible.tooltip.1",
                               "gregtech.machine.electric_blast_furnace.tooltip.1",
                               "gregtech.machine.electric_blast_furnace.tooltip.2",
                               "gregtech.machine.electric_blast_furnace.tooltip.3")
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addParallelInfo(UpgradeMode.WIRE_COIL, 8)
            addDurationInfo(UpgradeMode.MOTOR_CASING, 250)
            addEnergyInfo(40)
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
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addRecipeOutputLine(recipeMapWorkable)
    }

    override fun getDataInfo(): List<ITextComponent>
    {
        val textList = super.getDataInfo()
        val temperatureInfo = translationWithColor(TextFormatting.RED,
            formatNumbers(temperature) + " K")
        textList.add(translationWithColor(TextFormatting.GRAY,
            "gregtech.multiblock.blast_furnace.max_temperature", temperatureInfo))
        return textList
    }

    override fun canBeDistinct() = true

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
        = temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0)!!

    override fun hasMufflerMechanics() = true

    override fun getCurrentTemperature() = temperature

    private inner class CrystallizationCrucibleRecipeLogic(mte: RecipeMapMultiblockController?) : HeatingCoilRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -40%
            ocResult.setEut(max(1, (ocResult.eut() * 0.6).toLong()))

            // +250% / motor casing tier | D' = D / (1 + 2.5 * (T - 1.0)) = D / (2.5 * T - 1.5), where k = 2.5
            if (motorCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (2.5 * motorCasingTier - 1.5)).toInt()))
        }

        override fun getParallelLimit() = 8 * coilTier

    }

}
