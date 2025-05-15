package magicbook.gtlitecore.client.renderer.handler;

import gregtech.client.shader.postprocessing.BloomEffect;
import magicbook.gtlitecore.client.shader.IBloomRenderFast;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

@SideOnly(Side.CLIENT)
public enum ForceFieldRenderer implements IBloomRenderFast
{

    INSTANCE;

    @Override
    public int customBloomStyle()
    {
        return 2;
    }

    float lastBrightnessX;
    float lastBrightnessY;

    @SideOnly(Side.CLIENT)
    @Override
    public void preDraw(@NotNull BufferBuilder buffer)
    {
        BloomEffect.strength = 1.5F;
        BloomEffect.baseBrightness = 0.0F;
        BloomEffect.highBrightnessThreshold = 1.3F;
        BloomEffect.lowBrightnessThreshold = 0.3F;
        BloomEffect.step = 1;

        lastBrightnessX = OpenGlHelper.lastBrightnessX;
        lastBrightnessY = OpenGlHelper.lastBrightnessY;
        GlStateManager.color(1, 1, 1, 1);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void postDraw(@NotNull BufferBuilder buffer)
    {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
    }

}
