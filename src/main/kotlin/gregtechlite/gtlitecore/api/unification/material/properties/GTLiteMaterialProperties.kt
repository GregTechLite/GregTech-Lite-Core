package gregtechlite.gtlitecore.api.unification.material.properties

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
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
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium

/**
 * Consists of all material properties settings.
 *
 * Materials can modify its main properties by the mixins feature, please see these change below.
 *
 * @see magicbook.gtlitecore.mixins.gregtech.MixinMaterialProperties
 */
object GTLiteMaterialProperties
{

    fun setMaterialProperties()
    {

        // Ingot Properties
        arrayOf(Strontium, Rhenium, Uranium, Uranium235, Uranium238, Selenium, Tellurium, Lanthanum, Cerium,
                Praseodymium, Promethium, Gadolinium, Terbium, Dysprosium, Holmium, Erbium, Thulium, Ytterbium,
                Scandium, Germanium, Technetium, Cadmium, Dubnium, Rutherfordium, Curium, Seaborgium, Bohrium,
                Neptunium, Fermium, Rubidium, Calcium, Magnesium, Francium, Thallium, Barium, Lutetium, Californium,
                Radium, Polonium, Actinium, Protactinium, Berkelium, Einsteinium, Mendelevium, Astatine, Nobelium,
                Lawrencium, Roentgenium, Moscovium, Meitnerium, Copernicium, Nihonium, Livermorium, Tennessine, Sulfur)
            .forEach { addIngot(it) }

        // Dust Properties
        arrayOf(Iodine).forEach { addDust(it) }

        // Liquid Properties
        arrayOf(Bromine, Uranium238, Zircaloy4, Inconel718, SodiumBisulfate, Germanium, Rutherfordium, Dubnium, Curium,
                Seaborgium, Bohrium, Selenium, Francium, Thallium, Sodium, Californium, Radium, Scandium, Polonium,
                Actinium, Protactinium, Berkelium, Einsteinium, Mendelevium, Astatine, Nobelium, Lawrencium, Ytterbium,
                Rhenium, Roentgenium, Moscovium, Meitnerium, Copernicium, Nihonium, Livermorium, Tennessine, Thulium,
                Promethium, Barium, Tellurium, Holmium, Erbium, Gadolinium, EnderPearl)
            .forEach { addLiquid(it) }

        // Plasma Properties
        arrayOf(Niobium, Zinc, Krypton, Xenon, Radon, Neon, Bismuth, Thorium, Silver, Titanium, Lead)
            .forEach { addPlasma(it) }

        // Liquid and Plasma Properties
        arrayOf(Neptunium, Fermium, Boron, Rubidium, Technetium, Calcium, Sulfur)
            .forEach { addLiquidAndPlasma(it) }

        // Ore Properties

        // World Gen Ores
        Andradite.setProperty(PropertyKey.ORE, OreProperty())
        Biotite.setProperty(PropertyKey.ORE, OreProperty())
        Uvarovite.setProperty(PropertyKey.ORE, OreProperty())
        Plutonium239.setProperty(PropertyKey.ORE, OreProperty())

        // Virtual Ores for Mining Drones
        Uranium.setProperty(PropertyKey.ORE, OreProperty())
        Plutonium241.setProperty(PropertyKey.ORE, OreProperty())
        Chrome.setProperty(PropertyKey.ORE, OreProperty())
        Cadmium.setProperty(PropertyKey.ORE, OreProperty())

        // Ore Byproducts
        var oreProp: OreProperty = Andradite.getProperty(PropertyKey.ORE)
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

        // Magnetic Materials
        ChromiumGermaniumTelluride.getProperty(PropertyKey.INGOT)
            .magneticMaterial = ChromiumGermaniumTellurideMagnetic

        Europium.getProperty(PropertyKey.INGOT)
            .magneticMaterial = Magnetium

        // Blast Properties
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

        // Fluid Pipe Properties
        Inconel718.setProperty(PropertyKey.FLUID_PIPE,
                               FluidPipeProperties(2010, 175,
                                                   true, true, true, false))

        RhodiumPlatedPalladium.setProperty(PropertyKey.FLUID_PIPE,
                                           FluidPipeProperties(6120, 225,
                                                               true, true, true, false))

        // Wire Properties
        Rutherfordium.setProperty(PropertyKey.WIRE, WireProperties(V[ZPM].toInt(), 8, 2))

        Seaborgium.setProperty(PropertyKey.WIRE, WireProperties(V[UEV].toInt(), 4, 16))

        // Tool Properties
        Naquadah.setProperty(PropertyKey.TOOL, MaterialToolProperty(20.0F, 8.0F, 2048, 4))
        Iridium.setProperty(PropertyKey.TOOL, MaterialToolProperty(4.8F, 10.0F, 2560, 4))

        // Material Formulas
        Biotite.setFormula("KMg3Al2(AlSi3O10)F2", true)
        Mica.setFormula("KAl2(AlSi3O10)F2", true)
        Bauxite.setFormula("(Al2O3)3(TiO2)2(H2O)2?", true)
        Graphene.setFormula("C8", true)
        PalladiumRaw.setFormula("PdCl2", true)
        RarestMetalMixture.setFormula("IrOs?", true)
        IridiumMetalResidue.setFormula("Ir2O3", true)

        // Material RGBs
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

    // region Short-circuit Material Properties Adders

    private fun addIngot(material: Material) = material.setProperty(PropertyKey.INGOT, IngotProperty())

    private fun addDust(material: Material) = material.setProperty(PropertyKey.DUST, DustProperty())

    private fun addLiquid(material: Material) = material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))

    private fun addPlasma(material: Material) = material.getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())

    private fun addLiquidAndPlasma(material: Material)
    {
        material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))
        material.getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())
    }

    // endregion

}