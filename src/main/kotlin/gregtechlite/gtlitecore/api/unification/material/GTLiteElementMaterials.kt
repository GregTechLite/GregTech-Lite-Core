package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.unification.Elements.Fl
import gregtech.api.unification.Elements.Hs
import gregtech.api.unification.Elements.Og
import gregtech.api.unification.Elements.Rh
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
import gregtech.api.unification.material.properties.BlastProperty.GasTier
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.blastProp
import gregtechlite.gtlitecore.api.extension.cableProp
import gregtechlite.gtlitecore.api.extension.fluidPipeProp
import gregtechlite.gtlitecore.api.extension.itemPipeProp
import gregtechlite.gtlitecore.api.extension.liquid
import gregtechlite.gtlitecore.api.extension.plasma
import gregtechlite.gtlitecore.api.extension.rotorProp
import gregtechlite.gtlitecore.api.extension.toolProp
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
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Omnium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Plutonium244
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.material.GTMaterialBuilder.addMaterial
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Ad
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Crx
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.En
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Fs
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Hy
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.If
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.M
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Mx
import gregtechlite.gtlitecore.api.unification.material.element.GTLiteElements.Om
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
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.OMNIUM
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.SPACETIME
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.UNIVERSIUM
import net.minecraft.init.Enchantments

object GTLiteElementMaterials
{

    // @formatter:off

    fun init()
    {
        // 1 Adamantium
        Adamantium = addMaterial(1, "adamantium")
        {
            ingot()
            liquid().plasma()
            color(0xFF0040).iconSet(METALLIC)
            element(Ad)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_ROTOR, GENERATE_FRAME, GENERATE_GEAR,
                  GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
            blastProp(10501, GasTier.HIGH, // Tritanium
                      VA[UV], 45 * SECOND,
                      VA[ZPM], 22 * SECOND + 10 * TICK)
            rotorProp(22.0f, 10.0f, 491520)
            toolProp(140.0F, 95.0F, 49152, 6)
            {
                attackSpeed(0.5F)
                enchantability(32)
                enchantment(Enchantments.FORTUNE, 5)
                magnetic()
            }
            cableProp(V[UHV], 4, 24)
        }

        // 2 Vibranium
        Vibranium = addMaterial(2, "vibranium")
        {
            ingot()
            liquid().plasma()
            color(0xC880FF).iconSet(SHINY)
            element(Vb)
            flags(EXT2_METAL, GENERATE_FRAME, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_DOUBLE_PLATE, GENERATE_DENSE,
                  GENERATE_RING, GENERATE_ROTOR, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_ROUND, GENERATE_SPRING_SMALL)
            blastProp(12001, GasTier.HIGHER, // Adamantium
                      VA[UHV], 75 * SECOND,
                      VA[UV], 42 * SECOND + 15 * TICK)
            toolProp(155.0F, 120.0F, 73728, 7)
            {
                attackSpeed(0.8F)
                enchantability(34)
                enchantment(Enchantments.SHARPNESS, 10)
                enchantment(Enchantments.SWEEPING, 5)
                enchantment(Enchantments.LOOTING, 3)
                magnetic()
            }
        }

        // 3 Taranium
        Taranium = addMaterial(3, "taranium")
        {
            ingot()
            liquid()
            color(0x4F404F).iconSet(METALLIC)
            element(Tn)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_SPRING_SMALL, GENERATE_DENSE)
            blastProp(7000, GasTier.HIGH, // Naquadah
                      VA[ZPM], 22 * SECOND,
                      VA[IV], 11 * SECOND)
            cableProp(V[UHV], 8, 2)
        }

        // 4 Metastable Flerovium
        MetastableFlerovium = addMaterial(4, "metastable_flerovium")
        {
            ingot()
            liquid().plasma()
            color(0x521973).iconSet(SHINY)
            element(Fl)
            flags(EXT_METAL, GENERATE_DOUBLE_PLATE, GENERATE_BOLT_SCREW, GENERATE_FOIL)
            blastProp(9900, GasTier.HIGHEST, // Tritanium
                      VA[UHV], 2 * MINUTE + 35 * SECOND,
                      VA[UV], 40 * SECOND)
            rotorStats(25.0F, 3.0F, 153_600)
        }

