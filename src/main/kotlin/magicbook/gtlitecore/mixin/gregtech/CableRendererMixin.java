package magicbook.gtlitecore.mixin.gregtech;

import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.uv.IconTransformation;
import gregtech.api.pipenet.block.BlockPipe;
import gregtech.api.pipenet.block.IPipeType;
import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.pipe.CableRenderer;
import gregtech.client.renderer.pipe.PipeRenderer;
import gregtech.common.pipelike.cable.Insulation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

/**
 * Feature: Let wireGtX/cableGtX renderer {@link MaterialIconSet} textures.
 * <p>
 * This class implement a feature that make material wireGtX/cableGtX can render its
 * textures from its {@link MaterialIconSet}. This change will cause the original
 * wireGtX/cableGtX source texture at "gregtech/blocks/cable/wire.png" be invalid in
 * its renderer. The solution of this problem is created a new texture at its folder.
 *
 * @author Magic_Sweepy
 */
@Mixin(value = CableRenderer.class, remap = false)
public abstract class CableRendererMixin
{

    // This map is transform of original wireTexture param, used to stored TextureAtlasSprite
    // data with extra MaterialIconSet (used to change the path of wire texture for different
    // situation, because we needed some special texture for special MaterialIconSet).
    @Unique
    private final Map<MaterialIconSet, TextureAtlasSprite> gtlitecore$wireTextures = new HashMap<>();

    @Final
    @Shadow
    private TextureAtlasSprite[] insulationTextures;

    /**
     * Register {@code wireTextures} for several {@code MaterialIconSet}.
     *
     * @param map Texture Map, used to register {@code TextureAtlasSprite}.
     * @param ci  Callback Info.
     *
     * @author Magic_Sweepy
     */
    @Inject(method = "registerIcons",
            at = @At(value = "TAIL"))
    private void registerIcons(TextureMap map,
                               CallbackInfo ci)
    {
        // Wire texture location by default, this is used to DULL icon set.
        // path: "gregtech/textures/blocks/cable/wire.png"
        ResourceLocation wireLocation = GTUtility.gregtechId("blocks/cable/wire");
        this.gtlitecore$wireTextures.put(MaterialIconSet.DULL, map.registerSprite(wireLocation));
        // Wire texture locations for several icon sets.
        // path: "gregtech/textures/blocks/material_sets/iconSetName/wire.png"
        for (MaterialIconSet iconSet : MaterialIconSet.ICON_SETS.values())
        {
            ResourceLocation iconSetWireLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/wire");
            this.gtlitecore$wireTextures.put(iconSet, map.registerSprite(iconSetWireLocation));
        }
    }

