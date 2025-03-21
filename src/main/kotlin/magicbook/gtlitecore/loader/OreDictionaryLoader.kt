package magicbook.gtlitecore.loader

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials
import gregtech.api.unification.stack.ItemMaterialInfo
import gregtech.api.unification.stack.MaterialStack
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks

@Suppress("MISSING_DEPENDENCY_CLASS")
class OreDictionaryLoader
{

    companion object
    {

        fun init()
        {
            GTLiteMetaBlocks.SHEETED_FRAMES.entries.forEach { (m, b) ->
                if (m == Materials.NULL)
                    return@forEach
                b.getItem(m).let { stack ->
                    OreDictUnifier.registerOre(stack, GTLiteOrePrefix.sheetedFrame, m)
                    OreDictUnifier.registerOre(stack, ItemMaterialInfo(MaterialStack(m, 1)))
                }
            }

            GTLiteMetaBlocks.WALLS.entries.forEach { (m, b) ->
                if (m == Materials.NULL)
                    return@forEach
                b.getItem(m).let { stack ->
                    OreDictUnifier.registerOre(stack, GTLiteOrePrefix.wallGt, m)
                    OreDictUnifier.registerOre(stack, ItemMaterialInfo(MaterialStack(m, 1)))
                }
            }

        }

    }

}