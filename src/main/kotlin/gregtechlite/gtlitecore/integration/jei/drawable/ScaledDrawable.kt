package gregtechlite.gtlitecore.integration.jei.drawable

import mezz.jei.api.gui.IDrawable
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class ScaledDrawable(private val delegate: IDrawable, private val drawWidth: Int, private val drawHeight: Int) : IDrawable
{
    override fun getWidth(): Int = drawWidth

    override fun getHeight(): Int = drawHeight

    override fun draw(minecraft: Minecraft, xOffset: Int, yOffset: Int)
    {
        GlStateManager.pushMatrix()
        GlStateManager.translate(xOffset.toFloat(), yOffset.toFloat(), 0.0f)
        GlStateManager.scale(drawWidth.toFloat() / delegate.width,
                             drawHeight.toFloat() / delegate.height, 1.0f)
        delegate.draw(minecraft, 0, 0)
        GlStateManager.popMatrix()
    }
}

@SideOnly(Side.CLIENT)
fun IDrawable.scaled(width: Int, height: Int): IDrawable = ScaledDrawable(this, width, height)