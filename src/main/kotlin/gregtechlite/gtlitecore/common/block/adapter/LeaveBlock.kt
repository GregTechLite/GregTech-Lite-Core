package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

@Suppress("Deprecation")
enum class LeaveBlock : BlockVariant
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
        get() = if (this.ordinal >= 4)
        {
            if (this.ordinal >= 8)
            {
                GTLiteBlocks.LEAVES[2].getStateFromMeta((this.ordinal - 8) * 4)
            }
            else
            {
                GTLiteBlocks.LEAVES[1].getStateFromMeta((this.ordinal - 4) * 4)
            }
        }
        else
        {
            GTLiteBlocks.LEAVES[0].getStateFromMeta(this.ordinal * 4)
        }

    override fun getStack(count: Int): ItemStack
    {
        return if (this.ordinal >= 4)
        {
            if (this.ordinal >= 8)
            {
                GameRegistry.makeItemStack("$MOD_ID:leaves_2", (this.ordinal - 8) * 4, count, null)
            }
            else
            {
                GameRegistry.makeItemStack("$MOD_ID:leaves_1", (this.ordinal - 4) * 4, count, null)
            }
        }
        else
        {
            GameRegistry.makeItemStack("$MOD_ID:leaves_0", this.ordinal * 4, count, null)
        }
    }

}