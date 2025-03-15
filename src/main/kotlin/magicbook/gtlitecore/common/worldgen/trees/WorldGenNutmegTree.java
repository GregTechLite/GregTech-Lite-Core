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

public class WorldGenNutmegTree extends AbstractTree
{

    public static int LEAVES_COLOR = 0x6DB626;

    public WorldGenNutmegTree()
    {
        super("nutmeg", 7);
        addCondition(new BiomeCondition(Biomes.JUNGLE, 3, 0.3));
        addCondition(new TemperatureRainfallCondition(3, 1.2, 0.85, 1.0, 0.3));
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos currentYBlockPos = GTLiteUtility.copy(blockPos);
        currentYBlockPos.move(EnumFacing.UP, height - 2);
        for (double i = 3; i > 0; i -= (random.nextDouble() / 2 + 0.5))
        {
            int layerSize = (int) (Math.ceil(i));
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYBlockPos.offset(EnumFacing.NORTH, layerSize)
                            .offset(EnumFacing.WEST, layerSize),
                    currentYBlockPos.offset(EnumFacing.SOUTH, layerSize)
                            .offset(EnumFacing.EAST, layerSize));
            double j = i;
            iterator.forEach(leavesPos -> {
                if (Math.pow(Math.pow(Math.abs(leavesPos.getX() - currentYBlockPos.getX()), 2)
                        + Math.pow(Math.abs(leavesPos.getZ() - currentYBlockPos.getZ()), 2), 0.5) <= j)
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
            return GTLiteMetaItems.NUTMEG.getStackForm(GTLiteValues.RNG.nextInt(2) + 1);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple()
    {
        return GTLiteMetaItems.NUTMEG.getStackForm();
    }

}
