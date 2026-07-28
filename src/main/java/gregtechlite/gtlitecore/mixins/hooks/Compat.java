package gregtechlite.gtlitecore.mixins.hooks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a mixins is used to build compatibility with some mods.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Compat
{
    /**
     * All compatible mods by this mixins doing.
     * <p>
     * For notation convention, if you want to identify specific version of mod,
     * please use a format like <tt>"modid:version"</tt>.
     *
     * @return The mod id for the compatible mod.
     */
    String[] modId() default "";
}
