package magicbook.gtlitecore.api.utils

import gregtech.api.GTValues
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import java.util.*

class TranslateUtility
{

    companion object
    {

        @JvmStatic
        fun format(translationKey: String, defaultValue: String)
            = if (GTValues.isClientSide()) format(translationKey) else defaultValue

        fun format(translationKey: String, defaultValue: String, vararg params: Any)
            = if (GTValues.isClientSide()) format(translationKey, params) else defaultValue

        /**
         * Transformed from [I18n.format].
         */
        @JvmStatic
        fun format(translationKey: String) = I18n.format(translationKey)

        /**
         * Transformed from [I18n.format].
         */
        @JvmStatic
        fun format(translationKey: String, vararg params: Any) = I18n.format(translationKey, params)

        @JvmStatic
        fun translateToLocal(translationKey: String): String
        {
            if (net.minecraft.util.text.translation.I18n.canTranslate(translationKey))
                return net.minecraft.util.text.translation.I18n.translateToLocal(translationKey)
            else
                return net.minecraft.util.text.translation.I18n.translateToFallback(translationKey)
        }

        @JvmStatic
        fun translateToLocalFormatted(translationKey: String, vararg params: Any): String
        {
            val translation = translateToLocal(translationKey)
            try
            {
                return String.format(translation, params)
            }
            catch (exception: IllegalFormatException)
            {
                GTLiteLog.logger.error("Format error: {}", translationKey, exception)
                return "Format error: $translation"
            }
        }

        @JvmStatic
        fun toLowercaseWithLocale(translationKey: String) = translationKey.lowercase(getLocale())

        @JvmStatic
        private fun getLocale(): Locale = Minecraft.getMinecraft().languageManager.currentLanguage.javaLocale

    }

}