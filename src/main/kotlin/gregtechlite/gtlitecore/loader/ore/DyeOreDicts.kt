package gregtechlite.gtlitecore.loader.ore

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Color
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Barite
import gregtech.api.unification.material.Materials.BrownLimonite
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cinnabar
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.YellowLimonite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dye
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azurite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BurntSienna
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CobaltAluminate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CyanIndigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiaminostilbenedisulfonicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diketopyrrolopyrrole
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DirectBrown77
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EosinY
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Erythrosine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorescein
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Indigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadChromate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ManganeseBlue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mauveine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nigrosin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Orpiment
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PigmentRed
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrussianBlue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScheelesGreen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sienna

internal object DyeOreDicts
{

    // @formatter:off

    fun init()
    {
        // White
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, LeadNitrate),
                                   dye, Color.White)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, DiaminostilbenedisulfonicAcid),
                                   dye, Color.White)

        // Orange
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, BrownLimonite),
                                   dye, Color.Orange)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, YellowLimonite),
                                   dye, Color.Orange)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Diketopyrrolopyrrole),
                                   dye, Color.Orange)

        // Magenta
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, PigmentRed),
                                   dye, Color.Magenta)

        // Light Blue
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, ManganeseBlue),
                                   dye, Color.LightBlue)

        // Yellow
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Massicot),
                                   dye, Color.Yellow)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, LeadChromate),
                                   dye, Color.Yellow)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Orpiment),
                                   dye, Color.Yellow)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Fluorescein),
                                   dye, Color.Yellow)

        // Lime
        // TODO

        // Pink
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, EosinY),
                                   dye, Color.Pink)

        // Gray
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Pyrolusite),
                                   dye, Color.Gray)

        // Light Gray
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Barite),
                                   dye, Color.LightGray)

        // Cyan
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, CyanIndigo),
                                   dye, Color.Cyan)

        // Purple
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Mauveine),
                                   dye, Color.Purple)

        // Blue
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, CobaltAluminate),
                                   dye, Color.Blue)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Azurite),
                                   dye, Color.Blue)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, PrussianBlue),
                                   dye, Color.Blue)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Indigo),
                                   dye, Color.Blue)
        // TODO Indanthrone Blue

        // Brown
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, BandedIron),
                                   dye, Color.Brown)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Sienna),
                                   dye, Color.Brown)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, DirectBrown77),
                                   dye, Color.Brown)

        // Green
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Malachite),
                                   dye, Color.Green)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, ScheelesGreen),
                                   dye, Color.Green)

        // Red
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Cinnabar),
                                   dye, Color.Red)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Realgar),
                                   dye, Color.Red)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, BurntSienna),
                                   dye, Color.Red)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Erythrosine),
                                   dye, Color.Red)

        // Black
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Carbon),
                                   dye, Color.Black)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Graphite),
                                   dye, Color.Black)
        OreDictUnifier.registerOre(OreDictUnifier.get(dust, Nigrosin),
                                   dye, Color.Black)
    }

    // @formatter:on

}