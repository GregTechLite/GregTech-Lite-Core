package gregtechlite.gtlitecore.client.renderer.handler.item

import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.client.model.WrappedModelGetter
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer
import gregtechlite.gtlitecore.client.renderer.UniversiumRenderBehavior
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper.releaseShader
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper.setLightLevel
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper.useShader
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraftforge.common.model.IModelState
import org.lwjgl.opengl.GL11

class UniversiumItemRenderer : WrappedItemRenderer
{
    @Suppress("unused")
    constructor(state: IModelState?, model: IBakedModel?): super(state, model)

    constructor(state: IModelState?, getter: WrappedModelGetter): super(state, getter)

    override fun renderItem(stack: ItemStack, transformType: TransformType?)
    {
        if (stack.item !is MetaItem<*>)
        {
            renderModel(wrapped!!, stack)
            return
        }

        val valueItem = (stack.item as MetaItem<*>).getItem(stack)
        var renderBehavior: UniversiumRenderBehavior? = null
        if (valueItem != null)
        {
            renderBehavior = (valueItem as CustomItemRenderer).rendererManager as UniversiumRenderBehavior
        }

        processLightLevel(transformType!!)

        if (transformType == TransformType.GUI)
        {
            renderInventory(stack, renderEntity, renderBehavior)
        }
        else
        {
            renderSimple(stack, renderEntity, renderBehavior)
        }
    }

    private fun renderSimple(stack: ItemStack, player: EntityLivingBase?, renderBehavior: UniversiumRenderBehavior?)
    {
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.color(1f, 1f, 1f, 1f)

        val world = player?.world
        val model = wrapped!!.overrides.handleItemState(wrapped!!, stack, world, player)
        renderModel(model, stack)

        if (renderBehavior != null)
        {
            GlStateManager.disableAlpha()
            GlStateManager.depthFunc(GL11.GL_EQUAL)

            CosmicShaderHelper.cosmicOpacity = renderBehavior.getCosmicOpacity(stack)
            useShader()
            renderModel(model, stack)
            releaseShader()

            GlStateManager.depthFunc(GL11.GL_LEQUAL)
            GlStateManager.enableAlpha()
        }

        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }

    private fun renderInventory(stack: ItemStack, player: EntityLivingBase?, renderBehavior: UniversiumRenderBehavior?)
    {
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.disableAlpha()
        GlStateManager.disableDepth()

        val world = player?.world
        val model = wrapped!!.overrides.handleItemState(wrapped!!, stack, world, player)
        renderModel(model, stack)

        if (renderBehavior != null)
        {
            GlStateManager.pushMatrix()
            GlStateManager.enableBlend()
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
            GlStateManager.disableAlpha()
            GlStateManager.disableDepth()
            GlStateManager.color(1f, 1f, 1f, 1f)

            CosmicShaderHelper.cosmicOpacity = renderBehavior.getCosmicOpacity(stack)
            CosmicShaderHelper.inventoryRender = true
            useShader()
            renderModel(model, stack)
            releaseShader()
            CosmicShaderHelper.inventoryRender = false

            GlStateManager.popMatrix()
        }
        GlStateManager.enableAlpha()
        GlStateManager.enableRescaleNormal()
        GlStateManager.enableDepth()
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }

    private fun processLightLevel(transformType: TransformType)
    {
        when (transformType)
        {
            TransformType.GROUND -> {
                if (entityPos != null)
                {
                    CosmicShaderHelper.setLightFromLocation(world, entityPos!!)
                    return
                }
            }

            TransformType.THIRD_PERSON_LEFT_HAND,
            TransformType.THIRD_PERSON_RIGHT_HAND,
            TransformType.FIRST_PERSON_LEFT_HAND,
            TransformType.FIRST_PERSON_RIGHT_HAND,
            TransformType.HEAD -> {
                if (renderEntity != null)
                {
                    CosmicShaderHelper.setLightFromLocation(world, entityPos!!)
                    return
                }
            }
            TransformType.GUI ->
            {
                setLightLevel(1.2f)
                return
            }
            else -> {}
        }
        setLightLevel(1.0f)
    }
}