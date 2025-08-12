package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.FluidBuilder
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Actinium
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Arsenic
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Dysprosium
import gregtech.api.unification.material.Materials.EXT2_METAL
import gregtech.api.unification.material.Materials.EXT_METAL
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Mercury
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neodymium
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.NiobiumNitride
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.STD_METAL
import gregtech.api.unification.material.Materials.Samarium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Tennessine
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_BOLT_SCREW
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DOUBLE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LONG_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROTOR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.info.MaterialFlags.NO_UNIFICATION
import gregtech.api.unification.material.info.MaterialIconSet.BRIGHT
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.METALLIC
import gregtech.api.unification.material.info.MaterialIconSet.SAND
import gregtech.api.unification.material.info.MaterialIconSet.SHINY
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.MaterialToolProperty
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumGroupAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumGroupAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AlkaliEarthGroupAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AlkaliGroupAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumBronze
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BETSPerrhenate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BabbitAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CinobiteA243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EglinSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EglinSteelBase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Grisium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HafniumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyC276
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyN
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA813
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA956
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Inconel625
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jasper
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kovar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lafium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumFullereneNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumGroupAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumGroupAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Legendarium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetoResonatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MaragingSteel250
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableFlerovium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MolybdenumDisilicide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Monel500
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nitinol60
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NobleGasMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NonMetallicMixtureA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NonMetallicMixtureB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PreciousMetalAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RareEarthAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RedPhosphorus
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RefractoryAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ReneN5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaborgiumDopedCarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SiliconCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Staballoy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Stellite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tairitsium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Talonite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumHafniumSeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumTungstenCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ToxicAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TransitionAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TransitionAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TransitionAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trinaquadalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tumbaga
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WatertightSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zeron100
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZirconiumCarbide
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.ENRICHED
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.HALKONITE
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronFranciumCarbideSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaesiumCeriumCobaltIndium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyConductiveMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlutoniumPhosphide
import net.minecraft.init.Enchantments

object GTLiteSecondDegreeMaterials
{

    // @formatter:off

    fun init()
    {
        // 4001 Kovar
        Kovar = Material.Builder(4001, GTLiteMod.id("kovar"))
            .ingot()
            .colorAverage().iconSet(SHINY)
            .components(Iron, 2, Nickel, 1, Cobalt, 1)
            .flags(EXT_METAL, GENERATE_RING, GENERATE_FRAME)
            .build()
            .setFormula("Fe10Ni5Co3", true)

        // 4002 Maraging Steel 250
        MaragingSteel250 = Material.Builder(4002, GTLiteMod.id("maraging_steel_250"))
            .ingot()
            .fluid()
            .color(0x92918D).iconSet(METALLIC)
            .components(Iron, 16, Molybdenum, 1, Titanium, 1, Nickel, 4, Cobalt, 2)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_DOUBLE_PLATE)
            .blast { b ->
                b.temp(2413, BlastProperty.GasTier.LOW) // Kanthal
                    .blastStats(VA[HV], 20 * SECOND)
                    .vacuumStats(VA[MV], 2 * SECOND + 2 * TICK)
            }
            .itemPipeProperties(576, 16F)
            .build()

