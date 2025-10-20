package gregtechlite.gtlitecore.client.renderer.handler

import codechicken.lib.model.ModelRegistryHelper
import com.morphismmc.morphismlib.client.Games
import gregtechlite.gtlitecore.client.model.WrappedModelGetter
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.EntityRenderer
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureUtil
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.registry.IRegistry
import net.minecraftforge.client.model.pipeline.LightUtil
import net.minecraftforge.common.model.IModelState
import java.util.LinkedList

abstract class WrappedItemRenderer : PerspectiveAwareItemRenderer
{

    @JvmField
    protected var wrapped: IBakedModel? = null

    constructor(state: IModelState?, model: IBakedModel?) : super(state)
    {
        this.wrapped = model
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
        @JvmStatic
        @JvmOverloads
        fun renderModel(model: IBakedModel, stack: ItemStack, alphaOverride: Float = 1.0f)
        {
            val itemColorProvider = Games.itemColors()
            val tess = Tessellator.getInstance()
            val buffer = tess.buffer
            buffer.begin(0x07, DefaultVertexFormats.ITEM)
            val quads: MutableList<BakedQuad> = LinkedList<BakedQuad>()
            for (face in EnumFacing.VALUES)
                quads.addAll(model.getQuads(null, face, 0))

            quads.addAll(model.getQuads(null, null, 0))
            val alpha = (alphaOverride * 255f).toInt() and 0xFF
            for (quad in quads)
            {
                var colour = -1
                if (quad.hasTintIndex())
                {
                    colour = itemColorProvider.colorMultiplier(stack, quad.getTintIndex())

                    if (EntityRenderer.anaglyphEnable) colour = TextureUtil.anaglyphColor(colour)
                }
                colour = colour or (alpha shl 24)
                LightUtil.renderQuadColor(buffer, quad, colour)
            }
            tess.draw()
        }
    }

}