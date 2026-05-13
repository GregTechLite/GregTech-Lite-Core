package gregtechlite.gtlitecore.api.network

import net.minecraft.client.network.NetHandlerPlayClient
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface ClientExecutor
{

    @SideOnly(Side.CLIENT)
    fun executeClient(handler: NetHandlerPlayClient)
}