package gregtechlite.gtlitecore.api.integration

import gregtechlite.gtlitecore.mixins.MixinExtension
import mezz.jei.api.IModRegistry

@MixinExtension
interface InjectableModRegistry
{

    fun registerPostContext(registry: IModRegistry)

}