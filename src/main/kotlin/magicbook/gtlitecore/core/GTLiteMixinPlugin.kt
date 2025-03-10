package magicbook.gtlitecore.core

import magicbook.gtlitecore.api.utils.GTLiteValues
import zone.rong.mixinbooter.ILateMixinLoader

@Suppress("unused")
class GTLiteMixinPlugin : ILateMixinLoader
{

    override fun getMixinConfigs(): List<String> = listOf("mixins.${GTLiteValues.MODID}.late.json")

}