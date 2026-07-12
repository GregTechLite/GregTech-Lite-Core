package gregtechlite.gtlitecore.api.recipe.property.value

import net.minecraft.util.ResourceLocation

class RequestAdditionalPropertyValue(val additionalStructures: MutableList<ResourceLocation>)
{
    constructor(vararg additionalStructures: ResourceLocation) : this(mutableListOf(*additionalStructures))
}