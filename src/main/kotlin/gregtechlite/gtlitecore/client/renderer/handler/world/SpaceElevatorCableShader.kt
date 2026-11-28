package gregtechlite.gtlitecore.client.renderer.handler.world

import gregtechlite.gtlitecore.api.LOGGER
import net.minecraft.client.renderer.OpenGlHelper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.ARBFragmentShader
import org.lwjgl.opengl.ARBShaderObjects
import org.lwjgl.opengl.ARBVertexShader
import org.lwjgl.opengl.GL11
import java.nio.charset.StandardCharsets

// TODO: Extract shader program part with other programs with a unified API?
@SideOnly(Side.CLIENT)
object SpaceElevatorCableShader
{
    private const val PREFIX = "/assets/gtlitecore/shaders/"
    private const val VERTEX_SHADER = ARBVertexShader.GL_VERTEX_SHADER_ARB
    private const val FRAGMENT_SHADER = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB

    private const val GLOW_START = 7f / 16f
    private const val GLOW_END = 9f / 16f

    private var program = 0
    private var initialized = false

    private var baseYLocation = -1
    private var timeLocation = -1
    private var cableHeightLocation = -1
    private var glowULocation = -1
    private var glowVLocation = -1
    private var samplerLocation = -1

    val available: Boolean
        get()
        {
            if (!initialized)
                init()
            return program != 0
        }

    fun use(cableBaseY: Double, waveTime: Float, cableHeight: Double,
            minU: Float, maxU: Float, minV: Float, maxV: Float)
    {
        if (!available) return

        ARBShaderObjects.glUseProgramObjectARB(program)
        ARBShaderObjects.glUniform1fARB(baseYLocation, cableBaseY.toFloat())
        ARBShaderObjects.glUniform1fARB(timeLocation, waveTime)
        ARBShaderObjects.glUniform1fARB(cableHeightLocation, cableHeight.toFloat())
        ARBShaderObjects.glUniform2fARB(glowULocation,
                                        minU + (maxU - minU) * GLOW_START, minU + (maxU - minU) * GLOW_END)
        ARBShaderObjects.glUniform2fARB(glowVLocation,
                                        minV + (maxV - minV) * GLOW_START, minV + (maxV - minV) * GLOW_END)
        ARBShaderObjects.glUniform1iARB(samplerLocation, 0)
    }

    fun release()
    {
        if (program != 0)
            ARBShaderObjects.glUseProgramObjectARB(0)
    }

    private fun init()
    {
        initialized = true
        if (!OpenGlHelper.shadersSupported) return

        program = createProgram("space_elevator_cable.vert", "space_elevator_cable.frag")
        if (program == 0) return

        baseYLocation = uniform("u_BaseY")
        timeLocation = uniform("u_Time")
        cableHeightLocation = uniform("u_CableHeight")
        glowULocation = uniform("u_GlowU")
        glowVLocation = uniform("u_GlowV")
        samplerLocation = uniform("u_CableTex")
    }

    private fun uniform(name: String): Int
        = ARBShaderObjects.glGetUniformLocationARB(program, name)

    private fun createProgram(vertexFile: String, fragmentFile: String): Int
    {
        val vertexShader = createShader(PREFIX + vertexFile, VERTEX_SHADER)
        val fragmentShader = createShader(PREFIX + fragmentFile, FRAGMENT_SHADER)
        if (vertexShader == 0 || fragmentShader == 0) return 0

        val program = ARBShaderObjects.glCreateProgramObjectARB()
        if (program == 0) return 0

        ARBShaderObjects.glAttachObjectARB(program, vertexShader)
        ARBShaderObjects.glAttachObjectARB(program, fragmentShader)
        ARBShaderObjects.glLinkProgramARB(program)
        if (ARBShaderObjects.glGetObjectParameteriARB(program,
                                                      ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE)
        {
            LOGGER.error(getLogInfo(program))
            return 0
        }

        ARBShaderObjects.glValidateProgramARB(program)
        if (ARBShaderObjects.glGetObjectParameteriARB(program,
                                                      ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE)
        {
            LOGGER.error(getLogInfo(program))
            return 0
        }

        return program
    }

    private fun createShader(fileName: String, shaderType: Int): Int
    {
        val shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType)
        if (shader == 0)
            return 0
        return try
        {
            ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(fileName))
            ARBShaderObjects.glCompileShaderARB(shader)
            if (ARBShaderObjects.glGetObjectParameteriARB(
                    shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
            {
                throw RuntimeException("Error creating shader \"$fileName\": ${getLogInfo(shader)}")
            }
            shader
        }
        catch (e: Exception)
        {
            ARBShaderObjects.glDeleteObjectARB(shader)
            LOGGER.error("Cannot create ShaderProgram \"$fileName\"", e)
            0
        }
    }

    private fun getLogInfo(shader: Int): String
        = ARBShaderObjects.glGetInfoLogARB(shader, ARBShaderObjects.glGetObjectParameteriARB(
            shader, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB))

    private fun readFileAsString(fileName: String): String
    {
        val resource = SpaceElevatorCableShader::class.java.getResourceAsStream(fileName) ?: return ""
        return resource.use { it.reader(StandardCharsets.UTF_8).readText() }
    }
}
