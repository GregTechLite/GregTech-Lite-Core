package gregtechlite.gtlitecore.common.item.behavior

import codechicken.lib.model.ModelRegistryHelper
import codechicken.lib.util.TransformUtils
import gregtechlite.gtlitecore.client.renderer.CosmicRenderBehavior
import gregtechlite.gtlitecore.client.renderer.handler.item.MaskUniversiumItemRenderer
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MaskUniversiumRenderItemBehavior(private val maskTexture: () -> TextureAtlasSprite,
                                       private val maskOpacity: Int) : CosmicRenderBehavior
{
    @SideOnly(Side.CLIENT)
    override fun getMaskTexture(stack: ItemStack, player: EntityLivingBase?): TextureAtlasSprite? = maskTexture.invoke()

    @SideOnly(Side.CLIENT)
    override fun getMaskOpacity(stack: ItemStack, player: EntityLivingBase?): Float = maskOpacity.toFloat()

    @SideOnly(Side.CLIENT)
    override fun onRendererRegistry(location: ResourceLocation)
    {
        ModelRegistryHelper.register(ModelResourceLocation(location, "inventory"),
            MaskUniversiumItemRenderer(TransformUtils.DEFAULT_ITEM) {
                it.getObject(ModelResourceLocation(location, "inventory"))
            })
    }
}