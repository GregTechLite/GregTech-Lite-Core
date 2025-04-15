package magicbook.gtlitecore.api.recipe.property.storage

import magicbook.gtlitecore.api.recipe.property.AdvancedFusionTieredProperty

class AdvancedFusionTieredPropertyStorage
{

    companion object
    {

        fun loadProperties()
        {
            AdvancedFusionTieredProperty.register(1, "MK1")
            AdvancedFusionTieredProperty.register(2, "MK2")
            AdvancedFusionTieredProperty.register(3, "MK3")
            AdvancedFusionTieredProperty.register(4, "MK4")
            AdvancedFusionTieredProperty.register(5, "MK5")
        }

    }

}