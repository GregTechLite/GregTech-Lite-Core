package gregtechlite.gtlitecore.mixins.hooks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a class (maybe final or abstract) is a hook for mixins.
 * <p>
 * Generally, we not recommend to use those classes with this annotation in non mixins
 * situations. This annotation is not like {@link Extension}, this annotation only
 * use for some utility class or hooks which mixin used only.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Hook {}
