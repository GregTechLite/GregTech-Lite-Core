package gregtechlite.gtlitecore.api.unification.material.properties

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.FluidBuilder
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Actinium
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Bauxite
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Biotite
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Dysprosium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.EnderPearl
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.IridiumMetalResidue
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.PalladiumRaw
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.RarestMetalMixture
import gregtech.api.unification.material.Materials.RawGrowthMedium
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumBisulfate
import gregtech.api.unification.material.Materials.SodiumPersulfate
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Tennessine
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.material.Materials.Zircaloy4
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.FluidPipeProperties
import gregtech.api.unification.material.properties.FluidProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.MaterialToolProperty
import gregtech.api.unification.material.properties.OreProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.WireProperties
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aegirine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Albite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Alumina
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Anorthite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Augite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azurite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Baddeleyite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bytownite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Celestine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromiumGermaniumTelluride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromiumGermaniumTellurideMagnetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cryolite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cuprite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dolomite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ferrosilite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorapatite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Forsterite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jade
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jasper
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kaolinite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Labradorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lizardite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Muscovite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nephelite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Oligoclase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Orpiment
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phlogopite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Picotite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Strontianite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tanzanite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tenorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Wollastonite
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.addDust
import gregtechlite.gtlitecore.api.extension.addIngot
import gregtechlite.gtlitecore.api.extension.addLiquid
import gregtechlite.gtlitecore.api.extension.addLiquidAndPlasma
import gregtechlite.gtlitecore.api.extension.addPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumGroupAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumOxalate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AdamantiumUnstable
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminaSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumSulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumAcetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumHexachloropalladate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumHexachloroplatinate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumPersulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumSulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmorphousBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BETSPerrhenate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthStrontiumCalciumCuprate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CalciumAlginate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CalciumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CeriumCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChlorinatedSolvents
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromaticGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Clinochlore
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CopperArsenite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CopperNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicHeterodiamond
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicZirconia
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DegenerateRhenium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylcadmium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ErbiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Firestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FleroviumYtterbiumPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyAlkaliChloridesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HexagonalBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HexagonalSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydroxyquinolineAluminium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Iron3Sulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kovar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumEmbeddedFullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumFullereneMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumFullereneNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lignite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LuTmDopedYttriumVanadateDeposition
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LuTmYChloridesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LuTmYVO
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MOX
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetoResonatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NdYAG
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PalladiumAcetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PalladiumFullereneMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PalladiumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhotopolymerSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlatinumGroupConcentrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlatinumGroupResidue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlutoniumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlutoniumPhosphide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlutoniumTrihydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PoloniumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumFerrocyanideTrihydrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrHoYLF
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrHoYNitratesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PraseodymiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrussianBlue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PurifiedPlatinumGroupConcentrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RareEarthAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RoastedSphalerite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilicaGelBase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumAcetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumAlginate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumFormate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPolonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumHafniumSeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumThuliumDopedCaesiumIodide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThoriumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TransitionAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TrichlorocyclopentadienylTitanium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.UranylNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.UranylNitrateSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumUnstable
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WaelzOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WaelzSlag
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YttriumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZSM5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZincRichSphalerite

/**
 * Consists of all material properties settings.
 *
 * Materials can modify its main properties by the mixins feature, please see these change below.
 *
 * @see gregtechlite.gtlitecore.mixins.gregtech.MixinMaterialProperties
 */
object GTLiteMaterialProperties
{

    fun init()
    {
        setMaterialProperties()
        setOreProperties()
        setMaterialColors()
        setChemicalFormula()
    }

