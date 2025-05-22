package magicbook.gtlitecore.api.unification.material.properties

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
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Ferrosilite
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
import gregtech.api.unification.material.Materials.Mercury
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
import gregtech.api.unification.material.Materials.Phosphate
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
import gregtech.api.unification.material.info.MaterialIconSet
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.FluidPipeProperties
import gregtech.api.unification.material.properties.FluidProperty
import gregtech.api.unification.material.properties.GemProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.MaterialToolProperty
import gregtech.api.unification.material.properties.OreProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.WireProperties
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aegirine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Albite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Anorthite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Augite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Baddeleyite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bytownite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Celestine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTelluride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTellurideMagnetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cryolite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cuprite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dolomite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorapatite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Forsterite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jade
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jasper
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kaolinite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Labradorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lizardite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Muscovite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nephelite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Oligoclase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Orpiment
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phlogopite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Picotite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Strontianite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tanzanite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Wollastonite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
class GTLiteMaterialProperties
{

    companion object
    {

        /**
         * We modified some features of `MaterialProperty`, [DustProperty] can be overridden to [IngotProperty]
         * or [GemProperty] now, please see: [magicbook.gtlitecore.mixin.gregtech.MaterialPropertiesMixin.setProperty].
         */
        fun setMaterialProperties()
        {
            // Ingot Property
            sequenceOf(Strontium, Rhenium, Uranium, Uranium235, Uranium238,
                Selenium, Tellurium, Lanthanum, Cerium, Praseodymium, Promethium,
                Gadolinium, Terbium, Dysprosium, Holmium, Erbium, Thulium, Ytterbium,
                Scandium, Germanium, Technetium, Cadmium, Dubnium, Rutherfordium,
                Curium, Seaborgium, Bohrium, Neptunium, Fermium, Rubidium, Calcium,
                Magnesium, Francium, Thallium, Barium, Lutetium, Californium, Radium,
                Polonium, Actinium, Protactinium, Berkelium, Einsteinium, Mendelevium,
                Astatine, Nobelium, Lawrencium, Mercury, Roentgenium, Moscovium,
                Meitnerium, Copernicium, Nihonium, Livermorium, Tennessine, Sulfur)
                .forEach { addIngot(it) }

            // Dust Property
            sequenceOf(Iodine).forEach { addDust(it) }

            // Liquid
            sequenceOf(Bromine, Uranium238, Zircaloy4, Inconel718, SodiumBisulfate,
                Germanium, Rutherfordium, Dubnium, Curium, Seaborgium, Bohrium, Selenium,
                Francium, Thallium, Sodium, Californium, Radium, Scandium, Polonium, Actinium,
                Protactinium, Berkelium, Einsteinium, Mendelevium, Astatine, Nobelium,
                Lawrencium, Ytterbium, Rhenium, Roentgenium, Moscovium, Meitnerium, Copernicium,
                Nihonium, Livermorium, Tennessine, Thulium, Promethium, Barium, Tellurium,
                Holmium, Erbium, Gadolinium)
                .forEach { addLiquid(it) }

            // Plasma
            sequenceOf(Niobium, Zinc, Krypton, Xenon, Radon, Neon, Bismuth, Thorium, Silver, Titanium,
                Lead)
                .forEach { addPlasma(it) }

            // Liquid & Plasma
            sequenceOf(Neptunium, Fermium, Boron, Rubidium, Technetium, Calcium, Sulfur)
                .forEach { addLiquidAndPlasma(it) }

            // Ore Property
            Andradite.setProperty(PropertyKey.ORE, OreProperty()) // New world gen ores
            Biotite.setProperty(PropertyKey.ORE, OreProperty()) // New world gen ores
            Ferrosilite.setProperty(PropertyKey.ORE, OreProperty()) // New world gen ores
            Uvarovite.setProperty(PropertyKey.ORE, OreProperty()) // New world gen ores
            Plutonium239.setProperty(PropertyKey.ORE, OreProperty()) // New world gen ores

            Uranium.setProperty(PropertyKey.ORE, OreProperty()) // For Mining Drone Airport
            Plutonium241.setProperty(PropertyKey.ORE, OreProperty()) // For Mining Drone Airport
            Chrome.setProperty(PropertyKey.ORE, OreProperty()) // For Mining Drone Airport
            Cadmium.setProperty(PropertyKey.ORE, OreProperty()) // For Mining Drone Airport

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
            oreProp.setOreByProducts(Fluorapatite, Apatite, Phosphate)

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

            // Direct Smelt Result
            oreProp = Molybdenite.getProperty(PropertyKey.ORE)
            oreProp.directSmeltResult = null

            // Magnetic Property
            ChromiumGermaniumTelluride.getProperty(PropertyKey.INGOT)
                .magneticMaterial = ChromiumGermaniumTellurideMagnetic

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
                FluidPipeProperties(6120, 225, true, true, true, false))

            // Wire Properties
            Rutherfordium.setProperty(PropertyKey.WIRE, WireProperties(V[ZPM].toInt(), 8, 2))
            Seaborgium.setProperty(PropertyKey.WIRE, WireProperties(V[UEV].toInt(), 4, 16))

            // Tool Properties
            Naquadah.setProperty(PropertyKey.TOOL, MaterialToolProperty(20.0F, 8.0F, 2048, 4))
            Iridium.setProperty(PropertyKey.TOOL, MaterialToolProperty(4.8F, 10.0F, 2560, 4))

            // Formulas
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

        /**
         * Add ingot for a material which do not have [IngotProperty].
         *
         * The feature mixin allowed to use [IngotProperty] to change the material
         * which has [DustProperty].
         *
         * @param material Material which do not have [IngotProperty].
         *
         * @see magicbook.gtlitecore.mixin.gregtech.MaterialPropertiesMixin
         */
        private fun addIngot(material: Material) = material.setProperty(PropertyKey.INGOT, IngotProperty())

        /**
         *
         */
        private fun addGem(material: Material) = material.setProperty(PropertyKey.GEM, GemProperty())

        /**
         *
         */
        private fun addDust(material: Material) = material.setProperty(PropertyKey.DUST, DustProperty())

        /**
         *
         */
        private fun addLiquid(material: Material) = material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))

        /**
         *
         */
        private fun addGas(material: Material) = material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.GAS, FluidBuilder()))

        /**
         * Add plasma for a material which exists liquid or gas (fluid).
         *
         * @param material Material which has fluids form.
         */
        private fun addPlasma(material: Material) = material.getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())

        /**
         *
         */
        private fun addLiquidAndPlasma(material: Material)
        {
            material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))
            material.getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())
        }

    }

}