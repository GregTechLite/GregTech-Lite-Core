package gregtechlite.gtlitecore.client.renderer.handler;

import com.morphismmc.morphismlib.client.Games;
import gregtech.api.items.metaitem.MetaItem;
import gregtechlite.gtlitecore.client.model.WrappedModelGetter;
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer;
import gregtechlite.gtlitecore.client.renderer.TranscendentRenderBehavior;
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.model.IModelState;

public class TranscendentItemRenderer extends WrappedItemRenderer
{

    private long animationTick = 0;

    public TranscendentItemRenderer(IModelState state, IBakedModel model)
    {
        super(state, model);
    }

    public TranscendentItemRenderer(IModelState state, WrappedModelGetter getter)
    {
        super(state, getter);
    }

    @Override
    public void renderItem(ItemStack stack, ItemCameraTransforms.TransformType transformType)
    {
        if (!(stack.getItem() instanceof MetaItem)) {
            renderModel(wrapped, stack);
            return;
        }

        MetaItem<?>.MetaValueItem valueItem = ((MetaItem<?>) stack.getItem()).getItem(stack);
        TranscendentRenderBehavior trb = null;
        if (valueItem != null) {
            trb = (TranscendentRenderBehavior) ((CustomItemRenderer) valueItem).getRendererManager();
        }

        if (trb == null) {
            renderModel(wrapped, stack);
            return;
        }

        processLightLevel(transformType);

        if (transformType == ItemCameraTransforms.TransformType.GUI)
        {
            renderInventory(stack, renderEntity, trb);
        }
        else
        {
            renderSimple(stack, renderEntity, trb);
        }
    }

    protected void renderSimple(ItemStack stack, EntityLivingBase player, TranscendentRenderBehavior trb)
    {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1F, 1F, 1F, 1F);

        World world = player != null ? player.world : null;
        IBakedModel model = wrapped.getOverrides().handleItemState(wrapped, stack, world, player);

        applyEffect(trb);
        renderModel(model, stack);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    protected void renderInventory(ItemStack stack, EntityLivingBase player, TranscendentRenderBehavior trb)
    {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();

        World world = player != null ? player.world : null;
        IBakedModel model = wrapped.getOverrides().handleItemState(wrapped, stack, world, player);

        applyEffect(trb);
        renderModel(model, stack);

        GlStateManager.enableAlpha();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    protected void applyEffect(TranscendentRenderBehavior trb)
    {
        updateAnimationTick();
        float rotation = (animationTick * trb.getRotationSpeed()) % 360;

        GlStateManager.translate(0.5f, 0.5f, 0.0f);

        float[] axis = trb.getRotationAxis();
        GlStateManager.rotate(rotation, axis[0], axis[1], axis[2]);
        GlStateManager.rotate(180, 0.5f, 0.0f, 0.0f);
        GlStateManager.translate(-0.5f, -0.5f, 0.0f);
    }

    private void updateAnimationTick()
    {
        if (Games.world() != null) {
            animationTick = Games.world().getWorldTime() % Integer.MAX_VALUE;
        }
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