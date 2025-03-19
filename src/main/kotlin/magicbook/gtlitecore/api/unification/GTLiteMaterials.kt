package magicbook.gtlitecore.api.unification

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.FluidBuilder
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Almandine
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.Bauxite
import gregtech.api.unification.material.Materials.Biotite
import gregtech.api.unification.material.Materials.BlueTopaz
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.EXT_METAL
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Ferrosilite
import gregtech.api.unification.material.Materials.Flint
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.GarnetRed
import gregtech.api.unification.material.Materials.GarnetYellow
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lapis
import gregtech.api.unification.material.Materials.Lazurite
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Monazite
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phosphate
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Potin
import gregtech.api.unification.material.Materials.Powellite
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Pyrochlore
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.RareEarth
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumPersulfate
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.material.Materials.Tantalite
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.Wulfenite
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.material.info.MaterialFlags.CRYSTALLIZABLE
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.FLAMMABLE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DOUBLE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LENS
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LONG_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROTOR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROUND
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.info.MaterialFlags.HIGH_SIFTER_OUTPUT
import gregtech.api.unification.material.info.MaterialFlags.MORTAR_GRINDABLE
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING
import gregtech.api.unification.material.info.MaterialIconSet.CERTUS
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.EMERALD
import gregtech.api.unification.material.info.MaterialIconSet.GEM_HORIZONTAL
import gregtech.api.unification.material.info.MaterialIconSet.GEM_VERTICAL
import gregtech.api.unification.material.info.MaterialIconSet.LAPIS
import gregtech.api.unification.material.info.MaterialIconSet.LIGNITE
import gregtech.api.unification.material.info.MaterialIconSet.METALLIC
import gregtech.api.unification.material.info.MaterialIconSet.OPAL
import gregtech.api.unification.material.info.MaterialIconSet.QUARTZ
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtech.api.unification.material.info.MaterialIconSet.RUBY
import gregtech.api.unification.material.info.MaterialIconSet.SAND
import gregtech.api.unification.material.info.MaterialIconSet.SHINY
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.FluidPipeProperties
import gregtech.api.unification.material.properties.GemProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.OreProperty
import gregtech.api.unification.material.properties.PropertyKey
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.gtliteId
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

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
            .ore()
            .color(0xA79E42).iconSet(DULL)
            .components(Magnesium, 3, Silicon, 2, Oxygen, 9, Hydrogen, 4)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
            .build()
            .setFormula("Mg3Si2O5(OH)4", true)

        // 2007 Muscovite
        @JvmField
        val Muscovite: Material = Material.Builder(2007, gtliteId("muscovite"))
            .ore()
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

        // 2020 Lignite
        @JvmField
        val Lignite: Material = Material.Builder(2020, gtliteId("lignite"))
            .gem(0, 1600)
            .ore()
            .color(6571590)
            .iconSet(LIGNITE)
            .flags(FLAMMABLE, DISABLE_DECOMPOSITION, NO_SMELTING, NO_SMASHING, MORTAR_GRINDABLE)
            .components(Carbon, 3, Water, 1)
            .build()
            .setFormula("C3(H2O)", true);

        // 2021 Firestone
        @JvmField
        val Firestone: Material = Material.Builder(2021, gtliteId("firestone"))
            .gem(1, 3200)
            .ore()
            .color(0xC81400)
            .iconSet(QUARTZ)
            .flags(NO_SMASHING, NO_SMELTING)
            .components(SiliconDioxide, 2, Flint, 1, Pyrite, 1)
            .build()
            .setFormula("(SiO2)3(FeS2)?", true)

        // 2022 Iron (III) Sulfate
        @JvmField
        val Iron3Sulfate: Material = Material.Builder(2022, gtliteId("iron_sulfate"))
            .dust()
            .colorAverage()
            .components(Iron, 2, Sulfur, 3, Oxygen, 12)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("Fe2(SO4)3", true)

        // 2023 Celestine
        @JvmField
        val Celestine: Material = Material.Builder(2023, gtliteId("celestine"))
            .gem(2)
            .ore()
            .color(0x4AE3E6).iconSet(OPAL)
            .components(Strontium, 1, Sulfur, 1, Oxygen, 4)
            .flags(CRYSTALLIZABLE, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS)
            .build()

        // 2024 Strontianite
        @JvmField
        val Strontianite: Material = Material.Builder(2024, gtliteId("strontianite"))
            .dust()
            .ore()
            .color(0x1DAFD3).iconSet(SAND)
            .components(Strontium, 1, Carbon, 1, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2025 Strontium Oxide
        @JvmField
        val StrontiumOxide: Material = Material.Builder(2025, gtliteId("strontium_oxide"))
            .dust()
            .colorAverage().iconSet(DULL)
            .components(Strontium, 1, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2026 Strontium Sulfide
        @JvmField
        val StrontiumSulfide: Material = Material.Builder(2026, gtliteId("strontium_sulfide"))
            .dust()
            .colorAverage().iconSet(SHINY)
            .components(Strontium, 1, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2027 Alumina
        @JvmField
        val Alumina: Material = Material.Builder(2027, gtliteId("alumina"))
            .dust()
            .color(0x78C3EB).iconSet(METALLIC)
            .components(Aluminium, 2, Oxygen, 3)
            .build()

        // 2028 Phlogopite
        @JvmField
        val Phlogopite: Material = Material.Builder(2028, gtliteId("phlogopite"))
            .dust()
            .ore()
            .color(0xDCDD0D)
            .components(Potassium, 1, Magnesium, 3, Aluminium, 1, Silicon, 3, Oxygen, 10, Fluorine, 2)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
            .build()
            .setFormula("KMg3(AlSi3O10)F2", true)

        // 2029 Baddeleyite
        @JvmField
        val Baddeleyite: Material = Material.Builder(2029, gtliteId("baddeleyite"))
            .gem()
            .ore()
            .color(0x689F9F).iconSet(GEM_HORIZONTAL)
            .components(Zirconium, 1, Oxygen, 2)
            .flags(HIGH_SIFTER_OUTPUT, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS)
            .build()

        // 2030 Nephelite
        @JvmField
        val Nephelite: Material = Material.Builder(2030, gtliteId("nephelite"))
            .gem()
            .ore()
            .color(0xE56842).iconSet(CERTUS)
            .components(Potassium, 1, Sodium, 3, Aluminium, 4, Silicon, 4, Oxygen, 16)
            .flags(GENERATE_PLATE, GENERATE_LENS)
            .build()
            .setFormula("KNa3(AlSiO4)4", true)

        // 2031 Aegirine
        @JvmField
        val Aegirine: Material = Material.Builder(2031, gtliteId("aegirine"))
            .gem()
            .ore()
            .color(0x4ACA3B).iconSet(EMERALD)
            .components(Sodium, 1, Iron, 1, Silicon, 2, Oxygen, 6)
            .flags(GENERATE_PLATE, GENERATE_LENS)
            .build()

        // 2032 Niobium Pentoxide
        @JvmField
        val NiobiumPentoxide: Material = Material.Builder(2032, gtliteId("niobium_pentoxide"))
            .dust()
            .color(0xBAB0C3).iconSet(ROUGH)
            .components(Niobium, 2, Oxygen, 5)
            .build()

        // 2033 Tantalum Pentoxide
        @JvmField
        val TantalumPentoxide: Material = Material.Builder(2033, gtliteId("tantalum_pentoxide"))
            .dust()
            .color(0x72728A).iconSet(ROUGH)
            .components(Tantalum, 2, Oxygen, 5)
            .build()

        // 2034 Calcium Difluoride
        @JvmField
        val CalciumDifluoride: Material = Material.Builder(2034, gtliteId("calcium_difluoride"))
            .dust()
            .color(0xFFFC9E).iconSet(ROUGH)
            .components(Calcium, 1, Fluorine, 2)
            .build()

        // 2035 Manganese Difluoride
        @JvmField
        val ManganeseDifluoride: Material = Material.Builder(2035, gtliteId("manganese_difluoride"))
            .dust()
            .color(0xEF4B3D).iconSet(ROUGH)
            .components(Manganese, 1, Fluorine, 2)
            .build()

        // 2036 Heavy Alkali Chlorides Solution
        @JvmField
        val HeavyAlkaliChloridesSolution: Material = Material.Builder(2036, gtliteId("heavy_alkali_chlorides_solution"))
            .liquid()
            .color(0x8F5353)
            .components(Rubidium, 1, Caesium, 2, Chlorine, 6, Water, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(RbCl)(CsCl)2Cl3(H2O)2", true)

        // 2037 Tin Dichloride
        @JvmField
        val TinDichloride: Material = Material.Builder(2037, gtliteId("tin_dichloride"))
            .dust()
            .color(0xDBDBDB).iconSet(METALLIC)
            .components(Tin, 1, Chlorine, 2)
            .build()

        // 2038 Tin Tetrachloride
        @JvmField
        val TinTetrachloride: Material = Material.Builder(2038, gtliteId("tin_tetrachloride"))
            .dust()
            .color(0x33BBF5).iconSet(METALLIC)
            .components(Tin, 1, Chlorine, 4)
            .build()

        // 2039 Caesium Hexachlorotinate
        @JvmField
        val CaesiumHexachlorotinate: Material = Material.Builder(2039, gtliteId("caesium_hexachlorotinate"))
            .dust()
            .color(0xBDAD88).iconSet(SHINY)
            .components(Caesium, 2, Tin, 1, Chlorine, 6)
            .build()

        // 2040 Rubidium Hexachlorotinate
        @JvmField
        val RubidiumHexachlorotinate: Material = Material.Builder(2040, gtliteId("rubidium_hexachlorotinate"))
            .dust()
            .color(0xBD888A).iconSet(METALLIC)
            .components(Rubidium, 2, Tin, 1, Chlorine, 6)
            .build()

        // 2041 Cryolite
        @JvmField
        val Cryolite: Material = Material.Builder(2041, gtliteId("cryolite"))
            .gem()
            .ore()
            .color(0xBFEFFF).iconSet(QUARTZ)
            .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
            .flags(GENERATE_PLATE, GENERATE_LENS)
            .build()

        // 2042 Aluminium Hydroxide
        @JvmField
        val AluminiumHydroxide: Material = Material.Builder(2042, gtliteId("aluminium_hydroxide"))
            .dust()
            .color(0xBEBEC8)
            .components(Aluminium, 1, Oxygen, 3, Hydrogen, 3)
            .build()
            .setFormula("Al(OH)3", true)

        // 2043 Sodium Aluminate
        @JvmField
        val SodiumAluminate: Material = Material.Builder(2043, gtliteId("sodium_aluminate"))
            .dust()
            .colorAverage()
            .components(Sodium, 1, Aluminium, 1, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2044 Sodium Carbonate
        @JvmField
        val SodiumCarbonate: Material = Material.Builder(2044, gtliteId("sodium_carbonate"))
            .dust()
            .colorAverage()
            .components(SodaAsh, 1, Water, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2045 Aluminium Sulfate
        @JvmField
        val AluminiumSulfate: Material = Material.Builder(2045, gtliteId("aluminium_sulfate"))
            .dust()
            .colorAverage()
            .components(Aluminium, 2, Sulfur, 3, Oxygen, 12)
            .build()
            .setFormula("Al2(SO4)3", true)

        // 2046 ZSM-5
        @JvmField
        val ZSM5: Material = Material.Builder(2046, gtliteId("zsm_5"))
            .dust()
            .colorAverage().iconSet(SHINY)
            .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE)
            .components(Sodium, 1, Aluminium, 2, Sulfur, 3, Silicon, 2, Oxygen, 18, Hydrogen, 4)
            .build()
            .setFormula("Na(Al2(SO4)3)(SiO2)2(H2O)2", true)

        // 2047 Molybdenum Trioxide
        @JvmField
        val MolybdenumTrioxide: Material = Material.Builder(2047, gtliteId("molybdenum_trioxide"))
            .dust()
            .color(0xCBCFDA)
            .iconSet(ROUGH)
            .flags(DISABLE_DECOMPOSITION)
            .components(Molybdenum, 1, Oxygen, 3)
            .build()

        // 2048 Molybdenum Flue
        @JvmField
        val MolybdenumFlue: Material = Material.Builder(2048, gtliteId("molybdenum_flue"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x39194A)
            .components(Rhenium, 1, Oxygen, 2, RareEarth, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2049 Lead Dichloride
        @JvmField
        val LeadDichloride: Material = Material.Builder(2049, gtliteId("lead_dichloride"))
            .dust()
            .color(0xF3F3F3).iconSet(ROUGH)
            .components(Lead, 1, Chlorine, 2)
            .build()

        // 2050 Trace Rhenium Flue
        @JvmField
        val TraceRheniumFlue: Material = Material.Builder(2050, gtliteId("trace_rhenium_flue"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .color(0x96D6D5)
            .components(Rhenium, 1, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2051 Perrhenic Acid
        @JvmField
        val PerrhenicAcid: Material = Material.Builder(2051, gtliteId("perrhenic_acid"))
            .dust()
            .color(0xE6DC70).iconSet(SHINY)
            .flags(DISABLE_DECOMPOSITION)
            .components(Hydrogen, 1, Rhenium, 1, Oxygen, 4)
            .build()

        // 2052 Ammonium Perrhenate
        @JvmField
        val AmmoniumPerrhenate: Material = Material.Builder(2052, gtliteId("ammonium_perrhenate"))
            .dust()
            .color(0xA69970).iconSet(METALLIC)
            .flags(DISABLE_DECOMPOSITION)
            .components(Nitrogen, 1, Hydrogen, 4, Rhenium, 1, Oxygen, 4)
            .build()

        // =======================================================================
        // 4001-6000: Second Degree Materials

        // 4001 Kovar
        @JvmField
        val Kovar: Material = Material.Builder(4001, gtliteId("kovar"))
            .ingot()
            .colorAverage().iconSet(SHINY)
            .components(Iron, 2, Nickel, 1, Cobalt, 1)
            .flags(GENERATE_ROD, GENERATE_RING)
            .build()
            .setFormula("Fe10Ni5Co3", true)

        // 4002 Maraging Steel 250
        @JvmField
        val MaragingSteel250: Material = Material.Builder(4002, gtliteId("maraging_steel_250"))
            .ingot()
            .fluid()
            .color(0x92918D).iconSet(METALLIC)
            .components(Iron, 16, Molybdenum, 1, Titanium, 1, Nickel, 4, Cobalt, 2)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(2413, BlastProperty.GasTier.LOW) // Kanthal
                    .blastStats(VA[HV], 20 * SECOND)
                    .vacuumStats(VA[MV], 2 * SECOND + 2 * TICK)
            }
            .build()

        // 4003 Inconel-625
        @JvmField
        val Inconel625: Material = Material.Builder(4003, gtliteId("inconel_625"))
            .ingot()
            .fluid()
            .color(0x80C880).iconSet(METALLIC)
            .components(Nickel, 3, Chrome, 7, Molybdenum, 10, Invar, 10, Nichrome, 13)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(2425, BlastProperty.GasTier.LOW) // Kanthal
                    .blastStats(VA[HV], 25 * SECOND)
                    .vacuumStats(VA[MV], 4 * SECOND + 4 * TICK)
            }
            .build()

        // 4004 Staballoy
        @JvmField
        val Staballoy: Material = Material.Builder(4004, gtliteId("staballoy"))
            .ingot()
            .fluid()
            .color(0x444B42).iconSet(METALLIC)
            .components(Uranium, 9, Titanium, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(3450, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[HV], 37 * SECOND + 10 * TICK)
                    .vacuumStats(VA[MV], 21 * SECOND + 9 * TICK)
            }
            .build()

        // 4005 Talonite
        @JvmField
        val Talonite: Material = Material.Builder(4005, gtliteId("talonite"))
            .ingot()
            .fluid()
            .color(0x9991A5).iconSet(SHINY)
            .components(Cobalt, 4, Chrome, 3, Phosphorus, 2, Molybdenum, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(3454, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[HV], 28 * SECOND + 10 * TICK)
                    .vacuumStats(VA[MV], 3 * SECOND + 18 * TICK)
            }
            .build()

        // 4006 Zeron-100
        @JvmField
        val Zeron100: Material = Material.Builder(4006, gtliteId("zeron_100"))
            .ingot()
            .fluid()
            .color(0xB4B414).iconSet(SHINY)
            .components(Chrome, 13, Nickel, 3, Molybdenum, 2, Copper, 10, Tungsten, 2, Steel, 20)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(5400, BlastProperty.GasTier.HIGH) // HSS-G
                    .blastStats(VA[IV], 48 * SECOND)
                    .vacuumStats(VA[EV], 5 * SECOND + 8 * TICK)
            }
            .build()

        // 4007 Watertight Steel
        @JvmField
        val WatertightSteel: Material = Material.Builder(4007, gtliteId("watertight_steel"))
            .ingot()
            .fluid()
            .color(0x7878B4).iconSet(METALLIC)
            .components(Iron, 7, Aluminium, 4, Nickel, 2, Chrome, 1, Sulfur, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b -> b
                .temp(3850, BlastProperty.GasTier.MID) // RTM Alloy (Nichrome via 2x EV Energy Hatch)
                .blastStats(VA[HV], 24 * SECOND)
                .vacuumStats(VA[MV], 2 * SECOND)
            }
            .build()

        // 4008 Stellite
        @JvmField
        val Stellite: Material = Material.Builder(4008, gtliteId("stellite"))
            .ingot()
            .fluid()
            .color(0x9991A5).iconSet(SHINY)
            .components(Cobalt, 9, Chrome, 9, Manganese, 5, Titanium, 2)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(3310, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[EV], 30 * SECOND)
                    .vacuumStats(VA[MV], 6 * SECOND)
            }
            .build()

        // 4009 Tumbaga
        @JvmField
        val Tumbaga: Material = Material.Builder(4009, gtliteId("tumbaga"))
            .ingot()
            .fluid()
            .color(0xFFB20F).iconSet(SHINY)
            .components(Gold, 7, Bronze, 3)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_DOUBLE_PLATE,
                GENERATE_DENSE)
            .blast { b ->
                b.temp(1200, BlastProperty.GasTier.LOW)
                    .blastStats(VA[MV], 24 * SECOND)
            }
            .build()

        // 4010 Eglin Steel Base
        @JvmField
        val EglinSteelBase: Material = Material.Builder(4010, gtliteId("eglin_steel_base"))
            .dust()
            .color(0x8B4513).iconSet(SAND)
            .components(Iron, 4, Kanthal, 1, Invar, 5)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 4011 Eglin Steel
        @JvmField
        val EglinSteel: Material = Material.Builder(4011, gtliteId("eglin_steel"))
            .ingot()
            .fluid()
            .color(0x8B4513).iconSet(METALLIC)
            .components(EglinSteelBase, 10, Sulfur, 1, Silicon, 1, Carbon, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(1048, BlastProperty.GasTier.LOW)
                    .blastStats(VA[MV], 1 * SECOND + 4 * TICK)
            }
            .build()

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

        // 8001 Dicyclopentadiene
        @JvmField
        val Dicyclopentadiene: Material = Material.Builder(8001, gtliteId("dicyclopentadiene"))
            .liquid(FluidBuilder().temperature(306))
            .color(0x9C388B)
            .components(Carbon, 10, Hydrogen, 12)
            .build()

        // 8002 Pentane
        @JvmField
        val Pentane: Material = Material.Builder(8002, gtliteId("pentane"))
            .liquid()
            .color(0xE8E7BE)
            .components(Carbon, 5, Hydrogen, 12)
            .build()

        // 8003 Polyisoprene
        @JvmField
        val Polyisoprene: Material = Material.Builder(8003, gtliteId("polyisoprene"))
            .polymer()
            .liquid()
            .color(0x575757).iconSet(SHINY)
            .components(Carbon, 5, Hydrogen, 8)
            .flags(NO_SMASHING, NO_SMELTING)
            .build()

        // 8004 Para Xylene
        @JvmField
        val ParaXylene: Material = Material.Builder(8004, gtliteId("para_xylene"))
            .liquid()
            .color(0x666040)
            .components(Carbon, 8, Hydrogen, 10)
            .build()
            .setFormula("C6H4(CH3)2", true)

        // =======================================================================
        // 12001-14000: Unknown Composition Materials

        // 12001 Latex
        @JvmField
        val Latex: Material = Material.Builder(12001, gtliteId("latex"))
            .dust()
            .liquid()
            .color(0xFFFADA)
            .build()

        // 12002 Resin
        @JvmField
        val Resin: Material = Material.Builder(12002, gtliteId("resin"))
            .dust()
            .liquid()
            .color(0xB5803A)
            .flags(FLAMMABLE)
            .build()

        // 12003 Rainbow Sap
        @JvmField
        val RainbowSap: Material = Material.Builder(12003, gtliteId("rainbow_sap"))
            .liquid(FluidBuilder().customStill())
            .build()

        // 12031 Green Sapphire Juice
        @JvmField
        val GreenSapphireJuice: Material = Material.Builder(12031, gtliteId("green_sapphire_juice"))
            .liquid()
            .color(GreenSapphire.materialRGB)
            .components(GreenSapphire.materialComponents)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 12032 Sapphire Juice
        @JvmField
        val SapphireJuice: Material = Material.Builder(12032, gtliteId("sapphire_juice"))
            .liquid()
            .color(Sapphire.materialRGB)
            .components(Sapphire.materialComponents)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 12033 Ruby Juice
        @JvmField
        val RubyJuice: Material = Material.Builder(12033, gtliteId("ruby_juice"))
            .liquid()
            .color(Ruby.materialRGB)
            .components(Ruby.materialComponents)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 12040 Greenhouse Gas
        @JvmField
        val GreenhouseGas: Material = Material.Builder(12040, gtliteId("greenhouse_gas"))
            .gas(FluidBuilder().temperature(313))
            .build()
            .setFormula("N78O21Ar9?", true)

        // 12050 Carbon 5 Fraction
        @JvmField
        val Carbon5Fraction: Material = Material.Builder(12050, gtliteId("carbon_5_fraction"))
            .liquid()
            .color(0x9C8638)
            .flags(FLAMMABLE)
            .build()

        // 12051 Dimerized Carbon 5 Fraction
        @JvmField
        val DimerizedCarbon5Fraction: Material = Material.Builder(12051, gtliteId("dimerized_carbon_5_fraction"))
            .liquid()
            .color(0x9C9538)
            .flags(FLAMMABLE)
            .build()

        fun setMaterialProperties()
        {
            // DustProperty can be overridden to IngotProperty or GemProperty yet,
            // please see: MaterialPropertiesMixin#setProperty().
            sequenceOf(Strontium, Rhenium).forEach { addIngot(it) }

            sequenceOf(Rubidium).forEach { addDust(it) }

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

            // Add fluid pipe properties.
            Inconel718.setProperty(PropertyKey.FLUID_PIPE,
                FluidPipeProperties(2010, 175,
                    true, true, true, false))

            // Add blast properties.
            Rhenium.setProperty(PropertyKey.BLAST, BlastProperty(3459))
            Rhenium.getProperty(PropertyKey.BLAST).durationOverride = 13 * SECOND + 8 * TICK

        }

        fun setMaterialFlags()
        {
            // foil
            Nickel.addFlags(GENERATE_FOIL)
            Niobium.addFlags(GENERATE_FOIL)
            Zirconium.addFlags(GENERATE_FOIL)
            Hafnium.addFlags(GENERATE_FOIL)
            Kanthal.addFlags(GENERATE_FOIL)

            // frameGt
            Potin.addFlags(GENERATE_FRAME)

            // gear
            RhodiumPlatedPalladium.addFlags(GENERATE_GEAR)
            Darmstadtium.addFlags(GENERATE_GEAR)

            // gearSmall
            Neutronium.addFlags(GENERATE_SMALL_GEAR)

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
            Zircon.addFlags(GENERATE_LENS)

            // plate
            Clay.addFlags(GENERATE_PLATE)
            Inconel718.addFlags(GENERATE_PLATE)
            Niobium.addFlags(GENERATE_PLATE)
            Zirconium.addFlags(GENERATE_PLATE)
            Hafnium.addFlags(GENERATE_PLATE)
            Graphite.addFlags(GENERATE_PLATE)
            Rhenium.addFlags(GENERATE_PLATE)

            // plateDouble
            Inconel718.addFlags(GENERATE_DOUBLE_PLATE)
            Invar.addFlags(GENERATE_DOUBLE_PLATE)

            // plateDense
            WroughtIron.addFlags(GENERATE_DENSE)
            Steel.addFlags(GENERATE_DENSE)
            Aluminium.addFlags(GENERATE_DENSE)
            StainlessSteel.addFlags(GENERATE_DENSE)
            Titanium.addFlags(GENERATE_DENSE)
            Neutronium.addFlags(GENERATE_DENSE)
            Invar.addFlags(GENERATE_DENSE)
            Potin.addFlags(GENERATE_DENSE)

            // rotor
            WroughtIron.addFlags(GENERATE_ROTOR)
            TinAlloy.addFlags(GENERATE_ROTOR)

            // round
            Steel.addFlags(GENERATE_ROUND)

            // stick

            // stickLong
            Chrome.addFlags(GENERATE_LONG_ROD)

            // spring
            TinAlloy.addFlags(GENERATE_SPRING)

            // springSmall
            WroughtIron.addFlags(GENERATE_SPRING_SMALL)
            Europium.addFlags(GENERATE_SPRING_SMALL)

            // wireFine
            Titanium.addFlags(GENERATE_FINE_WIRE)
            Niobium.addFlags(GENERATE_FINE_WIRE)
            Tungsten.addFlags(GENERATE_FINE_WIRE)
            Ruthenium.addFlags(GENERATE_FINE_WIRE)
            Hafnium.addFlags(GENERATE_FINE_WIRE)
            Kanthal.addFlags(GENERATE_FINE_WIRE)

            // Disabled pyrochlore and tantalite ore composition for Niobium-Tantalum chain.
            Pyrochlore.addFlags(DISABLE_DECOMPOSITION)
            Tantalite.addFlags(DISABLE_DECOMPOSITION)
            // Disabled molybdenite, powellite and wulfenite ore composition for Molybdenum-Rhenium chain.
            Molybdenite.addFlags(DISABLE_DECOMPOSITION)
            Powellite.addFlags(DISABLE_DECOMPOSITION)
            Wulfenite.addFlags(DISABLE_DECOMPOSITION)
        }

        // Quick-path of add MaterialProperty to a material.
        private fun addIngot(material: Material) = material.setProperty(PropertyKey.INGOT, IngotProperty())
        private fun addGem(material: Material) = material.setProperty(PropertyKey.GEM, GemProperty())
        private fun addDust(material: Material) = material.setProperty(PropertyKey.DUST, DustProperty())

    }

}