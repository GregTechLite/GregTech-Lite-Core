package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.util.GTUtility;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GTLiteRootCropVariantBlock extends GTLiteCropVariantBlock
{

    protected static final PropertyInteger DEFAULT_AGE_ROOT = PropertyInteger.create("age", 0, 7);

    protected GTLiteRootCropVariantBlock(String name)
    {
        super(name);
    }

    public static GTLiteRootCropVariantBlock create(String name)
    {
        return new GTLiteRootCropVariantBlock(name);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess worldIn, BlockPos pos,
                         IBlockState state, int fortune)
    {
        Random random = worldIn instanceof World ? ((World) worldIn).rand : new Random();

        int age = this.getAge(state);
        if (age >= this.getMinHarvestingAge() && age <= this.getMaxHarvestingAge())
        {
            for (int i = 0; i < 1 + fortune; i++)
                drops.add(this.crop.copy());
        }
        else if (age >= this.getMaxAge())
        {
            int cropCount = 0;
            for (int i = 0; i < 3 + fortune; ++i)
            {
                if (random.nextInt(2 * this.getMaxAge()) <= age)
                    cropCount++;
            }
            if (cropCount > 0)
            {
                ItemStack cropStack = this.crop.copy();
                cropStack.setCount(cropCount);
                drops.add(cropStack);
            }
            drops.add(GTUtility.copy(3 + fortune, this.seed));
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos blockPos, IBlockState blockState,
                                    EntityPlayer player, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ)
    {

        if (this.getAge(blockState) >= this.getMaxAge())
        {
            Random random = worldIn.rand;
            spawnAsEntity(worldIn, blockPos, GTUtility.copy(random.nextInt(2) + 1, this.seed));
            worldIn.setBlockState(blockPos, this.withAge(this.getAge(blockState) - 1), 2);
        }
        return super.onBlockActivated(worldIn, blockPos, blockState, player, hand, facing, hitX, hitY, hitZ);
    }

    public int getMinHarvestingAge()
    {
        return 4;
    }

    public int getMaxHarvestingAge()
    {
        return 5;
    }

    @Override
    public int getMaxAge()
    {
        return 7;
    }

    public boolean seedHarvestable(IBlockState state)
    {
        return this.getAge(state) == this.getMaxAge();
    }

    public boolean cropHarvestable(IBlockState state)
    {
        return this.getAge(state) <= this.getMaxHarvestingAge()
                && this.getAge(state) >= this.getMinHarvestingAge();
    }

    @Override
    protected PropertyInteger getAgeProperty()
    {
        return DEFAULT_AGE_ROOT;
    }

}
