package gregtechlite.gtlitecore.mixins.hooks

import com.morphismmc.morphismlib.util.I18nUtil

/**
 * A compatible wrapper for Side-Distributing `I18n` implementation.
 *
 * This is use for make compatibility in early mixin loading stage for [I18nUtil],
 * we choose to wrap it with an interface static method.
 */
@Hook
object I18nHooks
{
    /**
     * Translates and formats `translationKey` with some optional parameters.
     *
     * Will returns `translationKey` as hardcoded text in server side.
     *
     * @param translationKey The translation key which should in same namespace lang file.
     * @param args           Some optional values referenced by the format specifiers
     *                       in the `translationKey`.
     * @return               The translated and formatted text of `translationKey`
     *                       with these optional values.
     */
    @JvmStatic
    fun format(translationKey: String, vararg args: Any): String
        = I18nUtil.format(translationKey, *args)

    /**
     * Translates and formats `translationKey` with some optional parameters.
     *
     * Will returns `defaultKey` as hardcoded text in server side.
     *
     * @param translationKey The translation key which should in same namespace lang file.
     * @param defaultKey     The default text for server side returned value,
     *                       used human-readable text
     * @param args           Some optional values referenced by the format specifiers
     *                       in the `translationKey`.
     * @return               The translated and formatted text of `translationKey`
     *                       with these optional values.
     */
    @JvmStatic
    fun format(translationKey: String, defaultKey: String, vararg args: Any): String
        = I18nUtil.format(translationKey, defaultKey, *args)
}