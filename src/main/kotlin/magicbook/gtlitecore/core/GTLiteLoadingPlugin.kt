package magicbook.gtlitecore.core

import magicbook.gtlitecore.api.utils.GTLiteValues
import net.minecraftforge.common.ForgeVersion
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import zone.rong.mixinbooter.IEarlyMixinLoader

@Suppress("unused")
@IFMLLoadingPlugin.Name(GTLiteValues.MODID)
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
class GTLiteLoadingPlugin : IFMLLoadingPlugin, IEarlyMixinLoader
{

    override fun getMixinConfigs(): List<String> = listOf("mixins.${GTLiteValues.MODID}.early.json")

    override fun getASMTransformerClass(): Array<String> = arrayOf("magicbook.gtlitecore.core.GTLiteASMTransformer")

    override fun getModContainerClass(): String? = null

    override fun getSetupClass(): String? = null

    override fun injectData(data: MutableMap<String, Any>?) {}

    override fun getAccessTransformerClass(): String? = null
}