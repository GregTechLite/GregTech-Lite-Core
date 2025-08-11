package gregtechlite.gtlitecore.loader.ore

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import net.minecraftforge.oredict.OreDictionary

internal object UnifiedRubberOreDicts
{

    // @formatter:off

    fun init()
    {
        // Basic
        for (rubber in arrayOf(
            // #1
            Materials.Rubber,
            // #2
            Materials.StyreneButadieneRubber,
            Materials.SiliconeRubber,
            // #3
            GTLiteMaterials.PolyphosphonitrileFluoroRubber,
            GTLiteMaterials.PolytetramethyleneGlycolRubber))
        {
            OreDictionary.registerOre("plateAnyRubber", OreDictUnifier.get(OrePrefix.plate, rubber))
            OreDictionary.registerOre("ringAnyRubber", OreDictUnifier.get(OrePrefix.ring, rubber))
            OreDictionary.registerOre("foilAnyRubber", OreDictUnifier.get(OrePrefix.foil, rubber))
        }

        // Synthetic
        for (rubber in arrayOf(
            // #2
            Materials.StyreneButadieneRubber,
            Materials.SiliconeRubber,
            // #3
            GTLiteMaterials.PolyphosphonitrileFluoroRubber,
            GTLiteMaterials.PolytetramethyleneGlycolRubber))
        {
            OreDictionary.registerOre("plateAnySyntheticRubber", OreDictUnifier.get(OrePrefix.plate, rubber))
            OreDictionary.registerOre("ringAnySyntheticRubber", OreDictUnifier.get(OrePrefix.ring, rubber))
            OreDictionary.registerOre("foilAnySyntheticRubber", OreDictUnifier.get(OrePrefix.foil, rubber))
        }

        // Advanced Synthetic
        for (rubber in arrayOf(
            // #3
            GTLiteMaterials.PolyphosphonitrileFluoroRubber,
            GTLiteMaterials.PolytetramethyleneGlycolRubber))
        {
            OreDictionary.registerOre("plateAnyAdvancedSyntheticRubber", OreDictUnifier.get(OrePrefix.plate, rubber))
            OreDictionary.registerOre("ringAnyAdvancedSyntheticRubber", OreDictUnifier.get(OrePrefix.ring, rubber))
            OreDictionary.registerOre("foilAnyAdvancedSyntheticRubber", OreDictUnifier.get(OrePrefix.foil, rubber))
        }

    }

    // @formatter:on

}