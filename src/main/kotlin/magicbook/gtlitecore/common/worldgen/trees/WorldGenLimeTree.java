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

public class WorldGenLimeTree extends AbstractTree
{

    public static int LEAVES_COLOR = 0x426801;

    public WorldGenLimeTree()
    {
        super("lime", 5);
        addCondition(new BiomeCondition(Biomes.JUNGLE, 3, 0.3));
        addCondition(new BiomeCondition(Biomes.MUTATED_JUNGLE_EDGE, 4, 0.3));
        addCondition(new TemperatureRainfallCondition(5, 1.2, 0.8, 0.85, 0.3));
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos currentYBlockPos = GTLiteUtility.copy(blockPos);
        currentYBlockPos.move(EnumFacing.UP, height - 2);
        for (int i = 9; i > 0; i -= (random.nextInt(2) + 2))
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
                        + Math.abs(leavesPos.getZ() - currentYBlockPos.getZ()) <= Math.sqrt(j)
                        || (Math.abs(leavesPos.getX() - currentYBlockPos.getX())
                            + Math.abs(leavesPos.getZ() - currentYBlockPos.getZ()) <= (Math.sqrt(j) + 0.5)
                            && random.nextInt(2) == 0))
                    notifier.accept(worldIn, leavesPos, getNaturalLeaveState());
            });
            currentYBlockPos.move(EnumFacing.UP);
        }
        notifier.accept(worldIn, GTLiteUtility.copy(blockPos)
                .move(EnumFacing.UP, height), getNaturalLeaveState());
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
            return GTLiteMetaItems.LIME.getStackForm(GTLiteValues.RNG.nextInt(2) + 1);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple()
    {
        return GTLiteMetaItems.LIME.getStackForm();
    }

}
