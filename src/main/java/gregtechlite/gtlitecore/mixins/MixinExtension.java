package gregtechlite.gtlitecore.mixins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Extension attributor for all mixins classes.
 * <p>
 * This annotation used to mark an interface is an extension for mixins class.
 *
 * @see MixinOnly
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MixinExtension {}
