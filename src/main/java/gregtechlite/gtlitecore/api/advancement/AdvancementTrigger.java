package gregtechlite.gtlitecore.api.advancement;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.entity.player.EntityPlayerMP;

public interface AdvancementTrigger<T extends AdvancementCriterion> extends ICriterionTrigger<T>
{

    void trigger(EntityPlayerMP player);

}