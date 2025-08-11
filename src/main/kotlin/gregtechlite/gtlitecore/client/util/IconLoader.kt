package gregtechlite.gtlitecore.client.util

import net.minecraft.util.Util
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.Display
import java.awt.image.BufferedImage
import java.io.IOException
import java.nio.ByteBuffer
import javax.imageio.ImageIO

/**
 * A convenience class for loading icons from images.
 *
 *
 * Icons loaded from this class are formatted to fit within the required dimension (16x16, 32x32, or 128x128). If the
 * source image is larger than the target dimension, it is shrunk down to the minimum size that will fit. If it is
 * smaller, then it is only scaled up if the new scale can be a per-pixel linear scale (i.e., x2, x3, x4, etc.). In both
 * cases, the image's width/height ratio is kept the same as the source image.
 *
 * @author Chris Molini
 */
@SideOnly(Side.CLIENT)
object IconLoader
{
    /**
     * Get an icon texture from `resourcePath`.
     *
     * @param resourcePath Path to the resource.
     * @return Returns `true` if it sets the icon of the display.
     */
    @JvmStatic
    fun setCustomIcon(resourcePath: String?): Boolean
    {
        val resource = IconLoader::class.java.getClassLoader().getResourceAsStream(resourcePath)
        if (resource == null) return false
        try
        {
            val image = ImageIO.read(resource)
            Display.setIcon(load(image))
            return true
        }
        catch (exception: IOException)
        {
            return false
        }
    }

    /**
     * Loads the icon in `ByteBuffer` form.
     *
     * @param image Buffered image.
     * @return An array of `ByteBuffer`s containing the pixel data for the icon in
     * various sizes (as recommended by the OS).
     */
    @JvmStatic
    fun load(image: BufferedImage): Array<ByteBuffer?>
    {
        return when (Util.getOSType())
        {
            Util.EnumOS.WINDOWS -> arrayOf(loadInstance(image, 16), loadInstance(image, 32))
            Util.EnumOS.OSX -> arrayOf(loadInstance(image, 128))
            else -> arrayOf(loadInstance(image, 32))
        }
    }

    /**
     * Copies the supplied image into a square icon at the indicated size.
     *
     * @param image The image to place onto the icon.
     * @param size  The desired size of the icon.
     * @return A `ByteBuffer` of pixel data at the indicated size.
     */
    private fun loadInstance(image: BufferedImage, size: Int): ByteBuffer
    {
        val imageScaled = BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB_PRE)
        val graphics = imageScaled.createGraphics()
        val ratio = getIconRatio(image, imageScaled)
        val width = image.width * ratio
        val height = image.height * ratio
        graphics.drawImage(
            image,
            ((imageScaled.width - width) / 2).toInt(),
            ((imageScaled.height - height) / 2).toInt(),
            width.toInt(), height.toInt(), null
        )
        graphics.dispose()
        return convertToByteBuffer(imageScaled)
    }

    /**
     * Gets the width/height ratio of the icon. This is meant to simplify scaling the icon to
     * a new dimension.
     *
     * @param src  The base image that will be placed onto the icon.
     * @param icon The icon that will have the image placed on it.
     * @return The amount to scale the source image to fit it onto the icon appropriately.
     */
    private fun getIconRatio(src: BufferedImage, icon: BufferedImage): Double
    {
        var ratio = if (src.width > icon.width)
        {
            (icon.width).toDouble() / src.width
        }
        else
        {
            (icon.width / src.width).toDouble()
        }
        if (src.height > icon.height)
        {
            val r2 = (icon.height).toDouble() / src.height
            if (r2 < ratio) ratio = r2
        }
        else
        {
            val r2 = (icon.height / src.height).toDouble()
            if (r2 < ratio) ratio = r2
        }
        return ratio
    }

    /**
     * Converts a BufferedImage into a ByteBuffer of pixel data.
     *
     * @param image The image to convert.
     * @return A `ByteBuffer` that contains the pixel data of the supplied image.
     */
    @JvmStatic
    fun convertToByteBuffer(image: BufferedImage): ByteBuffer
    {
        val buffer = ByteArray(image.width * image.height * 4)
        var counter = 0
        for (i in 0..<image.height)
        {
            for (j in 0..<image.width)
            {
                val colorSpace = image.getRGB(j, i)
                buffer[counter] = ((colorSpace shl 8) shr 24).toByte()
                buffer[counter + 1] = ((colorSpace shl 16) shr 24).toByte()
                buffer[counter + 2] = ((colorSpace shl 24) shr 24).toByte()
                buffer[counter + 3] = (colorSpace shr 24).toByte()
                counter += 4
            }
        }
        return ByteBuffer.wrap(buffer)
    }

}