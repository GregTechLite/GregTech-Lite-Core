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

public class WorldGenApricotTree extends AbstractTree
{

    public static int LEAVES_COLOR = 0x87A92C;

    public WorldGenApricotTree()
    {
        super("apricot", 3);
        addCondition(new BiomeCondition(Biomes.MUTATED_SAVANNA, 4, 0.40));
        addCondition(new BiomeCondition(Biomes.SAVANNA, 2, 0.55));
        addCondition(new TemperatureRainfallCondition(2, 1.20, 1.2, 0.05, 0.2));
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos currentYBlockPos = GTLiteUtility.copy(blockPos);
        currentYBlockPos.move(EnumFacing.UP, height - 4);
        boolean atBottom = true;
        for (int i = 8; i > 0; i -= (random.nextInt(3) + 3))
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
                        + Math.abs(leavesPos.getZ() - currentYBlockPos.getZ()) <= Math.sqrt(j))
                    notifier.accept(worldIn, leavesPos, getNaturalLeaveState());
            });
            if (atBottom)
            {
                i += 7;
                atBottom = false;
            }
            currentYBlockPos.move(EnumFacing.UP);
        }
        notifier.accept(worldIn, GTLiteUtility.copy(blockPos)
                .move(EnumFacing.UP, height), getNaturalLeaveState());
    }

    @Override
    public int getMinTrunkHeight(Random random)
    {
        return super.getMinTrunkHeight(random);
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
            return GTLiteMetaItems.APRICOT.getStackForm();
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple()
    {
        return GTLiteMetaItems.APRICOT.getStackForm();
    }

}
