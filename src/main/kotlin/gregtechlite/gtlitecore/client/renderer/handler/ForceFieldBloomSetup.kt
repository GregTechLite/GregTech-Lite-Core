package gregtechlite.gtlitecore.client.renderer.handler

import gregtech.client.renderer.IRenderSetup
import gregtech.client.shader.postprocessing.BloomEffect
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.OpenGlHelper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
enum class ForceFieldBloomSetup : IRenderSetup
{

    INSTANCE;

    var lastBrightnessX: Float = 0f
    var lastBrightnessY: Float = 0f

    override fun preDraw(buffer: BufferBuilder)
    {
        BloomEffect.strength = 1.5f
        BloomEffect.baseBrightness = 0.0f
        BloomEffect.highBrightnessThreshold = 1.3f
        BloomEffect.lowBrightnessThreshold = 0.3f
        BloomEffect.step = 1f

        lastBrightnessX = OpenGlHelper.lastBrightnessX
        lastBrightnessY = OpenGlHelper.lastBrightnessY
        GlStateManager.color(1f, 1f, 1f, 1f)
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f)
    }

    override fun postDraw(buffer: BufferBuilder)
    {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY)
    }
}
