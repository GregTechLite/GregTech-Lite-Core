package magicbook.gtlitecore.common.block.itemblocks;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import magicbook.gtlitecore.client.renderer.IItemRendererManager;
import magicbook.gtlitecore.client.renderer.handler.DimensionDisplayItemRenderer;
import magicbook.gtlitecore.common.block.blocks.BlockDimensionDisplay;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DimensionDisplayItemBlock extends ItemBlock implements IItemRendererManager
{

    public DimensionDisplayItemBlock(Block block)
    {
        super(block);
    }

    public static String getDimension(ItemStack stack)
    {
        if (stack.getItem() instanceof DimensionDisplayItemBlock)
        {
            return ((BlockDimensionDisplay) Block.getBlockFromItem(stack.getItem())).getDimension();
        }
        return null;
    }

    @Override
    public void onRendererRegistry(ResourceLocation location)
    {
        ModelRegistryHelper.register(new ModelResourceLocation(location, "inventory"),
                new DimensionDisplayItemRenderer(TransformUtils.DEFAULT_ITEM, modelRegistry ->
                        modelRegistry.getObject(new ModelResourceLocation(location, "inventory"))));
    }

}
