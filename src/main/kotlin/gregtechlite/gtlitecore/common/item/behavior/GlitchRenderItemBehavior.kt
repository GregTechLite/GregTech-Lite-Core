package gregtechlite.gtlitecore.common.item.behavior

import codechicken.lib.model.ModelRegistryHelper
import codechicken.lib.util.TransformUtils
import gregtechlite.gtlitecore.client.renderer.GlitchRenderBehavior
import gregtechlite.gtlitecore.client.renderer.handler.item.GlitchItemRenderer
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class GlitchRenderItemBehavior(private val primaryColor: Int = 0xA000DCDC.toInt(),
                               private val secondaryColor: Int = 0xC0FF3232.toInt(),
                               private val maxOffset: Double = 1.7,
                               private val frameTimeNanos: Long = 10_000_000L,
                               private val loopFrameCount: Int = 200,
                               private val durationFrameCount: Int = 40,
                               private val moveFrameCount: Int = 5) : GlitchRenderBehavior
{
    override fun getGlitchPrimaryColor(): Int = primaryColor

    override fun getGlitchSecondaryColor(): Int = secondaryColor

    override fun getGlitchMaxOffset(): Double = maxOffset

    override fun getGlitchFrameTimeNanos(): Long = frameTimeNanos

    override fun getGlitchLoopFrameCount(): Int = loopFrameCount

    override fun getGlitchDurationFrameCount(): Int = durationFrameCount

    override fun getGlitchMoveFrameCount(): Int = moveFrameCount

    @SideOnly(Side.CLIENT)
    override fun onRendererRegistry(location: ResourceLocation)
    {
        ModelRegistryHelper.register(ModelResourceLocation(location, "inventory"),
            GlitchItemRenderer(TransformUtils.DEFAULT_ITEM) {
                it.getObject(ModelResourceLocation(location, "inventory"))
            })
    }
}
