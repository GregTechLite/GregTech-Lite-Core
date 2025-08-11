package gregtechlite.gtlitecore.client;

import gregtechlite.gtlitecore.client.event.GTLiteTooltips;
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures;
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper;
import gregtechlite.gtlitecore.client.shader.CosmicShaderProgram;
import gregtechlite.gtlitecore.common.CommonProxy;
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks;
import gregtechlite.gtlitecore.common.entity.GTLiteMetaEntities;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
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
        CosmicShaderProgram.initShaders();
        GTLiteMetaEntities.initRenderers();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        GTLiteMetaBlocks.registerItemModels();
    }

}
