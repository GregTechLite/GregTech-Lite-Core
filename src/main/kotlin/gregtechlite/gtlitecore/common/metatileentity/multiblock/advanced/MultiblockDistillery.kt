package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.IDistillationTower
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.DistillationTowerLogicHandler
import gregtech.api.capability.impl.MultiblockRecipeLogic
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
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
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

// TODO FIXME
//  (1) When change this class to Kotlin version, then checkOutputSpaceFluids() will throws NPE when player running
//  recipes in Distillation Tower and the output fluids hatch has some liquids (not necessarily full).
//  (2) Seems will crash when switch mode of the machine.
class MultiblockDistillery(id: ResourceLocation) : MultiMapMultiblockController(id, arrayOf(DISTILLERY_RECIPES, DISTILLATION_RECIPES)), IDistillationTower
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

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockDistillery(metaTileEntityId)

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

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addOverclockInfo("gtlitecore.machine.large_distillery.tooltip.1")
            addParallelInfo(UpgradeMode.PUMP_CASING, 16)
            addDurationInfo(UpgradeMode.VOLTAGE_TIER, 350)
            addEnergyInfo(UpgradeMode.VOLTAGE_TIER, 40)
        }
    }

    override fun canBeDistinct(): Boolean = false

    private inner class LargeDistilleryRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun outputRecipeOutputs()
        {
            if (usesAdvancedHatchLogic())
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
                // We have already trimmed fluid outputs at this time.
                if (!metaTileEntity.canVoidRecipeFluidOutputs() &&
                    !workableHandler!!.applyFluidToOutputs(recipe.allFluidOutputs, false))
                {
                    this.isOutputsFull = true
                    return false
                }
                return true
            }
            return super.checkOutputSpaceFluids(recipe, exportFluids)
        }

        override fun getOutputTank(): IMultipleTankHandler?
        {
            if (usesAdvancedHatchLogic())
                return workableHandler?.fluidTanks
            return super.getOutputTank()
        }

        override fun getOverclockingDurationFactor(): Double
            = if ((maxVoltage >= V[UV] && usesAdvancedHatchLogic()) || !usesAdvancedHatchLogic()) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -40% / voltage tier
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - getTierByVoltage(maxVoltage) * 0.4)).toLong()))

            // +350% / voltage tier | D' = D / (1 + 3.5 * (T - 1)) = D / (3.5 * T - 2.5), where k = 3.5
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (3.5 * getTierByVoltage(maxVoltage) - 2.5)).toInt()))
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}
