package gregtechlite.gtlitecore.api.capability

import gregtechlite.gtlitecore.api.capability.handler.SingletonDualInputHandler

interface PatternedSingletonDualInputInventory : SingletonDualInputInventory
{
    fun getPatternInputs(): SingletonDualInputHandler

    fun shouldBeCached(): Boolean
}
