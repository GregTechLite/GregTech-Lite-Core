package gregtechlite.gtlitecore.client.renderer.handler.item

import com.morphismmc.morphismlib.client.Games
import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.client.model.WrappedModelGetter
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer
import gregtechlite.gtlitecore.client.renderer.TranscendentRenderBehavior
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraftforge.common.model.IModelState

class TranscendentItemRenderer : WrappedItemRenderer
{
    private var animationTick = 0L

    @Suppress("unused")
    constructor(state: IModelState?, model: IBakedModel?) : super(state, model)

    constructor(state: IModelState?, getter: WrappedModelGetter) : super(state, getter)

    override fun renderItem(stack: ItemStack, transformType: ItemCameraTransforms.TransformType?)
    {
        if (stack.item is MetaItem<*>)
        {
            val valueItem = (stack.item as MetaItem<*>).getItem(stack)
            var renderBehavior: TranscendentRenderBehavior? = null
            if (valueItem != null)
            {
                renderBehavior = (valueItem as? CustomItemRenderer)?.rendererManager as? TranscendentRenderBehavior
            }
            if (renderBehavior == null)
            {
                renderModel(wrapped!!, stack)
                return
            }

            if (transformType == ItemCameraTransforms.TransformType.GUI)
            {
                renderInventory(stack, renderEntity, renderBehavior)
            }
            else
            {
                renderSimple(stack, renderEntity, renderBehavior)
            }
        }
    }

    private fun renderSimple(stack: ItemStack, player: EntityLivingBase?, renderBehavior: TranscendentRenderBehavior)
    {
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.color(1f, 1f, 1f, 1f)

        val world = player?.world
        val model = wrapped!!.overrides.handleItemState(wrapped!!, stack, world, player)
        renderRotationEffect(renderBehavior)
        renderModel(model, stack)

        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }

    private fun renderInventory(stack: ItemStack, player: EntityLivingBase?, renderBehavior: TranscendentRenderBehavior)
    {
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.disableAlpha()
        GlStateManager.disableDepth()

        val world = player?.world
        val model = wrapped!!.overrides.handleItemState(wrapped!!, stack, world, player)
        renderRotationEffect(renderBehavior)
        renderModel(model, stack)

        GlStateManager.enableAlpha()
        GlStateManager.enableRescaleNormal()
        GlStateManager.enableDepth()
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }

    private fun renderRotationEffect(renderBehavior: TranscendentRenderBehavior)
    {
        updateAnimationTick()
        val rotation = (animationTick * renderBehavior.getRotationSpeed()) % 360
        GlStateManager.translate(0.5f, 0.5f, 0.0f)
        val axis = renderBehavior.getRotationAxis()
        GlStateManager.rotate(rotation, axis[0], axis[1], axis[2])
        GlStateManager.rotate(180f, 0.5f, 0.0f, 0.0f)
        GlStateManager.translate(-0.5f, -0.5f, 0.0f)
        GlStateManager.translate(0.0f, 0.0f, renderBehavior.getFloatingOffset())
    }

    private fun updateAnimationTick()
    {
        Games.world()?.let { animationTick = Games.world()!!.worldTime % Int.MAX_VALUE }
    }
}