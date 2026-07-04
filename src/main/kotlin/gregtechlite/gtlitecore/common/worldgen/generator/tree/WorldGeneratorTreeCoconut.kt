package gregtechlite.gtlitecore.common.worldgen.generator.tree

import gregtech.api.GTValues
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.horizontal
import gregtechlite.gtlitecore.api.extension.horizontalYRotate
import gregtechlite.gtlitecore.api.extension.moveHorizontal
import gregtechlite.gtlitecore.api.extension.moveHorizontalRotateY
import gregtechlite.gtlitecore.api.extension.moveHorizontalRotateYCCW
import gregtechlite.gtlitecore.api.extension.moveUp
import gregtechlite.gtlitecore.api.worldgen.condition.ClimateCondition
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class WorldGeneratorTreeCoconut : WorldGeneratorTreeBase("coconut", 8)
{

    init
    {
        conditions.add(ClimateCondition(5, 1.5, 0.8, 0.9, 0.5))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        // region Top Leaves

        val pos = blockPos.up(height - 1).copy()
        val leafOffset = rand.nextInt(4)
        for (facingIdx in 0 .. 2)
        {
            pos.moveUp()
            notifier(worldIn, pos, placedLeaveState)
            if (facingIdx == 1 || facingIdx == 2)
            {
                pos.moveHorizontal(leafOffset)
                notifier(worldIn, pos, placedLeaveState)
            }
        }
        pos.moveHorizontal(leafOffset)
        notifier(worldIn, pos, placedLeaveState)

        // endregion

        // region Sideway Leaves

        for (facingIdx in 0 .. 3)
        {
            val leafOffset = rand.nextInt(2)
            val pos = blockPos.up(height - 2 + leafOffset).copy()

            val randIdx = rand.nextInt(2)
            for (count in 0 .. 2)
            {
                pos.moveHorizontal(facingIdx)
                notifier(worldIn, pos, placedLeaveState)
                if (count == 0)
                {
                    pos.moveUp()
                    notifier(worldIn, pos, placedLeaveState)
                }
                if (rand.nextInt(3) == 0)
                {
                    if (randIdx == 0)
                    {
                        pos.moveHorizontalRotateY(facingIdx)
                        notifier(worldIn, pos, placedLeaveState)
                    }
                    else
                    {
                        pos.moveHorizontalRotateYCCW(facingIdx)
                        notifier(worldIn, pos, placedLeaveState)
                    }
                }
            }
        }

        // endregion

        // region Bottom Leaves

        for (facingIdx in 0 .. 3)
        {
            notifier(worldIn, blockPos.up(height - 1).horizontal(facingIdx), placedLeaveState)
            notifier(worldIn, blockPos.up(height - 1).horizontal(facingIdx).horizontalYRotate(facingIdx), placedLeaveState)
        }

        // endregion
    }

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 7) == 0)
            return GTLiteMetaItems.COCONUT.getStackForm(GTValues.RNG.nextInt(2))
        return ItemStack.EMPTY
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x657F1C

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x657F1C

}
