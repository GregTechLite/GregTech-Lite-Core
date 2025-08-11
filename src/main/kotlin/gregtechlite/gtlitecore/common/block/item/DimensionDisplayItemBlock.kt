package gregtechlite.gtlitecore.common.block.item

import codechicken.lib.model.ModelRegistryHelper
import gregtechlite.gtlitecore.client.renderer.IItemRendererManager
import gregtechlite.gtlitecore.client.renderer.handler.DimensionDisplayItemRenderer
import gregtechlite.gtlitecore.common.block.base.BlockDimensionDisplay
import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class DimensionDisplayItemBlock(block: Block) : ItemBlock(block), IItemRendererManager
{

    override fun onRendererRegistry(location: ResourceLocation)
    {
        val modelLocation = ModelResourceLocation(location, "inventory")
        ModelRegistryHelper.register(modelLocation, DimensionDisplayItemRenderer(modelLocation))
    }

    companion object
    {
        fun getDimension(stack: ItemStack): String?
        {
            if (stack.item is DimensionDisplayItemBlock)
            {
                return (Block.getBlockFromItem(stack.item) as BlockDimensionDisplay).dimension
            }
            return null
        }
    }

}
