package magicbook.gtlitecore.common.item.behavior;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import magicbook.gtlitecore.common.block.blocks.GTLiteCropVariantBlock;
import magicbook.gtlitecore.common.block.blocks.GTLiteRootCropVariantBlock;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class CropSeedBehavior implements IItemBehaviour
{

    protected final GTLiteCropVariantBlock crop;

    public CropSeedBehavior(GTLiteCropVariantBlock cropBlock, ItemStack seed, ItemStack crop)
    {
        this.crop = cropBlock;
        cropBlock.setSeed(seed);
        cropBlock.setCrop(crop);
    }

    public CropSeedBehavior(GTLiteCropVariantBlock cropBlock, MetaItem<?>.MetaValueItem seed, MetaItem<?>.MetaValueItem crop)
    {
        this(cropBlock, seed.getStackForm(), crop.getStackForm());
    }

    @Override
    public ActionResult<ItemStack> onItemUse(EntityPlayer player, World worldIn, BlockPos pos,
                                             EnumHand hand, EnumFacing facing,
                                             float hitX, float hitY, float hitZ)
    {

        if (worldIn.isAirBlock(pos.up()) && this.crop.getDefaultState().getBlock()
                .canPlaceBlockAt(worldIn, pos.up()))
        {
            worldIn.setBlockState(pos.up(), this.crop.getDefaultState());
            ItemStack heldItem = player.getHeldItem(hand);
            heldItem.shrink(1);
            return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
        }

        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }

    @Override
    public void addInformation(ItemStack stack, List<String> lines)
    {

        lines.add(I18n.format("gtlitecore.tooltip.crop.placeable_seed"));
        if (this.crop instanceof GTLiteRootCropVariantBlock)
        {
            lines.add(I18n.format("gtlitecore.tooltip.crop.root_placeable_seed.1"));
            lines.add(I18n.format("gtlitecore.tooltip.crop.root_placeable_seed.2"));
        }

    }

}
