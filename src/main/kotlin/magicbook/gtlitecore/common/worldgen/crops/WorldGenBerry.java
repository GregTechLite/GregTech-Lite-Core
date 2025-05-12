package magicbook.gtlitecore.common.worldgen.crops;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import magicbook.gtlitecore.api.utils.functions.TriConsumer;
import magicbook.gtlitecore.api.worldgen.feature.AbstractFeature;
import magicbook.gtlitecore.common.block.blocks.GTLiteBerryBushVariantBlock;
import magicbook.gtlitecore.common.worldgen.generator.GTLiteBerryGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

@Getter
@Setter
@ToString
public class WorldGenBerry extends AbstractFeature
{

    public final GTLiteBerryBushVariantBlock berryBush;

    public WorldGenBerry(int seed, GTLiteBerryBushVariantBlock berryBush)
    {
        super(seed);
        this.berryBush = berryBush;
        WORLD_GEN_INSTANCE = new GTLiteBerryGenerator(this);
    }

    @Override
    public boolean generate(World worldIn, BlockPos.MutableBlockPos blockPos,
                            Random random,
                            TriConsumer<World, BlockPos, IBlockState> notifier)
    {
        if (canGrowAt(worldIn, blockPos))
        {
            notifier.accept(worldIn, blockPos, berryBush.withAge(2));
            for (int i = 0; i < random.nextInt(3); i++)
            {
                BlockPos other = blockPos.add(random.nextInt(5) - 2,
                        random.nextInt(5) - 2, 0);
                if (canGrowAt(worldIn, other))
                    notifier.accept(worldIn, other, berryBush.withAge(2));
            }
            return true;
        }
        return false;
    }

    protected boolean canGrowAt(World world, BlockPos pos)
    {
        if (pos.getY() >= 1 && pos.getY() < world.getHeight())
        {
            IBlockState soilState = world.getBlockState(pos.down());
            IBlockState currentState = world.getBlockState(pos);
            return canGrowInto(currentState.getBlock()) && soilState.getBlock()
                    .canSustainPlant(soilState, world, pos.down(), EnumFacing.UP, berryBush);
        }
        return false;
    }

    protected boolean canGrowInto(Block block)
    {
        Material material = block.getDefaultState().getMaterial();
        return material == Material.AIR || block == Blocks.VINE || material == Material.SNOW;
    }

}
