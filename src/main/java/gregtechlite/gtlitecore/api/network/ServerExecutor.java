package gregtechlite.gtlitecore.api.network;

import net.minecraft.network.NetHandlerPlayServer;

public interface ServerExecutor
{

    void executeServer(NetHandlerPlayServer handler);

}