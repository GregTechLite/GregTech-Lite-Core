package gregtechlite.gtlitecore.mixins;

import com.morphismmc.morphismlib.util.I18nUtil;

@MixinOnly
public interface Texts
{

    static String literal(String text)
    {
        return text;
    }

    static String translated(String translationKey, Object... args)
    {
        return I18nUtil.format(translationKey, args);
    }

    static String translated(String translationKey, String defaultKey, Object... args)
    {
        return I18nUtil.format(translationKey, defaultKey, args);
    }

}
