package gregtechlite.gtlitecore.client.renderer.handler.item

import codechicken.lib.model.ModelRegistryHelper
import com.morphismmc.morphismlib.client.Games
import gregtechlite.gtlitecore.client.model.WrappedModelGetter
import net.minecraft.client.renderer.EntityRenderer
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.texture.TextureUtil
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.client.model.pipeline.LightUtil
import net.minecraftforge.common.model.IModelState

abstract class WrappedItemRenderer : PerspectiveAwareItemRenderer
{
    protected var wrapped: IBakedModel? = null

    constructor(state: IModelState?, model: IBakedModel?) : super(state)
    {
        wrapped = model
    }

    constructor(state: IModelState?, getter: WrappedModelGetter) : super(state)
    {
        ModelRegistryHelper.registerPreBakeCallback { wrapped = getter.getWrappedModel(it) }
    }

    companion object
    {
        /**
         * Renders a model basically the same as `RenderItem` does, except allows overriding the alpha.
         *
         * @param model         The model to render.
         * @param stack         The stack being renderer. Used for quad tinting.
         * @param alphaOverride The alpha override value (0.0 -> 1.0).
         */
        @JvmOverloads
        fun renderModel(model: IBakedModel, stack: ItemStack, alphaOverride: Float = 1.0f)
        {
            val quads = mutableListOf<BakedQuad>()
            for (face in EnumFacing.VALUES)
                quads.addAll(model.getQuads(null, face, 0))
            quads.addAll(model.getQuads(null, null, 0))
            renderQuads(quads, stack, alphaOverride)
        }

        /**
         * Renders a list of quads basically the same as `RenderItem` does, except allows overriding the alpha.
         *
         * @param quads         The quads to render.
         * @param stack         The stack being renderer. Used for quad tinting.
         * @param alphaOverride The alpha override value (0.0 -> 1.0).
         */
        fun renderQuads(quads: List<BakedQuad>, stack: ItemStack, alphaOverride: Float = 1.0f)
        {
            val itemColors = Games.itemColors()
            val tess = Tessellator.getInstance()
            val buffer = tess.buffer
            buffer.begin(0x07, DefaultVertexFormats.ITEM)
            val alpha = (alphaOverride * 255f).toInt() and 0xFF
            for (quad in quads)
            {
                var color = -1
                if (quad.hasTintIndex())
                {
                    color = itemColors.colorMultiplier(stack, quad.getTintIndex())
                    if (EntityRenderer.anaglyphEnable)
                        color = TextureUtil.anaglyphColor(color)
                }
                color = (color and 0x00FFFFFF) or (alpha shl 24)
                LightUtil.renderQuadColor(buffer, quad, color)
            }
            tess.draw()
        }
    }
}