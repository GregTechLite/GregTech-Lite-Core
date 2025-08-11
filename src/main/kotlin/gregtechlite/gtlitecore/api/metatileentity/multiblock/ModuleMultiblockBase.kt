package gregtechlite.gtlitecore.api.metatileentity.multiblock

import gregtech.api.capability.GregtechDataCodes
import gregtech.api.capability.GregtechTileCapabilities
import gregtech.api.capability.IControllable
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.IWorkable
import gregtech.api.capability.impl.EnergyContainerHandler
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
import kotlin.math.pow

abstract class ModuleMultiblockBase(metaTileEntityId: ResourceLocation,
                                    protected val tier: Int,
                                    protected val moduleTier: Int,
                                    protected val minCasingTier: Int) : MultiblockWithDisplayBase(metaTileEntityId), ModuleReceiver, IWorkable, IControllable
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
     * tiered status predicate.
     */
    init
    {
        this.energyContainer = EnergyContainerHandler(
            this,
            (160008000 * 4.0.pow((this.tier - 9).toDouble())).toLong(), this.energyConsumed,
            1, 0, 0
        )
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
                && moduleProvider!!.subEnergyContainer
                    !!.energyStored > energyConsumed * SECOND
            )
            {
                val simulate = energyContainer.energyCapacity - energyContainer.energyStored
                energyContainer.addEnergy(simulate)
            }
        }
        else if (moduleProvider == null)
        {
            isWorkingEnabled = false
        }
    }

    fun getEnergyContainer(): IEnergyContainer?
    {
        return if (moduleProvider?.subEnergyContainer == null)
        {
            EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        }
        else
        {
            energyContainer
        }
    }

    override fun <T : Any> getCapability(capability: Capability<T>, side: EnumFacing?): T?
    {
        if (capability === GregtechTileCapabilities.CAPABILITY_WORKABLE) return GregtechTileCapabilities.CAPABILITY_WORKABLE.cast(
            this
        )
        if (capability === GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(
            this
        )
        return super.getCapability<T>(capability, side)
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
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE)
        {
            setActive(buf.readBoolean())
            scheduleRenderUpdate()
        }
        else if (dataId == GregtechDataCodes.WORKING_ENABLED)
        {
            isWorkingEnabled = buf.readBoolean()
            scheduleRenderUpdate()
        }
    }

    override fun isActive(): Boolean
    {
        return isActive && isWorkingEnabled
    }

    fun setActive(active: Boolean)
    {
        if (isActive != active)
        {
            isActive = active
            markDirty()
            if (world != null && !world.isRemote) writeCustomData(
                GregtechDataCodes.WORKABLE_ACTIVE
            ) { buf: PacketBuffer? -> buf!!.writeBoolean(active) }
        }
    }

    override fun getProgress(): Int
    {
        return progress
    }

    protected fun drainEnergy(simulate: Boolean, energy: Long): Boolean
    {
        val result = energyContainer.energyStored - energy
        if (result >= 0L && result <= energyContainer.energyCapacity)
        {
            if (!simulate) energyContainer.changeEnergy(-energy)
            return true
        }
        return false
    }

    protected fun drainEnergy(simulate: Boolean): Boolean
    {
        val result = energyContainer.energyStored - energyContainer.inputVoltage
        if (result >= 0L && result <= energyContainer.energyCapacity)
        {
            if (!simulate) energyContainer.changeEnergy(-energyContainer.inputVoltage)
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
        this.isWorkingEnabled = workingEnabled
        markDirty()
        if (world?.isRemote == false) writeCustomData(GregtechDataCodes.WORKING_ENABLED)
        {
            it.writeBoolean(this.isWorkingEnabled)
        }
    }

    override fun getMaxProgress() = maxProgress

}
