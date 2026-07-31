package gregtechlite.gtlitecore.client.shader

import com.morphismmc.morphismlib.client.Games
import gregtechlite.gtlitecore.client.event.ClientEventHandlers
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.lwjgl.opengl.ARBShaderObjects
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min

object CosmicShaderHelper
{
    val shaderCallback: ShaderCallback

    var lightlevel = FloatArray(3)
    var inventoryRender: Boolean = false
    var cosmicOpacity: Float = 1.0f

    init
    {
        shaderCallback = object : ShaderCallback()
        {
            override fun call(shader: Int)
            {
                var yaw = 0f
                var pitch = 0f
                var scale = 1.0f

                if (!inventoryRender)
                {
                    yaw = ((Games.player()!!.rotationYaw * 2 * PI) / 360.0).toFloat()
                    pitch = -((Games.player()!!.rotationPitch * 2 * PI) / 360.0).toFloat()
                }
                else
                {
                    scale = 25.0f
                }

                val x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw")
                ARBShaderObjects.glUniform1fARB(x, yaw)

                val z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch")
                ARBShaderObjects.glUniform1fARB(z, pitch)

                val l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel")
                ARBShaderObjects.glUniform3fARB(l, lightlevel[0], lightlevel[1], lightlevel[2])

                val lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix")
                ARBShaderObjects.glUniform1fARB(lightmix, 0.2f)

                val uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs")
                ARBShaderObjects.glUniformMatrix2ARB(uvs, false, ClientEventHandlers.cosmicUVs)

                val s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale")
                ARBShaderObjects.glUniform1fARB(s, scale)

                val o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity")
                ARBShaderObjects.glUniform1fARB(o, cosmicOpacity)
            }
        }
    }

    fun useShader()
    {
        CosmicShaderProgram.useShader(CosmicShaderProgram.cosmicShader, shaderCallback)
    }

    fun releaseShader()
    {
        CosmicShaderProgram.releaseShader()
    }

    fun setLightFromLocation(world: World?, pos: BlockPos)
    {
        if (world == null)
        {
            setLightLevel(1.0f)
            return
        }

        val coord = world.getCombinedLight(pos, 0)

        val map = Games.mc().entityRenderer.lightmapColors
        if (map == null)
        {
            setLightLevel(1.0f)
            return
        }

        val mx = (coord % 65536) / 16
        val my = (coord / 65536) / 16

        val lightColor = map[my * 16 + mx]

        setLightLevel(((lightColor shr 16) and 0xFF) / 256.0f,
                      ((lightColor shr 8) and 0xFF) / 256.0f,
                      ((lightColor) and 0xFF) / 256.0f)
    }

    @JvmStatic
    fun setLightLevel(level: Float)
    {
        setLightLevel(level, level, level)
    }

    fun setLightLevel(r: Float, g: Float, b: Float)
    {
        lightlevel[0] = max(0.0f, min(1.0f, r))
        lightlevel[1] = max(0.0f, min(1.0f, g))
        lightlevel[2] = max(0.0f, min(1.0f, b))
    }
}