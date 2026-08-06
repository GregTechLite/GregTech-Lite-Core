package gregtechlite.gtlitecore.api.capability

import gregtech.api.capability.INotifiableHandler
import gregtechlite.gtlitecore.mixins.hooks.Extension

@Extension
interface MultipleNotifiableHandler
{
    fun getBackingNotifiers(): Collection<INotifiableHandler>
}