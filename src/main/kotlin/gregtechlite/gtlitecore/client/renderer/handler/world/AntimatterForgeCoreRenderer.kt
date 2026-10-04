package gregtechlite.gtlitecore.client.renderer.handler.world

import gregtechlite.gtlitecore.api.extension.fract
import gregtechlite.gtlitecore.api.extension.toRadians
import gregtechlite.gtlitecore.api.extension.triangle
import net.minecraft.client.renderer.BufferBuilder
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@SideOnly(Side.CLIENT)
object AntimatterForgeCoreRenderer
{
    private const val CORE_R = 0.435f
    private const val CORE_G = 0.718f
    private const val CORE_B = 1.0f

    private const val SPIKE_R = 0.153f
    private const val SPIKE_G = 0.435f
    private const val SPIKE_B = 1.0f

    private const val PROTO_R = 0.2f
    private const val PROTO_G = 0.2f
    private const val PROTO_B = 0.2f

    private const val GLOW_R = 0.0f
    private const val GLOW_G = 1.0f
    private const val GLOW_B = 1.0f

    // Max radius = core radius + full spike length
    const val MAX_RADIUS = 7.0f

    // Means how far the spikes can extend past the base core radius.
    const val SPIKE_FACTOR = 0.01f

    private val scratch0 = FloatArray(3)
    private val scratch1 = FloatArray(3)

    private val CUBE_FACES = arrayOf(
        intArrayOf(1, 3, 7, 5), // +X
        intArrayOf(0, 4, 6, 2), // -X
        intArrayOf(2, 3, 7, 6), // +Y
        intArrayOf(0, 1, 5, 4), // -Y
        intArrayOf(4, 5, 7, 6), // +Z
        intArrayOf(0, 2, 3, 1)) // -Z

    fun renderCore(buffer: BufferBuilder, cx: Double, cy: Double, cz: Double, size: Float, timeSec: Float)
    {
        val s = min(size, MAX_RADIUS / (1f + SPIKE_FACTOR))
        if (s <= 0f) return

        val stacks = 24
        val slices = 24

        val angleY = (timeSec * 20f) % 72000f
        val angleX = angleY / 8f
        val colPhase = (timeSec / 4f) % 1f
        val pulseBase = (timeSec * 6f) % (2f * PI.toFloat())

        val mY = rotationMatrix(0f, 1f, 0f, angleY)
        val mX = rotationMatrix(1f, 0f, 0f, angleX)

        val phiSin = FloatArray(stacks + 1)
        val phiCos = FloatArray(stacks + 1)
        for (i in 0..stacks)
        {
            val phi = (PI * i / stacks).toFloat()
            phiSin[i] = sin(phi)
            phiCos[i] = cos(phi)
        }

        val thetaSin = FloatArray(slices + 1)
        val thetaCos = FloatArray(slices + 1)
        for (j in 0..slices)
        {
            val theta = (2.0 * PI * j / slices).toFloat()
            thetaSin[j] = sin(theta)
            thetaCos[j] = cos(theta)
        }

        fun vertex(i: Int, j: Int)
        {
            val dx = phiSin[i] * thetaCos[j]
            val dy = phiCos[i]
            val dz = phiSin[i] * thetaSin[j]

            val pulse = sin(pulseBase + dx * 1.0f + dy * 1.3f + dz * 1.7f)
            val radius = s * (1f + SPIKE_FACTOR + 0.08f * pulse)

            val lx = dx * radius
            val ly = dy * radius
            val lz = dz * radius

            apply(mY, lx, ly, lz, scratch0)
            apply(mX, scratch0[0], scratch0[1], scratch0[2], scratch1)

            val t = (colPhase + lazyHash(dx, dy, dz) / 2f).fract().triangle
            val r = CORE_R + (SPIKE_R - CORE_R) * t
            val g = CORE_G + (SPIKE_G - CORE_G) * t
            val b = CORE_B + (SPIKE_B - CORE_B) * t

            buffer.pos(cx + scratch1[0], cy + scratch1[1], cz + scratch1[2]).color(r, g, b, 1f).endVertex()
        }

        for (i in 0 until stacks)
        {
            for (j in 0 until slices)
            {
                vertex(i, j)
                vertex(i + 1, j)
                vertex(i + 1, j + 1)
                vertex(i, j + 1)
            }
        }
    }

