package gregtechlite.gtlitecore.api.capability

interface PatternedSingletonDualInputProxy : SingletonDualInputProxy
{
    override fun inventories(): Iterator<PatternedSingletonDualInputInventory>
}
