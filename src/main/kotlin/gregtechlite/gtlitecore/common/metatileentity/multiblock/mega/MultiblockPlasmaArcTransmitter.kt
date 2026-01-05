package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.STANDARD_SPACETIME_FIELD_GEN_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.STANDARD_STABILIZATION_FIELD_GEN_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.STANDARD_TIME_ACCELERATION_FIELD_GEN_TIER
import gregtechlite.gtlitecore.api.capability.logic.ExtendedPowerMultiblockRecipeLogic
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.standardSpacetimeFieldGens
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.standardStabilizationFieldGens
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.standardTimeAccelerationFieldGens
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.PLASMA_ARC_TRANSMITTER_RECIPES
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max
import kotlin.math.pow

class MultiblockPlasmaArcTransmitter(id: ResourceLocation) : RecipeMapMultiblockController(id, PLASMA_ARC_TRANSMITTER_RECIPES)
{

    private var spacetimeFieldGenTier = 0
    private var timeAccelerationFieldGenTier = 0
    private var stabilizationFieldGenTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = PlasmaArcTransmitterRecipeLogic(this)
    }

    companion object
    {
        private val casingState = ScienceCasing.ULTIMATE_MOLECULAR_CASING.state
        private val secondCasingState = ScienceCasing.HOLLOW_CASING.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockPlasmaArcTransmitter(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        spacetimeFieldGenTier = context.getAttributeOrDefault(STANDARD_SPACETIME_FIELD_GEN_TIER, 0)
        timeAccelerationFieldGenTier = context.getAttributeOrDefault(STANDARD_TIME_ACCELERATION_FIELD_GEN_TIER, 0)
        stabilizationFieldGenTier = context.getAttributeOrDefault(STANDARD_STABILIZATION_FIELD_GEN_TIER, 0)
        tier = minOf(spacetimeFieldGenTier, timeAccelerationFieldGenTier, stabilizationFieldGenTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        spacetimeFieldGenTier = 0
        timeAccelerationFieldGenTier = 0
        stabilizationFieldGenTier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("  DDDDD  ", " DDAAADD ", "DDA   ADD", "DA     AD", "DA     AD", "DA     AD", "DDA   ADD", " DDAAADD ", "  DDDDD  ")
        .aisle("  DGGGD  ", " EABBBAE ", "DABFFFBAD", "GBF   FBG", "GBF   FBG", "GBF   FBG", "DABFFFBAD", " EABBBAE ", "  DGGGD  ")
        .aisle("  DGGGD  ", " EABBBAE ", "DABCCCBAD", "GBC   CBG", "GBC   CBG", "GBC   CBG", "DABCCCBAD", " EABBBAE ", "  DGGGD  ")
        .aisle("  DGGGD  ", " EABBBAE ", "DABFFFBAD", "GBF   FBG", "GBF   FBG", "GBF   FBG", "DABFFFBAD", " EABBBAE ", "  DGGGD  ")
        .aisle("  DDDDD  ", " DDAAADD ", "DDA   ADD", "DA     AD", "DA     AD", "DA     AD", "DDA   ADD", " DDAAADD ", "  DDDDD  ")
        .aisle("    D    ", "    D    ", "         ", "         ", "DD     DD", "         ", "         ", "    D    ", "    D    ")
        .aisle("         ", "    D    ", "         ", "         ", " D     D ", "         ", "         ", "    D    ", "         ")
        .aisle("         ", "    D    ", "    D    ", "         ", " DD   DD ", "         ", "    D    ", "    D    ", "         ")
        .aisle("         ", "         ", "    D    ", "   F F   ", "  D   D  ", "   F F   ", "    D    ", "         ", "         ")
        .aisle("         ", "         ", "    D    ", "   DDD   ", "  DDBDD  ", "   DDD   ", "    D    ", "         ", "         ")
        .aisle("         ", "         ", "         ", "   EEE   ", "   ESE   ", "   EEE   ", "         ", "         ", "         ")
        .where('S', selfPredicate())
        .where('A', standardSpacetimeFieldGens())
        .where('B', states(secondCasingState))
        .where('C', standardTimeAccelerationFieldGens())
        .where('D', states(casingState))
        .where('E', states(casingState)
            .setMinGlobalLimited(12)
            .or(autoAbilities(false, false, false, false, true, true, false)))
        .where('F', standardStabilizationFieldGens())
        .where('G', states(casingState)
            .setMinGlobalLimited(18)
            .or(abilities(INPUT_ENERGY)
                    .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                    .setPreviewCount(0))
            .or(autoAbilities(false, false, true, true, false, false, false)))
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.ULTIMATE_MOLECULAR_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.PLASMA_ARC_TRANSMITTER_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine("${RecipeMaps.ARC_FURNACE_RECIPES.localizedName}, ${RecipeMaps.ALLOY_SMELTER_RECIPES.localizedName}")
            addDescriptionLine("gtlitecore.machine.plasma_arc_transmitter.tooltip.1",
                               "gtlitecore.machine.plasma_arc_transmitter.tooltip.2",
                               "gtlitecore.machine.plasma_arc_transmitter.tooltip.3",
                               "gtlitecore.machine.plasma_arc_transmitter.tooltip.4")
            addOverclockInfo(OverclockMode.PERFECT_DOUBLE)
            addDescriptionLine(I18n.format("gtlitecore.tooltip.machine.parallel_mode")
                                       + I18n.format("gtlitecore.machine.plasma_arc_transmitter.tooltip.5"))
            addDurationInfo(UpgradeMode.TIME_ACCELERATION_FIELD_GEN, 3600)
            addEnergyInfo(UpgradeMode.STABILIZATION_FIELD_GEN, 10)
            addMaxVoltageInfo()
            addLaserHatchInfo()
        }
    }

    override fun canBeDistinct(): Boolean = true

    override fun hasMaintenanceMechanics(): Boolean = false

    private inner class PlasmaArcTransmitterRecipeLogic(mte: RecipeMapMultiblockController) : ExtendedPowerMultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = PERFECT_DURATION_FACTOR / 2

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -10% / stabilization field gen
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - max(0, stabilizationFieldGenTier) * 0.1)).toLong()))

            // +3600% / time acc field gen | D' = D / (1 + 36.0 * (T - 1.0)) = D / (36.0 * T - 35.0), where k = 36.0
            if (timeAccelerationFieldGenTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (36.0 * timeAccelerationFieldGenTier - 35.0)).toInt()))
        }

        override fun getParallelLimit(): Int = 1024 + spacetimeFieldGenTier.toDouble().pow(spacetimeFieldGenTier).toInt()

    }

}