package gregtechlite.gtlitecore.client;

import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.client.event.ClientEventHandlers;
import gregtechlite.gtlitecore.client.event.GTLiteTooltips;
import gregtechlite.gtlitecore.client.renderer.handler.tesr.TESRBottlecrate;
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures;
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper;
import gregtechlite.gtlitecore.client.shader.CosmicShaderProgram;
import gregtechlite.gtlitecore.common.CommonProxy;
import gregtechlite.gtlitecore.common.block.GTLiteBlocks;
import gregtechlite.gtlitecore.common.entity.GTLiteMetaEntities;
import gregtechlite.gtlitecore.common.tileentity.TileEntityBlockcrate;
import gregtechlite.gtlitecore.core.network.ClientNetworkHandler;
import gregtechlite.gtlitecore.core.network.NetworkHandlerImpl;
import gregtechlite.gtlitecore.core.network.PacketHandler;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{

    @Override
    public void onPreInit()
    {
        super.onPreInit();
        GTLiteTextures.preInit();
        MinecraftForge.EVENT_BUS.register(CosmicShaderHelper.class);
        MinecraftForge.EVENT_BUS.register(GTLiteTooltips.class);
        MinecraftForge.EVENT_BUS.register(ClientEventHandlers.INSTANCE);
        CosmicShaderProgram.initShaders();
        GTLiteMetaEntities.initRenderers();
        NetworkHandlerImpl.Companion.getInstance().registerEventListener(
                new ClientNetworkHandler(PacketHandler.Companion.getInstance()));
    }

    @Override
    public void onInit()
    {
        super.onInit();
        GTLiteValues.LOGGER.debug("Registering Block(Item)ColorHandler for Meta(Item)Blocks");
        GTLiteBlocks.registerColors();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        GTLiteBlocks.registerItemModels();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockcrate.class, new TESRBottlecrate());
    }

}