        // 5 Metastable Oganesson
        MetastableOganesson = addMaterial(5, "metastable_oganesson")
        {
            ingot()
            liquid().plasma()
            color(0xE61C24).iconSet(SHINY)
            element(Og)
            flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_RING, GENERATE_DOUBLE_PLATE,
                  GENERATE_FRAME, GENERATE_SMALL_GEAR, GENERATE_SPRING_SMALL, GENERATE_DENSE)
            blastProp(12380, GasTier.HIGHEST, // Adamantium
                      VA[UEV], 2 * MINUTE,
                      VA[UV], 40 * SECOND)
            toolProp(55.0F, 95.0F, 110592, 7)
            {
                attackSpeed(0.2F)
                enchantability(36)
                enchantment(Enchantments.MENDING, 3)
                magnetic()
            }
            cableProp(V[UEV], 18, 9)
        }

        // 6 Metastable Hassium
        MetastableHassium = addMaterial(6, "metastable_hassium")
        {
            ingot()
            liquid().plasma()
            color(0x2D3A9D).iconSet(BRIGHT)
            element(Hs)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_FRAME)
            blastProp(12000, GasTier.HIGHEST, // Adamantium
                      VA[UEV], 4 * MINUTE + 30 * SECOND,
                      VA[UV], 1 * MINUTE)
            cableProp(V[UEV], 28, 5)
            itemPipeProp(256, 128F)
        }


        // 7 Cosmic Neutronium
        CosmicNeutronium = addMaterial(7, "cosmic_neutronium")
        {
            ingot()
            liquid()
            iconSet(COSMIC)
            element(SpNt)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_ROTOR, GENERATE_FRAME, GENERATE_GEAR,
                  GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE)
            blastProp(12600, GasTier.HIGHEST, // Adamantium
                      VA[UEV], 12 * MINUTE,
                      VA[UHV], 4 * MINUTE)
            cableProp(V[UEV], 24, 4)
        }

        // 8 Infinity
        Infinity = addMaterial(8, "infinity")
        {
            ingot()
            liquid()
            iconSet(INFINITY)
            element(If)
            flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_DOUBLE_PLATE,
                  GENERATE_DENSE, GENERATE_RING, GENERATE_ROTOR, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FRAME)
            blastProp(12600, GasTier.HIGHEST, // Adamantium
                      VA[UEV], 16 * MINUTE,
                      VA[UHV], 8 * MINUTE)
            cableProp(V[UEV], 36, 2)
            fluidPipeProp(200_000, 24000, gasProof = true, acidProof = true, cryoProof = true, plasmaProof = true)
        }

        // 9 Plutonium-244
        Plutonium244 = addMaterial(9, "plutonium_244")
        {
            ingot()
            liquid()
            {
                temperature(913)
            }
            color(0xD82D2D).iconSet(METALLIC)
            element(Pu244)
            flags(STD_METAL, GENERATE_FOIL)
        }

        // 10 Degenerate Rhenium
        DegenerateRhenium = addMaterial(10, "degenerate_rhenium")
        {
            dust()
            plasma()
            {
                temperature(1_000_000)
            }
            iconSet(DEGENERATE)
            element(Rh)
            flags(STD_METAL, NO_UNIFICATION)
        }

        // TODO: Maybe we needs a renderer which allowed to set foreground and background colors?
        // 11 Magnetium
        Magnetium = addMaterial(11, "magnetium")
        {
            ingot()
            liquid()
            {
                temperature(2_560_000)
            }
            iconSet(MAGNETIUM) // Foreground Color: 0xF2F226, Background Color: 0x21A7A7
            element(M)
            flags(EXT2_METAL, NO_UNIFICATION, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_DOUBLE_PLATE, GENERATE_DENSE,
                  GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_ROTOR,
                  GENERATE_ROUND, GENERATE_FRAME)
            blastProp(13900, GasTier.HIGHEST, // Infinity
                      VA[UIV], 30 * SECOND,
                      VA[UHV], 15 * SECOND)
        }

        // 12 Rhugnor
        Rhugnor = addMaterial(12, "rhugnor")
        {
            ingot()
            liquid()
            {
                temperature(9025)
            }
            color(0xBE00FF).iconSet(METALLIC)
            flags(EXT2_METAL, GENERATE_RING, GENERATE_GEAR, GENERATE_DOUBLE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE,
                  GENERATE_SPRING_SMALL, GENERATE_SMALL_GEAR, GENERATE_FRAME)
            element(Fs)
            toolProp(76.0F, 110.0F, 165888, 9)
            {
                attackSpeed(0.4F)
                enchantability(42)
                enchantment(Enchantments.EFFICIENCY, 6)
                enchantment(Enchantments.FORTUNE, 4)
                magnetic()
            }
            itemPipeProp(16384, 256F)
            rotorProp(48.0F, 8.0F, 98_304)
        }

        // 13 Hypogen
        Hypogen = addMaterial(13, "hypogen")
        {
            ingot()
            liquid()
            {
                temperature(11530)
            }
            color(0xDC784B).iconSet(ENRICHED) // Opacity Changing: 5-35 (per 5)
            element(Hy)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_ROTOR,
                  GENERATE_FRAME)
            cableProp(V[UIV], 16, 4)
        }

        // 14 Shirabon
        Shirabon = addMaterial(14, "shirabon")
        {
            ingot()
            liquid()
            color(0xE0156D).iconSet(BRIGHT)
            element(Sh)
            flags(EXT2_METAL, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_ROTOR,
                  GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_RING, GENERATE_FRAME, GENERATE_ROUND, GENERATE_NANITE)
            rotorProp(144.0F, 2.4F, 786_432)
        }

        // TODO: New renderer with rotate animation.
        // 15 Transcendent Metal
        TranscendentMetal = addMaterial(15, "transcendent_metal")
        {
            ingot()
            liquid()
            color(0x1A1A1A).iconSet(METALLIC)
            element(Tsx)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_ROTOR, GENERATE_FRAME, GENERATE_GEAR,
                  GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_FINE_WIRE,
                  GENERATE_NANITE)
            blastProp(23500, GasTier.HIGHEST, // Eternity Plus
                      VA[UXV], 3 * MINUTE,
                      VA[UIV], 2 * MINUTE)
            fluidPipeProp(800_000, 40000, gasProof = true, acidProof = true, cryoProof = true, plasmaProof = true)
        }

        // 16 Space Time
        SpaceTime = addMaterial(16, "space_time")
        {
            ingot()
            liquid()
            iconSet(SPACETIME)
            element(Spx)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_FRAME,
                  GENERATE_GEAR, GENERATE_SMALL_GEAR)
            blastProp(16201, GasTier.HIGHEST, // Halkonite Steel
                      VA[UXV], 4 * MINUTE + 30 * SECOND,
                      VA[UIV], 3 * MINUTE + 25 * SECOND)
            cableProp(V[UXV], 96,16)
            fluidPipeProp(1_200_000, 65000, gasProof = true, acidProof = true, cryoProof = true, plasmaProof = true)
        }

        // 17 Creon
        Creon = addMaterial(17, "creon")
        {
            ingot()
            liquid().plasma()
            color(0x460046).iconSet(SHINY)
            element(Crx)
            flags(EXT2_METAL, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_RING,
                  GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_ROTOR, GENERATE_FRAME, GENERATE_ROUND, GENERATE_FOIL,
                  GENERATE_FINE_WIRE)
            blastProp(24000, GasTier.HIGHEST, // Eternity Plus
                      VA[UXV], 2 * MINUTE + 40 * SECOND,
                      VA[UIV], 1 * MINUTE + 45 * SECOND)
            toolProp(160.0F, 320.0F, 62_914_560, 20)
            {
                attackSpeed(0.2F)
                enchantability(40)
                enchantment(Enchantments.SHARPNESS, 20)
                enchantment(Enchantments.SWEEPING, 20)
                enchantment(Enchantments.LOOTING, 20)
                enchantment(Enchantments.FORTUNE, 20)
                magnetic()
                unbreakable()
            }
            rotorProp(576.0F, 64.0F, 62914560)
        }

        // 18 Mag Matter
        MagMatter = addMaterial(18, "mag_matter")
        {
            ingot()
            liquid()
            iconSet(MAGMATTER)
            element(Mx)
            flags(EXT2_METAL, NO_UNIFICATION, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE,
                  GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_RING, GENERATE_ROTOR, GENERATE_FRAME,
                  GENERATE_NANITE)
            cableProp(V[UXV], 56, 14)
        }

        // TODO: Finished renderer for universium and supported block format iconSet textures.
        // 19 Universium
        Universium = addMaterial(19, "universium")
        {
            ingot()
            liquid()
            iconSet(UNIVERSIUM)
            element(Ux)
            flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_NANITE,
                  GENERATE_FRAME)
            cableProp(V[OpV], 144, 8)
        }

        // 20 Eternity
        Eternity = addMaterial(20, "eternity")
        {
            ingot()
            liquid()
            iconSet(ETERNITY)
            element(En)
            flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_NANITE,
                  GENERATE_ROTOR, GENERATE_SPRING, GENERATE_SPRING_SMALL, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME,
                  GENERATE_ROUND)
            cableProperties(V[OpV], 180, 40)
        }

        // 21 Omnium
        Omnium = addMaterial(21, "omnium")
        {
            ingot()
            liquid()
            iconSet(OMNIUM)
            element(Om)
            flags(EXT2_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_FRAME, GENERATE_ROTOR, GENERATE_ROUND,
                  GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_GEAR, GENERATE_SMALL_GEAR)
            cableProp(V[MAX] - 1, 360, 20)
        }

    }

    // @formatter:on

}