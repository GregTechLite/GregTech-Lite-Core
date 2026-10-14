package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.value.sync.BooleanSyncValue
import com.cleanroommc.modularui.widgets.ToggleButton
import gregtech.api.GTValues.ULV
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.MultiblockAbility.DATA_ACCESS_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OPTICAL_DATA_RECEPTION
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.util.RelativeDirection
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtech.common.metatileentities.MetaTileEntities.ITEM_IMPORT_BUS
import gregtech.common.metatileentities.multi.electric.MetaTileEntityAssemblyLine
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch
import gregtechlite.gtlitecore.api.GTLiteAPI.CONVEYOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PROCESSOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.ROBOT_ARM_CASING_TIER
import gregtechlite.gtlitecore.api.capability.logic.AdvancedAssemblyLineRecipeLogic
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.metatileentity.sync.MetaTileEntitySyncer
import gregtechlite.gtlitecore.api.metatileentity.sync.SyncedMetaTileEntity
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.conveyorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.processorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.robotArmCasings
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.QUANTUM_ITEM_IMPORT_BUS
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockAdvancedAssemblyLine(id: ResourceLocation) : MetaTileEntityAssemblyLine(id), SyncedMetaTileEntity
{
    override val syncer: MetaTileEntitySyncer = MetaTileEntitySyncer(this)

    var isPipelineMode by syncer.syncedBoolean(true)
    var stuck by syncer.syncedBoolean(false)
    var activeSlices by syncer.syncedInt(0)

    internal var conveyorCasingTier = 0
    internal var robotArmCasingTier = 0
    internal var processorCasingTier = 0
    internal var tier = 0

    init
    {
        recipeMapWorkable = AdvancedAssemblyLineRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.NEUTRONIUM.state
        private val secondCasingState = GTCleanroomCasing.FILTER_CASING_STERILE.state
        private val thirdCasingState = GTMultiblockCasing.ASSEMBLY_CONTROL.state
        private val fourthCasingState = GTMultiblockCasing.ASSEMBLY_LINE_CASING.state
        private val pipeCasingState = BoilerCasing.POLYBENZIMIDAZOLE.state
        private val glassState = GTGlassCasing.FUSION_GLASS.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockAdvancedAssemblyLine(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        conveyorCasingTier = context.getAttributeOrDefault(CONVEYOR_CASING_TIER, 0)
        robotArmCasingTier = context.getAttributeOrDefault(ROBOT_ARM_CASING_TIER, 0)
        processorCasingTier = context.getAttributeOrDefault(PROCESSOR_CASING_TIER, 0)
        tier = minOf(conveyorCasingTier, robotArmCasingTier, processorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        conveyorCasingTier = 0
        robotArmCasingTier = 0
        processorCasingTier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
        .aisle(" P ", " P ", " P ", " F ")
        .aisle("CCC", "CCC", "CCC", " P ")
        .aisle("CCC", "SXC", "CCC", "CPC")
        .aisle("IKD", "GYR", "GLP", "ECE").setRepeatable(4, 16)
        .aisle("OCC", "CXC", "CPC", "FCF")
        .aisle("F F", "F F", "F F", "   ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(INPUT_ENERGY)
                    .setMaxGlobalLimited(3)
                    .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(0))
            .or(dataInputHatchPredicate()))
        .where('D', states(casingState)
            .or(fluidInputHatchPredicate()))
        .where('I', metaTileEntities(ITEM_IMPORT_BUS[ULV])
            .or(metaTileEntities(QUANTUM_ITEM_IMPORT_BUS[ULV])))
        .where('O', abilities(EXPORT_ITEMS)
            .addTooltips("gregtech.multiblock.pattern.location_end"))
        .where('E', states(secondCasingState))
        .where('K', states(thirdCasingState))
        .where('L', states(fourthCasingState))
        .where('P', states(pipeCasingState))
        .where('G', states(glassState))
        .where('F', frames(NaquadahAlloy))
        .where('X', processorCasings())
        .where('Y', conveyorCasings())
        .where('R', robotArmCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    private fun fluidInputHatchPredicate(): TraceabilityPredicate
    {
        if (ConfigHolder.machines.orderedFluidAssembly)
        {
            return metaTileEntities(*MultiblockAbility.REGISTRY[IMPORT_FLUIDS]!!
                                        .filter { it !is MetaTileEntityMultiFluidHatch }
                                        .toTypedArray()).setMaxGlobalLimited(4)
        }
        return abilities(IMPORT_FLUIDS).setPreviewCount(4)
    }

    private fun dataInputHatchPredicate(): TraceabilityPredicate
    {
        if (ConfigHolder.machines.enableResearch)
        {
            return abilities(DATA_ACCESS_HATCH, OPTICAL_DATA_RECEPTION).setExactLimit(1)
                .or(states(secondCasingState))
        }
        return states(secondCasingState)
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.NEUTRONIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.PCB_FACTORY_OVERLAY

    override fun createUIFactory(): MultiblockUIFactory = super.createUIFactory()
        .createFlexButton { _, _ ->
            val modeSync = BooleanSyncValue({ isPipelineMode }, { mode -> isPipelineMode = mode })
            return@createFlexButton ToggleButton()
                .size(18)
                .disableHoverBackground()
                .overlay(true, GTLiteMuiTextures.BUTTON_PIPELINE_MODE[1])
                .overlay(false, GTLiteMuiTextures.BUTTON_PIPELINE_MODE[0])
                .addTooltip(true, IKey.lang("gtlitecore.machine.advanced_assembly_line.pipeline.enabled"))
                .addTooltip(false, IKey.lang("gtlitecore.machine.advanced_assembly_line.pipeline.disabled"))
                .value(modeSync)
        }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.7"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.8"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.9"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.10"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.11"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_assembly_line.tooltip.12"))
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.laser_hatch"))

        if (ConfigHolder.machines.orderedAssembly && ConfigHolder.machines.orderedFluidAssembly)
            tooltip.add(I18n.format("gregtech.machine.assembly_line.tooltip_ordered_both"))
        else if (ConfigHolder.machines.orderedAssembly)
            tooltip.add(I18n.format("gregtech.machine.assembly_line.tooltip_ordered_items"))
        else if (ConfigHolder.machines.orderedFluidAssembly)
            tooltip.add(I18n.format("gregtech.machine.assembly_line.tooltip_ordered_fluids"))
    }

    override fun canBeDistinct(): Boolean = false
}
