package gregtechlite.gtlitecore.mixins.hooks;

import com.morphismmc.morphismlib.util.I18nUtil;

/**
 * A compatible wrapper for Side-Distributing {@code I18n} implementation.
 * <p>
 * This is use for make compatibility in early mixin loading stage for {@link I18nUtil},
 * we choose to wrap it with an interface static method.
 */
@Hook
public interface I18nHooks
{
    /**
     * Translates and formats {@code translationKey} with some optional parameters.
     * <p>
     * Will returns {@code translationKey} as hardcoded text in server side.
     *
     * @param translationKey The translation key which should in same namespace lang file.
     * @param args           Some optional values referenced by the format specifiers
     *                       in the {@code translationKey}.
     * @return               The translated and formatted text of {@code translationKey}
     *                       with these optional values.
     */
    static String format(String translationKey, Object... args)
    {
        return I18nUtil.format(translationKey, args);
    }

    /**
     * Translates and formats {@code translationKey} with some optional parameters.
     * <p>
     * Will returns {@code defaultKey} as hardcoded text in server side.
     *
     * @param translationKey The translation key which should in same namespace lang file.
     * @param defaultKey     The default text for server side returned value,
     *                       used human-readable text
     * @param args           Some optional values referenced by the format specifiers
     *                       in the {@code translationKey}.
     * @return               The translated and formatted text of {@code translationKey}
     *                       with these optional values.
     */
    static String format(String translationKey, String defaultKey, Object... args)
    {
        return I18nUtil.format(translationKey, defaultKey, args);
    }
}
