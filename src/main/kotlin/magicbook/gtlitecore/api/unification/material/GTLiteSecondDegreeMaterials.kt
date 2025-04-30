package magicbook.gtlitecore.api.unification.material

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.FluidBuilder
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Arsenic
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.EXT2_METAL
import gregtech.api.unification.material.Materials.EXT_METAL
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.NiobiumNitride
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.STD_METAL
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Thallium
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
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DOUBLE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROTOR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.info.MaterialFlags.NO_UNIFICATION
import gregtech.api.unification.material.info.MaterialIconSet.BRIGHT
import gregtech.api.unification.material.info.MaterialIconSet.METALLIC
import gregtech.api.unification.material.info.MaterialIconSet.SAND
import gregtech.api.unification.material.info.MaterialIconSet.SHINY
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.MaterialToolProperty
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumBronze
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ArceusAlloy2B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BabbitAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteelBase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedNaquadahAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Grisium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HDCS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HSLASteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HalkoniteSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyC276
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyK243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyN
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX78
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA813
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA956
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Inconel625
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jasper
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kovar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MaragingSteel250
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MolybdenumDisilicide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Monel500
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pikyonium64B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RedPhosphorus
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ReneN5
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SiliconCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Staballoy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Stellite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tairitsium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Talonite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TechnetiumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitanSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumTungstenCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trinaquadalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tumbaga
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WatertightSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zeron100
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZirconiumCarbide
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.HALKONITE
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.gtliteId
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import net.minecraft.init.Enchantments

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteSecondDegreeMaterials
{

    companion object
    {

        fun init()
        {
            // 4001 Kovar
            Kovar = Material.Builder(4001, gtliteId("kovar"))
                .ingot()
                .colorAverage().iconSet(SHINY)
                .components(Iron, 2, Nickel, 1, Cobalt, 1)
                .flags(EXT_METAL, GENERATE_RING, GENERATE_FRAME)
                .build()
                .setFormula("Fe10Ni5Co3", true)

            // 4002 Maraging Steel 250
            MaragingSteel250 = Material.Builder(4002, gtliteId("maraging_steel_250"))
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
            Inconel625 = Material.Builder(4003, gtliteId("inconel_625"))
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
            Staballoy = Material.Builder(4004, gtliteId("staballoy"))
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
            Talonite = Material.Builder(4005, gtliteId("talonite"))
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
            Zeron100 = Material.Builder(4006, gtliteId("zeron_100"))
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
            WatertightSteel = Material.Builder(4007, gtliteId("watertight_steel"))
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
            Stellite = Material.Builder(4008, gtliteId("stellite"))
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
            Tumbaga = Material.Builder(4009, gtliteId("tumbaga"))
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
            EglinSteelBase = Material.Builder(4010, gtliteId("eglin_steel_base"))
                .dust()
                .color(0x8B4513).iconSet(SAND)
                .components(Iron, 4, Kanthal, 1, Invar, 5)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build()

            // 4011 Eglin Steel
            EglinSteel = Material.Builder(4011, gtliteId("eglin_steel"))
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
            Grisium = Material.Builder(4012, gtliteId("grisium"))
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
            BabbitAlloy = Material.Builder(4013, gtliteId("babbit_alloy"))
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
            SiliconCarbide = Material.Builder(4014, gtliteId("silicon_carbide"))
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
            HSLASteel = Material.Builder(4015, gtliteId("hsla_steel"))
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
            IncoloyMA813 = Material.Builder(4016, gtliteId("incoloy_ma_813"))
                .ingot()
                .fluid()
                .color(0x37BF7E).iconSet(SHINY)
                .components(VanadiumSteel, 4, Niobium, 2, Chrome, 3, Nickel, 4)
                .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME)
                .blast { b ->
                    b.temp(4800, BlastProperty.GasTier.HIGH) // HSS-G (RTM Alloy via 2x HV Energy Hatch)
                        .blastStats(VA[IV], 34 * SECOND)
                        .vacuumStats(VA[EV], 17 * SECOND)
                }
                .build()

            // 4017 Monel 500
            Monel500 = Material.Builder(4017, gtliteId("monel_500"))
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
            IncoloyMA956 = Material.Builder(4018, gtliteId("incoloy_ma_956"))
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
            ZirconiumCarbide = Material.Builder(4019, gtliteId("zirconium_carbide"))
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
            TantalumCarbide = Material.Builder(4020, gtliteId("tantalum_carbide"))
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
            MolybdenumDisilicide = Material.Builder(4021, gtliteId("molybdenum_disilicide"))
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
            HastelloyC276 = Material.Builder(4022, gtliteId("hastelloy_c_276"))
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
            HastelloyX = Material.Builder(4023, gtliteId("hastelloy_x"))
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
            HastelloyN = Material.Builder(4024, gtliteId("hastelloy_n"))
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
            AluminiumBronze = Material.Builder(4025, gtliteId("aluminium_bronze"))
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
            ReneN5 = Material.Builder(4026, gtliteId("rene_n_5"))
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
            TitaniumCarbide = Material.Builder(4027, gtliteId("titanium_carbide"))
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
            TitaniumTungstenCarbide = Material.Builder(4028, gtliteId("titanium_tungsten_carbide"))
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
            Trinaquadalloy = Material.Builder(4029, gtliteId("trinaquadalloy"))
                .ingot()
                .fluid()
                .color(0x281832).iconSet(BRIGHT)
                .components(Trinium, 6, Naquadah, 2, Carbon, 1)
                .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_DOUBLE_PLATE)
                .blast { b ->
                    b.temp(8747, BlastProperty.GasTier.HIGHER) // Trinium
                        .blastStats(VA[ZPM], 1 * MINUTE)
                        .vacuumStats(VA[IV], 45 * SECOND)
                }
                .fluidPipeProperties(7552, 400, true, true, true, true)
                .build()

            // 4030 Enriched Naquadah Alloy
            EnrichedNaquadahAlloy = Material.Builder(4030, gtliteId("enriched_naquadah_alloy"))
                .ingot(6)
                .fluid()
                .color(0x160740).iconSet(SHINY)
                .components(NaquadahEnriched, 4, Ruridit, 2, Rutherfordium, 1)
                .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE, GENERATE_FOIL,
                    GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME)
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
            QuantumAlloy = Material.Builder(4031, gtliteId("quantum_alloy"))
                .ingot(7)
                .fluid()
                .color(0x0F0F0F).iconSet(BRIGHT)
                .components(Stellite, 15, Jasper, 5, Gallium, 5, Americium, 5, Palladium, 5, Bismuth, 5, Germanium, 5, SiliconCarbide, 5)
                .flags(EXT_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_DOUBLE_PLATE)
                .blast { b ->
                    b.temp(11400, BlastProperty.GasTier.HIGHEST) // Adamantium
                        .blastStats(VA[UHV], 58 * SECOND)
                        .vacuumStats(VA[UV], 29 * SECOND)
                }
                .itemPipeProperties(64, 96F)
                .build()

            // 4032 HDCS (High Durability Compound Steel)
            HDCS = Material.Builder(4032, gtliteId("hdcs"))
                .ingot(7)
                .fluid()
                .color(0x334433).iconSet(SHINY)
                .components(TungstenSteel, 12, HSSS, 9, HSSG, 6, Ruridit, 3, MagnetoResonatic, 2, Plutonium241, 1)
                .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_FRAME, GENERATE_GEAR,
                    GENERATE_DOUBLE_PLATE)
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
            TitanSteel = Material.Builder(4033, gtliteId("titan_steel"))
                .ingot(7)
                .fluid()
                .color(0xAA0D0D).iconSet(SHINY)
                .components(TitaniumTungstenCarbide, 3, Jasper, 3, Tritanium, 2)
                .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE, GENERATE_GEAR,
                    GENERATE_FRAME)
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
            Tairitsium = Material.Builder(4034, gtliteId("tairitsium"))
                .ingot()
                .fluid()
                .color(0x003F5F).iconSet(METALLIC)
                .components(TungstenSteel, 8, Naquadria, 7, Bedrockium, 4, Carbon, 4, VanadiumSteel, 3, Francium, 1)
                .blast { b ->
                    b.temp(10400, BlastProperty.GasTier.HIGHEST) // Tritanium
                        .blastStats(VA[UHV], 20 * SECOND)
                        .vacuumStats(VA[UV], 6 * SECOND + 10 * TICK) }
                .flags(EXT2_METAL, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_DOUBLE_PLATE,
                    GENERATE_GEAR, GENERATE_FOIL, GENERATE_FRAME)
                .toolStats(MaterialToolProperty.Builder.of(36.5F, 44.2F, 4980, 7)
                    .attackSpeed(0.6F).enchantability(30)
                    .enchantment(Enchantments.LOOTING, 3)
                    .build())
                .build()

            // 4035 Halkonite Steel
            HalkoniteSteel = Material.Builder(4035, gtliteId("halkonite_steel"))
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
                    GENERATE_SPRING, GENERATE_SPRING_SMALL)
                .cableProperties(V[UIV], 48, 64, false)
                .build()
                .setFormula("SpNt2((FeW)8*Nq*7?4C4(VCrFe7)3Fr)2P8(((WC)(TiC)2)3(CaMg5(OH)2(Si4O11)2)3Tr2)If", true) // Fix a little formula problem from P4.

            // 4036 Hastelloy-X78
            HastelloyX78 = Material.Builder(4036, gtliteId("hastelloy_x_78"))
                .ingot()
                .fluid()
                .color(0xD1CB0B).iconSet(SHINY)
                .components(NaquadahAlloy, 10, Rhenium, 5, Naquadria, 4, Gadolinium, 3,
                    Strontium, 2, Polonium, 3, Rutherfordium, 2, Fermium, 1)
                .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FRAME, GENERATE_GEAR)
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
            HastelloyK243 = Material.Builder(4037, gtliteId("hastelloy_k_243"))
                .ingot()
                .fluid()
                .color(0x92D959).iconSet(BRIGHT)
                .components(HastelloyX78, 5, NiobiumNitride, 2, Tritanium, 4, TungstenCarbide, 4, Promethium, 4, Mendelevium, 1)
                .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FRAME, GENERATE_GEAR)
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
            Pikyonium64B = Material.Builder(4038, gtliteId("pikyonium_64_b"))
                .ingot()
                .fluid()
                .color(0x3467BA).iconSet(SHINY)
                .components(Inconel718, 8, EglinSteel, 5, NaquadahEnriched, 4, TungstenSteel, 4, Cerium, 3,
                    Antimony, 2, Platinum, 2, Ytterbium, 1)
                .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE)
                .blast { b ->
                    b.temp(10400, BlastProperty.GasTier.HIGHER) // Tritanium
                        .blastStats(VA[UV], 30 * SECOND)
                        .vacuumStats(VA[LuV], 15 * SECOND)
                }
                .rotorStats(18.2F, 5.5F, 7200)
                .build()

            // 4039 Arceus Alloy 2B
            ArceusAlloy2B = Material.Builder(4039, gtliteId("arceus_alloy_2_b"))
                .ingot()
                .fluid()
                .color(0xC4A415).iconSet(SHINY)
                .components(Pikyonium64B, 6, Vibranium, 4, Osmiridium, 2, Lawrencium, 3, Thallium, 2, Astatine, 2, Trinium, 1)
                .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE)
                .blast { b ->
                    b.temp(13900, BlastProperty.GasTier.HIGHEST) // Infinity
                        .blastStats(VA[UEV], 1 * MINUTE + 15 * SECOND)
                        .vacuumStats(VA[UV], 48 * SECOND)
                }
                .rotorStats(19.0F, 4.8F, 8400)
                .build()


        }

    }

}