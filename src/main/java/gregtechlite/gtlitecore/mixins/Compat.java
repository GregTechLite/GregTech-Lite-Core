package gregtechlite.gtlitecore.mixins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Compatible attributor for all mixins classes.
 * <p>
 * This annotation marked the mixins class provided compatibility for some mods.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Compat
{

    /**
     * The mod id for the compatible mod of the class.
     *
     * @return The mod id for the compatible mod.
     */
    String[] modId() default "";

}
