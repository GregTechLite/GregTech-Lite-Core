package gregtechlite.gtlitecore.api.unification.material.info

import gregtech.api.unification.material.info.MaterialIconSet
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer
import gregtechlite.gtlitecore.client.renderer.ItemRendererManager
import net.minecraft.util.ResourceLocation

class RenderedMaterialIconSet(name: String,
                              parentIconSet: MaterialIconSet? = null,
                              isRootIconSet: Boolean = true,
                              override val rendererManager: ItemRendererManager)
    : MaterialIconSet(name, parentIconSet, isRootIconSet), CustomItemRenderer, ItemRendererManager
{
    override fun onRendererRegistry(location: ResourceLocation) = rendererManager.onRendererRegistry(location)
}

