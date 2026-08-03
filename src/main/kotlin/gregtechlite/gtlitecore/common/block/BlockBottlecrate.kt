package gregtechlite.gtlitecore.common.block

import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.common.tileentity.TileEntityBlockcrate
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.InventoryHelper
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import gregtechlite.gtlitecore.common.item.behavior.BottlecrateBehavior
import net.minecraftforge.fluids.FluidUtil

class BlockBottlecrate : BlockHorizontal(Material.WOOD)
{
    companion object
    {
        private val BASE_AABB = AxisAlignedBB(.0, .0, .0, 1.0, 10.0 / 16.0, 1.0)
        private val CRATE_AABB = arrayOf(
            AxisAlignedBB(15.0 / 16.0, 1.0 / 16.0, .0, 1.0, 9.0 / 16.0, 1.0),
            AxisAlignedBB(.0, 1.0 / 16.0, 15.0 / 16.0, 1.0, 9.0 / 16.0, 1.0),
            AxisAlignedBB(.0, 1.0 / 16.0, .0, 1.0 / 16.0, 9.0 / 16.0, 1.0),
            AxisAlignedBB(.0, 1.0 / 16.0, .0, 1.0, 9.0 / 16.0, 1.0 / 16.0),
            AxisAlignedBB(.0, .0, .0, 1.0, 1.0 / 16.0, 1.0),
            AxisAlignedBB(.0, 6.0 / 16.0, .0, 1.0, 9.0 / 16.0, 1.0))
    }

