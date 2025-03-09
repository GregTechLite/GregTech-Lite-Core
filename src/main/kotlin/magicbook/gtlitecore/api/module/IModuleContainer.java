package magicbook.gtlitecore.api.module;

public interface IModuleContainer
{

    /**
     * Return container Id of the module, if this is a mod-only container, then should
     * use corresponding modId to prevent collisions.
     */
    String getId();

}
