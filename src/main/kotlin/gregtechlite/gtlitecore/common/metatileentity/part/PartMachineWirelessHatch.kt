package gregtechlite.gtlitecore.common.metatileentity.part

import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.drawable.Rectangle
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.screen.UISettings
import com.cleanroommc.modularui.utils.MouseData
import com.cleanroommc.modularui.value.sync.IntSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widgets.ButtonWidget
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.SliderWidget
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget
import gregtech.api.mui.GTGuiTextures
import gregtech.common.mui.widget.GTTextFieldWidget
import gregtech.api.capability.IEnergyContainer
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.mui.GTGuis
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart
import gregtechlite.gtlitecore.api.wireless.WirelessEnergyHolder
import gregtechlite.gtlitecore.api.wireless.WirelessNetworkManager
import gregtechlite.gtlitecore.api.wireless.WirelessRole
import gregtech.api.GTValues
import codechicken.lib.raytracer.CuboidRayTraceResult
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation

abstract class PartMachineWirelessHatch(
    id: ResourceLocation,
    tier: Int,
    initialAmperage: Int = 2
) : MetaTileEntityMultiblockPart(id, tier), IMultiblockAbilityPart<IEnergyContainer> {

    companion object {
        const val MAX_CHANNEL = 16
        private const val UPDATE_WIRELESS_CHANNEL = 998877
        const val MIN_AMPERAGE = 1
        const val MIN_BUFFER_DURATION = 5
        const val MAX_BUFFER_DURATION = 600
    }

    protected abstract val role: WirelessRole
    protected open fun getMaxAmperage(): Int = 256
    protected abstract val bufferCapacityMultiplier: Int
    protected abstract fun initializeEnergyContainer()

    val bufferCapacity: Long
        get() = GTValues.V[tier] * amperage * bufferDurationSeconds * 20L * bufferCapacityMultiplier

    var amperage: Int = 2
        private set
    var bufferDurationSeconds: Int = 10
        private set
    var overflowPool: Long = 0L
        private set

    private var channel: Int = 0
    private var priority: Int = 0
    protected var persistedBuffer: Long = 0L
    protected var wirelessHolder: WirelessEnergyHolder? = null
    protected lateinit var energyContainer: IEnergyContainer

    init {
        amperage = initialAmperage
        initializeEnergyContainer()
    }

    override fun initializeInventory() {
        super.initializeInventory()
        if (!::energyContainer.isInitialized) {
            initializeEnergyContainer()
        }
    }

    fun setAmperage(newAmperage: Int) {
        if (newAmperage == amperage || newAmperage !in MIN_AMPERAGE..getMaxAmperage()) return
        if (world.isRemote) return

        amperage = newAmperage

        // Handle internal container overflow from reduced capacity
        val containerEnergy = energyContainer.energyStored
        val containerCapacity = energyContainer.energyCapacity
        if (containerEnergy > containerCapacity) {
            val excess = containerEnergy - containerCapacity
            energyContainer.removeEnergy(excess)
            wirelessHolder?.let { holder ->
                val accepted = holder.addEnergy(excess)
                if (accepted < excess) {
                    overflowPool += (excess - accepted)
                }
            } ?: run {
                overflowPool += excess
            }
        }

        // Adjust holder buffer if capacity decreased
        val newCapacity = bufferCapacity
        wirelessHolder?.let { holder ->
            val newBuffer = minOf(holder.buffer, newCapacity)
            overflowPool += (holder.buffer - newBuffer)
            holder.buffer = newBuffer
            holder.capacity = newCapacity
        }

        markDirty()
        writeCustomData(UPDATE_WIRELESS_CHANNEL) { buf ->
            buf.writeInt(channel)
            buf.writeInt(priority)
            buf.writeInt(amperage)
        }
    }

    fun setBufferDuration(newDuration: Int) {
        if (newDuration == bufferDurationSeconds || newDuration !in MIN_BUFFER_DURATION..MAX_BUFFER_DURATION) return
        if (world.isRemote) return

        bufferDurationSeconds = newDuration

        // Adjust holder buffer if capacity decreased
        val newCapacity = bufferCapacity
        wirelessHolder?.let { holder ->
            val newBuffer = minOf(holder.buffer, newCapacity)
            overflowPool += (holder.buffer - newBuffer)
            holder.buffer = newBuffer
            holder.capacity = newCapacity
        }

        markDirty()
        writeCustomData(UPDATE_WIRELESS_CHANNEL) { buf ->
            buf.writeInt(channel)
            buf.writeInt(priority)
            buf.writeInt(amperage)
            buf.writeInt(bufferDurationSeconds)
        }
    }

    override fun onScrewdriverClick(playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitResult: CuboidRayTraceResult): Boolean {
        if (world.isRemote) return true
        val newAmp = if (playerIn.isSneaking) amperage / 2 else amperage * 2
        if (newAmp != amperage && newAmp in MIN_AMPERAGE..getMaxAmperage()) {
            setAmperage(newAmp)
        }
        return true
    }

    override fun update() {
        super.update()
        if (!world.isRemote) {
            processOverflowPool()
        }
    }

    private fun processOverflowPool() {
        if (overflowPool <= 0) return
        // 1. Try to transfer to internal energy container
        val containerSpace = energyContainer.energyCanBeInserted
        if (containerSpace > 0) {
            val toTransfer = minOf(overflowPool, containerSpace)
            energyContainer.addEnergy(toTransfer)
            overflowPool -= toTransfer
        }
        // 2. Try to transfer to wireless buffer
        if (overflowPool > 0) {
            wirelessHolder?.let { holder ->
                val bufferSpace = holder.capacity - holder.buffer
                if (bufferSpace > 0) {
                    val toTransfer = minOf(overflowPool, bufferSpace)
                    holder.buffer += toTransfer
                    overflowPool -= toTransfer
                }
            }
        }
        // 3. Decay 0.1%: floor(n * 0.999)
        if (overflowPool > 0) {
            overflowPool = (overflowPool * 0.999).toLong()
        }
    }

    protected fun updateWireless() {
        if (offsetTimer % 5 == 0L) {
            updateWirelessConnection()
        }
    }

    protected fun updateWirelessConnection() {
        val currentHolder = wirelessHolder

        if (currentHolder != null) {
            if (currentHolder.channel != channel || currentHolder.priority != priority) {
                WirelessNetworkManager.unregister(currentHolder)
                persistedBuffer = currentHolder.buffer
                val newHolder = createWirelessHolder()
                WirelessNetworkManager.register(newHolder)
                wirelessHolder = newHolder
            }
        } else if (channel != 0) {
            val newHolder = createWirelessHolder()
            WirelessNetworkManager.register(newHolder)
            wirelessHolder = newHolder
        }
    }

    protected fun createWirelessHolder(): WirelessEnergyHolder {
        return WirelessEnergyHolder(
            channel = channel,
            buffer = persistedBuffer,
            capacity = bufferCapacity,
            role = role,
            pos = pos,
            priority = priority
        )
    }

    fun getChannel(): Int = channel

    fun getPriority(): Int = priority

    fun setPriority(newPriority: Int) {
        if (priority != newPriority) {
            priority = newPriority
            if (!world.isRemote) {
                markDirty()
                writeCustomData(UPDATE_WIRELESS_CHANNEL) { buf ->
                    buf.writeInt(channel)
                    buf.writeInt(priority)
                }
            }
        }
    }

    fun setChannel(newChannel: Int) {
        if (channel != newChannel) {
            channel = newChannel
            if (!world.isRemote) {
                markDirty()
                writeCustomData(UPDATE_WIRELESS_CHANNEL) { buf ->
                    buf.writeInt(channel)
                    buf.writeInt(priority)
                }
            }
        }
    }

    fun getBufferEnergyStored(): Long = wirelessHolder?.buffer ?: 0L

    override fun openGUIOnRightClick(): Boolean = true

    override fun usesMui2(): Boolean = true

    @Suppress("UnstableApiUsage")
    override fun buildUI(guiData: PosGuiData, panelSyncManager: PanelSyncManager, settings: UISettings): ModularPanel {
        val channelSync = IntSyncValue(
            { this.channel },
            { newChannel -> setChannel(newChannel.coerceIn(0, MAX_CHANNEL)) }
        )
        val prioritySync = IntSyncValue(
            { this.priority },
            { newPriority -> setPriority(newPriority) }
        )
        val ampSync = IntSyncValue(
            { this.amperage },
            { newAmp -> setAmperage(newAmp) }
        )
        val durationSync = IntSyncValue(
            { this.bufferDurationSeconds },
            { newDuration -> setBufferDuration(newDuration.coerceIn(MIN_BUFFER_DURATION, MAX_BUFFER_DURATION)) }
        )

        return GTGuis.createPanel(this, 180, 110)
            .child(Flow.column().margin(7, 8).widthRel(1f).coverChildrenHeight()
                .child(IKey.lang(metaFullName).asWidget().marginBottom(4))
                .child(Flow.row().coverChildrenHeight().marginBottom(2).widthRel(1f)
                    .child(IKey.lang("gtlitecore.gui.wireless_hatch.channel_label").asWidget().width(90))
                    .child(TextFieldWidget()
                        .width(80)
                        .setNumbers(0, MAX_CHANNEL)
                        .value(channelSync)
                        .setTextColor(0xFFAAAA99.toInt())))
                .child(Flow.row().coverChildrenHeight().marginBottom(2).widthRel(1f)
                    .child(IKey.lang("gtlitecore.gui.wireless_hatch.priority_label").asWidget().width(90))
                    .child(TextFieldWidget()
                        .width(80)
                        .setNumbers(Int.MIN_VALUE, Int.MAX_VALUE)
                        .value(prioritySync)
                        .setTextColor(0xFFAAAA99.toInt())))
                .child(Flow.row().coverChildrenHeight().marginBottom(2).widthRel(1f)
                    .child(IKey.lang("gtlitecore.gui.wireless_hatch.amperage_label").asWidget().width(90))
                    .child(ButtonWidget()
                        .width(18)
                        .onMouseTapped { mouseButton ->
                            val factor = if (MouseData.create(mouseButton).shift) 4 else 2
                            ampSync.setIntValue((amperage / factor).coerceAtLeast(MIN_AMPERAGE), true, true)
                            true
                        }
                        .overlay(IKey.dynamic {
                            val shift = net.minecraft.client.gui.GuiScreen.isShiftKeyDown()
                            if (shift) "/4" else "/2"
                        }))
                    .child(GTTextFieldWidget()
                        .width(44)
                        .setPostFix("A")
                        .setNumbers(MIN_AMPERAGE, getMaxAmperage())
                        .setTextColor(0xFFAAAA99.toInt())
                        .value(ampSync)
                        .background(GTGuiTextures.DISPLAY))
                    .child(ButtonWidget()
                        .width(18)
                        .onMouseTapped { mouseButton ->
                            val factor = if (MouseData.create(mouseButton).shift) 4 else 2
                            ampSync.setIntValue((amperage * factor).coerceAtMost(getMaxAmperage()), true, true)
                            true
                        }
                        .overlay(IKey.dynamic {
                            val shift = net.minecraft.client.gui.GuiScreen.isShiftKeyDown()
                            if (shift) "x4" else "x2"
                        })))
                .child(Flow.row().coverChildrenHeight().marginBottom(2).widthRel(1f)
                    .child(IKey.lang("gtlitecore.gui.wireless_hatch.buffer_duration_label").asWidget().width(90))
                    .child(SliderWidget()
                        .width(50)
                        .bounds(MIN_BUFFER_DURATION.toDouble(), MAX_BUFFER_DURATION.toDouble())
                        .background(Rectangle().setColor(0xFF333333.toInt()))
                        .value(durationSync))
                    .child(GTTextFieldWidget()
                        .width(30)
                        .setPostFix { "s" }
                        .setNumbers(MIN_BUFFER_DURATION, MAX_BUFFER_DURATION)
                        .value(durationSync)
                        .setTextColor(0xFFAAAA99.toInt()))))
    }

    override fun writeInitialSyncData(buf: PacketBuffer) {
        super.writeInitialSyncData(buf)
        buf.writeInt(channel)
        buf.writeInt(priority)
        buf.writeInt(amperage)
        buf.writeInt(bufferDurationSeconds)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer) {
        super.receiveInitialSyncData(buf)
        channel = buf.readInt()
        priority = buf.readInt()
        amperage = buf.readInt()
        bufferDurationSeconds = buf.readInt()
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer) {
        super.receiveCustomData(dataId, buf)
        if (dataId == UPDATE_WIRELESS_CHANNEL) {
            channel = buf.readInt()
            priority = buf.readInt()
            if (buf.readableBytes() >= 4) {
                amperage = buf.readInt()
            }
            if (buf.readableBytes() >= 4) {
                bufferDurationSeconds = buf.readInt()
            }
        }
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(data)
        data.setInteger("wireless_channel", channel)
        data.setInteger("wireless_priority", priority)
        data.setInteger("wireless_amperage", amperage)
        data.setInteger("wireless_buffer_duration", bufferDurationSeconds)
        persistedBuffer = wirelessHolder?.buffer ?: persistedBuffer
        data.setLong("wireless_buffer", persistedBuffer)
        data.setLong("wireless_overflow_pool", overflowPool)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound) {
        super.readFromNBT(data)
        channel = data.getInteger("wireless_channel")
        priority = data.getInteger("wireless_priority")
        val savedAmperage = data.getInteger("wireless_amperage")
        if (savedAmperage > 0) {
            amperage = savedAmperage
        }
        val savedDuration = data.getInteger("wireless_buffer_duration")
        if (savedDuration > 0) {
            bufferDurationSeconds = savedDuration
        }
        persistedBuffer = data.getLong("wireless_buffer")
        overflowPool = data.getLong("wireless_overflow_pool")
    }

    override fun onRemoval() {
        wirelessHolder?.let { WirelessNetworkManager.unregister(it) }
        super.onRemoval()
    }
}
