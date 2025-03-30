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
import gregtech.api.unification.material.Materials.YellowLimonite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.stack.ItemMaterialInfo
import gregtech.api.unification.stack.MaterialStack
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BurntSienna
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CobaltAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadChromate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Orpiment
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Sienna
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraftforge.oredict.OreDictionary

@Suppress("MISSING_DEPENDENCY_CLASS")
class OreDictionaryLoader
{

    companion object
    {

        fun init()
        {
            // Add more chemistry products to dye.
            dyeAddition()

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

        private fun dyeAddition()
        {
            // Add three new dyes for each color, consists of ore dyes, organic dyes etc.

            // White
            OreDictionary.registerOre("dyeWhite", OreDictUnifier.get(dust, LeadNitrate))

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

    }

}