package gregtechlite.gtlitecore.loader.ore

import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraftforge.oredict.OreDictionary

@Suppress("Deprecation")
internal object MiscOreDicts
{

    // @formatter:off

    fun init()
    {
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[0].getStateFromMeta(0).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[0].getStateFromMeta(2).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[0].getStateFromMeta(4).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[0].getStateFromMeta(6).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[0].getStateFromMeta(8).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[0].getStateFromMeta(10).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[0].getStateFromMeta(12).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[0].getStateFromMeta(14).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[1].getStateFromMeta(0).block)
        OreDictionary.registerOre("treeSapling", GTLiteBlocks.SAPLINGS[1].getStateFromMeta(2).block)
    }

    // @formatter:on

}