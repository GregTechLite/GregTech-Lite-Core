package magicbook.gtlitecore.api.module;

import magicbook.gtlitecore.api.utils.GTLiteUtility;
import net.minecraft.util.ResourceLocation;

public interface IModuleManager
{

    boolean isModuleEnabled(ResourceLocation namespace);

    default boolean isModuleEnabled(String moduleId)
    {
        return this.isModuleEnabled(GTLiteUtility.gtliteId(moduleId));
    }

    default boolean isModuleEnabled(String containerId, String moduleId)
    {
        return this.isModuleEnabled(GTLiteUtility.getId(containerId, moduleId));
    }

    void registerContainer(IModuleContainer container);

    IModuleContainer getLoadedContainer();

    ModuleStage getStage();

    boolean hasPassedStage(ModuleStage stage);

}
