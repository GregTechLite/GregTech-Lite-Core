package magicbook.gtlitecore.api.utils.math;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

import java.util.Iterator;

/**
 * An iterator for {@link Vector3i} which used to traverse all dirs in 3d space.
 *
 * @author Magic_Sweepy
 */
@Getter
@AllArgsConstructor
public class CuboidIterator implements Iterable<Vector3i>
{

    private final int minX, maxX, minY, maxY, minZ, maxZ;

    public CuboidIterator(int radius)
    {
        this(-radius, radius, -radius, radius, -radius, radius);
    }

    @NotNull
    @Override
    public Iterator<Vector3i> iterator()
    {
        return new Iterator<Vector3i>()
        {
            private int x = minX;
            private int y = minY;
            private int z = minZ;

            @Override
            public boolean hasNext()
            {
                return x <= maxX && y <= maxY && z <= maxZ;
            }

            @Override
            public Vector3i next()
            {
                Vector3i current = new Vector3i(x, y, z);
                if (++z > maxZ)
                {
                    z = minZ;
                    if (++y > maxY)
                    {
                        y = minY;
                        x++;
                    }
                }
                return current;
            }
        };
    }

}