    init
    {
        setHardness(2.0f)
        setResistance(5.0f)
        setHarvestLevel("axe", 0)
        setDefaultState(blockState.baseState.withProperty(FACING, EnumFacing.NORTH))
    }

    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(blockState: IBlockState, blockSource: IBlockAccess, blockPos: BlockPos): AxisAlignedBB = BASE_AABB

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, FACING)

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing,
                                      hitX: Float, hitY: Float, hitZ: Float, meta: Int,
                                      placer: EntityLivingBase, hand: EnumHand): IBlockState
        = defaultState.withProperty(FACING, placer.horizontalFacing.opposite)

    @Deprecated("Deprecated in Java")
    override fun isOpaqueCube(blockState: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState
        = defaultState.withProperty(FACING, EnumFacing.byHorizontalIndex(meta and 3))

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(FACING).horizontalIndex

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState,
                                  playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing,
                                  hitX: Float, hitY: Float, hitZ: Float): Boolean
    {
        if (worldIn.isRemote) return true

        val te = worldIn.getTileEntity(pos)
        if (te !is TileEntityBlockcrate) return false

        val bottlecrate = te as TileEntityBlockcrate?
        if (bottlecrate == null) return false

        val heldItem = playerIn.getHeldItem(hand)
        var hitSlot = -1

        for (i in 0..8)
        {
            if (te.getStackInSlot(i).isEmpty) continue

            val physIdx = BottlecrateUtils.getPhysicalIndex(i, state.getValue(FACING))
            val box = BottlecrateUtils.bottleBoxes[physIdx]
            if ((box.minX <= hitX) and (hitX <= box.maxX) and
                (box.minY <= hitY) and (hitY <= box.maxY) and
                (box.minZ <= hitZ) and (hitZ <= box.maxZ))
            {
                hitSlot = i
                break
            }
        }

        val slotItem = if (hitSlot == -1) ItemStack.EMPTY else bottlecrate.getStackInSlot(hitSlot)
        if (!slotItem.isEmpty and !canInsertIntoBlock(heldItem))
        {
            if (!playerIn.inventory.addItemStackToInventory(slotItem))
            {
                playerIn.dropItem(slotItem, false)
            }
            bottlecrate.setStackInSlot(hitSlot, ItemStack.EMPTY)
            worldIn.notifyBlockUpdate(pos,state,state,2)
            return true

        }
        else if (!heldItem.isEmpty and canInsertIntoBlock(heldItem))
        {
            val item = heldItem.copy(1)
            if (bottlecrate.insertItem(item))
            {
                heldItem.shrink(1)
                if (heldItem.isEmpty)
                {
                    playerIn.setHeldItem(hand, ItemStack.EMPTY)
                }
                worldIn.notifyBlockUpdate(pos,state,state,2)
                return true
            }
        }
        return false
    }

    @Suppress("Deprecation")
    @Deprecated("Deprecated in Java")
    override fun addCollisionBoxToList(state: IBlockState, worldIn: World, pos: BlockPos,
                                       entityBox: AxisAlignedBB, collidingBoxes: MutableList<AxisAlignedBB?>,
                                       entityIn: Entity?, isActualState: Boolean)
    {
        val facing = state.getValue(FACING)
        CRATE_AABB.forEachIndexed { i, boundingBox ->
            if ((i % 2 == facing.horizontalIndex % 2) or (i >= 4))
                addCollisionBoxToList(pos, entityBox, collidingBoxes, boundingBox)
         }

        val te = worldIn.getTileEntity(pos)
        if (te !is TileEntityBlockcrate) return

        for (i in 0..8)
        {
            if (te.getStackInSlot(i).isEmpty) continue

            val physIdx = BottlecrateUtils.getPhysicalIndex(i, state.getValue(FACING))
            val box = BottlecrateUtils.bottleBoxes[physIdx]
            addCollisionBoxToList(pos, entityBox, collidingBoxes, box)
        }
    }

    @Suppress("Deprecation")
    @Deprecated("Deprecated in Java")
    override fun collisionRayTrace(blockState: IBlockState, worldIn: World, pos: BlockPos,
                                   start: Vec3d, end: Vec3d): RayTraceResult?
    {
        val te = worldIn.getTileEntity(pos)
        if (te !is TileEntityBlockcrate) return super.collisionRayTrace(blockState, worldIn, pos, start, end)

        val facing = blockState.getValue(FACING)
        val inventory = te.inventory
        var closest: RayTraceResult? = null
        var minDist = Double.Companion.MAX_VALUE

        for (i in 0..8)
        {
            if (inventory[i].isEmpty) continue

            val physIdx = BottlecrateUtils.getPhysicalIndex(i, facing)
            val box = BottlecrateUtils.bottleBoxes[physIdx]
            val hit = rayTrace(pos, start, end, box)
            hit?.let {
                val dist = hit.hitVec.squareDistanceTo(start)
                if (dist < minDist)
                {
                    minDist = dist
                    closest = hit
                }
            }
        }

        CRATE_AABB.forEachIndexed lit@ { i, boundingBox ->
            if ((i % 2 == facing.horizontalIndex % 2) or (i >= 4))
            {
                val hit = rayTrace(pos, start, end, boundingBox)
                hit?.let {
                    val dist = hit.hitVec.squareDistanceTo(start)
                    if (dist < minDist)
                    {
                        minDist = dist
                        closest = hit
                    }
                }
            }
        }
        return closest
    }

    override fun hasTileEntity(state: IBlockState): Boolean = true

    @Deprecated("Deprecated in Java")
    override fun getRenderType(state: IBlockState): EnumBlockRenderType = EnumBlockRenderType.MODEL

    override fun createTileEntity(world: World, state: IBlockState): TileEntity = TileEntityBlockcrate()

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState)
    {
        val te = world.getTileEntity(pos)
        if (te is TileEntityBlockcrate)
        {
            val bottlecrate = te as TileEntityBlockcrate?
            if (bottlecrate == null)
            {
                super.breakBlock(world, pos, state)
                return
            }

            for (i in 0..8)
            {
                val stack = bottlecrate.getStackInSlot(i)
                if (!stack.isEmpty)
                {
                    InventoryHelper.spawnItemStack(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), stack)
                }
            }
        }
        super.breakBlock(world, pos, state)
    }

    private fun canInsertIntoBlock(stack: ItemStack): Boolean
    {
        if (stack.isEmpty) return false
        val handler = FluidUtil.getFluidHandler(stack)
        return handler != null || BottlecrateBehavior.canInsertInto(stack)
    }
}

