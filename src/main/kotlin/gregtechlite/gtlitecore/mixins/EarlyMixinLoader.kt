package gregtechlite.gtlitecore.mixins

import gregtechlite.gtlitecore.api.MOD_NAME
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import zone.rong.mixinbooter.IEarlyMixinLoader

@IFMLLoadingPlugin.Name(MOD_NAME)
@IFMLLoadingPlugin.MCVersion(Loader.MC_VERSION)
class EarlyMixinLoader : CustomMixinLoader, IFMLLoadingPlugin, IEarlyMixinLoader
{
    override fun getMixinConfigs(): List<String> = createMixinConfigs("minecraft")

    override fun getASMTransformerClass(): Array<out String>? = null

    override fun getModContainerClass(): String? = null

    override fun getSetupClass(): String? = null

    override fun injectData(data: Map<String, Any>) {}

    override fun getAccessTransformerClass(): String? = null
}