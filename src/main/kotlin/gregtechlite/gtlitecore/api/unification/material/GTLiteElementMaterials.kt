package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.GTValues.IV
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
import gregtech.api.unification.Elements.Fl
import gregtech.api.unification.Elements.Hs
import gregtech.api.unification.Elements.Og
import gregtech.api.unification.Elements.Rh
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.EXT2_METAL
import gregtech.api.unification.material.Materials.EXT_METAL
import gregtech.api.unification.material.Materials.STD_METAL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_BOLT_SCREW
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DOUBLE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROTOR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROUND
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.info.MaterialFlags.NO_UNIFICATION
import gregtech.api.unification.material.info.MaterialIconSet.BRIGHT
import gregtech.api.unification.material.info.MaterialIconSet.METALLIC
import gregtech.api.unification.material.info.MaterialIconSet.SHINY
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.MaterialToolProperty
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DegenerateRhenium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableFlerovium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Plutonium244
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Ad
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Crx
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.En
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Fs
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Hy
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.If
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.M
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Mx
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Pu244
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Sh
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.SpNt
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Spx
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Tn
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Tsx
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Ux
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Vb
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags.GENERATE_NANITE
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.COSMIC
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.DEGENERATE
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.ENRICHED
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.ETERNITY
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.INFINITY
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.MAGMATTER
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.MAGNETIUM
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.SPACETIME
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.UNIVERSIUM
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import net.minecraft.init.Enchantments

object GTLiteElementMaterials
{

    // @formatter:off

    fun init()
    {
        // 1 Adamantium
        Adamantium = Material.Builder(1, GTLiteMod.id("adamantium"))
            .ingot()
            .liquid()
            .plasma()
            .color(0xFF0040).iconSet(METALLIC)
            .element(Ad)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_ROTOR,
                GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND,
                GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(10501, BlastProperty.GasTier.HIGH) // Tritanium
                    .blastStats(VA[UV], 45 * SECOND)
                    .vacuumStats(VA[ZPM], 22 * SECOND + 10 * TICK)
            }
            .rotorStats(22.0f, 10.0f, 491520)
            .toolStats(MaterialToolProperty.Builder.of(140.0F, 95.0F, 49152, 6)
                .attackSpeed(0.5F).enchantability(32)
                .enchantment(Enchantments.FORTUNE, 5)
                .magnetic().build())
            .cableProperties(V[UHV], 4, 24)
            .build()

        // 2 Vibranium
        Vibranium = Material.Builder(2, GTLiteMod.id("vibranium"))
            .ingot()
            .liquid()
            .plasma()
            .color(0xC880FF).iconSet(SHINY)
            .element(Vb)
            .flags(EXT2_METAL, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR,
                GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_RING, GENERATE_ROTOR,
                GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_ROUND, GENERATE_SPRING_SMALL)
            .blast { b ->
                b.temp(12001, BlastProperty.GasTier.HIGHER) // Adamantium
                    .blastStats(VA[UHV], 75 * SECOND)
                    .vacuumStats(VA[UV], 42 * SECOND + 15 * TICK)
            }
            .toolStats(MaterialToolProperty.Builder.of(155.0F, 120.0F, 73728, 7)
                .attackSpeed(0.8F).enchantability(34)
                .enchantment(Enchantments.SHARPNESS, 10)
                .enchantment(Enchantments.SWEEPING, 5)
                .enchantment(Enchantments.LOOTING, 3)
                .magnetic().build())
            .build()

