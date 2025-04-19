package magicbook.gtlitecore.api.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public interface INetworkHandler
{

    void registerPacket(Class<? extends INetworkPacket> packetClass);

    void sendToAll(INetworkPacket packet);

    void sendTo(INetworkPacket packet, EntityPlayerMP player);

    void sendToAllAround(INetworkPacket packet, NetworkRegistry.TargetPoint point);

    void sendToAllTracking(INetworkPacket packet, NetworkRegistry.TargetPoint point);

    void sendToAllTracking(INetworkPacket packet, Entity entity);

    void sendToDimension(INetworkPacket packet, int dimensionId);

    void sendToServer(INetworkPacket packet);

}
