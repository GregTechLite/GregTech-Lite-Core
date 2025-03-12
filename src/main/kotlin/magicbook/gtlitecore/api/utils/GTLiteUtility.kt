package magicbook.gtlitecore.api.utils

import gregtech.api.GTValues
import net.minecraft.util.ResourceLocation

class GTLiteUtility
{

    companion object
    {

        @JvmField
        var collectorTankSizeFunction: java.util.function.Function<Int, Int> = java.util.function.Function<Int, Int> { tier ->
            if (tier <= GTValues.LV)
                return@Function 16000
            if (tier === GTValues.MV)
                return@Function 24000
            if (tier === GTValues.HV)
                return@Function 32000
            64000
        }

        @JvmStatic
        fun gtliteId(path: String) = ResourceLocation(GTLiteValues.MODID, path)

        @JvmStatic
        fun getId(namespace: String, path: String) = ResourceLocation(namespace, path)

    }

}