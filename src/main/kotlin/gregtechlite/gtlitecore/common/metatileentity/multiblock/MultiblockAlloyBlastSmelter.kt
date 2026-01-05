package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues
import gregtech.api.capability.IHeatingCoil
import gregtech.api.capability.impl.HeatingCoilRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtech.api.util.GTUtility
import gregtech.api.util.KeyUtil
import gregtech.api.util.TextComponentUtil
import gregtech.api.util.TextFormattingUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockAlloyBlastSmelter(id: ResourceLocation) : RecipeMapMultiblockController(id,
                                                                                        GTLiteRecipeMaps.ALLOY_BLAST_RECIPES),
    IHeatingCoil
{

    private var pumpCasingTier = 0
    private var coilTier = 0
    private var tier = 0

    private var temperature = 0

    init
    {
        recipeMapWorkable = AlloyBlastSmelterRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.ZIRCONIUM_CARBIDE.state
        private val pipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
        private val uniqueCasingState = ActiveUniqueCasing.HEAT_VENT.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockAlloyBlastSmelter(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pumpCasingTier = context.getAttributeOrDefault(GTLiteAPI.PUMP_CASING_TIER, 0)
        coilTier = context.getAttributeOrDefault(GTLiteAPI.COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).tier
        tier = minOf(pumpCasingTier, coilTier)

        temperature = context.getAttributeOrDefault(GTLiteAPI.COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).coilTemperature
        temperature += 100 * max(0, GTUtility.getTierByVoltage(energyContainer.inputVoltage) - GTValues.MV)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pumpCasingTier = 0
        temperature = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " HHH ", " UUU ", " HHH ", " CCC ")
        .aisle("CCCCC", "H###H", "U###U", "H###H", "CCCCC")
        .aisle("CCCCC", "H#P#H", "U#Q#U", "H#P#H", "CCOCC")
        .aisle("CCCCC", "H###H", "U###U", "H###H", "CCCCC")
        .aisle(" CSC ", " HHH ", " UUU ", " HHH ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(10)
            .or(autoAbilities(true, true, true, false, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('U', states(uniqueCasingState))
        .where('O', abilities(MultiblockAbility.MUFFLER_HATCH))
        .where('H', TraceabilityPredicates.coils())
        .where('P', TraceabilityPredicates.pumpCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.ZIRCONIUM_CARBIDE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.ALLOY_BLAST_SMELTER_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gregtech.machine.electric_blast_furnace.tooltip.1",
                               "gregtech.machine.electric_blast_furnace.tooltip.2",
                               "gregtech.machine.electric_blast_furnace.tooltip.3")
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addParallelInfo(UpgradeMode.PUMP_CASING, 16)
            addDurationInfo(UpgradeMode.WIRE_COIL, 350)
            addEnergyInfo(30)
        }
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(GTUtility.getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
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
        val temperatureInfo = TextComponentUtil.translationWithColor(TextFormatting.RED,
                                                                     TextFormattingUtil.formatNumbers(temperature.toLong()) + " K")
        textList.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY,
                                                            "gregtech.multiblock.blast_furnace.max_temperature",
                                                            temperatureInfo))
        return textList
    }

    override fun canBeDistinct() = true

    override fun hasMufflerMechanics() = true

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        return temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0)!!
    }

    override fun getCurrentTemperature() = this.temperature

    private inner class AlloyBlastSmelterRecipeLogic(mte: RecipeMapMultiblockController) : HeatingCoilRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= GTValues.V[GTValues.UV]) OverclockingLogic.PERFECT_DURATION_FACTOR else OverclockingLogic.STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -30%
            ocResult.setEut(max(1, (ocResult.eut() * 0.7).toLong()))

            // +350% / wire coil tier | D' = D / (1 + 3.5 * (T - 1)) = D / (3.5 * T - 2.5), where k = 3.5
            if (coilTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (3.5 * coilTier - 2.5)).toInt()))
        }

        override fun getParallelLimit() = 16 * pumpCasingTier

    }

}