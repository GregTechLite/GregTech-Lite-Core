@file:Suppress("SameParameterValue")

package gregtechlite.gtlitecore.client.renderer.handler.tesr

import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.BottlecrateUtils
import gregtechlite.gtlitecore.common.tileentity.TileEntityBlockcrate
import net.minecraft.block.BlockHorizontal
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.item.ItemStack
import gregtechlite.gtlitecore.common.item.behavior.BottlecrateBehavior
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.GL11

@SideOnly(Side.CLIENT)
class TESRBottlecrate : TileEntitySpecialRenderer<TileEntityBlockcrate>()
{

    override fun render(te: TileEntityBlockcrate, x: Double, y: Double, z: Double,
                        partialTicks: Float, destroyStage: Int, alpha: Float)
    {
        val state = te.world.getBlockState(te.pos)
        val facing = state.getValue(BlockHorizontal.FACING)

        te.inventory.forEachIndexed { i, bottleStack ->
            if (!bottleStack.isEmpty)
            {
                val physIdx = BottlecrateUtils.getPhysicalIndex(i, facing)
                renderBottleToSlot(x, y, z, physIdx, bottleStack)
            }
        }
    }

    fun renderBottleToSlot(x: Double, y: Double, z: Double, physIdx: Int, bottleStack: ItemStack)
    {
        val (xOffset, yOffset, zOffset) = BottlecrateUtils.getStartPoint(physIdx)

        val handler = FluidUtil.getFluidHandler(bottleStack)
        val canInsertInto = BottlecrateBehavior.canInsertInto(bottleStack)
        if (handler == null && !canInsertInto) return

        val fxOffset = xOffset + BottlecrateUtils.FLUID_MARGIN
        val fyOffset = yOffset + BottlecrateUtils.FLUID_MARGIN
        val fzOffset = zOffset + BottlecrateUtils.FLUID_MARGIN

        if (handler != null)
        {
            val fluidStack = handler.tankProperties[0].contents
            val capacity = handler.tankProperties[0].capacity.toDouble()
            if (fluidStack != null)
            {
                val color = fluidStack.fluid?.color ?: 0x00000000
                val amount = fluidStack.amount.toDouble()
                val height = amount / capacity * BottlecrateUtils.FLUID_HEIGHT
                drawFluid(x + fxOffset, y + fyOffset, z + fzOffset, height, color)
            }
        }
        else // canInsertInto
        {
            drawFluid(x + fxOffset, y + fyOffset, z + fzOffset,
                      BottlecrateUtils.FLUID_HEIGHT, BottlecrateBehavior.getColor(bottleStack).toInt())
        }

        val bxOffset = xOffset + BottlecrateUtils.DEPTH_OFFSET
        val byOffset = yOffset
        val bzOffset = zOffset + BottlecrateUtils.DEPTH_OFFSET

        drawBottle(x + bxOffset, y + byOffset, z + bzOffset)
    }

    private fun drawBox(buffer: BufferBuilder, w: Double, h: Double, l: Double,
                        uMin: Double, vMin: Double, uMax: Double, vMax: Double)
    {
        buffer.pos(.0, .0, .0).tex(uMin, vMin).endVertex()
        buffer.pos(w, .0, .0).tex(uMax, vMin).endVertex()
        buffer.pos(w, h, .0).tex(uMax, vMax).endVertex()
        buffer.pos(.0, h, .0).tex(uMin, vMax).endVertex()

        buffer.pos(w, .0, l).tex(uMin, vMin).endVertex()
        buffer.pos(.0, .0, l).tex(uMax, vMin).endVertex()
        buffer.pos(.0, h, l).tex(uMax, vMax).endVertex()
        buffer.pos(w, h, l).tex(uMin, vMax).endVertex()

        buffer.pos(.0, .0, l).tex(uMin, vMin).endVertex()
        buffer.pos(.0, .0, .0).tex(uMax, vMin).endVertex()
        buffer.pos(.0, h, .0).tex(uMax, vMax).endVertex()
        buffer.pos(.0, h, l).tex(uMin, vMax).endVertex()

        buffer.pos(w, .0, .0).tex(uMin, vMin).endVertex()
        buffer.pos(w, .0, l).tex(uMax, vMin).endVertex()
        buffer.pos(w, h, l).tex(uMax, vMax).endVertex()
        buffer.pos(w, h, .0).tex(uMin, vMax).endVertex()
    }

    private fun drawBottomAndTop(buffer: BufferBuilder, w: Double, h: Double, l: Double,
                                 uMin: Double, vMin: Double, uMax: Double, vMax: Double)
    {
        buffer.pos(.0, .0, .0).tex(uMin, vMin).endVertex()
        buffer.pos(.0, .0, l).tex(uMin, vMax).endVertex()
        buffer.pos(w, .0, l).tex(uMax, vMax).endVertex()
        buffer.pos(w, .0, .0).tex(uMax, vMin).endVertex()

        buffer.pos(.0, h, .0).tex(uMin, vMin).endVertex()
        buffer.pos(.0, h, l).tex(uMin, vMax).endVertex()
        buffer.pos(w, h, l).tex(uMax, vMax).endVertex()
        buffer.pos(w, h, .0).tex(uMax, vMin).endVertex()
    }

