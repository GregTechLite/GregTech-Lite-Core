package magicbook.gtlitecore.mixin.gregtech;

import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.uv.IconTransformation;
import com.google.common.base.Preconditions;
import gregtech.client.renderer.pipe.PipeRenderer;
import gregtech.client.renderer.texture.Textures;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

// FIXME TODO Fix it completely.
@Mixin(value = PipeRenderer.class, remap = false)
public class PipeRendererMixin
{

    @Shadow
    @Final
    private static Int2ObjectMap<IVertexOperation[]> RESTRICTOR_MAP;

    /**
     * Pseudo-overwrite the {@code addRestrictor} method to fix it {@code NullPointerException} problem
     * when CCL loading the sprite.
     *
     * @param sprite
     * @param borders
     */
    private static void addRestrictor(TextureAtlasSprite sprite, PipeRenderer.Border... borders)
    {
        int mask = 0;
        for (PipeRenderer.Border border : borders)
        {
            mask|= border.mask;
        }
        // TODO FIXME This is still has a sprite error but it will not crash CCL...
        if (sprite == null) // Add a null check for sprites.
        {
            // Do nothing if sprite is null.
        }
        else
        {
            RESTRICTOR_MAP.put(mask, new IVertexOperation[] { new IconTransformation(sprite) });
        }
    }

}
