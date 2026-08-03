package gregtechlite.gtlitecore.client.shader

import com.morphismmc.morphismlib.client.Games
import gregtechlite.gtlitecore.api.LOGGER
import net.minecraft.client.renderer.OpenGlHelper
import org.lwjgl.opengl.ARBFragmentShader
import org.lwjgl.opengl.ARBShaderObjects
import org.lwjgl.opengl.ARBVertexShader
import org.lwjgl.opengl.GL11
import java.nio.charset.StandardCharsets

object CosmicShaderProgram
{
    private const val VERT = ARBVertexShader.GL_VERTEX_SHADER_ARB
    private const val FRAG = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB
    private const val PREFIX = "/assets/gtlitecore/shaders/"

    var cosmicShader: Int = 0

    @JvmStatic
    fun initShaders()
    {
        if (!useShaders()) return
        cosmicShader = createProgram("cosmic.vert", "cosmic.frag")
    }

    @JvmOverloads
    fun useShader(shader: Int, callback: ShaderCallback? = null)
    {
        if (!useShaders()) return
        ARBShaderObjects.glUseProgramObjectARB(shader)
        if (shader != 0)
        {
            val time = ARBShaderObjects.glGetUniformLocationARB(shader, "time")
            if (Games.player() != null && Games.world() != null)
            {
                ARBShaderObjects.glUniform1iARB(time, (Games.world()!!.worldTime % Int.MAX_VALUE).toInt())
            }
            callback?.call(shader)
        }
    }

    fun releaseShader()
    {
        useShader(0)
    }

    fun useShaders(): Boolean = OpenGlHelper.shadersSupported

    /**
     * Most of the code taken from the [LWJGL wiki](http://lwjgl.org/wiki/index.php?title=GLSL_Shaders_with_LWJGL).
     */
    private fun createProgram(vert: String?, frag: String?): Int
    {
        var vertId = 0
        var fragId = 0
        if (vert != null) vertId = createShader(PREFIX + vert, VERT)
        if (frag != null) fragId = createShader(PREFIX + frag, FRAG)

        val program = ARBShaderObjects.glCreateProgramObjectARB()
        if (program == 0) return 0

        if (vert != null) ARBShaderObjects.glAttachObjectARB(program, vertId)
        if (frag != null) ARBShaderObjects.glAttachObjectARB(program, fragId)

        ARBShaderObjects.glLinkProgramARB(program)
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE)
        {
            LOGGER.error(getLogInfo(program))
            return 0
        }

        ARBShaderObjects.glValidateProgramARB(program)
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE)
        {
            LOGGER.error(getLogInfo(program))
            return 0
        }

        return program
    }

    private fun createShader(fileName: String, shaderType: Int): Int
    {
        val shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType)
        if (shader == 0) return 0
        return try
        {
            ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(fileName))
            ARBShaderObjects.glCompileShaderARB(shader)
            if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
            {
                throw RuntimeException("Error creating shader \"$fileName\": ${getLogInfo(shader)}")
            }
            shader
        }
        catch (e: Exception)
        {
            ARBShaderObjects.glDeleteObjectARB(shader)
            LOGGER.error("Cannot create ShaderProgram \"$fileName\"", e)
            -1
        }
    }

    private fun getLogInfo(shaderType: Int): String = ARBShaderObjects.glGetInfoLogARB(shaderType,
        ARBShaderObjects.glGetObjectParameteriARB(shaderType, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB))

    private fun readFileAsString(fileName: String): String
    {
        val resourceIn = CosmicShaderProgram::class.java.getResourceAsStream(fileName) ?: return ""
        return resourceIn.use { it.reader(StandardCharsets.UTF_8).readText() }
    }
}