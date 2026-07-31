package gregtechlite.gtlitecore.mixins;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;

@SuppressWarnings("unused")
public final class LateMixinLoader implements CustomMixinLoader, ILateMixinLoader
{
    @Override
    public List<String> getMixinConfigs()
    {
        return createMixinConfigs("gregtech", "jei", "modularui");
    }
}
