package gregtechlite.gtlitecore.api.module

interface CustomModuleContainer
{
    /**
     * The container id of the module, if this is a mod only container,
     * then should use corresponding mod id to prevent collisions.
     */
    val id: String
}
