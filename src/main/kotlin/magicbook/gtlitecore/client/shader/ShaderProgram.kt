package magicbook.gtlitecore.client.shader

import magicbook.gtlitecore.api.utils.GTLiteLog
import magicbook.gtlitecore.api.utils.GTLiteUtility
import net.minecraft.client.renderer.OpenGlHelper
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20

/**
 * Wrapped the OpenGL ShaderProgram with vertex/fragment shader file names.
 */
class ShaderProgram
{

    private var program = 0

    constructor(domain: String, vertShaderFileName: String, fragShaderFileName: String)
    {
        var program: Int
        try
        {
            program = createProgram(domain, vertShaderFileName, fragShaderFileName)
        }
        catch (exception: Exception)
        {
            GTLiteLog.logger.error("Could not initialize shader program!", exception)
            program = 0
        }
        this.program = program
    }

    companion object
    {

        private fun getProgramLogInfo(obj: Int) = GL20.glGetProgramInfoLog(obj, GL20.glGetProgrami(obj, GL20.GL_INFO_LOG_LENGTH))

        private fun getShaderLogInfo(obj: Int) = GL20.glGetShaderInfoLog(obj, GL20.glGetShaderi(obj, GL20.GL_INFO_LOG_LENGTH))

    }

    fun use()
    {
        GL20.glUseProgram(this.program)
    }

    fun clear()
    {
        GL20.glUseProgram(0)
    }

    private fun createProgram(domain: String,
                              vertShaderFileName: String, fragShaderFileName: String): Int
    {
        if (!OpenGlHelper.shadersSupported) return 0
        val program = GL20.glCreateProgram()
        val vertShader = loadAndCompileShader(program, domain, vertShaderFileName, GL20.GL_VERTEX_SHADER)
        val fragShader = loadAndCompileShader(program, domain, fragShaderFileName, GL20.GL_FRAGMENT_SHADER)

        if (vertShader != 0) GL20.glAttachShader(program, vertShader)
        if (fragShader != 0) GL20.glAttachShader(program, fragShader)

        GL20.glLinkProgram(program)
        if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
        {
            GTLiteLog.logger.error("Could not link shader '${getProgramLogInfo(program)}'")
            GL20.glDeleteProgram(program)
            return 0
        }

        GL20.glValidateProgram(program)
        if (GL20.glGetProgrami(program, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE)
        {
            GTLiteLog.logger.error("Could not validate shader '${getProgramLogInfo(program)}'")
            GL20.glDeleteProgram(program)
            return 0
        }

        return program
    }

    private fun loadAndCompileShader(program: Int, domain: String,
                                     fileName: String?, shaderType: Int): Int
    {
        if (fileName == null) return 0
        val shader = GL20.glCreateShader(shaderType)
        if (shader == 0)
        {
            GTLiteLog.logger.error("Could not create shader of type '$shaderType' from '$fileName': '${getProgramLogInfo(program)}")
            return 0
        }
        val code = GTLiteUtility.loadFile(GTLiteUtility.getId(domain, fileName))
        if (code == null)
        {
            GL20.glDeleteShader(shader)
            return 0
        }

        GL20.glShaderSource(shader, code)
        GL20.glCompileShader(shader)

        if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
        {
            GTLiteLog.logger.error("Could not compile shader '$fileName': '${getShaderLogInfo(shader)}'")
            GL20.glDeleteShader(shader)
            return 0
        }

        return shader
    }

    fun getUniformLocation(name: String): Int
    {
        val index = GL20.glGetUniformLocation(this.program, name)
        if (index < 0)
            throw NullPointerException("No uniform exists with name: '$name'")
        return index
    }

    fun getAttributeLocation(name: String): Int
    {
        val index = GL20.glGetAttribLocation(this.program, name)
        if (index < 0)
            throw NullPointerException("No attribute exists with name: '$name'")
        return index
    }

}