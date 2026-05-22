package gregtechlite.gtlitecore.core.network

import gregtechlite.gtlitecore.api.collection.intIdHashBiMapOf
import gregtechlite.gtlitecore.api.network.NetworkPacket

class PacketHandler(initialCapacity: Int = 10)
{
    private val packets = intIdHashBiMapOf<Class<out NetworkPacket>>(initialCapacity)
    private var id = 1

    companion object
    {
        private val instance = PacketHandler()

        fun getInstance(): PacketHandler = instance
    }

    fun registerPacket(packetClass: Class<out NetworkPacket>)
    {
        packets.put(packetClass, id++)
    }

    fun getPacketId(packetClass: Class<out NetworkPacket>) = packets.getId(packetClass)

    fun getPacketClass(packetId: Int): Class<out NetworkPacket> = packets[packetId]!!
}