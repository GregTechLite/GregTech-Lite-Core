package gregtechlite.gtlitecore.api.capability

import gregtech.api.capability.INotifiableHandler

interface MultipleNotifiableHandler
{

    fun getBackingNotifiers(): Collection<INotifiableHandler>

}