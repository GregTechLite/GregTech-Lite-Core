package gregtechlite.gtlitecore.core.network

import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.module.ModuleStage
import gregtechlite.gtlitecore.api.network.ClientExecutor
import gregtechlite.gtlitecore.api.network.NetworkHandler
import gregtechlite.gtlitecore.api.network.NetworkPacket
import gregtechlite.gtlitecore.api.network.ServerExecutor
import gregtechlite.gtlitecore.core.CoreModule
import io.netty.buffer.Unpooled
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.network.NetHandlerPlayServer
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.network.FMLEventChannel
import net.minecraftforge.fml.common.network.FMLNetworkEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket
class NetworkHandlerImpl : NetworkHandler
{
    private var channel: FMLEventChannel? = null
    private var packetHandler: PacketHandler? = null

    companion object
    {
        private val instance = NetworkHandlerImpl()

        fun getInstance(): NetworkHandlerImpl = instance
    }

    private constructor()
    {
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(MOD_ID)
        channel?.register(this)
        packetHandler = PacketHandler.getInstance()
    }

    override fun registerPacket(packetClass: Class<out NetworkPacket>)
    {
        if (GTLiteAPI.moduleManager.hasPassedStage(ModuleStage.PRE_INIT))
        {
            CoreModule.logger.error("Could not register packet '${packetClass.name}' as packet registration has ended!")
            return
        }

        val hasServerExecutor = ServerExecutor::class.java.isAssignableFrom(packetClass)
        val hasClientExecutor = ClientExecutor::class.java.isAssignableFrom(packetClass)
        if (hasServerExecutor && hasClientExecutor)
        {
            CoreModule.logger.error("Could not register packet '${packetClass.name}', as it is both a Server and Client executor! Only one allowed. Skipping...")
            return
        }
        if (!hasServerExecutor && !hasClientExecutor)
        {
            CoreModule.logger.error("Could not register packet '${packetClass.name}', as it does not have an executor! Must have either ServerExecutor or ClientExecutor. Skipping...")
            return
        }
        packetHandler?.registerPacket(packetClass)
    }

    override fun sendToAll(packet: NetworkPacket)
    {
        channel?.sendToAll(toFMLPacket(packet))
    }

    override fun sendTo(packet: NetworkPacket, player: EntityPlayerMP)
    {
        channel?.sendTo(toFMLPacket(packet), player)
    }

    override fun sendToAllAround(packet: NetworkPacket, point: NetworkRegistry.TargetPoint)
    {
        channel?.sendToAllAround(toFMLPacket(packet), point)
    }

    override fun sendToAllTracking(packet: NetworkPacket, point: NetworkRegistry.TargetPoint)
    {
        channel?.sendToAllTracking(toFMLPacket(packet), point)
    }

    override fun sendToAllTracking(packet: NetworkPacket, entity: Entity)
    {
        channel?.sendToAllTracking(toFMLPacket(packet), entity)
    }

    override fun sendToDimension(packet: NetworkPacket, dimensionId: Int)
    {
        channel?.sendToDimension(toFMLPacket(packet), dimensionId)
    }

    override fun sendToServer(packet: NetworkPacket)
    {
        channel?.sendToServer(toFMLPacket(packet))
    }

    fun registerEventListener(target: Any)
    {
        channel?.register(target)
    }

    @SubscribeEvent
    fun onServerPacket(event: FMLNetworkEvent.ServerCustomPacketEvent)
    {
        val packet = toModPacket(event.packet)
        if (ServerExecutor::class.java.isAssignableFrom(packet::class.java))
        {
            val executor = packet as ServerExecutor
            val handler = event.handler as NetHandlerPlayServer
            val threadListener = FMLCommonHandler.instance().getWorldThread(handler)
            if (threadListener.isCallingFromMinecraftThread)
                executor.executeServer(handler)
            else
                threadListener.addScheduledTask { executor.executeServer(handler) }
        }
    }

    private fun toFMLPacket(packet: NetworkPacket): FMLProxyPacket
    {
        val buf = PacketBuffer(Unpooled.buffer())
        buf.writeVarInt(packetHandler!!.getPacketId(packet::class.java))
        packet.encode(buf)
        return FMLProxyPacket(buf, MOD_ID)
    }

    private fun toModPacket(packet: FMLProxyPacket): NetworkPacket
    {
        val payloadBuf = packet.payload() as PacketBuffer
        val packetClass = packetHandler!!.getPacketClass(payloadBuf.readVarInt())
        val _packet = packetClass.getConstructor().newInstance()
        _packet.decode(payloadBuf)
        if (payloadBuf.readableBytes() != 0)
        {
            CoreModule.logger.error("NetworkHandler failed to finish reading packet with class ${packetClass.name} and ${payloadBuf.readableBytes()} bytes remaining")
        }
        return _packet
    }

}