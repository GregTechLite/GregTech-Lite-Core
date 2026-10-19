package gregtechlite.gtlitecore.mixins.hooks

import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.renderer.GlStateManager
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.Locale

@SideOnly(Side.CLIENT)
@Hook
object ItemCountRenderer
{
    @JvmStatic
    fun renderCount(fontRenderer: FontRenderer, count: Int, x: Int, y: Int)
    {
        if (count == 1)
            return

        val text = formatCount(count)
        val shouldScale = count > 99

        GlStateManager.disableLighting()
        GlStateManager.disableDepth()
        GlStateManager.disableBlend()

        if (shouldScale)
        {
            GlStateManager.pushMatrix()
            GlStateManager.scale(0.5f, 0.5f, 1.0f)
        }

        val posX = if (shouldScale) (x + 16) * 2 - fontRenderer.getStringWidth(text)
                   else x + 16 - fontRenderer.getStringWidth(text)
        val posY = if (shouldScale) (y + 16) * 2 - 8 else y + 16 - 8
        fontRenderer.drawStringWithShadow(text, posX.toFloat(), posY.toFloat(), 0xFFFFFF)

        if (shouldScale)
            GlStateManager.popMatrix()

        GlStateManager.enableLighting()
        GlStateManager.enableDepth()
        GlStateManager.enableBlend()
    }

    @JvmStatic
    fun formatCount(count: Int): String = when
    {
        count <= 9_999       -> count.toString()
        count <= 999_999     -> compact(count / 1_000f, "k")
        count <= 999_999_999 -> compact(count / 1_000_000f, "m")
        else                 -> compact(count / 1_000_000_000f, "g")
    }

    private fun compact(value: Float, suffix: String): String
    {
        val digits = if (value % 1f == 0f) value.toInt().toString() else "%.1f".format(Locale.ROOT, value)
        return digits + suffix
    }
}
