package magicbook.gtlitecore.core.advancement.trigger;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import magicbook.gtlitecore.api.advancement.IAdvancementCriterion;
import magicbook.gtlitecore.api.advancement.IAdvancementTrigger;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.core.advancement.listener.AdvancementListeners;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@ApiStatus.Internal
public class AdvancementTrigger<T extends IAdvancementCriterion> implements IAdvancementTrigger<T>
{

    private final ResourceLocation id;
    private final T criterion;
    private final Map<PlayerAdvancements, AdvancementListeners<T>> listeners = Maps.newHashMap();

    public AdvancementTrigger(String name, @NotNull T criterion)
    {
        this.id = GTLiteUtility.gtliteId(name);
        this.criterion = criterion;
    }

    @Override
    public void addListener(@NotNull PlayerAdvancements advancementsIn,
                            @NotNull Listener<T> listener)
    {
        AdvancementListeners<T> advancementListener = listeners.get(advancementsIn);
        if (advancementListener == null) {
            advancementListener = new AdvancementListeners<>(advancementsIn);
            listeners.put(advancementsIn, advancementListener);
        }
        advancementListener.add(listener);
    }

    @Override
    public void removeListener(@NotNull PlayerAdvancements advancementsIn,
                               @NotNull Listener<T> listener)
    {
        AdvancementListeners<T> advancementListener = listeners.get(advancementsIn);
        if (advancementListener != null) {
            advancementListener.remove(listener);
            if (advancementListener.isEmpty())
                listeners.remove(advancementsIn);
        }
    }

    @Override
    public void removeAllListeners(@NotNull PlayerAdvancements advancementsIn)
    {
        listeners.remove(advancementsIn);
    }

    @NotNull
    @Override
    public T deserializeInstance(@NotNull JsonObject object,
                                 @NotNull JsonDeserializationContext context)
    {
        return this.criterion;
    }

    @Override
    public void trigger(EntityPlayerMP player)
    {
        AdvancementListeners<T> listener = listeners.get(player.getAdvancements());
        if (listener != null)
            listener.trigger(player);
    }

    @NotNull
    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

}
