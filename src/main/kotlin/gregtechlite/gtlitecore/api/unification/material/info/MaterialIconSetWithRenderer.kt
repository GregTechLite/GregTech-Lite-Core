package gregtechlite.gtlitecore.api.unification.material.info

import gregtech.api.unification.material.info.MaterialIconSet
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer
import gregtechlite.gtlitecore.client.renderer.ItemRendererManager
import net.minecraft.util.ResourceLocation

class MaterialIconSetWithRenderer : MaterialIconSet, CustomItemRenderer, ItemRendererManager
{

    private val rendererManager: ItemRendererManager

    constructor(name: String,
                rendererManager: ItemRendererManager
    ) : super(name)
    {
        this.rendererManager = rendererManager
    }

    constructor(name: String,
                parentIconSet: MaterialIconSet,
                rendererManager: ItemRendererManager
    ) : super(name, parentIconSet)
    {
        this.rendererManager = rendererManager
    }

    constructor(name: String,
                parentIconSet: MaterialIconSet?,
                isRootIconSet: Boolean,
                rendererManager: ItemRendererManager
    ) : super(name, parentIconSet, isRootIconSet)
    {
        this.rendererManager = rendererManager
    }

    override fun getRendererManager(): ItemRendererManager = this.rendererManager

    override fun onRendererRegistry(location: ResourceLocation?)
    {
        this.rendererManager.onRendererRegistry(location)
    }

}

