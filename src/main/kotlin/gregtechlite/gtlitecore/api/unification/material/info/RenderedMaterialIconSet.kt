package gregtechlite.gtlitecore.api.unification.material.info

import gregtech.api.unification.material.info.MaterialIconSet
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer
import gregtechlite.gtlitecore.client.renderer.ItemRendererManager
import net.minecraft.util.ResourceLocation

class RenderedMaterialIconSet(name: String, parentIconSet: MaterialIconSet? = null, isRootIconSet: Boolean = true,
                              private val rendererManager: ItemRendererManager)
    : MaterialIconSet(name, parentIconSet, isRootIconSet), CustomItemRenderer, ItemRendererManager
{
    override fun getRendererManager(): ItemRendererManager = rendererManager

    override fun onRendererRegistry(location: ResourceLocation) = rendererManager.onRendererRegistry(location)
}

