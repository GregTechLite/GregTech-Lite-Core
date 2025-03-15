package magicbook.gtlitecore.api.block

import net.minecraft.util.IStringSerializable

enum class GTLiteWoodType(private val typeName: String) : IStringSerializable
{

    BANANA("banana"),
    ORANGE("orange"),
    MANGO("mango"),
    APRICOT("apricot"),
    LEMON("lemon"),
    LIME("lime"),
    OLIVE("olive"),
    NUTMEG("nutmeg"),
    COCONUT("coconut"),
    RAINBOW("rainbow");

    override fun getName(): String = this.typeName

}