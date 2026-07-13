package gregtechlite.gtlitecore.common.metatileentity.electric

import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues
import gregtech.api.GTValues.VC
import gregtech.api.GTValues.VNF
import gregtech.api.capability.GregtechDataCodes.SYNC_TILE_MODE
import gregtech.api.capability.impl.EnergyContainerHandler
import gregtech.api.metatileentity.TieredMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.PipelineUtil
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MachineEnergyDistributor(id: ResourceLocation, tier: Int) : TieredMetaTileEntity(id, tier)
{
    var isDistributeMode = true
        private set

    override fun createMetaTileEntity(te: IGregTechTileEntity) = MachineEnergyDistributor(metaTileEntityId, tier)

    override fun reinitializeEnergyContainer()
    {
        val tierVoltage = GTValues.V[tier]
        energyContainer = EnergyContainerHandler(this, tierVoltage * 320, tierVoltage, 320, tierVoltage, 320)
        if (isDistributeMode)
        {
            (energyContainer as EnergyContainerHandler).setSideInputCondition { it == frontFacing }
            (energyContainer as EnergyContainerHandler).setSideOutputCondition { it != frontFacing }
        }
        else
        {
            (energyContainer as EnergyContainerHandler).setSideInputCondition { it != frontFacing }
            (energyContainer as EnergyContainerHandler).setSideOutputCondition { it == frontFacing }
        }
    }

    override fun getMaxInputOutputAmperage(): Long = 320

    override fun openGUIOnRightClick() = false

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setBoolean("DistributeMode", isDistributeMode)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        isDistributeMode = data.getBoolean("DistributeMode")
        reinitializeEnergyContainer()
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(isDistributeMode)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        isDistributeMode = buf.readBoolean()
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == SYNC_TILE_MODE)
        {
            isDistributeMode = buf.readBoolean()
            scheduleRenderUpdate()
        }
    }

    override fun onSoftMalletClick(playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing,
                                   hitResult: CuboidRayTraceResult): Boolean
    {
        if (world.isRemote)
        {
            scheduleRenderUpdate()
            return true
        }
        isDistributeMode = !isDistributeMode
        reinitializeEnergyContainer()
        writeCustomData(SYNC_TILE_MODE) { it.writeBoolean(isDistributeMode) }
        notifyBlockUpdate()
        markDirty()
        return true
    }

    override fun addToolUsages(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.tool_action.soft_mallet.toggle_mode"))
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"))
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"))
        super.addToolUsages(stack, world, tooltip, advanced)
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        if (renderState == null || pipeline == null) return
        if (isDistributeMode)
        {
            Textures.ENERGY_IN.renderSided(frontFacing, renderState, translation, PipelineUtil.color(pipeline, VC[tier]))
            EnumFacing.entries.filter { it != frontFacing }
                .forEach {
                    Textures.ENERGY_OUT.renderSided(it, renderState, translation, PipelineUtil.color(pipeline, VC[tier]))
                }
        }
        else
        {
            Textures.ENERGY_OUT.renderSided(frontFacing, renderState, translation, PipelineUtil.color(pipeline, VC[tier]))
            EnumFacing.entries.filter { it != frontFacing }
                .forEach { f ->
                    Textures.ENERGY_IN.renderSided(f, renderState, translation, PipelineUtil.color(pipeline, VC[tier]))
                }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gregtech.universal.tooltip.max_voltage_in", energyContainer.inputVoltage, VNF[tier]))
        tooltip.add(I18n.format("gtlitecore.machine.energy_distributor.tooltip.1"))
        tooltip.add(I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.energyCapacity))
        tooltip.add(I18n.format("gtlitecore.machine.energy_distributor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.energy_distributor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.energy_distributor.tooltip.4"))
    }

    override fun isValidFrontFacing(facing: EnumFacing): Boolean = true
}
