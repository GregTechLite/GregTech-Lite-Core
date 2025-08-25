package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.V
import gregtech.api.fluids.FluidBuilder
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.EXT2_METAL
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.SaltWater
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.FLAMMABLE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DOUBLE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROTOR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROUND
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.info.MaterialFlags.NO_UNIFICATION
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.FINE
import gregtech.api.unification.material.info.MaterialIconSet.METALLIC
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.colorAverage
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AcidicSaltWater
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AlgaeMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Antimatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AppleCaneSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AppleSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BFGF
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BZMedium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BedrockGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BedrockSootSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bitumen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Blood
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BloodCells
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BloodPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BosonicUUMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BrevibacteriumFlavum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BrownAlgae
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CAT
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaneSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Carbon5Fraction
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChalcogenAnodeMud
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CleanBedrockSootSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CleanEnrichedBedrockSootSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Coffee
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CoughSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrackedHeavyEnrichedTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrackedHeavyTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrackedLightEnrichedTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrackedLightTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrackedMediumEnrichedTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrackedMediumTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CranberryEtirps
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CranberryExtract
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CranberrySodaSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrudeNaquadahFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CupriavidusNecator
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimerizedCarbon5Fraction
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EGF
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedBedrockSootSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EscherichiaColi
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Etirps
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fat
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FermionicUUMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FracturingFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FreeElectronGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Gluons
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GrapeJuice
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenAlgae
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenSapphireJuice
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenhouseGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HadronicResonantGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HardAppleCandySyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyBedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyEnrichedBedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyEnrichedTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyEnrichedTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyLeptonMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyNaquadahFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarks
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HighEnergyQuarkGluonPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kerogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Latex
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LemonExtract
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LemonLimeMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LemonLimeSodaSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightBedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightEnrichedBedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightEnrichedTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightEnrichedTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightNaquadahFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightQuarks
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LimeExtract
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LowPurityEnrichedNaquadahEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LowPurityNaquadriaEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MediumBedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MediumEnrichedBedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MediumEnrichedTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MediumEnrichedTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MediumNaquadahFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MediumTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MediumTaraniumGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MethylamineMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mud
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NaquadahGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NaquadriaEnergetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutronProtonFermiSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OganessonBreedingBase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OliveOil
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OrangeExtract
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Paraffin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphoreneSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polenta
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotatoJuice
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Protomatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PurpleDrink
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuarkGluonPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuasifissioningPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RainbowSap
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RareEarthChloridesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RareEarthHydroxidesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RawStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RedAlgae
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RedWine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ResonantStrangeMeson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichAmmoniaMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichNitrogenMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RubyJuice
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SapphireJuice
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaWater
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SelfInteractingDarkMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SemistableAntimatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodioIndene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpatiallyEnlargedFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StableBaryonicMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SteamCrackedSodioIndene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StreptococcusPyogenes
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TachyonRichTemporalFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.UltralightBedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.UnprocessedNdYAGSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vinegar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vodka
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Yeast
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.DARKMATTER
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.MHDCSM
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.WAX

object GTLiteUnknownCompositionMaterials
{

    // @formatter:off

