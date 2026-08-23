@file:Suppress("UNCHECKED_CAST")
package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.drawable.ItemDrawable
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.utils.Alignment
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widgets.layout.Flow
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.IControllable
import gregtech.api.capability.IDataStickIntractable
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.mui.GTGuiTextures
import gregtech.api.util.GTUtility
import gregtech.api.util.KeyUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting

abstract class AdditionalMultiblockBase<T : ExtendableMultiblock<T>>(metaTileEntityId: ResourceLocation)
    : MultiblockWithDisplayBase(metaTileEntityId), IControllable, IDataStickIntractable
{
    @JvmField
    protected var mainController: ExtendableMultiblock<T>? = null

    @JvmField
    protected var isWorkingEnabled: Boolean = false

    private var snapshotControllerPos: BlockPos? = null

    override fun hasMaintenanceMechanics() = false

    override fun isWorkingEnabled() = isStructureFormed && isConnected()

    override fun setWorkingEnabled(workingStatus: Boolean)
    {
        isWorkingEnabled = workingStatus
        markDirty()
        if (world != null && !world.isRemote)
        {
            writeCustomData(WORKING_ENABLED) { it.writeBoolean(isWorkingEnabled) }
        }
    }

    protected fun isConnected(): Boolean
    {
        if (mainController != null) return mainController!!.isWorkingEnabled()
        val pendingPos = snapshotControllerPos ?: return false
        val mte = GTUtility.getMetaTileEntity(world, pendingPos)
        if (mte is ExtendableMultiblock<*>)
        {
            connect(mte as ExtendableMultiblock<T>)
            return mainController?.isWorkingEnabled() ?: false
        }
        return false
    }

    fun connect(controller: ExtendableMultiblock<T>?)
    {
        mainController?.removeAdditional(pos)
        mainController = controller
        mainController?.addAdditional(this)
        snapshotControllerPos = null
        scheduleRenderUpdate()
    }

    override fun onRemoval()
    {
        super.onRemoval()
        mainController?.removeAdditional(pos)
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setBoolean("isWorkingEnabled", isWorkingEnabled)
        mainController?.let { controller ->
            val mainPos = NBTTagCompound()
            mainPos.setInteger("X", controller.controllerPos.x)
            mainPos.setInteger("Y", controller.controllerPos.y)
            mainPos.setInteger("Z", controller.controllerPos.z)
            data.setTag("MainControllerPos", mainPos)
        }
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        setWorkingEnabled(data.getBoolean("isWorkingEnabled"))
        if (data.hasKey("MainControllerPos"))
        {
            val mainPos = data.getCompoundTag("MainControllerPos")
            val pos = BlockPos(mainPos.getInteger("X"), mainPos.getInteger("Y"), mainPos.getInteger("Z"))
            val mte = GTUtility.getMetaTileEntity(world, pos)
            if (mte is ExtendableMultiblock<*>)
            {
                connect(mte as ExtendableMultiblock<T>)
            }
            else
            {
                snapshotControllerPos = pos
            }
        }
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(isWorkingEnabled)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        setWorkingEnabled(buf.readBoolean())
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == WORKING_ENABLED)
        {
            isWorkingEnabled = buf.readBoolean()
            scheduleRenderUpdate()
        }
    }

    override fun createUIFactory(): MultiblockUIFactory = SimpleUIFactory(this)

    override fun onDataStickLeftClick(player: EntityPlayer, stack: ItemStack)
    {
        val tag = stack.tagCompound ?: NBTTagCompound()
        tag.setTag("AdditionalPos", NBTTagCompound().apply {
            setInteger("X", pos.x)
            setInteger("Y", pos.y)
            setInteger("Z", pos.z)
        })
        stack.tagCompound = tag
        stack.setTranslatableName("gtlitecore.machine.additional_structure.data_stick.name")
        player.sendStatusMessage(TextComponentTranslation("gtlitecore.machine.additional_structure.pos_saved",
                                                          pos.x, pos.y, pos.z), true)
    }

    override fun onDataStickRightClick(player: EntityPlayer, stack: ItemStack): Boolean = false

    override fun renderMetaTileEntity(renderState: CCRenderState,
                                      translation: Matrix4,
                                      pipeline: Array<out IVertexOperation>)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        frontOverlay.renderOrientedState(renderState, translation, pipeline, frontFacing, isActive, isWorkingEnabled)
    }

    @Suppress("UnstableApiUsage")
    private class SimpleUIFactory(val controller: AdditionalMultiblockBase<*>) : MultiblockUIFactory(controller)
    {
        override fun buildUI(guiData: PosGuiData, panelSyncManager: PanelSyncManager): ModularPanel = ModularPanel(controller.metaName)
                .coverChildren()
                .child(Flow.row()
                           .padding(4)
                           .coverChildren()
                           .background(GTGuiTextures.BACKGROUND)
                           .crossAxisAlignment(Alignment.CrossAxis.CENTER)
                           .child(ItemDrawable(controller.stackForm).asWidget()
                                      .size(16)
                                      .marginRight(4))
                           .child(KeyUtil.lang(TextFormatting.WHITE, controller.metaFullName).asWidget()
                                      .padding(4)
                                      .background(GTGuiTextures.DISPLAY)))
    }
}