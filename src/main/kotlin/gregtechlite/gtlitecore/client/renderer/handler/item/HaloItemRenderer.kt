package gregtechlite.gtlitecore.client.renderer.handler.item

import codechicken.lib.colour.Colour
import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.client.model.WrappedModelGetter
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer
import gregtechlite.gtlitecore.client.renderer.HaloRenderBehavior
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.item.ItemStack
import net.minecraftforge.common.model.IModelState
import java.util.Random

class HaloItemRenderer : WrappedItemRenderer
{
    private val random = Random()

    @Suppress("unused")
    constructor(state: IModelState?, model: IBakedModel?) : super(state, model)

    constructor(state: IModelState?, getter: WrappedModelGetter) : super(state, getter)

    override fun renderItem(stack: ItemStack, transformType: ItemCameraTransforms.TransformType?)
    {
        if (stack.item is MetaItem<*>)
        {
            val tess = Tessellator.getInstance()
            val buffer = tess.buffer
            if (transformType == ItemCameraTransforms.TransformType.GUI)
            {
                val valueItem = (stack.item as MetaItem<*>).getItem(stack)
                var renderBehavior: HaloRenderBehavior? = null
                if (valueItem != null)
                {
                    renderBehavior = (valueItem as CustomItemRenderer).rendererManager as HaloRenderBehavior
                }

                if (renderBehavior != null)
                {
                    GlStateManager.pushMatrix()
                    GlStateManager.enableBlend()
                    GlStateManager.disableDepth()
                    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
                    GlStateManager.disableAlpha()

                    if (renderBehavior.shouldDrawHalo())
                    {
                        Colour.glColourARGB(renderBehavior.getHaloColor())
                        val sprite = renderBehavior.getHaloTexture()
                        val spread = renderBehavior.getHaloSize() / 16.0
                        val min = 0.0 - spread
                        val max = 1.0 + spread
                        val minU = sprite?.minU
                        val maxU = sprite?.maxU
                        val minV = sprite?.minV
                        val maxV = sprite?.maxV

                        buffer.begin(0x07, DefaultVertexFormats.POSITION_TEX)
                        buffer.pos(max, max, 0.0).tex(maxU!!.toDouble(), minV!!.toDouble()).endVertex()
                        buffer.pos(min, max, 0.0).tex(minU!!.toDouble(), minV.toDouble()).endVertex()
                        buffer.pos(min, min, 0.0).tex(minU.toDouble(), maxV!!.toDouble()).endVertex()
                        buffer.pos(max, min, 0.0).tex(maxU.toDouble(), maxV.toDouble()).endVertex()

                        tess.draw()
                    }

                    if (renderBehavior.shouldDrawPulse())
                    {
                        GlStateManager.pushMatrix()
                        val scale = random.nextDouble() * 0.15 + 0.95
                        val trans = (1 - scale) / 2
                        GlStateManager.translate(trans, trans, 0.0)
                        GlStateManager.scale(scale, scale, 1.0001)

                        renderModel(wrapped!!, stack, 0.6f)

                        GlStateManager.popMatrix()
                    }
                    renderModel(wrapped!!, stack)

                    GlStateManager.enableAlpha()
                    GlStateManager.enableDepth()
                    GlStateManager.enableRescaleNormal()

                    GlStateManager.disableBlend()
                    GlStateManager.popMatrix()
                }
                else
                {
                    renderModel(wrapped!!, stack)
                }
            }
            else
            {
                renderModel(wrapped!!, stack)
            }
        }
    }
}