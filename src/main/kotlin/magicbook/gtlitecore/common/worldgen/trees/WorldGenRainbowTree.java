package magicbook.gtlitecore.common.worldgen.trees;

import magicbook.gtlitecore.api.unification.GTLiteMaterials;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.api.utils.functions.TriConsumer;
import magicbook.gtlitecore.common.worldgen.features.BiomeCondition;
import magicbook.gtlitecore.common.worldgen.features.TemperatureRainfallCondition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

public class WorldGenRainbowTree extends AbstractTree
{

    public static final int[] LEAVES_COLOR = { 0xFF0000, 0xFF4000, 0xFF8000, 0xFFC000,
            0xFFFF00, 0xC0FF00, 0x80FF00, 0x40FF00, 0x00FF00, 0x00FF40, 0x00FF80,
            0x00FFC0, 0x00FFFF, 0x00C0FF, 0x0080FF, 0x0040FF, 0x0000FF, 0x4000FF,
            0x8000FF, 0xC000FF, 0xFF00FF, 0xFF00C0, 0xFF0080, 0xFF0040 };

    public WorldGenRainbowTree()
    {
        super("rainbow", 9);
        addCondition(new BiomeCondition(Biomes.PLAINS, 5, 0.89));
        addCondition(new BiomeCondition(Biomes.MUTATED_PLAINS, 5, 0.89));
        addCondition(new TemperatureRainfallCondition(5, 0.5, 0.8, 0.4, 0.1));
    }

    @Override
    protected void generateLeaves(World worldIn, BlockPos.MutableBlockPos blockPos,
                                  int height, Random random,
                                  TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        BlockPos.MutableBlockPos currentYBlockPos = GTLiteUtility.copy(blockPos);
        currentYBlockPos.move(EnumFacing.UP, height - 3);
        for (int i = 0; i < 7; i++)
        {
            int layerSize = getMooreRadiusAtHeight(i + height - 3, height);
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYBlockPos.offset(EnumFacing.NORTH, layerSize)
                            .offset(EnumFacing.WEST, layerSize),
                    currentYBlockPos.offset(EnumFacing.SOUTH, layerSize)
                            .offset(EnumFacing.EAST, layerSize));
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentYBlockPos.getX())
                        + Math.abs(leavesPos.getZ() - currentYBlockPos.getZ()) < 6)
                    notifier.accept(worldIn, leavesPos, getNaturalLeaveState());
            });
            currentYBlockPos.move(EnumFacing.UP);
        }
    }

    @Override
    public int getMinTrunkHeight(Random random)
    {
        return random.nextInt(3) + 6;
    }

    @Override
    protected int getMooreRadiusAtHeight(int height, int trunkHeight)
    {
        if (height < trunkHeight - 3)
            return 0;
        if (height == trunkHeight - 3)
            return 2;
        if (height < trunkHeight + 3)
            return 3;
        return 2;
    }

    @Override
    public double getPerlinScale()
    {
        return 0.05;
    }

    @Override
    public int getBlockColor(IBlockState blockState, IBlockAccess worldIn,
                             BlockPos blockPos, int tintIndex)
    {
        return LEAVES_COLOR[(Math.abs(blockPos.getX()) + Math.abs(blockPos.getY())
                + Math.abs(blockPos.getZ())) % LEAVES_COLOR.length];
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex)
    {
        return 0x8F00FF;
    }

    @Override
    public Fluid getTreeSap()
    {
        return GTLiteMaterials.RainbowSap.getFluid();
    }

}
