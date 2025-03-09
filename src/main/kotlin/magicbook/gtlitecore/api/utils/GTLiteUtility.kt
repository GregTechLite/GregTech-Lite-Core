package magicbook.gtlitecore.api.utils

import net.minecraft.util.ResourceLocation

class GTLiteUtility
{

    companion object
    {

        @JvmStatic
        fun gtliteId(path: String) = ResourceLocation(GTLiteValues.MODID, path)

        @JvmStatic
        fun getId(namespace: String, path: String) = ResourceLocation(namespace, path)

    }

}