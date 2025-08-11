package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
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
import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.HEATING_COIL_STATS
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ALLOY_BLAST_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.pow

class MultiblockAlloyBlastSmelter(id: ResourceLocation)
    : RecipeMapMultiblockController(id, ALLOY_BLAST_RECIPES), IHeatingCoil
{

    private var pumpCasingTier = 0
    private var coilTier = 0
    private var tier = 0

    private var temperature = 0

    init
    {
        this.recipeMapWorkable = AlloyBlastSmelterRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.ZIRCONIUM_CARBIDE.state

        private val pipeCasingState
            get() = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state

        private val uniqueCasingState
            get() = ActiveUniqueCasing.HEAT_VENT.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockAlloyBlastSmelter(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)

        val coilType = context.get<Any>(HEATING_COIL_STATS)
        if (coilType is IHeatingCoilBlockStats)
        {
            this.coilTier = coilType.tier
            this.temperature = coilType.coilTemperature
        }
        else
        {
            this.coilTier = BlockWireCoil.CoilType.CUPRONICKEL.tier
            this.temperature = BlockWireCoil.CoilType.CUPRONICKEL.coilTemperature
        }

        this.tier = minOf(pumpCasingTier, coilTier)
        this.temperature += 100 * max(0, getTierByVoltage(energyContainer.inputVoltage) - MV)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.pumpCasingTier = 0
        this.temperature = 0
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
        .where('O', abilities(MUFFLER_HATCH))
        .where('H', heatingCoils())
        .where('P', pumpCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.ZIRCONIUM_CARBIDE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.ALLOY_BLAST_SMELTER_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.4"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"))
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
            formatNumbers(temperature.toLong()) + " K")
        textList.add(translationWithColor(TextFormatting.GRAY,
            "gregtech.multiblock.blast_furnace.max_temperature", temperatureInfo))
        return textList
    }

    override fun canBeDistinct() = true

    override fun hasMufflerMechanics() = true

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        return temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0)!!
    }

    override fun getCurrentTemperature() = this.temperature

    private inner class AlloyBlastSmelterRecipeLogic(mte: RecipeMapMultiblockController?) : HeatingCoilRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(coilTier)).toInt())
        }

        override fun getParallelLimit() = 16 * pumpCasingTier

    }

}
