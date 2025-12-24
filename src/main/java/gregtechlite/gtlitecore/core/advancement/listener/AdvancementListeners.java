package gregtechlite.gtlitecore.core.advancement.listener;

import com.morphismmc.morphismlib.collection.ListOps;
import com.morphismmc.morphismlib.collection.SetOps;
import gregtechlite.gtlitecore.api.advancement.AdvancementCriterion;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import org.jetbrains.annotations.ApiStatus.Internal;

import java.util.List;
import java.util.Set;

@Internal
public class AdvancementListeners<T extends AdvancementCriterion>
{

    private final PlayerAdvancements advancements;
    private final Set<ICriterionTrigger.Listener<T>> listeners = SetOps.of();

    public AdvancementListeners(PlayerAdvancements advancementsIn)
    {
        this.advancements = advancementsIn;
    }

    public void add(ICriterionTrigger.Listener<T> listener)
    {
        listeners.add(listener);
    }

    public void remove(ICriterionTrigger.Listener<T> listener)
    {
        listeners.remove(listener);
    }

    public boolean isEmpty()
    {
        return listeners.isEmpty();
    }

    public void trigger(EntityPlayerMP player)
    {
        List<ICriterionTrigger.Listener<T>> triggerListeners = ListOps.ofMutable();
        for (ICriterionTrigger.Listener<T> listener : listeners)
        {
            if (listener.getCriterionInstance().test(player))
            {
                triggerListeners.add(listener);
            }
        }
        if (!triggerListeners.isEmpty())
        {
            for (ICriterionTrigger.Listener<T> listener : triggerListeners)
            {
                listener.grantCriterion(advancements);
            }
        }
    }

}