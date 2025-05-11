package magicbook.gtlitecore.client.utils;

import net.minecraft.util.Util;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * A convenience class for loading icons from images.
 * <p>
 * Icons loaded from this class are formatted to fit within the required dimension (16x16, 32x32, or 128x128). If the
 * source image is larger than the target dimension, it is shrunk down to the minimum size that will fit. If it is
 * smaller, then it is only scaled up if the new scale can be a per-pixel linear scale (i.e., x2, x3, x4, etc.). In both
 * cases, the image's width/height ratio is kept the same as the source image.
 *
 * @author Chris Molini
 */
@SideOnly(Side.CLIENT)
public class IconLoader
{

    /**
     * Get an icon texture from {@code resourcePath}.
     *
     * @param resourcePath Path to the resource.
     * @return             Returns {@code true} if it sets the icon of the display.
     */
    public static boolean setCustomIcon(String resourcePath)
    {
        InputStream resource = IconLoader.class.getClassLoader().getResourceAsStream(resourcePath);
        if (resource == null)
            return false;
        try
        {
            final BufferedImage image = ImageIO.read(resource);
            Display.setIcon(load(image));
            return true;
        }
        catch (IOException exception)
        {
            return false;
        }
    }

    /**
     * Loads the icon in {@code ByteBuffer} form.
     *
     * @param image Buffered image.
     * @return      An array of {@code ByteBuffer}s containing the pixel data for the icon in
     *              various sizes (as recommended by the OS).
     */
    public static ByteBuffer[] load(BufferedImage image)
    {
        final ByteBuffer[] buffers;
        final Util.EnumOS osType = Util.getOSType();
        if (osType == Util.EnumOS.WINDOWS)
        {
            buffers = new ByteBuffer[2];
            buffers[0] = loadInstance(image, 16);
            buffers[1] = loadInstance(image, 32);
        }
        else if (osType == Util.EnumOS.OSX)
        {
            buffers = new ByteBuffer[1];
            buffers[0] = loadInstance(image, 128);
        }
        else
        {
            buffers = new ByteBuffer[1];
            buffers[0] = loadInstance(image, 32);
        }
        return buffers;
    }

    /**
     * Copies the supplied image into a square icon at the indicated size.
     *
     * @param image The image to place onto the icon.
     * @param size  The desired size of the icon.
     * @return      A {@code ByteBuffer} of pixel data at the indicated size.
     */
    private static ByteBuffer loadInstance(BufferedImage image, int size)
    {
        BufferedImage imageScaled = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics2D graphics = imageScaled.createGraphics();
        double ratio = getIconRatio(image, imageScaled);
        double width = image.getWidth() * ratio, height = image.getHeight() * ratio;
        graphics.drawImage(image,
                (int) ((imageScaled.getWidth() - width) / 2),
                (int) ((imageScaled.getHeight() - height) / 2),
                (int) width, (int) height, null);
        graphics.dispose();
        return convertToByteBuffer(imageScaled);
    }

    /**
     * Gets the width/height ratio of the icon. This is meant to simplify scaling the icon to
     * a new dimension.
     *
     * @param src  The base image that will be placed onto the icon.
     * @param icon The icon that will have the image placed on it.
     * @return     The amount to scale the source image to fit it onto the icon appropriately.
     */
    private static double getIconRatio(BufferedImage src, BufferedImage icon)
    {
        double ratio;
        if (src.getWidth() > icon.getWidth())
        {
            ratio = (double) (icon.getWidth()) / src.getWidth();
        }
        else
        {
            ratio = (int) (icon.getWidth() / src.getWidth());
        }
        if (src.getHeight() > icon.getHeight())
        {
            double r2 = (double) (icon.getHeight()) / src.getHeight();
            if (r2 < ratio) ratio = r2;
        }
        else
        {
            double r2 = (int) (icon.getHeight() / src.getHeight());
            if (r2 < ratio) ratio = r2;
        }
        return ratio;
    }

    /**
     * Converts a BufferedImage into a ByteBuffer of pixel data.
     *
     * @param image The image to convert.
     * @return      A {@code ByteBuffer} that contains the pixel data of the supplied image.
     */
    public static ByteBuffer convertToByteBuffer(BufferedImage image)
    {
        byte[] buffer = new byte[image.getWidth() * image.getHeight() * 4];
        int counter = 0;
        for (int i = 0; i < image.getHeight(); i++)
        {
            for (int j = 0; j < image.getWidth(); j++)
            {
                int colorSpace = image.getRGB(j, i);
                buffer[counter] = (byte) ((colorSpace << 8) >> 24);
                buffer[counter + 1] = (byte) ((colorSpace << 16) >> 24);
                buffer[counter + 2] = (byte) ((colorSpace << 24) >> 24);
                buffer[counter + 3] = (byte) (colorSpace >> 24);
                counter += 4;
            }
        }
        return ByteBuffer.wrap(buffer);
    }

}