    fun setMaterialProperties()
    {
        // region State Properties

        arrayOf(Strontium, Rhenium, Uranium, Uranium235, Uranium238, Selenium, Tellurium, Lanthanum, Cerium,
                Praseodymium, Promethium, Gadolinium, Terbium, Dysprosium, Holmium, Erbium, Thulium, Ytterbium,
                Scandium, Germanium, Technetium, Cadmium, Dubnium, Rutherfordium, Curium, Seaborgium, Bohrium,
                Neptunium, Fermium, Rubidium, Calcium, Magnesium, Francium, Thallium, Barium, Lutetium, Californium,
                Radium, Polonium, Actinium, Protactinium, Berkelium, Einsteinium, Mendelevium, Astatine, Nobelium,
                Lawrencium, Roentgenium, Moscovium, Meitnerium, Copernicium, Nihonium, Livermorium, Tennessine, Sulfur)
            .forEach { it.addIngot() }

        // Dust Properties
        arrayOf(Iodine).forEach { it.addDust() }

        // Liquid Properties
        arrayOf(Bromine, Uranium238, Zircaloy4, Inconel718, SodiumBisulfate, Germanium, Rutherfordium, Dubnium, Curium,
                Seaborgium, Bohrium, Selenium, Francium, Thallium, Sodium, Californium, Radium, Scandium, Polonium,
                Actinium, Protactinium, Berkelium, Einsteinium, Mendelevium, Astatine, Nobelium, Lawrencium, Ytterbium,
                Rhenium, Roentgenium, Moscovium, Meitnerium, Copernicium, Nihonium, Livermorium, Tennessine, Thulium,
                Promethium, Barium, Tellurium, Holmium, Erbium, Gadolinium, EnderPearl)
            .forEach { it.addLiquid() }

        // Plasma Properties
        arrayOf(Niobium, Zinc, Krypton, Xenon, Radon, Neon, Bismuth, Thorium, Silver, Titanium, Lead)
            .forEach { it.addPlasma() }

        // Liquid and Plasma Properties
        arrayOf(Neptunium, Fermium, Boron, Rubidium, Technetium, Calcium, Sulfur)
            .forEach { it.addLiquidAndPlasma() }

        // endregion

        // region Magnetic Properties
        ChromiumGermaniumTelluride.getProperty(PropertyKey.INGOT)
            .magneticMaterial = ChromiumGermaniumTellurideMagnetic

        Europium.getProperty(PropertyKey.INGOT)
            .magneticMaterial = Magnetium

        // endregion

        // region Blast Properties

        Rhenium.setProperty(PropertyKey.BLAST, BlastProperty(3459))
        Rhenium.getProperty(PropertyKey.BLAST).durationOverride = 13 * SECOND + 8 * TICK

        Uranium.setProperty(PropertyKey.BLAST, BlastProperty(600))
        Uranium.getProperty(PropertyKey.BLAST).setEutOverride(VA[MV])
        Uranium.getProperty(PropertyKey.BLAST).durationOverride = 15 * SECOND

        Germanium.setProperty(PropertyKey.BLAST, BlastProperty(1211))

        Americium.setProperty(PropertyKey.BLAST, BlastProperty(7500))
        Americium.getProperty(PropertyKey.BLAST).setEutOverride(VA[ZPM])
        Americium.getProperty(PropertyKey.BLAST).durationOverride = 12 * SECOND
        Americium.getProperty(PropertyKey.BLAST).setVacuumEutOverride(VA[LuV])
        Americium.getProperty(PropertyKey.BLAST).vacuumDurationOverride = 18 * SECOND

        Seaborgium.setProperty(PropertyKey.BLAST, BlastProperty(8300))
        Seaborgium.getProperty(PropertyKey.BLAST).setEutOverride(VA[ZPM])
        Seaborgium.getProperty(PropertyKey.BLAST).durationOverride = 16 * SECOND
        Seaborgium.getProperty(PropertyKey.BLAST).setVacuumEutOverride(VA[IV])
        Seaborgium.getProperty(PropertyKey.BLAST).vacuumDurationOverride = 22 * SECOND

        Bohrium.setProperty(PropertyKey.BLAST, BlastProperty(8500))
        Bohrium.getProperty(PropertyKey.BLAST).setEutOverride(VA[ZPM])
        Bohrium.getProperty(PropertyKey.BLAST).durationOverride = 18 * SECOND
        Bohrium.getProperty(PropertyKey.BLAST).setVacuumEutOverride(VA[IV])
        Bohrium.getProperty(PropertyKey.BLAST).vacuumDurationOverride = 14 * SECOND

        RutheniumTriniumAmericiumNeutronate.getProperty(PropertyKey.BLAST).blastTemperature = 12100
        RutheniumTriniumAmericiumNeutronate.getProperty(PropertyKey.BLAST).setEutOverride(VA[UHV])

        Fermium.setProperty(PropertyKey.BLAST, BlastProperty(9500))
        Fermium.getProperty(PropertyKey.BLAST).setEutOverride(VA[UV])
        Fermium.getProperty(PropertyKey.BLAST).durationOverride = 22 * SECOND
        Fermium.getProperty(PropertyKey.BLAST).setVacuumEutOverride(VA[IV])
        Fermium.getProperty(PropertyKey.BLAST).vacuumDurationOverride = 12 * SECOND

        Neptunium.setProperty(PropertyKey.BLAST, BlastProperty(3440))
        Neptunium.getProperty(PropertyKey.BLAST).setEutOverride(VA[EV])
        Neptunium.getProperty(PropertyKey.BLAST).durationOverride = 14 * SECOND
        Neptunium.getProperty(PropertyKey.BLAST).setVacuumEutOverride(VA[MV])
        Neptunium.getProperty(PropertyKey.BLAST).vacuumDurationOverride = 6 * SECOND + 5 * TICK

        Technetium.setProperty(PropertyKey.BLAST, BlastProperty(2800))
        Technetium.getProperty(PropertyKey.BLAST).setEutOverride(VA[IV])
        Technetium.getProperty(PropertyKey.BLAST).durationOverride = 15 * SECOND
        Technetium.getProperty(PropertyKey.BLAST).setVacuumEutOverride(VA[HV])
        Technetium.getProperty(PropertyKey.BLAST).vacuumDurationOverride = 4 * SECOND + 10 * TICK

        // endregion

        // region Fluid Pipe Properties
        Inconel718.setProperty(PropertyKey.FLUID_PIPE,
                               FluidPipeProperties(2010, 175,
                                                   true, true, true, false))

        RhodiumPlatedPalladium.setProperty(PropertyKey.FLUID_PIPE,
                                           FluidPipeProperties(6120, 225,
                                                               true, true, true, false))

        // endregion

        // region Wire Properties

        Rutherfordium.setProperty(PropertyKey.WIRE, WireProperties(V[ZPM].toInt(), 8, 2))
        Seaborgium.setProperty(PropertyKey.WIRE, WireProperties(V[UEV].toInt(), 4, 16))

        // endregion

        // region Tool Properties

        Naquadah.setProperty(PropertyKey.TOOL, MaterialToolProperty(20.0F, 8.0F, 2048, 4))
        Iridium.setProperty(PropertyKey.TOOL, MaterialToolProperty(4.8F, 10.0F, 2560, 4))

        // endregion

    }

