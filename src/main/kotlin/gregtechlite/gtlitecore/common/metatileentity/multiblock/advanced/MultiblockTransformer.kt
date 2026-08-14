package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_CONTROLLABLE
import gregtech.api.capability.IControllable
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.metatileentity.sync.MetaTileEntitySyncer
import gregtechlite.gtlitecore.api.metatileentity.sync.SyncedMetaTileEntity
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockTransformer(id: ResourceLocation) : MultiblockWithDisplayBase(id), IControllable, SyncedMetaTileEntity
{
    override val syncer: MetaTileEntitySyncer = MetaTileEntitySyncer(this)

    private var _isWorkingEnabled by syncer.syncedBoolean()
    private var _isActive by syncer.syncedBoolean()

    private var inputEnergy: IEnergyContainer = EnergyContainerList(arrayListOf())
    private var outputEnergy: IEnergyContainer = EnergyContainerList(arrayListOf())

    private var averageIOLastSec: Long = 0L
    private var netIOLastSec: Long = 0L

    companion object
    {
        private val casingState = GTMetalCasing.ALUMINIUM_FROSTPROOF.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockTransformer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))

        val outputEnergy = ArrayList(getAbilities(OUTPUT_ENERGY))
        outputEnergy.addAll(getAbilities(SUBSTATION_OUTPUT_ENERGY))

        if (inputEnergy.isEmpty() || outputEnergy.isEmpty())
            invalidateStructure()

        this.inputEnergy = EnergyContainerList(inputEnergy)
        this.outputEnergy = EnergyContainerList(outputEnergy)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        inputEnergy = EnergyContainerList(arrayListOf())
        outputEnergy = EnergyContainerList(arrayListOf())
        setActive(false)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CSC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(INPUT_ENERGY)
                    .setPreviewCount(1))
            .or(abilities(OUTPUT_ENERGY)
                    .setPreviewCount(1))
            .or(abilities(SUBSTATION_INPUT_ENERGY)
                    .setPreviewCount(0))
            .or(abilities(SUBSTATION_OUTPUT_ENERGY)
                    .setPreviewCount(0)))
        .build()

    // @formatter:on

    override fun updateFormedValid()
    {
        if (!world.isRemote)
        {
            if (offsetTimer % (1 * SECOND) == 0L)
            {
                averageIOLastSec = netIOLastSec / SECOND
                netIOLastSec = 0
            }
            if (this@MultiblockTransformer.isWorkingEnabled())
            {
                val drainableEnergy = inputEnergy.energyStored
                val totalDrainedEnergy = outputEnergy.changeEnergy(drainableEnergy)
                inputEnergy.removeEnergy(totalDrainedEnergy)
                netIOLastSec += totalDrainedEnergy
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.FROST_PROOF_CASING

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        frontOverlay.renderOrientedState(renderState, translation, pipeline, frontFacing, _isActive, _isWorkingEnabled)
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(true, _isActive)
            .setWorkingStatusKeys("gregtech.multiblock.idling",
                                  "gregtech.multiblock.idling",
                                  "gregtech.machine.active_transformer.routing")
            .addCustom { tooltip, syncer ->
                if (isStructureFormed)
                {

                    val maxInputKey = KeyUtil.number(TextFormatting.WHITE,
                        syncer.syncLong(this.inputEnergy.inputVoltage * this.inputEnergy.inputAmperage), " EU/t")
                    tooltip.add(KeyUtil.lang(TextFormatting.GREEN,
                                             "gregtech.multiblock.active_transformer.max_in", maxInputKey))

                    val maxOutputKey = KeyUtil.number(TextFormatting.WHITE,
                        syncer.syncLong(this.outputEnergy.outputVoltage * this.outputEnergy.outputAmperage), " EU/t")
                    tooltip.add(KeyUtil.lang(TextFormatting.RED,
                                             "gregtech.multiblock.active_transformer.max_out", maxOutputKey))

                    val avgIOKey = KeyUtil.number(TextFormatting.WHITE,
                        syncer.syncLong(averageIOLastSec), " EU/t")
                    tooltip.add(KeyUtil.lang(TextFormatting.AQUA,
                                             "gregtech.multiblock.active_transformer.average_io", avgIOKey))
                }
            }
            .addWorkingStatusLine()
    }

    override fun hasMaintenanceMechanics(): Boolean = false

    override fun shouldShowVoidingModeButton(): Boolean = false

    override fun isWorkingEnabled(): Boolean = _isWorkingEnabled

    override fun setWorkingEnabled(workingStatus: Boolean)
    {
        _isWorkingEnabled = workingStatus
    }

    override fun isActive(): Boolean = super.isActive() && _isWorkingEnabled

    fun setActive(active: Boolean)
    {
        _isActive = active
    }

    override fun <T : Any?> getCapability(capability: Capability<T>, side: EnumFacing?): T?
    {
        if (capability === CAPABILITY_CONTROLLABLE) return CAPABILITY_CONTROLLABLE.cast(this)
        return super.getCapability(capability, side)
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gtlitecore.machine.large_transformer.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_transformer.tooltip.2"))
    }
}