package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.common.block.base.GTLiteBerryBushVariantBlock
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

class BerryCropSeedBehavior(berryBush: GTLiteBerryBushVariantBlock, seed: ItemStack, crop: ItemStack) : CropSeedBehavior(berryBush, seed, crop)
{

    constructor(berryBush: GTLiteBerryBushVariantBlock,
                seed: MetaItem<*>.MetaValueItem,
                crop: MetaItem<*>.MetaValueItem) : this(berryBush, seed.stackForm, crop.stackForm)

    override fun onItemUse(player: EntityPlayer,
                           worldIn: World,
                           pos: BlockPos,
                           hand: EnumHand,
                           facing: EnumFacing?,
                           hitX: Float,
                           hitY: Float,
                           hitZ: Float): ActionResult<ItemStack?>?
    {
        if (!isBlocked(worldIn, pos, player))
            return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ)
        return ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand))
    }

    override fun addInformation(stack: ItemStack?, lines: MutableList<String?>)
    {
        super.addInformation(stack, lines)
        lines.add(I18n.format("gtlitecore.tooltip.crop.berry_bush.1"))
        lines.add(I18n.format("gtlitecore.tooltip.crop.berry_bush.2"))
        lines.add(I18n.format("gtlitecore.tooltip.crop.berry_bush.3"))
        lines.add(I18n.format("gtlitecore.tooltip.crop.berry_bush.4"))
    }

    private fun isBlocked(world: World, pos: BlockPos, player: EntityPlayer): Boolean
    {
        val areAnyBlocked = AtomicInteger(0)
        val blockedCrop = AtomicReference<BlockPos?>()
        BlockPos.getAllInBox(pos.up().east().north(), pos.up().west().south())
            .forEach { crop ->
                if (crop == pos.up()
                    || world.getBlockState(crop!!).getBlock() is GTLiteBerryBushVariantBlock)
                {
                    val isBlocked = AtomicBoolean(true)
                    BlockPos.getAllInBox(crop.east().north(), crop.west().south())
                        .forEach { blockPos ->
                            if (blockPos != pos.up()
                                && world.getBlockState(blockPos).getBlock()
                                    .isAir(world.getBlockState(blockPos), world, blockPos))
                                isBlocked.set(false)
                    }
                    if (isBlocked.get())
                    {
                        blockedCrop.set(crop)
                        areAnyBlocked.set(areAnyBlocked.get() + 1)
                    }
                }
            }
        if (world.isRemote && areAnyBlocked.get() > 0)
        {
            val posString = "(" + blockedCrop.get()!!.x + ", " + blockedCrop.get()!!
                .y + ", " + blockedCrop.get()!!.z + ")"
            player.sendMessage(
                TextComponentTranslation(
                    "gtlitecore.message.berry_bush_blocked",
                    posString,
                    areAnyBlocked.get()
                )
            )
        }
        return areAnyBlocked.get() > 0
    }

}
