package magicbook.gtlitecore.client.model.quad.properties;

import magicbook.gtlitecore.client.model.quad.ModelQuadView;
import net.minecraft.util.EnumFacing;

public class ModelQuadFlags
{
    /**
     * Indicates that the quad does not fully cover the given face for the model.
     */
    public static final int IS_PARTIAL = 0b001;

    /**
     * Indicates that the quad is parallel to its light face.
     */
    public static final int IS_PARALLEL = 0b010;

    /**
     * Indicates that the quad is aligned to the block grid.
     * <p>
     * This flag is only set if {@link #IS_PARALLEL} is set.
     */
    public static final int IS_ALIGNED = 0b100;

    /**
     * @return Returns {@code true} if the big-flag of {@link ModelQuadFlags} contains
     *         the given {@code flags}.
     */
    public static boolean contains(int flags, int mask)
    {
        return (flags & mask) != 0;
    }

}
