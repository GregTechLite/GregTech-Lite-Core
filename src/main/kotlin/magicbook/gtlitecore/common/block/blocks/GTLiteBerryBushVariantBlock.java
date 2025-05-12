package magicbook.gtlitecore.common.block.blocks;

import magicbook.gtlitecore.api.utils.GTLiteLog;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GTLiteBerryBushVariantBlock extends GTLiteCropVariantBlock
{

    public static final PropertyInteger EFFICIENCY = PropertyInteger.create("efficiency", 0, 4);
    public static final PropertyInteger DEFAULT_AGE = PropertyInteger.create("age", 0, 2);

    private static final AxisAlignedBB SMALL_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
    private static final AxisAlignedBB LARGE_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
    private static final AxisAlignedBB STEM_AABB = new AxisAlignedBB(0.4325D, 0.0D, 0.4325D, 0.5675D, 0.25D, 0.5675D);

    private boolean isThorny = false; // Thorny bush will hurt player when they run on it.

    protected GTLiteBerryBushVariantBlock(String name)
    {
        super(name);
        this.setTranslationKey("gtlite_berry_bush_" + name);
        this.setHardness(1F);
    }

    public static GTLiteBerryBushVariantBlock create(String name)
    {
        return new GTLiteBerryBushVariantBlock(name);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, getAgeProperty(), EFFICIENCY);
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.DIRT || super.canSustainBush(state);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops,
                         IBlockAccess worldIn,
                         BlockPos pos,
                         IBlockState state,
                         int fortune)
    {
        super.getDrops(drops, worldIn, pos, state, fortune);
        int age = getAge(state);
        int efficiency = getEfficiency(state);
        Random rand = worldIn instanceof World ? ((World) worldIn).rand : new Random();

        if (age >= getMaxAge())
        {
            int cropCount = 1;
            for (int i = 0; i < 2 + efficiency; ++i)
            {
                if (rand.nextInt(2) == 0)
                    cropCount++;
            }
            ItemStack crop = this.crop.copy();
            crop.setCount(cropCount);
            drops.add(crop);
        }
    }

    @Override
    public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        if (worldIn.rand.nextInt(Math.max(2, getGrowthSlowdown(worldIn, pos, state) / 8)) != 0)
            return;

        int age = getAge(state) + getBonemealAgeIncrease(worldIn);
        int maxAge = getMaxAge();

        if (age > maxAge)
            age = maxAge;

        worldIn.setBlockState(pos, withEfficiency(withAge(age), getEfficiencyByPos(worldIn, pos)), 3);
    }

    public int getEfficiencyByPos(World worldIn, BlockPos pos)
    {
        int[] efficiencies = new int[StreamEx.of(EFFICIENCY.getAllowedValues()).max(Integer::compare).get() + 1];
        BlockPos.getAllInBox(pos.east().north(), pos.west().south())
                .forEach((blockpos) -> {
                    if (!blockpos.equals(pos)
                            && getEfficiency(worldIn.getBlockState(blockpos))
                                    + 1 < efficiencies.length)
                        efficiencies[getEfficiency(worldIn.getBlockState(blockpos)) + 1]++;
                });

        for (int i = efficiencies.length - 1; i >= 0; --i)
        {
            if (efficiencies[i] > 2)
                return i;
        }
        return 0;
    }

    @Override
    protected int getBonemealAgeIncrease(World worldIn)
    {
        return 1;
    }

    public IBlockState withEfficiency(IBlockState state, int efficiency)
    {
        if (efficiency > 4)
        {
            efficiency = 4;
            GTLiteLog.logger.warn("The efficiency of a Berry Bush cannot large than 4!");
        }
        return state.withProperty(EFFICIENCY, efficiency);
    }

    public int getGrowthSlowdown(World world, BlockPos pos, IBlockState state)
    {
        if (getAge(state) == 0)
            return 4; // Usual value for growing crops

        int growthSlowdown = 320 << getEfficiency(state);
        if (!world.isDaytime())
            growthSlowdown *= 2;

        if (world.isRaining())
            growthSlowdown = growthSlowdown * 2 / 3;

        return growthSlowdown;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Plains;
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state,
                                  Entity entityIn)
    {
        if (isThorny && entityIn instanceof EntityLiving)
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
        double distanceFromCenter = entityIn.getDistanceSq(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
        distanceFromCenter += 0.5; // no singularity going on here
        distanceFromCenter /= 4;
        entityIn.stepHeight = 0.125F;
        entityIn.motionX *= distanceFromCenter;
        entityIn.motionY *= distanceFromCenter;
        entityIn.motionZ *= distanceFromCenter;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return STEM_AABB;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return this.getAge(state) == 0 ? SMALL_AABB : LARGE_AABB;
    }

    public int getMaxAge() {
        return 2;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
                                    EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ)
    {
        if (this.isMaxAge(state))
        {
            int berries = 1;
            for (int i = 0; i < 2 + getEfficiency(state); ++i)
            {
                if (worldIn.rand.nextInt(2) == 0)
                    berries++;
            }

            ItemStack berryStack = this.getCropStack().copy();
            berryStack.setCount(berries);
            if (!playerIn.addItemStackToInventory(berryStack))
                playerIn.dropItem(this.getCropStack(), false);
            worldIn.setBlockState(pos, state.withProperty(getAgeProperty(), this.getMaxAge() - 1), 3);
            return true;
        }
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.withAge(meta % 3).withProperty(EFFICIENCY, meta / 3);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return this.getEfficiency(state) * 3 + this.getAge(state);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!(worldIn.getBlockState(fromPos).getBlock() instanceof GTLiteBerryBushVariantBlock))
        {
            // We don't want crops transmuting to higher efficiencies.
            int newEfficiency = Math.min(getEfficiencyByPos(worldIn, pos), getEfficiency(state));
            worldIn.setBlockState(pos, state.withProperty(EFFICIENCY, newEfficiency), 3);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }

    @Override
    public PropertyInteger getAgeProperty()
    {
        return DEFAULT_AGE;
    }

    public int getEfficiency(IBlockState state)
    {
        return state.getProperties().get(EFFICIENCY) != null ? state.getValue(EFFICIENCY) : -1;
    }

    public void setThorny(boolean thorny)
    {
        isThorny = thorny;
    }

}
