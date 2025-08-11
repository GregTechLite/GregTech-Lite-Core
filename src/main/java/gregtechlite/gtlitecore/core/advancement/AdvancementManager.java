package gregtechlite.gtlitecore.core.advancement;

import gregtechlite.gtlitecore.api.GTLiteAPI;
import gregtechlite.gtlitecore.api.advancement.IAdvancementCriterion;
import gregtechlite.gtlitecore.api.advancement.IAdvancementManager;
import gregtechlite.gtlitecore.api.advancement.IAdvancementTrigger;
import gregtechlite.gtlitecore.api.module.ModuleStage;
import gregtechlite.gtlitecore.core.CoreModule;
import gregtechlite.gtlitecore.core.advancement.trigger.AdvancementTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class AdvancementManager implements IAdvancementManager
{

    private static final AdvancementManager INSTANCE = new AdvancementManager();

    private AdvancementManager() {}

    public static AdvancementManager getInstance()
    {
        return INSTANCE;
    }

    @Override
    public <T extends IAdvancementCriterion> IAdvancementTrigger<T> registerTrigger(String id, T criterion)
    {
        if (GTLiteAPI.moduleManager.hasPassedStage(ModuleStage.PRE_INIT))
        {
            CoreModule.logger.error("Could not register Advancement Trigger {}, as trigger registration has ended!", id);
            return null;
        }
        IAdvancementTrigger<T> trigger = new AdvancementTrigger<>(id, criterion);
        criterion.setId(trigger.getId());
        CriteriaTriggers.register(trigger);
        return trigger;
    }

}