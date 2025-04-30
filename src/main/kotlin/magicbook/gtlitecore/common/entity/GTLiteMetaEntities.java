package magicbook.gtlitecore.common.entity;

import gregtech.client.renderer.handler.GTExplosiveRenderer;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.common.entity.explosive.EntityLeptonicCharge;
import magicbook.gtlitecore.common.entity.explosive.EntityNaquadriaCharge;
import magicbook.gtlitecore.common.entity.explosive.EntityTaraniumCharge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTLiteMetaEntities
{

    public static void init()
    {
        EntityRegistry.registerModEntity(GTLiteUtility.gtliteId("naquadria_charge"),
                EntityNaquadriaCharge.class, "NaquadriaCharge", 1,
                GTLiteAPI.instance, 64, 3, true);

        EntityRegistry.registerModEntity(GTLiteUtility.gtliteId("taranium_charge"),
                EntityTaraniumCharge.class, "TaraniumCharge", 2,
                GTLiteAPI.instance, 64, 3, true);

        EntityRegistry.registerModEntity(GTLiteUtility.gtliteId("leptonic_charge"),
                EntityLeptonicCharge.class, "LeptonicCharge", 3,
                GTLiteAPI.instance, 64, 3, true);

    }

    @SideOnly(Side.CLIENT)
    public static void initRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityNaquadriaCharge.class,
                GTExplosiveRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityTaraniumCharge.class,
                GTExplosiveRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityLeptonicCharge.class,
                GTExplosiveRenderer::new);
    }

}
