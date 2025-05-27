package magicbook.gtlitecore.api.utils

import magicbook.gtlitecore.api.GTLiteTags
import java.util.*

class GTLiteValues
{

    companion object
    {

        const val MODID = GTLiteTags.MOD_ID
        const val NAME = GTLiteTags.MOD_NAME
        const val VERSION = GTLiteTags.VERSION

        const val TICK = 1
        const val SECOND = 20 * TICK
        const val MINUTE = 60 * SECOND
        const val HOUR = 60 * MINUTE

        @JvmField
        val RNG: Random = XSTR()

    }

}