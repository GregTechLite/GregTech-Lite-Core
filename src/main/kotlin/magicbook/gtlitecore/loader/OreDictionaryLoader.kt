package magicbook.gtlitecore.loader

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Barite
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.BrownLimonite
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cinnabar
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tennessine
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.YellowLimonite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BurntSienna
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CobaltAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DiaminostilbenedisulfonicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorescein
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadChromate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Orpiment
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Plutonium244
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyphosphonitrileFluoroRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolytetramethyleneGlycolRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Sienna
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing02
import net.minecraft.init.Blocks
import net.minecraftforge.oredict.OreDictionary

@Suppress("MISSING_DEPENDENCY_CLASS")
class OreDictionaryLoader
{

    companion object
    {

        fun init()
        {
            // Add more chemistry products to dye.
            chromaticDyes()
            // Add ore dictionaries to tiered glasses.
            tieredGlasses()

            // Add treeSapling ore dictionary to all gtlite trees.
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getItem("gtlite_sapling_0"))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 2))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 4))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 6))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 8))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 10))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 12))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 14))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getItem("gtlite_sapling_1"))
            OreDictionary.registerOre("treeSapling", Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_1", 2))

            // Let rubbers compatible with a fake tiered in recipes.
            for (rubber in arrayOf(Rubber, StyreneButadieneRubber, SiliconeRubber,
                PolyphosphonitrileFluoroRubber, PolytetramethyleneGlycolRubber))
            {
                OreDictionary.registerOre("plateAnyRubber", OreDictUnifier.get(plate, rubber))
                OreDictionary.registerOre("ringAnyRubber", OreDictUnifier.get(ring, rubber))
                OreDictionary.registerOre("foilAnyRubber", OreDictUnifier.get(foil, rubber))
            }

            for (rubber in arrayOf(StyreneButadieneRubber, SiliconeRubber,
                PolyphosphonitrileFluoroRubber, PolytetramethyleneGlycolRubber))
            {
                OreDictionary.registerOre("plateAnySyntheticRubber", OreDictUnifier.get(plate, rubber))
                OreDictionary.registerOre("ringAnySyntheticRubber", OreDictUnifier.get(ring, rubber))
                OreDictionary.registerOre("foilAnySyntheticRubber", OreDictUnifier.get(foil, rubber))
            }

            for (rubber in arrayOf(PolyphosphonitrileFluoroRubber, PolytetramethyleneGlycolRubber))
            {
                OreDictionary.registerOre("plateAnyAdvancedSyntheticRubber", OreDictUnifier.get(plate, rubber))
                OreDictionary.registerOre("ringAnyAdvancedSyntheticRubber", OreDictUnifier.get(ring, rubber))
                OreDictionary.registerOre("foilAnyAdvancedSyntheticRubber", OreDictUnifier.get(foil, rubber))
            }

            // Radioactive dusts.
            for (material in arrayOf(Technetium, Thorium, Protactinium, Uranium, Uranium235, Uranium238,
                Neptunium, Plutonium239, Plutonium241, Plutonium244, Americium, Curium, Berkelium, Californium,
                Einsteinium, Fermium, Mendelevium, Nobelium, Lawrencium, Rutherfordium, Dubnium, Seaborgium,
                Bohrium, MetastableHassium, Meitnerium, Darmstadtium, Roentgenium, Copernicium, Nihonium,
                MetastableFlerovium, Moscovium, Livermorium, Tennessine, MetastableOganesson, Naquadah,
                NaquadahEnriched, Naquadria, Taranium))
            {
                OreDictionary.registerOre("dustRadioactive", OreDictUnifier.get(dust, material))
                OreDictionary.registerOre("dustSmallRadioactive", OreDictUnifier.get(dustSmall, material))
                OreDictionary.registerOre("dustTinyRadioactive", OreDictUnifier.get(dustTiny, material))
            }

        }

        private fun chromaticDyes()
        {
            // Add three new dyes for each color, consists of ore dyes, organic dyes etc.

            // White
            OreDictionary.registerOre("dyeWhite", OreDictUnifier.get(dust, LeadNitrate))
            OreDictionary.registerOre("dyeWhite", OreDictUnifier.get(dust, DiaminostilbenedisulfonicAcid))

            // Orange
            OreDictionary.registerOre("dyeOrange", OreDictUnifier.get(dust, BrownLimonite))
            OreDictionary.registerOre("dyeOrange", OreDictUnifier.get(dust, YellowLimonite))

            // Magenta

            // Light Blue

            // Yellow
            OreDictionary.registerOre("dyeYellow", OreDictUnifier.get(dust, Massicot)) // "Lead Yellow"
            OreDictionary.registerOre("dyeYellow", OreDictUnifier.get(dust, LeadChromate))
            OreDictionary.registerOre("dyeYellow", OreDictUnifier.get(dust, Orpiment)) // "Arsenic Yellow"
            OreDictionary.registerOre("dyeYellow", OreDictUnifier.get(dust, Fluorescein))

            // Lime

            // Pink

            // Gray
            OreDictionary.registerOre("dyeGray", OreDictUnifier.get(dust, Pyrolusite))

            // Light Gray (Silver)
            OreDictionary.registerOre("dyeLightGray", OreDictUnifier.get(dust, Barite))

            // Cyan

            // Purple

            // Blue
            OreDictionary.registerOre("dyeBlue", OreDictUnifier.get(dust, CobaltAluminate)) // "Cobalt Blue"
            OreDictionary.registerOre("dyeBlue", OreDictUnifier.get(dust, Azurite)) // "Copper Blue"
            // TODO Prussian Blue?

            // Brown
            OreDictionary.registerOre("dyeBrown", OreDictUnifier.get(dust, BandedIron))
            OreDictionary.registerOre("dyeBrown", OreDictUnifier.get(dust, Sienna))

            // Green
            OreDictionary.registerOre("dyeGreen", OreDictUnifier.get(dust, Malachite))
            // TODO Scheele Green?

            // Red
            OreDictionary.registerOre("dyeRed", OreDictUnifier.get(dust, Cinnabar))
            OreDictionary.registerOre("dyeRed", OreDictUnifier.get(dust, Realgar))
            OreDictionary.registerOre("dyeRed", OreDictUnifier.get(dust, BurntSienna))

            // Black
            OreDictionary.registerOre("dyeBlack", OreDictUnifier.get(dust, Carbon))
            OreDictionary.registerOre("dyeBlack", OreDictUnifier.get(dust, Graphite))

        }

        private fun tieredGlasses() // TODO Redo it with MarkerMaterials system or not?
        {
            // LV: Glass (vanilla)
            OreDictionary.registerOre("glassLv", Blocks.GLASS)
            // MV: Glass (vanilla), Cleanroom Glass
            OreDictionary.registerOre("glassMv", Blocks.GLASS)
            OreDictionary.registerOre("glassMv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.CLEANROOM_GLASS))
            // HV: Tempered Glass, Borosilicate Glass
            OreDictionary.registerOre("glassHv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))
            OreDictionary.registerOre("glassHv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
            // EV: Tempered Glass, Titanium reinforced Borosilicate Glass
            OreDictionary.registerOre("glassEv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))
            OreDictionary.registerOre("glassEv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.SILICON_CARBIDE))
            OreDictionary.registerOre("glassEv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.LEAD_SILICON))
            OreDictionary.registerOre("glassEv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TITANIUM_BOROSILICATE))
            // IV: Laminated Glass, Tungsten Steel reinforced Borosilicate Glass
            OreDictionary.registerOre("glassIv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.LAMINATED_GLASS))
            OreDictionary.registerOre("glassIv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.THORIUM_YTTRIUM))
            OreDictionary.registerOre("glassIv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TUNGSTEN_STEEL_BOROSILICATE))
            // LuV: Laminated Glass, Rhodium Plated Palladium reinforced Borosilicate Glass
            OreDictionary.registerOre("glassLuv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.LAMINATED_GLASS))
            OreDictionary.registerOre("glassLuv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.WOODS))
            OreDictionary.registerOre("glassLuv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.RHODIUM_PLATED_PALLADIUM_BOROSILICATE))
            // ZPM: Fusion Glass, BPA Polycarbonate Glass, Osmiridium reinforced Borosilicate Glass
            OreDictionary.registerOre("glassZpm", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.FUSION_GLASS))
            OreDictionary.registerOre("glassZpm", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.BPA_POLYCARBONATE))
            OreDictionary.registerOre("glassZpm", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.OSMIRIDIUM_BOROSILICATE))
            // UV: Fusion Glass, BPA Polycarbonate Glass, Tritanium reinforced Borosilicate Glass, ZBLAN Glass, GST Glass
            OreDictionary.registerOre("glassUv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.FUSION_GLASS))
            OreDictionary.registerOre("glassUv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.BPA_POLYCARBONATE))
            OreDictionary.registerOre("glassUv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TRITANIUM_BOROSILICATE))
            OreDictionary.registerOre("glassUv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.ZBLAN))
            OreDictionary.registerOre("glassUv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.GST))
            // UHV: PMMA Glass, Neutronium reinforced Borosilicate Glass, Erbium-doped ZBLAN Glass, Praseodymium-doped ZBLAN Glass, Quantum Glass
            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.PMMA))
            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.NEUTRONIUM_BOROSILICATE))
            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.ERBIUM_ZBLAN))
            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.PRASEODYMIUM_ZBLAN))
            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.QUANTUM))
            // UEV: CBDO Polycarbonate Glass, Cosmic Neutronium reinforced Borosilicate Glass, Force Field Glass
            OreDictionary.registerOre("glassUev", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.CBDO_POLYCARBONATE))
            OreDictionary.registerOre("glassUev", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.COSMIC_NEUTRONIUM_BOROSILICATE))
            OreDictionary.registerOre("glassUev", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.FORCE_FIELD))
            // UIV: CBDO Polycarbonate Glass, Infinity reinforced Borosilicate Glass, Antimatter Containment Glass
            OreDictionary.registerOre("glassUiv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.CBDO_POLYCARBONATE))
            OreDictionary.registerOre("glassUiv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.INFINITY_BOROSILICATE))
            OreDictionary.registerOre("glassUiv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.ANTIMATTER_CONTAINMENT))
            // UXV: NaN
            // ...
            // OpV: NaN
            // ...
            // MAX: NaN
            // ...
        }

    }

}