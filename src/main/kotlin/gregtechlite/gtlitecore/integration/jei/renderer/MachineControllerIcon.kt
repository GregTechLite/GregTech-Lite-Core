package gregtechlite.gtlitecore.integration.jei.renderer

import gregtech.api.metatileentity.MetaTileEntity
import mezz.jei.api.gui.IDrawable
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * A JEI icon which renders the controller block as a 3D item icon, and
 * optionally overlays in the bottom-right corner of the icon.
 *
 * @param metaTileEntity The machine whose controller block will be drawn.
 * @param badge          The overlay drawn in the bottom-right corner, or `null` for no overlay.
 * @param iconWidth      The icon width in pixels.
 * @param iconHeight     The icon height in pixels.
 * @param badgeInset     The distance in pixels between the badge and the icon's bottom-right corner.
 */
@SideOnly(Side.CLIENT)
class MachineControllerIcon(metaTileEntity: MetaTileEntity, private val badge: IDrawable? = null,
                            private val iconWidth: Int = 16, private val iconHeight: Int = 16,
                            private val badgeInset: Int = 0) : IDrawable
{
    private val controller: ItemStack by lazy { metaTileEntity.stackForm }

    override fun getWidth(): Int = iconWidth

    override fun getHeight(): Int = iconHeight

    override fun draw(minecraft: Minecraft, xOffset: Int, yOffset: Int)
    {
        GlStateManager.pushAttrib()

        GlStateManager.enableDepth()
        RenderHelper.enableGUIStandardItemLighting()
        minecraft.renderItem.renderItemAndEffectIntoGUI(null, controller,
                                                        xOffset + (iconWidth - 16) / 2, yOffset + (iconHeight - 16) / 2)
        RenderHelper.disableStandardItemLighting()

        badge?.let {
            GlStateManager.disableDepth()
            GlStateManager.enableAlpha()
            GlStateManager.enableBlend()
            GlStateManager.enableTexture2D()
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                                                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                                                GlStateManager.SourceFactor.ONE,
                                                GlStateManager.DestFactor.ZERO)
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)

            GlStateManager.pushMatrix()
            GlStateManager.translate(0.0f, 0.0f, 100.0f)
            it.draw(minecraft,
                    xOffset + iconWidth - it.width - badgeInset,
                    yOffset + iconHeight - it.height - badgeInset)
            GlStateManager.popMatrix()
        }

        GlStateManager.popAttrib()
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
    }
}

@SideOnly(Side.CLIENT)
fun MetaTileEntity.controllerIcon(badge: IDrawable? = null, width: Int = 16, height: Int = 16, badgeInset: Int = 0): MachineControllerIcon
    = MachineControllerIcon(this, badge, width, height, badgeInset)