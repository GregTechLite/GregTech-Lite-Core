package gregtechlite.gtlitecore.client.renderer

import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface HaloRenderBehavior : ItemRendererManager
{
    /**
     * The mask texture where the halo overlay will be.
     *
     * @return The masked area and extra area of halo texture will be.
     */
    @SideOnly(Side.CLIENT)
    fun getHaloTexture(): TextureAtlasSprite?

    /**
     * The color of halo texture render.
     *
     * @return The color which will be rendered on halo texture.
     */
    @SideOnly(Side.CLIENT)
    fun getHaloColor(): Int

    /**
     * The size of total halo texture render.
     *
     * @return The rendered size of all halo texture otherwise main and extra area.
     */
    @SideOnly(Side.CLIENT)
    fun getHaloSize(): Int

    /**
     * Check if the item required render halo texture.
     *
     * @return If required render halo texture current, then returns `true`,
     *         otherwise returns `false`.
     */
    @SideOnly(Side.CLIENT)
    fun shouldDrawHalo(): Boolean

    /**
     * Check if the halo texture should draw a little dynamic pulse animation.
     *
     * @return If required this animation, then returns `true`, otherwise
     *         returns `false`.
     */
    @SideOnly(Side.CLIENT)
    fun shouldDrawPulse(): Boolean
}