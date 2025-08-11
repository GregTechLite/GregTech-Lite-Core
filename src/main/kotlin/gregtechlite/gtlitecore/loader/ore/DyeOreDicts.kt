package gregtechlite.gtlitecore.loader.ore

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Color
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials

internal object DyeOreDicts
{

    // @formatter:off

    fun init()
    {
        // White
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.LeadNitrate), OrePrefix.dye, Color.White)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.DiaminostilbenedisulfonicAcid), OrePrefix.dye, Color.White)

        // Orange
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.BrownLimonite), OrePrefix.dye, Color.Orange)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.YellowLimonite), OrePrefix.dye, Color.Orange)
        // TODO Diketopyrrolopyrrole

        // Magenta
        // TODO

        // Light Blue
        // TODO

        // Yellow
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.Massicot), OrePrefix.dye, Color.Yellow)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.LeadChromate), OrePrefix.dye, Color.Yellow)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.Orpiment), OrePrefix.dye, Color.Yellow)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.Fluorescein), OrePrefix.dye, Color.Yellow)

        // Lime
        // TODO

        // Pink
        // TODO

        // Gray
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.Pyrolusite), OrePrefix.dye, Color.Gray)

        // Light Gray
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.Barite), OrePrefix.dye, Color.LightGray)

        // Cyan
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.CyanIndigo), OrePrefix.dye, Color.Cyan)

        // Purple
        // TODO Mauveine

        // Blue
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.CobaltAluminate), OrePrefix.dye, Color.Blue)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.Azurite), OrePrefix.dye, Color.Blue)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.PrussianBlue), OrePrefix.dye, Color.Blue)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.Indigo), OrePrefix.dye, Color.Blue)
        // TODO Indanthrone Blue

        // Brown
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.BandedIron), OrePrefix.dye, Color.Brown)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.Sienna), OrePrefix.dye, Color.Brown)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.DirectBrown77), OrePrefix.dye, Color.Brown)

        // Green
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.Malachite), OrePrefix.dye, Color.Green)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.ScheelesGreen), OrePrefix.dye, Color.Green)

        // Red
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.Cinnabar), OrePrefix.dye, Color.Red)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.Realgar), OrePrefix.dye, Color.Red)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.BurntSienna), OrePrefix.dye, Color.Red)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.Erythrosine), OrePrefix.dye, Color.Red)

        // Black
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon), OrePrefix.dye, Color.Black)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, Materials.Graphite), OrePrefix.dye, Color.Black)
        OreDictUnifier.registerOre(OreDictUnifier.get(OrePrefix.dust, GTLiteMaterials.Nigrosin), OrePrefix.dye, Color.Black)
    }

    // @formatter:on

}