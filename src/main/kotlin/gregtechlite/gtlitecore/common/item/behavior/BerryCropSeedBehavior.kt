package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.common.block.GTLiteBerryBushBlock
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

class BerryCropSeedBehavior(berryBush: GTLiteBerryBushBlock, seed: ItemStack, crop: ItemStack) : CropSeedBehavior(berryBush, seed, crop)
{

    constructor(berryBush: GTLiteBerryBushBlock, seed: MetaItem<*>.MetaValueItem, crop: MetaItem<*>.MetaValueItem)
            : this(berryBush, seed.stack(), crop.stack())

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing?,
                           hitX: Float, hitY: Float, hitZ: Float): ActionResult<ItemStack?>?
    {
        if (!isBlocked(worldIn, pos, player))
        {
            return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ)
        }
        return ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand))
    }

    override fun addInformation(stack: ItemStack, lines: MutableList<String>)
    {
        val cropType = "gtlitecore.tooltip.crop.type"
        val cropPrefix = "${cropType}.berry"
        lines.add(I18n.format(cropType) + I18n.format(cropPrefix))
    }

    private fun isBlocked(world: World, pos: BlockPos, player: EntityPlayer): Boolean
    {
        var blockedCount = 0
        var blockedCrop: BlockPos? = null

        //  |
        // [ ]
        val up = pos.up()

        //        [ ]
        //         |
        // [ ] -- [ ]
        //  |
        // [ ]
        val start = up.east().north()

        // [ ] -- [ ]
        //  |      |
        // [ ]    [ ]
        val end = up.west().south()

        for (crop in BlockPos.getAllInBox(start, end))
        {
            if (crop == up || world.getBlockState(crop).block is GTLiteBerryBushBlock)
            {
                var isBlocked = true
                val innerStart = crop.east().north()
                val innerEnd = crop.west().south()

                for (blockPos in BlockPos.getAllInBox(innerStart, innerEnd))
                {
                    // If the block state at pos is air, then marked it is not be blocked.
                    if (blockPos != up && world.getBlockState(blockPos)
                        .block.isAir(world.getBlockState(blockPos), world, blockPos))
                    {
                        isBlocked = false
                        break
                    }
                }
                // If the block state at pos is blocked, then update count.
                if (isBlocked)
                {
                    blockedCrop = crop
                    blockedCount++
                }
            }
        }

        // If the crop at pos is blocked with several blocks, then sending a message to player.
        if (world.isRemote && blockedCount > 0)
        {
            val cropPos = blockedCrop ?: up
            val cropPosInfo = "(${cropPos.x}, ${cropPos.y}, ${cropPos.z})"
            player.sendMessage(TextComponentTranslation(
                "gtlitecore.message.berry_bush_blocked", cropPosInfo, blockedCount))
        }

        return blockedCount > 0
    }

    override fun getDescription(): Array<String>
    {
        return arrayOf("gtlitecore.jei.berry_crop_info.1", "gtlitecore.jei.berry_crop_info.2",
                       "gtlitecore.jei.berry_crop_info.3", "gtlitecore.jei.berry_crop_info.4")
    }

}
