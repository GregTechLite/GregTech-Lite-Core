package gregtechlite.gtlitecore.common.block.base

import gregtechlite.gtlitecore.api.MOD_ID
import net.minecraft.block.BlockCrops
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import java.util.*

open class GTLiteCropVariantBlock protected constructor(name: String) : BlockCrops()
{

    var seedStack: ItemStack = ItemStack.EMPTY
    var cropStack: ItemStack = ItemStack.EMPTY

    init
    {
        this.setRegistryName(MOD_ID, "crop_$name")
        this.setTranslationKey("gtlite_crop_$name")
        this.defaultState = this.blockState.baseState
            .withProperty<Int, Int>(this.ageProperty, 0)
        CROPS.add(this)
    }

    companion object
    {
        val DEFAULT_AGE: PropertyInteger = PropertyInteger.create("age", 0, 5)

        private val CROPS_AABB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0)

        @JvmField
        var CROPS = ArrayList<GTLiteCropVariantBlock?>()

        fun create(name: String) = GTLiteCropVariantBlock(name)

    }

    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(state: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB = CROPS_AABB

    override fun getPlantType(worldIn: IBlockAccess, pos: BlockPos) = EnumPlantType.Crop

    override fun getMaxAge() = 5

    override fun getDrops(drops: NonNullList<ItemStack?>,
                          worldIn: IBlockAccess,
                          pos: BlockPos,
                          state: IBlockState, fortune: Int)
    {
        val random = if (worldIn is World) worldIn.rand else Random()

        val age = this.getAge(state)
        if (age >= this.maxAge)
        {
            if (!this.seedStack.isEmpty)
            {
                val seedStack = this.seedStack.copy()
                if (random.nextInt(9) == 0)
                    seedStack.setCount(seedStack.count + 1)
                drops.add(seedStack)
            }

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
        }
    }

    override fun damageDropped(blockState: IBlockState) = seedStack.getItemDamage()

    // Override this method to provide supported of TheOneProbe's view info.
    override fun getItem(worldIn: World, pos: BlockPos, state: IBlockState) = seedStack

    override fun getAgeProperty(): PropertyInteger = DEFAULT_AGE

    override fun createBlockState() = BlockStateContainer(this, this.ageProperty)

    public override fun getSeed(): Item = this.seedStack.item

    public override fun getCrop(): Item = this.cropStack.item

}
