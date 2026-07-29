package gregtechlite.gtlitecore.api.module;

import gregtechlite.gtlitecore.GTLiteMod;
import net.minecraft.util.ResourceLocation;

public interface ModuleManager
{
    /**
     * Checks if the module with a specific {@code namespace} is enabled.
     *
     * @param namespace The namespace of the module, like <tt>containerId:moduleId</tt>.
     * @return          If the module is enabled, then returns {@code true},
     *                  otherwise returns {@code false}.
     */
    boolean isModuleEnabled(ResourceLocation namespace);

    /**
     * Checks if the module with a specific {@code moduleId} with default {@code containerId} is enabled.
     *
     * @param moduleId The id of the module in <tt>gtlitecore</tt> namespace.
     * @return         If the module is enabled, then returns {@code true},
     *                 otherwise returns {@code false}.
     */
    default boolean isModuleEnabled(String moduleId)
    {
        return isModuleEnabled(GTLiteMod.id(moduleId));
    }

    /**
     * Checks if the module with specific {@code containerId} and {@code moduleId} is enabled.
     *
     * @param containerId The container id of the module.
     * @param moduleId    The module id of the module.
     * @return            If the module is enabled, then returns {@code true},
     *                    otherwise returns {@code false}.
     */
    default boolean isModuleEnabled(String containerId, String moduleId)
    {
        return isModuleEnabled(new ResourceLocation(containerId, moduleId));
    }

    /**
     * Registers a {@link CustomModuleContainer} for {@link ModuleManager} searching.
     *
     * @param container An existed module container, or make a "mod" be a module container
     *                  in {@link ModuleManager} loading.
     */
    void registerContainer(CustomModuleContainer container);

    /**
     * Gets current loading module container.
     *
     * @return The loaded module container from {@link ModuleManager}.
     */
    CustomModuleContainer getLoadedContainer();

    /**
     * Gets current module loading stage.
     *
     * @return The current stage from {@link ModuleManager}.
     */
    ModuleStage getStage();

    /**
     * If current stage is later than {@code stage}, then it is passed.
     * <p>
     * For example, if a stage in {@link ModuleStage#C_SETUP} and now we are already in
     * next stage, i.e. {@link ModuleStage#M_SETUP}, then this stage is passed.
     *
     * @param stage The stage which will be compared with current stage.
     * @return      If the stage is later than current stage, then returns {@code true},
     *              otherwise returns {@code false}.
     */
    boolean hasPassedStage(ModuleStage stage);
}
