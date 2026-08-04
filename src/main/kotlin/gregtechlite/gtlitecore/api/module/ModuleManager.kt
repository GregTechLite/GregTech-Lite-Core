package gregtechlite.gtlitecore.api.module

import gregtechlite.gtlitecore.GTLiteMod
import net.minecraft.util.ResourceLocation

interface ModuleManager
{
    /**
     * Checks if the module with a specific `namespace` is enabled.
     *
     * @param namespace The namespace of the module, like `containerId:moduleId`.
     * @return          If the module is enabled, then returns `true`,
     *                  otherwise returns `false`.
     */
    fun isModuleEnabled(namespace: ResourceLocation): Boolean

    /**
     * Checks if the module with a specific `moduleId` with default `containerId` is enabled.
     *
     * @param moduleId The id of the module in `gtlitecore` namespace.
     * @return         If the module is enabled, then returns `true`,
     *                 otherwise returns `false`.
     */
    fun isModuleEnabled(moduleId: String): Boolean = isModuleEnabled(GTLiteMod.id(moduleId))


    /**
     * Checks if the module with specific `containerId` and `moduleId` is enabled.
     *
     * @param containerId The container id of the module.
     * @param moduleId    The module id of the module.
     * @return            If the module is enabled, then returns `true`,
     *                    otherwise returns `false`.
     */
    fun isModuleEnabled(containerId: String, moduleId: String): Boolean
        = isModuleEnabled(ResourceLocation(containerId, moduleId))

    /**
     * Registers a [CustomModuleContainer] for [ModuleManager] searching.
     *
     * @param container An existed module container, or make a "mod" be a module container
     *                  in `ModuleManager` loading.
     */
    fun registerContainer(container: CustomModuleContainer)

    /**
     * Gets current loading module container.
     *
     * @return The loaded module container from [ModuleManager].
     */
    fun getLoadedContainer(): CustomModuleContainer

    /**
     * Gets current module loading stage.
     *
     * @return The current stage from [ModuleManager].
     */
    fun getStage(): ModuleStage

    /**
     * If current stage is later than `stage`, then it is passed.
     * <p>
     * For example, if a stage in [ModuleStage.C_SETUP] and now we are already in
     * next stage, i.e. [ModuleStage.M_SETUP], then this stage is passed.
     *
     * @param stage The stage which will be compared with current stage.
     * @return      If the stage is later than current stage, then returns `true`,
     *              otherwise returns `false`.
     */
    fun hasPassedStage(stage: ModuleStage): Boolean
}
