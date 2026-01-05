package gregtechlite.gtlitecore.mixins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Distribution attributor for all mixins classes.
 * <p>
 * This annotation has some duplicated function with {@link MixinExtension}, but it is only for utility class, hooks or
 * adapter for mixins classes (means this class is only used for mixins classes).
 *
 * @see MixinExtension
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MixinOnly {}
