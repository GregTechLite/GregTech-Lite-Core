package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.V
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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.cableProp
import gregtechlite.gtlitecore.api.extension.colorAverage
import gregtechlite.gtlitecore.api.extension.gas
import gregtechlite.gtlitecore.api.extension.liquid
import gregtechlite.gtlitecore.api.extension.plasma
import gregtechlite.gtlitecore.api.extension.rotorProp
import gregtechlite.gtlitecore.api.extension.toolProp
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
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AxinoFusedRedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EternityPlusToken
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuasifissioningPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RainbowSap
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RareEarthChloridesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RareEarthHydroxidesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RawStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RealizedQuantumFoamShard
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
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.QUANTUM
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.REDMATTER
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.WAX
import net.minecraft.init.Enchantments

object GTLiteUnknownCompositionMaterials
{

    // @formatter:off

    fun init()
    {
        // 12001 Latex
        Latex = addMaterial(12001, "latex")
        {
            dust()
            liquid()
            color(0xFFFADA)
        }

        // 12002 Resin
        Resin = addMaterial(12002, "resin")
        {
            dust()
            liquid()
            color(0xB5803A)
            flags(FLAMMABLE)
        }

        // 12003 Rainbow Sap
        RainbowSap = addMaterial(12003, "rainbow_sap")
        {
            liquid()
            {
                customStill()
            }
        }

        // 12004-12020 for other sap liquids.
        // ...

        // 12021 Blood
        Blood = addMaterial(12021, "blood")
        {
            liquid()
            color(0x5C0606)
        }

        // 12022 Blood Cells
        BloodCells = addMaterial(12022, "blood_cells")
        {
            liquid()
            color(0xAD2424).iconSet(DULL)
        }

        // 12023 Blood Plasma
        BloodPlasma = addMaterial(12023, "blood_plasma")
        {
            liquid()
            color(0xE37171).iconSet(DULL)
        }

        // 12024 bFGF
        BFGF = addMaterial(12024, "bfgf")
        {
            liquid()
            color(0xB365E0)
        }

        // 12025 EGF
        // Chemical formula is C257H381N73O83S7 in reality world ^^.
        EGF = addMaterial(12025, "egf")
        {
            liquid()
            color(0x815799)
        }

        // 12026 CAT
        CAT = addMaterial(12026, "cat")
        {
            liquid()
            color(0xDB6596)
        }

        // 12027 Kerogen
        Kerogen = addMaterial(12027, "kerogen")
        {
            liquid()
            {
                temperature(302)
            }
            color(0xA7A7A7).iconSet(DULL)
        }

        // 12028 Paraffin
        Paraffin = addMaterial(12028, "paraffin")
        {
            dust(0, 40 * SECOND)
            color(0xD2D2FA).iconSet(WAX)
            flags(FLAMMABLE)
        }

        // 12029 Bitumen
        Bitumen = addMaterial(12029, "bitumen")
        {
            dust()
            color(0x2D2D05).iconSet(WAX)
        }

        // 12030 for other misc biological materials
        // ...

        // 12031 Green Sapphire Juice
        GreenSapphireJuice = addMaterial(12031, "green_sapphire_juice")
        {
            liquid()
            color(GreenSapphire.materialRGB)
            components(GreenSapphire.materialComponents)
            flags(DISABLE_DECOMPOSITION)
        }

        // 12032 Sapphire Juice
        SapphireJuice = addMaterial(12032, "sapphire_juice")
        {
            liquid()
            color(Sapphire.materialRGB)
            components(Sapphire.materialComponents)
            flags(DISABLE_DECOMPOSITION)
        }

        // 12033 Ruby Juice
        RubyJuice = addMaterial(12033, "ruby_juice")
        {
            liquid()
            color(Ruby.materialRGB)
            components(Ruby.materialComponents)
            flags(DISABLE_DECOMPOSITION)
        }

        // 12034-12039 for other gem slurries and juices.
        // ...

        // 12040 Greenhouse Gas
        GreenhouseGas = addMaterial(12040, "greenhouse_gas")
        {
            gas()
            {
                temperature(313)
            }
        }

        // 12041 Grape Juice
        GrapeJuice = addMaterial(12041, "grape_juice")
        {
            liquid()
            color(0x50C039)
        }

        // 12042 Red Wine
        RedWine = addMaterial(12042, "red_wine")
        {
            liquid()
            color(0x7D0B07)
        }

        // 12043 Vinegar
        Vinegar = addMaterial(12043, "vinegar")
        {
            liquid()
            color(0x5E0805)
        }

        // 12044 Potato Juice
        PotatoJuice = addMaterial(12044, "potato_juice")
        {
            liquid()
            color(0xC3A92C)
        }

        // 12045 Vodka
        Vodka = addMaterial(12045, "vodka")
        {
            liquid()
            colorAverage(PotatoJuice, Water)
        }

        // 12046 Polenta
        Polenta = addMaterial(12046, "polenta")
        {
            liquid()
            color(0xBBA844)
        }

        // 12047 Coffee
        Coffee = addMaterial(12047, "coffee")
        {
            liquid()
            color(0x36312E)
        }

        // 12048 Butter
        Butter = addMaterial(12048, "butter")
        {
            liquid()
            color(0xFFEF82)
        }

        // 12049 Purple Drink
        PurpleDrink = addMaterial(12049, "purple_drink")
        {
            liquid()
            color(0xB405FF)
        }

        // 12050 Carbon 5 Fraction
        Carbon5Fraction = addMaterial(12050, "carbon_5_fraction")
        {
            liquid()
            color(0x9C8638)
            flags(FLAMMABLE)
        }

        // 12051 Dimerized Carbon 5 Fraction
        DimerizedCarbon5Fraction = addMaterial(12051, "dimerized_carbon_5_fraction")
        {
            liquid()
            color(0x9C9538)
            flags(FLAMMABLE)
        }

        // 12052 Olive Oil
        OliveOil = addMaterial(12052, "olive_oil")
        {
            liquid()
            color(0x969D56)
        }

        // 12053 Fat
        Fat = addMaterial(12053, "fat")
        {
            liquid()
            color(0xFFF200)
        }

        // 12054 Mud
        Mud = addMaterial(12054, "mud")
        {
            liquid()
            color(0x2C2B01)
        }

        // 12055 Lemon Extract
        LemonExtract = addMaterial(12055, "lemon_extract")
        {
            liquid()
            color(0xFCE80A)
        }

        // 12056 Lime Extract
        LimeExtract = addMaterial(12056, "lime_extract")
        {
            liquid()
            color(0x85F218)
        }

        // 12057 Orange Extract
        OrangeExtract = addMaterial(12057, "orange_extract")
        {
            liquid()
            color(0xFF6100)
        }

        // 12058 Lemon-Lime Mixture
        LemonLimeMixture = addMaterial(12058, "lemon_lime_mixture")
        {
            liquid()
            color(0xBDDB5A)
        }

        // 12059 Lemon-Lime Soda Syrup
        LemonLimeSodaSyrup = addMaterial(12059, "lemon_lime_soda_syrup")
        {
            liquid()
            color(0x76FF0D)
        }

        // 12060 Etirps™ (Sprite)
        Etirps = addMaterial(12060, "etirps")
        {
            liquid()
            color(0xB0FF73)
        }

        // 12061 Cranberry Extract
        CranberryExtract = addMaterial(12061, "cranberry_extract")
        {
            liquid()
            color(0x8C0D22)
        }

        // 12062 Cranberry Soda Syrup
        CranberrySodaSyrup = addMaterial(12062, "cranberry_soda_syrup")
        {
            liquid()
            color(0x8C0D22)
        }

        // 12063 Cranberry Etirps™ (Sprite)
        CranberryEtirps = addMaterial(12063, "cranberry_etirps")
        {
            liquid()
            colorAverage(CranberryExtract.materialRGB, 0xBBDDBB)
        }

        // 12064 Cough Syrup
        CoughSyrup = addMaterial(12064, "cough_syrup")
        {
            liquid()
            color(0x5C1B5E)
        }

        // 12065 Apple Syrup
        AppleSyrup = addMaterial(12065, "apple_syrup")
        {
            liquid()
            color(0xF2E1AC)
        }

        // 12065 Cane Syrup
        CaneSyrup = addMaterial(12066, "cane_syrup")
        {
            liquid()
            color(0xF2F1DC)
        }

        // 12066 Apple-Cane Syrup
        AppleCaneSyrup = addMaterial(12067, "apple_cane_syrup")
        {
            liquid()
            color(0xE7F5AE)
        }

        // 12067 Hard Apple Candy Syrup
        HardAppleCandySyrup = addMaterial(12068, "hard_apple_candy_syrup")
        {
            liquid()
            color(0x78E32B)
        }

        // 12068 Fracturing Fluid
        FracturingFluid = addMaterial(12069, "fracturing_fluid")
        {
            liquid()
            color(0x96D6D5)
        }

        // 12069-12100 for misc unknown composition materials.
        // ...

        // 12101 Free Electron Gas
        FreeElectronGas = addMaterial(12101, "free_electron_gas")
        {
            gas()
            {
                temperature(50)
                translation("gregtech.fluid.generic")
            }
            .color(0x507BB3)
        }

        // 12102 Fermionic UU Matter
        FermionicUUMatter = addMaterial(12102, "fermionic_uu_matter")
        {
            liquid()
            {
                temperature(125)
            }
            color(UUMatter.materialRGB / 3)
        }

        // 12103 Bosonic UU Matter
        BosonicUUMatter = addMaterial(12103, "bosonic_uu_matter")
        {
            liquid()
            {
                temperature(125)
            }
            color(UUMatter.materialRGB - FermionicUUMatter.materialRGB)
        }

        // 12104 Rich Nitrogen Mixture
        RichNitrogenMixture = addMaterial(12104, "rich_nitrogen_mixture")
        {
            gas()
            {
                temperature(400)
            }
            color(0x6891D8)
        }

        // 12105 Rich Ammonia Mixture
        RichAmmoniaMixture = addMaterial(12105, "rich_ammonia_mixture")
        {
            gas()
            {
                temperature(400)
            }
            color(0x708ACD)
        }

        // 12106 Sea Water
        SeaWater = addMaterial(12106, "sea_water")
        {
            liquid()
            color(0x0000FF)
        }

        // 12107 Acidic Salt Water
        AcidicSaltWater = addMaterial(12107, "acidic_salt_water")
        {
            liquid()
            colorAverage(SaltWater, SulfuricAcid)
        }

        // 12108 Chalcogen Anode Mud
        ChalcogenAnodeMud = addMaterial(12108, "chalcogen_anode_mud")
        {
            dust()
            color(0x8A3324).iconSet(FINE)
        }

        // 12109 Rare Earth Hydroxides Solution
        RareEarthHydroxidesSolution = addMaterial(12109, "rare_earth_hydroxides_solution")
        {
            liquid()
            color(0x434327)
        }

        // 12110 Rare Earth Chlorides Solution
        RareEarthChloridesSolution = addMaterial(12110, "rare_earth_chlorides_solution")
        {
            liquid()
            color(0x838367)
        }

        // 12111 Brevibacterium Flavum
        BrevibacteriumFlavum = addMaterial(12111, "brevibacterium_flavum")
        {
            dust()
            color(0x766718).iconSet(ROUGH)
        }

        // 12112 Yeast
        Yeast = addMaterial(12112, "yeast")
        {
            dust()
            color(0xF0E660).iconSet(ROUGH)
        }

        // 12113 Cupriavidus Necator
        CupriavidusNecator = addMaterial(12113, "cupriavidus_necator")
        {
            dust()
            color(0x2C4D24).iconSet(ROUGH)
        }

        // 12114 Streptococcus Pyogenes
        StreptococcusPyogenes = addMaterial(12114, "streptococcus_pyogenes")
        {
            dust()
            color(0x999933).iconSet(ROUGH)
        }

        // 12115 Escherichia Coli
        EscherichiaColi = addMaterial(12115, "escherichia_coli")
        {
            dust()
            color(0x398C47).iconSet(DULL)
        }

        // 12116 Green Algae
        GreenAlgae = addMaterial(12116, "green_algae")
        {
            dust()
            color(0x228B22).iconSet(METALLIC)
        }

        // 12117 Brown Algae
        BrownAlgae = addMaterial(12117, "brown_algae")
        {
            dust()
            color(0xA52A2A).iconSet(METALLIC)
        }

        // 12118 Red Algae
        RedAlgae = addMaterial(12118, "red_algae")
        {
            dust()
            color(0xF08080).iconSet(METALLIC)
        }

        // 12119 Algae Mixture
        AlgaeMixture = addMaterial(12119, "algae_mixture")
        {
            liquid()
            colorAverage(GreenAlgae, BrownAlgae, RedAlgae).iconSet(DULL)
        }

        // 12115-12130 for other biological components.
        // ...

        // 12131 MethylamineMixture
        MethylamineMixture = addMaterial(12131, "methylamine_mixture")
        {
            liquid()
            color(0xAA4400)
        }

        // 12132 B-Z Medium
        BZMedium = addMaterial(12132, "bz_medium")
        {
            liquid()
            color(0xA2FD35)
        }

        // 12133 Low Purity Enriched Naquadah Emulsion
        LowPurityEnrichedNaquadahEmulsion = addMaterial(12133, "low_purity_enriched_naquadah_emulsion")
        {
            liquid()
            color(0x4C4C4C).iconSet(DULL)
        }

        // 12134 Low Purity Naquadria Emulsion
        LowPurityNaquadriaEmulsion = addMaterial(12134, "low_purity_naquadria_emulsion")
        {
            liquid()
            color(0x393939).iconSet(DULL)
        }

        // 12135 Bedrock Smoke
        BedrockSmoke = addMaterial(12135, "bedrock_smoke")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x525252)
        }

        // 12136 Bedrock Soot Solution
        BedrockSootSolution = addMaterial(12136, "bedrock_soot_solution")
        {
            liquid()
            color(0x1E2430)
        }

        // 12137 Clean Bedrock Soot Solution
        CleanBedrockSootSolution = addMaterial(12137, "clean_bedrock_soot_solution")
        {
            liquid()
            color(0xA89F9E)
        }

        // 12138 Heavy Bedrock Smoke
        HeavyBedrockSmoke = addMaterial(12138, "heavy_bedrock_smoke")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x242222)
        }

        // 12139 Medium Bedrock Smoke
        MediumBedrockSmoke = addMaterial(12139, "medium_bedrock_smoke")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x2E2C2C)
        }

        // 12140 Light Bedrock Smoke
        LightBedrockSmoke = addMaterial(12140, "light_bedrock_smoke")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x363333)
        }

        // 12141 Ultralight Bedrock Smoke
        UltralightBedrockSmoke = addMaterial(12141, "ultralight_bedrock_smoke")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x403D3D)
        }

        // 12142 Heavy Taranium Gas
        HeavyTaraniumGas = addMaterial(12142, "heavy_taranium_gas")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x262626)
        }

        // 12143 Medium Taranium Gas
        MediumTaraniumGas = addMaterial(12143, "medium_taranium_gas")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x313131)
        }

        // 12144 Light Taranium Gas
        LightTaraniumGas = addMaterial(12144, "light_taranium_gas")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x404040)
        }

        // 12145 Bedrock Gas
        BedrockGas = addMaterial(12145, "bedrock_gas")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x575757)
        }

        // 12146 Crude Naquadah Fuel
        CrudeNaquadahFuel = addMaterial(12146, "crude_naquadah_fuel")
        {
            liquid()
            color(0x077F4E)
        }

        // 12147 Heavy Naquadah Fuel
        HeavyNaquadahFuel = addMaterial(12147, "heavy_naquadah_fuel")
        {
            liquid()
            color(0x088C56)
        }

        // 12148 Medium Naquadah Fuel
        MediumNaquadahFuel = addMaterial(12148, "medium_naquadah_fuel")
        {
            liquid()
            color(0x09A566)
        }

        // 12149 Light Naquadah Fuel
        LightNaquadahFuel = addMaterial(12149, "light_naquadah_fuel")
        {
            liquid()
            color(0x0BBF75)
        }

        // 12150 Naquadah Gas
        NaquadahGas = addMaterial(12150, "naquadah_gas")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x0CD985)
        }

        // 12151 Cracked Heavy Taranium Gas
        CrackedHeavyTaraniumGas = addMaterial(12151, "cracked_heavy_taranium_gas")
        {
            liquid()
            color(0x1F2B2E)
        }

        // 12152 Cracked Medium Taranium Gas
        CrackedMediumTaraniumGas = addMaterial(12152, "cracked_medium_taranium_gas")
        {
            liquid()
            color(0x29393D)
        }

        // 12153 Cracked Light Taranium Gas
        CrackedLightTaraniumGas = addMaterial(12153, "cracked_light_taranium_gas")
        {
            liquid()
            color(0x374C52)
        }

        // 12154 Heavy Taranium Fuel
        HeavyTaraniumFuel = addMaterial(12154, "heavy_taranium_fuel")
        {
            liquid()
            color(0x141414)
        }

        // 12155 Medium Taranium Fuel
        MediumTaraniumFuel = addMaterial(12155, "medium_taranium_fuel")
        {
            liquid()
            color(0x181818)
        }

        // 12156 Light Taranium Fuel
        LightTaraniumFuel = addMaterial(12156, "light_taranium_fuel")
        {
            liquid()
            color(0x1C1C1C)
        }

        // 12157 Enriched Bedrock Soot Solution
        EnrichedBedrockSootSolution = addMaterial(12157, "enriched_bedrock_soot_solution")
        {
            liquid()
            color(0x280C26)
        }

        // 12158 Clean Enriched Bedrock Soot Solution
        CleanEnrichedBedrockSootSolution = addMaterial(12158, "clean_enriched_bedrock_soot_solution")
        {
            liquid()
            color(0x828C8C)
        }

        // 12159 Heavy Enriched Bedrock Smoke
        HeavyEnrichedBedrockSmoke = addMaterial(12159, "heavy_enriched_bedrock_smoke")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x1A2222)
        }

        // 12160 Medium Enriched Bedrock Smoke
        MediumEnrichedBedrockSmoke = addMaterial(12160, "medium_enriched_bedrock_smoke")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x1E2C2C)
        }

        // 12161 Light Enriched Bedrock Smoke
        LightEnrichedBedrockSmoke = addMaterial(12161, "light_enriched_bedrock_smoke")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x163333)
        }

        // 12162 Heavy Enriched Taranium Gas
        HeavyEnrichedTaraniumGas = addMaterial(12162, "heavy_enriched_taranium_gas")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x1F2626)
        }

        // 12163 Medium Enriched Taranium Gas
        MediumEnrichedTaraniumGas = addMaterial(12163, "medium_enriched_taranium_gas")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x1F3131)
        }

        // 12164 Light Enriched Taranium Gas
        LightEnrichedTaraniumGas = addMaterial(12164, "light_enriched_taranium_gas")
        {
            gas()
            {
                translation("gregtech.fluid.generic")
            }
            color(0x1F4040)
        }

        // 12165 Cracked Heavy Enriched Taranium Gas
        CrackedHeavyEnrichedTaraniumGas = addMaterial(12165, "cracked_heavy_enriched_taranium_gas")
        {
            liquid()
            color(0x2E1F2E)
        }

        // 12166 Cracked Medium Enriched Taranium Gas
        CrackedMediumEnrichedTaraniumGas = addMaterial(12166, "cracked_medium_enriched_taranium_gas")
        {
            liquid()
            color(0x29393D)
        }

        // 12167 Cracked Light Enriched Taranium Gas
        CrackedLightEnrichedTaraniumGas = addMaterial(12167, "cracked_light_enriched_taranium_gas")
        {
            liquid()
            color(0x374C52)
        }

        // 12168 Heavy Enriched Taranium Fuel
        HeavyEnrichedTaraniumFuel = addMaterial(12168, "heavy_enriched_taranium_fuel")
        {
            liquid()
            color(0x0F1414)
        }

        // 12169 Medium Enriched Taranium Fuel
        MediumEnrichedTaraniumFuel = addMaterial(12169, "medium_enriched_taranium_fuel")
        {
            liquid()
            color(0x0F1818)
        }

        // 12170 Light Enriched Taranium Fuel
        LightEnrichedTaraniumFuel = addMaterial(12170, "light_enriched_taranium_fuel")
        {
            liquid()
            color(0x0F1C1C)
        }

        // 12171 Energetic Naquadria
        NaquadriaEnergetic = addMaterial(12171, "energetic_naquadria")
        {
            liquid()
            color(0x202020)
        }

        // 12172 Quasi-fissioning plasma
        QuasifissioningPlasma = addMaterial(12172, "quasi_fissioning_plasma")
        {
            plasma()
            color(0xB0A2C3).iconSet(DULL)
        }

        // 12173 Oganesson Breeding Base
        OganessonBreedingBase = addMaterial(12173, "oganesson_breeding_base")
        {
            liquid()
            color(0xA65A7F).iconSet(DULL)
        }

        // 12174 Unprocessed Nd:YAG Solution
        UnprocessedNdYAGSolution = addMaterial(12174, "unprocessed_nd_yag_solution")
        {
            liquid()
            color(0x6F20AF).iconSet(DULL)
        }

        // 12175 Mutated Living Solder
        MutatedLivingSolder = addMaterial(12175, "mutated_living_solder")
        {
            liquid()
            {
                temperature(10525)
            }
            color(0x936D9B).iconSet(DULL)
        }

        // 12176 Sodio-Indene
        SodioIndene = addMaterial(12176, "sodio_indene")
        {
            liquid()
            color(0x1D1C24)
        }

        // 12177 Steam-cracked Sodio-Indene
        SteamCrackedSodioIndene = addMaterial(12177, "steam_cracked_sodio_indene")
        {
            liquid()
            {
                temperature(1105)
            }
            color(0x1C1A29)
        }

        // 12178 Phosphorene Solution
        PhosphoreneSolution = addMaterial(12178, "phosphorene_solution")
        {
            liquid()
            color(0x465966)
        }

        // 12179-12999 for misc materials.
        // ...

        // Materials for particles and QCD contents reference this:
        // Helmut Satz, The Thermodynamics of Quarks and Gluons
        // https://link.springer.com/chapter/10.1007/978-3-642-02286-9_1

        // 13001 Neutron-Proton Fermi Superfluid
        NeutronProtonFermiSuperfluid = addMaterial(13001, "neutron_proton_fermi_superfluid")
        {
            plasma()
            {
                temperature(100_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13002 Heavy Lepton Mixture
        HeavyLeptonMixture = addMaterial(13002, "heavy_lepton_mixture")
        {
            liquid()
            {
                temperature(48_000_000)
                customStill()
            }
        }

        // 13003 Quark-Gluon Plasma
        // In reality world, Q-G plasma has 15,000,000,000~20,000,000,000 MeV by theory, so we transform it as K.
        QuarkGluonPlasma = addMaterial(13003, "quark_gluon_plasma")
        {
            plasma()
            {
                translation("gregtech.fluid.generic")
                temperature(1_500_000_000)
                customStill()
            }
        }

        // 13004 Heavy Quarks
        HeavyQuarks = addMaterial(13004, "heavy_quarks")
        {
            liquid()
            {
                temperature(1_800_000_000)
                customStill()
            }
        }

        // 13005 Light Quarks
        LightQuarks = addMaterial(13005, "light_quarks")
        {
            liquid()
            {
                temperature(900_000_000)
                customStill()
            }
        }

        // 13006 Gluons
        Gluons = addMaterial(13006, "gluons")
        {
            gas()
            {
                temperature(2_000_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13007 Hadronic Resonant Gas
        HadronicResonantGas = addMaterial(13007, "hadronic_resonant_gas")
        {
            gas()
            {
                temperature(1_500_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13008 Stable Baryonic Matter
        StableBaryonicMatter = addMaterial(13008, "stable_baryonic_matter")
        {
            plasma()
            {
                temperature(1_200_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13009 High Energy Quark-Gluon Plasma
        HighEnergyQuarkGluonPlasma = addMaterial(13009, "high_energy_quark_gluon_plasma")
        {
            plasma()
            {
                translation("gregtech.fluid.generic")
                temperature(2_000_000_000)
                customStill()
            }
        }

        // 13010 Resonant Strange Meson
        ResonantStrangeMeson = addMaterial(13010, "resonant_strange_meson")
        {
            plasma()
            {
                temperature(1_600_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13011 Protomatter
        Protomatter = addMaterial(13011, "protomatter")
        {
            plasma()
            {
                temperature(1_000_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13012 Semistable Antimatter
        SemistableAntimatter = addMaterial(13012, "semistable_antimatter")
        {
            plasma()
            {
                temperature(1_500_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13013 Antimatter
        Antimatter = addMaterial(13013, "antimatter")
        {
            plasma()
            {
                temperature(2_000_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13014 Spatially Enlarged Fluid
        SpatiallyEnlargedFluid = addMaterial(13014, "spatially_enlarged_fluid")
        {
            liquid()
            {
                temperature(1)
                customStill()
            }
        }

        // 13015 Tachyon Rich Temporal Fluid
        TachyonRichTemporalFluid = addMaterial(13015, "tachyon_rich_temporal_fluid")
        {
            liquid()
            {
                temperature(1)
                customStill()
            }
        }

        // 13016 Primordial Matter
        PrimordialMatter = addMaterial(13016, "primordial_matter")
        {
            liquid()
            {
                temperature(2_000_000_000)
                customStill()
            }
        }

        // 13017 Condensed Raw Stellar Plasma Mixture
        RawStarMatter = addMaterial(13017, "condensed_raw_stellar_plasma_mixture")
        {
            plasma()
            {
                temperature(2_000_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // 13018 Magnetohydrodynamically Constrained Star Matter (MHDCSM)
        MagnetohydrodynamicallyConstrainedStarMatter = addMaterial(13018, "magnetohydrodynamically_constrained_star_matter")
        {
            ingot()
            plasma()
            {
                temperature(2_000_000_000)
            }
            iconSet(MHDCSM)
            flags(EXT2_METAL, NO_UNIFICATION, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_ROTOR,
                  GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_SPRING, GENERATE_SPRING_SMALL,
                  GENERATE_FINE_WIRE)
        }

        // 13019 Self-Interacting Dark Matter (SIDM)
        SelfInteractingDarkMatter = addMaterial(13019, "self_interacting_dark_matter")
        {
            ingot()
            liquid()
            iconSet(DARKMATTER)
            flags(EXT2_METAL, GENERATE_FRAME, GENERATE_FOIL, GENERATE_FINE_WIRE)
            cableProp(V[MAX] - 1, 488, 1)
        }

        // 13020 Realized Quantum Foam Shard (RQFS)
        RealizedQuantumFoamShard = addMaterial(13020, "realized_quantum_foam_shard")
        {
            ingot()
            liquid()
            iconSet(QUANTUM) // lightness 80 on base; shadow
            flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_FRAME, GENERATE_SPRING, GENERATE_SPRING_SMALL)
            cableProp(V[MAX] - 1, 365, 1)
        }

        // 13021 Axino-Fused Red Matter (AFRM)
        AxinoFusedRedMatter = addMaterial(13021, "axino_fused_red_matter")
        {
            ingot()
            liquid()
            color(0x9A0707).iconSet(REDMATTER)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND,
                  GENERATE_ROTOR, GENERATE_FRAME, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FOIL, GENERATE_FINE_WIRE)
            toolProp(640.0F, 640.0F, Int.MAX_VALUE - 1, 1_048_576)
            {
                attackSpeed(9.0F)
                enchantability(99)
                enchantment(Enchantments.SHARPNESS, 128)
                enchantment(Enchantments.SWEEPING, 128)
                enchantment(Enchantments.LOOTING, 128)
                enchantment(Enchantments.EFFICIENCY, 128)
                enchantment(Enchantments.FORTUNE, 128)
                enchantment(Enchantments.MENDING, 128)
                enchantment(Enchantments.UNBREAKING, 128)
                magnetic()
                unbreakable()
            }
            rotorProp(8192.0F, 2048.0F, Int.MAX_VALUE - 1)
        }

        // ...

        // 13050 Dimensionally Shifted Superfluid
        DimensionallyShiftedSuperfluid = addMaterial(13050, "dimensionally_shifted_superfluid")
        {
            plasma()
            {
                temperature(2_000_000_000)
                translation("gregtech.fluid.generic")
                customStill()
            }
        }

        // ...

        // 14000 Eternity+ (Token)
        EternityPlusToken = addMaterial(14000, "eternity_plus_token") {}
    }

    // @formatter:on

}