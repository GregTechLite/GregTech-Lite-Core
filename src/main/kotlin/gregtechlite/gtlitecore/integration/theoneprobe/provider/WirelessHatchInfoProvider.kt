package gregtechlite.gtlitecore.integration.theoneprobe.provider

import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.wireless.WirelessNetworkManager
import gregtechlite.gtlitecore.common.metatileentity.part.WirelessDynamoHatch
import gregtechlite.gtlitecore.common.metatileentity.part.WirelessEnergyHatch
import gregtechlite.gtlitecore.common.metatileentity.part.WirelessHatch
import gregtechlite.gtlitecore.common.metatileentity.part.WirelessStorageHatch
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.IProbeInfoProvider
import mcjty.theoneprobe.api.NumberFormat
import mcjty.theoneprobe.api.ProbeMode
import mcjty.theoneprobe.apiimpl.elements.ElementProgress
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

/**
 * TOP provider for Wireless Energy Hatches, Wireless Dynamo Hatches, and Wireless Storage Hatches.
 * Displays channel info, buffer status, and connection count.
 */
class WirelessHatchInfoProvider : IProbeInfoProvider {

    override fun addProbeInfo(mode: ProbeMode, info: IProbeInfo, player: EntityPlayer,
                              worldIn: World, state: IBlockState, data: IProbeHitData) {
        if (!state.block.hasTileEntity(state)) return

        val tile = worldIn.getTileEntity(data.pos) ?: return
        if (tile !is IGregTechTileEntity) return

        val mte = tile.metaTileEntity ?: return
        if (mte !is WirelessHatch) return

        val isInput = mte is WirelessEnergyHatch
        val isOutput = mte is WirelessDynamoHatch
        val isStorage = mte is WirelessStorageHatch

        val channel = mte.getChannel()
        val bufferStored = mte.getBufferEnergyStored()
        val bufferCapacity = mte.bufferCapacity
        val priority = mte.getPriority()
        val amperage = mte.amperage
        val overflow = mte.overflowPool

        // Display role and amperage (always visible)
        val roleText = when {
            isInput -> I18n.format("gtlitecore.top.wireless_hatch.role_input")
            isOutput -> I18n.format("gtlitecore.top.wireless_hatch.role_output")
            isStorage -> I18n.format("gtlitecore.top.wireless_hatch.role_storage")
            else -> "??"
        }
        info.text("§e$roleText")
        info.text(I18n.format("gtlitecore.top.wireless_hatch.amperage", amperage))

        // Display channel info
        if (channel > 0) {
            info.text(I18n.format("gtlitecore.top.wireless_hatch.channel", channel))
            val connectionCount = WirelessNetworkManager.getConnectionCount(channel)
            info.text(I18n.format("gtlitecore.top.wireless_hatch.connections", connectionCount))
            info.text(I18n.format("gtlitecore.top.wireless_hatch.priority", priority))
        } else {
            info.text(I18n.format("gtlitecore.top.wireless_hatch.no_channel"))
        }

        // Display buffer progress
        if (bufferCapacity > 0) {
            val suffix = if (bufferCapacity >= 10000) "EU" else " EU"
            val format = if (bufferStored >= 10000 || bufferCapacity >= 10000)
                NumberFormat.COMPACT else NumberFormat.COMMAS

            val filledColor = when {
                isInput -> 0xFF00AA00.toInt()
                isOutput -> 0xFFAA0000.toInt()
                isStorage -> 0xFFAA8800.toInt()
                else -> 0xFF888888.toInt()
            }
            val altColor = when {
                isInput -> 0xFF00FF00.toInt()
                isOutput -> 0xFFFF0000.toInt()
                isStorage -> 0xFFFFAA00.toInt()
                else -> 0xFF888888.toInt()
            }

            info.progress(bufferStored, bufferCapacity, info.defaultProgressStyle()
                    .numberFormat(format)
                    .suffix(" / " + ElementProgress.format(bufferCapacity, format, suffix))
                    .filledColor(filledColor)
                    .alternateFilledColor(altColor)
                    .borderColor(0xFF555555.toInt()))
        }

        // Display overflow pool if non-zero
        if (overflow > 0) {
            info.text(I18n.format("gtlitecore.top.wireless_hatch.overflow", overflow))
        }
    }

    override fun getID(): String = "${MOD_ID}:wireless_hatch_provider"
}
