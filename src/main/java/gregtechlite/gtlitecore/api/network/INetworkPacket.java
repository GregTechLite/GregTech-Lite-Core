package gregtechlite.gtlitecore.api.network;

import gregtechlite.gtlitecore.api.GTLiteAPI;
import net.minecraft.network.PacketBuffer;

/**
 * Abstract network packets structure.
 * <p>
 * To implement a new network packet, implement both {@link #encode} and {@link #decode} and register
 * the packet to object in API registry ({@link GTLiteAPI}). Additionally, do one of the following:
 * <ul>
 *     <li>If this network packet is to be received on the server side, implement {@link IServerExecutor}.</li>
 *     <li>If this network packet is to be received on the client side, implement {@link IClientExecutor}.</li>
 * </ul>
 * Lastly, add a no-args constructor to the network packet class.
 */
public interface INetworkPacket
{

    /**
     * Used to write data from a network packet into a {@code PacketBuffer}.
     * <p>
     * This is the first step in sending a network packet to a different thread, and is done on
     * the "sending" side.
     *
     * @param buf The {@code PacketBuffer} to write Packet data to.
     */
    void encode(PacketBuffer buf);

    /**
     * Used to read data from a {@code PacketBuffer} into this Packet.
     * <p>
     * This is the next step of sending a network packet to a different thread, and is done on
     * the "receiving" side.
     *
     * @param buf The {@code PacketBuffer} to read network packet data from.
     */
    void decode(PacketBuffer buf);

}
