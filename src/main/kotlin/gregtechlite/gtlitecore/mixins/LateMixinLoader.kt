package gregtechlite.gtlitecore.mixins

import zone.rong.mixinbooter.ILateMixinLoader

@Suppress("unused")
class LateMixinLoader : CustomMixinLoader, ILateMixinLoader
{
    override fun getMixinConfigs(): List<String> = createMixinConfigs("gregtech", "jei", "modularui")
}