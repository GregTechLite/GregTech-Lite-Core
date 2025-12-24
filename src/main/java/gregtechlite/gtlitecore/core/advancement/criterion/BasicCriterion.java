package gregtechlite.gtlitecore.core.advancement.criterion;

import gregtechlite.gtlitecore.api.advancement.impl.AbstractCriterion;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Will always succeed when an {@code AdvancementTrigger} with this {@code AdvancementCriterion} is fired.
 */
public class BasicCriterion extends AbstractCriterion
{

    @Override
    public boolean test(EntityPlayerMP player)
    {
        return true;
    }

}
