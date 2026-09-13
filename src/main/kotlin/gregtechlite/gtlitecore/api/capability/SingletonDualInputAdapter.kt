package gregtechlite.gtlitecore.api.capability

import gregtechlite.gtlitecore.mixins.hooks.Extension

@Extension
interface SingletonDualInputAdapter
{
    fun removeInventoryRecipeCache(inventory: PatternedSingletonDualInputInventory)

    fun clearInventoryRecipeCache()
}