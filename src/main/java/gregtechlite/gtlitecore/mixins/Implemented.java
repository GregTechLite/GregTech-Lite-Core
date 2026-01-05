package gregtechlite.gtlitecore.mixins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Implemented attributor for all mixins classes.
 * <p>
 * This annotation marked the mixins class is an adhoc resolve which implemented from GTCEu or other mods unrelease
 * contents or referenced with some external contents.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Implemented
{

    /**
     * The source link for the implemented contents.
     *
     * @return The url format link for the implemented contents. Should use gh pull request link, issue link, e.t.c.
     */
    String[] at() default "";

}
