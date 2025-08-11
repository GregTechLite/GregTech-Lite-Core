package gregtechlite.gtlitecore.client.renderer.handler

import gregtech.client.renderer.IRenderSetup
import gregtech.client.shader.postprocessing.BloomEffect
import gregtech.common.ConfigHolder
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.OpenGlHelper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
enum class FusionBloomSetup : IRenderSetup
{

    INSTANCE;

    var lastBrightnessX: Float = 0f
    var lastBrightnessY: Float = 0f

    override fun preDraw(buffer: BufferBuilder)
    {
        BloomEffect.strength = ConfigHolder.client.shader.fusionBloom.strength.toFloat()
        BloomEffect.baseBrightness = ConfigHolder.client.shader.fusionBloom.baseBrightness.toFloat()
        BloomEffect.highBrightnessThreshold = ConfigHolder.client.shader.fusionBloom.highBrightnessThreshold.toFloat()
        BloomEffect.lowBrightnessThreshold = ConfigHolder.client.shader.fusionBloom.lowBrightnessThreshold.toFloat()
        BloomEffect.step = 1.0f
        this.lastBrightnessX = OpenGlHelper.lastBrightnessX
        this.lastBrightnessY = OpenGlHelper.lastBrightnessY
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f)
        GlStateManager.disableTexture2D()
    }

    override fun postDraw(buffer: BufferBuilder)
    {
        GlStateManager.enableTexture2D()
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, this.lastBrightnessX, this.lastBrightnessY)
    }

}