package gregtechlite.gtlitecore.client.renderer.handler

import codechicken.lib.model.ModelRegistryHelper
import codechicken.lib.render.CCModelState
import codechicken.lib.render.item.IItemRenderer
import codechicken.lib.util.TransformUtils
import gregtechlite.magicbook.client.Games
import gregtechlite.gtlitecore.common.block.item.DimensionDisplayItemBlock
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import org.lwjgl.opengl.GL11

class DimensionDisplayItemRenderer(location: ModelResourceLocation) : IItemRenderer
{

    private lateinit var model: IBakedModel

    init
    {
        ModelRegistryHelper.registerPreBakeCallback {
            model = it.getObject(location) ?: throw IllegalArgumentException("Model not found")
        }
    }

    companion object
    {
        fun getPrefix(dimName: String) = when (dimName)
        {
            "Ow" -> 1L
            "Ne" -> 2L
            "ED" -> 3L
            else -> 0L
        }
    }

    override fun renderItem(stack: ItemStack,
                            transformType: ItemCameraTransforms.TransformType?)
    {
        if (stack.item is DimensionDisplayItemBlock)
        {
            val renderItem = Games.renderItem()

            val dimension = DimensionDisplayItemBlock.Companion.getDimension(stack)
            if (dimension == null || transformType != ItemCameraTransforms.TransformType.GUI) return

            renderItem.renderItemIntoGUI(stack, 0, 0)

            val fontRenderer = Games.fontRenderer()
            val smallTextScale = 3f / 4f

            GL11.glPushMatrix()
            GL11.glTranslatef(0f, 0f, 300f)
            GL11.glScalef(smallTextScale, smallTextScale, 1.0f)

            val prefix = getPrefix(dimension)
            val tooltipPrefix = if (prefix != -1L) "T$prefix" else "ERROR"
            fontRenderer.drawString(tooltipPrefix, 0f,
                ((16 / smallTextScale).toInt() - fontRenderer.FONT_HEIGHT + 1).toFloat(),
                0xFFFFFF, true)

            GL11.glPopMatrix()
            GL11.glDisable(GL11.GL_ALPHA_TEST)
        }
    }

    override fun getTransforms(): CCModelState? = TransformUtils.DEFAULT_ITEM

    override fun isAmbientOcclusion() = false

    override fun isGui3d() = false

}