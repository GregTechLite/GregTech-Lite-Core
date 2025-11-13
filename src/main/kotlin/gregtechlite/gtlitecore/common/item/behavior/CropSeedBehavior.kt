package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtechlite.gtlitecore.api.item.DescriptiveStats
import gregtechlite.gtlitecore.common.block.GTLiteCropBlock
import gregtechlite.gtlitecore.common.block.GTLiteRootCropBlock
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * @property cropBlock
 * @property seed
 * @property crop
 *
 * @constructor
 */
open class CropSeedBehavior(protected val cropBlock: GTLiteCropBlock,
                            seed: ItemStack,
                            crop: ItemStack) : IItemBehaviour, DescriptiveStats
{

    init
    {
        cropBlock.seedStack = seed
        cropBlock.cropStack = crop
    }

    constructor(cropBlock: GTLiteCropBlock,
                seed: MetaItem<*>.MetaValueItem,
                crop: MetaItem<*>.MetaValueItem) : this(cropBlock, seed.stackForm, crop.stackForm)

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos,
                           hand: EnumHand, facing: EnumFacing?,
                           hitX: Float, hitY: Float, hitZ: Float): ActionResult<ItemStack?>?
    {
        if (worldIn.isAirBlock(pos.up())
            && this.cropBlock.defaultState.getBlock().canPlaceBlockAt(worldIn, pos.up()))
        {
            worldIn.setBlockState(pos.up(), this.cropBlock.defaultState)
            val heldItem = player.getHeldItem(hand)
            heldItem.shrink(1)
            return ActionResult(EnumActionResult.SUCCESS, heldItem)
        }

        return ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand))
    }

    override fun addInformation(stack: ItemStack, lines: MutableList<String>)
    {
        val cropType = "gtlitecore.tooltip.crop.type"
        val cropPrefix = "$cropType." + when (cropBlock)
        {
            is GTLiteRootCropBlock -> "root"
            else -> "standard"
        }
        lines.add(I18n.format(cropType) + I18n.format(cropPrefix))
    }

    override fun getDescription(): Array<String> = when (cropBlock)
    {
        is GTLiteRootCropBlock -> arrayOf("gtlitecore.jei.root_crop_info")
        else -> arrayOf("gtlitecore.jei.standard_crop_info")
    }

}
