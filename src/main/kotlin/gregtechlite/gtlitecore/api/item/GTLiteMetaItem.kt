package gregtechlite.gtlitecore.api.item

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtechlite.gtlitecore.GTLiteMod
import net.minecraft.util.ResourceLocation

class GTLiteMetaItem() : StandardMetaItem(0)
{

    override fun createItemModelPath(metaValueItem: MetaItem<*>.MetaValueItem, postfix: String): ResourceLocation
        = GTLiteMod.Companion.id(formatModelPath(metaValueItem) + postfix)

}