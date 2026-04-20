package gregtechlite.gtlitecore.client.renderer.handler;

import gregtech.api.items.metaitem.MetaItem;
import gregtechlite.gtlitecore.client.model.WrappedModelGetter;
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer;
import gregtechlite.gtlitecore.client.renderer.UniversiumRenderBehavior;
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.model.IModelState;
import org.lwjgl.opengl.GL11;

public class UniversiumItemRenderer extends WrappedItemRenderer
{

    public UniversiumItemRenderer(IModelState state, IBakedModel model)
    {
        super(state, model);
    }

    public UniversiumItemRenderer(IModelState state, WrappedModelGetter getter)
    {
        super(state, getter);
    }

    @Override
    public void renderItem(ItemStack stack, ItemCameraTransforms.TransformType transformType)
    {
        if (!(stack.getItem() instanceof MetaItem))
        {
            renderModel(wrapped, stack);
            return;
        }

        MetaItem<?>.MetaValueItem valueItem = ((MetaItem<?>) stack.getItem()).getItem(stack);
        UniversiumRenderBehavior urb = null;
        if (valueItem != null)
        {
            urb = (UniversiumRenderBehavior) ((CustomItemRenderer) valueItem).getRendererManager();
        }

        processLightLevel(transformType);

        if (transformType == net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType.GUI)
        {
            renderInventory(stack, renderEntity, urb);
        }
        else
        {
            renderSimple(stack, renderEntity, urb);
        }
    }

    protected void renderSimple(ItemStack stack, EntityLivingBase player, UniversiumRenderBehavior urb)
    {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1F, 1F, 1F, 1F);

        World world = player != null ? player.world : null;
        IBakedModel model = wrapped.getOverrides().handleItemState(wrapped, stack, world, player);

        // Render base model
        renderModel(model, stack);

        // Render cosmic overlay with shader
        if (urb != null) {
            GlStateManager.disableAlpha();
            GlStateManager.depthFunc(GL11.GL_EQUAL);

            CosmicShaderHelper.cosmicOpacity = urb.getCosmicOpacity(stack);
            CosmicShaderHelper.useShader();

            renderModel(model, stack);

            CosmicShaderHelper.releaseShader();

            GlStateManager.depthFunc(GL11.GL_LEQUAL);
            GlStateManager.enableAlpha();
        }

        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    protected void renderInventory(ItemStack stack, EntityLivingBase player, UniversiumRenderBehavior urb)
    {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();

        World world = player != null ? player.world : null;
        IBakedModel model = wrapped.getOverrides().handleItemState(wrapped, stack, world, player);

        // Render base model
        renderModel(model, stack);

        // Render cosmic overlay with shader
        if (urb != null) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.disableAlpha();
            GlStateManager.disableDepth();

            GlStateManager.color(1F, 1F, 1F, 1F);
            CosmicShaderHelper.cosmicOpacity = urb.getCosmicOpacity(stack);
            CosmicShaderHelper.inventoryRender = true;
            CosmicShaderHelper.useShader();

            renderModel(model, stack);

            CosmicShaderHelper.releaseShader();
            CosmicShaderHelper.inventoryRender = false;
            GlStateManager.popMatrix();
        }

        GlStateManager.enableAlpha();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    protected void processLightLevel(ItemCameraTransforms.TransformType transformType)
    {
        switch (transformType)
        {
            case GROUND:
                if (entityPos != null)
                {
                    CosmicShaderHelper.setLightFromLocation(world, entityPos);
                    return;
                }
                break;
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
            case FIRST_PERSON_LEFT_HAND:
            case FIRST_PERSON_RIGHT_HAND:
            case HEAD:
                if (renderEntity != null)
                {
                    CosmicShaderHelper.setLightFromLocation(world, entityPos);
                    return;
                }
                break;
            case GUI:
                CosmicShaderHelper.setLightLevel(1.2F);
                return;
            default:
                break;
        }
        CosmicShaderHelper.setLightLevel(1.0F);
    }
}