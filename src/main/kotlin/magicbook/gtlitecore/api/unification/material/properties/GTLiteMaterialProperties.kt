package magicbook.gtlitecore.api.unification.material.properties

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.FluidBuilder
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.Bauxite
import gregtech.api.unification.material.Materials.Biotite
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Ferrosilite
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Phosphate
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumPersulfate
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.Zircaloy4
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.FluidPipeProperties
import gregtech.api.unification.material.properties.FluidProperty
import gregtech.api.unification.material.properties.GemProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.OreProperty
import gregtech.api.unification.material.properties.PropertyKey
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aegirine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Albite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Anorthite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Augite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Baddeleyite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bytownite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Celestine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cryolite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cuprite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dolomite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorapatite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Forsterite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kaolinite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Labradorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lizardite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Muscovite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nephelite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Oligoclase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phlogopite
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
            sequenceOf(Strontium, Rhenium, Uranium, Uranium235, Uranium238)
                .forEach { addIngot(it) }

            sequenceOf(Rubidium).forEach { addDust(it) }

            sequenceOf(Uranium238, Zircaloy4, Inconel718).forEach { addLiquid(it) }

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
            Plutonium239.setProperty(PropertyKey.ORE, OreProperty())
            Plutonium239.getProperty(PropertyKey.ORE).setOreByProducts(Plutonium239, Plutonium239, Plutonium241)

            // Add fluid pipe properties.
            Inconel718.setProperty(
                PropertyKey.FLUID_PIPE,
                FluidPipeProperties(2010, 175,
                    true, true, true, false)
            )

            // Add blast properties.
            Rhenium.setProperty(PropertyKey.BLAST, BlastProperty(3459))
            Rhenium.getProperty(PropertyKey.BLAST).durationOverride = 13 * SECOND + 8 * TICK

            Uranium.setProperty(PropertyKey.BLAST, BlastProperty(600))
            Uranium.getProperty(PropertyKey.BLAST).setEutOverride(VA[MV])
            Uranium.getProperty(PropertyKey.BLAST).durationOverride = 15 * SECOND

        }

        // Quick-path of add MaterialProperty to a material.
        private fun addIngot(material: Material) = material.setProperty(PropertyKey.INGOT, IngotProperty())
        private fun addGem(material: Material) = material.setProperty(PropertyKey.GEM, GemProperty())
        private fun addDust(material: Material) = material.setProperty(PropertyKey.DUST, DustProperty())
        private fun addLiquid(material: Material) = material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))
        private fun addGas(material: Material) = material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.GAS, FluidBuilder()))
        private fun addPlasma(material: Material) = material.setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.PLASMA, FluidBuilder()))

    }

}