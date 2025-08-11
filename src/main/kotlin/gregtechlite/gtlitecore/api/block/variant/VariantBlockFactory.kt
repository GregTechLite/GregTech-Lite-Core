package gregtechlite.gtlitecore.api.block.variant

import gregtech.api.block.VariantBlock
import gregtechlite.magicbook.util.Unchecks
import gregtechlite.gtlitecore.api.block.variant.types.makeCrucibleBlock
import gregtechlite.gtlitecore.api.block.variant.types.makeMetalActiveBlock
import gregtechlite.gtlitecore.api.block.variant.types.makeMetalBlock
import gregtechlite.gtlitecore.api.block.variant.types.makeMetalCutoutBlock
import gregtechlite.gtlitecore.api.block.variant.types.makeTransparentBlock
import gregtechlite.gtlitecore.api.block.variant.types.makeWireCoilBlock
import net.minecraft.util.IStringSerializable

object VariantBlockFactory
{

    inline fun <reified T> make(type: BlockVariantType) : VariantBlock<T> where T : Enum<T>, T : IStringSerializable
    {
        return when(type)
        {
            BlockVariantType.METAL_BLOCK -> makeMetalBlock()
            BlockVariantType.METAL_CUTOUT_BLOCK -> makeMetalCutoutBlock()
            BlockVariantType.METAL_ACTIVE_BLOCK -> makeMetalActiveBlock()
            BlockVariantType.WIRE_COIL_BLOCK -> Unchecks.cast<VariantBlock<T>>(makeWireCoilBlock())
            BlockVariantType.TRANSPARENT_BLOCK -> makeTransparentBlock()
            BlockVariantType.CRUCIBLE_BLOCK -> Unchecks.cast<VariantBlock<T>>(makeCrucibleBlock())
        }
    }

}