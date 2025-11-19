package gregtechlite.gtlitecore.common.item

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtechlite.gtlitecore.GTLiteMod
import net.minecraft.util.ResourceLocation

class GTLiteMetaItem() : StandardMetaItem(0)
{

    override fun createItemModelPath(metaValueItem: MetaItem<*>.MetaValueItem, postfix: String): ResourceLocation
        = GTLiteMod.id(formatModelPath(metaValueItem) + postfix)

}