package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtechlite.gtlitecore.common.block.base.GTLiteCropVariantBlock
import gregtechlite.gtlitecore.common.block.base.GTLiteRootCropVariantBlock
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class CropSeedBehavior(protected val cropBlock: GTLiteCropVariantBlock, seed: ItemStack, crop: ItemStack) : IItemBehaviour
{

    init
    {
        cropBlock.seedStack = seed
        cropBlock.cropStack = crop
    }

    constructor(cropBlock: GTLiteCropVariantBlock,
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
            return ActionResult<ItemStack?>(EnumActionResult.SUCCESS, heldItem)
        }

        return ActionResult<ItemStack?>(EnumActionResult.FAIL, player.getHeldItem(hand))
    }

    override fun addInformation(stack: ItemStack?, lines: MutableList<String?>)
    {
        lines.add(I18n.format("gtlitecore.tooltip.crop.placeable_seed"))
        if (this.cropBlock is GTLiteRootCropVariantBlock)
        {
            lines.add(I18n.format("gtlitecore.tooltip.crop.root_placeable_seed.1"))
            lines.add(I18n.format("gtlitecore.tooltip.crop.root_placeable_seed.2"))
        }
    }

}
