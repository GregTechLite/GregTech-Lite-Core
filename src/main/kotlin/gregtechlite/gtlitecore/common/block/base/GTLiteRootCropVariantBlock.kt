package gregtechlite.gtlitecore.common.block.base

import gregtechlite.gtlitecore.api.extension.copy
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

@Suppress("unused")
class GTLiteRootCropVariantBlock private constructor(name: String) : GTLiteCropVariantBlock(name)
{

    companion object
    {
        private val DEFAULT_AGE_ROOT: PropertyInteger = PropertyInteger.create("age", 0, 7)

        fun create(name: String) = GTLiteRootCropVariantBlock(name)
    }

    override fun getDrops(drops: NonNullList<ItemStack?>,
                          worldIn: IBlockAccess,
                          pos: BlockPos,
                          state: IBlockState, fortune: Int)
    {
        val random = if (worldIn is World) worldIn.rand else Random()

        val age = this.getAge(state)
        if (age >= this.minHarvestingAge && age <= this.maxHarvestingAge)
        {
            repeat(1 + fortune)
            {
                drops.add(this.cropStack.copy())
            }
        }
        else if (age >= this.maxAge)
        {
            var cropCount = 0
            repeat(3 + fortune)
            {
                if (random.nextInt(2 * this.maxAge) <= age)
                    cropCount++
            }
            if (cropCount > 0)
            {
                val cropStack = this.cropStack.copy()
                cropStack.setCount(cropCount)
                drops.add(cropStack)
            }
            drops.add(this.seedStack.copy(3 + fortune))
        }
    }

    override fun onBlockActivated(worldIn: World,
                                  blockPos: BlockPos, blockState: IBlockState,
                                  player: EntityPlayer, hand: EnumHand, facing: EnumFacing,
                                  hitX: Float, hitY: Float, hitZ: Float): Boolean
    {
        if (this.getAge(blockState) >= this.maxAge)
        {
            val random = worldIn.rand
            spawnAsEntity(worldIn, blockPos, this.seedStack.copy(random.nextInt(2) + 1))
            worldIn.setBlockState(blockPos, withAge(getAge(blockState) - 1), 2)
        }
        return super.onBlockActivated(worldIn, blockPos, blockState, player, hand, facing, hitX, hitY, hitZ)
    }

    val minHarvestingAge: Int
        get() = 4

    val maxHarvestingAge: Int
        get() = 5

    override fun getMaxAge() = 7

    fun seedHarvestable(state: IBlockState): Boolean
    {
        return getAge(state) == this.maxAge
    }

    fun cropHarvestable(state: IBlockState): Boolean
    {
        return getAge(state) <= this.maxHarvestingAge
                && getAge(state) >= this.minHarvestingAge
    }

    override fun getAgeProperty(): PropertyInteger = DEFAULT_AGE_ROOT

}
