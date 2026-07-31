package gregtechlite.gtlitecore.core.network

import gregtechlite.gtlitecore.api.network.ClientExecutor
import gregtechlite.gtlitecore.api.network.NetworkPacket
import gregtechlite.gtlitecore.core.CoreModule
import net.minecraft.client.network.NetHandlerPlayClient
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.network.FMLNetworkEvent
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class ClientNetworkHandler(private val packetHandler: PacketHandler)
{
    @SubscribeEvent
    fun onClientPacket(event: FMLNetworkEvent.ClientCustomPacketEvent)
    {
        val packet = toModPacket(event.packet)
        if (ClientExecutor::class.java.isAssignableFrom(packet::class.java))
        {
            val executor = packet as ClientExecutor
            val handler = event.handler as NetHandlerPlayClient
            val threadListener = FMLCommonHandler.instance().getWorldThread(handler)
            if (threadListener.isCallingFromMinecraftThread)
                executor.executeClient(handler)
            else
                threadListener.addScheduledTask { executor.executeClient(handler) }
        }
    }

    private fun toModPacket(packet: FMLProxyPacket): NetworkPacket
    {
        val payloadBuf = packet.payload() as PacketBuffer
        val packetClass = packetHandler.getPacketClass(payloadBuf.readVarInt())
        val _packet = packetClass.getConstructor().newInstance()
        _packet.decode(payloadBuf)
        if (payloadBuf.readableBytes() != 0)
        {
            CoreModule.logger.error("NetworkHandler failed to finish reading packet with class ${packetClass.name} and ${payloadBuf.readableBytes()} bytes remaining")
        }
        return _packet
    }
}
