package magicbook.gtlitecore.api.unification

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.FluidBuilder
import gregtech.api.fluids.attribute.FluidAttributes
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Arsenic
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Blaze
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Dysprosium
import gregtech.api.unification.material.Materials.EXT2_METAL
import gregtech.api.unification.material.Materials.EXT_METAL
import gregtech.api.unification.material.Materials.Electrotine
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Flint
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Neodymium
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Obsidian
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Phosphate
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.RareEarth
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.STD_METAL
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SaltWater
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.Samarium
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.SteelMagnetic
import gregtech.api.unification.material.Materials.Stone
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.material.Materials.Yttrium
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
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROTOR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.HIGH_SIFTER_OUTPUT
import gregtech.api.unification.material.info.MaterialFlags.MORTAR_GRINDABLE
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING
import gregtech.api.unification.material.info.MaterialFlags.NO_WORKING
import gregtech.api.unification.material.info.MaterialIconSet.BRIGHT
import gregtech.api.unification.material.info.MaterialIconSet.CERTUS
import gregtech.api.unification.material.info.MaterialIconSet.DIAMOND
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.EMERALD
import gregtech.api.unification.material.info.MaterialIconSet.FINE
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
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialFlags.Companion.DISABLE_CRYSTALLIZATION
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialFlags.Companion.GENERATE_BOULE
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialIconSet.Companion.AEROTHEUM
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialIconSet.Companion.CRYOTHEUM
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialIconSet.Companion.MAGNETO
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialIconSet.Companion.NANOPARTICLES
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialIconSet.Companion.PETROTHEUM
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialIconSet.Companion.PYROTHEUM
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.averageRGB
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
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, GENERATE_BOULE)
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
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
            .build()
            .setFormula("Mg5Al2Si3O10(OH)8", true)

        // 2009 Albite
        @JvmField
        val Albite: Material = Material.Builder(2009, gtliteId("albite"))
            .gem()
            .ore()
            .color(0xC4A997).iconSet(CERTUS)
            .components(Sodium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
            .build()

        // 2010 Fluorite
        @JvmField
        val Fluorite: Material = Material.Builder(2010, gtliteId("fluorite"))
            .gem()
            .ore()
            .color(0x276A4C).iconSet(GEM_HORIZONTAL)
            .components(Calcium, 1, Fluorine, 2)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, GENERATE_BOULE)
            .build()

        // 2011 Anorthite
        @JvmField
        val Anorthite: Material = Material.Builder(2011, gtliteId("anorthite"))
            .gem()
            .ore()
            .color(0x595853).iconSet(CERTUS)
            .components(Calcium, 1, Aluminium, 2, Silicon, 2, Oxygen, 8)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
            .build()

        // 2012 Oligoclase
        @JvmField
        val Oligoclase: Material = Material.Builder(2012, gtliteId("oligoclase"))
            .gem()
            .ore()
            .color(0xC4A997).iconSet(CERTUS)
            .components(Sodium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
            .build()

        // 2013 Labradorite
        @JvmField
        val Labradorite: Material = Material.Builder(2013, gtliteId("labradorite"))
            .gem()
            .ore()
            .color(0x5C7181).iconSet(RUBY)
            .components(Albite, 2, Anorthite, 3)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
            .build()

        // 2014 Bytownite
        @JvmField
        val Bytownite: Material = Material.Builder(2014, gtliteId("bytownite"))
            .gem()
            .ore()
            .color(0xC99C67).iconSet(LAPIS)
            .components(Albite, 1, Anorthite, 4)
            .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
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
            .flags(DECOMPOSITION_BY_ELECTROLYZING, HIGH_SIFTER_OUTPUT, GENERATE_LENS, GENERATE_BOULE)
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
            .color(0xB09D99)
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
            .flags(CRYSTALLIZABLE, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
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
            .flags(HIGH_SIFTER_OUTPUT, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
            .build()

        // 2030 Nephelite
        @JvmField
        val Nephelite: Material = Material.Builder(2030, gtliteId("nephelite"))
            .gem()
            .ore()
            .color(0xE56842).iconSet(CERTUS)
            .components(Potassium, 1, Sodium, 3, Aluminium, 4, Silicon, 4, Oxygen, 16)
            .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
            .build()
            .setFormula("KNa3(AlSiO4)4", true)

        // 2031 Aegirine
        @JvmField
        val Aegirine: Material = Material.Builder(2031, gtliteId("aegirine"))
            .gem()
            .ore()
            .color(0x4ACA3B).iconSet(EMERALD)
            .components(Sodium, 1, Iron, 1, Silicon, 2, Oxygen, 6)
            .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
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
            .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
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

        // 2053 Jade
        @JvmField
        val Jade: Material = Material.Builder(2053, gtliteId("jade"))
            .gem(2)
            .ore()
            .color(0x006400).iconSet(RUBY)
            .components(Sodium, 1, Aluminium, 1, Silicon, 2, Oxygen, 6)
            .flags(CRYSTALLIZABLE, GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
            .build()

        // 2054 Jasper
        @JvmField
        val Jasper: Material = Material.Builder(2054, gtliteId("jasper"))
            .gem(2)
            .ore()
            .color(0xC85050).iconSet(EMERALD)
            .components(Calcium, 1, Magnesium, 5, Oxygen, 24, Hydrogen, 2, Silicon, 8)
            .flags(HIGH_SIFTER_OUTPUT, CRYSTALLIZABLE, GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
            .build()
            .setFormula("CaMg5(OH)2(Si4O11)2", true)

        // 2055 Picotite
        @JvmField
        val Picotite: Material = Material.Builder(2055, gtliteId("picotite"))
            .gem(3)
            .ore(2, 3)
            .color(0x931C24).iconSet(DIAMOND)
            .components(Iron, 1, Chrome, 2, Oxygen, 4)
            .flags(GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
            .build()

        // 2056 Manganese Monoxide
        @JvmField
        val ManganeseMonoxide: Material = Material.Builder(2056, gtliteId("manganese_monoxide"))
            .dust()
            .color(0x472400)
            .components(Manganese, 1, Oxygen, 1)
            .build()

        // 2057 Lead Chromate
        @JvmField
        val LeadChromate: Material = Material.Builder(2057, gtliteId("lead_chromate"))
            .dust()
            .color(0xFFFB00).iconSet(SHINY)
            .components(Lead, 1, Chrome, 1, Oxygen, 4)
            .build()

        // 2058 Lead Nitrate
        @JvmField
        val LeadNitrate: Material = Material.Builder(2058, gtliteId("lead_nitrate"))
            .dust()
            .color(0xFFFFFF).iconSet(SHINY)
            .components(Lead, 1, Nitrogen, 2, Oxygen, 6)
            .build()
            .setFormula("Pb(NO3)2", true)

        // 2059 Cobalt Aluminate
        @JvmField
        val CobaltAluminate: Material = Material.Builder(2059,  gtliteId("cobalt_aluminate"))
            .dust()
            .color(0x1605FF).iconSet(SHINY)
            .components(Cobalt, 1, Aluminium, 2, Oxygen, 4)
            .build()

        // 2060 Orpiment
        @JvmField
        val Orpiment: Material = Material.Builder(2060, gtliteId("orpiment"))
            .gem()
            .ore()
            .color(0xEBD352).iconSet(EMERALD)
            .components(Arsenic, 2, Sulfur, 3)
            .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
            .build()

        // 2061 Sodium Chlorate
        @JvmField
        val SodiumChlorate: Material = Material.Builder(2061, gtliteId("sodium_chlorate"))
            .dust()
            .colorAverage().iconSet(ROUGH)
            .components(Sodium, 1,  Chlorine, 1, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2062 Sodium Perchlorate
        @JvmField
        val SodiumPerchlorate: Material = Material.Builder(2062, gtliteId("sodium_perchlorate"))
            .dust()
            .color(Salt.materialRGB).iconSet(ROUGH)
            .components(Sodium, 1, Chlorine, 1, Oxygen, 4)
            .build()

        // 2063 Sodium Hypochlorite
        @JvmField
        val SodiumHypochlorite: Material = Material.Builder(2063, gtliteId("sodium_hypochlorite"))
            .dust()
            .color(0x778D56).iconSet(SHINY)
            .components(Sodium, 1, Chlorine, 1, Oxygen, 1)
            .build()

        // 2064 Tungsten Trioxide
        @JvmField
        val TungstenTrioxide: Material = Material.Builder(2064, gtliteId("tungsten_trioxide"))
            .dust()
            .color(0xC7D300).iconSet(DULL)
            .components(Tungsten, 1, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2065 Strontium Ferrite
        @JvmField
        val StrontiumFerrite: Material = Material.Builder(2065, gtliteId("strontium_ferrite"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(SHINY)
            .components(Strontium, 1, Iron, 12, Oxygen, 19)
            .flags(GENERATE_ROD, GENERATE_RING)
            .blast { b ->
                b.temp(3000, BlastProperty.GasTier.MID)
                    .blastStats(VA[EV], 40 * SECOND)
                    .vacuumStats(VA[MV], 10 * SECOND)
            }
            .build()

        // 2066 Titanium Nitrate
        @JvmField
        val TitaniumNitrate: Material = Material.Builder(2066, gtliteId("titanium_nitrate"))
            .dust()
            .colorAverage()
            .components(Titanium, 1, Nitrogen, 4, Oxygen, 12)
            .build()
            .setFormula("Ti(NO3)4", true)

        // 2067 Lithium Titanate
        @JvmField
        val LithiumTitanate: Material = Material.Builder(2067, gtliteId("lithium_titanate"))
            .ingot()
            .color(0xFE71A9).iconSet(SHINY)
            .components(Lithium, 2, Titanium, 1, Oxygen, 3)
            .flags(STD_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(3100, BlastProperty.GasTier.MID)
                    .blastStats(VA[EV], 16 * SECOND)
                    .vacuumStats(VA[MV], 8 * SECOND)
            }
            .fluidPipeProperties(2830, 200, true, true, false, false)
            .build()

        // 2068 Palladium Nitrate
        @JvmField
        val PalladiumNitrate: Material = Material.Builder(2068, gtliteId("palladium_nitrate"))
            .dust()
            .color(0x82312A).iconSet(METALLIC)
            .components(Palladium, 1, Nitrogen, 2, Oxygen, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("Pd(NO3)2", true)

        // 2069 Palladium Acetate
        @JvmField
        val PalladiumAcetate: Material = Material.Builder(2069, gtliteId("palladium_acetate"))
            .dust()
            .color(0x693C2D).iconSet(SHINY)
            .flags(DISABLE_DECOMPOSITION)
            .components(Palladium, 1, AceticAcid, 2)
            .build()
            .setFormula("Pd(CH3COOH)2", true)

        // 2070 Palladium Loaded Rutile Nanoparticles
        @JvmField
        val PalladiumLoadedRutileNanoparticles: Material = Material.Builder(2070, gtliteId("palladium_loaded_rutile_nanoparticles"))
            .dust()
            .colorAverage().iconSet(NANOPARTICLES)
            .components(Palladium, 1, Rutile, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2071 Lithium Oxide
        @JvmField
        val LithiumOxide: Material = Material.Builder(2071, gtliteId("lithium_oxide"))
            .dust()
            .color(0x9DB6B9).iconSet(DULL)
            .components(Lithium, 2, Oxygen, 1)
            .build()

        // 2072 Lithium Carbonate
        @JvmField
        val LithiumCarbonate: Material = Material.Builder(2072, gtliteId("lithium_carbonate"))
            .dust()
            .color(0xD1F3F6).iconSet(ROUGH)
            .components(Lithium, 2, Carbon, 1, Oxygen, 3)
            .build()

        // 2073 Blue Vitriol
        @JvmField
        val BlueVitriol: Material = Material.Builder(2073, gtliteId("blue_vitriol"))
            .liquid()
            .color(0x4242DE)
            .components(Copper, 1, Sulfur, 1, Oxygen, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2074 Sodium Tellurite
        @JvmField
        val SodiumTellurite: Material = Material.Builder(2074, gtliteId("sodium_tellurite"))
            .dust()
            .color(0xC6C9BE).iconSet(ROUGH)
            .flags(DISABLE_DECOMPOSITION)
            .components(Sodium, 2, Tellurium, 1, Oxygen, 3)
            .build()

        // 2075 Selenium Dioxide
        @JvmField
        val SeleniumDioxide: Material = Material.Builder(2075, gtliteId("selenium_dioxide"))
            .dust()
            .color(0xE0DDD8).iconSet(METALLIC)
            .flags(DISABLE_DECOMPOSITION)
            .components(Selenium, 1, Oxygen, 2)
            .build()

        // 2076 Tellurium Dioxide
        @JvmField
        val TelluriumDioxide: Material = Material.Builder(2076, gtliteId("tellurium_dioxide"))
            .dust()
            .color(0xE3DDB8).iconSet(METALLIC)
            .flags(DISABLE_DECOMPOSITION)
            .components(Tellurium, 1, Oxygen, 2)
            .build()

        // 2077 Selenous Acid
        @JvmField
        val SelenousAcid: Material = Material.Builder(2077, gtliteId("selenous_acid"))
            .dust()
            .color(0xE0E083).iconSet(SHINY)
            .flags(DISABLE_DECOMPOSITION)
            .components(Hydrogen, 2, Selenium, 1, Oxygen, 3)
            .build()

        // 2078 Aluminium Selenide
        @JvmField
        val AluminiumSelenide: Material = Material.Builder(2078, gtliteId("aluminium_selenide"))
            .dust()
            .color(0x969651)
            .components(Aluminium, 2, Selenium, 3)
            .build()

        // 2079 Hydrogen Selenide
        @JvmField
        val HydrogenSelenide: Material = Material.Builder(2079, gtliteId("hydrogen_selenide"))
            .gas()
            .color(0x42f554)
            .components(Hydrogen, 2, Selenium, 1)
            .build()

        // 2080 Cadmium Bromide
        @JvmField
        val CadmiumBromide: Material = Material.Builder(2080, gtliteId("cadmium_bromide"))
            .dust()
            .color(0xFF1774).iconSet(SHINY)
            .components(Cadmium, 1, Bromine, 2)
            .build()

        // 2081 Magnesium Bromide
        @JvmField
        val MagnesiumBromide: Material = Material.Builder(2081, gtliteId("magnesium_bromide"))
            .dust()
            .color(0x5F4C32).iconSet(METALLIC)
            .components(Magnesium, 1, Bromine, 2)
            .build()

        // 2082 HRA Magnesium
        @JvmField
        val HRAMagnesium: Material = Material.Builder(2082, gtliteId("hra_magnesium"))
            .dust()
            .color(Magnesium.materialRGB).iconSet(SHINY)
            .components(Magnesium, 1)
            .build()

        // 2083 Hydrobromic Acid
        @JvmField
        val HydrobromicAcid: Material = Material.Builder(2083, gtliteId("hydrobromic_acid"))
            .liquid(FluidBuilder().attributes(FluidAttributes.ACID))
            .color(0x8D1212)
            .components(Hydrogen, 1, Bromine, 1)
            .build()

        // 2084 Dimethylcadmium
        @JvmField
        val Dimethylcadmium: Material = Material.Builder(2084, gtliteId("dimethylcadmium"))
            .liquid()
            .color(0x5C037F)
            .components(Carbon, 2, Hydrogen, 6, Cadmium, 1)
            .build()
            .setFormula("(CH3)2Cd", true)

        // 2085 Cadmium Selenide
        @JvmField
        val CadmiumSelenide: Material = Material.Builder(2085, gtliteId("cadmium_selenide"))
            .dust()
            .color(0x983034).iconSet(METALLIC)
            .components(Cadmium, 1, Selenium, 1)
            .build()

        // 2086 Prasiolite
        @JvmField
        val Prasiolite: Material = Material.Builder(2086, gtliteId("prasiolite"))
            .gem()
            .ore()
            .color(0x9EB749).iconSet(QUARTZ)
            .components(SiliconDioxide, 5, Iron, 1)
            .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
            .build()

        // 2087 ZBLAN Glass
        @JvmField
        val ZBLANGlass: Material = Material.Builder(2087, gtliteId("zblan_glass"))
            .ingot()
            .fluid()
            .color(0xACB4BC).iconSet(SHINY)
            .components(Zirconium, 5, Barium, 2, Lanthanum, 1, Aluminium, 1, Sodium, 2, Fluorine, 6)
            .flags(NO_SMASHING, NO_WORKING, DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2", true)

        // 2088 Er-doped ZBLAN Glass
        @JvmField
        val ErbiumDopedZBLANGlass: Material = Material.Builder(2088, gtliteId("erbium_doped_zblan_glass"))
            .ingot()
            .color(0x505444).iconSet(BRIGHT)
            .components(ZBLANGlass, 1, Erbium, 1)
            .flags(NO_SMASHING, NO_WORKING, DISABLE_DECOMPOSITION, GENERATE_PLATE)
            .build().setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2Er", true)

        // 2089 Pr-doped ZBLAN Glass
        @JvmField
        val PraseodymiumDopedZBLANGlass: Material = Material.Builder(2089, gtliteId("praseodymium_doped_zblan_glass"))
            .ingot()
            .color(0xC5C88D).iconSet(BRIGHT)
            .flags(NO_SMASHING, NO_WORKING, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .components(ZBLANGlass, 1, Praseodymium, 1)
            .build()
            .setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2Pr", true)

        // 2090 Ozone
        @JvmField
        val Ozone: Material = Material.Builder(2090, gtliteId("ozone"))
            .gas()
            .color(0xBEF4FA)
            .components(Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2091 Cubic Zirconia
        @JvmField
        val CubicZirconia: Material = Material.Builder(2091, gtliteId("cubic_zirconia"))
            .gem()
            .color(0xFFDFE2).iconSet(DIAMOND)
            .components(Zirconium, 1, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
            .build()
            .setFormula("c-ZrO2", true)

        // 2092 Bismuth Telluride
        @JvmField
        val BismuthTelluride: Material = Material.Builder(2092, gtliteId("bismuth_telluride"))
            .dust()
            .color(0x0E8933).iconSet(DULL)
            .components(Bismuth, 2, Tellurium, 3)
            .build()

        // 2093 Magneto Resonatic
        @JvmField
        val MagnetoResonatic: Material = Material.Builder(2093, gtliteId("magneto_resonatic"))
            .gem()
            .color(0xFF97FF).iconSet(MAGNETO)
            .components(BismuthTelluride, 4, Prasiolite, 3, CubicZirconia, 1, SteelMagnetic, 1)
            .flags(NO_SMELTING, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
            .build()
            .setFormula("(Bi2Te3)4((SiO2)5Fe)3(ZrO2)Fe", true)

        // 2094 Lanthanum Oxide
        @JvmField
        val LanthanumOxide: Material = Material.Builder(2094, gtliteId("lanthanum_oxide"))
            .dust()
            .color(0x5F7777).iconSet(SHINY)
            .components(Lanthanum, 2, Oxygen, 3)
            .build()

        // 2095 Cerium Oxide
        @JvmField
        val CeriumOxide: Material = Material.Builder(2095, gtliteId("cerium_oxide"))
            .dust()
            .color(0x10937F).iconSet(METALLIC)
            .components(Cerium, 1, Oxygen, 2)
            .build()

        // 2096 Praseodymium Oxide
        @JvmField
        val PraseodymiumOxide: Material = Material.Builder(2096, gtliteId("praseodymium_oxide"))
            .dust()
            .color(0xD0D0D0).iconSet(METALLIC)
            .components(Praseodymium, 2, Oxygen, 3)
            .build()

        // 2097 Neodymium Oxide
        @JvmField
        val NeodymiumOxide: Material = Material.Builder(2097, gtliteId("neodymium_oxide"))
            .dust()
            .color(0x868686)
            .components(Neodymium, 2, Oxygen, 3)
            .build()

        // 2098 Samarium Oxide
        @JvmField
        val SamariumOxide: Material = Material.Builder(2098, gtliteId("samarium_oxide"))
            .dust()
            .color(0xFFFFDD)
            .components(Samarium, 2, Oxygen, 3)
            .build()

        // 2099 Europium Oxide
        @JvmField
        val EuropiumOxide: Material = Material.Builder(2099, gtliteId("europium_oxide"))
            .dust()
            .color(0x20AAAA).iconSet(SHINY)
            .components(Europium, 2, Oxygen, 3)
            .build()

        // 2100 Gadolinium Oxide
        @JvmField
        val GadoliniumOxide: Material = Material.Builder(2100, gtliteId("gadolinium_oxide"))
            .dust()
            .color(0xEEEEFF).iconSet(METALLIC)
            .components(Gadolinium, 2, Oxygen, 3)
            .build()

        // 2101 Terbium Oxide
        @JvmField
        val TerbiumOxide: Material = Material.Builder(2101, gtliteId("terbium_oxide"))
            .dust()
            .color(0xA264A2).iconSet(METALLIC)
            .components(Terbium, 2, Oxygen, 3)
            .build()

        // 2102 Dysprosium Oxide
        @JvmField
        val DysprosiumOxide: Material = Material.Builder(2102, gtliteId("dysprosium_oxide"))
            .dust()
            .color(0xD273D2).iconSet(METALLIC)
            .components(Dysprosium, 2, Oxygen, 3)
            .build()

        // 2103 Holmium Oxide
        @JvmField
        val HolmiumOxide: Material = Material.Builder(2103, gtliteId("holmium_oxide"))
            .dust()
            .color(0xAF7F2A).iconSet(SHINY)
            .components(Holmium, 2, Oxygen, 3)
            .build()

        // 2104 Erbium Oxide
        @JvmField
        val ErbiumOxide: Material = Material.Builder(2104, gtliteId("erbium_oxide"))
            .dust()
            .color(0xE07A32).iconSet(METALLIC)
            .components(Erbium, 2, Oxygen, 3)
            .build()

        // 2105 Thulium Oxide
        @JvmField
        val ThuliumOxide: Material = Material.Builder(2105, gtliteId("thulium_oxide"))
            .dust()
            .color(0x3B9E8B)
            .components(Thulium, 2, Oxygen, 3)
            .build()

        // 2106 Ytterbium Oxide
        @JvmField
        val YtterbiumOxide: Material = Material.Builder(2106, gtliteId("ytterbium_oxide"))
            .dust()
            .color(0xA9A9A9)
            .components(Ytterbium, 2, Oxygen, 3)
            .build()

        // 2107 Lutetium Oxide
        @JvmField
        val LutetiumOxide: Material = Material.Builder(2107, gtliteId("lutetium_oxide"))
            .dust()
            .color(0x11BBFF).iconSet(METALLIC)
            .components(Lutetium, 2, Oxygen, 3)
            .build()

        // 2108 Scandium Oxide
        @JvmField
        val ScandiumOxide: Material = Material.Builder(2108, gtliteId("scandium_oxide"))
            .dust()
            .color(0x43964F).iconSet(METALLIC)
            .components(Scandium, 2, Oxygen, 3)
            .build()

        // 2109 Yttrium Oxide
        @JvmField
        val YttriumOxide: Material = Material.Builder(2109, gtliteId("yttrium_oxide"))
            .dust()
            .color(0x78544E).iconSet(SHINY)
            .components(Yttrium, 2, Oxygen, 3)
            .build()

        // 2110 Promethium Oxide
        @JvmField
        val PromethiumOxide: Material = Material.Builder(2110, gtliteId("promethium_oxide"))
            .dust()
            .color(0x1B8828).iconSet(METALLIC)
            .components(Promethium, 2, Oxygen, 3)
            .build()

        // 2111 La-Pr-Nd-Ce Oxides Solution
        @JvmField
        val LaPrNdCeOxidesSolution: Material = Material.Builder(2111, gtliteId("la_pr_nd_ce_oxides_solution"))
            .liquid()
            .color(0x9CE3DB)
            .components(LanthanumOxide, 1, PraseodymiumOxide, 1, NeodymiumOxide, 1, CeriumOxide, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 2112 Sc-Eu-Gd-Sm Oxides Solution
        @JvmField
        val ScEuGdSmOxidesSolution: Material = Material.Builder(2112, gtliteId("sc_eu_gd_sm_oxides_solution"))
            .liquid()
            .color(0xFFFF99)
            .components(ScandiumOxide, 1, EuropiumOxide, 1, GadoliniumOxide, 1, SamariumOxide, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 2113 Y-Tb-Dy-Ho Oxides Solution
        @JvmField
        val YTbDyHoOxidesSolution: Material = Material.Builder(2113, gtliteId("y_tb_dy_ho_oxides_solution"))
            .liquid()
            .color(0x99FF99)
            .components(YttriumOxide, 1, TerbiumOxide, 1, DysprosiumOxide, 1, HolmiumOxide, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 2114 Er-Tm-Yb-Lu Oxides Solution
        @JvmField
        val ErTmYbLuOxidesSolution: Material = Material.Builder(2114, gtliteId("er_tm_yb_lu_oxides_solution"))
            .liquid()
            .color(0xFFB3FF)
            .components(ErbiumOxide, 1, ThuliumOxide, 1, YtterbiumOxide, 1, LutetiumOxide, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 2115 Platinum Group Residue
        @JvmField
        val PlatinumGroupResidue: Material = Material.Builder(2115, gtliteId("platinum_group_residue"))
            .dust()
            .color(0x64632E).iconSet(ROUGH)
            .components(Iridium, 1, Osmium, 1, Rhodium, 1, Ruthenium, 1, RareEarth, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("RuRhIr2Os(HNO3)3", true)

        // 2116 Platinum Group Concentrate
        @JvmField
        val PlatinumGroupConcentrate: Material = Material.Builder(2116, gtliteId("platinum_group_concentrate"))
            .liquid()
            .color(0xFFFFA6)
            .components(Gold, 1, Platinum, 1, Palladium, 1, HydrochloricAcid, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("AuPtPd(HCl)6", true)

        // 2117 Purified Platinum Group Concentrate
        @JvmField
        val PurifiedPlatinumGroupConcentrate: Material = Material.Builder(2117, gtliteId("purified_platinum_group_concentrate"))
            .liquid()
            .color(0xFFFFC8)
            .components(Hydrogen, 2, Platinum, 1, Palladium, 1, Chlorine, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("H2PtPdCl6", true)

        // 2118 Ammonium Hexachloroplatinate
        @JvmField
        val AmmoniumHexachloroplatinate: Material = Material.Builder(2118, gtliteId("ammonium_hexachloroplatinate"))
            .liquid()
            .color(0xFEF0C2)
            .flags(DISABLE_DECOMPOSITION)
            .components(Nitrogen, 2, Hydrogen, 8, Platinum, 1, Chlorine, 6)
            .build()
            .setFormula("(NH4)2PtCl6", true)

        // 2119 Ammonium Hexachloropalladate
        @JvmField
        val AmmoniumHexachloropalladate: Material = Material.Builder(2119, gtliteId("ammonium_hexachloropalladate"))
            .liquid()
            .color(0x808080)
            .components(Nitrogen, 2, Hydrogen, 8, Palladium, 1, Chlorine, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(NH4)2PdCl6", true)

        // 2120 Sodium Nitrate
        @JvmField
        val SodiumNitrate: Material = Material.Builder(2120, gtliteId("sodium_nitrate"))
            .dust()
            .color(0x846684).iconSet(ROUGH)
            .components(Sodium, 1, Nitrogen, 1, Oxygen, 3)
            .build()

        // 2121 Hexachloroplatinic Acid
        @JvmField
        val HexachloroplatinicAcid: Material = Material.Builder(2121, gtliteId("hexachloroplatinic_acid"))
            .liquid(FluidBuilder().attribute(FluidAttributes.ACID))
            .color(0xFEF4D1)
            .components(Hydrogen, 2, Platinum, 1, Chlorine, 6)
            .build()

        // 2122 Carbon Tetrachloride
        @JvmField
        val CarbonTetrachloride: Material = Material.Builder(2122, gtliteId("carbon_tetrachloride"))
            .liquid()
            .color(0x75201A)
            .components(Carbon, 1, Chlorine, 4)
            .build()

        // 2123 Sodium Peroxide
        @JvmField
        val SodiumPeroxide: Material = Material.Builder(2123, gtliteId("sodium_peroxide"))
            .dust()
            .color(0xECFF80).iconSet(ROUGH)
            .components(Sodium, 2, Oxygen, 2)
            .build()

        // 2124 Ruthenium Trichloride
        @JvmField
        val RutheniumTrichloride: Material = Material.Builder(2124, gtliteId("ruthenium_trichloride"))
            .dust()
            .color(0x605C6C).iconSet(METALLIC)
            .components(Ruthenium, 1, Chlorine, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2125 Rhodium Trioxide
        @JvmField
        val RhodiumTrioxide: Material = Material.Builder(2125, gtliteId("rhodium_trioxide"))
            .dust()
            .color(0xD93D16).iconSet(METALLIC)
            .components(Rhodium, 2, Oxygen, 3)
            .build()

        // 2126 Sulfur Dichloride
        @JvmField
        val SulfurDichloride: Material = Material.Builder(2126, gtliteId("sulfur_dichloride"))
            .liquid()
            .color(0x761410)
            .components(Sulfur, 1, Chlorine, 2)
            .build()

        // 2127 Osmium Tetrachloride
        @JvmField
        val OsmiumTetrachloride: Material = Material.Builder(2127, gtliteId("osmium_tetrachloride"))
            .dust()
            .color(0x29080A).iconSet(METALLIC)
            .components(Osmium, 1, Chlorine, 4)
            .build()

        // 2128 Beryllium Oxide
        @JvmField
        val BerylliumOxide: Material = Material.Builder(2128, gtliteId("beryllium_oxide"))
            .ingot()
            .color(0x54C757)
            .components(Beryllium, 1, Oxygen, 1)
            .flags(GENERATE_ROD, GENERATE_RING)
            .build()

        // 2129 Hydrogen Peroxide
        @JvmField
        val HydrogenPeroxide: Material = Material.Builder(2129, gtliteId("hydrogen_peroxide"))
            .liquid()
            .color(0xD2FFFF)
            .components(Hydrogen, 2, Oxygen, 2)
            .build()

        // 2130 Graphene Oxide
        @JvmField
        val GrapheneOxide: Material = Material.Builder(2130, gtliteId("graphene_oxide"))
            .dust()
            .color(0x777777).iconSet(ROUGH)
            .components(Graphene, 1, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 2131 Yttrium Nitrate
        @JvmField
        val YttriumNitrate: Material = Material.Builder(2131, gtliteId("yttrium_nitrate"))
            .dust()
            .colorAverage().iconSet(SHINY)
            .components(Yttrium, 1, Nitrogen, 3, Oxygen, 9)
            .build()
            .setFormula("Y(NO3)3", true)

        // 2132 Barium Nitrate
        @JvmField
        val BariumNitrate: Material = Material.Builder(2132, gtliteId("barium_nitrate"))
            .dust()
            .colorAverage().iconSet(METALLIC)
            .components(Barium, 1, Nitrogen, 2, Oxygen, 6)
            .build()
            .setFormula("Ba(NO3)2", true)

        // 2133 Copper Nitrate
        @JvmField
        val CopperNitrate: Material = Material.Builder(2133, gtliteId("copper_nitrate"))
            .dust()
            .colorAverage().iconSet(DULL)
            .components(Copper, 1, Nitrogen, 2, Oxygen, 6)
            .build()
            .setFormula("Cu(NO3)2", true)

        // 2134 Yttrium Barium Copper Oxides Mixture
        @JvmField
        val YttriumBariumCopperOxidesMixture: Material = Material.Builder(2134, gtliteId("yttrium_barium_copper_oxides_mixture"))
            .dust()
            .colorAverage().iconSet(ROUGH)
            .components(Yttrium, 1, Barium, 2, Copper, 3, Oxygen, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // =======================================================================
        // 4001-6000: Second Degree Materials

        // 4001 Kovar
        @JvmField
        val Kovar: Material = Material.Builder(4001, gtliteId("kovar"))
            .ingot()
            .colorAverage().iconSet(SHINY)
            .components(Iron, 2, Nickel, 1, Cobalt, 1)
            .flags(EXT_METAL, GENERATE_RING, GENERATE_FRAME)
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
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_DOUBLE_PLATE, GENERATE_DENSE)
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
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_ROTOR)
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
            .blast { b ->
                b.temp(3850, BlastProperty.GasTier.MID) // RTM Alloy (Nichrome via 2x EV Energy Hatch)
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
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_DOUBLE_PLATE, GENERATE_DENSE)
            .blast { b ->
                b.temp(1048, BlastProperty.GasTier.LOW) // Cupronickel
                    .blastStats(VA[MV], 1 * SECOND + 4 * TICK)
            }
            .build()

        // 4012 Grisium
        @JvmField
        val Grisium: Material = Material.Builder(4012, gtliteId("grisium"))
            .ingot()
            .fluid()
            .color(0x355D6A).iconSet(METALLIC)
            .components(Titanium, 9, Carbon, 9, Potassium, 9, Lithium, 9, Sulfur, 9, Hydrogen, 5)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(3550, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[EV], 26 * SECOND)
                    .vacuumStats(VA[HV], 13 * SECOND)
            }
            .build()

        // 4013 Babbit Alloy
        @JvmField
        val BabbitAlloy: Material = Material.Builder(4013, gtliteId("babbit_alloy"))
            .ingot()
            .fluid()
            .color(0xA19CA4).iconSet(METALLIC)
            .components(Tin, 5, Lead, 36, Antimony, 8, Arsenic, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(737, BlastProperty.GasTier.LOW) // Cupronickel
                    .blastStats(VA[MV], 8 * SECOND)
            }
            .build()

        // 4014 Silicon Carbide
        @JvmField
        val SiliconCarbide: Material = Material.Builder(4014, gtliteId("silicon_carbide"))
            .ingot()
            .fluid()
            .color(0x404040).iconSet(METALLIC)
            .components(Silicon, 1, Carbon, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_FOIL,
                GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(3850, BlastProperty.GasTier.HIGH) // RTM Alloy (Nichrome via 2x EV Energy Hatch)
                    .blastStats(VA[IV], 7 * SECOND + 10 * TICK)
                    .vacuumStats(VA[EV], 3 * SECOND + 5 * TICK)
            }
            .build()

        // 4015 HSLA Steel
        @JvmField
        val HSLASteel: Material = Material.Builder(4015, gtliteId("hsla_steel"))
            .ingot()
            .fluid()
            .color(0x808080).iconSet(METALLIC)
            .components(Invar, 2, Vanadium, 1, Titanium, 1, Molybdenum, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(1711, BlastProperty.GasTier.LOW) // Kanthal
                    .blastStats(VA[HV], 25 * SECOND)
            }
            .build()

        // 4016 Incoloy-MA813
        @JvmField
        val IncoloyMA813: Material = Material.Builder(4016, gtliteId("incoloy_ma_813"))
            .ingot()
            .fluid()
            .color(0x37BF7E).iconSet(SHINY)
            .components(VanadiumSteel, 4, Niobium, 2, Chrome, 3, Nickel, 4)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b
                    .temp(4800, BlastProperty.GasTier.HIGH) // HSS-G (RTM Alloy via 2x HV Energy Hatch)
                    .blastStats(VA[IV], 34 * SECOND)
                    .vacuumStats(VA[EV], 17 * SECOND)
            }
            .build()

        // 4017 Monel 500
        @JvmField
        val Monel500: Material = Material.Builder(4017, gtliteId("monel_500"))
            .ingot()
            .fluid()
            .color(0x7777F1).iconSet(BRIGHT)
            .components(Nickel, 23, Manganese, 2, Copper, 10, Aluminium, 4, Titanium, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b
                    .temp(4500, BlastProperty.GasTier.MID) // RTM Alloy
                    .blastStats(VA[IV], 27 * SECOND)
                    .vacuumStats(VA[HV], 20 * SECOND)
            }
            .build()

        // 4018 Incoloy-MA956
        @JvmField
        val IncoloyMA956: Material = Material.Builder(4018, gtliteId("incoloy_ma_956"))
            .ingot()
            .fluid()
            .color(0xAABEBB).iconSet(METALLIC)
            .components(Iron, 16, Aluminium, 3, Chrome, 5, Yttrium, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(5325, BlastProperty.GasTier.HIGH) // HSS-G
                    .blastStats(VA[IV], 40 * SECOND)
                    .vacuumStats(VA[HV], 25 * SECOND)
            }
            .build()

        // 4019 Zirconium Carbide
        @JvmField
        val ZirconiumCarbide: Material = Material.Builder(4019, gtliteId("zirconium_carbide"))
            .ingot()
            .fluid()
            .color(0xDECAB4).iconSet(METALLIC)
            .components(Zirconium, 1, Carbon, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(3250, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[HV], 48 * SECOND)
                    .vacuumStats(VA[LV], 24 * SECOND)
            }
            .build()

        // 4020 Tantalum Carbide
        @JvmField
        val TantalumCarbide: Material = Material.Builder(4020, gtliteId("tantalum_carbide"))
            .ingot()
            .fluid()
            .color(0x56566A).iconSet(METALLIC)
            .components(Tantalum, 1, Carbon, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_ROTOR)
            .blast { b ->
                b.temp(4120, BlastProperty.GasTier.MID) // RTM Alloy
                    .blastStats(VA[EV], 60 * SECOND)
                    .vacuumStats(VA[HV], 20 * SECOND)
            }
            .build()

        // 4021 Molybdenum Disilicide
        @JvmField
        val MolybdenumDisilicide: Material = Material.Builder(4021, gtliteId("molybdenum_disilicide"))
            .ingot()
            .fluid()
            .color(0x6A5BA3).iconSet(METALLIC)
            .components(Molybdenum, 1, Silicon, 2)
            .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE,
                GENERATE_SPRING)
            .blast { b ->
                b.temp(2300, BlastProperty.GasTier.MID) // Kanthal
                    .blastStats(VA[EV], 40 * SECOND)
                    .vacuumStats(12 * SECOND)
            }
            .build()

        // 4022 Hastelloy-C276
        @JvmField
        val HastelloyC276: Material = Material.Builder(4022, gtliteId("hastelloy_c_276"))
            .ingot()
            .fluid()
            .color(0xA47657).iconSet(METALLIC)
            .components(Nickel, 12, Molybdenum, 8, Chrome, 7, Tungsten, 1, Cobalt, 1, Copper, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(4625, BlastProperty.GasTier.MID) // RTM Alloy
                    .blastStats(VA[IV], 38 * SECOND)
                    .vacuumStats(VA[HV], 14 * SECOND)
            }
            .build()

        // 4023 Hastelloy-X
        @JvmField
        val HastelloyX: Material = Material.Builder(4023, gtliteId("hastelloy_x"))
            .ingot()
            .fluid()
            .color(0x5785A4).iconSet(METALLIC)
            .components(Nickel, 8, Iron, 3, Tungsten, 4, Molybdenum, 2, Chrome, 1, Niobium, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(4200, BlastProperty.GasTier.MID) // RTM Alloy
                    .blastStats(VA[IV], 30 * SECOND)
                    .vacuumStats(VA[HV], 16 * SECOND)
            }
            .build()

        // 4024 Hastelloy-N
        @JvmField
        val HastelloyN: Material = Material.Builder(4024, gtliteId("hastelloy_n"))
            .ingot()
            .fluid()
            .color(0xDDDDDD).iconSet(METALLIC)
            .components(Yttrium, 2, Molybdenum, 4, Chrome, 2, Titanium, 2, Nickel, 15)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR)
            .blast { b ->
                b.temp(4625, BlastProperty.GasTier.HIGH) // HSS-G
                    .blastStats(VA[IV], 50 * SECOND)
                    .vacuumStats(VA[EV], 15 * SECOND)
            }
            .build()

        // 4025 Aluminium Bronze
        @JvmField
        val AluminiumBronze: Material = Material.Builder(4025, gtliteId("aluminium_bronze"))
            .ingot()
            .fluid()
            .color(0x6CB451).iconSet(METALLIC)
            .components(Aluminium, 2, Manganese, 1, Bronze, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME,
                GENERATE_DOUBLE_PLATE)
            .blast { b ->
                b.temp(1073, BlastProperty.GasTier.LOW) // Cupronickel
                    .blastStats(VA[MV], 16 * SECOND)
            }
            .build()

        // 4026 René N5
        @JvmField
        val ReneN5: Material = Material.Builder(4026, gtliteId("rene_n_5"))
            .ingot()
            .fluid()
            .color(0xD599BD).iconSet(SHINY)
            .components(Nickel, 22, Cobalt, 4, Chrome, 3, Aluminium, 3, Tungsten, 2, Hafnium, 1, Rhenium, 2, Tantalum, 3)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(6800, BlastProperty.GasTier.HIGH) // Naquadah
                    .blastStats(VA[LuV], 30 * SECOND)
                    .vacuumStats(VA[EV], 25 * SECOND)
            }
            .build()

        // 4027 Titanium Carbide
        @JvmField
        val TitaniumCarbide: Material = Material.Builder(4027, gtliteId("titanium_carbide"))
            .ingot()
            .fluid()
            .color(0xB20B3A).iconSet(METALLIC)
            .components(Titanium, 1, Carbon, 1)
            .flags(STD_METAL, DECOMPOSITION_BY_CENTRIFUGING)
            .blast { b ->
                b.temp(3430, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[HV], 50 * SECOND)
                    .vacuumStats(VA[MV], 10 * SECOND)
            }
            .build()

        // 4028 Titanium Tungsten Carbide
        @JvmField
        val TitaniumTungstenCarbide: Material = Material.Builder(4028, gtliteId("titanium_tungsten_carbide"))
            .ingot()
            .fluid()
            .color(0x800D0D).iconSet(METALLIC)
            .components(TungstenCarbide, 1, TitaniumCarbide, 2)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(3800, BlastProperty.GasTier.HIGH) // Nichrome
                    .blastStats(VA[EV], 50 * SECOND)
                    .vacuumStats(VA[HV], 15 * SECOND)
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
            .flags(NO_SMASHING, DISABLE_DECOMPOSITION) // Add Centrifuging recipe at CentrifugeRecipes#init().
            .build()
            .setFormula("(CaCO3)6(Na2LiAl2Si2(H2O)6)2(SiO2)(CaF2)?", true)

        // 6008-6010 empty now

        // 6011 Blazing Pyrotheum
        @JvmField
        val BlazingPyrotheum: Material = Material.Builder(6011, gtliteId("blazing_pyrotheum"))
            .dust()
            .liquid(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(4000)
                .customFlow().customStill())
            .iconSet(PYROTHEUM)
            .components(Blaze, 2, Redstone, 1, Sulfur, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 6012 Gelid Cryotheum
        @JvmField
        val GelidCryotheum: Material = Material.Builder(6012, gtliteId("gelid_cryotheum"))
            .dust()
            .liquid(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(2)
                .customFlow().customStill())
            .iconSet(CRYOTHEUM)
            .components(Ice, 2, Electrotine, 1, Water, 1)
            .flags(DECOMPOSITION_BY_ELECTROLYZING)
            .build()
            .setFormula("((Si(FeS2)5(CrAl2O3)Hg3)(AgAu))(H2O)3", true)

        // 6013 Tectonic Petrotheum
        @JvmField
        val TectonicPetrotheum: Material = Material.Builder(6013, gtliteId("tectonic_petrotheum"))
            .dust()
            .liquid(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(350)
                .customFlow().customFlow())
            .iconSet(PETROTHEUM)
            .components(Clay, 2, Obsidian, 1, Stone, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 6014 Zephyrean Aerotheum
        @JvmField
        val ZephyreanAerotheum: Material = Material.Builder(6014, gtliteId("zephyrean_aerotheum"))
            .dust()
            .liquid(FluidBuilder()
                .translation("gregtech.fluid.generic")
                .temperature(600)
                .customFlow().customStill())
            .iconSet(AEROTHEUM)
            .components(SiliconDioxide, 2, Saltpeter, 1, Air, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 6015 Sienna
        @JvmField
        val Sienna: Material = Material.Builder(6015, gtliteId("sienna"))
            .dust()
            .color(0x4A3724)
            .components(ManganeseMonoxide, 1, BandedIron, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 6016 Burnt Sienna
        @JvmField
        val BurntSienna: Material = Material.Builder(6016, gtliteId("burnt_sienna"))
            .dust()
            .color(0x662E2E)
            .components(ManganeseMonoxide, 1, BandedIron, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

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

        // 8005 Nitrotoluene
        @JvmField
        val Nitrotoluene: Material = Material.Builder(8005, gtliteId("nitrotoluene"))
            .liquid()
            .color(0x99236E)
            .components(Carbon, 7, Hydrogen, 7, Nitrogen, 1, Oxygen, 2)
            .build()
            .setFormula("C6H4CH3NO2", true)

        // 8006 Diaminostilbenedisulfonic Acid (DSDA)
        @JvmField
        val DiaminostilbenedisulfonicAcid: Material = Material.Builder(8006, gtliteId("diaminostilbenedisulfonic_acid"))
            .dust()
            .color(0xF2F2F2).iconSet(ROUGH)
            .components(Carbon, 14, Hydrogen, 14, Nitrogen, 2, Oxygen, 6, Sulfur, 2)
            .build()

        // 8007 Succinic Acid
        @JvmField
        val SuccinicAcid: Material = Material.Builder(8007, gtliteId("succinic_acid"))
            .dust()
            .color(0x0C3A5B).iconSet(DULL)
            .components(Carbon, 4, Hydrogen, 6, Oxygen, 4)
            .build()

        // 8008 Butanediol
        @JvmField
        val Butanediol: Material = Material.Builder(8008, gtliteId("butanediol"))
            .liquid()
            .color(0xAAC4DA)
            .components(Carbon, 4, Hydrogen, 10, Oxygen, 2)
            .build()
            .setFormula("C4H8(OH)2", true)

        // 8009 Tetrahydrofuran
        @JvmField
        val Tetrahydrofuran: Material = Material.Builder(8009, gtliteId("tetrahydrofuran"))
            .liquid()
            .color(0x0BCF52)
            .components(Carbon, 4, Hydrogen, 8, Oxygen, 1)
            .build()
            .setFormula("(CH2)4O", true)

        // 8010 Ethylene Dibromide
        @JvmField
        val EthyleneDibromide: Material = Material.Builder(8010, gtliteId("ethylene_dibromide"))
            .liquid()
            .color(0x4F1743)
            .components(Carbon, 2, Hydrogen, 4, Bromine, 2)
            .build()

        // 8011 Grignard Reagent
        @JvmField
        val GrignardReagent: Material = Material.Builder(8011, gtliteId("grignard_reagent"))
            .liquid()
            .color(0xA12AA1)
            .components(Carbon, 1, Hydrogen, 3, HRAMagnesium, 1, Bromine, 1)
            .build()

        // 8012 Ethylhexanol
        @JvmField
        val Ethylhexanol: Material = Material.Builder(8012, gtliteId("ethylhexanol"))
            .liquid()
            .color(0xFEEA9A)
            .components(Carbon, 8, Hydrogen, 10, Oxygen, 1)
            .build()

        // 8013 Di-(2-Ethylhexyl) Phosphoric Acid
        @JvmField
        val DiethylhexylPhosphoricAcid: Material = Material.Builder(8013, gtliteId("diethylhexyl_phosphoric_acid"))
            .liquid()
            .color(0xFFFF99)
            .components(Carbon, 16, Hydrogen, 35, Oxygen, 4, Phosphorus, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C8H7O)2PO2H", true)

        // 8014 Formic Acid
        @JvmField
        val FormicAcid: Material = Material.Builder(8014, gtliteId("formic_acid"))
            .liquid(FluidBuilder().attribute(FluidAttributes.ACID))
            .color(0xFFAA77)
            .components(Hydrogen, 2, Carbon, 1, Oxygen, 2)
            .build()
            .setFormula("HCOOH", true)

        // 8015 Methyl Formate
        @JvmField
        val MethylFormate: Material = Material.Builder(8015, gtliteId("methyl_formate"))
            .liquid()
            .color(0xFFAAAA)
            .components(Hydrogen, 4, Carbon, 2, Oxygen, 2)
            .build()
            .setFormula("HCO2CH3", true)

        // 8016 Thionyl Chloride
        @JvmField
        val ThionylChloride: Material = Material.Builder(8016, gtliteId("thionyl_chloride"))
            .liquid()
            .color(0xEBE863)
            .components(Sulfur, 1, Oxygen, 1, Chlorine, 2)
            .build()

        // 8017 Phthalic Anhydride
        @JvmField
        val PhthalicAnhydride: Material = Material.Builder(8017, gtliteId("phthalic_anhydride"))
            .dust()
            .color(0xEEAAEE)
            .components(Carbon, 8, Hydrogen, 4, Oxygen, 3)
            .build()
            .setFormula("C6H4(CO)2O", true)

        // 8018 Ethylanthraquinone
        @JvmField
        val Ethylanthraquinone: Material = Material.Builder(8018, gtliteId("ethylanthraquinone"))
            .liquid()
            .color(0xCC865A)
            .components(Carbon, 16, Hydrogen, 12, Oxygen, 2)
            .build()
            .setFormula("C6H4(CO)2C6H3Et", true)

        // 8019 Ethylanthrahydroquinone
        @JvmField
        val Ethylanthrahydroquinone: Material = Material.Builder(8019, gtliteId("ethylanthrahydroquinone"))
            .liquid()
            .color(0xAD531A)
            .components(Carbon, 16, Hydrogen, 18, Oxygen, 2)
            .build()
            .setFormula("C6H4(CH2OH)2C6H3Et", true)

        // 8020 Hydrazine
        @JvmField
        val Hydrazine: Material = Material.Builder(8020, gtliteId("hydrazine"))
            .liquid()
            .color(0xB50707)
            .components(Nitrogen, 2, Hydrogen, 4)
            .build()

        // 8021 Citric Acid
        @JvmField
        val CitricAcid: Material = Material.Builder(8021, gtliteId("citric_acid"))
            .liquid()
            .color(0xFFCC00)
            .components(Carbon, 6, Hydrogen, 8, Oxygen, 7)
            .build()

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

        // ...

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

        // ...

        // 12101 Free Electron Gas
        @JvmField
        val FreeElectronGas: Material = Material.Builder(12101, gtliteId("free_electron_gas"))
            .gas(FluidBuilder()
                .temperature(50)
                .translation("gregtech.fluid.generic"))
            .color(0x507BB3)
            .build()

        // 12102 Fermionic UU Matter
        @JvmField
        val FermionicUUMatter: Material = Material.Builder(12102, gtliteId("fermionic_uu_matter"))
            .liquid(FluidBuilder().temperature(125))
            .color(UUMatter.materialRGB / 3)
            .build()

        // 12103 Bosonic UU Matter
        @JvmField
        val BosonicUUMatter: Material = Material.Builder(12103, gtliteId("bosonic_uu_matter"))
            .liquid(FluidBuilder().temperature(125))
            .color(UUMatter.materialRGB - FermionicUUMatter.materialRGB)
            .build()

        // 12104 Rich Nitrogen Mixture
        @JvmField
        val RichNitrogenMixture: Material = Material.Builder(12104, gtliteId("rich_nitrogen_mixture"))
            .gas(FluidBuilder().temperature(400))
            .color(0x6891D8)
            .build()

        // 12105 Rich Ammonia Mixture
        @JvmField
        val RichAmmoniaMixture: Material = Material.Builder(12105, gtliteId("rich_ammonia_mixture"))
            .gas(FluidBuilder().temperature(400))
            .color(0x708ACD)
            .build()

        // 12106 Sea Water
        @JvmField
        val SeaWater: Material = Material.Builder(12106, gtliteId("sea_water"))
            .liquid()
            .color(0x0000FF)
            .build()
            .setFormula("H2O?", true)

        // 12107 Acidic Salt Water
        @JvmField
        val AcidicSaltWater: Material = Material.Builder(12107, gtliteId("acidic_salt_water"))
            .liquid()
            .color(averageRGB(2, SaltWater.materialRGB, SulfuricAcid.materialRGB))
            .build()
            .setFormula("(H2O)(H2SO4)?", true)

        // 12108 Chalcogen Anode Mud
        @JvmField
        val ChalcogenAnodeMud: Material = Material.Builder(12108, gtliteId("chalcogen_anode_mud"))
            .dust()
            .color(0x8A3324)
            .iconSet(FINE)
            .build()

        // 12109 Rare Earth Hydroxides Solution
        @JvmField
        val RareEarthHydroxidesSolution: Material = Material.Builder(12109, gtliteId("rare_earth_hydroxides_solution"))
            .liquid()
            .color(0x434327)
            .build()

        // 12110 Rare Earth Chlorides Solution
        @JvmField
        val RareEarthChloridesSolution: Material = Material.Builder(12110, gtliteId("rare_earth_chlorides_solution"))
            .liquid()
            .color(0x838367)
            .build()

    }

}