    fun setOreProperties()
    {
        // Ores which generated in world.
        Andradite.setProperty(PropertyKey.ORE, OreProperty())
        Biotite.setProperty(PropertyKey.ORE, OreProperty())
        Uvarovite.setProperty(PropertyKey.ORE, OreProperty())
        Plutonium239.setProperty(PropertyKey.ORE, OreProperty())

        // Virtual Ores for Mining Drone Airport or higher ore producing machines.
        Uranium.setProperty(PropertyKey.ORE, OreProperty())
        Plutonium241.setProperty(PropertyKey.ORE, OreProperty())
        Chrome.setProperty(PropertyKey.ORE, OreProperty())
        Cadmium.setProperty(PropertyKey.ORE, OreProperty())

        // Set Ore Byproducts.
        var oreProp = Andradite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Andradite, Andradite, Calcium)

        oreProp = Dolomite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Dolomite, Calcium, Magnesium)

        oreProp = Tanzanite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Tanzanite, Opal, Aluminium)

        oreProp = Azurite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Iron, Copper, Malachite)
        oreProp.setWashedIn(SodiumPersulfate)

        oreProp = Forsterite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Forsterite, Magnesium, Manganese)

        oreProp = Augite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Calcite, SiliconDioxide, Kaolinite)

        oreProp = Albite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Albite, Sodium, Silicon)

        oreProp = Fluorite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Fluorite, Calcium, Calcium)

        oreProp = Anorthite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Calcium, Silicon, Aluminium)

        oreProp = Oligoclase.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Anorthite, Oligoclase, Aluminium)

        oreProp = Labradorite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Albite, Silicon, Aluminium)

        oreProp = Bytownite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Anorthite, Calcium, Aluminium)

        oreProp = Tenorite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Iron, Manganese, Malachite)

        oreProp = Cuprite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Iron, Antimony, Malachite)

        oreProp = Wollastonite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Wollastonite, Calcium, Silicon)

        oreProp = Fluorapatite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Fluorapatite, Apatite, Phosphorus)

        oreProp = Kaolinite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Clay, Clay, SiliconDioxide)

        oreProp = Celestine.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Celestine, Celestine, StrontiumOxide)

        oreProp = Strontianite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Strontianite, Calcite, StrontiumOxide)

        oreProp = Muscovite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Mica, Muscovite, Phlogopite)

        oreProp = Phlogopite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Muscovite, Phlogopite, Mica)

        oreProp = Baddeleyite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Baddeleyite, Baddeleyite, Zircon)

        oreProp = Nephelite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Nephelite, Albite, Silicon)

        oreProp = Aegirine.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Aegirine, Ferrosilite, Iron)

        oreProp = Cryolite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Alumina, Alumina, Sodium)

        oreProp = Jade.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Jade, Jasper, Emerald)

        oreProp = Jasper.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Jasper, Jade, SiliconDioxide)

        oreProp = Picotite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Picotite, Chrome, Chrome)

        oreProp = Orpiment.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Sulfur, Antimony, Realgar)

        oreProp = Biotite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Phlogopite, Muscovite, Biotite)

        oreProp = Ferrosilite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Ferrosilite, SiliconDioxide, Iron)

        oreProp = Uvarovite.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Quartzite, Uvarovite, Lizardite)

        oreProp = Uranium.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Uranium, Uranium238, Uranium235)

        oreProp = Plutonium239.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Plutonium239, Plutonium239, Plutonium241)

        oreProp = Plutonium241.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Plutonium241, Plutonium239, Plutonium241)

        oreProp = Thorium.getProperty(PropertyKey.ORE)
        oreProp.setOreByProducts(Uraninite, Lead, Actinium)

        oreProp = Molybdenite.getProperty(PropertyKey.ORE)
        oreProp.directSmeltResult = null
    }

    fun setMaterialColors()
    {
        Promethium.materialRGB = 0x24B535
        Dysprosium.materialRGB = 0xDD79DD
        Erbium.materialRGB = 0xCC6633
        Holmium.materialRGB = 0xC38E2F
        Terbium.materialRGB = 0xA274A2
        Thulium.materialRGB = 0x596BC2
        Nobelium.materialRGB = 0xF6B5FF
        Lawrencium.materialRGB = 0xBF4655
        Tellurium.materialRGB = 0xE2D8C1
        RawGrowthMedium.materialRGB = 0x0B2E12
        Meitnerium.materialRGB = 0x9C1E55
    }

    fun setChemicalFormula()
    {
        // GTCEu Materials
        Biotite.setFormula("KMg3Al2(AlSi3O10)F2", true)
        Mica.setFormula("KAl2(AlSi3O10)F2", true)
        Bauxite.setFormula("(Al2O3)3(TiO2)2(H2O)2?", true)
        Graphene.setFormula("C8", true)
        PalladiumRaw.setFormula("PdCl2", true)
        RarestMetalMixture.setFormula("IrOs?", true)
        IridiumMetalResidue.setFormula("Ir2O3", true)

        // Element Materials
        DegenerateRhenium.setFormula("§cR§de", true)

        // First Degree Materials
        Dolomite.setFormula("CaMg(CO3)2", true)
        Azurite.setFormula("Cu3(CO3)2(OH)2", true)
        Forsterite.setFormula("Mg2(SiO4)", true)
        Augite.setFormula("(Ca2MgFe)(MgFe)2(Si2O6)4", true)
        Lizardite.setFormula("Mg3Si2O5(OH)4", true)
        Muscovite.setFormula("KAl2(AlSi3O10)(OH)2", true)
        Clinochlore.setFormula("Mg5Al2Si3O10(OH)8", true)
        Fluorapatite.setFormula("Ca5(PO4)3F", true)
        Lignite.setFormula("C3(H2O)", true)
        Firestone.setFormula("(SiO2)3(FeS2)?", true)
        Iron3Sulfate.setFormula("Fe2(SO4)3", true)
        Phlogopite.setFormula("KMg3(AlSi3O10)F2", true)
        Nephelite.setFormula("KNa3(AlSiO4)4", true)
        HeavyAlkaliChloridesSolution.setFormula("(RbCl)(CsCl)2Cl3(H2O)2", true)
        AluminiumHydroxide.setFormula("Al(OH)3", true)
        AluminiumSulfate.setFormula("Al2(SO4)3", true)
        ZSM5.setFormula("Na(Al2(SO4)3)(SiO2)2(H2O)2", true)
        Jasper.setFormula("CaMg5(OH)2(Si4O11)2", true)
        LeadNitrate.setFormula("Pb(NO3)2", true)
        TitaniumNitrate.setFormula("Ti(NO3)4", true)
        PalladiumNitrate.setFormula("Pd(NO3)2", true)
        PalladiumAcetate.setFormula("Pd(CH3COOH)2", true)
        Dimethylcadmium.setFormula("(CH3)2Cd", true)
        ZBLANGlass.setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2", true)
        ErbiumDopedZBLANGlass.setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2Er", true)
        PraseodymiumDopedZBLANGlass.setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2Pr", true)
        CubicZirconia.setFormula("c-ZrO2", true)
        MagnetoResonatic.setFormula("(Bi2Te3)4((SiO2)5Fe)3(ZrO2)Fe", true)
        PlatinumGroupResidue.setFormula("RuRhIr2Os(HNO3)3", true)
        PlatinumGroupConcentrate.setFormula("AuPtPd(HCl)6", true)
        PurifiedPlatinumGroupConcentrate.setFormula("H2PtPdCl6", true)
        AmmoniumHexachloroplatinate.setFormula("(NH4)2PtCl6", true)
        AmmoniumHexachloropalladate.setFormula("(NH4)2PdCl6", true)
        YttriumNitrate.setFormula("Y(NO3)3", true)
        BariumNitrate.setFormula("Ba(NO3)2", true)
        CopperNitrate.setFormula("Cu(NO3)2", true)
        RoastedSphalerite.setFormula("(GeO2)?", true)
        ZincRichSphalerite.setFormula("Zn2(GaGeO2)?", true)
        WaelzOxide.setFormula("(GeO2)Zn", true)
        WaelzSlag.setFormula("(ZnSO4)Ga", true)
        HydroxyquinolineAluminium.setFormula("(C9H7NO)Al", true)
        BariumHydroxide.setFormula("Ba(OH)2", true)
        AmmoniumNitrate.setFormula("NH4NO3", true)
        BismuthStrontiumCalciumCuprate.setFormula("Bi2Sr2CaCu2O8", true)
        AdamantiumUnstable.setFormula("Ad*", true)
        VibraniumUnstable.setFormula("Vb*", true)
        LithiumSodiumPotassiumFluorides.setFormula("F3LiNaK", true)
        LithiumBerylliumFluorides.setFormula("F3LiBe", true)
        FleroviumYtterbiumPlasma.setFormula("FlY?", true)
        SodiumAcetate.setFormula("C2H3NaO2", true)
        HexagonalBoronNitride.setFormula("h-BN", true)
        CubicBoronNitride.setFormula("c-BN", true)
        AmorphousBoronNitride.setFormula("a-BN", true)
        CubicHeterodiamond.setFormula("c-BC2N", true)
        CalciumHydroxide.setFormula("Ca(OH)2", true)
        AluminiumNitrate.setFormula("Al(NO3)3", true)
        ChlorinatedSolvents.setFormula("(CH4)2Cl5", true)
        AluminaSolution.setFormula("(Al2O3)(CH2Cl2)(C12H27N)2", true)
        NdYAG.setFormula("Nd:YAG", true)
        PoloniumNitrate.setFormula("Po(NO3)4", true)
        SodiumPolonate.setFormula("Na2PoO4", true)
        UranylNitrateSolution.setFormula("(UO2)(NO3)2(H2O)?", true)
        ThoriumNitrate.setFormula("Th(NO3)4", true)
        UranylNitrate.setFormula("UO2(NO3)2", true)
        ActiniumOxalate.setFormula("Ac(CO2)4", true)
        SodiumFormate.setFormula("HCOONa", false)
        BETSPerrhenate.setFormula("(C10H8S4Se4)ReO4", true)
        SilicaGelBase.setFormula("(SiNa(OH)O2)(HCl)(H2O)", true)
        AmmoniumSulfate.setFormula("(NH4)2SO4", true)
        AmmoniumCarbonate.setFormula("(NH4)2CO3", true)
        AmmoniumAcetate.setFormula("NH4CH3CO2", true)
        AmmoniumPersulfate.setFormula("(NH4)2S2O8", true)
        PalladiumFullereneMatrix.setFormula("(C73H15NFe)Pd", true)
        LuTmYChloridesSolution.setFormula("(LuCl3)2(TmCl3)2(YCl3)6(H2O)15", true)
        LuTmDopedYttriumVanadateDeposition.setFormula("Lu/Tm:YVO?", false)
        LuTmYVO.setFormula("Lu/Tm:YVO", false)
        HexagonalSiliconNitride.setFormula("h-Si3N4", true)
        CubicSiliconNitride.setFormula("c-Si3N4", true)
        CeriumCarbonate.setFormula("Ce2(CO3)3", true)
        PlutoniumTrihydride.setFormula("PuH3", true)
        PlutoniumPhosphide.setFormula("PuP", true)
        LanthanumFullereneMixture.setFormula("(C60H30)2La2", true)
        LanthanumEmbeddedFullerene.setFormula("(C60)2La2", true)
        LanthanumFullereneNanotube.setFormula("C48C60La", true)
        PrHoYNitratesSolution.setFormula("(Pr(NO3)3)2(Ho(NO3)3)2(Y(NO3)3)6(H2O)15", true)
        PrHoYLF.setFormula("Pr/Ho:YLF", true)
        TrichlorocyclopentadienylTitanium.setFormula("(C5H5)2Cl3Ti", true)
        PhotopolymerSolution.setFormula("C149H97N10O2(TiBF20)", true)
        PotassiumFerrocyanideTrihydrate.setFormula("K4Fe(CN)6(H2O)3", true)
        PrussianBlue.setFormula("Fe4[Fe(CN)6]3", true)
        CopperArsenite.setFormula("Cu3(AsO4)2", true)
        ThalliumThuliumDopedCaesiumIodide.setFormula("Tl/Tm:CsI", false)
        SodiumAlginate.setFormula("C5H7O4COONa", true)
        CalciumAlginate.setFormula("(C5H7O4COO)2Ca", true)
        ChromaticGlass.setFormula("(SiO2)64", true)
        PlutoniumDioxide.setFormula("PuO2", true)
        MOX.setFormula("(PuO2)(UO2)2", true)

        // Second Degree Materials
        Kovar.setFormula("Fe10Ni5Co3", true)
        HalkoniteSteel.setFormula("SpNt2((FeW)8*Nq*7?4C4(VCrFe7)3Fr)2P8(((WC)(TiC)2)3(CaMg5(OH)2(Si4O11)2)3Tr2)If", true)
        TantalumHafniumSeaborgiumCarbide.setFormula("Ta12Hf3SgC16", true)
        ActiniumGroupAlloyA.setFormula("AcThPaUNpPuAmCm", true)
        TransitionAlloy.setFormula("TiVCrMnFeCoNiCuAlZnGaGeCdInSnSb", true)
        RareEarthAlloy.setFormula("LaCePrNdPmSmEuGdTbDyHoErTmYbLuScYAcThPaUNpPuAmCmBkCfEsFmMdNoLr", true)
        Periodicium.setFormula("HHeLiBeBCNOFNeNaMgAlSiPSClArKCaScTiVCrMnFeCoNiCuZnGaGe"
                               + "AsSeBrKrRbSrYZrNbMoTcRuRhPdAgCdInSnSbTeIXeCsBaLaCePrNdPm"
                               + "SmEuGdTbDyHoErTmYbLuHfTaWReOsIrPtAuHgTlPbBiPoAtRnFrRaAcTh"
                               + "PaUNpPuAmCmBkCfEsFmMdNoLrRfDbSgBhHsMtDsRgCnNhFlMcLvTsOg")


    }

}