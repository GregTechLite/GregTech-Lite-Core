package gregtechlite.gtlitecore.mixins.gregtech.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.pipe.CableRenderer;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
    private void gtlitecore$registerIcons(TextureMap map, CallbackInfo callbackInfo)
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

    /**
     * @author Magic_Sweepy
     * @reason Replace the {@code wireTexture} field read of {@code buildRenderer}, the enclosing method keeps its own
     *         parameter list, {@code material} is captured by its local variable slot.
     */
    @WrapOperation(method = "buildRenderer",
                   at = @At(value = "FIELD",
                            target = "Lgregtech/client/renderer/pipe/CableRenderer;wireTexture:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private TextureAtlasSprite gtlitecore$getWireTexture(CableRenderer instance, Operation<TextureAtlasSprite> original,
                                                         @Local(index = 5, argsOnly = true) Material material)
    {
        return gtlitecore$wireTextures.getOrDefault(material.getMaterialIconSet(),
                gtlitecore$wireTextures.get(MaterialIconSet.DULL));
    }

    @WrapOperation(method = "getParticleTexture(Lgregtech/api/pipenet/tile/IPipeTile;)Lorg/apache/commons/lang3/tuple/Pair;",
                   at = @At(value = "FIELD",
                            target = "Lgregtech/client/renderer/pipe/CableRenderer;wireTexture:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private TextureAtlasSprite gtlitecore$getParticleWireTexture(CableRenderer instance,
                                                                 Operation<TextureAtlasSprite> original,
                                                                 @Local(index = 1, argsOnly = true) IPipeTile<?, ?> pipeTile)
    {
        Material material = pipeTile instanceof TileEntityMaterialPipeBase
                ? ((TileEntityMaterialPipeBase<?, ?>) pipeTile).getPipeMaterial() : null;
        return gtlitecore$wireTextures.getOrDefault(material != null ? material.getMaterialIconSet()
                        : MaterialIconSet.DULL, gtlitecore$wireTextures.get(MaterialIconSet.DULL));
    }
}
