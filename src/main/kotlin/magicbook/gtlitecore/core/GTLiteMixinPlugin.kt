package magicbook.gtlitecore.core

import zone.rong.mixinbooter.ILateMixinLoader

@Suppress("unused")
class GTLiteMixinPlugin : ILateMixinLoader
{

    override fun getMixinConfigs(): List<String> = listOf("mixins.gtlitecore.late.json")

}