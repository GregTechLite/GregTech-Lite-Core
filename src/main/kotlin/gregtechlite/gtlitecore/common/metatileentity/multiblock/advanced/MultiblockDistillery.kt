package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.IDistillationTower
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.DistillationTowerLogicHandler
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTTransferUtils.addItemsToItemHandler
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.RIGHT
import gregtech.api.util.RelativeDirection.UP
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.Function
import kotlin.math.max

// The structure pattern is identical for both recipe maps, but the fluid output handling
// depends on the active mode: the advanced Distillation Tower hatch logic is only initialized
// while the structure is formed in Distillation Tower mode. When the mode is switched,
// MultiMapMultiblockController.setRecipeMapIndex() immediately calls recipeMapWorkable.forceRecipeRecheck(),
// which validates recipes against the NEW mode while the DistillationTowerLogicHandler is still in the
// OLD mode's state. This used to crash with an NPE in DistillationTowerLogicHandler.applyFluidToOutputs(),
// both in checkOutputSpaceFluids() during the switch and in outputRecipeOutputs() when the old recipe
// completed afterwards. To fix this, the recipe checks are made null-safe while the handler is not
// initialized, the output tank falls back to the standard tanks, and the handler is re-initialized after
// the mode change so it always matches the newly selected recipe map. An in-progress recipe is left
// running, so switching modes does not cancel it or lose its inputs.
class MultiblockDistillery(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(DISTILLERY_RECIPES, DISTILLATION_RECIPES)), IDistillationTower
{

    private var workableHandler: DistillationTowerLogicHandler?

    private var casingTier = 0

    companion object
    {
        private val casingState = MetalCasing.SILICON_CARBIDE.state
        private val pipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
    }

