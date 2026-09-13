package gregtechlite.gtlitecore.api.metatileentity.multiblock

import gregtech.api.capability.GregtechDataCodes.WORKABLE_ACTIVE
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_CONTROLLABLE
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_WORKABLE
import gregtech.api.capability.IControllable
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.IWorkable
import gregtech.api.capability.impl.EnergyContainerHandler
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.capability.ModuleProvider
import gregtechlite.gtlitecore.api.capability.ModuleReceiver
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.min
import kotlin.math.pow

abstract class ModuleMultiblockBase(metaTileEntityId: ResourceLocation,
                                    protected val tier: Int,
                                    protected val moduleTier: Int,
                                    protected val minCasingTier: Int)
    : MultiblockWithDisplayBase(metaTileEntityId), ModuleReceiver, IWorkable, IControllable
{
    override var moduleProvider: ModuleProvider? = null

    override val displayCountName: String
        get() = "$metaName.display_count"

    @JvmField
    protected var energyContainer: IEnergyContainer
    protected val energyConsumed = (4.0.pow((this.tier + 2).toDouble()) / 2).toLong()

    @JvmField
    protected var isActive: Boolean = false

    @JvmField
    protected var maxProgress: Int = 0

    @JvmField
    protected var progress: Int = 0

    val progressPercent: Int
        get() = ((1.0f * progress / maxProgress) * 100).toInt()

    @JvmField
    protected var isWorkingEnabled: Boolean = false

    /**
     * @param tier          The voltage tier of this mte.
     * @param moduleTier    The inner tier of the module.
     * @param minCasingTier The minimum casing tier of this module required, this is useful for some
     *                      tiered status predicate.
     */
    init
    {
        energyContainer = EnergyContainerHandler(this, (160008000L * 4.0.pow((tier - 9).toDouble())).toLong(),
            energyConsumed, 1, 0, 0)
    }

    override fun formStructure(context: PatternMatchContext?)
    {
        super.formStructure(context)
        initializeAbilities()
    }

    protected abstract fun initializeAbilities()

    override fun checkStructurePattern()
    {
        super.checkStructurePattern()
        moduleProvider?.casingTier?.also {
            if (it >= minCasingTier)
            {
                super.checkStructurePattern()
            }
        }
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        moduleProvider = null
    }

    abstract override fun createStructurePattern(): BlockPattern

    override fun updateFormedValid()
    {
        if (offsetTimer % SECOND == 0L && moduleProvider != null)
        {
            if (energyContainer.energyCapacity != energyContainer.energyStored
                && moduleProvider!!.subEnergyContainer!!.energyStored > energyConsumed * SECOND)
            {
                val maxModuleReceive = energyContainer.energyCapacity - energyContainer.energyStored
                val energyDrained = min(moduleProvider!!.subEnergyContainer!!.energyStored, maxModuleReceive)

                moduleProvider!!.subEnergyContainer!!.removeEnergy(energyDrained)
                energyContainer.addEnergy(energyDrained)
            }
        }
        else if (moduleProvider == null)
        {
            isWorkingEnabled = false
        }
    }

    fun getEnergyContainer(): IEnergyContainer?
        = if (moduleProvider?.subEnergyContainer == null) EnergyContainerHandler(this, 0, 0, 0, 0, 0) else energyContainer

    fun getCombinedEnergyContainer(): IEnergyContainer
    = if (moduleProvider?.subEnergyContainer == null) EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        else EnergyContainerList(listOf(moduleProvider!!.subEnergyContainer, energyContainer))

    override fun <T : Any> getCapability(capability: Capability<T>, side: EnumFacing?): T?
    {
        if (capability === CAPABILITY_WORKABLE) return CAPABILITY_WORKABLE.cast(this)
        if (capability === CAPABILITY_CONTROLLABLE) return CAPABILITY_CONTROLLABLE.cast(this)
        return super.getCapability(capability, side)
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setInteger("progressTime", progress)
        data.setInteger("maxProgress", maxProgress)
        data.setBoolean("isActive", isActive)
        data.setBoolean("isWorkingEnabled", isWorkingEnabled)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        progress = data.getInteger("progressTime")
        maxProgress = data.getInteger("maxProgress")
        isActive = data.getBoolean("isActive")
        isWorkingEnabled = data.getBoolean("isWorkingEnabled")
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeInt(progress)
        buf.writeInt(maxProgress)
        buf.writeBoolean(isActive)
        buf.writeBoolean(isWorkingEnabled)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        progress = buf.readInt()
        maxProgress = buf.readInt()
        isActive = buf.readBoolean()
        isWorkingEnabled = buf.readBoolean()
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        when (dataId)
        {
            WORKABLE_ACTIVE ->
            {
                setActive(buf.readBoolean())
                scheduleRenderUpdate()
            }
            WORKING_ENABLED ->
            {
                isWorkingEnabled = buf.readBoolean()
                scheduleRenderUpdate()
            }
        }
    }

    override fun isActive(): Boolean = isActive && isWorkingEnabled

    fun setActive(active: Boolean)
    {
        if (isActive != active)
        {
            isActive = active
            markDirty()
            if (world != null && !world.isRemote)
            {
                writeCustomData(WORKABLE_ACTIVE) { it.writeBoolean(active) }
            }
        }
    }

    override fun getProgress(): Int = progress

    protected fun drainEnergy(simulate: Boolean, energy: Long = energyContainer.inputVoltage): Boolean
    {
        val container = getCombinedEnergyContainer()
        val result = container.energyStored - energy
        if (result >= 0L && result <= container.energyCapacity)
        {
            if (!simulate) container.changeEnergy(-energy)
            return true
        }
        return false
    }

    @SideOnly(Side.CLIENT)
    abstract override fun getFrontOverlay(): ICubeRenderer

    @SideOnly(Side.CLIENT)
    abstract override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer?

    override fun hasMaintenanceMechanics() = false

    override fun sentWorkingDisabled()
    {
        isWorkingEnabled = false
    }

    override fun sentWorkingEnabled()
    {
        isWorkingEnabled = true
    }

    override fun isWorkingEnabled(): Boolean = this.isWorkingEnabled

    override fun setWorkingEnabled(workingEnabled: Boolean)
    {
        initializeAbilities()
        isWorkingEnabled = workingEnabled
        markDirty()
        if (world != null && !world.isRemote)
        {
            writeCustomData(WORKING_ENABLED) { it.writeBoolean(isWorkingEnabled) }
        }
    }

    override fun getMaxProgress() = maxProgress
}
