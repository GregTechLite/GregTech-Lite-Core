package gregtechlite.gtlitecore.integration.theoneprobe.provider

import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.wireless.WirelessNetworkManager
import gregtechlite.gtlitecore.common.metatileentity.part.PartMachineWirelessDynamoHatch
import gregtechlite.gtlitecore.common.metatileentity.part.PartMachineWirelessEnergyHatch
import gregtechlite.gtlitecore.common.metatileentity.part.PartMachineWirelessHatch
import gregtechlite.gtlitecore.common.metatileentity.part.PartMachineWirelessStorageHatch
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.IProbeInfoProvider
import mcjty.theoneprobe.api.NumberFormat
import mcjty.theoneprobe.api.ProbeMode
import mcjty.theoneprobe.apiimpl.elements.ElementProgress
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World

class WirelessHatchInfoProvider : IProbeInfoProvider
{
    override fun addProbeInfo(mode: ProbeMode, info: IProbeInfo, player: EntityPlayer, worldIn: World,
                              state: IBlockState, data: IProbeHitData)
    {
        if (!state.block.hasTileEntity(state)) return

        val tile = worldIn.getTileEntity(data.pos) ?: return
        if (tile !is IGregTechTileEntity) return

        val mte = tile.metaTileEntity ?: return
        if (mte !is PartMachineWirelessHatch) return

        val isInput = mte is PartMachineWirelessEnergyHatch
        val isOutput = mte is PartMachineWirelessDynamoHatch
        val isStorage = mte is PartMachineWirelessStorageHatch

        val channel = mte.getChannel()
        val bufferStored = mte.getBufferEnergyStored()
        val bufferCapacity = mte.bufferCapacity
        val priority = mte.getPriority()
        val amperage = mte.amperage
        val overflow = mte.overflowPool

        // Display Role & Amperage
        val roleKey = when
        {
            isInput   -> "gtlitecore.top.wireless_hatch.role_input"
            isOutput  -> "gtlitecore.top.wireless_hatch.role_output"
            isStorage -> "gtlitecore.top.wireless_hatch.role_storage"
            else      ->
            {
                info.text(TextFormatting.RED.toString() + "{*gtlitecore.top.wireless_hatch.error_unknown*}")
                return
            }
        }
        info.text("{*$roleKey*}")
        info.text(translationWithColor(TextFormatting.GRAY, "gtlitecore.top.wireless_hatch.amperage", amperage).formattedText)

        // Display Channel
        if (channel > 0)
        {
            info.text(translationWithColor(TextFormatting.GRAY, "gtlitecore.top.wireless_hatch.channel", channel).formattedText)
            val connectionCount = WirelessNetworkManager.getConnectionCount(channel)
            info.text(translationWithColor(TextFormatting.GRAY, "gtlitecore.top.wireless_hatch.connections", connectionCount).formattedText)
            info.text(translationWithColor(TextFormatting.GRAY, "gtlitecore.top.wireless_hatch.priority", priority).formattedText)
        }
        else
        {
            info.text("{*gtlitecore.top.wireless_hatch.no_channel*}")
        }

        // Display Buffer Progress
        if (bufferCapacity > 0)
        {
            val suffix = if (bufferCapacity >= 10000) "EU" else " EU"
            val format = if (bufferStored >= 10000 || bufferCapacity >= 10000) NumberFormat.COMPACT else NumberFormat.COMMAS

            val filledColor = when
            {
                isInput   -> 0xFF00AA00.toInt()
                isOutput  -> 0xFFAA0000.toInt()
                isStorage -> 0xFFAA8800.toInt()
                else      -> 0xFF888888.toInt()
            }
            val altColor = when
            {
                isInput   -> 0xFF00FF00.toInt()
                isOutput  -> 0xFFFF0000.toInt()
                isStorage -> 0xFFFFAA00.toInt()
                else      -> 0xFF888888.toInt()
            }

            info.progress(bufferStored, bufferCapacity, info.defaultProgressStyle()
                    .numberFormat(format)
                    .suffix(" / " + ElementProgress.format(bufferCapacity, format, suffix))
                    .filledColor(filledColor)
                    .alternateFilledColor(altColor)
                    .borderColor(0xFF555555.toInt()))
        }

        // Display Overflow Pool
        if (overflow > 0)
        {
            info.text(translationWithColor(TextFormatting.GRAY, "gtlitecore.top.wireless_hatch.overflow", overflow).formattedText)
        }
    }

    override fun getID(): String = "${MOD_ID}:wireless_hatch_provider"
}
