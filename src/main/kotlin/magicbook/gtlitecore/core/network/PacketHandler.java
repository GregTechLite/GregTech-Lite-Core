package magicbook.gtlitecore.core.network;

import magicbook.gtlitecore.api.network.INetworkPacket;
import net.minecraft.util.IntIdentityHashBiMap;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class PacketHandler
{

    private static final PacketHandler INSTANCE = new PacketHandler(10);

    private final IntIdentityHashBiMap<Class<? extends INetworkPacket>> packets;

    private int id = 1;

    private PacketHandler(int initialCapacity)
    {
        this.packets = new IntIdentityHashBiMap<>(initialCapacity);
    }

    /* package */ static PacketHandler getInstance()
    {
        return INSTANCE;
    }

    public void registerPacket(Class<? extends INetworkPacket> packetClass)
    {
        this.packets.put(packetClass, id++);
    }

    public int getPacketId(Class<? extends INetworkPacket> packetClass)
    {
        return this.packets.getId(packetClass);
    }

    public Class<? extends INetworkPacket> getPacketClass(int packetId)
    {
        return this.packets.get(packetId);
    }

}
