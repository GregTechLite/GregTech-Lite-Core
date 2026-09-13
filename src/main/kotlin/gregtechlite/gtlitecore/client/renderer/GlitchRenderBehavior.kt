package gregtechlite.gtlitecore.client.renderer

import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface GlitchRenderBehavior : ItemRendererManager
{
    /**
     * @return The color of the first offset copy drawn by the glitch effect as ARGB.
     */
    @SideOnly(Side.CLIENT)
    fun getGlitchPrimaryColor(): Int

    /**
     * @return The color of the second offset copy drawn by the glitch effect as ARGB.
     */
    @SideOnly(Side.CLIENT)
    fun getGlitchSecondaryColor(): Int

    /**
     * @return The maximum distance, in 1/16 item units, the copies can drift away from the item.
     */
    @SideOnly(Side.CLIENT)
    fun getGlitchMaxOffset(): Double

    /**
     * @return The duration of a single animation frame, in nanoseconds.
     */
    @SideOnly(Side.CLIENT)
    fun getGlitchFrameTimeNanos(): Long

    /**
     * @return The amount of frames in a full glitch loop.
     */
    @SideOnly(Side.CLIENT)
    fun getGlitchLoopFrameCount(): Int

    /**
     * @return The amount of frames at the start of a loop during which the copies are drawn.
     */
    @SideOnly(Side.CLIENT)
    fun getGlitchDurationFrameCount(): Int

    /**
     * @return The interval in frames at which the offsets are randomised.
     */
    @SideOnly(Side.CLIENT)
    fun getGlitchMoveFrameCount(): Int
}
