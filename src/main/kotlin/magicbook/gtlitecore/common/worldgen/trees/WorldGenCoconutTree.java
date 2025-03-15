package magicbook.gtlitecore.common.worldgen.trees;

import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.api.utils.functions.TriConsumer;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
import magicbook.gtlitecore.common.worldgen.features.TemperatureRainfallCondition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCoconutTree extends AbstractTree
{

    public static int LEAVES_COLOR = 0x657F1C;

    public WorldGenCoconutTree()
    {
        super("coconut", 8);
        addCondition(new TemperatureRainfallCondition(5, 1.5, 0.8, 0.9, 0.5));
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        // Generate top.
        BlockPos.MutableBlockPos tBlockPos = GTLiteUtility.copy(blockPos.up(height - 1));
        int tSideVariance = random.nextInt(4);
        for (int i = 0; i < 3; i++)
        {
            tBlockPos.move(EnumFacing.UP);
            notifier.accept(worldIn, tBlockPos, getNaturalLeaveState());
            if (i == 1 || i == 2)
            {
                tBlockPos.move(EnumFacing.byHorizontalIndex(tSideVariance));
                notifier.accept(worldIn, tBlockPos, getNaturalLeaveState());
            }
        }
        tBlockPos.move(EnumFacing.byHorizontalIndex(tSideVariance));
        notifier.accept(worldIn, tBlockPos, getNaturalLeaveState());
        // Generate sideways leaves.
        for (int i = 0; i < 4; i++)
        {
            int leafOffset = random.nextInt(2);
            BlockPos.MutableBlockPos sBlockPos = GTLiteUtility.copy(blockPos.up(height - 2 + leafOffset));

            int sSideVariance = random.nextInt(2);
            for (int j = 0; j < 3; j++)
            {
                sBlockPos.move(EnumFacing.byHorizontalIndex(i));
                notifier.accept(worldIn, sBlockPos, getNaturalLeaveState());
                if (j == 0)
                {
                    sBlockPos.move(EnumFacing.UP);
                    notifier.accept(worldIn, sBlockPos, getNaturalLeaveState());
                }
                if (random.nextInt(3) == 0)
                {
                    if (sSideVariance == 0)
                    {
                        sBlockPos.move(EnumFacing.byHorizontalIndex(i).rotateY());
                        notifier.accept(worldIn, sBlockPos, getNaturalLeaveState());
                    }
                    else
                    {
                        sBlockPos.move(EnumFacing.byHorizontalIndex(i).rotateYCCW());
                        notifier.accept(worldIn, sBlockPos, getNaturalLeaveState());
                    }
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
        if (GTLiteValues.RNG.nextInt(chance / 7) == 0)
            return GTLiteMetaItems.COCONUT.getStackForm(GTLiteValues.RNG.nextInt(2));
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple()
    {
        return GTLiteMetaItems.COCONUT.getStackForm();
    }

}
