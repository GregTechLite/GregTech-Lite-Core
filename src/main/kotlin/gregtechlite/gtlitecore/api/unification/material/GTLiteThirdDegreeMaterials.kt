package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.fluids.FluidBuilder
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Barite
import gregtech.api.unification.material.Materials.Blaze
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Electrotine
import gregtech.api.unification.material.Materials.Flint
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Obsidian
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Stone
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtechlite.gtlitecore.api.extension.gas
import gregtechlite.gtlitecore.api.extension.liquid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Albite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Augite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azurite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumManganate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlazingPyrotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlueSchist
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BurntSienna
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Clinochlore
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dolomite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ferrosilite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Forsterite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GelidCryotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenSchist
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kimberlite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Komatiite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Limestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lizardite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ManganeseBlue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ManganeseMonoxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Muscovite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shale
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sienna
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Slate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalSodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalSteam
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedSodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedSteam
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tanzanite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TectonicPetrotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZephyreanAerotheum
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.AEROTHEUM
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.CRYOTHEUM
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.PETROTHEUM
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.PYROTHEUM
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.SUPERCRITICAL

object GTLiteThirdDegreeMaterials
{

    // @formatter:off

    fun init()
    {

        // 6001 Limestone
        // Add Centrifuging recipe at CentrifugeRecipes.
        Limestone = addMaterial(6001, "limestone")
        {
            dust()
            color(0xA9A9A9).iconSet(ROUGH)
            components(Calcite, 4, Dolomite, 1, Quicklime, 1)
            flags(NO_SMASHING, DISABLE_DECOMPOSITION)
        }

        // 6002 Komatiite
        // Add Centrifuging recipe at CentrifugeRecipes.
        Komatiite = addMaterial(6002, "komatiite")
        {
            dust()
            color(0xBCB073).iconSet(ROUGH)
            components(Olivine, 2, Magnesite, 1, Flint, 1, DarkAsh, 1)
            flags(NO_SMASHING, DISABLE_DECOMPOSITION)
        }

        // 6003 Green Schist
        // Add Centrifuging recipe at CentrifugeRecipes.
        GreenSchist = addMaterial(6003, "green_schist")
        {
            dust()
            color(0x56AE65).iconSet(ROUGH)
            components(Tanzanite, 2, Quartzite, 2, Talc, 1, Calcite, 1)
            flags(NO_SMASHING, DISABLE_DECOMPOSITION)
        }

        // 6004 Blue Schist
        // Add Centrifuging recipe at CentrifugeRecipes.
        BlueSchist = addMaterial(6004, "blue_schist")
        {
            dust()
            color(0x537FAC).iconSet(ROUGH)
            components(Azurite, 3, Sodalite, 2, Iron, 1)
            flags(NO_SMASHING, DISABLE_DECOMPOSITION)
        }

        // 6005 Kimberlite
        // Add Centrifuging recipe at CentrifugeRecipes.
        Kimberlite = addMaterial(6005, "kimberlite")
        {
            dust()
            color(0x201313).iconSet(ROUGH)
            components(Forsterite, 3, Augite, 3, Andradite, 2, Lizardite, 1)
            flags(NO_SMASHING, DISABLE_DECOMPOSITION)
        }

        // 6006 Slate
        // Add Centrifuging recipe at CentrifugeRecipes.
        Slate = addMaterial(6006, "slate")
        {
            dust()
            color(0x756869).iconSet(ROUGH)
            components(SiliconDioxide, 5, Muscovite, 2, Clinochlore, 2, Albite, 1)
            flags(NO_SMASHING, DISABLE_DECOMPOSITION)
        }

        // 6007 Shale
        // Add Centrifuging recipe at CentrifugeRecipes.
        Shale = addMaterial(6007, "shale")
        {
            dust()
            color(0x3F2E2F).iconSet(ROUGH)
            components(Calcite, 6, Clay, 2, SiliconDioxide, 1, Fluorite, 1)
            flags(NO_SMASHING, DISABLE_DECOMPOSITION)
        }

        // 6008-6010 is for stone types addition in the future.
        // ...

        // 6011 Blazing Pyrotheum
        BlazingPyrotheum = addMaterial(6011, "blazing_pyrotheum")
        {
            dust()
            liquid()
            {
                translation("gregtech.fluid.generic")
                temperature(4000)
                customFlow()
                customStill()
            }
            iconSet(PYROTHEUM)
            components(Blaze, 2, Redstone, 1, Sulfur, 1)
            flags(DECOMPOSITION_BY_CENTRIFUGING)
        }

        // 6012 Gelid Cryotheum
        GelidCryotheum = addMaterial(6012, "gelid_cryotheum")
        {
            dust()
            liquid()
            {
                translation("gregtech.fluid.generic")
                temperature(2)
                customFlow()
                customStill()
            }
            iconSet(CRYOTHEUM)
            components(Ice, 2, Electrotine, 1, Water, 1)
            flags(DECOMPOSITION_BY_ELECTROLYZING)
        }

        // 6013 Tectonic Petrotheum
        TectonicPetrotheum = addMaterial(6013, "tectonic_petrotheum")
        {
            dust()
            liquid()
            {
                translation("gregtech.fluid.generic")
                temperature(350)
                customFlow()
                customFlow()
            }
            iconSet(PETROTHEUM)
            components(Clay, 2, Obsidian, 1, Stone, 1)
            flags(DECOMPOSITION_BY_CENTRIFUGING)
        }

        // 6014 Zephyrean Aerotheum
        ZephyreanAerotheum = addMaterial(6014, "zephyrean_aerotheum")
        {
            dust()
            liquid()
            {
                translation("gregtech.fluid.generic")
                temperature(600)
                customFlow()
                customStill()
            }
            iconSet(AEROTHEUM)
            components(SiliconDioxide, 2, Saltpeter, 1, Air, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6015 Sienna
        Sienna = addMaterial(6015, "sienna")
        {
            dust()
            color(0x4A3724)
            components(ManganeseMonoxide, 1, BandedIron, 1)
            flags(DECOMPOSITION_BY_CENTRIFUGING)
        }

        // 6016 Burnt Sienna
        BurntSienna = addMaterial(6016, "burnt_sienna")
        {
            dust()
            color(0x662E2E)
            components(ManganeseMonoxide, 1, BandedIron, 1)
            flags(DECOMPOSITION_BY_CENTRIFUGING)
        }

        // 6017 Manganese Blue
        ManganeseBlue = addMaterial(6017, "manganese_blue")
        {
            dust()
            color(0x80ABC5).iconSet(ROUGH)
            components(Barite, 1, BariumManganate, 1)
            flags(DECOMPOSITION_BY_CENTRIFUGING)
        }

        // 6018-6025 for some misc materials in the future.
        // ...

        // 6026 Superheated Steam
        SuperheatedSteam = addMaterial(6026, "superheated_steam")
        {
            gas()
            {
                temperature(573)
            }
            color(0xC4C4C4).iconSet(DULL)
            components(Water, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6027 Superheated Eutatic Sodium Potassium
        SuperheatedSodiumPotassiumEutatic = addMaterial(6027, "superheated_sodium_potassium_eutatic")
        {
            liquid()
            {
                temperature(758)
            }
            color(SodiumPotassiumEutatic.materialRGB).iconSet(DULL)
            components(SodiumPotassiumEutatic, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6028 Superheated Eutatic Lead Bismuth
        SuperheatedLeadBismuthEutatic = addMaterial(6028, "superheated_lead_bismuth_eutatic")
        {
            liquid(FluidBuilder().temperature(1643))
            color(LeadBismuthEutatic.materialRGB).iconSet(DULL)
            components(LeadBismuthEutatic, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6029 Superheated Lithium Sodium Potassium Fluorides
        SuperheatedLithiumSodiumPotassiumFluorides = addMaterial(6029, "superheated_lithium_sodium_potassium_fluorides")
        {
            liquid()
            {
                temperature(1543)
            }
            color(LithiumSodiumPotassiumFluorides.materialRGB).iconSet(DULL)
            components(LithiumSodiumPotassiumFluorides, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6030 Superheated Lithium Beryllium Fluorides
        SuperheatedLithiumBerylliumFluorides = addMaterial(6030, "superheated_lithium_beryllium_fluorides")
        {
            liquid()
            {
                temperature(1403)
            }
            color(LithiumBerylliumFluorides.materialRGB).iconSet(DULL)
            components(LithiumBerylliumFluorides, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6031 Supercritical Steam
        SupercriticalSteam = addMaterial(6031, "supercritical_steam")
        {
            gas()
            {
                temperature(873)
            }
            color(0xC4C4C4).iconSet(SUPERCRITICAL)
            components(Water, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6032 Supercritical Eutatic Sodium Potassium
        SupercriticalSodiumPotassiumEutatic = addMaterial(6032, "supercritical_sodium_potassium_eutatic")
        {
            liquid()
            {
                temperature(1058)
            }
            color(SodiumPotassiumEutatic.materialRGB).iconSet(SUPERCRITICAL)
            components(SodiumPotassiumEutatic, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6033 Supercritical Eutatic Lead Bismuth
        SupercriticalLeadBismuthEutatic = addMaterial(6033, "supercritical_lead_bismuth_eutatic")
        {
            liquid()
            {
                temperature(1943)
            }
            color(LeadBismuthEutatic.materialRGB).iconSet(SUPERCRITICAL)
            components(LeadBismuthEutatic, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6034 Supercritical Lithium Sodium Potassium Fluorides
        SupercriticalLithiumSodiumPotassiumFluorides = addMaterial(6034, "supercritical_lithium_sodium_potassium_fluorides")
        {
            liquid()
            {
                temperature(1843)
            }
            color(LithiumSodiumPotassiumFluorides.materialRGB).iconSet(SUPERCRITICAL)
            components(LithiumSodiumPotassiumFluorides, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 6035 Supercritical Lithium Beryllium Fluorides
        SupercriticalLithiumBerylliumFluorides = addMaterial(6035, "supercritical_lithium_beryllium_fluorides")
        {
            liquid()
            {
                temperature(1703)
            }
            color(LithiumBerylliumFluorides.materialRGB).iconSet(SUPERCRITICAL)
            components(LithiumBerylliumFluorides, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // ...

        // 6051 Ferrosilite
        // Because GTCEu remove this material in 2.9.x version, nop...
        Ferrosilite = addMaterial(6051, "ferrosilite")
        {
            dust(1)
            ore()
            color(0x97632A)
            components(Iron, 1, Silicon, 1, Oxygen, 3)
        }

    }

    // @formatter:on

}