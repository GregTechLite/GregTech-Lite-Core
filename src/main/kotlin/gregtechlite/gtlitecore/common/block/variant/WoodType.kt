package gregtechlite.gtlitecore.common.block.variant

import net.minecraft.util.IStringSerializable

enum class WoodType(private val serializedName: String) : IStringSerializable
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

    override fun getName(): String = this.serializedName

}