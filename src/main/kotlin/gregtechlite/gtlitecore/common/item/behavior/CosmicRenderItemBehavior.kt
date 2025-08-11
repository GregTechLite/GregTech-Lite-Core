package gregtechlite.gtlitecore.common.item.behavior

import codechicken.lib.model.ModelRegistryHelper
import codechicken.lib.util.TransformUtils
import gregtechlite.gtlitecore.client.renderer.ICosmicRenderBehavior
import gregtechlite.gtlitecore.client.renderer.handler.CosmicItemRenderer
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class CosmicRenderItemBehavior(private val supplier: () -> TextureAtlasSprite,
                               private val maskOpacity: Int) : ICosmicRenderBehavior
{

    @SideOnly(Side.CLIENT)
    override fun getMaskTexture(stack: ItemStack?,
                                player: EntityLivingBase?): TextureAtlasSprite?
    {
        return this.supplier.invoke()
    }

    override fun getMaskOpacity(stack: ItemStack?,
                                player: EntityLivingBase?): Float
    {
        return this.maskOpacity.toFloat()
    }

    override fun onRendererRegistry(location: ResourceLocation)
    {
        ModelRegistryHelper.register(ModelResourceLocation(location, "inventory"),
            CosmicItemRenderer(TransformUtils.DEFAULT_ITEM) { modelRegistry ->
                modelRegistry.getObject(ModelResourceLocation(location, "inventory"))
            })
    }
}