    /**
     * Build wireGtX/cableGtX renderer with {@code wireTextures}.
     * <p>
     * If you used a {@code MaterialIconSet} with parent, you should add a new
     * wire texture for its location, if not, then this method will not load
     * the original wire texture of this MaterialIconSet's parent IconSet.
     *
     * @param renderContext Common {@code PipeRenderContext} for wireGtX/cableGtX.
     * @param blockPipe     Pipe block with Gregtech contexts in network.
     * @param pipeTile      Pipe tile with Gregtech contexts in network.
     * @param pipeType      The type of pipe (insulation/non-insulation/thickness).
     * @param material      Material of wireGtX/cableGtX.
     * @param ci            Callback Info.
     *
     * @author Magic_Sweepy
     */
    @Inject(method = "buildRenderer",
            at = @At(value = "HEAD"),
            cancellable = true)
    private void buildRenderer(PipeRenderer.PipeRenderContext renderContext,
                               BlockPipe<?, ?, ?> blockPipe,
                               IPipeTile<?, ?> pipeTile,
                               IPipeType<?> pipeType,
                               @Nullable Material material,
                               CallbackInfo ci)
    {
        if (material != null && pipeType instanceof Insulation) {
            int insulationLevel = ((Insulation) pipeType).insulationLevel;
            MaterialIconSet iconSet = material.getMaterialIconSet();
            // Get wire texture from wireTextures, if material do not have MaterialIconSet,
            // then return DULL wire texture (just a prepare method, this situation isn't
            // exist because all material's icon set is based on dull by default).
            TextureAtlasSprite wireTexture = this.gtlitecore$wireTextures.getOrDefault(iconSet,
                    this.gtlitecore$wireTextures.get(MaterialIconSet.DULL));
            // Wire render with new wireTexture, hint: this param is not the original param
            // which named by wireTexture, do not resolved it.
            IVertexOperation wireRender = new IconTransformation(wireTexture);
            // Get wire color via material's RGB property.
            ColourMultiplier wireColor = new ColourMultiplier(
                    GTUtility.convertRGBtoOpaqueRGBA_CL(material.getMaterialRGB()));
            // Get insulation color by default.
            ColourMultiplier insulationColor = new ColourMultiplier(
                    GTUtility.convertRGBtoOpaqueRGBA_CL(4210752));
            if (pipeTile != null)
            {
                if (pipeTile.getPaintingColor() != pipeTile.getDefaultPaintingColor())
                {
                    wireColor.colour = GTUtility.convertRGBtoOpaqueRGBA_CL(
                            pipeTile.getPaintingColor());
                }
                insulationColor.colour = GTUtility.convertRGBtoOpaqueRGBA_CL(
                        pipeTile.getPaintingColor());
            }
            if (insulationLevel != -1)
            {
                if ((renderContext.getConnections() & 63) == 0)
                {
                    renderContext.addOpenFaceRender(false,
                            new IconTransformation(this.insulationTextures[5]),
                            insulationColor);
                    return;
                }
                renderContext.addOpenFaceRender(false,
                                wireRender, wireColor)
                        .addOpenFaceRender(false,
                                new IconTransformation(this.insulationTextures[insulationLevel]),
                                insulationColor)
                        .addSideRender(false,
                                new IconTransformation(this.insulationTextures[5]),
                                insulationColor);
            }
            else
            {
                renderContext.addOpenFaceRender(false,
                                wireRender, wireColor)
                        .addSideRender(false,
                                wireRender, wireColor);
            }
        }
        ci.cancel();
    }

    /**
     * Allowed particle texture loaded texture with {@code MaterialIconSet}.
     *
     * @param pipeTile  Tile of wireGtX/cableGtX.
     * @param cir       Returnable callback info.
     *
     * @author Magic_Sweepy
     */
    @Inject(method = "getParticleTexture*",
            at = @At(value = "RETURN"),
            cancellable = true)
    private void onGetParticleTexture(IPipeTile<?, ?> pipeTile,
                                      CallbackInfoReturnable<Pair<TextureAtlasSprite, Integer>> cir)
    {
        if (pipeTile != null)
        {
            IPipeType<?> pipeType = pipeTile.getPipeType();
            if (pipeType instanceof Insulation)
            {
                Material material = pipeTile instanceof TileEntityMaterialPipeBase
                        ? ((TileEntityMaterialPipeBase<?, ?>) pipeTile).getPipeMaterial() : null;
                int insulationLevel = ((Insulation) pipeType).insulationLevel;
                MaterialIconSet iconSet = material != null ?
                        material.getMaterialIconSet() : MaterialIconSet.DULL;
                TextureAtlasSprite atlasSprite = this.gtlitecore$wireTextures.getOrDefault(iconSet,
                        this.gtlitecore$wireTextures.get(MaterialIconSet.DULL));
                int particleColor;
                if (insulationLevel == -1)
                {
                    particleColor = material == null ? 16777215 : material.getMaterialRGB();
                }
                else
                {
                    atlasSprite = this.insulationTextures[5];
                    particleColor = pipeTile.getPaintingColor();
                }
                cir.setReturnValue(Pair.of(atlasSprite, particleColor));
            }
        }
    }

}
