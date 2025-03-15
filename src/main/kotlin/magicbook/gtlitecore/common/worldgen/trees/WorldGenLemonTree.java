package magicbook.gtlitecore.common.worldgen.trees;

import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.api.utils.functions.TriConsumer;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
import magicbook.gtlitecore.common.worldgen.features.BiomeCondition;
import magicbook.gtlitecore.common.worldgen.features.TemperatureRainfallCondition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenLemonTree extends AbstractTree
{

    public static int LEAVES_COLOR = 0x87A92C;

    public WorldGenLemonTree()
    {
        super("lemon", 4);
        addCondition(new BiomeCondition(Biomes.JUNGLE_EDGE, 3, 0.4));
        addCondition(new BiomeCondition(Biomes.FOREST, 1, 0.65));
        addCondition(new TemperatureRainfallCondition(5, 1.2, 0.7, 0.7, 0.3));
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos currentYBlockPos = GTLiteUtility.copy(blockPos);
        currentYBlockPos.move(EnumFacing.UP, height - 4);
        for (int i = 1; i < 12; i += (random.nextInt(3) + 2))
        {
            int layerSize = (int) (Math.ceil(Math.sqrt(i)));
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYBlockPos.offset(EnumFacing.NORTH, layerSize)
                            .offset(EnumFacing.WEST, layerSize),
                    currentYBlockPos.offset(EnumFacing.SOUTH, layerSize)
                            .offset(EnumFacing.EAST, layerSize));
            int j = i;
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentYBlockPos.getX())
                        + Math.abs(leavesPos.getZ() - currentYBlockPos.getZ()) <= Math.sqrt(j) && random.nextInt(16 - j) != 0)
                    notifier.accept(worldIn, leavesPos, getNaturalLeaveState());
            });
            currentYBlockPos.move(EnumFacing.UP);
        }
        notifier.accept(worldIn, GTLiteUtility.copy(blockPos)
                .move(EnumFacing.UP, height), getNaturalLeaveState());
    }

    @Override
    public int getMinTrunkHeight(Random random)
    {
        return 6 + random.nextInt(3);
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
        if (GTLiteValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.LEMON.getStackForm(GTLiteValues.RNG.nextInt(2) + 1);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple()
    {
        return GTLiteMetaItems.LEMON.getStackForm();
    }

}
