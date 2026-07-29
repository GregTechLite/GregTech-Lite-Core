package gregtechlite.gtlitecore.mixins;

import gregtechlite.gtlitecore.api.GTLiteValues;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.Nullable;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.Name(GTLiteValues.MOD_NAME)
@IFMLLoadingPlugin.MCVersion(Loader.MC_VERSION)
public final class EarlyMixinLoader implements CustomMixinLoader, IFMLLoadingPlugin, IEarlyMixinLoader
{
    @Override
    public List<String> getMixinConfigs()
    {
        return createMixinConfigs("minecraft");
    }

    @Override
    public String @Nullable[] getASMTransformerClass()
    {
        return null;
    }

    @Override
    public @Nullable String getModContainerClass()
    {
        return null;
    }

    @Override
    public @Nullable String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public @Nullable String getAccessTransformerClass()
    {
        return null;
    }
}
