package magicbook.gtlitecore.client;

import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.client.shader.CosmicShaderHelper;
import magicbook.gtlitecore.client.shader.ShaderUtils;
import magicbook.gtlitecore.common.CommonProxy;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
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
    public void preLoad()
    {
        super.preLoad();
        GTLiteTextures.preInit();
        MinecraftForge.EVENT_BUS.register(CosmicShaderHelper.class);
        ShaderUtils.initShaders();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        GTLiteMetaBlocks.registerItemModels();
    }

}
