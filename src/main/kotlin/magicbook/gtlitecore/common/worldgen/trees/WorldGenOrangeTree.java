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

public class WorldGenOrangeTree extends AbstractTree
{

    public static int LEAVES_COLOR = 0x76C92C;

    public WorldGenOrangeTree()
    {
        super("orange", 1);
        addCondition(new BiomeCondition(Biomes.SAVANNA, 3, 0.45));
        addCondition(new BiomeCondition(Biomes.SAVANNA_PLATEAU, 6, 0.25));
        addCondition(new TemperatureRainfallCondition(3, 1.2, 1.15, 0.0, 0.2));
    }

    @Override
    public int getMinTrunkHeight(Random random)
    {
        return 2 + random.nextInt(3);
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int trunkHeight, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos currentYBlockPos = GTLiteUtility.copy(blockPos);
        currentYBlockPos.move(EnumFacing.UP);
        for (int i = 0; i < trunkHeight + 1; i++)
        {
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYBlockPos.offset(EnumFacing.NORTH, 2)
                            .offset(EnumFacing.WEST, 2),
                    currentYBlockPos.offset(EnumFacing.SOUTH, 2)
                            .offset(EnumFacing.EAST, 2));
            int j = i;
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentYBlockPos.getX())
                        + Math.abs(leavesPos.getZ() - currentYBlockPos.getZ()) < 3
                        && (j < trunkHeight
                        || ((Math.abs(leavesPos.getX() - currentYBlockPos.getX())
                        + Math.abs(leavesPos.getZ() - currentYBlockPos.getZ()) == 1
                        || random.nextInt(2) == 0))))
                    notifier.accept(worldIn, leavesPos, getNaturalLeaveState());
            });
            currentYBlockPos.move(EnumFacing.UP);
        }
        notifier.accept(worldIn, currentYBlockPos, getNaturalLeaveState());
    }

    @Override
    protected void generateTrunk(World worldIn, BlockPos.MutableBlockPos blockPos,
                                 int maxHeight, Random random,
                                 TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos upNBlockPos = GTLiteUtility.copy(blockPos);
        for (int height = 0; height < maxHeight; ++height) {
            notifier.accept(worldIn, upNBlockPos,
                    logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
            if (height > 0) {
                EnumFacing randomFacing = EnumFacing.byHorizontalIndex(random.nextInt(4));
                notifier.accept(worldIn, upNBlockPos.offset(randomFacing),
                        logState.withProperty(BlockLog.LOG_AXIS,
                                BlockLog.EnumAxis.fromFacingAxis(randomFacing.getAxis())));
            }
            upNBlockPos.move(EnumFacing.UP);
        }
    }

    @Override
    public int getBlockColor(IBlockState blockState, IBlockAccess worldIn,
                             BlockPos blockPos, int tintIndex) {
        return LEAVES_COLOR;
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex)
    {
        return LEAVES_COLOR;
    }

    @Override
    protected int getMooreRadiusAtHeight(int height, int trunkHeight)
    {
        if (height == 0)
            return 0;
        if (height < trunkHeight + 1)
            return 1;
        return 0;
    }

    @Override
    public ItemStack getAppleDrop(int chance)
    {
        if (GTLiteValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.ORANGE.getStackForm(GTLiteValues.RNG.nextInt(2) + 1);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple()
    {
        return GTLiteMetaItems.ORANGE.getStackForm();
    }

}
