package magicbook.gtlitecore.api.unification.material

import gregtech.api.unification.material.info.MaterialFlag
import gregtech.api.unification.material.properties.PropertyKey

class GTLiteMaterialFlags
{

    companion object
    {

        // Used to disabled ABS recipes for automatically generate handler.
        @JvmField
        val NO_ALLOY_BLAST_RECIPES = MaterialFlag.Builder("no_alloy_blast_recipes")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .build()

        // Used to disable everything related to ABS.
        @JvmField
        val DISABLE_ALLOY_PROPERTY = MaterialFlag.Builder("disable_alloy_property")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .requireFlags(NO_ALLOY_BLAST_RECIPES)
            .build()

    }

}