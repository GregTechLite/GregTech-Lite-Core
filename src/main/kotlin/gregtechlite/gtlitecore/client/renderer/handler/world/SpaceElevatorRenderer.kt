package gregtechlite.gtlitecore.client.renderer.handler.world

import codechicken.lib.render.CCModel
import codechicken.lib.render.OBJParser
import codechicken.lib.vec.Vertex5
import com.morphismmc.morphismlib.client.Games
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockSpaceElevator
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.OpenGlHelper
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.GL11
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.sqrt

/**
 * @author Adam, Jimbno (original authors)
 */
@SideOnly(Side.CLIENT)
object SpaceElevatorRenderer
{
    private const val CABLE_HEIGHT = 512.0
    private const val CABLE_SIDE = 2.0 / 5.4
    private const val SECTION_HEIGHT = 8.0 * CABLE_SIDE

    private val SECTION_COUNT = ceil(CABLE_HEIGHT / SECTION_HEIGHT).toInt()

    private val LONG_DISTANCE = (1.0 + sqrt(2.0)) / 5.4
    private const val SHORT_DISTANCE = 1.0 / 5.4

    private const val FACE_SLANT = 0.75

    private val EDGE_X = doubleArrayOf(LONG_DISTANCE, LONG_DISTANCE, SHORT_DISTANCE, -SHORT_DISTANCE,
                                       -LONG_DISTANCE, -LONG_DISTANCE, -SHORT_DISTANCE, SHORT_DISTANCE)
    private val EDGE_Z = doubleArrayOf(SHORT_DISTANCE, -SHORT_DISTANCE, -LONG_DISTANCE, -LONG_DISTANCE,
                                       -SHORT_DISTANCE, SHORT_DISTANCE, LONG_DISTANCE, LONG_DISTANCE)

    private val STRANDS = arrayOf(
        doubleArrayOf(1.0, 0.0, 0.0, 0.0),
        doubleArrayOf(0.0, 1.0, 0.0, 1.0),
        doubleArrayOf(-1.0, 0.0, 1.0, 1.0),
        doubleArrayOf(0.0, -1.0, 1.0, 0.0))

    private const val WAVE_PERIOD = 60
    private const val CLIMBER_SCALE = 4.0
    private const val CLIMBER_OFFSET = 50.0
    private const val CLIMBER_MIN_HEIGHT = 100.0

    private val CLIMBER_MODEL: ResourceLocation = GTLiteMod.id("models/climber.obj")
    private val CLIMBER_TEXTURE: ResourceLocation = GTLiteMod.id("textures/models/space_elevator/climber.png")

    private var climberModel: ClimberModel? = null
    private var climberModelLoaded = false

    @JvmStatic
    fun render(event: RenderWorldLastEvent)
    {
        val world = Games.world() ?: return
        val player = Games.player() ?: return

        val elevators = world.loadedTileEntityList
            .mapNotNull { (it as? IGregTechTileEntity)?.metaTileEntity as? MultiblockSpaceElevator }
            .filter { it.isValid && it.isStructureFormed }
        if (elevators.isEmpty()) return

        val renderManager = Games.mc().renderManager
        val waveTime = ((player.ticksExisted % WAVE_PERIOD) + event.partialTicks) / WAVE_PERIOD

        GlStateManager.pushMatrix()
        GlStateManager.translate(-renderManager.viewerPosX, -renderManager.viewerPosY,
                                 -renderManager.viewerPosZ)
        RenderHelper.disableStandardItemLighting()
        GlStateManager.enableTexture2D()
        GlStateManager.enableDepth()
        GlStateManager.depthMask(true)
        GlStateManager.disableBlend()
        GlStateManager.disableCull()
        GlStateManager.enableAlpha()
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f)
        GlStateManager.color(1f, 1f, 1f, 1f)

        elevators.forEach {
            renderCable(it, waveTime)
            renderClimber(it, event.partialTicks)
        }

