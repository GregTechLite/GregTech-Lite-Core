package magicbook.gtlitecore.api.unification

import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Almandine
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.BlueTopaz
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Flint
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.GarnetRed
import gregtech.api.unification.material.Materials.GarnetYellow
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lapis
import gregtech.api.unification.material.Materials.Lazurite
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Monazite
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phosphate
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.info.MaterialFlags.CRYSTALLIZABLE
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LENS
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LONG_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.info.MaterialFlags.HIGH_SIFTER_OUTPUT
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING
import gregtech.api.unification.material.info.MaterialIconSet.CERTUS
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.EMERALD
import gregtech.api.unification.material.info.MaterialIconSet.GEM_HORIZONTAL
import gregtech.api.unification.material.info.MaterialIconSet.GEM_VERTICAL
import gregtech.api.unification.material.info.MaterialIconSet.LAPIS
import gregtech.api.unification.material.info.MaterialIconSet.QUARTZ
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtech.api.unification.material.info.MaterialIconSet.RUBY
import gregtech.api.unification.material.properties.OreProperty
import gregtech.api.unification.material.properties.PropertyKey
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.gtliteId

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteMaterials
{

    companion object
    {
        // =======================================================================
        // 1-2000: Element Materials

        // =======================================================================
        // 2001-4000: First Degree Materials

        // 2001 Dolomite
        @JvmField
        val Dolomite: Material = Material.Builder(2001, gtliteId("dolomite"))
            .dust()
            .ore()
            .color(0xBBB8B2).iconSet(DULL)
            .components(Calcium, 1, Magnesium, 1, Carbon, 2, Oxygen, 6)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
            .build()
            .setFormula("CaMg(CO3)2", true)

        // 2002 Tanzanite
        @JvmField
        val Tanzanite: Material = Material.Builder(2002, gtliteId("tanzanite"))
            .gem(2)
            .ore()
            .color(0x4000C8).iconSet(GEM_VERTICAL)
            .components(Calcium, 2, Aluminium, 3, Silicon, 3, Hydrogen, 1, Oxygen, 13)
            .flags(NO_SMASHING, NO_SMELTING, HIGH_SIFTER_OUTPUT, CRYSTALLIZABLE,
                GENERATE_LENS)
            .build()

        // 2003 Azurite
        @JvmField
        val Azurite: Material = Material.Builder(2003, gtliteId("azurite"))
            .dust()
            .ore()
            .color(0x2E6DCE).iconSet(DULL)
            .components(Copper, 3, Carbon, 2, Oxygen, 8, Hydrogen, 2)
            .build()
            .setFormula("Cu3(CO3)2(OH)2", true)

        // 2004 Forsterite
        @JvmField
        val Forsterite: Material = Material.Builder(2004, gtliteId("forsterite"))
            .gem()
            .ore()
            .color(0x1D640F).iconSet(LAPIS)
            .components(Magnesium, 2, Silicon, 1, Oxygen, 4)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS)
            .build()
            .setFormula("Mg2(SiO4)", true)

        // 2005 Augite
        @JvmField
        val Augite: Material = Material.Builder(2005, gtliteId("augite"))
            .dust()
            .ore()
            .color(0x1B1717).iconSet(ROUGH)
            .components(Calcium, 2, Magnesium, 3, Iron, 3, Silicon, 8, Oxygen, 24)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
            .build()
            .setFormula("(Ca2MgFe)(MgFe)2(Si2O6)4", true)

        // 2006 Lizardite
        @JvmField
        val Lizardite: Material = Material.Builder(2006, gtliteId("lizardite"))
            .dust()
            .color(0xA79E42).iconSet(DULL)
            .components(Magnesium, 3, Silicon, 2, Oxygen, 9, Hydrogen, 4)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
            .build()
            .setFormula("Mg3Si2O5(OH)4", true)

        // 2007 Muscovite
        @JvmField
        val Muscovite: Material = Material.Builder(2007, gtliteId("muscovite"))
            .dust()
            .color(0x8B876A)
            .components(Potassium, 1, Aluminium, 3, Silicon, 3, Hydrogen, 10, Oxygen, 12)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
            .build()
            .setFormula("KAl2(AlSi3O10)(OH)2", true)

        // 2008 Clinochlore
        @JvmField
        val Clinochlore: Material = Material.Builder(2008, gtliteId("clinochlore"))
            .gem()
            .ore()
            .color(0x303E38).iconSet(EMERALD)
            .components(Magnesium, 5, Aluminium, 2, Silicon, 3, Hydrogen, 8, Oxygen, 18)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS)
            .build()
            .setFormula("Mg5Al2Si3O10(OH)8", true)

        // 2009 Albite
        @JvmField
        val Albite: Material = Material.Builder(2009, gtliteId("albite"))
            .gem()
            .ore()
            .color(0xC4A997).iconSet(CERTUS)
            .components(Sodium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS)
            .build()

        // 2010 Fluorite
        @JvmField
        val Fluorite: Material = Material.Builder(2010, gtliteId("fluorite"))
            .gem()
            .ore()
            .color(0x276A4C).iconSet(GEM_HORIZONTAL)
            .components(Calcium, 1, Fluorine, 2)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS)
            .build()

        // 2011 Anorthite
        @JvmField
        val Anorthite: Material = Material.Builder(2011, gtliteId("anorthite"))
            .gem()
            .ore()
            .color(0x595853).iconSet(CERTUS)
            .components(Calcium, 1, Aluminium, 2, Silicon, 2, Oxygen, 8)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS)
            .build()

        // 2012 Oligoclase
        @JvmField
        val Oligoclase: Material = Material.Builder(2012, gtliteId("oligoclase"))
            .gem()
            .ore()
            .color(0xC4A997).iconSet(CERTUS)
            .components(Sodium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS)
            .build()

        // 2013 Labradorite
        @JvmField
        val Labradorite: Material = Material.Builder(2013, gtliteId("labradorite"))
            .gem()
            .ore()
            .color(0x5C7181).iconSet(RUBY)
            .components(Albite, 2, Anorthite, 3)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS)
            .build()

        // 2014 Bytownite
        @JvmField
        val Bytownite: Material = Material.Builder(2014, gtliteId("bytownite"))
            .gem()
            .ore()
            .color(0xC99C67).iconSet(LAPIS)
            .components(Albite, 1, Anorthite, 4)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS)
            .build()

        // 2015 Tenorite
        @JvmField
        val Tenorite: Material = Material.Builder(2015, gtliteId("tenorite"))
            .dust()
            .ore()
            .color(0x443744).iconSet(DULL)
            .components(Copper, 1, Oxygen, 1)
            .flags(DECOMPOSITION_BY_ELECTROLYZING)
            .build()

        // 2016 Cuprite
        @JvmField
        val Cuprite: Material = Material.Builder(2016, gtliteId("cuprite"))
            .dust()
            .ore()
            .color(0x99292E).iconSet(DULL)
            .components(Copper, 2, Oxygen, 1)
            .flags(DECOMPOSITION_BY_ELECTROLYZING)
            .build()

        // 2017 Wollastonite
        @JvmField
        val Wollastonite: Material = Material.Builder(2017, gtliteId("wollastonite"))
            .dust()
            .ore()
            .color(0xDFDFDF).iconSet(ROUGH)
            .components(Calcium, 1, Silicon, 1, Oxygen, 3)
            .flags(DECOMPOSITION_BY_ELECTROLYZING)
            .build()

        // 2018 Fluorapatite
        @JvmField
        val Fluorapatite: Material = Material.Builder(2018, gtliteId("fluorapatite"))
            .gem()
            .ore()
            .color(0x4FB3D8).iconSet(QUARTZ)
            .components(Calcium, 5, Phosphate, 3, Fluorine, 1)
            .flags(DECOMPOSITION_BY_ELECTROLYZING, HIGH_SIFTER_OUTPUT, GENERATE_LENS)
            .build()
            .setFormula("Ca5(PO4)3F", true)

        // 2019 Kaolinite
        @JvmField
        val Kaolinite: Material = Material.Builder(2019, gtliteId("kaolinite"))
            .dust()
            .ore()
            .color(0xDBCAC6).iconSet(DULL)
            .components(Aluminium, 2, Silicon, 2, Hydrogen, 4, Oxygen, 9)
            .flags(DECOMPOSITION_BY_ELECTROLYZING)
            .build()

        // =======================================================================
        // 4001-6000: Second Degree Materials

        // =======================================================================
        // 6001-8000: High Degree Materials

        // 6001 Limestone
        @JvmField
        val Limestone: Material = Material.Builder(6001, gtliteId("limestone"))
            .dust()
            .color(0xA9A9A9).iconSet(ROUGH)
            .components(Calcite, 4, Dolomite, 1, Quicklime, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(CaCO3)4(CaMg(CO3)2)?", true)

        // 6002 Komatiite
        @JvmField
        val Komatiite: Material = Material.Builder(6002, gtliteId("komatiite"))
            .dust()
            .color(0xBCB073).iconSet(ROUGH)
            .components(Olivine, 2, Magnesite, 1, Flint, 1, DarkAsh, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(Mg2Fe(SiO2)2)2(MgCO3)(SiO2)?", true)

        // 6003 Green Schist
        @JvmField
        val GreenSchist: Material = Material.Builder(6003, gtliteId("green_schist"))
            .dust()
            .color(0x56AE65).iconSet(ROUGH)
            .components(Tanzanite, 2, Quartzite, 2, Talc, 1, Calcite, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(Ca2Al3Si3HO13)2(SiO2)2(Mg3Si4H2O12)?", true)

        // 6004 Blue Schist
        @JvmField
        val BlueSchist: Material = Material.Builder(6004, gtliteId("blue_schist"))
            .dust()
            .color(0x537FAC).iconSet(ROUGH)
            .components(Azurite, 3, Sodalite, 2, Iron, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(Cu3(CO3)2(OH)2)3(Al3Si3Na4Cl)2?", true)

        // 6005 Kimberlite
        @JvmField
        val Kimberlite: Material = Material.Builder(6005, gtliteId("kimberlite"))
            .dust()
            .color(0x201313).iconSet(ROUGH)
            .components(Forsterite, 3, Augite, 3, Andradite, 2, Lizardite, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(Mg2(SiO4))3((Ca2MgFe)(MgFe)2(Si2O6)4)3(Ca3Fe2Si3O12)2?", true)

        // 6006 Slate
        @JvmField
        val Slate: Material = Material.Builder(6006, gtliteId("slate"))
            .dust()
            .color(0x756869).iconSet(ROUGH)
            .components(SiliconDioxide, 5, Muscovite, 2, Clinochlore, 2, Albite, 1)
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(SiO2)5(KAl2(AlSi3O10)(OH)2)2(Mg5Al2Si3O10(OH)8)2?", true)

        // 6007 Shale
        @JvmField
        val Shale: Material = Material.Builder(6007, gtliteId("shale"))
            .dust()
            .color(0x3F2E2F).iconSet(ROUGH)
            .components(Calcite, 6, Clay, 2, SiliconDioxide, 1, Fluorite, 1)
            .flags(NO_SMASHING, DECOMPOSITION_BY_CENTRIFUGING) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(CaCO3)6(Na2LiAl2Si2(H2O)6)2(SiO2)(CaF2)?", true)


        // =======================================================================
        // 8001-12000: Organic Chemistry Materials

        // =======================================================================
        // 12001-14000: Unknown Composition Materials

        // 12001 Latex
        @JvmField
        val Latex: Material = Material.Builder(12001, gtliteId("latex"))
            .dust()
            .liquid()
            .color(0xFFFADA)
            .build()

        fun setMaterialProperties()
        {
            // Let andradite can generate in world natural.
            Andradite.setProperty(PropertyKey.ORE, OreProperty())
            val oreProp: OreProperty = Andradite.getProperty(PropertyKey.ORE)
            oreProp.setOreByProducts(Andradite, Andradite, Calcium)
        }

        fun setMaterialFlags()
        {
            // gear
            RhodiumPlatedPalladium.addFlags(GENERATE_GEAR)
            Darmstadtium.addFlags(GENERATE_GEAR)

            // gearSmall

            // lens/craftingLens
            CertusQuartz.addFlags(GENERATE_LENS)
            Almandine.addFlags(GENERATE_LENS)
            Andradite.addFlags(GENERATE_LENS)
            BlueTopaz.addFlags(GENERATE_LENS)
            GreenSapphire.addFlags(GENERATE_LENS)
            Grossular.addFlags(GENERATE_LENS)
            Rutile.addFlags(GENERATE_LENS)
            Lazurite.addFlags(GENERATE_LENS)
            Lapis.addFlags(GENERATE_LENS)
            Pyrope.addFlags(GENERATE_LENS)
            Sodalite.addFlags(GENERATE_LENS)
            Spessartine.addFlags(GENERATE_LENS)
            Topaz.addFlags(GENERATE_LENS)
            Uvarovite.addFlags(GENERATE_LENS)
            Quartzite.addFlags(GENERATE_LENS)
            Realgar.addFlags(GENERATE_LENS)
            Malachite.addFlags(GENERATE_LENS)
            Olivine.addFlags(GENERATE_LENS)
            Opal.addFlags(GENERATE_LENS)
            Amethyst.addFlags(GENERATE_LENS)
            Apatite.addFlags(GENERATE_LENS)
            GarnetRed.addFlags(GENERATE_LENS)
            GarnetYellow.addFlags(GENERATE_LENS)
            Monazite.addFlags(GENERATE_LENS)

            // plate
            Clay.addFlags(GENERATE_PLATE)

            // plateDense
            WroughtIron.addFlags(GENERATE_DENSE)
            Steel.addFlags(GENERATE_DENSE)
            Aluminium.addFlags(GENERATE_DENSE)
            StainlessSteel.addFlags(GENERATE_DENSE)
            Titanium.addFlags(GENERATE_DENSE)
            Neutronium.addFlags(GENERATE_DENSE)

            // stick

            // stickLong
            Chrome.addFlags(GENERATE_LONG_ROD)

            // spring
            TinAlloy.addFlags(GENERATE_SPRING)

            // springSmall
            WroughtIron.addFlags(GENERATE_SPRING_SMALL)

        }

    }

}