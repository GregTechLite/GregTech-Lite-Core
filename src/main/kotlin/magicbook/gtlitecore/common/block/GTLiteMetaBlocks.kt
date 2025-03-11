package magicbook.gtlitecore.common.block

import magicbook.gtlitecore.client.model.ItemModelHelper.registerItemModel
import magicbook.gtlitecore.common.block.blocks.GTLiteStoneVariantBlock
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.EnumMap

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteMetaBlocks
{

    companion object
    {

        // Enum map of all variant stones in gtlitecore, same as GregTech StoneVariantBlock.
        @JvmField
        val STONES: EnumMap<GTLiteStoneVariantBlock.StoneVariant, GTLiteStoneVariantBlock>
            = EnumMap(GTLiteStoneVariantBlock.StoneVariant::class.java)

        @JvmStatic
        fun init()
        {
            // Various stones initialization.
            for (variant: GTLiteStoneVariantBlock.StoneVariant in GTLiteStoneVariantBlock.StoneVariant.entries)
                STONES[variant] = GTLiteStoneVariantBlock(variant)
        }

        @SideOnly(Side.CLIENT)
        @JvmStatic
        fun registerItemModels()
        {
            for (block in STONES.values)
                registerItemModel(block)
        }

    }

}