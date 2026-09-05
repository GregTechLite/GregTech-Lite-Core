package gregtechlite.gtlitecore.common.metatileentity.part

import appeng.api.AEApi
import appeng.api.config.AccessRestriction
import appeng.api.config.Actionable
import appeng.api.networking.GridFlags
import appeng.api.networking.IGridNode
import appeng.api.networking.events.MENetworkCellArrayUpdate
import appeng.api.networking.security.IActionHost
import appeng.api.networking.security.IActionSource
import appeng.api.storage.ICellContainer
import appeng.api.storage.ICellInventory
import appeng.api.storage.IMEInventoryHandler
import appeng.api.storage.IStorageChannel
import appeng.api.storage.channels.IFluidStorageChannel
import appeng.api.storage.channels.IItemStorageChannel
import appeng.api.storage.data.IAEFluidStack
import appeng.api.storage.data.IAEItemStack
import appeng.api.storage.data.IItemList
import appeng.api.util.AECableType
import appeng.api.util.AEPartLocation
import appeng.api.util.DimensionalCoord
import appeng.fluids.util.AEFluidStack
import appeng.me.helpers.AENetworkProxy
import appeng.me.helpers.IGridProxyable
import appeng.util.item.AEItemStack
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.screen.UISettings
import com.cleanroommc.modularui.value.sync.IntSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget
import gregtech.api.capability.GregtechDataCodes
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.mui.GTGuis
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.longValue
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.metatileentity.multiblock.storage.MultiblockQuantumChest
import gregtechlite.gtlitecore.common.metatileentity.multiblock.storage.MultiblockQuantumTank
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.math.BigInteger
import java.util.EnumSet

