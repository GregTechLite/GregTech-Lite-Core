package magicbook.gtlitecore.api.utils;

import magicbook.gtlitecore.api.utils.math.CuboidIterator;
import magicbook.gtlitecore.common.block.blocks.GTLiteLeaveVariantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3i;

import java.util.Arrays;

/**
 * A helper for leaf decay checking.
 * <p>
 * The original class is from <a href="Forestry">https://github.com/ForestryMC/ForestryMC</a>,
 * this program is on <a href="GNU LGPL-3.0">https://github.com/ForestryMC/ForestryMC/blob/mc-1.12/LICENSE.txt</a> License.
 * <p>
 * This is an enhancement version of original class, used vector operations to simply some logics.
 */
public class LeafDecayHelper
{

    // Leaves status storages.
    private static final byte SUSTAINS_LEAVES = 0;
    private static final byte NOT_SUSTAINS_LEAVES = -1;
    private static final byte IS_LEAVES = -2;
    // Radius of leaves decay.
    private static final int BASE_RADIUS = 4;
    private static final int ARRAY_SIZE = 2 * BASE_RADIUS + 1; // 9x9x9
    private static final int ARRAY_OFFSET = BASE_RADIUS;

    private static final byte[][][] leafDecayGrid = new byte[ARRAY_SIZE][ARRAY_SIZE][ARRAY_SIZE];

    private static final Vector3i[] CARDINAL_DIRS = {
            new Vector3i(1, 0, 0), new Vector3i(-1, 0, 0),
            new Vector3i(0, 1, 0), new Vector3i(0, -1, 0),
            new Vector3i(0, 0, 1), new Vector3i(0, 0, -1)
    };

    public static void leafDecay(GTLiteLeaveVariantBlock leaves, World world, BlockPos pos)
    {
        if (world.isRemote || !world.isAreaLoaded(pos, BASE_RADIUS + 1))
            return;

        IBlockState centerState = world.getBlockState(pos);
        if (!isDecayableLeaf(centerState))
            return;

        resetDecayGrid();
        scanSurroundingBlocks(world, pos);
        propagateSustainValues();
        determineBlockFate(leaves, world, pos, centerState);
    }

    private static boolean isDecayableLeaf(IBlockState state)
    {
        return state.getBlock() instanceof BlockLeaves
                && state.getValue(BlockLeaves.CHECK_DECAY)
                && state.getValue(BlockLeaves.DECAYABLE);
    }

    private static void resetDecayGrid()
    {
        for (byte[][] layer : leafDecayGrid)
            for (byte[] row : layer)
                Arrays.fill(row, NOT_SUSTAINS_LEAVES);
    }

    private static void scanSurroundingBlocks(World world, BlockPos center)
    {
        for (Vector3i offset : new CuboidIterator(BASE_RADIUS))
        {
            BlockPos targetPos = center.add(offset.x, offset.y, offset.z);
            IBlockState state = world.getBlockState(targetPos);
            Block block = state.getBlock();
            Vector3i gridPos = offset.add(ARRAY_OFFSET, ARRAY_OFFSET, ARRAY_OFFSET, new Vector3i());
            leafDecayGrid[gridPos.x][gridPos.y][gridPos.z] = determineBlockValue(block, state, world, targetPos);
        }
    }

    private static byte determineBlockValue(Block block, IBlockState state, World world, BlockPos pos)
    {
        if (block.canSustainLeaves(state, world, pos))
        {
            return SUSTAINS_LEAVES;
        }
        return block.isLeaves(state, world, pos) ? IS_LEAVES : NOT_SUSTAINS_LEAVES;
    }

    private static void propagateSustainValues()
    {
        for (int iteration = 1; iteration <= 8; iteration++)
            for (int x = 0; x < ARRAY_SIZE; x++)
                for (int y = 0; y < ARRAY_SIZE; y++)
                    for (int z = 0; z < ARRAY_SIZE; z++)
                        if (leafDecayGrid[x][y][z] == iteration - 1)
                            updateNeighbors(x, y, z, (byte) iteration);
    }

    private static void updateNeighbors(int x, int y, int z, byte value)
    {
        for (Vector3i dir : CARDINAL_DIRS)
        {
            Vector3i neighbor = new Vector3i(x, y, z).add(dir);
            if (isWithinBounds(neighbor) &&
                    leafDecayGrid[neighbor.x][neighbor.y][neighbor.z] == IS_LEAVES)
            {
                leafDecayGrid[neighbor.x][neighbor.y][neighbor.z] = value;
            }
        }
    }

    private static boolean isWithinBounds(Vector3i pos)
    {
        return pos.x >= 0 && pos.x < ARRAY_SIZE &&
                pos.y >= 0 && pos.y < ARRAY_SIZE &&
                pos.z >= 0 && pos.z < ARRAY_SIZE;
    }

    private static void determineBlockFate(GTLiteLeaveVariantBlock leaves, World world,
                                           BlockPos pos, IBlockState state)
    {
        byte centerValue = leafDecayGrid[ARRAY_OFFSET][ARRAY_OFFSET][ARRAY_OFFSET];
        if (centerValue >= SUSTAINS_LEAVES)
        {
            world.setBlockState(pos, state.withProperty(BlockLeaves.CHECK_DECAY, false), 2 | 16);
        }
        else
        {
            leaves.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

}
