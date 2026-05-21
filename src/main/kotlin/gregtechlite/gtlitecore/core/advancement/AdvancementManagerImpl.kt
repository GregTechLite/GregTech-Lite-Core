package gregtechlite.gtlitecore.core.advancement

import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.advancement.AdvancementCriterion
import gregtechlite.gtlitecore.api.advancement.AdvancementManager
import gregtechlite.gtlitecore.api.advancement.AdvancementTrigger
import gregtechlite.gtlitecore.api.module.ModuleStage
import gregtechlite.gtlitecore.core.CoreModule
import gregtechlite.gtlitecore.core.advancement.trigger.AdvancementTriggerImpl
import net.minecraft.advancements.CriteriaTriggers

class AdvancementManagerImpl private constructor() : AdvancementManager
{
    companion object
    {
        private val instance = AdvancementManagerImpl()

        fun getInstance(): AdvancementManagerImpl = instance
    }

    override fun <T : AdvancementCriterion> registerTrigger(id: String, criterion: T): AdvancementTrigger<T>?
    {
        if (GTLiteAPI.moduleManager.hasPassedStage(ModuleStage.PRE_INIT))
        {
            CoreModule.logger.error("Could not register AdvancementTrigger '$id' as trigger registration has ended!")
            return null
        }
        val trigger = AdvancementTriggerImpl(id, criterion)
        criterion.id = trigger.id
        CriteriaTriggers.register(trigger)
        return trigger
    }
}