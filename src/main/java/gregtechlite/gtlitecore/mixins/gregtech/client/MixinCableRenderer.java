package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.pipenet.block.BlockPipe;
import gregtech.api.pipenet.block.IPipeType;
import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.pipe.CableRenderer;
import gregtech.client.renderer.pipe.PipeRenderer;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Let wireGtX/cableGtX renderer {@link MaterialIconSet} textures.
 * <p>
 * This class implement a feature that make material wireGtX/cableGtX can render its
 * textures from its {@link MaterialIconSet}. This change will cause the original
 * wireGtX/cableGtX source texture at "gregtech/blocks/cable/wire.png" be invalid in
 * its renderer. The solution of this problem is created a new texture at its folder.
 *
 * @author Magic_Sweepy
 */
@Mixin(value = CableRenderer.class, remap = false)
public abstract class MixinCableRenderer
{
    @Unique
    private final Object2ObjectMap<MaterialIconSet, TextureAtlasSprite> gtlitecore$wireTextures
            = new Object2ObjectOpenHashMap<>();

    @Inject(method = "registerIcons",
            at = @At(value = "TAIL"))
    private void registerIcons(TextureMap map, CallbackInfo ci)
    {
        ResourceLocation wireLocation = GTUtility.gregtechId("blocks/cable/wire");
        gtlitecore$wireTextures.put(MaterialIconSet.DULL, map.registerSprite(wireLocation));
        for (MaterialIconSet iconSet : MaterialIconSet.ICON_SETS.values())
        {
            ResourceLocation iconSetWireLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/wire");
            gtlitecore$wireTextures.put(iconSet, map.registerSprite(iconSetWireLocation));
        }
    }

    @Redirect(method = "buildRenderer",
            at = @At(value = "FIELD",
                    target = "Lgregtech/client/renderer/pipe/CableRenderer;wireTexture:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private TextureAtlasSprite gtlitecore$getWireTexture(CableRenderer instance,
                                                         PipeRenderer.PipeRenderContext renderContext,
                                                         BlockPipe<?, ?, ?> blockPipe,
                                                         IPipeTile<?, ?> pipeTile,
                                                         IPipeType<?> pipeType,
                                                         Material material)
    {
        return gtlitecore$wireTextures.getOrDefault(material.getMaterialIconSet(),
                gtlitecore$wireTextures.get(MaterialIconSet.DULL));
    }

    @Redirect(method = "getParticleTexture(Lgregtech/api/pipenet/tile/IPipeTile;)Lorg/apache/commons/lang3/tuple/Pair;",
            at = @At(value = "FIELD",
                    target = "Lgregtech/client/renderer/pipe/CableRenderer;wireTexture:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private TextureAtlasSprite gtlitecore$getParticleWireTexture(CableRenderer instance, IPipeTile<?, ?> pipeTile)
    {
        Material material = pipeTile instanceof TileEntityMaterialPipeBase
                ? ((TileEntityMaterialPipeBase<?, ?>) pipeTile).getPipeMaterial() : null;
        return gtlitecore$wireTextures.getOrDefault(
                material != null ? material.getMaterialIconSet() : MaterialIconSet.DULL,
                gtlitecore$wireTextures.get(MaterialIconSet.DULL));
    }
}
