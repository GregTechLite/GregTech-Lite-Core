package gregtechlite.gtlitecore.common.item.behavior

import codechicken.lib.model.ModelRegistryHelper
import codechicken.lib.util.TransformUtils
import gregtechlite.gtlitecore.client.renderer.UniversiumRenderBehavior
import gregtechlite.gtlitecore.client.renderer.handler.UniversiumItemRenderer
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class UniversiumRenderItemBehavior(private val cosmicOpacity: Float = 2.5f) : UniversiumRenderBehavior
{

    override fun getCosmicOpacity(): Float = cosmicOpacity

    @SideOnly(Side.CLIENT)
    override fun onRendererRegistry(location: ResourceLocation)
    {
        ModelRegistryHelper.register(ModelResourceLocation(location, "inventory"),
            UniversiumItemRenderer(TransformUtils.DEFAULT_ITEM) { modelRegistry ->
                modelRegistry.getObject(ModelResourceLocation(location, "inventory"))
            })
    }

}