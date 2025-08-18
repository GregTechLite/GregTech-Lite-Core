package gregtechlite.gtlitecore.core.advancement;

import gregtechlite.gtlitecore.api.GTLiteAPI;
import gregtechlite.gtlitecore.api.advancement.AdvancementCriterion;
import gregtechlite.gtlitecore.api.advancement.AdvancementManager;
import gregtechlite.gtlitecore.api.advancement.AdvancementTrigger;
import gregtechlite.gtlitecore.api.module.ModuleStage;
import gregtechlite.gtlitecore.core.CoreModule;
import gregtechlite.gtlitecore.core.advancement.trigger.AdvancementTriggerImpl;
import net.minecraft.advancements.CriteriaTriggers;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class AdvancementManagerImpl implements AdvancementManager
{

    private static final AdvancementManagerImpl INSTANCE = new AdvancementManagerImpl();

    private AdvancementManagerImpl() {}

    public static AdvancementManagerImpl getInstance()
    {
        return INSTANCE;
    }

    @Override
    public <T extends AdvancementCriterion> AdvancementTrigger<T> registerTrigger(String id, T criterion)
    {
        if (GTLiteAPI.moduleManager.hasPassedStage(ModuleStage.PRE_INIT))
        {
            CoreModule.logger.error("Could not register Advancement Trigger {}, as trigger registration has ended!", id);
            return null;
        }
        AdvancementTrigger<T> trigger = new AdvancementTriggerImpl<>(id, criterion);
        criterion.setId(trigger.getId());
        CriteriaTriggers.register(trigger);
        return trigger;
    }

}