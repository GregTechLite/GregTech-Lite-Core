package gregtechlite.gtlitecore.mixins

import com.morphismmc.morphismlib.integration.Mods
import zone.rong.mixinbooter.ILateMixinLoader

@Suppress("unused")
class LateMixinLoader : CustomMixinLoader, ILateMixinLoader
{
    override fun getMixinConfigs(): List<String> = createMixinConfigs("appeng", "gregtech", "jei", "modularui")

    override fun shouldMixinConfigQueue(mixinConfig: String): Boolean = when (mixinConfig)
    {
        mixinName(Mods.AppliedEnergistics2.name) -> Mods.AppliedEnergistics2.isActive
        else                                     -> true
    }
}