        // 3 Taranium
        Taranium = Material.Builder(3, GTLiteMod.id("taranium"))
            .ingot()
            .liquid()
            .color(0x4F404F).iconSet(METALLIC)
            .element(Tn)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE,
                GENERATE_SPRING_SMALL, GENERATE_DENSE)
            .blast { b ->
                b.temp(7000, BlastProperty.GasTier.HIGH) // Naquadah
                    .blastStats(VA[ZPM], 22 * SECOND)
                    .vacuumStats(VA[IV], 11 * SECOND)
            }
            .cableProperties(V[UHV], 8, 2)
            .build()

        // 4 Metastable Flerovium
        MetastableFlerovium = Material.Builder(4, GTLiteMod.id("metastable_flerovium"))
            .ingot()
            .liquid()
            .plasma()
            .color(0x521973).iconSet(SHINY)
            .element(Fl)
            .flags(EXT_METAL, GENERATE_DOUBLE_PLATE, GENERATE_BOLT_SCREW, GENERATE_FOIL)
            .blast { b ->
                b.temp(9900, BlastProperty.GasTier.HIGHEST) // Tritanium
                    .blastStats(VA[UHV], 2 * MINUTE + 35 * SECOND)
                    .vacuumStats(VA[UV], 40 * SECOND) }
            .rotorStats(25.0F, 3.0F, 153600)
            .build()

        // 5 Metastable Oganesson
        MetastableOganesson = Material.Builder(5, GTLiteMod.id("metastable_oganesson"))
            .ingot()
            .liquid()
            .plasma()
            .color(0xE61C24).iconSet(SHINY)
            .element(Og)
            .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_RING,
                GENERATE_DOUBLE_PLATE, GENERATE_FRAME, GENERATE_SMALL_GEAR, GENERATE_SPRING_SMALL)
            .blast { b ->
                b.temp(12380, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UEV], 2 * MINUTE)
                    .vacuumStats(VA[UV], 40 * SECOND)
            }
            .toolStats(MaterialToolProperty.Builder.of(55.0F, 95.0F, 110592, 7)
                .enchantment(Enchantments.MENDING, 3)
                .attackSpeed(0.2F).enchantability(36).magnetic().build())
            .cableProperties(V[UEV], 18, 9)
            .build()

        // 6 Metastable Hassium
        MetastableHassium = Material.Builder(6, GTLiteMod.id("metastable_hassium"))
            .ingot()
            .liquid()
            .plasma()
            .color(0x2D3A9D).iconSet(BRIGHT)
            .element(Hs)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .blast { b -> b
                .temp(12000, BlastProperty.GasTier.HIGHEST) // Adamantium
                .blastStats(VA[UEV], 4 * MINUTE + 30 * SECOND)
                .vacuumStats(VA[UV], 1 * MINUTE)
            }
            .itemPipeProperties(256, 128F)
            .cableProperties(V[UEV], 28, 5)
            .build()

        // 7 Cosmic Neutronium
        CosmicNeutronium = Material.Builder(7, GTLiteMod.id("cosmic_neutronium"))
            .ingot()
            .liquid()
            .iconSet(COSMIC)
            .element(SpNt)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_ROTOR,
                GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND,
                GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
            .blast { b ->
                b.temp(12600, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UEV], 12 * MINUTE)
                    .vacuumStats(VA[UHV], 4 * MINUTE)
            }
            .cableProperties(V[UEV], 24, 4)
            .build()

        // 8 Infinity
        Infinity = Material.Builder(8, GTLiteMod.id("infinity"))
            .ingot()
            .liquid()
            .iconSet(INFINITY)
            .element(If)
            .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_SMALL_GEAR,
                GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_RING, GENERATE_ROTOR, GENERATE_SPRING,
                GENERATE_SPRING_SMALL, GENERATE_FRAME)
            .blast { b ->
                b.temp(12600, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UEV], 16 * MINUTE)
                    .vacuumStats(VA[UHV], 8 * MINUTE)
            }
            .cableProperties(V[UEV], 36, 2)
            .build()

        // 9 Plutonium-244
        Plutonium244 = Material.Builder(9, GTLiteMod.id("plutonium_244"))
            .ingot()
            .liquid(FluidBuilder().temperature(913))
            .color(0xD82D2D).iconSet(METALLIC)
            .element(Pu244)
            .flags(STD_METAL, GENERATE_FOIL)
            .build()

        // 10 Degenerate Rhenium
        DegenerateRhenium = Material.Builder(10, GTLiteMod.id("degenerate_rhenium"))
            .dust()
            .plasma(FluidBuilder()
                .temperature(1_000_000))
            .iconSet(DEGENERATE)
            .element(Rh)
            .flags(STD_METAL, NO_UNIFICATION)
            .build()
            .setFormula("§cR§de", true)

        // 11 Magnetium
        Magnetium = Material.Builder(11, GTLiteMod.id("magnetium"))
            .ingot()
            .liquid(FluidBuilder()
                .temperature(2_560_000))
            // TODO Maybe we needs a new renderer? allowed player set color(), colorBg()?
            .iconSet(MAGNETIUM) // Foreground RGB: F2F226; Background RGB: 21A7A7;
                                // Layered Halo RGB: F8F8D5; Transition Dir: LD -> RU.
            .element(M)
            .flags(EXT2_METAL, NO_UNIFICATION, GENERATE_GEAR, GENERATE_SMALL_GEAR,
                GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_SPRING, GENERATE_SPRING_SMALL,
                GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_ROTOR, GENERATE_ROUND, GENERATE_FRAME)
            .blast { b ->
                b.temp(13900, BlastProperty.GasTier.HIGHEST) // Infinity
                    .blastStats(VA[UIV], 30 * SECOND)
                    .vacuumStats(VA[UHV], 15 * SECOND)
            }
            .build()

        // 12 Rhugnor
        Rhugnor = Material.Builder(12, GTLiteMod.id("rhugnor"))
            .ingot()
            .liquid(FluidBuilder()
                .temperature(9025))
            .color(0xBE00FF).iconSet(METALLIC)
            .flags(EXT2_METAL, GENERATE_RING, GENERATE_GEAR, GENERATE_DOUBLE_PLATE, GENERATE_FOIL,
                GENERATE_FINE_WIRE, GENERATE_SPRING_SMALL, GENERATE_SMALL_GEAR)
            .element(Fs)
            .toolStats(MaterialToolProperty.Builder.of(76.0F, 110.0F, 165888, 9)
                .enchantment(Enchantments.EFFICIENCY, 6)
                .enchantment(Enchantments.FORTUNE, 4)
                .attackSpeed(0.4F).enchantability(42).magnetic().build())
            .itemPipeProperties(16384, 256F)
            .rotorStats(48.0F, 8.0F, 98304)
            .build()

        // 13 Hypogen
        Hypogen = Material.Builder(13, GTLiteMod.id("hypogen"))
            .ingot()
            .liquid(FluidBuilder()
                .temperature(11530))
            .color(0xDC784B).iconSet(ENRICHED) // Opacity: 5-10-15-20-25-30-35
            .element(Hy)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL,
                GENERATE_FINE_WIRE, GENERATE_ROTOR, GENERATE_FRAME)
            .cableProperties(V[UIV], 16, 4)
            .build()

        // 14 Shirabon
        Shirabon = Material.Builder(14, GTLiteMod.id("shirabon"))
            .ingot()
            .liquid()
            .color(0xE0156D).iconSet(BRIGHT)
            .element(Sh)
            .flags(EXT2_METAL, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_SPRING,
                GENERATE_SPRING_SMALL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE,
                GENERATE_RING, GENERATE_ROTOR, GENERATE_FRAME, GENERATE_ROUND)
            .rotorStats(144.0F, 2.4F, 786432)
            .build()

        // 15 Transcendent Metal
        TranscendentMetal = Material.Builder(15, GTLiteMod.id("transcendent_metal"))
            .ingot()
            .liquid()
            .color(0x1A1A1A).iconSet(METALLIC) // TODO Rotated animations.
            .element(Tsx)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_ROTOR,
                GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND,
                GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE, GENERATE_NANITE)
            .blast { b ->
                b.temp(23500, BlastProperty.GasTier.HIGHEST) // Error
                    .blastStats(VA[UXV], 3 * MINUTE)
                    .vacuumStats(VA[UIV], 2 * MINUTE)
            }
            .fluidPipeProperties(800_000, 40000, true, true, true, true)
            .build()

        // 16 Space Time
        SpaceTime = Material.Builder(16, GTLiteMod.id("space_time"))
            .ingot()
            .liquid()
            .iconSet(SPACETIME)
            .element(Spx)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL,
                GENERATE_FINE_WIRE, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR)
            .blast { b ->
                b.temp(16201, BlastProperty.GasTier.HIGHEST) // Halkonite Steel
                    .blastStats(VA[UXV], 4 * MINUTE + 30 * SECOND)
                    .vacuumStats(VA[UIV], 3 * MINUTE + 25 * SECOND)
            }
            .cableProperties(V[UXV], 96,16)
            .build()

        // 17 Creon
        Creon = Material.Builder(17, GTLiteMod.id("creon"))
            .ingot()
            .liquid()
            .plasma()
            .color(0x460046).iconSet(SHINY)
            .element(Crx)
            .flags(EXT2_METAL, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_SPRING,
                GENERATE_SPRING_SMALL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE,
                GENERATE_RING, GENERATE_ROTOR, GENERATE_FRAME, GENERATE_ROUND)
            .blast { b ->
                b.temp(24000, BlastProperty.GasTier.HIGHEST) // Error
                    .blastStats(VA[UXV], 2 * MINUTE + 40 * SECOND)
                    .vacuumStats(VA[UIV], 1 * MINUTE + 45 * SECOND)
            }
            .toolStats(MaterialToolProperty.Builder.of(160.0F, 320.0F, 62914560, 20)
                .attackSpeed(0.2F).enchantability(40)
                .enchantment(Enchantments.SHARPNESS, 20)
                .enchantment(Enchantments.SWEEPING, 20)
                .enchantment(Enchantments.LOOTING, 20)
                .enchantment(Enchantments.FORTUNE, 20)
                .magnetic()
                .unbreakable()
                .build())
            .rotorStats(576.0F, 64.0F, 62914560)
            .build()

        // 18 Mag Matter
        MagMatter = Material.Builder(18, GTLiteMod.id("mag_matter"))
            .ingot()
            .liquid()
            .iconSet(MAGMATTER)
            .element(Mx)
            .flags(EXT2_METAL, NO_UNIFICATION, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL,
                GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_RING,
                GENERATE_ROTOR, GENERATE_FRAME, GENERATE_NANITE)
            .cableProperties(V[UXV], 56, 14)
            .build()

        // 19 Universium
        Universium = Material.Builder(19, GTLiteMod.id("universium"))
            .ingot()
            .liquid()
            .iconSet(UNIVERSIUM)
            .element(Ux)
            .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_DOUBLE_PLATE, GENERATE_DENSE,
                GENERATE_NANITE)
            .cableProperties(V[OpV], 144, 8)
            .build()

        // 20 Eternity
        Eternity = Material.Builder(20, GTLiteMod.id("eternity"))
            .ingot()
            .liquid()
            .iconSet(ETERNITY)
            .element(En)
            .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE,
                GENERATE_NANITE, GENERATE_ROTOR, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_GEAR,
                GENERATE_SMALL_GEAR, GENERATE_FRAME, GENERATE_ROUND)
            .cableProperties(V[OpV], 180, 40)
            .build()

    }

    // @formatter:on

}