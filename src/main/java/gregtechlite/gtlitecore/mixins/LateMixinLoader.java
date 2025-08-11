package gregtechlite.gtlitecore.mixins;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;

@SuppressWarnings("unused")
public final class LateMixinLoader implements ILateMixinLoader
{

    @Override
    public List<String> getMixinConfigs()
    {
        return MixinUtil.getMixinConfigs("gregtech", "jei");
    }

}
