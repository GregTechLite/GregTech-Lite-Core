package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials

object BlastFurnaceRecipeBackend
{

    fun init()
    {
        // Register a fake temperature for all material which blast temperature higher than all wire coil.
        TemperatureProperty.registerCoilType(Int.MAX_VALUE,
                                             GTLiteMaterials.EternityPlusToken,
                                             "eternity_plus_token") // As an empty block state instance
    }

}