    init
    {
        recipeMapWorkable = LargeDistilleryRecipeLogic(this)
        workableHandler = DistillationTowerLogicHandler(this)
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockDistillery(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        if (structurePattern == null) return
        if (usesAdvancedHatchLogic())
        {
            workableHandler?.determineLayerCount(structurePattern!!)
            workableHandler?.determineOrderedFluidOutputs()
        }
        casingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        if (workableHandler != null)
            workableHandler!!.invalidate()
        casingTier = 0
    }

    /**
     * The base implementation immediately calls recipeMapWorkable.forceRecipeRecheck() after changing the index,
     * which re-validates recipes against the NEW recipe map while the [DistillationTowerLogicHandler] is still in
     * the OLD mode's state (the null-safe [LargeDistilleryRecipeLogic.checkOutputSpaceFluids] rejects that check).
     * Afterwards the handler is re-initialized so it matches the newly selected recipe map. An in-progress recipe
     * is deliberately left running (see [LargeDistilleryRecipeLogic.forceRecipeRecheck]): it finishes under the
     * new mode's output routing instead of being canceled, so no inputs are lost.
     */
    override fun setRecipeMapIndex(index: Int)
    {
        val changed = index != recipeMapIndex
        super.setRecipeMapIndex(index)
        if (changed && isStructureFormed && !world.isRemote && usesAdvancedHatchLogic())
        {
            val pattern = structurePattern ?: return
            workableHandler?.determineLayerCount(pattern)
            workableHandler?.determineOrderedFluidOutputs()
        }
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(RIGHT, FRONT, UP)
        .aisle("DSD", "DQD", "DDD")
        .aisle("CCC", "CPC", "CCC").setRepeatable(1, 11)
        .aisle("CCC", "CCC", "CCC")
        .where('S', selfPredicate())
        .where('D', states(casingState)
            .or(abilities(IMPORT_ITEMS)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(0))
            .or(abilities(EXPORT_ITEMS)
                    .setMaxGlobalLimited(1))
            .or(abilities(INPUT_ENERGY)
                    .setMinGlobalLimited(1)
                    .setMaxGlobalLimited(4))
            .or(abilities(IMPORT_FLUIDS)
                    .setExactLimit(1)))
        .where('C', states(casingState)
            .or(abilities(EXPORT_FLUIDS)
                    .setMaxLayerLimited(1, 1))
            .or(autoAbilities(true, false)))
        .where('P', states(pipeCasingState))
        .where('Q', pumpCasings())
        .build()

    // @formatter:on

    /**
     * Used if Multiblock Part Abilities need to be sorted a certain way, like Distillation
     * Tower and Assembly Line.
     *
     * There will be *consequences* if this is changed. Make sure to set the logic (workable)
     * handler to one with a property overriden.
     *
     * @see DistillationTowerLogicHandler.determineOrderedFluidOutputs
     */
    override fun multiblockPartSorter(): Function<BlockPos, Int>
    {
        return UP.getSorter(getFrontFacing(), getUpwardsFacing(), isFlipped())
    }

    /**
     * Whether this Multiblock Structure can be rotated or face upwards.
     *
     * There will be *consequences* if this is changed. Make sure to set the logic (workable)
     * handler to one with a property overriden.
     *
     * @see DistillationTowerLogicHandler.determineOrderedFluidOutputs
     */
    override fun allowsExtendedFacing() = false

    override fun allowSameFluidFillForOutputs() = !usesAdvancedHatchLogic()

    override fun getFluidOutputLimit(): Int
    {
        return if (workableHandler != null && usesAdvancedHatchLogic()) workableHandler!!.layerCount
                else super.getFluidOutputLimit()
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.SILICON_CARBIDE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.DISTILLATION_TOWER_OVERLAY

    /**
     * When current RecipeMap is Distillation Tower, then enabled special logic for
     * the hatches. Otherwise, used common hatches logic.
     *
     * @see DistillationTowerLogicHandler
     */
    private fun usesAdvancedHatchLogic() = currentRecipeMap === DISTILLATION_RECIPES

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addOverclockInfo("gtlitecore.machine.large_distillery.tooltip.1")
            addParallelInfo(16, UpgradeMode.PUMP_CASING)
            addDurationInfo(350, UpgradeMode.VOLTAGE_TIER)
            addEnergyInfo(40)
        }
    }

    override fun canBeDistinct(): Boolean = false

    private inner class LargeDistilleryRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        /**
         * Called by MultiMapMultiblockController.setRecipeMapIndex() when the recipe map is switched.
         * If a recipe is in progress, do not restart it (that would discard the progress and consume the
         * inputs again): only invalidate the cached recipe, so that once the current recipe finishes, the
         * next search picks up recipes from the newly selected recipe map.
         */
        override fun forceRecipeRecheck()
        {
            if (progress > 0)
            {
                previousRecipe = null
                return
            }
            super.forceRecipeRecheck()
        }

        override fun outputRecipeOutputs()
        {
            if (usesAdvancedHatchLogic() && workableHandler?.orderedFluidOutputs != null)
            {
                addItemsToItemHandler(getOutputInventory(), false, itemOutputs)
                workableHandler?.applyFluidToOutputs(fluidOutputs, true)
            }
            else
            {
                super.outputRecipeOutputs()
            }
        }

        override fun checkOutputSpaceFluids(recipe: Recipe,
                                            exportFluids: IMultipleTankHandler): Boolean
        {
            if (usesAdvancedHatchLogic())
            {
                val handler = workableHandler
                if (handler?.orderedFluidOutputs != null)
                {
                    // We have already trimmed fluid outputs at this time.
                    if (!metaTileEntity.canVoidRecipeFluidOutputs() &&
                        !handler.applyFluidToOutputs(recipe.allFluidOutputs, false))
                    {
                        this.isOutputsFull = true
                        return false
                    }
                    return true
                }
                // The advanced hatch logic is not initialized yet, e.g. the recipe map was just switched and the
                // handler has not been re-initialized. Reject the check so forceRecipeRecheck() does not crash; the
                // handler is re-initialized right after the mode switch and the recipe is picked up on the next search.
                return false
            }
            return super.checkOutputSpaceFluids(recipe, exportFluids)
        }

        override fun getOutputTank(): IMultipleTankHandler?
        {
            if (usesAdvancedHatchLogic())
            {
                val advancedTanks = workableHandler?.fluidTanks
                if (advancedTanks != null)
                    return advancedTanks
                // The advanced hatch logic is not initialized yet, e.g. the recipe map was just switched and the
                // handler has not been re-initialized. Fall back to the standard output tanks so callers never receive
                // null (the base checkOutputSpaceFluids/outputRecipeOutputs implementations do not handle null).
                return super.getOutputTank()
            }
            return super.getOutputTank()
        }

        override fun getOverclockingDurationFactor(): Double = if ((maxVoltage >= V[UV] && usesAdvancedHatchLogic())
            || !usesAdvancedHatchLogic()) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -40%
            ocResult.setEut(max(1, (ocResult.eut() * 0.6).toLong()))

            // +350% / voltage tier | D' = D / (1 + 3.5 * (T - 1)) = D / (3.5 * T - 2.5), where k = 3.5
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (3.5 * getTierByVoltage(maxVoltage) - 2.5)).toInt()))
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}