    fun init()
    {
        // 12001 Latex
        Latex = Material.Builder(12001, GTLiteMod.id("latex"))
            .dust()
            .liquid()
            .color(0xFFFADA)
            .build()

        // 12002 Resin
        Resin = Material.Builder(12002, GTLiteMod.id("resin"))
            .dust()
            .liquid()
            .color(0xB5803A)
            .flags(FLAMMABLE)
            .build()

        // 12003 Rainbow Sap
        RainbowSap = Material.Builder(12003, GTLiteMod.id("rainbow_sap"))
            .liquid(FluidBuilder().customStill())
            .build()

        // 12004-12020 for other sap liquids.
        // ...

        // 12021 Blood
        Blood = Material.Builder(12021, GTLiteMod.id("blood"))
            .liquid()
            .color(0x5C0606)
            .build()

        // 12022 Blood Cells
        BloodCells = Material.Builder(12022, GTLiteMod.id("blood_cells"))
            .liquid()
            .color(0xAD2424).iconSet(DULL)
            .build()

        // 12023 Blood Plasma
        BloodPlasma = Material.Builder(12023, GTLiteMod.id("blood_plasma"))
            .liquid()
            .color(0xE37171).iconSet(DULL)
            .build()

        // 12024 bFGF
        BFGF = Material.Builder(12024, GTLiteMod.id("bfgf"))
            .liquid()
            .color(0xB365E0)
            .build()
            .setFormula("bFGF", false)

        // 12025 EGF
        EGF = Material.Builder(12025, GTLiteMod.id("egf"))
            .liquid()
            .color(0x815799)
            .build()
            .setFormula("EGF", false) // C257H381N73O83S7 in reality world ^^.

        // 12026 CAT
        CAT = Material.Builder(12026, GTLiteMod.id("cat"))
            .liquid()
            .color(0xDB6596)
            .build()
            .setFormula("CAT", false)

        // 12027 Kerogen
        Kerogen = Material.Builder(12027, GTLiteMod.id("kerogen"))
            .liquid(FluidBuilder().temperature(302))
            .color(0xA7A7A7).iconSet(DULL)
            .build()

        // 12028 Paraffin
        Paraffin = Material.Builder(12028, GTLiteMod.id("paraffin"))
            .dust(0, 40 * SECOND)
            .color(0xD2D2FA).iconSet(WAX)
            .flags(FLAMMABLE)
            .build()

        // 12029 Bitumen
        Bitumen = Material.Builder(12029, GTLiteMod.id("bitumen"))
            .dust()
            .color(0x2D2D05).iconSet(WAX)
            .build()

        // 12030 for other misc biological materials
        // ...

        // 12031 Green Sapphire Juice
        GreenSapphireJuice = Material.Builder(12031, GTLiteMod.id("green_sapphire_juice"))
            .liquid()
            .color(GreenSapphire.materialRGB)
            .components(GreenSapphire.materialComponents)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 12032 Sapphire Juice
        SapphireJuice = Material.Builder(12032, GTLiteMod.id("sapphire_juice"))
            .liquid()
            .color(Sapphire.materialRGB)
            .components(Sapphire.materialComponents)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 12033 Ruby Juice
        RubyJuice = Material.Builder(12033, GTLiteMod.id("ruby_juice"))
            .liquid()
            .color(Ruby.materialRGB)
            .components(Ruby.materialComponents)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 12034-12039 for other gem slurries and juices.
        // ...

        // 12040 Greenhouse Gas
        GreenhouseGas = Material.Builder(12040, GTLiteMod.id("greenhouse_gas"))
            .gas(FluidBuilder().temperature(313))
            .build()
            .setFormula("N78O21Ar9?", true)

        // 12041 Grape Juice
        GrapeJuice = Material.Builder(12041, GTLiteMod.id("grape_juice"))
            .liquid()
            .color(0x50C039)
            .build()

        // 12042 Red Wine
        RedWine = Material.Builder(12042, GTLiteMod.id("red_wine"))
            .liquid()
            .color(0x7D0B07)
            .build()

        // 12043 Vinegar
        Vinegar = Material.Builder(12043, GTLiteMod.id("vinegar"))
            .liquid()
            .color(0x5E0805)
            .build()

        // 12044 Potato Juice
        PotatoJuice = Material.Builder(12044, GTLiteMod.id("potato_juice"))
            .liquid()
            .color(0xC3A92C)
            .build()

        // 12045 Vodka
        Vodka = Material.Builder(12045, GTLiteMod.id("vodka"))
            .liquid()
            .colorAverage(PotatoJuice, Water)
            .build()

        // 12046 Polenta
        Polenta = Material.Builder(12046, GTLiteMod.id("polenta"))
            .liquid()
            .color(0xBBA844)
            .build()

        // 12047 Coffee
        Coffee = Material.Builder(12047, GTLiteMod.id("coffee"))
            .liquid()
            .color(0x36312E)
            .build()

        // 12048 Butter
        Butter = Material.Builder(12048, GTLiteMod.id("butter"))
            .liquid()
            .color(0xFFEF82)
            .build()

        // 12049 Purple Drink
        PurpleDrink = Material.Builder(12049, GTLiteMod.id("purple_drink"))
            .liquid()
            .color(0xB405FF)
            .build()

        // 12050 Carbon 5 Fraction
        Carbon5Fraction = Material.Builder(12050, GTLiteMod.id("carbon_5_fraction"))
            .liquid()
            .color(0x9C8638)
            .flags(FLAMMABLE)
            .build()

        // 12051 Dimerized Carbon 5 Fraction
        DimerizedCarbon5Fraction = Material.Builder(12051, GTLiteMod.id("dimerized_carbon_5_fraction"))
            .liquid()
            .color(0x9C9538)
            .flags(FLAMMABLE)
            .build()

        // 12052 Olive Oil
        OliveOil = Material.Builder(12052, GTLiteMod.id("olive_oil"))
            .liquid()
            .color(0x969D56)
            .build()

        // 12053 Fat
        Fat = Material.Builder(12053, GTLiteMod.id("fat"))
            .liquid()
            .color(0xFFF200)
            .build()
            .setFormula("C57H110O6", true)

        // 12054 Mud
        Mud = Material.Builder(12054, GTLiteMod.id("mud"))
            .liquid()
            .color(0x2C2B01)
            .build()

        // 12055 Lemon Extract
        LemonExtract = Material.Builder(12055, GTLiteMod.id("lemon_extract"))
            .liquid()
            .color(0xFCE80A)
            .build()

        // 12056 Lime Extract
        LimeExtract = Material.Builder(12056, GTLiteMod.id("lime_extract"))
            .liquid()
            .color(0x85F218)
            .build()

        // 12057 Orange Extract
        OrangeExtract = Material.Builder(12057, GTLiteMod.id("orange_extract"))
            .liquid()
            .color(0xFF6100)
            .build()

        // 12058 Lemon-Lime Mixture
        LemonLimeMixture = Material.Builder(12058, GTLiteMod.id("lemon_lime_mixture"))
            .liquid()
            .color(0xBDDB5A)
            .build()

        // 12059 Lemon-Lime Soda Syrup
        LemonLimeSodaSyrup = Material.Builder(12059, GTLiteMod.id("lemon_lime_soda_syrup"))
            .liquid()
            .color(0x76FF0D)
            .build()

        // 12060 Etirps™ (Sprite)
        Etirps = Material.Builder(12060, GTLiteMod.id("etirps"))
            .liquid()
            .color(0xB0FF73)
            .build()

        // 12061 Cranberry Extract
        CranberryExtract = Material.Builder(12061, GTLiteMod.id("cranberry_extract"))
            .liquid()
            .color(0x8C0D22)
            .build()

        // 12062 Cranberry Soda Syrup
        CranberrySodaSyrup = Material.Builder(12062, GTLiteMod.id("cranberry_soda_syrup"))
            .liquid()
            .color(0x8C0D22)
            .build()

        // 12063 Cranberry Etirps™ (Sprite)
        CranberryEtirps = Material.Builder(12063, GTLiteMod.id("cranberry_etirps"))
            .liquid()
            .colorAverage(CranberryExtract.materialRGB, 0xBBDDBB)
            .build()

        // 12064 Cough Syrup
        CoughSyrup = Material.Builder(12064, GTLiteMod.id("cough_syrup"))
            .liquid()
            .color(0x5C1B5E)
            .build()

        // 12065 Apple Syrup
        AppleSyrup = Material.Builder(12065, GTLiteMod.id("apple_syrup"))
            .liquid()
            .color(0xF2E1AC)
            .build()

        // 12065 Cane Syrup
        CaneSyrup = Material.Builder(12066, GTLiteMod.id("cane_syrup"))
            .liquid()
            .color(0xF2F1DC)
            .build()

        // 12066 Apple-Cane Syrup
        AppleCaneSyrup = Material.Builder(12067, GTLiteMod.id("apple_cane_syrup"))
            .liquid()
            .color(0xE7F5AE)
            .build()

        // 12067 Hard Apple Candy Syrup
        HardAppleCandySyrup = Material.Builder(12068, GTLiteMod.id("hard_apple_candy_syrup"))
            .liquid()
            .color(0x78E32B)
            .build()

        // 12068 Fracturing Fluid
        FracturingFluid = Material.Builder(12069, GTLiteMod.id("fracturing_fluid"))
            .liquid()
            .color(0x96D6D5)
            .build()

        // 12069-12100 for misc unknown composition materials.
        // ...

        // 12101 Free Electron Gas
        FreeElectronGas = Material.Builder(12101, GTLiteMod.id("free_electron_gas"))
            .gas(FluidBuilder()
                .temperature(50)
                .translation("gregtech.fluid.generic"))
            .color(0x507BB3)
            .build()

        // 12102 Fermionic UU Matter
        FermionicUUMatter = Material.Builder(12102, GTLiteMod.id("fermionic_uu_matter"))
            .liquid(FluidBuilder().temperature(125))
            .color(UUMatter.materialRGB / 3)
            .build()

        // 12103 Bosonic UU Matter
        BosonicUUMatter = Material.Builder(12103, GTLiteMod.id("bosonic_uu_matter"))
            .liquid(FluidBuilder().temperature(125))
            .color(UUMatter.materialRGB - FermionicUUMatter.materialRGB)
            .build()

        // 12104 Rich Nitrogen Mixture
        RichNitrogenMixture = Material.Builder(12104, GTLiteMod.id("rich_nitrogen_mixture"))
            .gas(FluidBuilder().temperature(400))
            .color(0x6891D8)
            .build()

        // 12105 Rich Ammonia Mixture
        RichAmmoniaMixture = Material.Builder(12105, GTLiteMod.id("rich_ammonia_mixture"))
            .gas(FluidBuilder().temperature(400))
            .color(0x708ACD)
            .build()

        // 12106 Sea Water
        SeaWater = Material.Builder(12106, GTLiteMod.id("sea_water"))
            .liquid()
            .color(0x0000FF)
            .build()
            .setFormula("H2O?", true)

        // 12107 Acidic Salt Water
        AcidicSaltWater = Material.Builder(12107, GTLiteMod.id("acidic_salt_water"))
            .liquid()
            .colorAverage(SaltWater, SulfuricAcid)
            .build()
            .setFormula("(H2O)(H2SO4)?", true)

        // 12108 Chalcogen Anode Mud
        ChalcogenAnodeMud = Material.Builder(12108, GTLiteMod.id("chalcogen_anode_mud"))
            .dust()
            .color(0x8A3324)
            .iconSet(FINE)
            .build()

        // 12109 Rare Earth Hydroxides Solution
        RareEarthHydroxidesSolution = Material.Builder(12109, GTLiteMod.id("rare_earth_hydroxides_solution"))
            .liquid()
            .color(0x434327)
            .build()

        // 12110 Rare Earth Chlorides Solution
        RareEarthChloridesSolution = Material.Builder(12110, GTLiteMod.id("rare_earth_chlorides_solution"))
            .liquid()
            .color(0x838367)
            .build()

        // 12111 Brevibacterium Flavum
        BrevibacteriumFlavum = Material.Builder(12111, GTLiteMod.id("brevibacterium_flavum"))
            .dust()
            .color(0x766718).iconSet(ROUGH)
            .build()

        // 12112 Yeast
        Yeast = Material.Builder(12112, GTLiteMod.id("yeast"))
            .dust()
            .color(0xF0E660).iconSet(ROUGH)
            .build()

        // 12113 Cupriavidus Necator
        CupriavidusNecator = Material.Builder(12113, GTLiteMod.id("cupriavidus_necator"))
            .dust()
            .color(0x2C4D24).iconSet(ROUGH)
            .build()

        // 12114 Streptococcus Pyogenes
        StreptococcusPyogenes = Material.Builder(12114, GTLiteMod.id("streptococcus_pyogenes"))
            .dust()
            .color(0x999933).iconSet(ROUGH)
            .build()

        // 12115 Escherichia Coli
        EscherichiaColi = Material.Builder(12115, GTLiteMod.id("escherichia_coli"))
            .dust()
            .color(0x398C47).iconSet(DULL)
            .build()

        // 12116 Green Algae
        GreenAlgae = Material.Builder(12116, GTLiteMod.id("green_algae"))
            .dust()
            .color(0x228B22).iconSet(METALLIC)
            .build()

        // 12117 Brown Algae
        BrownAlgae = Material.Builder(12117, GTLiteMod.id("brown_algae"))
            .dust()
            .color(0xA52A2A).iconSet(METALLIC)
            .build()

        // 12118 Red Algae
        RedAlgae = Material.Builder(12118, GTLiteMod.id("red_algae"))
            .dust()
            .color(0xF08080).iconSet(METALLIC)
            .build()

        // 12119 Algae Mixture
        AlgaeMixture = Material.Builder(12119, GTLiteMod.id("algae_mixture"))
            .liquid()
            .colorAverage(GreenAlgae, BrownAlgae, RedAlgae).iconSet(DULL)
            .build()

        // 12115-12130 for other biological components.
        // ...

        // 12131 MethylamineMixture
        MethylamineMixture = Material.Builder(12131, GTLiteMod.id("methylamine_mixture"))
            .liquid()
            .color(0xAA4400)
            .build()

        // 12132 B-Z Medium
        BZMedium = Material.Builder(12132, GTLiteMod.id("bz_medium"))
            .liquid()
            .color(0xA2FD35)
            .build()

        // 12133 Low Purity Enriched Naquadah Emulsion
        LowPurityEnrichedNaquadahEmulsion = Material.Builder(12133, GTLiteMod.id("low_purity_enriched_naquadah_emulsion"))
            .liquid()
            .color(0x4C4C4C).iconSet(DULL)
            .build()

        // 12134 Low Purity Naquadria Emulsion
        LowPurityNaquadriaEmulsion = Material.Builder(12134, GTLiteMod.id("low_purity_naquadria_emulsion"))
            .liquid()
            .color(0x393939)
            .iconSet(DULL)
            .build()

        // 12135 Bedrock Smoke
        BedrockSmoke = Material.Builder(12135, GTLiteMod.id("bedrock_smoke"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x525252)
            .build()

        // 12136 Bedrock Soot Solution
        BedrockSootSolution = Material.Builder(12136, GTLiteMod.id("bedrock_soot_solution"))
            .liquid()
            .color(0x1E2430)
            .build()

        // 12137 Clean Bedrock Soot Solution
        CleanBedrockSootSolution = Material.Builder(12137, GTLiteMod.id("clean_bedrock_soot_solution"))
            .liquid()
            .color(0xA89F9E)
            .build()

        // 12138 Heavy Bedrock Smoke
        HeavyBedrockSmoke = Material.Builder(12138, GTLiteMod.id("heavy_bedrock_smoke"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x242222)
            .build()

        // 12139 Medium Bedrock Smoke
        MediumBedrockSmoke = Material.Builder(12139, GTLiteMod.id("medium_bedrock_smoke"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x2E2C2C)
            .build()

        // 12140 Light Bedrock Smoke
        LightBedrockSmoke = Material.Builder(12140, GTLiteMod.id("light_bedrock_smoke"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x363333)
            .build()

        // 12141 Ultralight Bedrock Smoke
        UltralightBedrockSmoke = Material.Builder(12141, GTLiteMod.id("ultralight_bedrock_smoke"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x403D3D)
            .build()

        // 12142 Heavy Taranium Gas
        HeavyTaraniumGas = Material.Builder(12142, GTLiteMod.id("heavy_taranium_gas"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x262626)
            .build()

        // 12143 Medium Taranium Gas
        MediumTaraniumGas = Material.Builder(12143, GTLiteMod.id("medium_taranium_gas"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x313131)
            .build()

        // 12144 Light Taranium Gas
        LightTaraniumGas = Material.Builder(12144, GTLiteMod.id("light_taranium_gas"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x404040)
            .build()

        // 12145 Bedrock Gas
        BedrockGas = Material.Builder(12145, GTLiteMod.id("bedrock_gas"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x575757)
            .build()

        // 12146 Crude Naquadah Fuel
        CrudeNaquadahFuel = Material.Builder(12146, GTLiteMod.id("crude_naquadah_fuel"))
            .liquid()
            .color(0x077F4E)
            .build()

        // 12147 Heavy Naquadah Fuel
        HeavyNaquadahFuel = Material.Builder(12147, GTLiteMod.id("heavy_naquadah_fuel"))
            .liquid()
            .color(0x088C56)
            .build()

        // 12148 Medium Naquadah Fuel
        MediumNaquadahFuel = Material.Builder(12148, GTLiteMod.id("medium_naquadah_fuel"))
            .liquid()
            .color(0x09A566)
            .build()

        // 12149 Light Naquadah Fuel
        LightNaquadahFuel = Material.Builder(12149, GTLiteMod.id("light_naquadah_fuel"))
            .liquid()
            .color(0x0BBF75)
            .build()

        // 12150 Naquadah Gas
        NaquadahGas = Material.Builder(12150, GTLiteMod.id("naquadah_gas"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x0CD985)
            .build()

        // 12151 Cracked Heavy Taranium Gas
        CrackedHeavyTaraniumGas = Material.Builder(12151, GTLiteMod.id("cracked_heavy_taranium_gas"))
            .liquid()
            .color(0x1F2B2E)
            .build()

        // 12152 Cracked Medium Taranium Gas
        CrackedMediumTaraniumGas = Material.Builder(12152, GTLiteMod.id("cracked_medium_taranium_gas"))
            .liquid()
            .color(0x29393D)
            .build()

        // 12153 Cracked Light Taranium Gas
        CrackedLightTaraniumGas = Material.Builder(12153, GTLiteMod.id("cracked_light_taranium_gas"))
            .liquid()
            .color(0x374C52)
            .build()

        // 12154 Heavy Taranium Fuel
        HeavyTaraniumFuel = Material.Builder(12154, GTLiteMod.id("heavy_taranium_fuel"))
            .liquid()
            .color(0x141414)
            .build()

        // 12155 Medium Taranium Fuel
        MediumTaraniumFuel = Material.Builder(12155, GTLiteMod.id("medium_taranium_fuel"))
            .liquid()
            .color(0x181818)
            .build()

        // 12156 Light Taranium Fuel
        LightTaraniumFuel = Material.Builder(12156, GTLiteMod.id("light_taranium_fuel"))
            .liquid()
            .color(0x1C1C1C)
            .build()

        // 12157 Enriched Bedrock Soot Solution
        EnrichedBedrockSootSolution = Material.Builder(12157, GTLiteMod.id("enriched_bedrock_soot_solution"))
            .liquid()
            .color(0x280C26)
            .build()

        // 12158 Clean Enriched Bedrock Soot Solution
        CleanEnrichedBedrockSootSolution = Material.Builder(12158, GTLiteMod.id("clean_enriched_bedrock_soot_solution"))
            .liquid()
            .color(0x828C8C)
            .build()

        // 12159 Heavy Enriched Bedrock Smoke
        HeavyEnrichedBedrockSmoke = Material.Builder(12159, GTLiteMod.id("heavy_enriched_bedrock_smoke"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x1A2222)
            .build()

        // 12160 Medium Enriched Bedrock Smoke
        MediumEnrichedBedrockSmoke = Material.Builder(12160, GTLiteMod.id("medium_enriched_bedrock_smoke"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x1E2C2C)
            .build()

        // 12161 Light Enriched Bedrock Smoke
        LightEnrichedBedrockSmoke = Material.Builder(12161, GTLiteMod.id("light_enriched_bedrock_smoke"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x163333)
            .build()

        // 12162 Heavy Enriched Taranium Gas
        HeavyEnrichedTaraniumGas = Material.Builder(12162, GTLiteMod.id("heavy_enriched_taranium_gas"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x1F2626)
            .build()

        // 12163 Medium Enriched Taranium Gas
        MediumEnrichedTaraniumGas = Material.Builder(12163, GTLiteMod.id("medium_enriched_taranium_gas"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x1F3131)
            .build()

        // 12164 Light Enriched Taranium Gas
        LightEnrichedTaraniumGas = Material.Builder(12164, GTLiteMod.id("light_enriched_taranium_gas"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x1F4040)
            .build()

        // 12165 Cracked Heavy Enriched Taranium Gas
        CrackedHeavyEnrichedTaraniumGas = Material.Builder(12165, GTLiteMod.id("cracked_heavy_enriched_taranium_gas"))
            .liquid()
            .color(0x2E1F2E)
            .build()

        // 12166 Cracked Medium Enriched Taranium Gas
        CrackedMediumEnrichedTaraniumGas = Material.Builder(12166, GTLiteMod.id("cracked_medium_enriched_taranium_gas"))
            .liquid()
            .color(0x29393D)
            .build()

        // 12167 Cracked Light Enriched Taranium Gas
        CrackedLightEnrichedTaraniumGas = Material.Builder(12167, GTLiteMod.id("cracked_light_enriched_taranium_gas"))
            .liquid()
            .color(0x374C52)
            .build()

        // 12168 Heavy Enriched Taranium Fuel
        HeavyEnrichedTaraniumFuel = Material.Builder(12168, GTLiteMod.id("heavy_enriched_taranium_fuel"))
            .liquid()
            .color(0x0F1414)
            .build()

        // 12169 Medium Enriched Taranium Fuel
        MediumEnrichedTaraniumFuel = Material.Builder(12169, GTLiteMod.id("medium_enriched_taranium_fuel"))
            .liquid()
            .color(0x0F1818)
            .build()

        // 12170 Light Enriched Taranium Fuel
        LightEnrichedTaraniumFuel = Material.Builder(12170, GTLiteMod.id("light_enriched_taranium_fuel"))
            .liquid()
            .color(0x0F1C1C)
            .build()

        // 12171 Energetic Naquadria
        NaquadriaEnergetic = Material.Builder(12171, GTLiteMod.id("energetic_naquadria"))
            .liquid()
            .color(0x202020)
            .build()

        // 12172 Quasi-fissioning plasma
        QuasifissioningPlasma = Material.Builder(12172, GTLiteMod.id("quasi_fissioning_plasma"))
            .plasma()
            .color(0xB0A2C3).iconSet(DULL)
            .build()

        // 12173 Oganesson Breeding Base
        OganessonBreedingBase = Material.Builder(12173, GTLiteMod.id("oganesson_breeding_base"))
            .liquid()
            .color(0xA65A7F).iconSet(DULL)
            .build()

        // 12174 Unprocessed Nd:YAG Solution
        UnprocessedNdYAGSolution = Material.Builder(12174, GTLiteMod.id("unprocessed_nd_yag_solution"))
            .liquid()
            .color(0x6F20AF).iconSet(DULL)
            .build()
            .setFormula("Nd:YAG?", false)

        // 12175 Mutated Living Solder
        MutatedLivingSolder = Material.Builder(12175, GTLiteMod.id("mutated_living_solder"))
            .liquid(FluidBuilder()
                .temperature(10525))
            .color(0x936D9B).iconSet(DULL)
            .build()

        // 12176 Sodio-Indene
        SodioIndene = Material.Builder(12176, GTLiteMod.id("sodio_indene"))
            .liquid()
            .color(0x1D1C24)
            .build()

        // 12177 Steam-cracked Sodio-Indene
        SteamCrackedSodioIndene = Material.Builder(12177, GTLiteMod.id("steam_cracked_sodio_indene"))
            .liquid(FluidBuilder().temperature(1105))
            .color(0x1C1A29)
            .build()

        // 12178 Phosphorene Solution
        PhosphoreneSolution = Material.Builder(12178, GTLiteMod.id("phosphorene_solution"))
            .liquid()
            .color(0x465966)
            .build()

        // 12179-12999 for misc materials.
        // ...

        // Materials for particles and QCD contents reference this:
        // Helmut Satz, The Thermodynamics of Quarks and Gluons
        // https://link.springer.com/chapter/10.1007/978-3-642-02286-9_1

        // 13001 Neutron-Proton Fermi Superfluid
        NeutronProtonFermiSuperfluid = Material.Builder(13001, GTLiteMod.id("neutron_proton_fermi_superfluid"))
            .plasma(FluidBuilder()
                .temperature(100_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13002 Heavy Lepton Mixture
        HeavyLeptonMixture = Material.Builder(13002, GTLiteMod.id("heavy_lepton_mixture"))
            .liquid(FluidBuilder()
                .temperature(48_000_000)
                .customStill())
            .build()

        // 13003 Quark-Gluon Plasma
        QuarkGluonPlasma = Material.Builder(13003, GTLiteMod.id("quark_gluon_plasma"))
            .plasma(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(1_500_000_000) // In reality world, Q-G plasma has 15000000000~20000000000 MeV by theory, so we transform it as K.
                .customStill())
            .build()

        // 13004 Heavy Quarks
        HeavyQuarks = Material.Builder(13004, GTLiteMod.id("heavy_quarks"))
            .liquid(FluidBuilder()
                .temperature(1_800_000_000)
                .customStill())
            .build()

        // 13005 Light Quarks
        LightQuarks = Material.Builder(13005, GTLiteMod.id("light_quarks"))
            .liquid(FluidBuilder()
                .temperature(900_000_000)
                .customStill())
            .build()

        // 13006 Gluons
        Gluons = Material.Builder(13006, GTLiteMod.id("gluons"))
            .gas(FluidBuilder()
                .temperature(2_000_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13007 Hadronic Resonant Gas
        HadronicResonantGas = Material.Builder(13007, GTLiteMod.id("hadronic_resonant_gas"))
            .gas(FluidBuilder()
                .temperature(1_500_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13008 Stable Baryonic Matter
        StableBaryonicMatter = Material.Builder(13008, GTLiteMod.id("stable_baryonic_matter"))
            .plasma(FluidBuilder()
                .temperature(1_200_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13009 High Energy Quark-Gluon Plasma
        HighEnergyQuarkGluonPlasma = Material.Builder(13009, GTLiteMod.id("high_energy_quark_gluon_plasma"))
            .plasma(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(2_000_000_000) // In reality world, Q-G plasma has 15000000000~20000000000 MeV by theory, so we transform it as K.
                .customStill())
            .build()

        // 13010 Resonant Strange Meson
        ResonantStrangeMeson = Material.Builder(13010, GTLiteMod.id("resonant_strange_meson"))
            .plasma(FluidBuilder()
                .temperature(1_600_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13011 Protomatter
        Protomatter = Material.Builder(13011, GTLiteMod.id("protomatter"))
            .plasma(FluidBuilder()
                .temperature(1_000_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13012 Semistable Antimatter
        SemistableAntimatter = Material.Builder(13012, GTLiteMod.id("semistable_antimatter"))
            .plasma(FluidBuilder()
                .temperature(1_500_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13013 Antimatter
        Antimatter = Material.Builder(13013, GTLiteMod.id("antimatter"))
            .plasma(FluidBuilder()
                .temperature(2_000_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13014 Spatially Enlarged Fluid
        SpatiallyEnlargedFluid = Material.Builder(13014, GTLiteMod.id("spatially_enlarged_fluid"))
            .liquid(FluidBuilder()
                .temperature(1)
                .customStill())
            .build()

        // 13015 Tachyon Rich Temporal Fluid
        TachyonRichTemporalFluid = Material.Builder(13015, GTLiteMod.id("tachyon_rich_temporal_fluid"))
            .liquid(FluidBuilder()
                .temperature(1)
                .customStill())
            .build()

        // 13016 Primordial Matter
        PrimordialMatter = Material.Builder(13016, GTLiteMod.id("primordial_matter"))
            .liquid(FluidBuilder()
                .temperature(2_000_000_000)
                .customStill())
            .build()

        // 13017 Condensed Raw Stellar Plasma Mixture
        RawStarMatter = Material.Builder(13017, GTLiteMod.id("condensed_raw_stellar_plasma_mixture"))
            .plasma(FluidBuilder()
                .temperature(2_000_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

        // 13018 Magnetohydrodynamically Constrained Star Matter (MHDCSM)
        MagnetohydrodynamicallyConstrainedStarMatter = Material.Builder(13018, GTLiteMod.id("magnetohydrodynamically_constrained_star_matter"))
            .ingot()
            .plasma(FluidBuilder()
                .temperature(2_000_000_000))
            .iconSet(MHDCSM)
            .flags(EXT2_METAL, NO_UNIFICATION, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL,
                GENERATE_ROTOR, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND,
                GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
            .build()

        // 13019 Self-Interacting Dark Matter (SIDM)
        SelfInteractingDarkMatter = Material.Builder(13019, GTLiteMod.id("self_interacting_dark_matter"))
            .ingot()
            .liquid()
            .iconSet(DARKMATTER)
            .flags(EXT2_METAL, GENERATE_FRAME, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .cableProperties(V[MAX] - 1, 488, 1)
            .build()

        // ...

        // 13050 Dimensionally Shifted Superfluid
        DimensionallyShiftedSuperfluid = Material.Builder(13050, GTLiteMod.id("dimensionally_shifted_superfluid"))
            .plasma(FluidBuilder()
                .temperature(2_000_000_000)
                .translation("gregtech.fluid.generic")
                .customStill())
            .build()

    }

    // @formatter:on

}