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

public class WorldGenBananaTree extends AbstractTree
{

    public static int LEAVES_COLOR = 0x396A2E;

    public WorldGenBananaTree()
    {
        super("banana", 0);
        addCondition(new BiomeCondition(Biomes.JUNGLE, 5, 0.35));
        addCondition(new BiomeCondition(Biomes.JUNGLE_EDGE, 5, 0.3));
        addCondition(new BiomeCondition(Biomes.JUNGLE_HILLS, 5, 0.35));
        addCondition(new BiomeCondition(Biomes.MUTATED_JUNGLE, 5, 0.3));
        addCondition(new BiomeCondition(Biomes.MUTATED_JUNGLE_EDGE, 5, 0.15));
        addCondition(new TemperatureRainfallCondition(5, 1.5, 0.8, 0.8, 0.4));
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        // Generate top.
        BlockPos.MutableBlockPos tBlockPos = GTLiteUtility.copy(blockPos.up(height - 1));

        for (int i = 0; i < 3; i++)
        {
            tBlockPos.move(EnumFacing.UP);
            notifier.accept(worldIn, tBlockPos, getNaturalLeaveState());
            if (i == 1)
            {
                tBlockPos.move(EnumFacing.byHorizontalIndex(random.nextInt(4)));
                notifier.accept(worldIn, tBlockPos, getNaturalLeaveState());
            }
        }
        // Generate sideways leaves.
        for (int i = 0; i < 4; i++)
        {
            int leafOffset = random.nextInt(2);
            BlockPos.MutableBlockPos sBlockPos = GTLiteUtility.copy(blockPos.up(height - 2 + leafOffset));

            for (int j = 0; j < 3; j++)
            {
                sBlockPos.move(EnumFacing.byHorizontalIndex(i));
                notifier.accept(worldIn, sBlockPos, getNaturalLeaveState());
                if (j == 0)
                {
                    sBlockPos.move(EnumFacing.UP);
                    notifier.accept(worldIn, sBlockPos, getNaturalLeaveState());
                }
            }

        }
        // Generate ring at height - 3 for extra fullness.
        for (int i = 0; i < 4; i++)
        {
            notifier.accept(worldIn, blockPos.up(height - 1)
                    .offset(EnumFacing.byHorizontalIndex(i)), getNaturalLeaveState());
            notifier.accept(worldIn, blockPos.up(height - 1)
                    .offset(EnumFacing.byHorizontalIndex(i))
                    .offset(EnumFacing.byHorizontalIndex(i).rotateY()), getNaturalLeaveState());
        }

    }

    @Override
    protected void generateTrunk(World worldIn,
                                 BlockPos.MutableBlockPos blockPos,
                                 int maxHeight, Random random,
                                 TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos upNBlockPos = GTLiteUtility.copy(blockPos);
        for (int height = 0; height < maxHeight; ++height)
        {
            IBlockState blockState = worldIn.getBlockState(upNBlockPos);
            if (blockState.getBlock().isAir(blockState, worldIn, upNBlockPos)
                    || blockState.getBlock().isLeaves(blockState, worldIn, upNBlockPos))
                notifier.accept(worldIn, blockPos.up(height),
                        logState.withProperty(BlockLog.LOG_AXIS,
                                height == maxHeight - 1 ? BlockLog.EnumAxis.NONE
                                        : BlockLog.EnumAxis.Y));
            upNBlockPos.move(EnumFacing.UP);
        }
    }

    @Override
    public int getMinTrunkHeight(Random random)
    {
        return 3 + random.nextInt(1);
    }

    @Override
    public ItemStack getAppleDrop(int chance)
    {
        if (GTLiteValues.RNG.nextInt(chance / 8) == 0)
            return GTLiteMetaItems.BANANA.getStackForm(GTLiteValues.RNG.nextInt(4) + 3);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple()
    {
        return GTLiteMetaItems.BANANA.getStackForm();
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

}
