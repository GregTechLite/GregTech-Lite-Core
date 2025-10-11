package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.FALLBACK
import gregtech.api.GTValues.ULV
import gregtech.api.block.VariantBlock
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.*
import gregtech.api.recipes.Recipe
import gregtech.api.util.BlockInfo
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityItemBus
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.GTLiteAPI.CRUCIBLE_STATS
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.ROBOT_ARM_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getTierOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.robotArmCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.Crucible
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.Predicate
import kotlin.math.floor
import kotlin.math.pow

class MultiblockNanoscaleFabricator(id: ResourceLocation)
    : RecipeMapMultiblockController(id, MOLECULAR_BEAM_RECIPES)
{

    private var emitterCasingTier = 0
    private var robotArmCasingTier = 0
    private var temperature = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = NanoscaleFabricatorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.TITANIUM_TUNGSTEN_CARBIDE.state
        private val secondCasingState = MetalCasing.HSLA_STEEL.state
        private val thirdCasingState = MultiblockCasing.SUBSTRATE_CASING.state
        private val glassState = GTGlassCasing.LAMINATED_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockNanoscaleFabricator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        robotArmCasingTier = context.getAttributeOrDefault(ROBOT_ARM_CASING_TIER, 0)

        val pseudoCrucibleTier = context.getTierOrDefault(CRUCIBLE_STATS, 0)
        tier = minOf(emitterCasingTier, robotArmCasingTier, pseudoCrucibleTier)

        // Initialized crucible temperature and amount info.
        val crucibleAmount = context.getInt("CrucibleAmount")
        temperature = if (crucibleAmount != 0) context.getInt("Temperature") / crucibleAmount else 0
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        robotArmCasingTier = 0
        temperature = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
        .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XXXXX  ")
        .aisle(" XXXXXXX ", " X#####X ", " X#####X ", " XXGGGXX ")
        .aisle("TXXTTTXXT", "TX##Z##XT", "TX#####XT", " XGGGGGX ")
        .aisle("TXXTITXXT", "IR#ZAZ#RI", "CX#####XC", " XGGGGGX ")
        .aisle("TXXTTTXXT", "TX##Z##XT", "TX#####XT", " XGGGGGX ")
        .aisle(" XXXXXXX ", " X#####X ", " X#####X ", " XXGGGXX ")
        .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XXSXX  ")
        .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
        .where('S', selfPredicate())
        .where('X', states(casingState)
            .setMinGlobalLimited(84)
            .or(autoAbilities(true, true, false, true, true, false, false)))
        .where('T', states(secondCasingState)
            .setMinGlobalLimited(36))
        .where('G', states(glassState))
        .where('I', metaTileEntities(MetaTileEntities.ITEM_IMPORT_BUS[ULV])
            .or(metaTileEntities(GTLiteMetaTileEntities.HUGE_ITEM_IMPORT_BUS[ULV]))
            .or(states(secondCasingState)))
        .where('C', states(secondCasingState)
            .or(cruciblePredicate()))
        .where('A', states(thirdCasingState))
        .where('Z', emitterCasings())
        .where('R', robotArmCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    private fun cruciblePredicate() = TraceabilityPredicate(Predicate { blockWorldState: BlockWorldState ->
        val state: IBlockState = blockWorldState.blockState
        val block: Block = state.block
        if (block is VariantBlock<*>)
        {
            val storedTemperature = blockWorldState.matchContext.getOrPut("Temperature", 0)
            blockWorldState.matchContext["Temperature"] = GTLiteBlocks.CRUCIBLE.getState(state).temperature + storedTemperature
            val storedCrucibleAmount = blockWorldState.matchContext.getOrPut("CrucibleAmount", 0)
            blockWorldState.matchContext["CrucibleAmount"] = 1 + storedCrucibleAmount
            return@Predicate true
        }
            return@Predicate false
        }) { Crucible.entries
                .sortedBy { it.temperature }
                .map { BlockInfo(GTLiteBlocks.CRUCIBLE.getState(it), null) }
                .toTypedArray()
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer
    {
        if (sourcePart is MetaTileEntityItemBus && sourcePart.exportItems.getSlots() == 0)
            return GTLiteOverlays.HSLA_STEEL_CASING
        return GTLiteOverlays.TITANIUM_TUNGSTEN_CARBIDE_CASING
    }

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.NANOSCALE_FABRICATOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine("NFab")
            addDescriptionLine(true,
                               "gtlitecore.machine.nanoscale_fabricator.tooltip.1",
                               "gtlitecore.machine.nanoscale_fabricator.tooltip.2",
                               "gtlitecore.machine.nanoscale_fabricator.tooltip.3")
            overclockInfo(FALLBACK)
            durationInfo(UpgradeType.EMITTER_CASING, 80)
            parallelInfo(UpgradeType.ROBOT_ARM_CASING, 4)
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
                        syncer.syncInt(temperature).toLong(), "K")
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                        "gtlitecore.machine.nanoscale_fabricator.average_temperature", temperatureKey))

                    val lowerTemperatureKey = KeyUtil.number(TextFormatting.RED,
                        syncer.syncInt(temperature).toLong() - 250L, "K")

                    val upperTemperatureKey = KeyUtil.number(TextFormatting.RED,
                        syncer.syncInt(temperature).toLong() + 250L, "K")

                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                        "gtlitecore.machine.nanoscale_fabricator.temperature_range",
                        lowerTemperatureKey, upperTemperatureKey))
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addRecipeOutputLine(recipeMapWorkable)
    }

    override fun canBeDistinct() = false

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    private inner class NanoscaleFabricatorRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity, true)
    {

        override fun checkRecipe(recipe: Recipe): Boolean
        {
            val delta = temperature - recipe.getProperty(GTLiteRecipeProperties.NO_COIL_TEMPERATURE, 0)!!
            return (delta in 1..249)
        }

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(emitterCasingTier)).toInt())
        }

        override fun getParallelLimit() = 4 * robotArmCasingTier

    }

}
