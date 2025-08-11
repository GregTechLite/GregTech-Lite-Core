package gregtechlite.gtlitecore.loader.ore

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import net.minecraftforge.oredict.OreDictionary

internal object AttributePrefixOreDicts
{

    // @formatter:off

    fun init()
    {

        // "Radioactive": consists of all radioactive elements' dusts prefixes.
        for (material in arrayOf(
            Materials.Technetium, Materials.Thorium, Materials.Protactinium, Materials.Uranium, Materials.Uranium235,
            Materials.Uranium238, Materials.Neptunium, Materials.Plutonium239, Materials.Plutonium241,
            GTLiteMaterials.Plutonium244, Materials.Americium, Materials.Curium, Materials.Berkelium,
            Materials.Californium, Materials.Einsteinium, Materials.Fermium, Materials.Mendelevium, Materials.Nobelium,
            Materials.Lawrencium, Materials.Rutherfordium, Materials.Dubnium, Materials.Seaborgium, Materials.Bohrium,
            GTLiteMaterials.MetastableHassium, Materials.Meitnerium, Materials.Darmstadtium, Materials.Roentgenium,
            Materials.Copernicium, Materials.Nihonium, GTLiteMaterials.MetastableFlerovium, Materials.Moscovium,
            Materials.Livermorium, Materials.Tennessine, GTLiteMaterials.MetastableOganesson, Materials.Naquadah,
            Materials.NaquadahEnriched, Materials.Naquadria, GTLiteMaterials.Taranium))
        {
            OreDictionary.registerOre("dustRadioactive", OreDictUnifier.get(OrePrefix.dust, material))
            OreDictionary.registerOre("dustSmallRadioactive", OreDictUnifier.get(OrePrefix.dustSmall, material))
            OreDictionary.registerOre("dustTinyRadioactive", OreDictUnifier.get(OrePrefix.dustTiny, material))
        }

        // "Bacterial": consists of all bacterial dusts, used for biological reactor recipes as default.
        for (material in arrayOf(
            GTLiteMaterials.BrevibacteriumFlavum, GTLiteMaterials.CupriavidusNecator, GTLiteMaterials.StreptococcusPyogenes,
            GTLiteMaterials.EscherichiaColi))
        {
            OreDictionary.registerOre("dustBacterial", OreDictUnifier.get(OrePrefix.dust, material))
        }

    }

    // @formatter:on

}