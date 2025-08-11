package gregtechlite.gtlitecore.api.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Module
{

    /**
     * Id of the module, must be unique within its container (containerId).
     */
    String moduleId();

    /**
     * Id of the container (ModuleContainer) to associate the module with.
     */
    String containerId();

    /**
     * Name of the module, used human-readable name.
     */
    String name();

    /**
     * A list of ModIds that the module depends on. If any mods specified
     * are not present, the module will not load.
     */
    String[] modDependencies() default {};

    /**
     * Whether the module is the core module for its container. Each container
     * must have exactly one core module which will be loaded before all other
     * modules in the container. Core modules should not have mod dependencies
     * because this module used to loaded mod structure from API to mod main.
     */
    boolean isCore() default false;

    /**
     * Author of the module.
     */
    String author() default "";

    /**
     * Version of the module.
     */
    String version() default "";

    /**
     * A description of the module in the module configuration file.
     */
    String descriptions() default "";

}
