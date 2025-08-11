package gregtechlite.gtlitecore.mixins;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.Nullable;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.util.List;
import java.util.Map;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_NAME;

@IFMLLoadingPlugin.Name(MOD_NAME)
@IFMLLoadingPlugin.MCVersion(Loader.MC_VERSION)
public final class EarlyMixinLoader implements IFMLLoadingPlugin, IEarlyMixinLoader
{

    @Override
    public List<String> getMixinConfigs()
    {
        return MixinUtil.getMixinConfigs("minecraft");
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return null;
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

}
