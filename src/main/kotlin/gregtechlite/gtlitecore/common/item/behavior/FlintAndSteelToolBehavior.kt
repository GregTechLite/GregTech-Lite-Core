package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.GTValues
import gregtech.api.items.toolitem.behavior.IToolBehavior
import gregtech.common.blocks.explosive.BlockGTExplosive
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.block.BlockTNT
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.init.SoundEvents
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class FlintAndSteelToolBehavior: IToolBehavior
{

    companion object
    {
        @JvmStatic
        val INSTANCE: FlintAndSteelToolBehavior = FlintAndSteelToolBehavior()
    }

    override fun onItemUseFirst(player: EntityPlayer, world: World, pos: BlockPos,
                                facing: EnumFacing,
                                hitX: Float, hitY: Float, hitZ: Float,
                                hand: EnumHand): EnumActionResult
    {
        val stack = player.getHeldItem(hand)

        player.entityWorld.playSound(null, player.position,
                                     SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS,
                                     1.0F, GTValues.RNG.nextFloat() * 0.4F + 0.8F)

        val blockState = world.getBlockState(pos)
        val block = blockState.block
        if (block is BlockTNT)
        {
            block.explode(world, pos, blockState.withProperty(BlockTNT.EXPLODE, true), player)
            world.setBlockState(pos, Blocks.AIR.defaultState, 11)
        }
        else if (block is BlockGTExplosive)
        {
            block.explode(world, pos, player as EntityLivingBase)
            world.setBlockState(pos, Blocks.AIR.defaultState, 11)
        }
        else // TODO Compatible with AE2 Tiny TNT?
        {
            val offset = pos.offset(facing)
            if (world.isAirBlock(offset))
            {
                world.setBlockState(offset, Blocks.FIRE.defaultState, 11)
                if (!world.isRemote)
                    CriteriaTriggers.PLACED_BLOCK.trigger(player as EntityPlayerMP, offset, stack)
            }
        }
        stack.damageItem(1, player)
        return EnumActionResult.SUCCESS
    }

}