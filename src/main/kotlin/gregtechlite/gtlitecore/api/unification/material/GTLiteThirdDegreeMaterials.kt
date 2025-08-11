package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.fluids.FluidBuilder
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.BandedIron
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
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Albite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Augite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azurite
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
        Limestone = Material.Builder(6001, GTLiteMod.id("limestone"))
            .dust()
            .color(0xA9A9A9).iconSet(ROUGH)
            .components(Calcite, 4, Dolomite, 1, Quicklime, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(CaCO3)4(CaMg(CO3)2)?", true)

        // 6002 Komatiite
        Komatiite = Material.Builder(6002, GTLiteMod.id("komatiite"))
            .dust()
            .color(0xBCB073).iconSet(ROUGH)
            .components(Olivine, 2, Magnesite, 1, Flint, 1, DarkAsh, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(Mg2Fe(SiO2)2)2(MgCO3)(SiO2)?", true)

        // 6003 Green Schist
        GreenSchist = Material.Builder(6003, GTLiteMod.id("green_schist"))
            .dust()
            .color(0x56AE65).iconSet(ROUGH)
            .components(Tanzanite, 2, Quartzite, 2, Talc, 1, Calcite, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(Ca2Al3Si3HO13)2(SiO2)2(Mg3Si4H2O12)?", true)

        // 6004 Blue Schist
        BlueSchist = Material.Builder(6004, GTLiteMod.id("blue_schist"))
            .dust()
            .color(0x537FAC).iconSet(ROUGH)
            .components(Azurite, 3, Sodalite, 2, Iron, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(Cu3(CO3)2(OH)2)3(Al3Si3Na4Cl)2?", true)

        // 6005 Kimberlite
        Kimberlite = Material.Builder(6005, GTLiteMod.id("kimberlite"))
            .dust()
            .color(0x201313).iconSet(ROUGH)
            .components(Forsterite, 3, Augite, 3, Andradite, 2, Lizardite, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(Mg2(SiO4))3((Ca2MgFe)(MgFe)2(Si2O6)4)3(Ca3Fe2Si3O12)2?", true)

        // 6006 Slate
        Slate = Material.Builder(6006, GTLiteMod.id("slate"))
            .dust()
            .color(0x756869).iconSet(ROUGH)
            .components(SiliconDioxide, 5, Muscovite, 2, Clinochlore, 2, Albite, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(SiO2)5(KAl2(AlSi3O10)(OH)2)2(Mg5Al2Si3O10(OH)8)2?", true)

        // 6007 Shale
        Shale = Material.Builder(6007, GTLiteMod.id("shale"))
            .dust()
            .color(0x3F2E2F).iconSet(ROUGH)
            .components(Calcite, 6, Clay, 2, SiliconDioxide, 1, Fluorite, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(CaCO3)6(Na2LiAl2Si2(H2O)6)2(SiO2)(CaF2)?", true)

        // 6008-6010 is for stone types addition in the future.
        // ...

        // 6011 Blazing Pyrotheum
        BlazingPyrotheum = Material.Builder(6011, GTLiteMod.id("blazing_pyrotheum"))
            .dust()
            .liquid(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(4000)
                .customFlow().customStill())
            .iconSet(PYROTHEUM)
            .components(Blaze, 2, Redstone, 1, Sulfur, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 6012 Gelid Cryotheum
        GelidCryotheum = Material.Builder(6012, GTLiteMod.id("gelid_cryotheum"))
            .dust()
            .liquid(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(2)
                .customFlow().customStill())
            .iconSet(CRYOTHEUM)
            .components(Ice, 2, Electrotine, 1, Water, 1)
            .flags(DECOMPOSITION_BY_ELECTROLYZING)
            .build()
            .setFormula("((Si(FeS2)5(CrAl2O3)Hg3)(AgAu))(H2O)3", true)

        // 6013 Tectonic Petrotheum
        TectonicPetrotheum = Material.Builder(6013, GTLiteMod.id("tectonic_petrotheum"))
            .dust()
            .liquid(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(350)
                .customFlow().customFlow())
            .iconSet(PETROTHEUM)
            .components(Clay, 2, Obsidian, 1, Stone, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 6014 Zephyrean Aerotheum
        ZephyreanAerotheum = Material.Builder(6014, GTLiteMod.id("zephyrean_aerotheum"))
            .dust()
            .liquid(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(600)
                .customFlow().customStill())
            .iconSet(AEROTHEUM)
            .components(SiliconDioxide, 2, Saltpeter, 1, Air, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6015 Sienna
        Sienna = Material.Builder(6015, GTLiteMod.id("sienna"))
            .dust()
            .color(0x4A3724)
            .components(ManganeseMonoxide, 1, BandedIron, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 6016 Burnt Sienna
        BurntSienna = Material.Builder(6016, GTLiteMod.id("burnt_sienna"))
            .dust()
            .color(0x662E2E)
            .components(ManganeseMonoxide, 1, BandedIron, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 6017-6025 for some misc materials in the future.
        // ...

        // 6026 Superheated Steam
        SuperheatedSteam = Material.Builder(6026, GTLiteMod.id("superheated_steam"))
            .gas(FluidBuilder().temperature(573))
            .color(0xC4C4C4).iconSet(DULL)
            .components(Water, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6027 Superheated Eutatic Sodium Potassium
        SuperheatedSodiumPotassiumEutatic = Material.Builder(6027, GTLiteMod.id("superheated_sodium_potassium_eutatic"))
            .liquid(FluidBuilder().temperature(758))
            .color(SodiumPotassiumEutatic.materialRGB).iconSet(DULL)
            .components(SodiumPotassiumEutatic, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6028 Superheated Eutatic Lead Bismuth
        SuperheatedLeadBismuthEutatic = Material.Builder(6028, GTLiteMod.id("superheated_lead_bismuth_eutatic"))
            .liquid(FluidBuilder().temperature(1643))
            .color(LeadBismuthEutatic.materialRGB).iconSet(DULL)
            .components(LeadBismuthEutatic, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6029 Superheated Lithium Sodium Potassium Fluorides
        SuperheatedLithiumSodiumPotassiumFluorides = Material.Builder(6029, GTLiteMod.id("superheated_lithium_sodium_potassium_fluorides"))
            .liquid(FluidBuilder().temperature(1543))
            .color(LithiumSodiumPotassiumFluorides.materialRGB).iconSet(DULL)
            .components(LithiumSodiumPotassiumFluorides, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6030 Superheated Lithium Beryllium Fluorides
        SuperheatedLithiumBerylliumFluorides = Material.Builder(6030, GTLiteMod.id("superheated_lithium_beryllium_fluorides"))
            .liquid(FluidBuilder().temperature(1403))
            .color(LithiumBerylliumFluorides.materialRGB).iconSet(DULL)
            .components(LithiumBerylliumFluorides, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6031 Supercritical Steam
        SupercriticalSteam = Material.Builder(6031, GTLiteMod.id("supercritical_steam"))
            .gas(FluidBuilder().temperature(873))
            .color(0xC4C4C4).iconSet(SUPERCRITICAL)
            .components(Water, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6032 Supercritical Eutatic Sodium Potassium
        SupercriticalSodiumPotassiumEutatic = Material.Builder(6032, GTLiteMod.id("supercritical_sodium_potassium_eutatic"))
            .liquid(FluidBuilder().temperature(1058))
            .color(SodiumPotassiumEutatic.materialRGB).iconSet(SUPERCRITICAL)
            .components(SodiumPotassiumEutatic, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6033 Supercritical Eutatic Lead Bismuth
        SupercriticalLeadBismuthEutatic = Material.Builder(6033, GTLiteMod.id("supercritical_lead_bismuth_eutatic"))
            .liquid(FluidBuilder().temperature(1943))
            .color(LeadBismuthEutatic.materialRGB).iconSet(SUPERCRITICAL)
            .components(LeadBismuthEutatic, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6034 Supercritical Lithium Sodium Potassium Fluorides
        SupercriticalLithiumSodiumPotassiumFluorides = Material.Builder(6034, GTLiteMod.id("supercritical_lithium_sodium_potassium_fluorides"))
            .liquid(FluidBuilder().temperature(1843))
            .color(LithiumSodiumPotassiumFluorides.materialRGB).iconSet(SUPERCRITICAL)
            .components(LithiumSodiumPotassiumFluorides, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6035 Supercritical Lithium Beryllium Fluorides
        SupercriticalLithiumBerylliumFluorides = Material.Builder(6035, GTLiteMod.id("supercritical_lithium_beryllium_fluorides"))
            .liquid(FluidBuilder().temperature(1703))
            .color(LithiumBerylliumFluorides.materialRGB).iconSet(SUPERCRITICAL)
            .components(LithiumBerylliumFluorides, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // ...

        // 6051 Ferrosilite
        Ferrosilite = Material.Builder(6051, GTLiteMod.id("ferrosilite"))
            .dust(1)
            .ore()
            .color(0x97632A)
            .components(Iron, 1, Silicon, 1, Oxygen, 3)
            .build()

    }

    // @formatter:on

}