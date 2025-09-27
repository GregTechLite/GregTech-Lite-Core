package gregtechlite.gtlitecore.api.capability

import gregtech.api.capability.INotifiableHandler
import gregtechlite.gtlitecore.api.mixins.MixinExtension

@MixinExtension
interface MultipleNotifiableHandler
{

    fun getBackingNotifiers(): Collection<INotifiableHandler>

}