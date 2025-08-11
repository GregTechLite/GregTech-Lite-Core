package gregtechlite.gtlitecore.api.unification.material.info

import gregtech.api.unification.material.info.MaterialIconSet
import gregtechlite.gtlitecore.client.renderer.IItemRenderer
import gregtechlite.gtlitecore.client.renderer.IItemRendererManager
import net.minecraft.util.ResourceLocation

class MaterialIconSetWithRenderer : MaterialIconSet, IItemRenderer, IItemRendererManager
{

    private val rendererManager: IItemRendererManager

    constructor(name: String,
                rendererManager: IItemRendererManager) : super(name)
    {
        this.rendererManager = rendererManager
    }

    constructor(name: String,
                parentIconSet: MaterialIconSet,
                rendererManager: IItemRendererManager) : super(name, parentIconSet)
    {
        this.rendererManager = rendererManager
    }

    constructor(name: String,
                parentIconSet: MaterialIconSet?,
                isRootIconSet: Boolean,
                rendererManager: IItemRendererManager) : super(name, parentIconSet, isRootIconSet)
    {
        this.rendererManager = rendererManager
    }

    override fun getRendererManager(): IItemRendererManager = this.rendererManager

    override fun onRendererRegistry(location: ResourceLocation?)
    {
        this.rendererManager.onRendererRegistry(location)
    }

}

