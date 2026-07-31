package gregtechlite.gtlitecore.core.advancement.criterion

import gregtechlite.gtlitecore.api.advancement.impl.AbstractCriterion
import net.minecraft.entity.player.EntityPlayerMP

/**
 * Will always succeed when an `AdvancementTrigger` with this `AdvancementCriterion` is fired.
 */
class BasicCriterion : AbstractCriterion()
{

    override fun test(player: EntityPlayerMP?): Boolean = true
}