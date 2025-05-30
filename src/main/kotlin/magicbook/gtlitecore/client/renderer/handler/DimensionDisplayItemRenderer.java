package magicbook.gtlitecore.client.renderer.handler;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.render.item.IItemRenderer;
import magicbook.gtlitecore.client.model.IWrappedModelGetter;
import magicbook.gtlitecore.common.block.itemblocks.DimensionDisplayItemBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.IModelState;
import org.lwjgl.opengl.GL11;

public class DimensionDisplayItemRenderer implements IItemRenderer
{

    protected IBakedModel wrapped;
    private final IModelState state;

    public DimensionDisplayItemRenderer(IModelState state,
                                        IWrappedModelGetter getter)
    {
        this.state = state;
        ModelRegistryHelper.registerPreBakeCallback(modelRegistry -> wrapped = getter.getWrappedModel(modelRegistry));
    }

    @Override
    public void renderItem(ItemStack stack,
                           ItemCameraTransforms.TransformType transformType)
    {
        if (stack.getItem() instanceof DimensionDisplayItemBlock)
        {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

            String dimension = DimensionDisplayItemBlock.getDimension(stack);
            if (dimension == null || transformType != ItemCameraTransforms.TransformType.GUI)
                return;

            renderItem.renderItemIntoGUI(stack, 0, 0);

            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            float smallTextScale = 3F / 4F;

            GL11.glPushMatrix();
            GL11.glTranslatef(0, 0, 300);
            GL11.glScalef(smallTextScale, smallTextScale, 1.0f);

            long prefix = getPrefix(dimension);
            String tooltipPrefix = prefix != -1 ? "T" + prefix : "ERROR";
            fontRenderer.drawString(tooltipPrefix, 0,
                    (int) (16 / smallTextScale) - fontRenderer.FONT_HEIGHT + 1,
                    0xFFFFFF, true);

            GL11.glPopMatrix();
            GL11.glDisable(GL11.GL_ALPHA_TEST);
        }
    }

    public static long getPrefix(String dimName)
    {
        switch (dimName)
        {
            case "Ow":
                return 1;
            case "Ne":
                return 2;
            case "ED":
                return 3;
        }
        return 0;
    }

    @Override
    public IModelState getTransforms()
    {
        return state;
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return false;
    }

    @Override
    public boolean isGui3d()
    {
        return false;
    }

}
