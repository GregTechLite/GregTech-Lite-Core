package gregtechlite.gtlitecore.api.advancement

interface AdvancementManager
{

    fun <T : AdvancementCriterion> registerTrigger(id: String, criterion: T): AdvancementTrigger<T>?
}