    fun renderProtomatterBeam(buffer: BufferBuilder, cx: Double, cy: Double, cz: Double, scale: Float,
                              spiralRadius: Float, rotAngleDeg: Float, rotX: Float, rotY: Float, rotZ: Float,
                              timeTicks: Float)
    {
        if (scale <= 0f) return

        val cubeCount = 32
        val loopTime = 100f
        val maxDistance = 22.5f

        val m = rotationMatrix(rotX, rotY, rotZ, rotAngleDeg)

        val baseTime = timeTicks % loopTime

        for (i in 0 until cubeCount)
        {
            val cycleOffset = (i / cubeCount.toFloat()) * loopTime
            var cubeTime = (baseTime + cycleOffset) % loopTime
            if (cubeTime < 0f) cubeTime += loopTime

            val dist = positionEquation(cubeTime)

            val halfCycle = PI * (i % 2)
            val angle = i / cubeCount.toFloat() * 3f * PI + halfCycle
            val xOff = sin(angle) * spiralRadius
            val zOff = cos(angle) * spiralRadius

            var yOff = maxDistance - dist

            val size = min((cubeTime / loopTime) / 0.8f, 1f) * scale
            var tall = size * max(1f, maxDistance - yOff)
            val width = if (tall <= 1e-6f) 0f else min(size / sqrt(tall), size)

            val yTrim = max(0f, tall / 2f - yOff)
            tall -= yTrim
            yOff += yTrim / 2f

            val hx = width
            val hy = tall / 2f
            val hz = width

            for (face in CUBE_FACES)
            {
                for (n in face)
                {
                    val sx = if (n and 1 != 0) 1f else -1f
                    val sy = if (n and 2 != 0) 1f else -1f
                    val sz = if (n and 4 != 0) 1f else -1f

                    apply(m, (xOff + sx * hx).toFloat(), yOff + sy * hy, (zOff + sz * hz).toFloat(), scratch0)
                    buffer.pos(cx + scratch0[0], cy + scratch0[1], cz + scratch0[2])
                        .color(PROTO_R, PROTO_G, PROTO_B, 1f).endVertex()
                }
            }
        }
    }

    fun renderGlowRing(buffer: BufferBuilder, cx: Double, cy: Double, cz: Double, radius: Double,
                       tubeRadius: Double, rotAngleDeg: Float, rotX: Float, rotY: Float, rotZ: Float)
    {
        val sides = 20
        val segments = 36
        val sideDelta = 2.0 * PI / sides
        val ringDelta = 2.0 * PI / segments

        val m = rotationMatrix(rotX, rotY, rotZ, rotAngleDeg)

        fun vertex(theta: Double, phi: Double)
        {
            val ring = radius + tubeRadius * cos(phi)
            val lx = (ring * cos(theta)).toFloat()
            val ly = (tubeRadius * sin(phi)).toFloat()
            val lz = (ring * sin(theta)).toFloat()
            apply(m, lx, ly, lz, scratch0)
            buffer.pos(cx + scratch0[0], cy + scratch0[1], cz + scratch0[2])
                .color(GLOW_R, GLOW_G, GLOW_B, 1f).endVertex()
        }

        for (i in 0 until segments)
        {
            val theta0 = ringDelta * i
            val theta1 = ringDelta * (i + 1)
            for (j in 0 until sides)
            {
                val phi0 = sideDelta * j
                val phi1 = sideDelta * (j + 1)
                vertex(theta0, phi0)
                vertex(theta1, phi0)
                vertex(theta1, phi1)
                vertex(theta0, phi1)
            }
        }
    }

    private fun lazyHash(x: Float, y: Float, z: Float): Float
    {
        val vx = (x * 1.23456f + 3.1456f).fract() * 7f
        val vy = (y * 1.23456f + 3.1456f).fract() * 7f
        val vz = (z * 1.23456f + 3.1456f).fract() * 7f
        return (vy + vx * (vz + 1f)).fract()
    }

    // Best fit equation used to move a beam cube along its axis.
    private fun positionEquation(tickTime: Float): Float
    {
        val x = (tickTime / 20f).toDouble()
        if (x <= 0.0)
            return 0f
        val y1 = 7.6331796059e-12 * x.pow(17.827335640)
        val y2 = x / 10.0
        return max(y1, y2).toFloat()
    }

    private fun rotationMatrix(ax: Float, ay: Float, az: Float, angleDeg: Float): FloatArray
    {
        val len = sqrt(ax * ax + ay * ay + az * az)
        if (len < 1e-6f)
        {
            return floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
        }
        val x = ax / len
        val y = ay / len
        val z = az / len
        val c = cos(angleDeg.toDouble().toRadians()).toFloat()
        val s = sin(angleDeg.toDouble().toRadians()).toFloat()
        val t = 1f - c
        return floatArrayOf(
            t * x * x + c, t * x * y - s * z, t * x * z + s * y,
            t * x * y + s * z, t * y * y + c, t * y * z - s * x,
            t * x * z - s * y, t * y * z + s * x, t * z * z + c)
    }

    private fun apply(m: FloatArray, x: Float, y: Float, z: Float, out: FloatArray)
    {
        out[0] = m[0] * x + m[1] * y + m[2] * z
        out[1] = m[3] * x + m[4] * y + m[5] * z
        out[2] = m[6] * x + m[7] * y + m[8] * z
    }
}