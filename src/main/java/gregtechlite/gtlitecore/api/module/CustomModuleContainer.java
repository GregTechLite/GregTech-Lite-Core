package gregtechlite.gtlitecore.api.module;

public interface CustomModuleContainer
{

    /**
     * Return {@code containerId} of the module, if this is a mod-only container, then should
     * use corresponding {@code modId} to prevent collisions.
     */
    String getId();

}
