package gregtechlite.gtlitecore.core.network;

import gregtechlite.gtlitecore.api.network.NetworkPacket;
import net.minecraft.util.IntIdentityHashBiMap;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class PacketHandler
{

    private static final PacketHandler INSTANCE = new PacketHandler(10);

    private final IntIdentityHashBiMap<Class<? extends NetworkPacket>> packets;

    private int id = 1;

    private PacketHandler(int initialCapacity)
    {
        this.packets = new IntIdentityHashBiMap<>(initialCapacity);
    }

    /* package */ static PacketHandler getInstance()
    {
        return INSTANCE;
    }

    public void registerPacket(Class<? extends NetworkPacket> packetClass)
    {
        this.packets.put(packetClass, id++);
    }

    public int getPacketId(Class<? extends NetworkPacket> packetClass)
    {
        return this.packets.getId(packetClass);
    }

    public Class<? extends NetworkPacket> getPacketClass(int packetId)
    {
        return this.packets.get(packetId);
    }

}
