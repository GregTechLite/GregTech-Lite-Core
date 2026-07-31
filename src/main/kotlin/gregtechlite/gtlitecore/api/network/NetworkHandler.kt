package gregtechlite.gtlitecore.api.network

import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraftforge.fml.common.network.NetworkRegistry

interface NetworkHandler
{

    fun registerPacket(packetClass: Class<out NetworkPacket>)

    fun sendToAll(packet: NetworkPacket)

    fun sendTo(packet: NetworkPacket, player: EntityPlayerMP)

    fun sendToAllAround(packet: NetworkPacket, point: NetworkRegistry.TargetPoint)

    fun sendToAllTracking(packet: NetworkPacket, point: NetworkRegistry.TargetPoint)

    fun sendToAllTracking(packet: NetworkPacket, entity: Entity)

    fun sendToDimension(packet: NetworkPacket, dimensionId: Int)

    fun sendToServer(packet: NetworkPacket)
}