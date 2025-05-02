package magicbook.gtlitecore.api.unification.material

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
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
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DegenerateRhenium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Plutonium244
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.material.element.GTLiteElements.Companion.Ad
import magicbook.gtlitecore.api.unification.material.element.GTLiteElements.Companion.If
import magicbook.gtlitecore.api.unification.material.element.GTLiteElements.Companion.Pu244
import magicbook.gtlitecore.api.unification.material.element.GTLiteElements.Companion.SpNt
import magicbook.gtlitecore.api.unification.material.element.GTLiteElements.Companion.Tn
import magicbook.gtlitecore.api.unification.material.element.GTLiteElements.Companion.Vb
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.COSMIC
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.DEGENERATE
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.INFINITY
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.gtliteId
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import net.minecraft.init.Enchantments

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteElementMaterials
{

    companion object
    {

        fun init()
        {
            // 1 Adamantium
            Adamantium = Material.Builder(1, gtliteId("adamantium"))
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
            Vibranium = Material.Builder(2, gtliteId("vibranium"))
                .ingot()
                .liquid()
                .plasma()
                .color(0xC880FF).iconSet(SHINY)
                .element(Vb)
                .flags(EXT2_METAL, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR,
                    GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_RING, GENERATE_ROTOR,
                    GENERATE_FOIL)
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
            Taranium = Material.Builder(3, gtliteId("taranium"))
                .ingot()
                .liquid()
                .color(0x4F404F).iconSet(METALLIC)
                .element(Tn)
                .flags(STD_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .blast { b ->
                    b.temp(7000, BlastProperty.GasTier.HIGH) // Naquadah
                        .blastStats(VA[ZPM], 22 * SECOND)
                        .vacuumStats(VA[IV], 11 * SECOND)
                }
                .cableProperties(V[UHV], 8, 2)
                .build()

            // 4 Metastable Flerovium
            MetastableFlerovium = Material.Builder(4, gtliteId("metastable_flerovium"))
                .ingot()
                .liquid()
                .plasma()
                .color(0x521973).iconSet(SHINY)
                .element(Fl)
                .flags(EXT_METAL, GENERATE_DOUBLE_PLATE, GENERATE_BOLT_SCREW)
                .blast { b ->
                    b.temp(9900, BlastProperty.GasTier.HIGHEST) // Tritanium
                        .blastStats(VA[UHV], 2 * MINUTE + 35 * SECOND)
                        .vacuumStats(VA[UV], 40 * SECOND) }
                .rotorStats(25.0F, 3.0F, 15360)
                .build()

            // 5 Metastable Oganesson
            MetastableOganesson = Material.Builder(5, gtliteId("metastable_oganesson"))
                .ingot()
                .liquid()
                .plasma()
                .color(0xE61C24).iconSet(SHINY)
                .element(Og)
                .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_DOUBLE_PLATE)
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
            MetastableHassium = Material.Builder(6, gtliteId("metastable_hassium"))
                .ingot()
                .liquid()
                .plasma()
                .color(0x2D3A9D).iconSet(BRIGHT)
                .element(Hs)
                .flags(STD_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .blast { b -> b
                    .temp(12000, BlastProperty.GasTier.HIGHEST) // Adamantium
                    .blastStats(VA[UEV], 4 * MINUTE + 30 * SECOND)
                    .vacuumStats(VA[UV], 1 * MINUTE)
                }
                .itemPipeProperties(256, 128F)
                .cableProperties(V[UEV], 28, 5)
                .build()

            // 7 Cosmic Neutronium
            CosmicNeutronium = Material.Builder(7, gtliteId("cosmic_neutronium"))
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
            Infinity = Material.Builder(8, gtliteId("infinity"))
                .ingot()
                .liquid()
                .iconSet(INFINITY)
                .element(If)
                .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_SMALL_GEAR,
                    GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_RING, GENERATE_ROTOR, GENERATE_SPRING,
                    GENERATE_SPRING_SMALL)
                .blast { b ->
                    b.temp(12600, BlastProperty.GasTier.HIGHEST) // Adamantium
                        .blastStats(VA[UEV], 16 * MINUTE)
                        .vacuumStats(VA[UHV], 8 * MINUTE)
                }
                .cableProperties(V[UEV], 36, 2)
                .build()

            // 9 Plutonium-244
            Plutonium244 = Material.Builder(9, gtliteId("plutonium_244"))
                .ingot()
                .liquid(FluidBuilder().temperature(913))
                .color(0xD82D2D).iconSet(METALLIC)
                .element(Pu244)
                .build()

            // 10 Degenerate Rhenium
            DegenerateRhenium = Material.Builder(10, gtliteId("degenerate_rhenium"))
                .dust()
                .plasma(FluidBuilder()
                    .temperature(1_000_000))
                .iconSet(DEGENERATE)
                .element(Rh)
                .flags(STD_METAL, NO_UNIFICATION)
                .build()
                .setFormula("§cR§de", true)

        }

    }

}