package gregtechlite.gtlitecore.client.renderer.handler.item

import codechicken.lib.model.ItemQuadBakery
import codechicken.lib.model.bakedmodels.ModelProperties
import codechicken.lib.model.bakedmodels.PerspectiveAwareBakedModel
import codechicken.lib.util.ResourceUtils
import codechicken.lib.util.TransformUtils
import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.client.model.WrappedModelGetter
import gregtechlite.gtlitecore.client.renderer.MaskUniversiumRenderBehavior
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraftforge.common.model.IModelState
import org.lwjgl.opengl.GL11

class MaskUniversiumItemRenderer : WrappedItemRenderer
{
    @Suppress("unused")
    constructor(state: IModelState?, wrapped: IBakedModel?): super(state, wrapped)

    constructor(state: IModelState?, getter: WrappedModelGetter) : super(state, getter)

    companion object
    {
        private val spriteQuadCache = hashMapOf<TextureAtlasSprite?, IBakedModel>()

        init
        {
            ResourceUtils.registerReloadListener { _ -> spriteQuadCache.clear() }
        }

        private fun computeModel(sprite: TextureAtlasSprite): IBakedModel
        {
            val quads = ItemQuadBakery.bakeItem(listOf(sprite))
            return PerspectiveAwareBakedModel(quads, TransformUtils.DEFAULT_ITEM, ModelProperties(true, false))
        }
    }

    override fun renderItem(item: ItemStack, transformType: TransformType)
    {
        processLightLevel(transformType)
        if (transformType == TransformType.GUI)
        {
            renderInventory(item, renderEntity)
        }
        else
        {
            renderSimple(item, renderEntity)
        }
    }

    private fun renderSimple(stack: ItemStack, player: EntityLivingBase?)
    {
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.color(1f, 1f, 1f, 1f)

        val world = player?.world
        val model = wrapped!!.overrides.handleItemState(wrapped!!, stack, world, player)
        renderModel(model, stack)

        val valueItem = (stack.item as MetaItem<*>).getItem(stack)
        var renderBehavior: MaskUniversiumRenderBehavior? = null
        if (valueItem != null)
        {
            renderBehavior = (valueItem as CustomItemRenderer).rendererManager as MaskUniversiumRenderBehavior
        }
        if (renderBehavior != null)
        {
            GlStateManager.disableAlpha()
            GlStateManager.depthFunc(GL11.GL_EQUAL)

            val cosmicSprite = renderBehavior.getMaskTexture(stack, player)
            val cosmicModel = spriteQuadCache.computeIfAbsent(cosmicSprite) { computeModel(it!!) }

            CosmicShaderHelper.cosmicOpacity = renderBehavior.getMaskOpacity(stack, player)
            CosmicShaderHelper.useShader()

            renderModel(cosmicModel, stack)

            CosmicShaderHelper.releaseShader()

            GlStateManager.depthFunc(GL11.GL_LEQUAL)
            GlStateManager.enableAlpha()
        }
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }

    private fun renderInventory(stack: ItemStack, player: EntityLivingBase?)
    {
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.disableAlpha()
        GlStateManager.disableDepth()

        val world = player?.world
        val model = wrapped!!.overrides.handleItemState(wrapped!!, stack, world, player)
        renderModel(model, stack)

        val valueItem = (stack.item as MetaItem<*>).getItem(stack)
        var renderBehavior: MaskUniversiumRenderBehavior? = null
        if (valueItem != null)
        {
            renderBehavior = (valueItem as CustomItemRenderer).rendererManager as MaskUniversiumRenderBehavior
        }

        if (renderBehavior != null)
        {
            GlStateManager.pushMatrix()
            GlStateManager.enableBlend()
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
            GlStateManager.disableAlpha()
            GlStateManager.disableDepth()

            val sprite = renderBehavior.getMaskTexture(stack, player)
            val cosmicModel = spriteQuadCache.computeIfAbsent(sprite) { computeModel(it!!) }

            GlStateManager.color(1f, 1f, 1f, 1f)
            CosmicShaderHelper.cosmicOpacity = renderBehavior.getMaskOpacity(stack, player)
            CosmicShaderHelper.inventoryRender = true
            CosmicShaderHelper.useShader()

            renderModel(cosmicModel, stack)

            CosmicShaderHelper.releaseShader()
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
            // Ground
            TransformType.GROUND -> {
                if (entityPos != null)
                {
                    CosmicShaderHelper.setLightFromLocation(world, entityPos!!)
                    return
                }
            }
            // Player
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
            // Gui
            TransformType.GUI ->
            {
                CosmicShaderHelper.setLightLevel(1.2f)
                return
            }
            else -> {}
        }
        CosmicShaderHelper.setLightLevel(1.0f)
    }
}