// TODO: If it not cause some performance problem, use new sync api (waiting for test).
class PartMachineQuantumAccessHatch(id: ResourceLocation, tier: Int)
    : MetaTileEntityMultiblockPart(id, tier), IGridProxyable, IActionHost, ICellContainer
{
    private val itemChannel: IItemStorageChannel
        get() = AEApi.instance().storage().getStorageChannel(IItemStorageChannel::class.java)
    private val fluidChannel: IFluidStorageChannel
        get() = AEApi.instance().storage().getStorageChannel(IFluidStorageChannel::class.java)

    private val itemHandler = QuantumStorageItemHandler()
    private val fluidHandler = QuantumStorageFluidHandler()

    private var _priority: Int = 0
    private var readMode: AccessRestriction = AccessRestriction.READ_WRITE
    private var sticky: Boolean = false

    private var aeProxy: AENetworkProxy? = null
    private var lastActive: Boolean = false
    private var lastTotal: BigInteger = BigInteger.ZERO
    private var lastDistinct: Int = 0

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = PartMachineQuantumAccessHatch(metaTileEntityId, tier)

    override fun update()
    {
        super.update()
        if (!world.isRemote && offsetTimer % (1 * SECOND) == 0L)
        {
            val controller = getController()
            val totalCapacity = when (controller)
            {
                is MultiblockQuantumChest -> if (controller.isWorkingEnabled) controller.itemStorage().totalStored() else BigInteger.ZERO
                is MultiblockQuantumTank  -> if (controller.isWorkingEnabled) controller.fluidStorage().totalStored() else BigInteger.ZERO
                else                      -> BigInteger.ZERO
            }
            val distinctSlots = when (controller)
            {
                is MultiblockQuantumChest -> if (controller.isWorkingEnabled) controller.itemStorage().distinctSlots() else 0
                is MultiblockQuantumTank  -> if (controller.isWorkingEnabled) controller.fluidStorage().distinctSlots() else 0
                else                      -> 0
            }
            if (totalCapacity != lastTotal || distinctSlots != lastDistinct)
            {
                notifyME()
                lastTotal = totalCapacity
                lastDistinct = distinctSlots
            }
        }

        val isOnline = getProxy()?.let { it.isActive && it.isPowered } == true
        if (isOnline != lastActive)
        {
            if (!lastActive) notifyME()
            lastActive = isOnline
            writeCustomData(GregtechDataCodes.UPDATE_ONLINE_STATUS) { it.writeBoolean(isOnline) }
        }
    }

    fun notifyME()
    {
        val node = getProxy()?.node ?: return
        node.grid.postEvent(MENetworkCellArrayUpdate())
    }

    override fun getCellArray(channel: IStorageChannel<*>): List<IMEInventoryHandler<*>>
    {
        if (channel === itemChannel)
            return if (controller is MultiblockQuantumChest) listOf(itemHandler) else emptyList()
        if (channel === fluidChannel)
            return if (controller is MultiblockQuantumTank) listOf(fluidHandler) else emptyList()
        return mutableListOf()
    }

    override fun blinkCell(i: Int) {}

    override fun getPriority(): Int = _priority

    override fun saveChanges(cellInventory: ICellInventory<*>?) {}

    override fun getLocation(): DimensionalCoord = DimensionalCoord(world, pos)

    override fun getCableConnectionType(part: AEPartLocation): AECableType
        = if (part.facing != frontFacing) AECableType.NONE else AECableType.SMART

    private fun createProxy(): AENetworkProxy
    {
        val proxy = AENetworkProxy(this, "mte_proxy", stackForm, true)
        proxy.setFlags(GridFlags.REQUIRE_CHANNEL)
        proxy.idlePowerUsage = 1.0
        proxy.setValidSides(EnumSet.of(frontFacing))
        return proxy
    }

    override fun getProxy(): AENetworkProxy?
    {
        if (aeProxy == null) aeProxy = createProxy()
        else if (!aeProxy!!.isReady && world != null) aeProxy!!.onReady()
        return aeProxy
    }

    override fun getGridNode(aePartLocation: AEPartLocation): IGridNode? = getProxy()?.node

    override fun getActionableNode(): IGridNode = getProxy()!!.node

    override fun securityBreak() {}

    override fun setFrontFacing(frontFacing: EnumFacing)
    {
        super.setFrontFacing(frontFacing)
        getProxy()?.setValidSides(EnumSet.of(frontFacing))
    }

    private fun setPriority(newPriority: Int)
    {
        if (_priority == newPriority) return
        _priority = newPriority
        if (!world.isRemote)
        {
            notifyME()
            markDirty()
        }
    }

    private fun setReadMode(newMode: Int)
    {
        if (readMode == AccessRestriction.entries.toTypedArray()[newMode]) return
        readMode = AccessRestriction.entries.toTypedArray()[newMode]
        if (!world.isRemote)
        {
            notifyME()
            markDirty()
        }
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setInteger("priority", _priority)
        data.setInteger("readMode", readMode.ordinal)
        data.setBoolean("sticky", sticky)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        _priority = data.getInteger("priority")
        readMode = AccessRestriction.entries.toTypedArray()[data.getInteger("readMode")]
        sticky = data.getBoolean("sticky")
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeInt(_priority)
        buf.writeByte(readMode.ordinal)
        buf.writeBoolean(sticky)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        _priority = buf.readInt()
        readMode = AccessRestriction.entries.toTypedArray()[buf.readByte().toInt()]
        sticky = buf.readBoolean()
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GregtechDataCodes.UPDATE_ONLINE_STATUS)
        {
            lastActive = buf.readBoolean()
        }
    }

    @Suppress("UnstableApiUsage")
    override fun usesMui2(): Boolean = true

    override fun buildUI(guiData: PosGuiData, guiSyncManager: PanelSyncManager, uiSettings: UISettings): ModularPanel
    {
        val prioritySync = IntSyncValue({ _priority }, { priority -> setPriority(priority) })
        return GTGuis.createPanel(this, 150, 76)
            .child(IKey.lang(metaFullName).asWidget()
                       .pos(5, 5))
            .child(IKey.dynamic {
                return@dynamic if (lastActive) I18n.format("gregtech.gui.me_network.online")
                    else I18n.format("gregtech.gui.me_network.offline")
            }.asWidget()
                       .pos(5, 22))
            .child(Flow.row()
                       .pos(5, 42)
                       .coverChildrenHeight()
                       .child(IKey.lang("gtlitecore.machine.quantum_access_hatch.priority").asWidget()
                                  .width(65))
                       .child(TextFieldWidget()
                                  .width(70)
                                  .setNumbers(Int.MIN_VALUE, Int.MAX_VALUE)
                                  .value(prioritySync)
                                  .setTextColor(0xFFAAAA99.toInt())))
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        GTLiteOverlays.QUANTUM_ACCESS_HATCH_OVERLAY.renderSided(frontFacing, renderState, translation, pipeline)
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced) // TODO: Description.
    }

    // region Handlers

    private fun fillItems(input: IAEItemStack, doFill: Boolean): Long
    {
        val mteController = controller as? MultiblockQuantumChest ?: return 0
        if (!mteController.isWorkingEnabled)
            return 0

        val acceptedAmount = mteController.insertItemStack(input.definition, BigInteger.valueOf(input.stackSize), !doFill)
        return acceptedAmount.longValue()
    }

    private fun drainItems(request: IAEItemStack, doDrain: Boolean): IAEItemStack?
    {
        val mteController = controller as? MultiblockQuantumChest ?: return null
        if (!mteController.isWorkingEnabled)
            return null

        val type = request.definition
        val removedAmount = mteController.extractItemStack(type, BigInteger.valueOf(request.stackSize), !doDrain)
        if (removedAmount.signum() <= 0)
            return null
        return AEItemStack.fromItemStack(type)?.setStackSize(removedAmount.longValue())
    }

    private fun fillFluids(input: IAEFluidStack, doFill: Boolean): Long
    {
        val mteController = controller as? MultiblockQuantumTank ?: return 0
        if (!mteController.isWorkingEnabled)
            return 0

        val acceptedAmount = mteController.insertFluid(input.fluidStack, BigInteger.valueOf(input.stackSize), !doFill)
        return acceptedAmount.longValue()
    }

    private fun drainFluids(request: IAEFluidStack, doDrain: Boolean): IAEFluidStack?
    {
        val mteController = controller as? MultiblockQuantumTank ?: return null
        if (!mteController.isWorkingEnabled)
            return null

        val fluid = request.fluidStack
        val removedAmount = mteController.extractFluid(fluid, BigInteger.valueOf(request.stackSize), simulate = !doDrain)
        if (removedAmount.signum() <= 0)
            return null
        return AEFluidStack.fromFluidStack(FluidStack(fluid, 1)).setStackSize(removedAmount.longValue())
    }

    private inner class QuantumStorageItemHandler : IMEInventoryHandler<IAEItemStack>
    {
        override fun getChannel(): IStorageChannel<IAEItemStack> = itemChannel

        override fun getAccess(): AccessRestriction = readMode

        override fun getPriority(): Int = this@PartMachineQuantumAccessHatch._priority

        override fun getSlot(): Int = 0

        override fun validForPass(i: Int): Boolean = true

        override fun isSticky(): Boolean = sticky

        override fun isPrioritized(input: IAEItemStack): Boolean
        {
            val mteController = controller as? MultiblockQuantumChest ?: return false
            return mteController.itemStorage().currentAmount(input.definition).signum() > 0
        }

        override fun canAccept(input: IAEItemStack): Boolean
            = !(readMode == AccessRestriction.NO_ACCESS || readMode == AccessRestriction.READ) && fillItems(input, false) > 0

        override fun injectItems(input: IAEItemStack, actionable: Actionable, source: IActionSource): IAEItemStack?
        {
            if (readMode == AccessRestriction.NO_ACCESS || readMode == AccessRestriction.READ)
                return input

            val acceptedAmount = fillItems(input, actionable == Actionable.MODULATE)
            if (acceptedAmount == 0L)
                return input

            if (actionable == Actionable.MODULATE) notifyME()
            val leftoverStack = input.copy()
            leftoverStack.decStackSize(acceptedAmount)
            return if (leftoverStack.stackSize <= 0) null else leftoverStack
        }

        override fun extractItems(request: IAEItemStack, actionable: Actionable, source: IActionSource): IAEItemStack?
        {
            if (readMode == AccessRestriction.NO_ACCESS || readMode == AccessRestriction.WRITE)
                return null
            val drained = drainItems(request, actionable == Actionable.MODULATE)
            if (drained != null && actionable == Actionable.MODULATE) notifyME()
            return drained
        }

        override fun getAvailableItems(itemList: IItemList<IAEItemStack>): IItemList<IAEItemStack>
        {
            if (readMode == AccessRestriction.NO_ACCESS || readMode == AccessRestriction.WRITE)
                return itemList

            val mteController = controller as? MultiblockQuantumChest ?: return itemList
            for ((type, amount) in mteController.itemStorage().entries())
            {
                if (amount.signum() <= 0) continue
                itemList.add(AEItemStack.fromItemStack(type)?.setStackSize(amount.longValue()))
            }
            return itemList
        }
    }

    private inner class QuantumStorageFluidHandler : IMEInventoryHandler<IAEFluidStack>
    {
        override fun getChannel(): IStorageChannel<IAEFluidStack> = fluidChannel

        override fun getAccess(): AccessRestriction = readMode

        override fun getPriority(): Int = this@PartMachineQuantumAccessHatch._priority

        override fun getSlot(): Int = 0

        override fun validForPass(i: Int): Boolean = true

        override fun isSticky(): Boolean = sticky

        override fun isPrioritized(input: IAEFluidStack): Boolean
        {
            val mteController = controller as? MultiblockQuantumTank ?: return false
            return mteController.fluidStorage().currentAmount(input.fluidStack).signum() > 0
        }

        override fun canAccept(input: IAEFluidStack): Boolean
            = !(readMode == AccessRestriction.NO_ACCESS || readMode == AccessRestriction.READ) && fillFluids(input, false) > 0

        override fun injectItems(input: IAEFluidStack, actionable: Actionable, source: IActionSource): IAEFluidStack?
        {
            if (readMode == AccessRestriction.NO_ACCESS || readMode == AccessRestriction.READ)
                return input

            val acceptedAmount = fillFluids(input, actionable == Actionable.MODULATE)
            if (acceptedAmount == 0L)
                return input

            if (actionable == Actionable.MODULATE)
                notifyME()

            val leftoverStack = input.copy()
            leftoverStack.decStackSize(acceptedAmount)
            return if (leftoverStack.stackSize <= 0) null else leftoverStack
        }

        override fun extractItems(request: IAEFluidStack, actionable: Actionable, source: IActionSource): IAEFluidStack?
        {
            if (readMode == AccessRestriction.NO_ACCESS || readMode == AccessRestriction.WRITE)
                return null

            val drainedStack = drainFluids(request, actionable == Actionable.MODULATE)
            if (drainedStack != null && actionable == Actionable.MODULATE)
                notifyME()
            return drainedStack
        }

        override fun getAvailableItems(itemList: IItemList<IAEFluidStack>): IItemList<IAEFluidStack>
        {
            if (readMode == AccessRestriction.NO_ACCESS || readMode == AccessRestriction.WRITE)
                return itemList

            val mteController = controller as? MultiblockQuantumTank ?: return itemList
            for ((type, amount) in mteController.fluidStorage().entries())
            {
                if (amount.signum() <= 0) continue
                itemList.add(AEFluidStack.fromFluidStack(type).setStackSize(amount.longValue()))
            }
            return itemList
        }
    }

    // endregion
}
