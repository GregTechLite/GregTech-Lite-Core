package gregtechlite.gtlitecore.core.advancement.listener

import gregtechlite.gtlitecore.api.advancement.AdvancementCriterion
import gregtechlite.gtlitecore.api.collection.openArrayListOf
import gregtechlite.gtlitecore.api.collection.openHashSetOf
import net.minecraft.advancements.ICriterionTrigger
import net.minecraft.advancements.PlayerAdvancements
import net.minecraft.entity.player.EntityPlayerMP

class AdvancementListeners<T : AdvancementCriterion>(private val advancementsIn: PlayerAdvancements)
{
    private val listeners: MutableSet<ICriterionTrigger.Listener<T>> = openHashSetOf()

    fun add(listener: ICriterionTrigger.Listener<T>)
    {
        listeners.add(listener)
    }

    fun remove(listener: ICriterionTrigger.Listener<T>)
    {
        listeners.remove(listener)
    }

    fun isEmpty() = listeners.isEmpty()

    fun trigger(player: EntityPlayerMP?)
    {
        val _listeners: MutableList<ICriterionTrigger.Listener<T>> = openArrayListOf()
        for (listener in listeners)
        {
            if (listener.criterionInstance.test(player))
            {
                _listeners.add(listener)
            }
        }
        if (_listeners.isNotEmpty())
        {
            for (listener in _listeners)
            {
                listener.grantCriterion(advancementsIn)
            }
        }
    }
}