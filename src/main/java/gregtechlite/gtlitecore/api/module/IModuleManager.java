package gregtechlite.gtlitecore.api.module;

import gregtechlite.gtlitecore.GTLiteMod;
import net.minecraft.util.ResourceLocation;

public interface IModuleManager
{

    boolean isModuleEnabled(ResourceLocation namespace);

    default boolean isModuleEnabled(String moduleId)
    {
        return this.isModuleEnabled(GTLiteMod.id(moduleId));
    }

    default boolean isModuleEnabled(String containerId, String moduleId)
    {
        return this.isModuleEnabled(new ResourceLocation(containerId, moduleId));
    }

    void registerContainer(IModuleContainer container);

    IModuleContainer getLoadedContainer();

    ModuleStage getStage();

    boolean hasPassedStage(ModuleStage stage);

}
