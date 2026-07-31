package gregtechlite.gtlitecore.api.advancement

import net.minecraft.advancements.ICriterionTrigger
import net.minecraft.entity.player.EntityPlayerMP

interface AdvancementTrigger<T : AdvancementCriterion> : ICriterionTrigger<T>
{

    fun trigger(player: EntityPlayerMP?)
}