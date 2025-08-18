package gregtechlite.gtlitecore.client.renderer;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface HaloRenderBehavior extends ItemRendererManager
{

    /**
     * Check if item required render Halo texture.
     *
     * @return If required render Halo texture in current situation, then return true.
     */
    @SideOnly(Side.CLIENT)
    boolean shouldDrawHalo();

    /**
     * The mask where the halo overlay will be.
     *
     * @return The masked area and extra area of halo texture will be.
     */
    @SideOnly(Side.CLIENT)
    TextureAtlasSprite getHaloTexture();

    /**
     * The color of Halo texture render.
     *
     * @return Color of the halo texture.
     */
    @SideOnly(Side.CLIENT)
    int getHaloColor();

    /**
     * The size of total Halo texture renderer.
     *
     * @return Renderer size of Halo texture.
     */
    @SideOnly(Side.CLIENT)
    int getHaloSize();

    /**
     * Check if halo texture should draw a little dynamic pulse.
     *
     * @return If required, then return true.
     */
    @SideOnly(Side.CLIENT)
    boolean shouldDrawPulse();

}