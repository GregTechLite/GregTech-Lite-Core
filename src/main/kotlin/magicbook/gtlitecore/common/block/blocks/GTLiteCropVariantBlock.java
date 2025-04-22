package magicbook.gtlitecore.common.block.blocks;

import lombok.Getter;
import lombok.Setter;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GTLiteCropVariantBlock extends BlockCrops
{

    public static final PropertyInteger DEFAULT_AGE = PropertyInteger.create("age", 0, 5);

    private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);

    @Setter
    protected ItemStack seed;
    @Setter
    protected ItemStack crop;

    public static List<GTLiteCropVariantBlock> CROPS = new ArrayList<>();

    @Getter
    private final String name;

    protected GTLiteCropVariantBlock(String name)
    {
        this.name = name;
        this.setRegistryName(GTLiteValues.MODID, "crop_" + name);
        this.setTranslationKey("gtlite_crop_" + name);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(this.getAgeProperty(), 0));
        CROPS.add(this);
    }

    public static GTLiteCropVariantBlock create(String name)
    {
        return new GTLiteCropVariantBlock(name);
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return CROPS_AABB;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess worldIn, BlockPos pos)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public int getMaxAge()
    {
        return 5;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess worldIn, BlockPos pos,
                         IBlockState state, int fortune)
    {
        Random random = worldIn instanceof World ? ((World) worldIn).rand : new Random();

        int age = this.getAge(state);
        if (age >= this.getMaxAge())
        {
            if (!this.seed.isEmpty())
            {
                ItemStack seedStack = this.seed.copy();
                if (random.nextInt(9) == 0)
                    seedStack.setCount(seedStack.getCount() + 1);
                drops.add(seedStack);
            }

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
        }
    }

    @Override
    public int damageDropped(IBlockState blockState)
    {
        return this.seed.getItemDamage();
    }

    // Override this method to provide supported of TheOneProbe's view info.
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return this.seed;
    }

    @Override
    protected PropertyInteger getAgeProperty()
    {
        return DEFAULT_AGE;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, this.getAgeProperty());
    }

    public Item getSeed()
    {
        return this.seed.getItem();
    }

    public ItemStack getSeedStack()
    {
        return this.seed;
    }

    public Item getCrop()
    {
        return this.crop.getItem();
    }

    public ItemStack getCropStack()
    {
        return this.crop;
    }

}
