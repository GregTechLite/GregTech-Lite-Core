package gregtechlite.gtlitecore.client.renderer

import gregtech.api.items.metaitem.stats.IItemComponent
import net.minecraft.util.ResourceLocation

interface ItemRendererManager : IItemComponent
{
    fun onRendererRegistry(location: ResourceLocation)
}