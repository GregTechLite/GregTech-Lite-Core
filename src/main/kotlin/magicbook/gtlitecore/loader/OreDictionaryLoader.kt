package magicbook.gtlitecore.loader

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Barite
import gregtech.api.unification.material.Materials.BrownLimonite
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cinnabar
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.YellowLimonite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.stack.ItemMaterialInfo
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BurntSienna
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CobaltAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DiaminostilbenedisulfonicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadChromate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Orpiment
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyphosphonitrileFluoroRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolytetramethyleneGlycolRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Sienna
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
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
            }

            for (rubber in arrayOf(StyreneButadieneRubber, SiliconeRubber,
                PolyphosphonitrileFluoroRubber, PolytetramethyleneGlycolRubber))
            {
                OreDictionary.registerOre("plateAnySyntheticRubber", OreDictUnifier.get(plate, rubber))
                OreDictionary.registerOre("ringAnySyntheticRubber", OreDictUnifier.get(ring, rubber))
            }

            for (rubber in arrayOf(PolyphosphonitrileFluoroRubber, PolytetramethyleneGlycolRubber))
            {
                OreDictionary.registerOre("plateAnyAdvancedSyntheticRubber", OreDictUnifier.get(plate, rubber))
                OreDictionary.registerOre("ringAnyAdvancedSyntheticRubber", OreDictUnifier.get(ring, rubber))
            }

            // sheetedFrame
            GTLiteMetaBlocks.SHEETED_FRAMES.entries.forEach { (m, b) ->
                if (m == Materials.NULL)
                    return@forEach
                b.getItem(m).let { stack ->
                    OreDictUnifier.registerOre(stack, GTLiteOrePrefix.sheetedFrame, m)
                    OreDictUnifier.registerOre(stack, ItemMaterialInfo(MaterialStack(m, 1)))
                }
            }
            // wallGt
            GTLiteMetaBlocks.WALLS.entries.forEach { (m, b) ->
                if (m == Materials.NULL)
                    return@forEach
                b.getItem(m).let { stack ->
                    OreDictUnifier.registerOre(stack, GTLiteOrePrefix.wallGt, m)
                    OreDictUnifier.registerOre(stack, ItemMaterialInfo(MaterialStack(m, 1)))
                }
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
            OreDictionary.registerOre("glassLv", Blocks.GLASS)

            OreDictionary.registerOre("glassMv", Blocks.GLASS)
            OreDictionary.registerOre("glassMv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.CLEANROOM_GLASS))

            OreDictionary.registerOre("glassHv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))
            OreDictionary.registerOre("glassHv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))

            OreDictionary.registerOre("glassEv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))
            OreDictionary.registerOre("glassEv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TITANIUM_BOROSILICATE))

            OreDictionary.registerOre("glassIv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.LAMINATED_GLASS))
            OreDictionary.registerOre("glassIv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TUNGSTEN_STEEL_BOROSILICATE))

            OreDictionary.registerOre("glassLuv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.LAMINATED_GLASS))
            OreDictionary.registerOre("glassLuv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.RHODIUM_PLATED_PALLADIUM_BOROSILICATE))

            OreDictionary.registerOre("glassZpm", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.FUSION_GLASS))
            OreDictionary.registerOre("glassZpm", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.BPA_POLYCARBONATE))
            OreDictionary.registerOre("glassZpm", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.OSMIRIDIUM_BOROSILICATE))

            OreDictionary.registerOre("glassUv", MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.FUSION_GLASS))
            OreDictionary.registerOre("glassUv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.BPA_POLYCARBONATE))
            OreDictionary.registerOre("glassUv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TRITANIUM_BOROSILICATE))
            OreDictionary.registerOre("glassUv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.ZBLAN))
            OreDictionary.registerOre("glassUv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.GST))

            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.PMMA))
            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.NEUTRONIUM_BOROSILICATE))
            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.ERBIUM_ZBLAN))
            OreDictionary.registerOre("glassUhv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.PRASEODYMIUM_ZBLAN))

            OreDictionary.registerOre("glassUev", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.CBDO_POLYCARBONATE))

            OreDictionary.registerOre("glassUiv", GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.CBDO_POLYCARBONATE))

            // TODO UXV-MAX
        }

    }

}