package magicbook.gtlitecore.api.unification.material.properties

import gregtech.api.GTValues.EV
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
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Neptunium
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
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumBisulfate
import gregtech.api.unification.material.Materials.SodiumPersulfate
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
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

class GTLiteMaterialProperties
{

    companion object
    {

        fun setMaterialProperties()
        {
            // DustProperty can be overridden to IngotProperty or GemProperty yet,
            // please see: MaterialPropertiesMixin#setProperty().
            sequenceOf(Strontium, Rhenium, Uranium, Uranium235, Uranium238,
                Selenium, Tellurium, Lanthanum, Cerium, Praseodymium, Promethium,
                Gadolinium, Terbium, Dysprosium, Holmium, Erbium, Thulium, Ytterbium,
                Scandium, Germanium, Technetium, Cadmium, Dubnium, Rutherfordium,
                Curium, Seaborgium, Bohrium, Neptunium, Fermium, Rubidium, Calcium,
                Magnesium, Francium, Thallium, Barium, Lutetium, Californium, Radium,
                Polonium, Actinium, Protactinium, Berkelium, Einsteinium, Mendelevium,
                Astatine, Nobelium, Lawrencium)
                .forEach { addIngot(it) }

            sequenceOf(Iodine).forEach { addDust(it) }

            sequenceOf(Bromine, Uranium238, Zircaloy4, Inconel718, SodiumBisulfate,
                Germanium, Rutherfordium, Dubnium, Curium, Seaborgium, Bohrium, Selenium,
                Francium, Thallium, Sodium, Californium, Radium, Scandium, Polonium, Actinium,
                Protactinium, Berkelium, Einsteinium, Mendelevium, Astatine, Nobelium, Lawrencium)
                .forEach { addLiquid(it) }

            sequenceOf(Niobium, Zinc, Krypton, Xenon, Radon, Neon)
                .forEach { addPlasma(it) }

            sequenceOf(Neptunium, Fermium, Boron, Rubidium, Technetium, Calcium)
                .forEach { addLiquidAndPlasma(it) }

            // Let andradite can generate in world natural.
            Andradite.setProperty(PropertyKey.ORE, OreProperty())
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

            // Modified Biotite and Mica properties.
            Biotite.setFormula("KMg3Al2(AlSi3O10)F2", true)
            Biotite.setProperty(PropertyKey.ORE, OreProperty())
            Biotite.getProperty(PropertyKey.ORE).setOreByProducts(Phlogopite, Muscovite, Biotite)

            Mica.setFormula("KAl2(AlSi3O10)F2", true)

            // Modified Ferrosilite properties.
            Ferrosilite.setProperty(PropertyKey.ORE, OreProperty())
            Ferrosilite.getProperty(PropertyKey.ORE).setOreByProducts(Ferrosilite, SiliconDioxide, Iron)

            // Modified Bauxite formulas.
            Bauxite.setFormula("(Al2O3)3(TiO2)2(H2O)2?", true)

            // Modified Uvarovite properties.
            Uvarovite.setProperty(PropertyKey.ORE, OreProperty())
            Uvarovite.getProperty(PropertyKey.ORE).setOreByProducts(Quartzite, Uvarovite, Lizardite)

            // Modified molybdenite properties.
            Molybdenite.getProperty(PropertyKey.ORE).directSmeltResult = null

            // Modified plutonium-239 properties.
            Uranium.setProperty(PropertyKey.ORE, OreProperty())
            Uranium.getProperty(PropertyKey.ORE).setOreByProducts(Uranium, Uranium238, Uranium235)
            Plutonium239.setProperty(PropertyKey.ORE, OreProperty())
            Plutonium239.getProperty(PropertyKey.ORE).setOreByProducts(Plutonium239, Plutonium239, Plutonium241)
            Plutonium241.setProperty(PropertyKey.ORE, OreProperty())
            Plutonium241.getProperty(PropertyKey.ORE).setOreByProducts(Plutonium241, Plutonium239, Plutonium241)

            // Modified thorium byproducts.
            Thorium.getProperty(PropertyKey.ORE).setOreByProducts(Uraninite, Lead, Actinium)

            // Add new ore properties.
            Chrome.setProperty(PropertyKey.ORE, OreProperty())
            Cadmium.setProperty(PropertyKey.ORE, OreProperty())

            // Add fluid pipe properties.
            Inconel718.setProperty(PropertyKey.FLUID_PIPE,
                FluidPipeProperties(2010, 175,
                    true, true, true, false))

            // Modified rare earth elements color.
            Promethium.materialRGB = 0x24B535
            Promethium.materialIconSet = MaterialIconSet.SHINY
            Dysprosium.materialRGB = 0xDD79DD
            Erbium.materialRGB = 0xCC6633
            Holmium.materialRGB = 0xC38E2F
            Terbium.materialRGB = 0xA274A2
            Thulium.materialRGB = 0x596BC2
            Thulium.materialIconSet = MaterialIconSet.SHINY

            Lutetium.materialIconSet = MaterialIconSet.SHINY

            // Modified superheavy elements color.
            Nobelium.materialRGB = 0xF6B5FF
            Nobelium.materialIconSet = MaterialIconSet.SHINY

            Lawrencium.materialRGB = 0xBF4655

            // Modified Graphene formula.
            Graphene.setFormula("C8", true)

            // Add blast properties.
            Rhenium.setProperty(PropertyKey.BLAST, BlastProperty(3459))
            Rhenium.getProperty(PropertyKey.BLAST).durationOverride = 13 * SECOND + 8 * TICK

            Uranium.setProperty(PropertyKey.BLAST, BlastProperty(600))
            Uranium.getProperty(PropertyKey.BLAST).setEutOverride(VA[MV])
            Uranium.getProperty(PropertyKey.BLAST).durationOverride = 15 * SECOND

            Germanium.setProperty(PropertyKey.BLAST, BlastProperty(1211))

            Americium.setProperty(PropertyKey.BLAST, BlastProperty(7500))
            Americium.getProperty(PropertyKey.BLAST).setEutOverride(VA[UV])
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

            // Modified platinum group related materials' formula.
            PalladiumRaw.setFormula("PdCl2", true);
            RarestMetalMixture.setFormula("IrOs?", true);
            IridiumMetalResidue.setFormula("Ir2O3", true)

            // Change color of Raw Growth Medium.
            RawGrowthMedium.materialRGB = 0x0B2E12

            // Add tool properties.
            Naquadah.setProperty(PropertyKey.TOOL, MaterialToolProperty(20.0F, 8.0F, 2048, 4))
            Iridium.setProperty(PropertyKey.TOOL, MaterialToolProperty(4.8F, 10.0F, 2560, 4))

            // Add cable properties.
            Seaborgium.setProperty(PropertyKey.WIRE, WireProperties(V[UEV].toInt(), 4, 16, false))

            // Add magnetic properties.
            ChromiumGermaniumTelluride.getProperty(PropertyKey.INGOT).magneticMaterial = ChromiumGermaniumTellurideMagnetic

        }

        // -------------------------------------------------------------------------------------------------------------
        // Quick-path of add MaterialProperty to a material.

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

        private fun addGem(material: Material) = material.setProperty(PropertyKey.GEM, GemProperty())

        private fun addDust(material: Material) = material.setProperty(PropertyKey.DUST, DustProperty())

        private fun addLiquid(material: Material) = material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))

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