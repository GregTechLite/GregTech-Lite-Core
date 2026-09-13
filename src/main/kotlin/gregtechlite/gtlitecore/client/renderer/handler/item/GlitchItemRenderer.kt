package gregtechlite.gtlitecore.client.renderer.handler.item

import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.client.model.WrappedModelGetter
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer
import gregtechlite.gtlitecore.client.renderer.GlitchRenderBehavior
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType
import net.minecraft.item.ItemStack
import net.minecraftforge.common.model.IModelState
import java.util.Random
import kotlin.math.sign

class GlitchItemRenderer : WrappedItemRenderer
{
    private val random = Random()

    private var primaryOffset = 0.0
    private var secondaryOffset = 0.0

    @Suppress("unused")
    constructor(state: IModelState?, model: IBakedModel?) : super(state, model)

    constructor(state: IModelState?, getter: WrappedModelGetter) : super(state, getter)

    override fun renderItem(stack: ItemStack, transformType: TransformType?)
    {
        if (stack.item !is MetaItem<*>)
        {
            renderModel(wrapped!!, stack)
            return
        }

        val valueItem = (stack.item as MetaItem<*>).getItem(stack)
        val renderBehavior = (valueItem as? CustomItemRenderer)?.rendererManager as? GlitchRenderBehavior
        if (renderBehavior == null)
        {
            renderModel(wrapped!!, stack)
            return
        }

        val frameTimeNanos = renderBehavior.getGlitchFrameTimeNanos()
        val loopLength = frameTimeNanos * renderBehavior.getGlitchLoopFrameCount()
        val currentFrame = (System.nanoTime().mod(loopLength) / frameTimeNanos).toInt()
        val glitching = currentFrame <= renderBehavior.getGlitchDurationFrameCount()

        if (glitching && currentFrame % renderBehavior.getGlitchMoveFrameCount() == 0)
        {
            val maxOffset = renderBehavior.getGlitchMaxOffset()
            primaryOffset = random.nextDouble() * maxOffset * random.nextGaussian().sign
            secondaryOffset = random.nextDouble() * maxOffset * random.nextGaussian().sign
        }

        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)

        renderModel(wrapped!!, stack)

        if (glitching)
        {
            GlStateManager.disableDepth()
            renderGlitchCopy(renderBehavior.getGlitchPrimaryColor(), primaryOffset)
            renderGlitchCopy(renderBehavior.getGlitchSecondaryColor(), secondaryOffset)
            GlStateManager.enableDepth()
        }

        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }

    private fun renderGlitchCopy(color: Int, offset: Double)
    {
        GlStateManager.pushMatrix()
        GlStateManager.translate(offset / 16.0, offset / 16.0, 0.0)
        renderModelColored(wrapped!!, color)
        GlStateManager.popMatrix()
    }
}
