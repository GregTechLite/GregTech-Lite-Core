package magicbook.gtlitecore.api.utils;

import magicbook.gtlitecore.common.block.blocks.GTLiteLeaveVariantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// This class is port of same name class in Forestry https://github.com/ForestryMC/ForestryMC.
// This program is on GNU LGPL v3 License.
public class LeafDecayHelper
{

    private static final byte SUSTAINS_LEAVES = 0;
    private static final byte NOT_SUSTAINS_LEAVES = -1;
    private static final byte IS_LEAVES = -2;
    private static final int ARRAY_SIZE = 32;
    private static final byte[][][] leafDecayValues;

    static
    {
        leafDecayValues = new byte[ARRAY_SIZE][ARRAY_SIZE][ARRAY_SIZE];
    }

    public static void leafDecay(GTLiteLeaveVariantBlock leaves, World world, BlockPos pos)
    {
        if (world.isRemote)
            return;

        IBlockState state = world.getBlockState(pos);

        if (!state.getProperties().containsKey(BlockLeaves.DECAYABLE))
            return;

        if (state.getValue(BlockLeaves.CHECK_DECAY) && state.getValue(BlockLeaves.DECAYABLE))
        {
            byte radius = 4;
            int arrayOffset = ARRAY_SIZE / 2;

            if (world.isAreaLoaded(pos, radius + 1))
            {
                for (int xOffset = -radius; xOffset <= radius; ++xOffset)
                {
                    for (int yOffset = -radius; yOffset <= radius; ++yOffset)
                    {
                        for (int zOffset = -radius; zOffset <= radius; ++zOffset)
                        {
                            BlockPos blockPos = pos.add(xOffset, yOffset, zOffset);
                            IBlockState blockState = world.getBlockState(blockPos);
                            Block block = blockState.getBlock();
                            if (!block.canSustainLeaves(blockState, world, blockPos))
                            {
                                if (block.isLeaves(blockState, world, blockPos))
                                {
                                    leafDecayValues[xOffset + arrayOffset][yOffset + arrayOffset][zOffset + arrayOffset] = IS_LEAVES;
                                }
                                else
                                {
                                    leafDecayValues[xOffset + arrayOffset][yOffset + arrayOffset][zOffset + arrayOffset] = NOT_SUSTAINS_LEAVES;
                                }
                            }
                            else
                            {
                                leafDecayValues[xOffset + arrayOffset][yOffset + arrayOffset][zOffset + arrayOffset] = SUSTAINS_LEAVES;
                            }

                        }

                    }

                }

                for (byte sustainedValue = 1; sustainedValue <= 8; ++sustainedValue)
                {
                    for (int xOffset = -radius; xOffset <= radius; ++xOffset)
                    {
                        for (int yOffset = -radius; yOffset <= radius; ++yOffset)
                        {
                            for (int zOffset = -radius; zOffset <= radius; ++zOffset)
                            {
                                if (leafDecayValues[xOffset + arrayOffset][yOffset + arrayOffset][zOffset + arrayOffset] == sustainedValue - 1)
                                {
                                    if (leafDecayValues[(xOffset + arrayOffset - 1)][yOffset + arrayOffset][zOffset + arrayOffset] == IS_LEAVES)
                                    {
                                        leafDecayValues[(xOffset + arrayOffset - 1)][yOffset + arrayOffset][zOffset + arrayOffset] = sustainedValue;
                                    }

                                    if (leafDecayValues[(xOffset + arrayOffset + 1)][yOffset + arrayOffset][zOffset + arrayOffset] == IS_LEAVES)
                                    {
                                        leafDecayValues[(xOffset + arrayOffset + 1)][yOffset + arrayOffset][zOffset + arrayOffset] = sustainedValue;
                                    }

                                    if (leafDecayValues[xOffset + arrayOffset][(yOffset + arrayOffset - 1)][zOffset + arrayOffset] == IS_LEAVES)
                                    {
                                        leafDecayValues[xOffset + arrayOffset][(yOffset + arrayOffset - 1)][zOffset + arrayOffset] = sustainedValue;
                                    }

                                    if (leafDecayValues[xOffset + arrayOffset][(yOffset + arrayOffset + 1)][zOffset + arrayOffset] == IS_LEAVES)
                                    {
                                        leafDecayValues[xOffset + arrayOffset][(yOffset + arrayOffset + 1)][zOffset + arrayOffset] = sustainedValue;
                                    }

                                    if (leafDecayValues[xOffset + arrayOffset][yOffset + arrayOffset][zOffset + arrayOffset - 1] == IS_LEAVES)
                                    {
                                        leafDecayValues[xOffset + arrayOffset][yOffset + arrayOffset][zOffset + arrayOffset - 1] = sustainedValue;
                                    }

                                    if (leafDecayValues[xOffset + arrayOffset][yOffset + arrayOffset][zOffset + arrayOffset + 1] == IS_LEAVES)
                                    {
                                        leafDecayValues[xOffset + arrayOffset][yOffset + arrayOffset][zOffset + arrayOffset + 1] = sustainedValue;
                                    }
                                }

                            }

                        }

                    }

                }

            }

            byte sustainValue = leafDecayValues[arrayOffset][arrayOffset][arrayOffset];

            if (sustainValue >= 0)
            {
                world.setBlockState(pos, state.withProperty(BlockLeaves.CHECK_DECAY, false), 4); // stop trying to decay
            }
            else
            {
                leaves.dropBlockAsItem(world, pos, state, 0);
                world.setBlockToAir(pos);
            }

        }

    }

}
