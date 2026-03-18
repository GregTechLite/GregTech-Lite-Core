package gregtechlite.gtlitecore.loader.ore

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.ore.OrePrefix
import gregtechlite.gtlitecore.api.unification.material.info.MaterialInfoCache
import net.minecraftforge.oredict.OreDictionary

internal object AttributePrefixOreDicts
{

    // @formatter:off

    fun init()
    {
        for (material in MaterialInfoCache.radioactiveMaterials)
        {
            OreDictionary.registerOre("dustRadioactive", OreDictUnifier.get(OrePrefix.dust, material))
            OreDictionary.registerOre("dustSmallRadioactive", OreDictUnifier.get(OrePrefix.dustSmall, material))
            OreDictionary.registerOre("dustTinyRadioactive", OreDictUnifier.get(OrePrefix.dustTiny, material))
        }

        // "Bacterial": consists of all bacterial dusts, used for biological reactor recipes as default.
        for (material in MaterialInfoCache.bacterialMaterials)
        {
            OreDictionary.registerOre("dustBacterial", OreDictUnifier.get(OrePrefix.dust, material))
        }

    }

    // @formatter:on

}