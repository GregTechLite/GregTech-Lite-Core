package gregtechlite.gtlitecore.api.network

import net.minecraft.network.PacketBuffer

/**
 * Abstract network packets structure.
 *
 * To implement a new network packet, implement both [encode] and [decode] and register
 * the packet to object in API registry. Additionally, do one of the following:
 *
 * - If this network packet is to be received on the server side, implement [ServerExecutor].
 * - If this network packet is to be received on the client side, implement [ClientExecutor].
 *
 * Lastly add a no-args constructor to the network packet class.
 */
interface NetworkPacket
{

    /**
     * Uses to write data from a network packet into a [PacketBuffer].
     *
     * This is the first step in sending a network packet to a different thread, and is
     * done on the "sending" side.
     *
     * @param buf The [PacketBuffer] to write packet data to.
     */
    fun encode(buf: PacketBuffer)

    /**
     * Uses to read data from a [PacketBuffer] into this packet.
     *
     * This is the next step of sending a network packet to a different thread, and is
     * done on the "receiving" side.
     *
     * @param buf The [PacketBuffer] to read network packet data from.
     */
    fun decode(buf: PacketBuffer)
}