package magicbook.gtlitecore.api.unification.material

import gregtech.api.fluids.FluidBuilder
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.SaltWater
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.FLAMMABLE
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.FINE
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AcidicSaltWater
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AppleCaneSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AppleSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BFGF
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BZMedium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BedrockGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BedrockSootSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Blood
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BloodCells
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BloodPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BosonicUUMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BrevibacteriumFlavum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CAT
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaneSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Carbon5Fraction
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChalcogenAnodeMud
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CleanBedrockSootSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CleanEnrichedBedrockSootSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Coffee
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CoughSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedHeavyEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedHeavyTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedLightEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedLightTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedMediumEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedMediumTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CranberryEtirps
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CranberryExtract
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CranberrySodaSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrudeNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CupriavidusNecator
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimensionallyShiftedSuperfluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimerizedCarbon5Fraction
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EGF
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedBedrockSootSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Etirps
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fat
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FermionicUUMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FreeElectronGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Gluons
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GrapeJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GreenSapphireJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GreenhouseGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HadronicResonantGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HardAppleCandySyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyEnrichedBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyEnrichedTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyLeptonMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HighEnergyQuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Latex
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LemonExtract
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LemonLimeMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LemonLimeSodaSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightEnrichedBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightEnrichedTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightQuarks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LimeExtract
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LowPurityEnrichedNaquadahEmulsion
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LowPurityNaquadriaEmulsion
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumEnrichedBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumEnrichedTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MethylamineMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Mud
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MutatedLivingSolder
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NaquadahGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NaquadriaEnergetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NeutronProtonFermiSuperfluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OganessonBreedingBase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OliveOil
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OrangeExtract
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhosphoreneSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polenta
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotatoJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PurpleDrink
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuasifissioningPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RainbowSap
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RareEarthChloridesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RareEarthHydroxidesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RedWine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resin
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RichAmmoniaMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RichNitrogenMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RubyJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SapphireJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaWater
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodioIndene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StableBaryonicMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SteamCrackedSodioIndene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.UltralightBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.UnprocessedNdYAGSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vinegar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vodka
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Yeast
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.averageRGB
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.gtliteId

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteUnknownCompositionMaterials
{

    companion object
    {

        fun init()
        {
            // 12001 Latex
            Latex = Material.Builder(12001, gtliteId("latex"))
                .dust()
                .liquid()
                .color(0xFFFADA)
                .build()

            // 12002 Resin
            Resin = Material.Builder(12002, gtliteId("resin"))
                .dust()
                .liquid()
                .color(0xB5803A)
                .flags(FLAMMABLE)
                .build()

            // 12003 Rainbow Sap
            RainbowSap = Material.Builder(12003, gtliteId("rainbow_sap"))
                .liquid(FluidBuilder().customStill())
                .build()

            // 12004-12020 for other sap liquids.
            // ...

            // 12021 Blood
            Blood = Material.Builder(12021, gtliteId("blood"))
                .liquid()
                .color(0x5C0606)
                .build()

            // 12022 Blood Cells
            BloodCells = Material.Builder(12022, gtliteId("blood_cells"))
                .liquid()
                .color(0xAD2424).iconSet(DULL)
                .build()

            // 12023 Blood Plasma
            BloodPlasma = Material.Builder(12023, gtliteId("blood_plasma"))
                .liquid()
                .color(0xE37171).iconSet(DULL)
                .build()

            // 12024 bFGF
            BFGF = Material.Builder(12024, gtliteId("bfgf"))
                .liquid()
                .color(0xB365E0)
                .build()
                .setFormula("bFGF", false)

            // 12025 EGF
            EGF = Material.Builder(12025, gtliteId("egf"))
                .liquid()
                .color(0x815799)
                .build()
                .setFormula("EGF", false) // C257H381N73O83S7 in reality world ^^.

            // 12026 CAT
            CAT = Material.Builder(12026, gtliteId("cat"))
                .liquid()
                .color(0xDB6596)
                .build()
                .setFormula("CAT", false)

            // 12027-12030 for other misc biological materials
            // ...

            // 12031 Green Sapphire Juice
            GreenSapphireJuice = Material.Builder(12031, gtliteId("green_sapphire_juice"))
                .liquid()
                .color(GreenSapphire.materialRGB)
                .components(GreenSapphire.materialComponents)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 12032 Sapphire Juice
            SapphireJuice = Material.Builder(12032, gtliteId("sapphire_juice"))
                .liquid()
                .color(Sapphire.materialRGB)
                .components(Sapphire.materialComponents)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 12033 Ruby Juice
            RubyJuice = Material.Builder(12033, gtliteId("ruby_juice"))
                .liquid()
                .color(Ruby.materialRGB)
                .components(Ruby.materialComponents)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 12034-12039 for other gem slurries and juices.
            // ...

            // 12040 Greenhouse Gas
            GreenhouseGas = Material.Builder(12040, gtliteId("greenhouse_gas"))
                .gas(FluidBuilder().temperature(313))
                .build()
                .setFormula("N78O21Ar9?", true)

            // 12041 Grape Juice
            GrapeJuice = Material.Builder(12041, gtliteId("grape_juice"))
                .liquid()
                .color(0x50C039)
                .build()

            // 12042 Red Wine
            RedWine = Material.Builder(12042, gtliteId("red_wine"))
                .liquid()
                .color(0x7D0B07)
                .build()

            // 12043 Vinegar
            Vinegar = Material.Builder(12043, gtliteId("vinegar"))
                .liquid()
                .color(0x5E0805)
                .build()

            // 12044 Potato Juice
            PotatoJuice = Material.Builder(12044, gtliteId("potato_juice"))
                .liquid()
                .color(0xC3A92C)
                .build()

            // 12045 Vodka
            Vodka = Material.Builder(12045, gtliteId("vodka"))
                .liquid()
                .color(averageRGB(2, PotatoJuice.materialRGB, Water.materialRGB))
                .build()

            // 12046 Polenta
            Polenta = Material.Builder(12046, gtliteId("polenta"))
                .liquid()
                .color(0xBBA844)
                .build()

            // 12047 Coffee
            Coffee = Material.Builder(12047, gtliteId("coffee"))
                .liquid()
                .color(0x36312E)
                .build()

            // 12048 Butter
            Butter = Material.Builder(12048, gtliteId("butter"))
                .liquid()
                .color(0xFFEF82)
                .build()

            // 12049 Purple Drink
            PurpleDrink = Material.Builder(12049, gtliteId("purple_drink"))
                .liquid()
                .color(0xB405FF)
                .build()

            // 12050 Carbon 5 Fraction
            Carbon5Fraction = Material.Builder(12050, gtliteId("carbon_5_fraction"))
                .liquid()
                .color(0x9C8638)
                .flags(FLAMMABLE)
                .build()

            // 12051 Dimerized Carbon 5 Fraction
            DimerizedCarbon5Fraction = Material.Builder(12051, gtliteId("dimerized_carbon_5_fraction"))
                .liquid()
                .color(0x9C9538)
                .flags(FLAMMABLE)
                .build()

            // 12052 Olive Oil
            OliveOil = Material.Builder(12052, gtliteId("olive_oil"))
                .liquid()
                .color(0x969D56)
                .build()

            // 12053 Fat
            Fat = Material.Builder(12053, gtliteId("fat"))
                .liquid()
                .color(0xFFF200)
                .build()
                .setFormula("C57H110O6", true)

            // 12054 Mud
            Mud = Material.Builder(12054, gtliteId("mud"))
                .liquid()
                .color(0x2C2B01)
                .build()

            // 12055 Lemon Extract
            LemonExtract = Material.Builder(12055, gtliteId("lemon_extract"))
                .liquid()
                .color(0xFCE80A)
                .build()

            // 12056 Lime Extract
            LimeExtract = Material.Builder(12056, gtliteId("lime_extract"))
                .liquid()
                .color(0x85F218)
                .build()

            // 12057 Orange Extract
            OrangeExtract = Material.Builder(12057, gtliteId("orange_extract"))
                .liquid()
                .color(0xFF6100)
                .build()

            // 12058 Lemon-Lime Mixture
            LemonLimeMixture = Material.Builder(12058, gtliteId("lemon_lime_mixture"))
                .liquid()
                .color(0xBDDB5A)
                .build()

            // 12059 Lemon-Lime Soda Syrup
            LemonLimeSodaSyrup = Material.Builder(12059, gtliteId("lemon_lime_soda_syrup"))
                .liquid()
                .color(0x76FF0D)
                .build()

            // 12060 Etirps™ (Sprite)
            Etirps = Material.Builder(12060, gtliteId("etirps"))
                .liquid()
                .color(0xB0FF73)
                .build()

            // 12061 Cranberry Extract
            CranberryExtract = Material.Builder(12061, gtliteId("cranberry_extract"))
                .liquid()
                .color(0x8C0D22)
                .build()

            // 12062 Cranberry Soda Syrup
            CranberrySodaSyrup = Material.Builder(12062, gtliteId("cranberry_soda_syrup"))
                .liquid()
                .color(0x8C0D22)
                .build()

            // 12063 Cranberry Etirps™ (Sprite)
            CranberryEtirps = Material.Builder(12063, gtliteId("cranberry_etirps"))
                .liquid()
                .color(averageRGB(2, CranberryExtract.materialRGB, 0xBBDDBB))
                .build()

            // 12064 Cough Syrup
            CoughSyrup = Material.Builder(12064, gtliteId("cough_syrup"))
                .liquid()
                .color(0x5C1B5E)
                .build()

            // 12065 Apple Syrup
            AppleSyrup = Material.Builder(12065, gtliteId("apple_syrup"))
                .liquid()
                .color(0xF2E1AC)
                .build()

            // 12065 Cane Syrup
            CaneSyrup = Material.Builder(12066, gtliteId("cane_syrup"))
                .liquid()
                .color(0xF2F1DC)
                .build()

            // 12066 Apple-Cane Syrup
            AppleCaneSyrup = Material.Builder(12067, gtliteId("apple_cane_syrup"))
                .liquid()
                .color(0xE7F5AE)
                .build()

            // 12067 Hard Apple Candy Syrup
            HardAppleCandySyrup = Material.Builder(12068, gtliteId("hard_apple_candy_syrup"))
                .liquid()
                .color(0x78E32B)
                .build()

            // 12068-12100 for misc unknown composition materials.
            // ...

            // 12101 Free Electron Gas
            FreeElectronGas = Material.Builder(12101, gtliteId("free_electron_gas"))
                .gas(FluidBuilder()
                    .temperature(50)
                    .translation("gregtech.fluid.generic"))
                .color(0x507BB3)
                .build()

            // 12102 Fermionic UU Matter
            FermionicUUMatter = Material.Builder(12102, gtliteId("fermionic_uu_matter"))
                .liquid(FluidBuilder().temperature(125))
                .color(UUMatter.materialRGB / 3)
                .build()

            // 12103 Bosonic UU Matter
            BosonicUUMatter = Material.Builder(12103, gtliteId("bosonic_uu_matter"))
                .liquid(FluidBuilder().temperature(125))
                .color(UUMatter.materialRGB - FermionicUUMatter.materialRGB)
                .build()

            // 12104 Rich Nitrogen Mixture
            RichNitrogenMixture = Material.Builder(12104, gtliteId("rich_nitrogen_mixture"))
                .gas(FluidBuilder().temperature(400))
                .color(0x6891D8)
                .build()

            // 12105 Rich Ammonia Mixture
            RichAmmoniaMixture = Material.Builder(12105, gtliteId("rich_ammonia_mixture"))
                .gas(FluidBuilder().temperature(400))
                .color(0x708ACD)
                .build()

            // 12106 Sea Water
            SeaWater = Material.Builder(12106, gtliteId("sea_water"))
                .liquid()
                .color(0x0000FF)
                .build()
                .setFormula("H2O?", true)

            // 12107 Acidic Salt Water
            AcidicSaltWater = Material.Builder(12107, gtliteId("acidic_salt_water"))
                .liquid()
                .color(averageRGB(2, SaltWater.materialRGB, SulfuricAcid.materialRGB))
                .build()
                .setFormula("(H2O)(H2SO4)?", true)

            // 12108 Chalcogen Anode Mud
            ChalcogenAnodeMud = Material.Builder(12108, gtliteId("chalcogen_anode_mud"))
                .dust()
                .color(0x8A3324)
                .iconSet(FINE)
                .build()

            // 12109 Rare Earth Hydroxides Solution
            RareEarthHydroxidesSolution = Material.Builder(12109, gtliteId("rare_earth_hydroxides_solution"))
                .liquid()
                .color(0x434327)
                .build()

            // 12110 Rare Earth Chlorides Solution
            RareEarthChloridesSolution = Material.Builder(12110, gtliteId("rare_earth_chlorides_solution"))
                .liquid()
                .color(0x838367)
                .build()

            // 12111 Brevibacterium Flavum
            BrevibacteriumFlavum = Material.Builder(12111, gtliteId("brevibacterium_flavum"))
                .dust()
                .color(0x766718).iconSet(ROUGH)
                .build()

            // 12112 Yeast
            Yeast = Material.Builder(12112, gtliteId("yeast"))
                .dust()
                .color(0xF0E660).iconSet(ROUGH)
                .build()

            // 12113 Cupriavidus Necator
            CupriavidusNecator = Material.Builder(12113, gtliteId("cupriavidus_necator"))
                .dust()
                .color(0x2C4D24).iconSet(ROUGH)
                .build()

            // 12114-12130 for other biological components.
            // ...

            // 12131 MethylamineMixture
            MethylamineMixture = Material.Builder(12131, gtliteId("methylamine_mixture"))
                .liquid()
                .color(0xAA4400)
                .build()

            // 12132 B-Z Medium
            BZMedium = Material.Builder(12132, gtliteId("bz_medium"))
                .liquid()
                .color(0xA2FD35)
                .build()

            // 12133 Low Purity Enriched Naquadah Emulsion
            LowPurityEnrichedNaquadahEmulsion = Material.Builder(12133, gtliteId("low_purity_enriched_naquadah_emulsion"))
                .liquid()
                .color(0x4C4C4C).iconSet(DULL)
                .build()

            // 12134 Low Purity Naquadria Emulsion
            LowPurityNaquadriaEmulsion = Material.Builder(12134, gtliteId("low_purity_naquadria_emulsion"))
                .liquid()
                .color(0x393939)
                .iconSet(DULL)
                .build()

            // 12135 Bedrock Smoke
            BedrockSmoke = Material.Builder(12135, gtliteId("bedrock_smoke"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x525252)
                .build()

            // 12136 Bedrock Soot Solution
            BedrockSootSolution = Material.Builder(12136, gtliteId("bedrock_soot_solution"))
                .liquid()
                .color(0x1E2430)
                .build()

            // 12137 Clean Bedrock Soot Solution
            CleanBedrockSootSolution = Material.Builder(12137, gtliteId("clean_bedrock_soot_solution"))
                .liquid()
                .color(0xA89F9E)
                .build()

            // 12138 Heavy Bedrock Smoke
            HeavyBedrockSmoke = Material.Builder(12138, gtliteId("heavy_bedrock_smoke"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x242222)
                .build()

            // 12139 Medium Bedrock Smoke
            MediumBedrockSmoke = Material.Builder(12139, gtliteId("medium_bedrock_smoke"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x2E2C2C)
                .build()

            // 12140 Light Bedrock Smoke
            LightBedrockSmoke = Material.Builder(12140, gtliteId("light_bedrock_smoke"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x363333)
                .build()

            // 12141 Ultralight Bedrock Smoke
            UltralightBedrockSmoke = Material.Builder(12141, gtliteId("ultralight_bedrock_smoke"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x403D3D)
                .build()

            // 12142 Heavy Taranium Gas
            HeavyTaraniumGas = Material.Builder(12142, gtliteId("heavy_taranium_gas"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x262626)
                .build()

            // 12143 Medium Taranium Gas
            MediumTaraniumGas = Material.Builder(12143, gtliteId("medium_taranium_gas"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x313131)
                .build()

            // 12144 Light Taranium Gas
            LightTaraniumGas = Material.Builder(12144, gtliteId("light_taranium_gas"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x404040)
                .build()

            // 12145 Bedrock Gas
            BedrockGas = Material.Builder(12145, gtliteId("bedrock_gas"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x575757)
                .build()

            // 12146 Crude Naquadah Fuel
            CrudeNaquadahFuel = Material.Builder(12146, gtliteId("crude_naquadah_fuel"))
                .liquid()
                .color(0x077F4E)
                .build()

            // 12147 Heavy Naquadah Fuel
            HeavyNaquadahFuel = Material.Builder(12147, gtliteId("heavy_naquadah_fuel"))
                .liquid()
                .color(0x088C56)
                .build()

            // 12148 Medium Naquadah Fuel
            MediumNaquadahFuel = Material.Builder(12148, gtliteId("medium_naquadah_fuel"))
                .liquid()
                .color(0x09A566)
                .build()

            // 12149 Light Naquadah Fuel
            LightNaquadahFuel = Material.Builder(12149, gtliteId("light_naquadah_fuel"))
                .liquid()
                .color(0x0BBF75)
                .build()

            // 12150 Naquadah Gas
            NaquadahGas = Material.Builder(12150, gtliteId("naquadah_gas"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x0CD985)
                .build()

            // 12151 Cracked Heavy Taranium Gas
            CrackedHeavyTaraniumGas = Material.Builder(12151, gtliteId("cracked_heavy_taranium_gas"))
                .liquid()
                .color(0x1F2B2E)
                .build()

            // 12152 Cracked Medium Taranium Gas
            CrackedMediumTaraniumGas = Material.Builder(12152, gtliteId("cracked_medium_taranium_gas"))
                .liquid()
                .color(0x29393D)
                .build()

            // 12153 Cracked Light Taranium Gas
            CrackedLightTaraniumGas = Material.Builder(12153, gtliteId("cracked_light_taranium_gas"))
                .liquid()
                .color(0x374C52)
                .build()

            // 12154 Heavy Taranium Fuel
            HeavyTaraniumFuel = Material.Builder(12154, gtliteId("heavy_taranium_fuel"))
                .liquid()
                .color(0x141414)
                .build()

            // 12155 Medium Taranium Fuel
            MediumTaraniumFuel = Material.Builder(12155, gtliteId("medium_taranium_fuel"))
                .liquid()
                .color(0x181818)
                .build()

            // 12156 Light Taranium Fuel
            LightTaraniumFuel = Material.Builder(12156, gtliteId("light_taranium_fuel"))
                .liquid()
                .color(0x1C1C1C)
                .build()

            // 12157 Enriched Bedrock Soot Solution
            EnrichedBedrockSootSolution = Material.Builder(12157, gtliteId("enriched_bedrock_soot_solution"))
                .liquid()
                .color(0x280C26)
                .build()

            // 12158 Clean Enriched Bedrock Soot Solution
            CleanEnrichedBedrockSootSolution = Material.Builder(12158, gtliteId("clean_enriched_bedrock_soot_solution"))
                .liquid()
                .color(0x828C8C)
                .build()

            // 12159 Heavy Enriched Bedrock Smoke
            HeavyEnrichedBedrockSmoke = Material.Builder(12159, gtliteId("heavy_enriched_bedrock_smoke"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x1A2222)
                .build()

            // 12160 Medium Enriched Bedrock Smoke
            MediumEnrichedBedrockSmoke = Material.Builder(12160, gtliteId("medium_enriched_bedrock_smoke"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x1E2C2C)
                .build()

            // 12161 Light Enriched Bedrock Smoke
            LightEnrichedBedrockSmoke = Material.Builder(12161, gtliteId("light_enriched_bedrock_smoke"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x163333)
                .build()

            // 12162 Heavy Enriched Taranium Gas
            HeavyEnrichedTaraniumGas = Material.Builder(12162, gtliteId("heavy_enriched_taranium_gas"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x1F2626)
                .build()

            // 12163 Medium Enriched Taranium Gas
            MediumEnrichedTaraniumGas = Material.Builder(12163, gtliteId("medium_enriched_taranium_gas"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x1F3131)
                .build()

            // 12164 Light Enriched Taranium Gas
            LightEnrichedTaraniumGas = Material.Builder(12164, gtliteId("light_enriched_taranium_gas"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x1F4040)
                .build()

            // 12165 Cracked Heavy Enriched Taranium Gas
            CrackedHeavyEnrichedTaraniumGas = Material.Builder(12165, gtliteId("cracked_heavy_enriched_taranium_gas"))
                .liquid()
                .color(0x2E1F2E)
                .build()

            // 12166 Cracked Medium Enriched Taranium Gas
            CrackedMediumEnrichedTaraniumGas = Material.Builder(12166, gtliteId("cracked_medium_enriched_taranium_gas"))
                .liquid()
                .color(0x29393D)
                .build()

            // 12167 Cracked Light Enriched Taranium Gas
            CrackedLightEnrichedTaraniumGas = Material.Builder(12167, gtliteId("cracked_light_enriched_taranium_gas"))
                .liquid()
                .color(0x374C52)
                .build()

            // 12168 Heavy Enriched Taranium Fuel
            HeavyEnrichedTaraniumFuel = Material.Builder(12168, gtliteId("heavy_enriched_taranium_fuel"))
                .liquid()
                .color(0x0F1414)
                .build()

            // 12169 Medium Enriched Taranium Fuel
            MediumEnrichedTaraniumFuel = Material.Builder(12169, gtliteId("medium_enriched_taranium_fuel"))
                .liquid()
                .color(0x0F1818)
                .build()

            // 12170 Light Enriched Taranium Fuel
            LightEnrichedTaraniumFuel = Material.Builder(12170, gtliteId("light_enriched_taranium_fuel"))
                .liquid()
                .color(0x0F1C1C)
                .build()

            // 12171 Energetic Naquadria
            NaquadriaEnergetic = Material.Builder(12171, gtliteId("energetic_naquadria"))
                .liquid()
                .color(0x202020)
                .build()

            // 12172 Quasi-fissioning plasma
            QuasifissioningPlasma = Material.Builder(12172, gtliteId("quasi_fissioning_plasma"))
                .plasma()
                .color(0xB0A2C3).iconSet(DULL)
                .build()

            // 12173 Oganesson Breeding Base
            OganessonBreedingBase = Material.Builder(12173, gtliteId("oganesson_breeding_base"))
                .liquid()
                .color(0xA65A7F).iconSet(DULL)
                .build()

            // 12174 Unprocessed Nd:YAG Solution
            UnprocessedNdYAGSolution = Material.Builder(12174, gtliteId("unprocessed_nd_yag_solution"))
                .liquid()
                .color(0x6F20AF).iconSet(DULL)
                .build()
                .setFormula("Nd:YAG?", false)

            // 12175 Mutated Living Solder
            MutatedLivingSolder = Material.Builder(12175, gtliteId("mutated_living_solder"))
                .liquid(FluidBuilder()
                    .temperature(10525))
                .color(0x936D9B).iconSet(DULL)
                .build()

            // 12176 Sodio-Indene
            SodioIndene = Material.Builder(12176, gtliteId("sodio_indene"))
                .liquid()
                .color(0x1D1C24)
                .build()

            // 12177 Steam-cracked Sodio-Indene
            SteamCrackedSodioIndene = Material.Builder(12177, gtliteId("steam_cracked_sodio_indene"))
                .liquid(FluidBuilder().temperature(1105))
                .color(0x1C1A29)
                .build()

            // 12178 Phosphorene Solution
            PhosphoreneSolution = Material.Builder(12178, gtliteId("phosphorene_solution"))
                .liquid()
                .color(0x465966)
                .build()

            // 12179-12999 for misc materials.
            // ...

            // Materials for particles and QCD contents reference this:
            // Helmut Satz, The Thermodynamics of Quarks and Gluons
            // https://link.springer.com/chapter/10.1007/978-3-642-02286-9_1

            // 13001 Neutron-Proton Fermi Superfluid
            NeutronProtonFermiSuperfluid = Material.Builder(13001, gtliteId("neutron_proton_fermi_superfluid"))
                .plasma(FluidBuilder()
                    .temperature(100_000_000)
                    .translation("gregtech.fluid.generic")
                    .customStill())
                .build()

            // 13002 Heavy Lepton Mixture
            HeavyLeptonMixture = Material.Builder(13002, gtliteId("heavy_lepton_mixture"))
                .liquid(FluidBuilder()
                    .temperature(48_000_000)
                    .customStill())
                .build()

            // 13003 Quark-Gluon Plasma
            QuarkGluonPlasma = Material.Builder(13003, gtliteId("quark_gluon_plasma"))
                .plasma(FluidBuilder()
                    .translation("gregtech.fluid.generic")
                    .temperature(1_500_000_000) // In reality world, Q-G plasma has 15000000000~20000000000 MeV by theory, so we transform it as K.
                    .customStill())
                .build()

            // 13004 Heavy Quarks
            HeavyQuarks = Material.Builder(13004, gtliteId("heavy_quarks"))
                .liquid(FluidBuilder()
                    .temperature(1_800_000_000)
                    .customStill())
                .build()

            // 13005 Light Quarks
            LightQuarks = Material.Builder(13005, gtliteId("light_quarks"))
                .liquid(FluidBuilder()
                    .temperature(900_000_000)
                    .customStill())
                .build()

            // 13006 Gluons
            Gluons = Material.Builder(13006, gtliteId("gluons"))
                .gas(FluidBuilder()
                    .temperature(2_000_000_000)
                    .translation("gregtech.fluid.generic")
                    .customStill())
                .build()

            // 13007 Hadronic Resonant Gas
            HadronicResonantGas = Material.Builder(13007, gtliteId("hadronic_resonant_gas"))
                .gas(FluidBuilder()
                    .temperature(1_500_000_000)
                    .translation("gregtech.fluid.generic")
                    .customStill())
                .build()

            // 13008 Stable Baryonic Matter
            StableBaryonicMatter = Material.Builder(13008, gtliteId("stable_baryonic_matter"))
                .plasma(FluidBuilder()
                    .temperature(1_200_000_000)
                    .translation("gregtech.fluid.generic")
                    .customStill())
                .build()

            // 13009 High Energy Quark-Gluon Plasma
            HighEnergyQuarkGluonPlasma = Material.Builder(13009, gtliteId("high_energy_quark_gluon_plasma"))
                .plasma(FluidBuilder()
                    .translation("gregtech.fluid.generic")
                    .temperature(2_000_000_000) // In reality world, Q-G plasma has 15000000000~20000000000 MeV by theory, so we transform it as K.
                    .customStill())
                .build()

            // ...

            // 13050 Dimensionally Shifted Superfluid
            DimensionallyShiftedSuperfluid = Material.Builder(13050, gtliteId("dimensionally_shifted_superfluid"))
                .plasma(FluidBuilder()
                    .temperature(2_000_000_000)
                    .translation("gregtech.fluid.generic")
                    .customStill())
                .build()

        }

    }

}