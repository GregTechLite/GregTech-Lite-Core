package gregtechlite.gtlitecore.client.shader

import com.morphismmc.morphismlib.client.Games
import gregtechlite.gtlitecore.api.LOGGER
import net.minecraft.client.renderer.OpenGlHelper
import org.lwjgl.opengl.ARBFragmentShader
import org.lwjgl.opengl.ARBShaderObjects
import org.lwjgl.opengl.ARBVertexShader
import org.lwjgl.opengl.GL11
import java.io.BufferedReader
import java.io.InputStreamReader
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

    private fun createShader(filename: String, shaderType: Int): Int
    {
        var shader = 0
        try
        {
            shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType)
            if (shader == 0) return 0

            ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(filename))
            ARBShaderObjects.glCompileShaderARB(shader)

            if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
            {
                throw RuntimeException("Error creating shader \"" + filename + "\": " + getLogInfo(shader))
            }

            return shader
        }
        catch (e: Exception)
        {
            ARBShaderObjects.glDeleteObjectARB(shader)
            LOGGER.error("Cannot create ShaderProgram \"$filename\"", e)
            return -1
        }
    }

    private fun getLogInfo(obj: Int): String = ARBShaderObjects.glGetInfoLogARB(obj,
        ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB))

    @Throws(Exception::class)
    private fun readFileAsString(filename: String): String
    {
        val source = StringBuilder()
        val resourceIn = CosmicShaderProgram::class.java.getResourceAsStream(filename)
        var exception: Exception? = null
        val reader: BufferedReader?

        if (resourceIn == null) return ""

        try
        {
            reader = BufferedReader(InputStreamReader(resourceIn, StandardCharsets.UTF_8))
            var innerExc: Exception? = null
            try
            {
                var line: String?
                while ((reader.readLine().also { line = it }) != null)
                {
                    source.append(line).append('\n')
                }
            }
            catch (exc: Exception)
            {
                exception = exc
            }
            finally
            {
                try
                {
                    reader.close()
                }
                catch (exc: Exception)
                {
                    if (innerExc == null)
                    {
                        innerExc = exc
                    }
                    else
                    {
                        exc.printStackTrace()
                    }
                }
            }

            if (innerExc != null)
            {
                throw innerExc
            }
        }
        catch (exc: Exception)
        {
            exception = exc
        }
        finally
        {
            try
            {
                resourceIn.close()
            }
            catch (exc: Exception)
            {
                if (exception == null)
                {
                    exception = exc
                }
                else
                {
                    exc.printStackTrace()
                }
            }

            if (exception != null)
            {
                throw exception
            }
        }

        return source.toString()
    }
}