package gregtechlite.gtlitecore.client

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.client.event.ClientEventHandlers
import gregtechlite.gtlitecore.client.event.GTLiteTooltips
import gregtechlite.gtlitecore.client.renderer.handler.tesr.TESRBottlecrate
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper
import gregtechlite.gtlitecore.client.shader.CosmicShaderProgram
import gregtechlite.gtlitecore.common.CommonProxy
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.entity.GTLiteMetaEntities
import gregtechlite.gtlitecore.common.tileentity.TileEntityBlockcrate
import gregtechlite.gtlitecore.core.network.ClientNetworkHandler
import gregtechlite.gtlitecore.core.network.NetworkHandlerImpl
import gregtechlite.gtlitecore.core.network.PacketHandler
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Mod.EventBusSubscriber(modid = MOD_ID, value = [Side.CLIENT])
@SideOnly(Side.CLIENT)
class ClientProxy : CommonProxy()
{
    override fun onPreInit()
    {
        super.onPreInit()
        GTLiteTextures.preInit()
        MinecraftForge.EVENT_BUS.register(CosmicShaderHelper)
        MinecraftForge.EVENT_BUS.register(GTLiteTooltips)
        MinecraftForge.EVENT_BUS.register(ClientEventHandlers)
        CosmicShaderProgram.initShaders()
        GTLiteMetaEntities.initRenderers()
        NetworkHandlerImpl.getInstance().registerEventListener(ClientNetworkHandler(PacketHandler.getInstance()))
    }

    override fun onInit()
    {
        super.onInit()
        GTLiteBlocks.registerColors()
    }

    companion object
    {
        @Suppress("unused")
        @SubscribeEvent
        fun registerModels(event: ModelRegistryEvent)
        {
            GTLiteBlocks.registerItemModels()
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockcrate::class.java, TESRBottlecrate())
        }
    }
}