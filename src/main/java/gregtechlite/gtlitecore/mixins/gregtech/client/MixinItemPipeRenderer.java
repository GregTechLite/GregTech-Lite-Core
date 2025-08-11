package gregtechlite.gtlitecore.mixins.gregtech.client;

import codechicken.lib.vec.uv.IconTransformation;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import gregtech.api.pipenet.block.BlockPipe;
import gregtech.api.pipenet.block.IPipeType;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.pipe.ItemPipeRenderer;
import gregtech.client.renderer.pipe.PipeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.pipelike.itempipe.ItemPipeType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Feature: Add {@link MaterialIconSet} supported for {@link ItemPipeRenderer}.
 * <p>
 * This class implement a feature that make material pipes rendered its iconSet textures
 * but not default textures.
 * <p>
 * WARNING: This change will cause the original pipes textures be broken, should add
 * correspondenced iconSet textures to all iconSets.
 *
 * @author Magic_Sweepy
 */
@Mixin(value = ItemPipeRenderer.class, remap = false)
public abstract class MixinItemPipeRenderer
{

    @Unique
    private final Table<ItemPipeType, MaterialIconSet, TextureAtlasSprite> gtlitecore$pipeTextures = HashBasedTable.create();

    @Unique
    private final Map<MaterialIconSet, TextureAtlasSprite> gtlitecore$pipeSideTextures = new HashMap<>();

    @Inject(method = "registerIcons",
            at = @At(value = "TAIL"))
    private void registerIcons(TextureMap map,
                               CallbackInfo ci)
    {
        for (MaterialIconSet iconSet : MaterialIconSet.ICON_SETS.values())
        {
            ResourceLocation iconSetPipeSmallLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_small_in");
            ResourceLocation iconSetPipeNormalLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_normal_in");
            ResourceLocation iconSetPipeLargeLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_large_in");
            ResourceLocation iconSetPipeHugeLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_huge_in");
            ResourceLocation iconSetPipeSideLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_side");

            this.gtlitecore$pipeTextures.put(ItemPipeType.SMALL, iconSet, map.registerSprite(iconSetPipeSmallLocation));
            this.gtlitecore$pipeTextures.put(ItemPipeType.NORMAL, iconSet, map.registerSprite(iconSetPipeNormalLocation));
            this.gtlitecore$pipeTextures.put(ItemPipeType.LARGE, iconSet, map.registerSprite(iconSetPipeLargeLocation));
            this.gtlitecore$pipeTextures.put(ItemPipeType.HUGE, iconSet, map.registerSprite(iconSetPipeHugeLocation));

            this.gtlitecore$pipeTextures.put(ItemPipeType.RESTRICTIVE_SMALL, iconSet, map.registerSprite(iconSetPipeSmallLocation));
            this.gtlitecore$pipeTextures.put(ItemPipeType.RESTRICTIVE_NORMAL, iconSet, map.registerSprite(iconSetPipeNormalLocation));
            this.gtlitecore$pipeTextures.put(ItemPipeType.RESTRICTIVE_LARGE, iconSet, map.registerSprite(iconSetPipeLargeLocation));
            this.gtlitecore$pipeTextures.put(ItemPipeType.RESTRICTIVE_HUGE, iconSet, map.registerSprite(iconSetPipeHugeLocation));

            this.gtlitecore$pipeSideTextures.put(iconSet, map.registerSprite(iconSetPipeSideLocation));
        }
    }

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
        if (material != null && pipeType instanceof ItemPipeType)
        {
            MaterialIconSet iconSet = material.getMaterialIconSet();
            TextureAtlasSprite pipeTexture = this.gtlitecore$pipeTextures.get(pipeType, iconSet);
            TextureAtlasSprite pipeSideTexture = this.gtlitecore$pipeSideTextures.get(iconSet);
            renderContext.addOpenFaceRender(new IconTransformation(pipeTexture))
                    .addSideRender(new IconTransformation(pipeSideTexture));
            if (((ItemPipeType) pipeType).isRestrictive())
            {
                renderContext.addSideRender(false, new IconTransformation(Textures.RESTRICTIVE_OVERLAY));
            }
        }

        ci.cancel();
    }

}
