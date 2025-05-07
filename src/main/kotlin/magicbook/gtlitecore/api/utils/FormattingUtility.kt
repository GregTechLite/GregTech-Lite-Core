package magicbook.gtlitecore.api.utils

import gregtech.api.GTValues
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import java.util.*
import java.util.function.Supplier

/**
 * Utilities for formatting, text interacted, I18n, etc.
 */
class FormattingUtility
{

    companion object
    {

        /**
         * Create a static string text by a supplier.
         *
         * @param text A nullable string.
         * @return     A nullable string supplier.
         *
         * @author glowredman
         */
        @JvmStatic
        fun text(text: String?): Supplier<String?> = Supplier { text }

        /**
         * Create a static and formatted string text by a supplier and its args.
         *
         * @param format A nullable string.
         * @param args   Args in formatted string text.
         * @return       A non-null string supplier.
         *
         * @author glowredman
         */
        @JvmStatic
        fun text(format: String?, vararg args: Any?): Supplier<String> = Supplier {
            String.format(Locale.ROOT, format!!, *args)
        }

        /**
         * Create a static and formatted translated text by a supplier.
         *
         * @param translationKey Translated text.
         * @return               A non-null string supplier.
         *
         * @author glowredman
         */
        @JvmStatic
        fun translatedText(translationKey: String): Supplier<String> = Supplier {
            translateToLocal(translationKey)
        }

        /**
         * Create a static and formatted translated text by a supplier.
         *
         * @param translationKey Translated text.
         * @param args           Args in formatted string text.
         * @return               A non-null string supplier.
         *
         * @author glowredman
         */
        @JvmStatic
        fun translatedText(translationKey: String, vararg args: Any?): Supplier<String> = Supplier {
            translateToLocalFormatted(translationKey, args) }

        /**
         * Create a chained supplier that concatenates string results from multiple suppliers
         * in sequence, when the returned supplier method is invoked, it will call each input
         * supplier method. In the order they are provided and append all results into a single
         * concatenated string.
         *
         * This method is safely handles the varargs parameter array without exposing it to
         * unsafe operations or heap pollution.
         *
         * @throws NullPointerException When [parts] array is `null`, or any elements is `null`.
         *
         * @param parts Variable number of string suppliers whose results will be concatenated.
         * @return      A non-null supplier that provides the concatenated string when invoked.
         *
         * @author glowredman
         */
        @JvmStatic
        @SafeVarargs
        fun chain(vararg parts: Supplier<String?>): Supplier<String> = Supplier {
            val stringBuilder = StringBuilder()
            for (text in parts)
                stringBuilder.append(text.get())
            stringBuilder.toString()
        }

        /**
         * Get a formated text [translationKey] or [defaultValue].
         *
         * @author vfyjxf
         */
        @JvmStatic
        fun format(translationKey: String, defaultValue: String)
            = if (GTValues.isClientSide()) format(translationKey) else defaultValue

        /**
         * Get a formated text [translationKey] or [defaultValue].
         *
         * @author vfyjxf
         */
        @JvmStatic
        fun format(translationKey: String, defaultValue: String, vararg params: Any): String
            = if (GTValues.isClientSide()) format(translationKey, params) else defaultValue

        /**
         * Transformed method for [I18n.format], this is a safety ensure method
         * for two sides import.
         *
         * @author vfyjxf
         */
        @JvmStatic
        fun format(translationKey: String): String = I18n.format(translationKey)

        /**
         * Transformed method for [I18n.format], this is a safety ensure method
         * for two sides import.
         *
         * @author vfyjxf
         */
        @JvmStatic
        fun format(translationKey: String, vararg params: Any): String = I18n.format(translationKey, params)

        /**
         * Transformed method for deprecated `I18n` class.
         *
         * @author Magic_Sweepy
         */
        @Suppress("deprecation")
        @JvmStatic
        fun translateToLocal(translationKey: String): String
        {
            return if (net.minecraft.util.text.translation.I18n.canTranslate(translationKey))
                net.minecraft.util.text.translation.I18n.translateToLocal(translationKey)
            else
                net.minecraft.util.text.translation.I18n.translateToFallback(translationKey)
        }

        /**
         * Transformed method for deprecated `I18n` class.
         *
         * @author Magic_Sweepy
         */
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
                GTLiteLog.logger.error("Format error: $translationKey", exception)
                return "Format error: $translation"
            }
        }

        /**
         * Let [translationKey] to lowercase with locale.
         *
         * @author mezz
         */
        @JvmStatic
        fun toLowercaseWithLocale(translationKey: String) = translationKey.lowercase(getLocale())

        /**
         * Get locale by minecraft language manager.
         *
         * @author mezz
         */
        @JvmStatic
        private fun getLocale(): Locale = Minecraft.getMinecraft().languageManager.currentLanguage.javaLocale

        @JvmStatic
        fun subDigits(number: String): String
        {
            val numbers = number.toCharArray()
            val chars = CharArray(numbers.size)
            for (i in numbers.indices)
            {
                chars[i] = when (numbers[i])
                {
                    '0' -> '₀'
                    '1' -> '₁'
                    '2' -> '₂'
                    '3' -> '₃'
                    '4' -> '₄'
                    '5' -> '₅'
                    '6' -> '₆'
                    '7' -> '₇'
                    '8' -> '₈'
                    '9' -> '₉'
                    else -> numbers[i]
                }
            }
            return String(chars)
        }

        @JvmStatic
        fun supDigits(number: String): String
        {
            val numbers = number.toCharArray()
            val chars = CharArray(numbers.size)
            for (i in numbers.indices) {
                chars[i] = when (numbers[i])
                {
                    '0' -> '⁰'
                    '1' -> '¹'
                    '2' -> '²'
                    '3' -> '³'
                    '4' -> '⁴'
                    '5' -> '⁵'
                    '6' -> '⁶'
                    '7' -> '⁷'
                    '8' -> '⁸'
                    '9' -> '⁹'
                    else -> numbers[i]
                }
            }
            return String(chars)
        }

    }

}