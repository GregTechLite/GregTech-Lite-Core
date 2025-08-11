package gregtechlite.gtlitecore.api.block.attribute

interface StateTier
{

    companion object
    {
        val COMPARATOR: Comparator<StateTier> = Comparator.comparingInt(StateTier::tier)
    }

    val tier: Int

}