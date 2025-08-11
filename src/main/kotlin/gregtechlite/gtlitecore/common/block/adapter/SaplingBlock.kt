package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

@Suppress("Deprecation")
enum class SaplingBlock : BlockVariant
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
        get() = if (this.ordinal >= 8)
        {
            GTLiteMetaBlocks.SAPLINGS[1].getStateFromMeta((this.ordinal - 8) * 2)
        }
        else
        {
            GTLiteMetaBlocks.SAPLINGS[0].getStateFromMeta(this.ordinal * 2)
        }

    override fun getStack(count: Int): ItemStack
    {
        return if (this.ordinal >= 8)
        {
            GameRegistry.makeItemStack("$MOD_ID:gtlite_sapling_1", (this.ordinal - 8) * 2, count, null)
        }
        else
        {
            GameRegistry.makeItemStack("$MOD_ID:gtlite_sapling_0", this.ordinal * 2, count, null)
        }
    }

}