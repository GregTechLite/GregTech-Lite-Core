package gregtechlite.gtlitecore.common.block

import gregtechlite.gtlitecore.api.GTLiteLog
import net.minecraft.block.Block
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.DamageSource
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import java.util.*
import kotlin.math.max
import kotlin.math.min

@Suppress("unused")
open class GTLiteBerryBushBlock protected constructor(name: String) : GTLiteCropBlock(name)
{

    private var isThorny = false // Thorny bush will hurt player when they run on it.

    init
    {
        this.setTranslationKey("gtlitecore.berry_bush_$name")
        this.setHardness(1f)
    }

    companion object
    {
        val EFFICIENCY: PropertyInteger = PropertyInteger.create("efficiency", 0, 4)
        val DEFAULT_AGE: PropertyInteger = PropertyInteger.create("age", 0, 2)

        private val SMALL_AABB = AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75)
        private val LARGE_AABB = AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.9375, 0.9375)
        private val STEM_AABB = AxisAlignedBB(0.4325, 0.0, 0.4325, 0.5675, 0.25, 0.5675)

        fun create(name: String) = GTLiteBerryBushBlock(name)
    }

    override fun createBlockState() = BlockStateContainer(this, ageProperty, EFFICIENCY)

    override fun canSustainBush(state: IBlockState): Boolean
    {
        return state.getBlock() === Blocks.DIRT || super.canSustainBush(state)
    }

    override fun getDrops(drops: NonNullList<ItemStack?>,
                          worldIn: IBlockAccess,
                          pos: BlockPos,
                          state: IBlockState,
                          fortune: Int)
    {
        super.getDrops(drops, worldIn, pos, state, fortune)
        val age = getAge(state)
        val efficiency = getEfficiency(state)
        val rand = if (worldIn is World) worldIn.rand else Random()

        if (age >= maxAge)
        {
            var cropCount = 1
            repeat(2 + efficiency)
            {
                if (rand.nextInt(2) == 0)
                    cropCount++
            }

            val crop = this.cropStack.copy()
            crop.setCount(cropCount)
            drops.add(crop)
        }
    }

    override fun grow(worldIn: World, pos: BlockPos, state: IBlockState)
    {
        if (worldIn.rand.nextInt(max(2, getGrowthSlowdown(worldIn, pos, state) / 8)) != 0) return

        var age = getAge(state) + getBonemealAgeIncrease(worldIn)
        val maxAge = getMaxAge()
        if (age >= maxAge)
            age = maxAge

        worldIn.setBlockState(pos, withEfficiency(withAge(age), getEfficiencyByPos(worldIn, pos)), 3)
    }

    fun getEfficiencyByPos(worldIn: World, pos: BlockPos): Int
    {
        val maxEfficiency = EFFICIENCY.allowedValues.max() ?: 0
        val efficiencies = IntArray(maxEfficiency + 1)

        BlockPos.getAllInBox(pos.east().north(), pos.west().south())
            .forEach { blockPos ->
                if (blockPos != pos)
                {
                    val efficiency = getEfficiency(worldIn.getBlockState(blockPos))
                    if (efficiency in 0 until efficiencies.size)
                        efficiencies[efficiency]++
            }
        }

        return efficiencies.indices.reversed().firstOrNull { efficiencies[it] > 2 } ?: 0
    }

    override fun getBonemealAgeIncrease(worldIn: World) = 1

    fun withEfficiency(state: IBlockState, efficiency: Int): IBlockState
    {
        var efficiency = efficiency
        if (efficiency > 4)
        {
            efficiency = 4
            GTLiteLog.logger.warn("The efficiency of a Berry Bush cannot large than 4!")
        }
        return state.withProperty(EFFICIENCY, efficiency)
    }

    fun getGrowthSlowdown(world: World, pos: BlockPos, state: IBlockState): Int
    {
        if (getAge(state) == 0) return 4 // Usual value for growing crops

        var growthSlowdown = 320 shl getEfficiency(state)
        if (!world.isDaytime) growthSlowdown *= 2

        if (world.isRaining) growthSlowdown = growthSlowdown * 2 / 3

        return growthSlowdown
    }

    override fun getPlantType(worldIn: IBlockAccess, pos: BlockPos) = EnumPlantType.Plains

    override fun onEntityCollision(worldIn: World,
                                   pos: BlockPos,
                                   state: IBlockState,
                                   entityIn: Entity)
    {
        if (isThorny && entityIn is EntityLiving)
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0f)
        var distanceFromCenter = entityIn.getDistanceSq(pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5)
        distanceFromCenter += 0.5 // no singularity going on here
        distanceFromCenter /= 4.0
        entityIn.stepHeight = 0.125f
        entityIn.motionX *= distanceFromCenter
        entityIn.motionY *= distanceFromCenter
        entityIn.motionZ *= distanceFromCenter
    }

    @Deprecated("Deprecated in Java")
    override fun getCollisionBoundingBox(blockState: IBlockState,
                                         worldIn: IBlockAccess,
                                         pos: BlockPos): AxisAlignedBB? = STEM_AABB

    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(state: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB
    {
        return if (getAge(state) == 0) SMALL_AABB else LARGE_AABB
    }

    override fun getMaxAge() = 2

    override fun onBlockActivated(worldIn: World,
                                  pos: BlockPos, state: IBlockState,
                                  playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing,
                                  hitX: Float, hitY: Float, hitZ: Float): Boolean
    {
        if (isMaxAge(state))
        {
            var berries = 1
            repeat(2 + getEfficiency(state))
            {
                if (worldIn.rand.nextInt(2) == 0)
                    berries++
            }

            val berryStack = this.cropStack.copy()
            berryStack.setCount(berries)
            if (!playerIn.addItemStackToInventory(berryStack))
                playerIn.dropItem(berryStack, false)

            worldIn.setBlockState(pos, state.withProperty(ageProperty, this.maxAge - 1), 3)
            return true
        }
        return false
    }

    override fun getStateFromMeta(meta: Int): IBlockState
    {
        return withAge(meta % 3).withProperty(EFFICIENCY, meta / 3)
    }

    override fun getMetaFromState(state: IBlockState): Int
    {
        return getEfficiency(state) * 3 + getAge(state)
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos)
    {
        if (worldIn.getBlockState(fromPos).getBlock() !is GTLiteBerryBushBlock)
        {
            // We don't want crops transmuting to higher efficiencies.
            val newEfficiency = min(getEfficiencyByPos(worldIn, pos), getEfficiency(state))
            worldIn.setBlockState(pos, state.withProperty(EFFICIENCY, newEfficiency), 3)
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos)
    }

    public override fun getAgeProperty(): PropertyInteger = DEFAULT_AGE

    fun getEfficiency(state: IBlockState): Int
    {
        return if (state.getProperties().get(EFFICIENCY) != null) state.getValue(EFFICIENCY) else -1
    }

    fun setThorny(thorny: Boolean): GTLiteBerryBushBlock
    {
        isThorny = thorny
        return this
    }

}
