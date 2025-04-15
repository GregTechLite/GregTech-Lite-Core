package magicbook.gtlitecore.client.model.quad;

import magicbook.gtlitecore.client.model.quad.properties.ModelQuadFlags;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

import java.nio.ByteBuffer;

public interface ModelQuadView
{

    /**
     * @return The x-position of the vertex at {@code index}.
     */
    float getX(int index);

    /**
     * @return The y-position of the vertex at {@code index}.
     */
    float getY(int index);

    /**
     * @return The z-position of the vertex at {@code index}.
     */
    float getZ(int index);

    /**
     * @return The integer encoded color (ARGB) of the vertex at {@code index}.
     */
    int getColor(int index);

    /**
     * @return The color index of this quad.
     */
    int getColorIndex();

    /**
     * @return The texture x-coordinate for the vertex at {@code index}.
     */
    float getTextureU(int index);

    /**
     * @return The texture y-coordinate for the vertex at {@code index}.
     */
    float getTextureV(int index);

    /**
     * @return The integer bit flags containing the {@link ModelQuadFlags} for this quad.
     */
    int getFlags();

    /**
     * @return The lightmap texture coordinates for the vertex at {@code index}.
     */
    int getLight(int index);

    /**
     * @return The integer encoded normal vector for the vertex at {@code index}.
     */
    int getNormal(int index);

    /**
     * If not {@code null}, then quad shouldn't be rendered in-world if the opposite face
     * of a neighbor block occludes it.
     */
    EnumFacing getCullFace();

    /**
     * This is the face used for vanilla lighting calculations and will be the block
     * face to which the quad is most closely aligned. Always the same as cull face
     * for quads that are on a block face, but never {@code null}.
     */
    EnumFacing getLightFace();

    /**
     * @return The sprite texture used by this quad, or null if none is attached.
     */
    TextureAtlasSprite getSprite();

    /**
     * Copies this quad's data into the specified buffer starting at the given position.
     *
     * @param buf The buffer to write this quad's data to.
     * @param pos The starting byte index to write to.
     */
    default void copyInto(ByteBuffer buf, int pos)
    {
        for (int i = 0; i < 4; i++)
        {
            buf.putFloat(pos, this.getX(i));
            buf.putFloat(pos + 4, this.getY(i));
            buf.putFloat(pos + 8, this.getZ(i));
            buf.putInt(pos + 12, this.getColor(i));
            buf.putFloat(pos + 16, this.getTextureU(i));
            buf.putFloat(pos + 20, this.getTextureV(i));
            buf.putInt(pos + 24, this.getLight(i));
            pos += 28;
        }
    }

}
