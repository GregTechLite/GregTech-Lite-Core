package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

@Suppress("Deprecation")
enum class PlankBlock : BlockVariant
{

    BANANA,
    ORANGE,
    MANGO,
    APRICOT,
    LEMON,
    LIME,
    OLIVE,
    NUTMEG,
    COCONUT,
    RAINBOW;

    override val state: IBlockState
        get() = GTLiteMetaBlocks.PLANKS[0].getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return GameRegistry.makeItemStack("$MOD_ID:gtlite_planks_0", this.ordinal, count, null)
    }

}