package gregtechlite.gtlitecore.integration.jei.drawable

import gregtech.api.gui.resources.TextureArea
import mezz.jei.api.gui.IDrawable
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class TextureAreaDrawable(private val area: TextureArea, private val drawWidth: Int, private val drawHeight: Int) : IDrawable
{
    override fun getWidth(): Int = drawWidth

    override fun getHeight(): Int = drawHeight

    override fun draw(minecraft: Minecraft, xOffset: Int, yOffset: Int)
        = area.draw(xOffset.toDouble(), yOffset.toDouble(), drawWidth, drawHeight)
}

@SideOnly(Side.CLIENT)
fun TextureArea.asJEIDrawable(width: Int, height: Int): IDrawable = TextureAreaDrawable(this, width, height)