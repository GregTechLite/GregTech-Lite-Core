package gregtechlite.gtlitecore.api.advancement

import net.minecraft.advancements.ICriterionInstance
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.ResourceLocation

interface AdvancementCriterion : ICriterionInstance
{

    fun test(player: EntityPlayerMP?): Boolean

    fun setId(id: ResourceLocation)
}