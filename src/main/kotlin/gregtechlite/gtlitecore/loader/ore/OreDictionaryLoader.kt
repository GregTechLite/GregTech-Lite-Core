package gregtechlite.gtlitecore.loader.ore

internal object OreDictionaryLoader
{

    // @formatter:off

    fun init()
    {
        DyeOreDicts.init()
        TieredGlassOreDicts.init()
        UnifiedRubberOreDicts.init()
        AttributePrefixOreDicts.init()
        MiscOreDicts.init()
    }

    // @formatter:on

}