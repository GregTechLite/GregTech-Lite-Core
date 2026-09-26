package gregtechlite.gtlitecore.loader.ore

import gregtechlite.gtlitecore.api.extension.toItem
import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeRegistry
import net.minecraftforge.oredict.OreDictionary

internal object MiscOreDicts
{
    // @formatter:off

    fun init()
    {
        WorldGeneratorTreeRegistry.forEach { OreDictionary.registerOre("treeSapling", it.saplingState.toItem()) }
    }

    // @formatter:on
}