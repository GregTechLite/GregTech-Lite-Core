package magicbook.gtlitecore.api.item

import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.PropertyKey

class GTLiteToolHelper
{

    companion object
    {

        /**
         * Get maximum durability of material from ToolProperty,
         * this method is just a lazy method to change ToolProperty#getToolDurability().
         */
        @JvmStatic
        fun getMaxDurability(material: Material): Int
        {
            if (!material.hasProperty(PropertyKey.TOOL))
                throw IllegalArgumentException("Material ${material.name} does not have ToolProperty!")
            return material.getProperty(PropertyKey.TOOL).toolDurability
        }

        /**
         * Get maximum crafting durability of material from ToolProperty.
         * This method is a choice to get crafting durability via material.
         */
        @JvmStatic
        fun getMaxCraftingDurability(material: Material): Int
        {
            if (!material.hasProperty(PropertyKey.TOOL))
                throw IllegalArgumentException("Material ${material.name} does not have ToolProperty!")
            return material.getProperty(PropertyKey.TOOL).toolDurability / 2
        }

    }

}