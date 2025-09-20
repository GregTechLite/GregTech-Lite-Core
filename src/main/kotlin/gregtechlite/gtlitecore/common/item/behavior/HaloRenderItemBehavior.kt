package gregtechlite.gtlitecore.common.item.behavior

import codechicken.lib.model.ModelRegistryHelper
import codechicken.lib.util.TransformUtils
import gregtechlite.gtlitecore.client.renderer.HaloRenderBehavior
import gregtechlite.gtlitecore.client.renderer.handler.HaloItemRenderer
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class HaloRenderItemBehavior(private val haloSize: Int,
                             private val haloColor: Int,
                             private val supplier: () -> () -> TextureAtlasSprite,
                             private val drawPulse: Boolean) : HaloRenderBehavior
{

    override fun shouldDrawHalo(): Boolean = true

    @SideOnly(Side.CLIENT)
    override fun getHaloTexture(): TextureAtlasSprite? = this.supplier.invoke().invoke()

    override fun getHaloColor(): Int = this.haloColor

    override fun getHaloSize(): Int = this.haloSize

    override fun shouldDrawPulse(): Boolean = this.drawPulse

    @SideOnly(Side.CLIENT)
    override fun onRendererRegistry(location: ResourceLocation)
    {
        ModelRegistryHelper.register(ModelResourceLocation(location, "inventory"),
            HaloItemRenderer(TransformUtils.DEFAULT_ITEM) { modelRegistry ->
                modelRegistry.getObject(ModelResourceLocation(location, "inventory"))
            })
    }

}