        GlStateManager.enableCull()
        GlStateManager.popMatrix()
        RenderHelper.enableStandardItemLighting()
    }

    private fun renderCable(elevator: MultiblockSpaceElevator, waveTime: Float)
    {
        val origin = elevator.cableOrigin
        val sprite = GTLiteTextures.SPACE_ELEVATOR_CABLE

        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit)
        Games.mc().textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
        if (SpaceElevatorCableShader.available)
        {
            SpaceElevatorCableShader.use(origin.y.toDouble(), waveTime, CABLE_HEIGHT,
                                         sprite.minU, sprite.maxU, sprite.minV, sprite.maxV)
        }

        GlStateManager.pushMatrix()
        GlStateManager.translate(origin.x.toDouble(), origin.y.toDouble(), origin.z.toDouble())

        val tessellator = Tessellator.getInstance()
        val buffer = tessellator.buffer
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)

        for (section in 0 until SECTION_COUNT)
        {
            val sectionY = section * SECTION_HEIGHT
            for (strand in STRANDS)
            {
                for (side in 0 until 8)
                {
                    val nextSide = (side + 1) and 7
                    val lowY = sectionY + side * CABLE_SIDE
                    val highY = lowY + CABLE_SIDE + FACE_SLANT

                    cableVertex(buffer, strand, nextSide, lowY, sprite.minU, sprite.maxV)
                    cableVertex(buffer, strand, nextSide, highY, sprite.minU, sprite.minV)
                    cableVertex(buffer, strand, side, highY, sprite.maxU, sprite.minV)
                    cableVertex(buffer, strand, side, lowY, sprite.maxU, sprite.maxV)
                }
            }
        }

        tessellator.draw()
        GlStateManager.popMatrix()
        SpaceElevatorCableShader.release()
    }

    private fun cableVertex(buffer: BufferBuilder, strand: DoubleArray, edge: Int, y: Double, u: Float, v: Float)
    {
        val x = 0.5 + EDGE_X[edge]
        val z = 0.5 + EDGE_Z[edge]
        buffer.pos(x * strand[0] + z * strand[1] + strand[2], y,
                   -x * strand[1] + z * strand[0] + strand[3])
            .tex(u.toDouble(), v.toDouble()).endVertex()
    }

    private fun renderClimber(elevator: MultiblockSpaceElevator, partialTicks: Float)
    {
        val model = loadClimberModel() ?: return
        val origin = elevator.cableOrigin

        val height = elevator.previousClimberHeight +
            (elevator.climberHeight - elevator.previousClimberHeight) * partialTicks

        val offset = max(CLIMBER_OFFSET, CLIMBER_MIN_HEIGHT - origin.y)
        val y = origin.y + 0.5 + height + offset

        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit)
        Games.mc().textureManager.bindTexture(CLIMBER_TEXTURE)

        GlStateManager.pushMatrix()
        GlStateManager.translate(origin.x + 0.5, y, origin.z + 0.5)
        GlStateManager.rotate(elevator.climberRotation, 0f, 1f, 0f)
        GlStateManager.scale(CLIMBER_SCALE, CLIMBER_SCALE, CLIMBER_SCALE)

        val tessellator = Tessellator.getInstance()
        val buffer = tessellator.buffer
        buffer.begin(model.mode, DefaultVertexFormats.POSITION_TEX)
        for (vertex in model.vertices)
        {
            buffer.pos(vertex.vec.x, vertex.vec.y, vertex.vec.z).tex(vertex.uv.u, vertex.uv.v).endVertex()
        }
        tessellator.draw()

        GlStateManager.popMatrix()
    }

    private fun loadClimberModel(): ClimberModel?
    {
        if (climberModelLoaded) return climberModel
        climberModelLoaded = true

        climberModel = runCatching {
            val model = CCModel.combine(OBJParser.parseModels(CLIMBER_MODEL).values)
            ClimberModel(model.vertexMode, model.verts)
        }.onFailure {
            LOGGER.error("Failed to load the space elevator climber model", it)
        }.getOrNull()

        return climberModel
    }

    private class ClimberModel(val mode: Int, val vertices: Array<Vertex5>)
}
