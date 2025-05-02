package magicbook.gtlitecore.api.unification.material

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.FluidBuilder
import gregtech.api.fluids.attribute.FluidAttributes
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Butene
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Dimethylhydrazine
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.STD_METAL
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tetranitromethane
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING
import gregtech.api.unification.material.info.MaterialIconSet.BRIGHT
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.FINE
import gregtech.api.unification.material.info.MaterialIconSet.METALLIC
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtech.api.unification.material.info.MaterialIconSet.SHINY
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AceticAnhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AcetoneCyanohydrin
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AcetylChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Acetylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AminooxyaceticAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aminophenol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumCyanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aniline
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BETS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BenzylBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BenzyltrimethylammoniumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Biotin
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BiphenylTetracarboxylicAcidDianhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bistrichloromethylbenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Borazine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BromoBromomethylNaphthalene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bromobutane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bromodihydrothiine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bromomethane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butanediol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butanol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butyllithium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Carbamide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChloroaceticAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Chlorobutane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CitricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DenseHydrazineRocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Diacetyl
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DiaminostilbenedisulfonicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Diaminotoluene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Diborane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dibromoacrolein
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dibromomethylbenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dichloroethane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dichloromethane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dicyclopentadiene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DiethylEther
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DiethylhexylPhosphoricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Difluorobenzophenone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimethylTerephthalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dimethylacetamide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dinitrodipropanyloxybenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dinitrotoluene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Durene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EDTA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Edot
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Erythrosine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ethylanthrahydroquinone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ethylanthraquinone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthyleneDibromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthyleneGlycol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthyleneOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ethylenediamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ethylhexanol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorescein
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FluorinatedEthylenePropylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorobenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorotoluene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Formaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FormicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fructose
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fullerene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GammaButyrolactone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GeodesicPolyarene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Glucose
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Glutamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GrignardReagent
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HRAMagnesium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hexafluoropropylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydrazine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenCyanide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydroquinone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydroxyquinoline
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Indanone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Indene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Isochloropropane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.KaptonE
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.KaptonK
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LinoleicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MalonicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MethylFormate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methylamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methylhydrazine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MethylhydrazineNitrateRocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methylparatoluate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NMethylPyrrolidone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nitroaniline
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nitrotoluene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OctafluoroPentanol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OxalicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Oxydianiline
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ParaPhenylenediamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ParaToluicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ParaXylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PedotPSS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PedotTMA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pentane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phosgene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhosphonitrilicChlorideTrimer
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhosphorylChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhthalicAnhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polyetheretherketone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyethyleneTerephthalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polyisoprene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polymethylmethacrylate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyphosphonitrileFluoroRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polystyrene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolystyreneSulfonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polytetrahydrofuran
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolytetramethyleneGlycolRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PretreatedZylon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PyromelliticDianhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RawPolyphosphonitrileFluoroRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RawPolytetramethyleneGlycolRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resorcinol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuccinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Terephthalaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TerephthalicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TerephthaloylChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TertbutylAlcohol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tetrabromoethane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tetrahydrofuran
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetrasodiumEDTA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThionylChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TolueneDiisocyanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TolueneTetramethylDiisocyanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tributylamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trichloroborazine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trichloroethylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylaluminium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylgallium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Truxene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zylon
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.gtliteId

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteOrganicChemistryMaterials
{

    companion object
    {

        fun init()
        {
            // 8001 Dicyclopentadiene
            Dicyclopentadiene = Material.Builder(8001, gtliteId("dicyclopentadiene"))
                .liquid(FluidBuilder().temperature(306))
                .color(0x9C388B)
                .components(Carbon, 10, Hydrogen, 12)
                .build()

            // 8002 Pentane
            Pentane = Material.Builder(8002, gtliteId("pentane"))
                .liquid()
                .color(0xE8E7BE)
                .components(Carbon, 5, Hydrogen, 12)
                .build()

            // 8003 Polyisoprene
            Polyisoprene = Material.Builder(8003, gtliteId("polyisoprene"))
                .polymer()
                .liquid()
                .color(0x575757).iconSet(SHINY)
                .components(Carbon, 5, Hydrogen, 8)
                .flags(NO_SMASHING, NO_SMELTING, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL)
                .build()

            // 8004 Para Xylene
            ParaXylene = Material.Builder(8004, gtliteId("para_xylene"))
                .liquid()
                .color(0x666040)
                .components(Carbon, 8, Hydrogen, 10)
                .build()
                .setFormula("C6H4(CH3)2", true)

            // 8005 Nitrotoluene
            Nitrotoluene = Material.Builder(8005, gtliteId("nitrotoluene"))
                .liquid()
                .color(0x99236E)
                .components(Carbon, 7, Hydrogen, 7, Nitrogen, 1, Oxygen, 2)
                .build()
                .setFormula("C6H4CH3NO2", true)

            // 8006 Diaminostilbenedisulfonic Acid (DSDA)
            DiaminostilbenedisulfonicAcid = Material.Builder(8006, gtliteId("diaminostilbenedisulfonic_acid"))
                .dust()
                .color(0xF2F2F2).iconSet(ROUGH)
                .components(Carbon, 14, Hydrogen, 14, Nitrogen, 2, Oxygen, 6, Sulfur, 2)
                .build()

            // 8007 Succinic Acid
            SuccinicAcid = Material.Builder(8007, gtliteId("succinic_acid"))
                .dust()
                .color(0x0C3A5B).iconSet(DULL)
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 4)
                .build()

            // 8008 Butanediol
            Butanediol = Material.Builder(8008, gtliteId("butanediol"))
                .liquid()
                .color(0xAAC4DA)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION) // Re-added electrolysis reaction by PedotChain.
                .build()
                .setFormula("C4H8(OH)2", true)

            // 8009 Tetrahydrofuran
            Tetrahydrofuran = Material.Builder(8009, gtliteId("tetrahydrofuran"))
                .liquid()
                .color(0x0BCF52)
                .components(Carbon, 4, Hydrogen, 8, Oxygen, 1)
                .build()
                .setFormula("(CH2)4O", true)

            // 8010 Ethylene Dibromide
            EthyleneDibromide = Material.Builder(8010, gtliteId("ethylene_dibromide"))
                .liquid()
                .color(0x4F1743)
                .components(Carbon, 2, Hydrogen, 4, Bromine, 2)
                .build()

            // 8011 Grignard Reagent
            GrignardReagent = Material.Builder(8011, gtliteId("grignard_reagent"))
                .liquid()
                .color(0xA12AA1)
                .components(Carbon, 1, Hydrogen, 3, HRAMagnesium, 1, Bromine, 1)
                .build()

            // 8012 Ethylhexanol
            Ethylhexanol = Material.Builder(8012, gtliteId("ethylhexanol"))
                .liquid()
                .color(0xFEEA9A)
                .components(Carbon, 8, Hydrogen, 10, Oxygen, 1)
                .build()

            // 8013 Di-(2-Ethylhexyl) Phosphoric Acid
            DiethylhexylPhosphoricAcid = Material.Builder(8013, gtliteId("diethylhexyl_phosphoric_acid"))
                .liquid()
                .color(0xFFFF99)
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 4, Phosphorus, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(C8H7O)2PO2H", true)

            // 8014 Formic Acid
            FormicAcid = Material.Builder(8014, gtliteId("formic_acid"))
                .liquid(FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xFFAA77)
                .components(Hydrogen, 2, Carbon, 1, Oxygen, 2)
                .build()
                .setFormula("HCOOH", true)

            // 8015 Methyl Formate
            MethylFormate = Material.Builder(8015, gtliteId("methyl_formate"))
                .liquid()
                .color(0xFFAAAA)
                .components(Hydrogen, 4, Carbon, 2, Oxygen, 2)
                .build()
                .setFormula("HCO2CH3", true)

            // 8016 Thionyl Chloride
            ThionylChloride = Material.Builder(8016, gtliteId("thionyl_chloride"))
                .liquid()
                .color(0xEBE863)
                .components(Sulfur, 1, Oxygen, 1, Chlorine, 2)
                .build()

            // 8017 Phthalic Anhydride
            PhthalicAnhydride = Material.Builder(8017, gtliteId("phthalic_anhydride"))
                .dust()
                .color(0xEEAAEE)
                .components(Carbon, 8, Hydrogen, 4, Oxygen, 3)
                .build()
                .setFormula("C6H4(CO)2O", true)

            // 8018 Ethylanthraquinone
            Ethylanthraquinone = Material.Builder(8018, gtliteId("ethylanthraquinone"))
                .liquid()
                .color(0xCC865A)
                .components(Carbon, 16, Hydrogen, 12, Oxygen, 2)
                .build()
                .setFormula("C6H4(CO)2C6H3Et", true)

            // 8019 Ethylanthrahydroquinone
            Ethylanthrahydroquinone = Material.Builder(8019, gtliteId("ethylanthrahydroquinone"))
                .liquid()
                .color(0xAD531A)
                .components(Carbon, 16, Hydrogen, 18, Oxygen, 2)
                .build()
                .setFormula("C6H4(CH2OH)2C6H3Et", true)

            // 8020 Hydrazine
            Hydrazine = Material.Builder(8020, gtliteId("hydrazine"))
                .liquid()
                .color(0xB50707)
                .components(Nitrogen, 2, Hydrogen, 4)
                .build()

            // 8021 Citric Acid
            CitricAcid = Material.Builder(8021, gtliteId("citric_acid"))
                .liquid()
                .color(0xFFCC00)
                .components(Carbon, 6, Hydrogen, 8, Oxygen, 7)
                .build()

            // 8022 Glutamine
            Glutamine = Material.Builder(8022, gtliteId("glutamine"))
                .dust()
                .color(0xEDE9B4).iconSet(DULL)
                .components(Carbon, 5, Hydrogen, 10, Nitrogen, 2, Oxygen, 3)
                .build()

            // 8023 Linoleic Acid
            LinoleicAcid = Material.Builder(8023, gtliteId("linoleic_acid"))
                .liquid()
                .color(0xD5D257)
                .components(Carbon, 18, Hydrogen, 32, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8024 Biotin
            Biotin = Material.Builder(8024, gtliteId("biotin"))
                .dust()
                .color(0x68CC6A)
                .components(Carbon, 10, Hydrogen, 16, Nitrogen, 2, Oxygen, 3, Sulfur, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8025 Durene
            Durene = Material.Builder(8025, gtliteId("durene"))
                .dust()
                .color(0x336040).iconSet(FINE)
                .components(Carbon, 10, Hydrogen, 14)
                .build()
                .setFormula("C6H2(CH3)4", true)

            // 8026 Pyromellitic Dianhydride (PDMA)
            PyromelliticDianhydride = Material.Builder(8026, gtliteId("pyromellitic_dianhydride"))
                .dust()
                .color(0xF0EAD6).iconSet(ROUGH)
                .components(Carbon, 10, Hydrogen, 2, Oxygen, 6)
                .build()
                .setFormula("C6H2(C2O3)2", true)

            // 8027 Aminophenol
            Aminophenol = Material.Builder(8027, gtliteId("aminophenol"))
                .dust()
                .color(0xFFFFFF).iconSet(SHINY)
                .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .build()

            // 8028 Aniline
            Aniline = Material.Builder(8028, gtliteId("aniline"))
                .liquid()
                .color(0x4c911d)
                .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1)
                .build()
                .setFormula("C6H5NH2", true)

            // 8029 Oxydianiline (ODA)
            Oxydianiline = Material.Builder(8029, gtliteId("oxydianiline"))
                .dust()
                .color(0xF0E130)
                .components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
                .build()
                .setFormula("O(C6H4NH2)2", true)

            // 8030 Kapton-K (Poly 4,4'-Oxydiphenylene-Pyromellitimide)
            KaptonK = Material.Builder(8030, gtliteId("kapton_k"))
                .polymer()
                .liquid()
                .color(0xFFCE52)
                .components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
                .build()
                .setFormula("(C7H2N2O4)(O(C6H4)2)", true)

            // 8031 Biphenyl Tetracarboxylic Acid Dianhydride (BTAD)
            BiphenylTetracarboxylicAcidDianhydride = Material.Builder(8031, gtliteId("biphenyl_tetracarboxylic_acid_dianhydride"))
                .dust()
                .color(0xFF7F50)
                .components(Carbon, 16, Hydrogen, 6, Oxygen, 6)
                .build()
                .setFormula("(C8H3O3)2", true)

            // 8032 Nitroaniline
            Nitroaniline = Material.Builder(8032, gtliteId("nitroaniline"))
                .liquid()
                .color(0x2A6E68)
                .components(Carbon, 6, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
                .build()
                .setFormula("H2NC6H4NO2", true)

            // 8033 Para-Phenylenediamine
            ParaPhenylenediamine = Material.Builder(8033, gtliteId("para_phenylenediamine"))
                .dust()
                .color(0x4A8E7B).iconSet(ROUGH)
                .components(Carbon, 6, Hydrogen, 8, Nitrogen, 2)
                .build()
                .setFormula("H2NC6H4NH2", true)

            // 8034 Kapton-E
            KaptonE = Material.Builder(8034, gtliteId("kapton_e"))
                .polymer()
                .liquid()
                .color(0xFFDF8C)
                .components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, NO_SMASHING, NO_SMELTING, GENERATE_FOIL)
                .build()
                .setFormula("O(C6H4NH2)2", true)

            // 8035 Hydroxyquinoline
            Hydroxyquinoline = Material.Builder(8035, gtliteId("hydroxyquinoline"))
                .dust()
                .color(0x3A9A71).iconSet(METALLIC)
                .components(Carbon, 9, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .build()

            // 8036 Hydrogen Cyanide
            HydrogenCyanide = Material.Builder(8036, gtliteId("hydrogen_cyanide"))
                .liquid()
                .color(0x6E6A5E)
                .components(Hydrogen, 1, Carbon, 1, Nitrogen, 1)
                .build()

            // 8037 Acetone Cyanohydrin
            AcetoneCyanohydrin = Material.Builder(8037, gtliteId("acetone_cyanohydrin"))
                .liquid()
                .color(0xA1FFD0)
                .components(Carbon, 4, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .build()

            // 8038 Polymethylmethacrylate (PMMA)
            Polymethylmethacrylate = Material.Builder(8038, gtliteId("polymethylmethacrylate"))
                .ingot()
                .liquid()
                .color(0x91CAE1)
                .components(Carbon, 5, Hydrogen, 8, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .build()

            // 8039 Diacetyl
            Diacetyl = Material.Builder(8039,  gtliteId("diacetyl"))
                .liquid()
                .color(0xF7FF65)
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 2)
                .build()

            // 8040 Ethylene Oxide
            EthyleneOxide = Material.Builder(8040, gtliteId("ethylene_oxide"))
                .gas()
                .color(0xDCBFE1)
                .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
                .build()

            // 8041 Ethylene Glycol
            EthyleneGlycol = Material.Builder(8041, gtliteId("ethylene_glycol"))
                .liquid()
                .color(0x286632)
                .components(Carbon, 2, Hydrogen, 6, Oxygen, 2)
                .build()
                .setFormula("C2H4(OH)2", true)

            // 8042 3,4-Ethylenedioxythiophene (EDOT)
            Edot = Material.Builder(8042, gtliteId("edot"))
                .liquid()
                .color(0xB1FFD7)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2, Sulfur, 1)
                .build()

            // 8043 Polystyrene (PS)
            Polystyrene = Material.Builder(8043, gtliteId("polystyrene"))
                .polymer()
                .liquid()
                .color(0xE1C2C2)
                .components(Carbon, 8, Hydrogen, 8)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
                .build()

            // 8044 Polystyrene Sulfonate (PSS)
            PolystyreneSulfonate = Material.Builder(8044, gtliteId("polystyrene_sulfonate"))
                .polymer()
                .liquid()
                .color(0xE17C72)
                .components(Carbon, 8, Hydrogen, 8, Sulfur, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
                .build()
                .setFormula("C8H7SO3H", true)

            // 8045 PEDOT:PSS
            PedotPSS = Material.Builder(8045, gtliteId("pedot_pss"))
                .polymer()
                .liquid()
                .color(0xE165A7)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
                .components(Edot, 1, PolystyreneSulfonate, 1)
                .cableProperties(V[ZPM], 6, 1)
                .build()

            // 8046 PEDOT-TMA
            PedotTMA = Material.Builder(8046, gtliteId("pedot_tma"))
                .polymer()
                .liquid()
                .color(0x5E9EE1)
                .components(Edot, 1, Polymethylmethacrylate, 2)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .cableProperties(V[UV], 8, 4)
                .build()

            // 8047 Methylamine
            Methylamine = Material.Builder(8047, gtliteId("methylamine"))
                .gas()
                .color(0xAA6600)
                .components(Carbon, 1, Hydrogen, 5, Nitrogen, 1)
                .build()
                .setFormula("CH3NH2", true)

            // 8048 Trimethylamine
            Trimethylamine = Material.Builder(8048, gtliteId("trimethylamine"))
                .gas()
                .color(0xBB7700)
                .components(Carbon, 3, Hydrogen, 9, Nitrogen, 1)
                .build()
                .setFormula("(CH3)3N", true)

            // 8049 Tetramethylammonium Chloride
            TetramethylammoniumChloride = Material.Builder(8049, gtliteId("tetramethylammonium_chloride"))
                .dust()
                .color(0x27FF81).iconSet(METALLIC)
                .components(Carbon, 4, Hydrogen, 12, Nitrogen, 1, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("N(CH3)4Cl", true)

            // 8050 Tetramethylammonium Hydroxide (TMAH)
            TetramethylammoniumHydroxide = Material.Builder(8050, gtliteId("tetramethylammonium_hydroxide"))
                .liquid()
                .color(0x40FFD6)
                .components(Nitrogen, 1, Carbon, 4, Hydrogen, 12, Oxygen, 1, Hydrogen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("N(CH3)4OH", true)

            // 8051 Pyrocatechol
            Pyrocatechol = Material.Builder(8051, gtliteId("pyrocatechol"))
                .dust()
                .color(0x784421).iconSet(DULL)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
                .build()

            // 8052 1,2-Dichloroethane
            Dichloroethane = Material.Builder(8052, gtliteId("dichloroethane"))
                .liquid()
                .color(0xDAAED3)
                .components(Carbon, 2, Hydrogen, 4, Chlorine, 2)
                .build()

            // 8053 Ethylenediamine
            Ethylenediamine = Material.Builder(8053, gtliteId("ethylenediamine"))
                .liquid()
                .color(0xD00ED0)
                .components(Carbon, 2, Hydrogen, 8, Nitrogen, 2)
                .build()
                .setFormula("C2H4(NH2)2", true)

            // 8054 Ethylenediamine Pyrocatechol (EDP)
            EthylenediaminePyrocatechol = Material.Builder(8054, gtliteId("ethylenediamine_pyrocatechol"))
                .liquid()
                .color(0xFBFF17)
                .components(Ethylenediamine, 1, Pyrocatechol, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8055 Formaldehyde
            Formaldehyde = Material.Builder(8055, gtliteId("formaldehyde"))
                .liquid()
                .color(0x858F40)
                .components(Carbon, 1, Hydrogen, 2, Oxygen, 1)
                .build()

            // 8056 Tetrasodium EDTA
            TetrasodiumEDTA = Material.Builder(8056, gtliteId("tetrasodium_ethylenediaminetetraacetic_acid"))
                .dust()
                .color(0x8890E0).iconSet(SHINY)
                .components(Carbon, 10, Hydrogen, 12, Nitrogen, 2, Oxygen, 8, Sodium, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8057 Ethylenediaminetetraacetic Acid (EDTA)
            EDTA = Material.Builder(8057, gtliteId("ethylenediaminetetraacetic_acid"))
                .dust()
                .liquid()
                .color(0x87E6D9).iconSet(ROUGH)
                .components(Carbon, 10, Hydrogen, 16, Nitrogen, 2, Oxygen, 8)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8058 Hexafluoropropylene
            Hexafluoropropylene = Material.Builder(8058, gtliteId("hexafluoropropylene"))
                .liquid()
                .color(0x141D6F)
                .components(Carbon, 3, Fluorine, 6)
                .build()

            // 8059 Fluorinated Ethylene Propylene (FEP)
            FluorinatedEthylenePropylene = Material.Builder(8059, gtliteId("fluorinated_ethylene_propylene"))
                .liquid()
                .color(0xC8C8C8).iconSet(DULL)
                .components(Carbon, 5, Fluorine, 10)
                .build()

            // 8060 Trichloroethylene
            Trichloroethylene = Material.Builder(8060, gtliteId("trichloroethylene"))
                .liquid()
                .color(0xB685B1)
                .components(Carbon, 2, Hydrogen, 1, Chlorine, 3)
                .build()

            // 8061 Chloroacetic Acid
            ChloroaceticAcid = Material.Builder(8061, gtliteId("chloroacetic_acid"))
                .dust()
                .color(0x38541A).iconSet(SHINY)
                .components(Carbon, 2, Hydrogen, 3, Chlorine, 1, Oxygen, 2)
                .build()

            // 8062 Malonic Acid
            MalonicAcid = Material.Builder(8062, gtliteId("malonic_acid"))
                .dust()
                .color(0x61932E).iconSet(SHINY)
                .components(Carbon, 3, Hydrogen, 4, Oxygen, 4)
                .build()

            // 8063 Phosphoryl Chloride
            PhosphorylChloride = Material.Builder(8063, gtliteId("phosphoryl_chloride"))
                .liquid()
                .color(0xE8BB5B)
                .components(Phosphorus, 1, Oxygen, 1, Chlorine, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8064 Phosphonitrilic Chloride Trimer
            PhosphonitrilicChlorideTrimer = Material.Builder(8064, gtliteId("phosphonitrilic_chloride_trimer"))
                .liquid()
                .color(0x082C38)
                .components(Chlorine, 6, Nitrogen, 3, Phosphorus, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8065 Fluorobenzene
            Fluorobenzene = Material.Builder(8065, gtliteId("fluorobenzene"))
                .liquid()
                .color(0x7CCA88)
                .components(Carbon, 6, Hydrogen, 5, Fluorine, 1)
                .build()

            // 8066 Octafluoro Pentanol
            OctafluoroPentanol = Material.Builder(8066, gtliteId("octafluoro_pentanol"))
                .liquid()
                .color(0xE5EBDE)
                .components(Carbon, 5, Hydrogen, 4, Fluorine, 8, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8067 Raw Polyphosphonitrile Fluoro Rubber
            RawPolyphosphonitrileFluoroRubber = Material.Builder(8067, gtliteId("raw_polyphosphonitrile_fluoro_rubber"))
                .dust()
                .color(0x585858)
                .components(Carbon, 24, Hydrogen, 16, Oxygen, 8, Nitrogen, 4, Phosphorus, 4, Fluorine, 40)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(CH2CF3)6(CH2C3F7)2(C2F4)2(NPO)4O4", true)

            // 8068 Polyphosphonitrile Fluoro Rubber
            PolyphosphonitrileFluoroRubber = Material.Builder(8068, gtliteId("polyphosphonitrile_fluoro_rubber"))
                .polymer()
                .liquid()
                .color(0x372B28)
                .components(Carbon, 24, Hydrogen, 16, Oxygen, 8, Nitrogen, 4, Phosphorus, 4, Fluorine, 40)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_ROD, GENERATE_RING, GENERATE_FOIL)
                .build()
                .setFormula("(CH2CF3)6(CH2C3F7)2(C2F4)2(NPO)4O4", true)

            // 8069 Polytetrahydrofuran
            Polytetrahydrofuran = Material.Builder(8069, gtliteId("polytetrahydrofuran"))
                .liquid()
                .color(0x089B3E)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(C4H8O)OH2", true)

            // 8070 Dinitrotoluene
            Dinitrotoluene = Material.Builder(8070, gtliteId("dinitrotoluene"))
                .liquid()
                .color(0xEAEFC9)
                .components(Carbon, 7, Hydrogen, 6, Nitrogen, 2, Oxygen, 4)
                .build()

            // 8071 Diaminotoluene
            Diaminotoluene = Material.Builder(8071, gtliteId("diaminotoluene"))
                .liquid()
                .color(0xEA8204)
                .components(Carbon, 7, Hydrogen, 7, Nitrogen, 2)
                .build()
                .setFormula("C6H3(NH2)2CH3", true)

            // 8072 Phosgene
            Phosgene = Material.Builder(8072, gtliteId("phosgene"))
                .gas()
                .color(0x48927C)
                .components(Carbon, 1, Oxygen, 1, Chlorine, 2)
                .build()

            // 8073 Toluene Diisocyanate
            TolueneDiisocyanate = Material.Builder(8073, gtliteId("toluene_diisocyanate"))
                .liquid()
                .color(0xCCCC66)
                .components(Carbon, 9, Hydrogen, 8, Nitrogen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("CH3C6H3(NCO)2", true)

            // 8074 Toluene Tetramethyl Diisocyanate
            TolueneTetramethylDiisocyanate = Material.Builder(8074, gtliteId("toluene_tetramethyl_diisocyanate"))
                .liquid()
                .color(0xBFBFBF)
                .components(Carbon, 19, Hydrogen, 12, Oxygen, 3, Nitrogen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(CONH)2(C6H4)2CH2(C4O)", true)

            // 8075 Raw Polytetramethylene Glycol Rubber
            RawPolytetramethyleneGlycolRubber = Material.Builder(8075, gtliteId("raw_polytetramethylene_glycol_rubber"))
                .dust()
                .color(0xFFFFFF).iconSet(ROUGH)
                .components(Carbon, 23, Hydrogen, 23, Oxygen, 5, Nitrogen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(CONH)2(C6H4)2CH2(C4O)HO(CH2)4OH", true)

            // 8076 Polytetramethylene Glycol Rubber
            PolytetramethyleneGlycolRubber = Material.Builder(8076, gtliteId("polytetramethylene_glycol_rubber"))
                .polymer()
                .liquid()
                .color(0xFFFFFF)
                .components(Carbon, 23, Hydrogen, 23, Oxygen, 5, Nitrogen, 2)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_ROD, GENERATE_RING, GENERATE_FOIL)
                .build()
                .setFormula("(CONH)2(C6H4)2CH2(C4O)HO(CH2)4OH", true)

            // 8077 Dense Hydrazine Rocket Fuel
            DenseHydrazineRocketFuel = Material.Builder(8077, gtliteId("dense_hydrazine_rocket_fuel"))
                .liquid()
                .color(0x912565)
                .components(Dimethylhydrazine, 1, Methanol, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8078 Methylhydrazine
            Methylhydrazine = Material.Builder(8078, gtliteId("methylhydrazine"))
                .liquid()
                .color(0x321452)
                .components(Carbon, 1, Hydrogen, 6, Nitrogen, 2)
                .build()

            // 8079 Methylhydrazine Nitrate Rocket Fuel
            MethylhydrazineNitrateRocketFuel = Material.Builder(8079, gtliteId("methylhydrazine_nitrate_rocket_fuel"))
                .liquid()
                .color(0x607186)
                .components(Methylhydrazine, 1, Tetranitromethane, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8080 Resorcinol
            Resorcinol = Material.Builder(8080, gtliteId("resorcinol"))
                .liquid()
                .color(0x9DA38D)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
                .build()

            // 8081 Fluorotoluene
            Fluorotoluene = Material.Builder(8081, gtliteId("fluorotoluene"))
                .liquid()
                .color(0x6EC5B8)
                .components(Carbon, 7, Hydrogen, 7, Fluorine, 1)
                .build()

            // 8082 Difluorobenzophenone
            Difluorobenzophenone = Material.Builder(8082, gtliteId("difluorobenzophenone"))
                .dust()
                .color(0xC44DA5).iconSet(SHINY)
                .components(Carbon, 13, Hydrogen, 8, Oxygen, 1, Fluorine, 2)
                .build()
                .setFormula("(FC6H4)2CO", true)

            // 8083 Hydroquinone
            Hydroquinone = Material.Builder(8083, gtliteId("hydroquinone"))
                .liquid()
                .color(0x83251A)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
                .build()
                .setFormula("C6H4(OH)2", true)

            // 8084 Polyetheretherketone (PEEK)
            Polyetheretherketone = Material.Builder(8084, gtliteId("polyetheretherketone"))
                .polymer()
                .liquid()
                .color(0x45433D)
                .components(Carbon, 20, Hydrogen, 12, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
                .build()

            // 8085 Fluorescein
            Fluorescein = Material.Builder(8085, gtliteId("fluorescein"))
                .dust()
                .color(0xE0E660).iconSet(SHINY)
                .components(Carbon, 20, Hydrogen, 12, Oxygen, 5)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8086 Erythrosine
            Erythrosine = Material.Builder(8086, gtliteId("erythrosine"))
                .dust()
                .color(0xC63611).iconSet(DULL)
                .components(Carbon, 20, Hydrogen, 6, Oxygen, 5, Sodium, 2, Iodine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8087 Isochloropropane
            Isochloropropane = Material.Builder(8087, gtliteId("isochloropropane"))
                .liquid()
                .color(0xC3AC65)
                .components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
                .build()
                .setFormula("CH3CHClCH3", true)

            // 8088 Acetic Anhydride
            AceticAnhydride = Material.Builder(8088, gtliteId("acetic_anhydride"))
                .liquid()
                .color(0xE2EBD9)
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 3)
                .build()

            // 8089 Dinitrodipropanyloxybenzene
            Dinitrodipropanyloxybenzene = Material.Builder(8089, gtliteId("dinitrodipropanyloxybenzene"))
                .liquid()
                .color(0x9FAD1D)
                .components(Carbon, 12, Hydrogen, 16, Oxygen, 6, Nitrogen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C12H16O2(NO2)2", true)

            // 8090 Dibromomethylbenzene
            Dibromomethylbenzene = Material.Builder(8090, gtliteId("dibromomethylbenzene"))
                .liquid()
                .color(0x9F4839)
                .components(Carbon, 7, Hydrogen, 6, Bromine, 2)
                .build()

            // 8091 Terephthalaldehyde
            Terephthalaldehyde = Material.Builder(8091, gtliteId("terephthalaldehyde"))
                .dust()
                .color(0x567C2D).iconSet(ROUGH)
                .components(Carbon, 8, Hydrogen, 6, Oxygen, 2)
                .build()

            // 8092 Pretreated Zylon
            PretreatedZylon = Material.Builder(8092, gtliteId("pretreated_zylon"))
                .dust()
                .color(0x623250).iconSet(DULL)
                .components(Carbon, 20, Hydrogen, 22, Nitrogen, 2, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8093 Zylon
            Zylon = Material.Builder(8093, gtliteId("zylon"))
                .polymer()
                .liquid()
                .color(0xFFE000).iconSet(SHINY)
                .components(Carbon, 14, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
                .build()

            // 8094 Diborane
            Diborane = Material.Builder(8094, gtliteId("diborane"))
                .gas()
                .color(0x3F3131)
                .components(Boron, 2, Hydrogen, 6)
                .build()

            // 8095 Borazine
            Borazine = Material.Builder(8095, gtliteId("borazine"))
                .liquid()
                .color(0x542828)
                .components(Boron, 3, Hydrogen, 6, Nitrogen, 3)
                .build()

            // 8096 Trichloroborazine
            Trichloroborazine = Material.Builder(8096, gtliteId("trichloroborazine"))
                .liquid()
                .color(0xD62929)
                .components(Boron, 3, Chlorine, 3, Hydrogen, 3, Nitrogen, 3)
                .build()

            // 8097 γ-Butyrolactone
            GammaButyrolactone = Material.Builder(8097, gtliteId("gamma_butyrolactone"))
                .liquid()
                .color(0xAF04D6)
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 2)
                .build()

            // 8098 N-Methyl-2-Pyrrolidone
            NMethylPyrrolidone = Material.Builder(8098, gtliteId("n_methyl_pyrrolidone"))
                .liquid()
                .color(0xA504D6)
                .components(Carbon, 5, Hydrogen, 9, Nitrogen, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8099 Acetylene
            Acetylene = Material.Builder(8099, gtliteId("acetylene"))
                .liquid()
                .color(0x959C60)
                .components(Carbon, 2, Hydrogen, 2)
                .build()

            // 8100 Tetrabromoethane
            Tetrabromoethane = Material.Builder(8100, gtliteId("tetrabromoethane"))
                .liquid()
                .color(0x5AAADA)
                .components(Carbon, 2, Hydrogen, 2, Bromine, 4)
                .build()

            // 8101 Terephthalic Acid
            TerephthalicAcid = Material.Builder(8101, gtliteId("terephthalic_acid"))
                .dust()
                .color(0x5ACCDA).iconSet(ROUGH)
                .components(Carbon, 8, Hydrogen, 6, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C6H4(CO2H)2", true)

            // 8102 Bistrichloromethylbenzene
            Bistrichloromethylbenzene = Material.Builder(8102, gtliteId("bistrichloromethylbenzene"))
                .liquid()
                .color(0xCF8498)
                .components(Carbon, 8, Hydrogen, 4, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C6H4(CCl3)2", true)

            // 8103 Terephthaloyl Chloride
            TerephthaloylChloride = Material.Builder(8103, gtliteId("terephthaloyl_chloride"))
                .dust()
                .color(0xFAC4DA).iconSet(SHINY)
                .components(Carbon, 8, Hydrogen, 4, Oxygen, 2, Chlorine, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C6H4(COCl)2", true)

            // 8104 Kevlar
            Kevlar = Material.Builder(8104, gtliteId("kevlar"))
                .polymer()
                .liquid()
                .color(0xF0F078)
                .components(Carbon, 14, Hydrogen, 10, Nitrogen, 2, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
                .fluidPipeProperties(2000, 700, true)
                .build()
                .setFormula("(C6H4)2(CO)2(NH)2", true);

            // 8105 Para Toluic Acid
            ParaToluicAcid = Material.Builder(8105, gtliteId("para_toluic_acid"))
                .liquid(FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0x4FA597)
                .components(Carbon, 8, Hydrogen, 8, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8106 Methylparatoluate
            Methylparatoluate = Material.Builder(8106, gtliteId("methylparatoluate"))
                .liquid()
                .color(0x76BCB0)
                .components(Carbon, 9, Hydrogen, 10, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8107 Dimethyl Terephthalate
            DimethylTerephthalate = Material.Builder(8107, gtliteId("dimethyl_terephthalate"))
                .liquid()
                .color(0x05D8AF)
                .components(Carbon, 10, Hydrogen, 10, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8108 Polyethylene Terephthalate (PET)
            PolyethyleneTerephthalate = Material.Builder(8108, gtliteId("polyethylene_terephthalate"))
                .polymer()
                .liquid()
                .color(0x1E5C58)
                .components(Carbon, 10, Hydrogen, 6, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
                .build()

            // 8109 Trimethylaluminium
            Trimethylaluminium = Material.Builder(8109, gtliteId("trimethylaluminium"))
                .liquid()
                .color(0x6ECCFF)
                .components(Aluminium, 2, Carbon, 6, Hydrogen, 18)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Al2(CH3)6", true)

            // 8110 Trimethylgallium
            Trimethylgallium = Material.Builder(8110, gtliteId("trimethylgallium"))
                .liquid()
                .color(0x4F92FF)
                .components(Gallium, 1, Carbon, 3, Hydrogen, 9)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ga(CH3)3", true)

            // 8111 Ammonium Cyanate
            AmmoniumCyanate = Material.Builder(8111, gtliteId("ammonium_cyanate"))
                .liquid()
                .color(0x3a5dcf)
                .components(Hydrogen, 4, Nitrogen, 2, Carbon, 1, Oxygen, 1)
                .build()
                .setFormula("NH4CNO", true)

            // 8112 Carbamide
            Carbamide = Material.Builder(8112, gtliteId("carbamide"))
                .dust()
                .color(0x16EF57).iconSet(ROUGH)
                .components(Carbon, 1, Hydrogen, 4, Nitrogen, 2, Oxygen, 1)
                .build()

            // 8113 Butanol
            Butanol = Material.Builder(8113, gtliteId("butanol"))
                .liquid()
                .color(0xC7AF2E)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
                .build()
                .setFormula("C4H9OH", true)

            // 8114 Tributylamine
            Tributylamine = Material.Builder(8114, gtliteId("tributylamine"))
                .liquid()
                .color(0x801A80)
                .components(Carbon, 12, Hydrogen, 27, Nitrogen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(C4H9)3N", true)

            // 8115 Dichloromethane
            Dichloromethane = Material.Builder(8115, gtliteId("dichloromethane"))
                .liquid()
                .color(0xB87FC7)
                .components(Carbon, 1, Hydrogen, 2, Chlorine, 2)
                .build()

            // 8116 Diethyl Ether
            DiethylEther = Material.Builder(8116, gtliteId("diethyl_ether"))
                .liquid()
                .color(0xFFA4A3)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(C2H5)2O", true)

            // 8117 2-Aminooxyacetic Acid
            AminooxyaceticAcid = Material.Builder(8117, gtliteId("aminooxyacetic_acid"))
                .liquid()
                .color(0xECFF1E)
                .components(Carbon, 2, Hydrogen, 5, Nitrogen, 1, Oxygen, 3)
                .build()

            // 8118 BenzylBromide
            BenzylBromide = Material.Builder(8118, gtliteId("benzyl_bromide"))
                .gas()
                .color(0xCF9D8C)
                .components(Carbon, 7, Hydrogen, 7, Bromine, 1)
                .build()
                .setFormula("C6H5CH2Br", true)

            // 8119 Benzyltrimethylammonium Bromide
            BenzyltrimethylammoniumBromide = Material.Builder(8119, gtliteId("benzyltrimethylammonium_bromide"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(BenzylBromide, 1, Trimethylamine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C6H5CH2N(CH3)3Br", true)

            // 8120 2-Chlorobutane
            Chlorobutane = Material.Builder(8120, gtliteId("chlorobutane"))
                .liquid()
                .color(0xE6772D)
                .components(Carbon, 4, Hydrogen, 9, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8121 Tertbutyl Alcohol
            TertbutylAlcohol = Material.Builder(8121, gtliteId("tertbutyl_alcohol"))
                .liquid()
                .color(0xBC8F44)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8122 Indene
            Indene = Material.Builder(8122, gtliteId("indene"))
                .liquid()
                .color(0x171429)
                .components(Carbon, 9, Hydrogen, 8)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C6H4C3H4", true)

            // 8123 Indanone
            Indanone = Material.Builder(8123, gtliteId("indanone"))
                .dust()
                .color(0x2E1616).iconSet(SHINY)
                .components(Carbon, 9, Hydrogen, 8, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C6H4C3H4O", true)

            // 8124 Truxene
            Truxene = Material.Builder(8124, gtliteId("truxene"))
                .liquid()
                .color(0x1A3336)
                .components(Carbon, 27, Hydrogen, 18)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8125 Bromomethane
            Bromomethane = Material.Builder(8125, gtliteId("bromomethane"))
                .gas()
                .color(0xC82C31)
                .components(Carbon, 1, Hydrogen, 3, Bromine, 1)
                .build()

            // 8126 1-Bromo-2-(Bromomethyl) Naphthalene
            BromoBromomethylNaphthalene = Material.Builder(8126, gtliteId("bromo_bromomethyl_naphthalene"))
                .liquid()
                .color(0x52122E)
                .components(Carbon, 11, Hydrogen, 8, Bromine, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8127 1-Bromobutane
            Bromobutane = Material.Builder(8127, gtliteId("bromobutane"))
                .gas()
                .color(0xE6E8A2)
                .components(Butene, 1, HydrobromicAcid, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C4H9Br", true)

            // 8128 Butyllithium
            Butyllithium = Material.Builder(8128, gtliteId("butyllithium"))
                .liquid()
                .color(0xE683B6B)
                .components(Butene, 1, Hydrogen, 1, Lithium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C4H9Li", true)

            // 8129 Acetyl Chloride
            AcetylChloride = Material.Builder(8129, gtliteId("acetyl_chloride"))
                .liquid()
                .color(0xC3C3C3)
                .components(Carbon, 2, Hydrogen, 3, Oxygen, 1, Chlorine, 1)
                .build()
                .setFormula("CH3COCl", true)

            // 8130 Dimethylacetamide
            Dimethylacetamide = Material.Builder(8130, gtliteId("dimethylacetamide"))
                .liquid()
                .color(0x18AEA5)
                .components(Carbon, 4, Hydrogen, 9, Nitrogen, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(CH3)2NC(O)CH3", true)

            // 8131 GeodesicPolyarene
            GeodesicPolyarene = Material.Builder(8131, gtliteId("geodesic_polyarene"))
                .dust()
                .color(0x9E81A8).iconSet(METALLIC)
                .components(Carbon, 60, Hydrogen, 30)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8132 Fullerene
            Fullerene = Material.Builder(8132, gtliteId("fullerene"))
                .polymer()
                .liquid()
                .color(0x72556A).iconSet(BRIGHT)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_SMELTING, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .components(Carbon, 60)
                .build()

            // 8133 Glucose
            Glucose = Material.Builder(8133, gtliteId("glucose"))
                .dust()
                .color(Sugar.materialRGB + 5).iconSet(ROUGH)
                .components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8134 Fructose
            Fructose = Material.Builder(8134, gtliteId("fructose"))
                .dust()
                .color(Sugar.materialRGB + 10).iconSet(ROUGH)
                .components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8135 Oxalic Acid
            OxalicAcid = Material.Builder(8135, gtliteId("oxalic_acid"))
                .liquid()
                .color(0x4AAAE2)
                .components(Carbon, 2, Hydrogen, 2, Oxygen, 4)
                .build() // (HOOC)(COOH)

            // 8136 Dibromoacrolein
            Dibromoacrolein = Material.Builder(8136, gtliteId("dibromoacrolein"))
                .liquid()
                .color(0x7C4660)
                .components(Carbon, 2, Hydrogen, 2, Bromine, 2, Oxygen, 2)
                .build()

            // 8137 Bromodihydrothiine
            Bromodihydrothiine = Material.Builder(8137, gtliteId("bromodihydrothiine"))
                .liquid()
                .color(0x66F36E)
                .components(Carbon, 4, Hydrogen, 4, Sulfur, 2, Bromine, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 8138 Bis-(Ethylenedithio)-Tetraselenafulvalene (BETS)
            BETS = Material.Builder(8138, gtliteId("bisethylenedithiotetraselenafulvalene"))
                .dust()
                .color(0x98E993).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 8, Sulfur, 4, Selenium, 4)
                .build()

        }

    }

}