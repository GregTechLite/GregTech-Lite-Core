package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.drawable.ItemDrawable
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widgets.ButtonWidget
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.IControllable
import gregtech.api.capability.IDataStickIntractable
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.util.KeyUtil
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentTranslation
import java.awt.event.MouseEvent

abstract class AdditionalMultiblockBase<T : ExtendableMultiblock<T>>(metaTileEntityId: ResourceLocation)
    : MultiblockWithDisplayBase(metaTileEntityId), IControllable, IDataStickIntractable
{
    @JvmField
    protected var mainController: ExtendableMultiblock<T>? = null
    @JvmField
    protected var isWorkingEnabled: Boolean = false

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

    protected fun isConnected() = mainController != null && mainController!!.isWorkingEnabled()

    fun connect(controller: ExtendableMultiblock<T>?)
    {
        mainController?.removeAdditional(pos)
        mainController = controller
        mainController?.addAdditional(this)
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setBoolean("isWorkingEnabled", isWorkingEnabled)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        setWorkingEnabled(data.getBoolean("isWorkingEnabled"))
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

    override fun configureWarningText(builder: MultiblockUIBuilder?)
    {
        if (mainController?.isStructureFormed!!)
        {
            builder?.addMaintenanceProblemLines(mainController?.maintenanceProblem!!, true)
        }
    }

    override fun createUIFactory(): MultiblockUIFactory?
    {
        return super.createUIFactory()
            .createFlexButton { _, guiSyncManager ->
                guiSyncManager.registerSyncedAction("highlight_main_controller") { mainController?.highlightController() }
                return@createFlexButton ButtonWidget()
                    .background(GTLiteMuiTextures.BUTTON_ENABLE_MODULE)
                    .disableHoverBackground()
                    .onMousePressed {
                        guiSyncManager.callSyncedAction("highlight_main_controller")
                        true
                    }
                    .tooltip { tooltip ->
                        tooltip.addLine(KeyUtil.lang("gtlitecore.machine.extendable.highlight_main_controller"))
                    }
            }
    }

    fun highlightController() {
        TODO()
    }

    fun getButton(panelSyncManager: PanelSyncManager): IWidget
    {
        panelSyncManager.registerSyncedAction("highlight_controller") { highlightController() }
        return ButtonWidget()
            .size(18, 18)
            .overlay(ItemDrawable(stackForm))
            .addTooltipStringLines(listOf(
                I18n.format(this.metaFullName),
                I18n.format("gtlitecore.machine.extendable.additional_button_action") // TODO
            ))
            .onMousePressed {
                if (it == 1)
                {
                    // Highlight
                    panelSyncManager.callSyncedAction("highlight_controller")
                    return@onMousePressed true
                }
                else if (it == 2)
                {
                    // Other action... TODO
                    return@onMousePressed true
                }
                else
                {
                    return@onMousePressed false
                }
            }
    }

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

    override fun renderMetaTileEntity(
        renderState: CCRenderState, translation: Matrix4,
        pipeline: Array<out IVertexOperation>,
    )
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        frontOverlay.renderOrientedState(renderState, translation, pipeline, frontFacing, isActive, isWorkingEnabled)
    }
}