    private fun drawBottle(bx: Double, by: Double, bz: Double)
    {
        GlStateManager.pushMatrix()
        GlStateManager.translate(bx + BottlecrateUtils.DEPTH_OFFSET, by, bz + BottlecrateUtils.DEPTH_OFFSET)

        bindTexture(GTLiteTextures.BOTTLE_IN_CRATE)

        RenderHelper.disableStandardItemLighting()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.disableCull()

        val tessellator = Tessellator.getInstance()
        val buffer = tessellator.buffer

        // draw bottle
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)
        drawBox(buffer,
                BottlecrateUtils.BOTTLE_WIDTH,
                BottlecrateUtils.BOTTLE_HEIGHT,
                BottlecrateUtils.BOTTLE_LENGTH, .0, 1.0, 4.0 / 16.0, 5.0 / 16.0)
        tessellator.draw()

        // draw neck
        GlStateManager.translate((BottlecrateUtils.BOTTLE_WIDTH - BottlecrateUtils.NECK_WIDTH) / 2,
                                 BottlecrateUtils.BOTTLE_HEIGHT, (BottlecrateUtils.BOTTLE_LENGTH - BottlecrateUtils.NECK_LENGTH) / 2)
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)
        drawBox(buffer,
                BottlecrateUtils.NECK_WIDTH,
                BottlecrateUtils.NECK_HEIGHT,
                BottlecrateUtils.NECK_LENGTH, 2.0 / 16.0, 5.0 / 16.0, 4.0 / 16.0, 3.0 / 16.0)
        tessellator.draw()

        // draw cap
        GlStateManager.translate((BottlecrateUtils.NECK_WIDTH - BottlecrateUtils.CAP_WIDTH) / 2,
                                 BottlecrateUtils.NECK_HEIGHT, (BottlecrateUtils.NECK_LENGTH - BottlecrateUtils.CAP_LENGTH) / 2)
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)
        drawBox(buffer,
                BottlecrateUtils.CAP_WIDTH,
                BottlecrateUtils.CAP_HEIGHT,
                BottlecrateUtils.CAP_LENGTH, .0, 3.0 / 16.0, 2.0 / 16.0, 2.0 / 16.0)
        drawBottomAndTop(buffer,
                         BottlecrateUtils.CAP_WIDTH,
                         BottlecrateUtils.CAP_HEIGHT,
                         BottlecrateUtils.CAP_LENGTH, .0, 5.0 / 16.0, 2.0 / 16.0, 3.0 / 16.0)
        tessellator.draw()

        GlStateManager.enableCull()
        GlStateManager.disableBlend()
        RenderHelper.enableStandardItemLighting()
        GlStateManager.popMatrix()
    }

    private fun drawColorCube(buffer: BufferBuilder, w: Double, h: Double, l: Double, color: Int)
    {
        val a = (color shr 24) and 0xFF
        val r = (color shr 16) and 0xFF
        val g = (color shr 8) and 0xFF
        val b = color and 0xFF

        buffer.pos(.0, h, .0).color(r, g, b, a).endVertex()
        buffer.pos(w, h, .0).color(r, g, b, a).endVertex()
        buffer.pos(w, h, l).color(r, g, b, a).endVertex()
        buffer.pos(.0, h, l).color(r, g, b, a).endVertex()

        buffer.pos(.0, .0, .0).color(r, g, b, a).endVertex()
        buffer.pos(.0, h, .0).color(r, g, b, a).endVertex()
        buffer.pos(.0, h, l).color(r, g, b, a).endVertex()
        buffer.pos(.0, .0, l).color(r, g, b, a).endVertex()

        buffer.pos(w, .0, .0).color(r, g, b, a).endVertex()
        buffer.pos(w, h, .0).color(r, g, b, a).endVertex()
        buffer.pos(w, h, l).color(r, g, b, a).endVertex()
        buffer.pos(w, .0, l).color(r, g, b, a).endVertex()

        buffer.pos(.0, .0, .0).color(r, g, b, a).endVertex()
        buffer.pos(w, .0, .0).color(r, g, b, a).endVertex()
        buffer.pos(w, h, .0).color(r, g, b, a).endVertex()
        buffer.pos(.0, h, .0).color(r, g, b, a).endVertex()

        buffer.pos(.0, .0, l).color(r, g, b, a).endVertex()
        buffer.pos(w, .0, l).color(r, g, b, a).endVertex()
        buffer.pos(w, h, l).color(r, g, b, a).endVertex()
        buffer.pos(.0, h, l).color(r, g, b, a).endVertex()

        buffer.pos(.0, .0, .0).color(r, g, b, a).endVertex()
        buffer.pos(w, .0, .0).color(r, g, b, a).endVertex()
        buffer.pos(w, .0, l).color(r, g, b, a).endVertex()
        buffer.pos(.0, .0, l).color(r, g, b, a).endVertex()
    }

    private fun drawFluid(x: Double, y: Double, z: Double, h: Double, color: Int)
    {
        GlStateManager.pushMatrix()
        GlStateManager.translate(x, y, z)

        RenderHelper.disableStandardItemLighting()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.disableCull()
        GlStateManager.disableTexture2D()

        val tessellator = Tessellator.getInstance()
        val buffer = tessellator.buffer
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)
        drawColorCube(buffer, BottlecrateUtils.FLUID_WIDTH, h, BottlecrateUtils.FLUID_LENGTH, color)
        tessellator.draw()

        GlStateManager.enableTexture2D()
        GlStateManager.enableCull()
        GlStateManager.disableBlend()
        RenderHelper.enableStandardItemLighting()
        GlStateManager.popMatrix()
    }
}