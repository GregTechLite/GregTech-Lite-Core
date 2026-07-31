package gregtechlite.gtlitecore.mixins.hooks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a mixins is an adhoc resolve which is implemented from upstream repositories or
 * unrelease contents in our mods, or just referenced some external contents.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Implemented
{
    /**
     * A source for the implemented contents.
     * <p>
     * Here are some allowed source format:
     * <ul>
     *     <li>URL format link, e.g. GitHub Issue/Pull Request link</li>
     *     <li>Reference with correct format, like paper reference format</li>
     *     <li>Blog link with its author name (or id)</li>
     * </ul>
     *
     * @return The extract source for implemented contents.
     */
    String[] at() default "";
}