        // 4003 Inconel-625
        Inconel625 = Material.Builder(4003, GTLiteMod.id("inconel_625"))
            .ingot()
            .fluid()
            .color(0x80C880).iconSet(METALLIC)
            .components(Nickel, 3, Chrome, 7, Molybdenum, 10, Invar, 10, Nichrome, 13)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_BOLT_SCREW)
            .blast { b ->
                b.temp(2425, BlastProperty.GasTier.LOW) // Kanthal
                    .blastStats(VA[HV], 25 * SECOND)
                    .vacuumStats(VA[MV], 4 * SECOND + 4 * TICK)
            }
            .build()

        // 4004 Staballoy
        Staballoy = Material.Builder(4004, GTLiteMod.id("staballoy"))
            .ingot()
            .fluid()
            .color(0x444B42).iconSet(METALLIC)
            .components(Uranium, 9, Titanium, 1)
            .flags(
                EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_DOUBLE_PLATE, GENERATE_DENSE
            )
            .blast { b ->
                b.temp(3450, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[HV], 37 * SECOND + 10 * TICK)
                    .vacuumStats(VA[MV], 21 * SECOND + 9 * TICK)
            }
            .build()

        // 4005 Talonite
        Talonite = Material.Builder(4005, GTLiteMod.id("talonite"))
            .ingot()
            .fluid()
            .color(0x9991A5).iconSet(SHINY)
            .components(Cobalt, 4, Chrome, 3, Phosphorus, 2, Molybdenum, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_ROTOR, GENERATE_SPRING_SMALL)
            .blast { b ->
                b.temp(3454, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[HV], 28 * SECOND + 10 * TICK)
                    .vacuumStats(VA[MV], 3 * SECOND + 18 * TICK)
            }
            .build()

        // 4006 Zeron-100
        Zeron100 = Material.Builder(4006, GTLiteMod.id("zeron_100"))
            .ingot()
            .fluid()
            .color(0xB4B414).iconSet(SHINY)
            .components(Chrome, 13, Nickel, 3, Molybdenum, 2, Copper, 10, Tungsten, 2, Steel, 20)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_BOLT_SCREW)
            .blast { b ->
                b.temp(5400, BlastProperty.GasTier.HIGH) // HSS-G
                    .blastStats(VA[IV], 48 * SECOND)
                    .vacuumStats(VA[EV], 5 * SECOND + 8 * TICK)
            }
            .build()

        // 4007 Watertight Steel
        WatertightSteel = Material.Builder(4007, GTLiteMod.id("watertight_steel"))
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
        Stellite = Material.Builder(4008, GTLiteMod.id("stellite"))
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
        Tumbaga = Material.Builder(4009, GTLiteMod.id("tumbaga"))
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
        EglinSteelBase = Material.Builder(4010, GTLiteMod.id("eglin_steel_base"))
            .dust()
            .color(0x8B4513).iconSet(SAND)
            .components(Iron, 4, Kanthal, 1, Invar, 5)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build()

        // 4011 Eglin Steel
        EglinSteel = Material.Builder(4011, GTLiteMod.id("eglin_steel"))
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
        Grisium = Material.Builder(4012, GTLiteMod.id("grisium"))
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
        BabbitAlloy = Material.Builder(4013, GTLiteMod.id("babbit_alloy"))
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
        SiliconCarbide = Material.Builder(4014, GTLiteMod.id("silicon_carbide"))
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
        HSLASteel = Material.Builder(4015, GTLiteMod.id("hsla_steel"))
            .ingot()
            .fluid()
            .color(0x808080).iconSet(METALLIC)
            .components(Invar, 2, Vanadium, 1, Titanium, 1, Molybdenum, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_RING)
            .blast { b ->
                b.temp(1711, BlastProperty.GasTier.LOW) // Kanthal
                    .blastStats(VA[HV], 25 * SECOND)
            }
            .build()

        // 4016 Incoloy-MA813
        IncoloyMA813 = Material.Builder(4016, GTLiteMod.id("incoloy_ma_813"))
            .ingot()
            .fluid()
            .color(0x37BF7E).iconSet(SHINY)
            .components(VanadiumSteel, 4, Niobium, 2, Chrome, 3, Nickel, 4)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_BOLT_SCREW)
            .blast { b ->
                b.temp(4800, BlastProperty.GasTier.HIGH) // HSS-G (RTM Alloy via 2x HV Energy Hatch)
                    .blastStats(VA[IV], 34 * SECOND)
                    .vacuumStats(VA[EV], 17 * SECOND)
            }
            .build()

        // 4017 Monel 500
        Monel500 = Material.Builder(4017, GTLiteMod.id("monel_500"))
            .ingot()
            .fluid()
            .color(0x7777F1).iconSet(BRIGHT)
            .components(Nickel, 23, Manganese, 2, Copper, 10, Aluminium, 4, Titanium, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(4500, BlastProperty.GasTier.MID) // RTM Alloy
                    .blastStats(VA[IV], 27 * SECOND)
                    .vacuumStats(VA[HV], 20 * SECOND)
            }
            .build()

        // 4018 Incoloy-MA956
        IncoloyMA956 = Material.Builder(4018, GTLiteMod.id("incoloy_ma_956"))
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
        ZirconiumCarbide = Material.Builder(4019, GTLiteMod.id("zirconium_carbide"))
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
        TantalumCarbide = Material.Builder(4020, GTLiteMod.id("tantalum_carbide"))
            .ingot()
            .fluid()
            .color(0x56566A).iconSet(METALLIC)
            .components(Tantalum, 1, Carbon, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_ROTOR, GENERATE_FRAME)
            .blast { b ->
                b.temp(4120, BlastProperty.GasTier.MID) // RTM Alloy
                    .blastStats(VA[EV], 60 * SECOND)
                    .vacuumStats(VA[HV], 20 * SECOND)
            }
            .build()

        // 4021 Molybdenum Disilicide
        MolybdenumDisilicide = Material.Builder(4021, GTLiteMod.id("molybdenum_disilicide"))
            .ingot()
            .fluid()
            .color(0x6A5BA3).iconSet(METALLIC)
            .components(Molybdenum, 1, Silicon, 2)
            .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE,
                GENERATE_SPRING, GENERATE_SPRING_SMALL)
            .blast { b ->
                b.temp(2300, BlastProperty.GasTier.MID) // Kanthal
                    .blastStats(VA[EV], 40 * SECOND)
                    .vacuumStats(12 * SECOND)
            }
            .build()

        // 4022 Hastelloy-C276
        HastelloyC276 = Material.Builder(4022, GTLiteMod.id("hastelloy_c_276"))
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
        HastelloyX = Material.Builder(4023, GTLiteMod.id("hastelloy_x"))
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
        HastelloyN = Material.Builder(4024, GTLiteMod.id("hastelloy_n"))
            .ingot()
            .fluid()
            .color(0xDDDDDD).iconSet(METALLIC)
            .components(Yttrium, 2, Molybdenum, 4, Chrome, 2, Titanium, 2, Nickel, 15)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_SPRING_SMALL,  GENERATE_SMALL_GEAR)
            .blast { b ->
                b.temp(4625, BlastProperty.GasTier.HIGH) // HSS-G
                    .blastStats(VA[IV], 50 * SECOND)
                    .vacuumStats(VA[EV], 15 * SECOND)
            }
            .build()

        // 4025 Aluminium Bronze
        AluminiumBronze = Material.Builder(4025, GTLiteMod.id("aluminium_bronze"))
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
        ReneN5 = Material.Builder(4026, GTLiteMod.id("rene_n_5"))
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
        TitaniumCarbide = Material.Builder(4027, GTLiteMod.id("titanium_carbide"))
            .ingot()
            .fluid()
            .color(0xB20B3A).iconSet(METALLIC)
            .components(Titanium, 1, Carbon, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
            .blast { b ->
                b.temp(3430, BlastProperty.GasTier.MID) // Nichrome
                    .blastStats(VA[HV], 50 * SECOND)
                    .vacuumStats(VA[MV], 10 * SECOND)
            }
            .build()

        // 4028 Titanium Tungsten Carbide
        TitaniumTungstenCarbide = Material.Builder(4028, GTLiteMod.id("titanium_tungsten_carbide"))
            .ingot()
            .fluid()
            .color(0x800D0D).iconSet(METALLIC)
            .components(TungstenCarbide, 1, TitaniumCarbide, 2)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE, GENERATE_FRAME)
            .blast { b ->
                b.temp(3800, BlastProperty.GasTier.HIGH) // Nichrome
                    .blastStats(VA[EV], 50 * SECOND)
                    .vacuumStats(VA[HV], 15 * SECOND)
            }
            .build()

        // 4029 Trinaquadalloy
        Trinaquadalloy = Material.Builder(4029, GTLiteMod.id("trinaquadalloy"))
            .ingot()
            .fluid()
            .color(0x281832).iconSet(BRIGHT)
            .components(Trinium, 6, Naquadah, 2, Carbon, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_DOUBLE_PLATE,
                GENERATE_SPRING_SMALL, GENERATE_BOLT_SCREW)
            .blast { b ->
                b.temp(8747, BlastProperty.GasTier.HIGHER) // Trinium
                    .blastStats(VA[ZPM], 1 * MINUTE)
                    .vacuumStats(VA[IV], 45 * SECOND)
            }
            .fluidPipeProperties(7552, 400, true, true, true, true)
            .build()

        // 4030 Enriched Naquadah Alloy
        EnrichedNaquadahAlloy = Material.Builder(4030, GTLiteMod.id("enriched_naquadah_alloy"))
            .ingot(6)
            .fluid()
            .color(0x160740).iconSet(SHINY)
            .components(NaquadahEnriched, 4, Ruridit, 2, Rutherfordium, 1)
            .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE,
                GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR,
                GENERATE_SMALL_GEAR, GENERATE_FRAME, GENERATE_RING, GENERATE_SPRING_SMALL)
            .blast { b ->
                b.temp(9001, BlastProperty.GasTier.HIGHER) // Trinium
                    .blastStats(VA[ZPM], 1 * MINUTE + 40 * SECOND)
                    .vacuumStats(VA[LuV], 30 * SECOND)
            }
            .toolStats(MaterialToolProperty.Builder.of(45.0F, 24.0F, 4096, 6)
                .attackSpeed(0.6F).enchantability(36).magnetic().build())
            .rotorStats(18.0f, 6.0f, 7680)
            .cableProperties(V[UHV], 4, 8)
            .build()

        // 4031 Quantum Alloy
        QuantumAlloy = Material.Builder(4031, GTLiteMod.id("quantum_alloy"))
            .ingot(7)
            .fluid()
            .color(0x0F0F0F).iconSet(BRIGHT)
            .components(Stellite, 15, Jasper, 5, Gallium, 5, Americium, 5, Palladium, 5, Bismuth, 5, Germanium, 5, SiliconCarbide, 5)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_DOUBLE_PLATE,
                GENERATE_FOIL)
            .blast { b ->
                b.temp(11400, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UHV], 58 * SECOND)
                    .vacuumStats(VA[UV], 29 * SECOND)
            }
            .itemPipeProperties(64, 96F)
            .build()

        // 4032 HDCS (High Durability Compound Steel)
        HDCS = Material.Builder(4032, GTLiteMod.id("hdcs"))
            .ingot(7)
            .fluid()
            .color(0x334433).iconSet(SHINY)
            .components(TungstenSteel, 12, HSSS, 9, HSSG, 6, Ruridit, 3, MagnetoResonatic, 2, Plutonium241, 1)
            .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_SMALL_GEAR, GENERATE_DOUBLE_PLATE, GENERATE_RING, GENERATE_SPRING,
                GENERATE_SPRING_SMALL)
            .blast { b ->
                b.temp(10900, BlastProperty.GasTier.HIGHEST) // Adamantium (Tritanium)
                    .blastStats(VA[UHV], 1 * MINUTE + 30 * SECOND)
                    .vacuumStats(VA[ZPM], 45 * SECOND)
            }
            .rotorStats(16.5f, 3.5f, 6000)
            .toolStats(MaterialToolProperty.Builder.of(52.5F, 28.0F, 6144, 7)
                .attackSpeed(0.5F).enchantability(34)
                .enchantment(Enchantments.SHARPNESS, 5)
                .magnetic().build())
            .build()

        // 4033 Titan Steel
        TitanSteel = Material.Builder(4033, GTLiteMod.id("titan_steel"))
            .ingot(7)
            .fluid()
            .color(0xAA0D0D).iconSet(SHINY)
            .components(TitaniumTungstenCarbide, 3, Jasper, 3, Tritanium, 2)
            .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE,
                GENERATE_GEAR, GENERATE_FRAME, GENERATE_RING, GENERATE_SMALL_GEAR, GENERATE_SPRING_SMALL)
            .blast { b ->
                b.temp(10600, BlastProperty.GasTier.HIGHEST) // Tritanium
                    .blastStats(VA[UHV], 1 * MINUTE)
                    .vacuumStats(VA[UV], 28 * SECOND) }
            .toolStats(MaterialToolProperty.Builder.of(48.0F, 24.0F, 5120, 7)
                .attackSpeed(0.4F).enchantability(32)
                .enchantment(Enchantments.FIRE_ASPECT, 3)
                .magnetic().build())
            .build()

        // 4034 Tairitsium
        Tairitsium = Material.Builder(4034, GTLiteMod.id("tairitsium"))
            .ingot()
            .fluid()
            .color(0x003F5F).iconSet(METALLIC)
            .components(TungstenSteel, 8, Naquadria, 7, Bedrockium, 4, Carbon, 4, VanadiumSteel, 3, Francium, 1)
            .blast { b ->
                b.temp(10400, BlastProperty.GasTier.HIGHEST) // Tritanium
                    .blastStats(VA[UHV], 20 * SECOND)
                    .vacuumStats(VA[UV], 6 * SECOND + 10 * TICK) }
            .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE,
                GENERATE_GEAR, GENERATE_FOIL, GENERATE_FRAME, GENERATE_RING,
                GENERATE_SPRING_SMALL, GENERATE_SMALL_GEAR)
            .toolStats(MaterialToolProperty.Builder.of(36.5F, 44.2F, 4980, 7)
                .attackSpeed(0.6F).enchantability(30)
                .enchantment(Enchantments.LOOTING, 3)
                .build())
            .build()

        // 4035 Halkonite Steel
        HalkoniteSteel = Material.Builder(4035, GTLiteMod.id("halkonite_steel"))
            .ingot()
            .liquid(FluidBuilder().customStill()
                .translation("gtlitecore.fluid.molten"))
            .iconSet(HALKONITE)
            .components(CosmicNeutronium, 2, Tairitsium, 2, RedPhosphorus, 2, TitanSteel, 1, Infinity, 1)
            .blast { b -> b
                .temp(13801, BlastProperty.GasTier.HIGHEST) // Infinity
                .blastStats(VA[UEV], 40 * SECOND)
                .vacuumStats(VA[UHV], 20 * SECOND)
            }
            .flags(EXT2_METAL, NO_UNIFICATION, GENERATE_FOIL, GENERATE_FRAME,
                GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE)
            .cableProperties(V[UIV], 48, 64, false)
            .build()
            .setFormula("SpNt2((FeW)8*Nq*7?4C4(VCrFe7)3Fr)2P8(((WC)(TiC)2)3(CaMg5(OH)2(Si4O11)2)3Tr2)If", true) // Fix a little formula problem from P4.

        // 4036 Hastelloy-X78
        HastelloyX78 = Material.Builder(4036, GTLiteMod.id("hastelloy_x_78"))
            .ingot()
            .fluid()
            .color(0xD1CB0B).iconSet(SHINY)
            .components(NaquadahAlloy, 10, Rhenium, 5, Naquadria, 4, Gadolinium, 3,
                Strontium, 2, Polonium, 3, Rutherfordium, 2, Fermium, 1)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FRAME, GENERATE_GEAR,
                GENERATE_RING, GENERATE_FOIL, GENERATE_SMALL_GEAR, GENERATE_SPRING_SMALL,
                GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(12300, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UHV], 44 * SECOND)
                    .vacuumStats(VA[UV], 22 * SECOND)
            }
            .fluidPipeProperties(9300, 640, true, true, true, true)
            .toolStats(MaterialToolProperty.Builder.of(44.8F, 64.4F, 7470, 7)
                .attackSpeed(0.5F).enchantability(32)
                .magnetic()
                .build())
            .build()

        // 4037 Hastelloy-K243
        HastelloyK243 = Material.Builder(4037, GTLiteMod.id("hastelloy_k_243"))
            .ingot()
            .fluid()
            .color(0x92D959).iconSet(BRIGHT)
            .components(HastelloyX78, 5, NiobiumNitride, 2, Tritanium, 4, TungstenCarbide, 4, Promethium, 4, Mendelevium, 1)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FRAME, GENERATE_GEAR, GENERATE_RING,
                GENERATE_SMALL_GEAR, GENERATE_SPRING_SMALL)
            .blast { b ->
                b.temp(14000, BlastProperty.GasTier.HIGHEST) // Infinity
                    .blastStats(VA[UEV], 1 * MINUTE + 25 * SECOND)
                    .vacuumStats(VA[UHV], 48 * SECOND)
            }
            .toolStats(MaterialToolProperty.Builder.of(58.0F, 80.0F, 11205, 8)
                .attackSpeed(0.8F).enchantability(36)
                .magnetic()
                .build())
            .build()

        // 4038 Pikyonium 64B
        Pikyonium64B = Material.Builder(4038, GTLiteMod.id("pikyonium_64_b"))
            .ingot()
            .fluid()
            .color(0x3467BA).iconSet(SHINY)
            .components(Inconel718, 8, EglinSteel, 5, NaquadahEnriched, 4, TungstenSteel, 4, Cerium, 3,
                Antimony, 2, Platinum, 2, Ytterbium, 1)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE)
            .blast { b ->
                b.temp(10400, BlastProperty.GasTier.HIGHER) // Tritanium
                    .blastStats(VA[UV], 30 * SECOND)
                    .vacuumStats(VA[LuV], 15 * SECOND)
            }
            .rotorStats(18.2F, 5.5F, 7200)
            .build()

        // 4039 Arceus Alloy 2B
        ArceusAlloy2B = Material.Builder(4039, GTLiteMod.id("arceus_alloy_2_b"))
            .ingot()
            .fluid()
            .color(0xC4A415).iconSet(SHINY)
            .components(Pikyonium64B, 6, Vibranium, 4, Osmiridium, 2, Lawrencium, 3, Thallium, 2, Astatine, 2, Trinium, 1)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(13900, BlastProperty.GasTier.HIGHEST) // Infinity
                    .blastStats(VA[UEV], 1 * MINUTE + 15 * SECOND)
                    .vacuumStats(VA[UV], 48 * SECOND)
            }
            .rotorStats(19.0F, 4.8F, 8400)
            .build()

        // 4040 Vibranium Tritanium Actinium Iron Superhydride
        VibraniumTritaniumActiniumIronSuperhydride = Material.Builder(4040, GTLiteMod.id("vibranium_tritanium_actinium_iron_superhydride"))
            .ingot()
            .fluid()
            .color(0x828AAD).iconSet(SHINY)
            .components(Vibranium, 5, Tritanium, 5, ActiniumSuperhydride, 1, BETSPerrhenate, 1, Iron, 1)
            .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .blast {b ->
                b.temp(14400, BlastProperty.GasTier.HIGHEST)
                    .blastStats(VA[UEV], 30 * SECOND)
                    .vacuumStats(VA[UHV], 15 * SECOND) }
            .cableProperties(V[UEV], 32, 0, true)
            .build()

        // 4041 Hafnium Carbide
        HafniumCarbide = Material.Builder(4041, GTLiteMod.id("hafnium_carbide"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(METALLIC)
            .components(Hafnium, 1, Carbon, 1)
            .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_BOLT_SCREW)
            .blast { b ->
                b.temp(4090, BlastProperty.GasTier.MID) // RTM Alloy
                    .blastStats(VA[EV], 1 * MINUTE + 5 * SECOND)
                    .vacuumStats(VA[HV], 30 * SECOND)
            }
            .build()

        // 4042 Seaborgium Carbide
        SeaborgiumCarbide = Material.Builder(4042, GTLiteMod.id("seaborgium_carbide"))
            .ingot()
            .fluid()
            .color(0x4059EA).iconSet(METALLIC)
            .components(Seaborgium, 1, Carbon, 1)
            .flags(STD_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FOIL)
            .blast { b ->
                b.temp(8500, BlastProperty.GasTier.HIGH) // Trinium
                    .blastStats(VA[ZPM], 45 * SECOND)
                    .vacuumStats(VA[IV], 22 * SECOND + 10 * TICK)
            }
            .build()

        // 4043 Tantalum Hafnium Seaborgium Carbide
        TantalumHafniumSeaborgiumCarbide = Material.Builder(4043, GTLiteMod.id("tantalum_hafnium_seaborgium_carbide"))
            .ingot()
            .fluid()
            .color(0x2C2C2C).iconSet(SHINY)
            .components(TantalumCarbide, 12, HafniumCarbide, 3, SeaborgiumCarbide, 1)
            .flags(EXT2_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL,
                GENERATE_FRAME, GENERATE_SPRING, GENERATE_DOUBLE_PLATE)
            .blast { b ->
                b.temp(12900, BlastProperty.GasTier.HIGHEST) // Infinity (Adamantium)
                    .blastStats(VA[UHV], 36 * SECOND)
                    .vacuumStats(VA[UV], 18 * SECOND)
            }
            .build()
            .setFormula("Ta12Hf3SgC16", true)

        // 4044 Superheavy Alloy (Light)
        SuperheavyAlloyA = Material.Builder(4044, GTLiteMod.id("light_superheavy_alloy"))
            .ingot()
            .fluid()
            .color(0x4D8BE9).iconSet(SHINY)
            .components(Rutherfordium, 1, Dubnium, 1, Seaborgium, 1, Bohrium, 1, MetastableHassium, 1,
                Meitnerium, 1, Darmstadtium, 1, Roentgenium, 1)
            .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_SPRING,
                GENERATE_SPRING_SMALL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE)
            .blast { b ->
                b.temp(13800, BlastProperty.GasTier.HIGHEST) // Infinity
                    .blastStats(VA[UIV], 48 * SECOND)
                    .vacuumStats(VA[UHV], 24 * SECOND)
            }
            .cableProperties(V[UIV], 48, 5)
            .build()

        // 4045 Superheavy Alloy (Heavy)
        SuperheavyAlloyB = Material.Builder(4045, GTLiteMod.id("heavy_superheavy_alloy"))
            .ingot()
            .fluid()
            .color(0xE84B36).iconSet(SHINY)
            .components(Copernicium, 1, Nihonium, 1, MetastableFlerovium, 1, Moscovium, 1,
                Livermorium, 1, Tennessine, 1, MetastableOganesson, 1)
            .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_SPRING,
                GENERATE_SPRING_SMALL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE)
            .blast { b ->
                b.temp(15400, BlastProperty.GasTier.HIGHEST) // Halkonite Steel
                    .blastStats(VA[UXV], 1 * MINUTE + 36 * SECOND)
                    .vacuumStats(VA[UEV], 48 * SECOND)
            }
            .cableProperties(V[UXV], 64, 10)
            .build()

        // 4046 Precious Metal Alloy
        PreciousMetalAlloy = Material.Builder(4046, GTLiteMod.id("precious_metal_alloy"))
            .ingot()
            .fluid()
            .color(Gold.materialRGB + Silver.materialRGB + Platinum.materialRGB + Palladium.materialRGB
                    + Ruthenium.materialRGB + Rhodium.materialRGB + Iridium.materialRGB
                    + Osmium.materialRGB).iconSet(BRIGHT)
            .components(Ruthenium, 1, Rhodium, 1, Palladium, 1, Silver, 1,
                Osmium, 1, Iridium, 1, Platinum, 1, Gold, 1)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(9900, BlastProperty.GasTier.HIGHER) // Tritanium
                    .blastStats(VA[UV], 1 * MINUTE)
                    .vacuumStats(VA[ZPM], 30 * SECOND)
            }
            .build()

        // 4047 Nitinol-60
        Nitinol60 = Material.Builder(4047, GTLiteMod.id("nitinol_60"))
            .ingot()
            .fluid()
            .color(0xCCB0EC).iconSet(SHINY)
            .components(Nickel, 2, Titanium, 3)
            .blast { b ->
                b.temp(1941, BlastProperty.GasTier.HIGH) // Kanthal
                    .blastStats(VA[EV], 32 * SECOND + 10 * TICK)
                    .vacuumStats(VA[MV], 7 * SECOND + 4 * TICK) }
            .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE, GENERATE_FRAME,
                GENERATE_SPRING, GENERATE_RING, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_SPRING_SMALL)
            .toolStats(MaterialToolProperty.Builder.of(12.0F, 7.0F, 2304, 3)
                .enchantability(16).build())
            .itemPipeProperties(288, 3F)
            .build()

        // 4048 Abyssalloy
        Abyssalloy = Material.Builder(4048, GTLiteMod.id("abyssalloy"))
            .ingot()
            .fluid()
            .color(0x9E706A).iconSet(METALLIC)
            .components(StainlessSteel, 5, TungstenCarbide, 5, Nichrome, 5, Bronze, 5,
                IncoloyMA956, 5, Iodine, 1, Germanium, 1, Radon, 1)
            .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FOIL, GENERATE_FINE_WIRE,
                   GENERATE_DOUBLE_PLATE, GENERATE_DENSE)
            .blast { b ->
                b.temp(12625, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UIV], 4 * MINUTE)
                    .vacuumStats(VA[UEV], 2 * MINUTE + 30 * SECOND)
            }
            .build()

        // 4049 Fullerene Superconductor
        FullereneSuperconductor = Material.Builder(4049, GTLiteMod.id("fullerene_superconductor"))
            .ingot()
            .fluid()
            .color(0x8BF743).iconSet(BRIGHT)
            .components(TitanSteel, 16, LanthanumFullereneNanotube, 4, SeaborgiumDopedCarbonNanotube, 4,
                MetastableOganesson, 3, Xenon, 1)
            .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL,
                GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(15900, BlastProperty.GasTier.HIGHEST) // Halkonite Steel
                    .blastStats(VA[UIV], 3 * MINUTE + 25 * SECOND)
                    .vacuumStats(VA[UEV], 2 * MINUTE)
            }
            .cableProperties(V[UIV], 64, 0, true)
            .build()

        // 4050 Lanthanum Group Alloy A
        LanthanumGroupAlloyA = Material.Builder(4050, GTLiteMod.id("light_lanthanum_group_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(METALLIC)
            .components(Lanthanum, 1, Cerium, 1, Praseodymium, 1, Neodymium, 1, Promethium, 1, Samarium, 1,
                Europium, 1, Gadolinium, 1)
            .flags(STD_METAL, GENERATE_FOIL)
            .blast { b -> b
                .temp(11400, BlastProperty.GasTier.HIGHEST) // Adamantium
                .blastStats(VA[UHV], 45 * SECOND)
                .vacuumStats(VA[ZPM], 35 * SECOND)
            }
            .build()

        // 4051 Lanthanum Group Alloy B
        LanthanumGroupAlloyB = Material.Builder(4051, GTLiteMod.id("heavy_lanthanum_group_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(METALLIC)
            .components(Terbium, 1, Dysprosium, 1, Holmium, 1, Erbium, 1, Thulium, 1, Ytterbium, 1,
                Lutetium, 1, Scandium, 1)
            .flags(EXT2_METAL, GENERATE_FOIL)
            .blast { b -> b
                .temp(11400, BlastProperty.GasTier.HIGHEST) // Adamantium
                .blastStats(VA[UHV], 45 * SECOND)
                .vacuumStats(VA[ZPM], 35 * SECOND)
            }
            .build()

        // 4052 Actinium Group Alloy A
        ActiniumGroupAlloyA = Material.Builder(4052, GTLiteMod.id("light_actinium_group_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(METALLIC)
            .components(Actinium, 1, Thorium, 1, Protactinium, 1, Uranium, 1, Neptunium, 1,
                Plutonium239, 1, Americium, 1, Curium, 1)
            .flags(STD_METAL, GENERATE_FOIL)
            .blast { b ->
                b.temp(11400, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UHV], 45 * SECOND)
                    .vacuumStats(VA[ZPM], 35 * SECOND)
            }
            .build()
            .setFormula("AcThPaUNpPuAmCm", false)

        // 4053 Actinium Group Alloy B
        ActiniumGroupAlloyB = Material.Builder(4053, GTLiteMod.id("heavy_actinium_group_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(METALLIC)
            .components(Berkelium, 1, Californium, 1, Einsteinium, 1, Fermium, 1, Mendelevium, 1,
                Nobelium, 1, Lawrencium, 1, Yttrium, 1)
            .flags(STD_METAL, GENERATE_FOIL)
            .blast { b ->
                b.temp(11400, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UHV], 45 * SECOND)
                    .vacuumStats(VA[ZPM], 35 * SECOND)
            }
            .build()

        // 4054 Cinobite A243
        CinobiteA243 = Material.Builder(4054, GTLiteMod.id("cinobite_a_243"))
            .ingot()
            .fluid()
            .color(0xA9A9B5).iconSet(METALLIC)
            .components(Zeron100, 8, Naquadria, 4, Gadolinium, 3, Aluminium, 2, Mercury, 1,
                Tin, 1, Titanium, 6, Osmiridium, 1)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FOIL, GENERATE_RING,
                GENERATE_SPRING_SMALL, GENERATE_DENSE)
            .blast { r ->
                r.temp(11465, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UEV], 1 * MINUTE + 25 * SECOND)
                    .vacuumStats(VA[UHV], 55 * SECOND)
            }
            .toolStats(MaterialToolProperty.Builder.of(30.0F, 144.0F, 9760, 9)
                .attackSpeed(0.5F).enchantability(38)
                .magnetic()
                .build())
            .rotorStats(22.0F, 15F, 6400)
            .build()

        // 4055 Lafium
        Lafium = Material.Builder(4055, GTLiteMod.id("lafium"))
            .ingot()
            .fluid()
            .color(0x0D0D60).iconSet(SHINY)
            .components(HastelloyN, 8, NaquadahAlloy, 4, Samarium, 2, Tungsten, 4, Argon, 2,
                Aluminium, 6, Nickel, 8, Carbon, 2)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FOIL)
            .blast { r ->
                r.temp(12865, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UEV], 45 * SECOND)
                    .vacuumStats(VA[UHV], 22 * SECOND + 10 * TICK)
            }
            .fluidPipeProperties(10640, 960, true, true, true, true)
            .build()

        // 4056 Alkali Group Alloy
        AlkaliGroupAlloy = Material.Builder(4056, GTLiteMod.id("alkali_group_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(SHINY)
            .components(Lithium, 1, Sodium, 1, Potassium, 1, Rubidium, 1, Caesium, 1, Francium, 1)
            .blast { b ->
                b.temp(6600, BlastProperty.GasTier.HIGH) // Naquadah
                    .blastStats(VA[IV], 20 * SECOND)
                    .vacuumStats(VA[HV], 10 * SECOND)
            }
            .build()

        // 4057 Alkali Earth Group Alloy
        AlkaliEarthGroupAlloy = Material.Builder(4057, GTLiteMod.id("alkali_earth_group_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(SHINY)
            .components(Beryllium, 1, Magnesium, 1, Calcium, 1, Strontium, 1, Barium, 1, Radium, 1)
            .blast { b ->
                b.temp(7400, BlastProperty.GasTier.HIGH) // Trinium (Naquadah)
                    .blastStats(VA[LuV], 25 * SECOND)
                    .vacuumStats(VA[EV], 15 * SECOND)
            }
            .build()

        // 4058 Light Transition Alloy
        TransitionAlloyA = Material.Builder(4058, GTLiteMod.id("light_transition_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(METALLIC)
            .components(Titanium, 1, Vanadium, 1, Chrome, 1, Manganese, 1, Iron, 1, Cobalt, 1,
                Nickel, 1, Copper, 1)
            .flags(GENERATE_ROD, GENERATE_LONG_ROD)
            .blast { b ->
                b.temp(8800, BlastProperty.GasTier.HIGH) // Trinium
                    .blastStats(VA[ZPM], 30 * SECOND)
                    .vacuumStats(VA[IV], 20 * SECOND)
            }
            .build()

        // 4059 Heavy Transition Alloy
        TransitionAlloyB = Material.Builder(4059, GTLiteMod.id("heavy_transition_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(METALLIC)
            .components(Aluminium, 1, Zinc, 1, Gallium, 1, Germanium, 1, Cadmium, 1, Indium, 1,
                Tin, 1, Antimony, 1)
            .blast { b ->
                b.temp(8800, BlastProperty.GasTier.HIGH) // Trinium
                    .blastStats(VA[ZPM], 30 * SECOND)
                    .vacuumStats(VA[IV], 20 * SECOND)
            }
            .build()

        // 4060 Transition Alloy
        TransitionAlloy = Material.Builder(4060, GTLiteMod.id("transition_alloy"))
            .dust()
            .colorAverage().iconSet(METALLIC)
            .components(TransitionAlloyA, 1, TransitionAlloyB, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("TiVCrMnFeCoNiCuAlZnGaGeCdInSnSb", true)

        // 4061 Refractory Alloy
        RefractoryAlloy = Material.Builder(4061, GTLiteMod.id("refractory_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(DULL)
            .components(Zirconium, 1, Niobium, 1, Molybdenum, 1, Technetium, 1, Hafnium, 1, Tantalum, 1,
                Tungsten, 1, Rhenium, 1)
            .blast { b ->
                b.temp(9900, BlastProperty.GasTier.HIGHER) // Tritanium
                    .blastStats(VA[UV], 35 * SECOND)
                    .vacuumStats(VA[LuV], 25 * SECOND)
            }
            .build()

        // 4062 Toxic Alloy
        ToxicAlloy = Material.Builder(4062, GTLiteMod.id("toxic_alloy"))
            .ingot()
            .fluid()
            .colorAverage().iconSet(METALLIC)
            .components(Arsenic, 1, Tellurium, 1, Mercury, 1, Thallium, 1, Lead, 1, Bismuth, 1, Polonium, 1,
                Astatine, 1)
            .blast { b ->
                b.temp(10800, BlastProperty.GasTier.HIGHEST) // Tritanium
                .blastStats(VA[UHV], 40 * SECOND)
                .vacuumStats(VA[ZPM], 30 * SECOND)
            }
            .build()

        // 4063 Rare Earth Alloy
        RareEarthAlloy = Material.Builder(4063, GTLiteMod.id("rare_earth_alloy"))
            .dust()
            .colorAverage().iconSet(METALLIC)
            .components(LanthanumGroupAlloyA, 1, LanthanumGroupAlloyB, 1, ActiniumGroupAlloyA, 1,
                ActiniumGroupAlloyB, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("LaCePrNdPmSmEuGdTbDyHoErTmYbLuScYAcThPaUNpPuAmCmBkCfEsFmMdNoLr", true)

        // 4064 Light Non-Metallic Mixture
        NonMetallicMixtureA = Material.Builder(4064, GTLiteMod.id("light_non_metallic_mixture"))
            .gas(FluidBuilder()
                .name("gregtech.fluid.generic"))
            .colorAverage()
            .components(Hydrogen, 1, Nitrogen, 1, Oxygen, 1, Fluorine, 1, Chlorine, 1, Bromine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 4065 Heavy Non-Metallic Mixture
        NonMetallicMixtureB = Material.Builder(4065, GTLiteMod.id("heavy_non_metallic_mixture"))
            .liquid()
            .colorAverage()
            .components(Boron, 1, Carbon, 1, Silicon, 1, Phosphorus, 1, Sulfur, 1, Selenium, 1,
                Iodine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 4066 Noble Gas Mixture
        NobleGasMixture = Material.Builder(4066, GTLiteMod.id("noble_gas_mixture"))
            .gas(FluidBuilder()
                .translation("gregtech.fluid.generic"))
            .colorAverage()
            .components(Helium, 1, Neon, 1, Argon, 1, Krypton, 1, Xenon, 1, Radon, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 4067 Periodicium
        Periodicium = Material.Builder(4067, GTLiteMod.id("periodicium"))
            .ingot()
            .fluid()
            .color(0x3D4BF6).iconSet(BRIGHT)
            .components(AlkaliGroupAlloy, 1, AlkaliEarthGroupAlloy, 1, TransitionAlloy, 1,
                RefractoryAlloy, 1, PreciousMetalAlloy, 1, ToxicAlloy, 1, RareEarthAlloy, 1,
                SuperheavyAlloyA, 1, SuperheavyAlloyB, 1, NonMetallicMixtureA, 1,
                NonMetallicMixtureB, 1, NobleGasMixture, 1)
            .flags(EXT2_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_SPRING,
                GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
            .cableProperties(V[OpV], 118, 2)
            .blast { b ->
                b.temp(18000, BlastProperty.GasTier.HIGHEST) // Space Time
                    .blastStats(VA[UXV], 10 * SECOND)
                    .vacuumStats(VA[UEV], 2 * SECOND + 10 * TICK)
            }
            .build()
            .setFormula("HHeLiBeBCNOFNeNaMgAlSiPSClArKCaScTiVCrMnFeCoNiCuZnGaGe"
                + "AsSeBrKrRbSrYZrNbMoTcRuRhPdAgCdInSnSbTeIXeCsBaLaCePrNdPm"
                + "SmEuGdTbDyHoErTmYbLuHfTaWReOsIrPtAuHgTlPbBiPoAtRnFrRaAcTh"
                + "PaUNpPuAmCmBkCfEsFmMdNoLrRfDbSgBhHsMtDsRgCnNhFlMcLvTsOg")

        // 4068 Legendarium
        Legendarium = Material.Builder(4068, GTLiteMod.id("legendarium"))
            .ingot()
            .fluid()
            .color(0xF58FDA).iconSet(ENRICHED)
            .components(Naquadria, 1, Trinium, 1, Duranium, 1, Tritanium, 1, Neutronium, 1,
                Adamantium, 1, Vibranium, 1, Taranium, 1)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_GEAR,
                GENERATE_SMALL_GEAR, GENERATE_RING, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FOIL)
            .blast { b ->
                b.temp(17800, BlastProperty.GasTier.HIGHEST) // Space Time
                    .blastStats(VA[UXV], 1 * MINUTE + 20 * SECOND)
                    .vacuumStats(VA[UEV], 40 * SECOND)
            }
            .toolStats(MaterialToolProperty.Builder.of(180.0F, 90.0F, 97600, 20)
                .attackSpeed(0.4F).enchantability(42)
                .magnetic()
                .build())
            .build()

        // 4069 Boron Francium Carbide Superconductor
        BoronFranciumCarbideSuperconductor = Material.Builder(4069, GTLiteMod.id("boron_francium_carbide_superconductor"))
            .ingot()
            .fluid()
            .color(0x359FFC).iconSet(BRIGHT)
            .components(HeavyConductiveMixture, 17, CaesiumCeriumCobaltIndium, 14, MagnetoResonatic, 4, Taranium, 2, PlutoniumPhosphide, 2,
                        MetastableHassium, 1, MetastableOganesson, 1)
            .flags(STD_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(17901, BlastProperty.GasTier.HIGHEST) // Space Time
                    .blastStats(VA[UXV], 4 * MINUTE + 25 * SECOND)
                    .vacuumStats(VA[UIV], 3 * MINUTE + 12 * SECOND)
            }
            .cableProperties(V[UXV], 128, 0, true)
            .build()

    }

    // @formatter:on

}