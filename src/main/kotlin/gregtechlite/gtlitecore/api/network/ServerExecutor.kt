package gregtechlite.gtlitecore.api.network

import net.minecraft.network.NetHandlerPlayServer

interface ServerExecutor
{

    fun executeServer(handler: NetHandlerPlayServer)
}