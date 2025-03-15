package magicbook.gtlitecore.common.worldgen.trees;

import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.api.utils.functions.TriConsumer;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
import magicbook.gtlitecore.common.worldgen.features.BiomeCondition;
import magicbook.gtlitecore.common.worldgen.features.TemperatureRainfallCondition;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenOliveTree extends AbstractTree
{

    public static int LEAVES_COLOR = 0x828E5A;

    public WorldGenOliveTree()
    {
        super("olive", 6);
        addCondition(new BiomeCondition(Biomes.BIRCH_FOREST, 5, 0.55));
        addCondition(new BiomeCondition(Biomes.FOREST, 2, 0.65));
        addCondition(new BiomeCondition(Biomes.PLAINS, 1, 0.88));
        addCondition(new TemperatureRainfallCondition(3, 1.5, 0.6, 0.6, 0.3));
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos currentBlockPos = GTLiteUtility.copy(blockPos);
        currentBlockPos.move(EnumFacing.UP, height);
        for (int i = 25; i > 0; i -= (random.nextInt(8) + 13))
        {
            int layerSize = (int) (Math.ceil(Math.sqrt(i)));
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentBlockPos.offset(EnumFacing.NORTH, layerSize)
                            .offset(EnumFacing.WEST, layerSize),
                    currentBlockPos.offset(EnumFacing.SOUTH, layerSize)
                            .offset(EnumFacing.EAST, layerSize));
            int j = i;
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentBlockPos.getX())
                        + Math.abs(leavesPos.getZ() - currentBlockPos.getZ()) <= Math.sqrt(j))
                    notifier.accept(worldIn, leavesPos, getNaturalLeaveState());
            });
            currentBlockPos.move(EnumFacing.UP);
        }
    }

    @Override
    protected void generateTrunk(World worldIn, BlockPos.MutableBlockPos blockPos,
                                 int maxHeight, Random random,
                                 TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos upNBlockPos = GTLiteUtility.copy(blockPos);
        BlockPos.MutableBlockPos upNSplitBlockPos = GTLiteUtility.copy(upNBlockPos);

        EnumFacing splitDirection = EnumFacing.byHorizontalIndex(random.nextInt(4));
        int splittingHeight = maxHeight - 1 - random.nextInt(3);

        for (int height = 0; height < maxHeight; ++height)
        {

            IBlockState state = worldIn.getBlockState(upNBlockPos);
            if (state.getBlock().isAir(state, worldIn, upNBlockPos)
                    || state.getBlock().isLeaves(state, worldIn, upNBlockPos))
            {
                notifier.accept(worldIn, upNBlockPos,
                        logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
            }

            if (height == splittingHeight)
            {
                upNSplitBlockPos.move(splitDirection);
            }
            if (height >= splittingHeight)
            {
                notifier.accept(worldIn, upNSplitBlockPos,
                        logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
                if (random.nextInt(2) == 0)
                    upNSplitBlockPos.move(splitDirection);
            }

            upNBlockPos.move(EnumFacing.UP);
            upNSplitBlockPos.move(EnumFacing.UP);
        }

    }

    @Override
    protected int getMooreRadiusAtHeight(int height, int trunkHeight)
    {
        if (height < trunkHeight - 3)
            return 0;
        if (height < trunkHeight)
            return 4 - (trunkHeight - height);
        return 0;
    }

    @Override
    public int getBlockColor(IBlockState blockState, IBlockAccess worldIn,
                             BlockPos blockPos, int tintIndex)
    {
        return LEAVES_COLOR;
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex)
    {
        return LEAVES_COLOR;
    }

    @Override
    public ItemStack getAppleDrop(int chance)
    {
        if (GTLiteValues.RNG.nextInt(chance / 15) == 0)
            return GTLiteMetaItems.OLIVE.getStackForm(GTLiteValues.RNG.nextInt(4) + 1);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple()
    {
        return GTLiteMetaItems.OLIVE.getStackForm();
    }

}
