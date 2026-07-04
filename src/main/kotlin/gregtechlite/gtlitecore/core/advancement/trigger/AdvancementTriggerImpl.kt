package gregtechlite.gtlitecore.core.advancement.trigger

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.advancement.AdvancementCriterion
import gregtechlite.gtlitecore.api.advancement.AdvancementTrigger
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.core.advancement.listener.AdvancementListeners
import net.minecraft.advancements.ICriterionTrigger
import net.minecraft.advancements.PlayerAdvancements
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.ResourceLocation

class AdvancementTriggerImpl<T : AdvancementCriterion>(private val id: ResourceLocation,
                                                       private val criterion: T) : AdvancementTrigger<T>
{
    private val listeners: MutableMap<PlayerAdvancements, AdvancementListeners<T>> = openHashMapOf()

    constructor(name: String, criterion: T) : this(GTLiteMod.id(name), criterion)

    override fun addListener(advancementsIn: PlayerAdvancements, listener: ICriterionTrigger.Listener<T>)
    {
        var _listener = listeners[advancementsIn]
        if (_listener == null)
        {
            _listener = AdvancementListeners(advancementsIn)
            listeners[advancementsIn] = _listener
        }
        _listener.add(listener)
    }

    override fun removeListener(advancementsIn: PlayerAdvancements, listener: ICriterionTrigger.Listener<T>)
    {
        val _listener = listeners[advancementsIn]
        _listener?.let {
            _listener.remove(listener)
            if (_listener.isEmpty())
            {
                listeners.remove(advancementsIn)
            }
        }
    }

    override fun removeAllListeners(advancementsIn: PlayerAdvancements)
    {
        listeners.remove(advancementsIn)
    }

    override fun deserializeInstance(json: JsonObject, context: JsonDeserializationContext): T = criterion

    override fun trigger(player: EntityPlayerMP?)
    {
        val listener = listeners[player?.advancements]
        listener?.let { listener.trigger(player) }
    }

    override fun getId(): ResourceLocation = id
}