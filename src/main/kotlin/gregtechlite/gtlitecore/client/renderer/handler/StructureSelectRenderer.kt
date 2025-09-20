package gregtechlite.gtlitecore.client.renderer.handler

import gregtech.client.utils.RenderBufferHelper
import gregtechlite.gtlitecore.common.item.behavior.StructureWriterBehavior
import com.morphismmc.morphismlib.client.Games
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.GL11

/**
 * Referenced some codes in [Multiblocked](https://github.com/CleanroomMC/Multiblocked/).
 */
@SideOnly(Side.CLIENT)
object StructureSelectRenderer
{

    @JvmStatic
    fun render(event: RenderWorldLastEvent)
    {
        val player = Games.player()
        val heldItem = player!!.heldItemMainhand
        if (StructureWriterBehavior.isItemStructureWriter(heldItem))
        {
            val blockPos = StructureWriterBehavior.getPos(heldItem)
            if (blockPos == null) return

            val posX = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.partialTicks
            val posY = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.partialTicks
            val posZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.partialTicks

            GlStateManager.pushMatrix()
            GlStateManager.translate(-posX, -posY, -posZ)

            GlStateManager.disableDepth()
            GlStateManager.disableTexture2D()
            GlStateManager.enableBlend()
            GlStateManager.disableCull()
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

            val tessellator = Tessellator.getInstance()
            val buffer = tessellator.buffer

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)

            RenderBufferHelper.renderCubeFace(buffer, blockPos[0].x.toDouble(), blockPos[0].y.toDouble(),
                                              blockPos[0].z.toDouble(), (blockPos[1].x + 1).toDouble(),
                                              (blockPos[1].y + 1).toDouble(), (blockPos[1].z + 1).toDouble(),
                                              0.2f, 0.2f, 1f, 0.25f, true)

            tessellator.draw()

            buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR)
            GlStateManager.glLineWidth(3f)

            RenderBufferHelper.renderCubeFrame(buffer, blockPos[0].x.toDouble(), blockPos[0].y.toDouble(),
                                               blockPos[0].z.toDouble(), (blockPos[1].x + 1).toDouble(),
                                               (blockPos[1].y + 1).toDouble(), (blockPos[1].z + 1).toDouble(),
                                               0.0f, 0.0f, 1f, 0.5f)

            tessellator.draw()

            GlStateManager.enableCull()

            GlStateManager.disableBlend()
            GlStateManager.enableTexture2D()
            GlStateManager.enableDepth()
            GlStateManager.color(1f, 1f, 1f)
            GlStateManager.popMatrix()
        }
    }

}