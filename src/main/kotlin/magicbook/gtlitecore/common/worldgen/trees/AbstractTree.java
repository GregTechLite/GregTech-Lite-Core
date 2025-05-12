package magicbook.gtlitecore.common.worldgen.trees;

import lombok.Getter;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.api.utils.functions.TriConsumer;
import magicbook.gtlitecore.api.worldgen.AbstractWorldGenerator;
import magicbook.gtlitecore.api.worldgen.feature.AbstractFeature;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.GTLiteLeaveVariantBlock;
import magicbook.gtlitecore.common.block.blocks.GTLiteLogVariantBlock;
import magicbook.gtlitecore.common.block.blocks.GTLiteSaplingVariantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fluids.Fluid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractTree extends AbstractFeature
{

    public final String name;
    private final int seed;

    @Getter
    public IBlockState logState;
    @Getter
    public IBlockState leaveState;
    @Getter
    public IBlockState saplingState;

    public static final List<AbstractTree> trees = new ArrayList<>();

    public AbstractTree(String name, int seed)
    {
        super(seed);
        this.name = name;
        this.seed = seed;

        FEATURE_GROW_INSTANCE = new WorldGeneratorTree(true, this);
        WORLD_GEN_INSTANCE = new WorldGeneratorTree(false, this);

        trees.add(this);
    }

    public abstract int getItemColor(ItemStack stack, int tintIndex);

    public abstract int getBlockColor(IBlockState blockState, IBlockAccess worldIn,
                                      BlockPos blockPos, int tintIndex);

    @Override
    public boolean generate(World worldIn,
                            BlockPos.MutableBlockPos blockPos,
                            Random random,
                            TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        int minHeight = this.getMinTrunkHeight(random);
        // Check if tree fits in #worldIn.
        if (blockPos.getY() >= 1 && blockPos.getY() + minHeight + 1 <= worldIn.getHeight())
        {
            if (this.isSuitableLocation(worldIn, blockPos, minHeight))
            {
                IBlockState blockState = worldIn.getBlockState(blockPos.down());
                if (blockState.getBlock().canSustainPlant(blockState, worldIn,
                        blockPos.down(), EnumFacing.UP, this.getPlantableSapling())
                        && blockPos.getY() < worldIn.getHeight() - minHeight - 1)
                {
                    blockState.getBlock().onPlantGrow(blockState, worldIn,
                            blockPos.down(), blockPos);
                    this.generateLeaves(worldIn, blockPos, minHeight, random, notifier);
                    this.generateTrunk(worldIn, blockPos, minHeight, random, notifier);
                    return true;
                }
            }
        }
        return false;
    }

    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        for (int foliageY = blockPos.getY() - 3 + height; foliageY <= blockPos.getY() + height; ++foliageY)
        {
            int foliageLayer = foliageY - (blockPos.getY() + height);
            int foliageLayerRadius = 1 - foliageLayer / 2;

            for (int foliageX = blockPos.getX() - foliageLayerRadius; foliageX <= blockPos.getX() + foliageLayerRadius; ++foliageX)
            {
                int foliageRelativeX = foliageX - blockPos.getX();

                for (int foliageZ = blockPos.getZ() - foliageLayerRadius; foliageZ <= blockPos.getZ() + foliageLayerRadius; ++foliageZ)
                {
                    int foliageRelativeZ = foliageZ - blockPos.getZ();
                    // Fill in layer with some randomness.
                    if (Math.abs(foliageRelativeX) != foliageLayerRadius
                            || Math.abs(foliageRelativeZ) != foliageLayerRadius
                            || random.nextInt(2) != 0
                            && foliageLayer != 0)
                    {
                        BlockPos leavesBlockPos = new BlockPos(foliageX, foliageY, foliageZ);
                        IBlockState leavesBlockState = worldIn.getBlockState(leavesBlockPos);

                        if (leavesBlockState.getBlock().isReplaceable(worldIn, blockPos)
                                || leavesBlockState.getBlock().canBeReplacedByLeaves(leavesBlockState, worldIn, blockPos))
                        {
                            notifier.accept(worldIn, leavesBlockPos, this.leaveState);
                        }
                    }
                }
            }
        }
    }

    protected void generateTrunk(World worldIn, BlockPos.MutableBlockPos blockPos,
                                 int maxHeight, Random random,
                                 TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos upNBlockPos = GTLiteUtility.copy(blockPos);
        for (int height = 0; height < maxHeight; ++height)
        {
            IBlockState blockState = worldIn.getBlockState(upNBlockPos);
            if (blockState.getBlock().isAir(blockState, worldIn, upNBlockPos)
                    || blockState.getBlock().isLeaves(blockState, worldIn, upNBlockPos))
            {
                notifier.accept(worldIn, blockPos.up(height),
                        this.logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
            }
            upNBlockPos.move(EnumFacing.UP);
        }
    }

    // Setup abstract tree blocks from GTLiteMetaBlocks#LEAVES, GTLiteMetaBlocks#LOGS
    // and GTLiteMetaBlocks#SAPLINGS.
    @SuppressWarnings("deprecation")
    public void setupBlocks()
    {
        GTLiteLeaveVariantBlock leaves = GTLiteMetaBlocks.LEAVES.get(this.seed / 4);
        this.leaveState = leaves.getStateFromMeta(this.seed % 4 << 2);

        GTLiteLogVariantBlock logs = GTLiteMetaBlocks.LOGS.get(this.seed / 4);
        this.logState = logs.getStateFromMeta(this.seed % 4 << 2);

        GTLiteSaplingVariantBlock saplings = GTLiteMetaBlocks.SAPLINGS.get(this.seed / 8);
        this.saplingState = saplings.getStateFromMeta(this.seed % 8 << 1);
    }

    public boolean isReplaceable(World worldIn, BlockPos blockPos)
    {
        return this.canGrowInto(worldIn.getBlockState(blockPos).getBlock());
    }

    protected boolean canGrowInto(Block block)
    {
        Material material = block.getDefaultState().getMaterial();
        return material == Material.AIR || material == Material.LEAVES
                || block == Blocks.GRASS || block == Blocks.DIRT
                || block == Blocks.LOG || block == Blocks.LOG2
                || block == Blocks.SAPLING || block == Blocks.VINE;
    }

    protected boolean isSuitableLocation(World worldIn, BlockPos blockPos, int minHeight)
    {
        for (int height = 0; height <= 1 + minHeight; ++height)
        {
            // Handles increasing space towards top of tree.
            int extraSpaceNeeded = this.getMooreRadiusAtHeight(height, minHeight);

            BlockPos.MutableBlockPos tBlockPos = new BlockPos.MutableBlockPos();
            for (int checkX = blockPos.getX() - extraSpaceNeeded; checkX <= blockPos.getX() + extraSpaceNeeded; ++checkX)
            {
                for (int checkZ = blockPos.getZ() - extraSpaceNeeded; checkZ <= blockPos.getZ() + extraSpaceNeeded; ++checkZ)
                {
                    if (!this.isReplaceable(worldIn, tBlockPos.setPos(checkX, height + blockPos.getY(), checkZ)))
                        return false;
                }
            }
        }
        return true;
    }

    public int getMinTrunkHeight(Random random)
    {
        return random.nextInt(3) + 5;
    }

    /**
     * @param height       The block height at which this radius is being taken (starting from 0).
     * @param trunkHeight  The height of the trunk.
     * @return             The maximum radius outside the center block that the
     *                     tree can take up at this height value.
     */
    protected int getMooreRadiusAtHeight(int height, int trunkHeight)
    {
        return 0;
    }

    public AbstractWorldGenerator getTreeGrowInstance()
    {
        return FEATURE_GROW_INSTANCE;
    }

    public IPlantable getPlantableSapling()
    {
        return (IPlantable) this.saplingState.getBlock();
    }

    // Drop items like apple of a tree with chance.
    public ItemStack getAppleDrop(int chance)
    {
        return ItemStack.EMPTY;
    }

    // Drop item stack.
    public ItemStack getApple()
    {
        return ItemStack.EMPTY;
    }

    // Get tree sap like maple sugar in maple tree!
    // This is useful for addon mod which will get sap liquid.
    public Fluid getTreeSap()
    {
        return null;
    }

    // Get sapling item stack.
    public ItemStack getSapling()
    {
        return new ItemStack(GTLiteMetaBlocks.SAPLINGS.get(this.seed / 8), 1, (this.seed % 8) << 1);
    }

    protected IBlockState getNaturalLeaveState()
    {
        return this.leaveState.withProperty(BlockLeaves.DECAYABLE, true)
                .withProperty(BlockLeaves.CHECK_DECAY, true);
    }

}
