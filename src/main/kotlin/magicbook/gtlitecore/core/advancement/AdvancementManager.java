package magicbook.gtlitecore.core.advancement;

import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.advancement.IAdvancementCriterion;
import magicbook.gtlitecore.api.advancement.IAdvancementManager;
import magicbook.gtlitecore.api.advancement.IAdvancementTrigger;
import magicbook.gtlitecore.api.module.ModuleStage;
import magicbook.gtlitecore.core.CoreModule;
import magicbook.gtlitecore.core.advancement.trigger.AdvancementTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
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