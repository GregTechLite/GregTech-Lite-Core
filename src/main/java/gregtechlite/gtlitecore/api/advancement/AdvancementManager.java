package gregtechlite.gtlitecore.api.advancement;

public interface AdvancementManager
{

    <T extends AdvancementCriterion> AdvancementTrigger<T> registerTrigger(String id, T criterion);

}