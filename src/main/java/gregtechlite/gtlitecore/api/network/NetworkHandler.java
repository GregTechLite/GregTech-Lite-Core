package gregtechlite.gtlitecore.api.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public interface NetworkHandler
{

    void registerPacket(Class<? extends NetworkPacket> packetClass);

    void sendToAll(NetworkPacket packet);

    void sendTo(NetworkPacket packet, EntityPlayerMP player);

    void sendToAllAround(NetworkPacket packet, NetworkRegistry.TargetPoint point);

    void sendToAllTracking(NetworkPacket packet, NetworkRegistry.TargetPoint point);

    void sendToAllTracking(NetworkPacket packet, Entity entity);

    void sendToDimension(NetworkPacket packet, int dimensionId);

    void sendToServer(NetworkPacket packet);

}
