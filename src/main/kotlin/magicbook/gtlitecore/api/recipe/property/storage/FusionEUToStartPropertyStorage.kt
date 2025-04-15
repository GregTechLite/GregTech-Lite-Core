package magicbook.gtlitecore.api.recipe.property.storage

import gregtech.api.recipes.properties.impl.FusionEUToStartProperty

class FusionEUToStartPropertyStorage
{

    companion object
    {

        fun loadProperties()
        {
            FusionEUToStartProperty.registerFusionTier(9, "(MK4)");
            FusionEUToStartProperty.registerFusionTier(10, "(MK5)");
        }

    }

}