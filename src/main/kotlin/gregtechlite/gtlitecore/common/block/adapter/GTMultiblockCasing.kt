package gregtechlite.gtlitecore.common.block.adapter

import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * @see gregtech.common.blocks.BlockMultiblockCasing
 */
enum class GTMultiblockCasing : BlockVariant
{

    ENGINE_INTAKE_CASING,
    EXTREME_ENGINE_INTAKE_CASING,
    GRATE_CASING,
    ASSEMBLY_CONTROL,
    ASSEMBLY_LINE_CASING;

    override val state: IBlockState
        get() = MetaBlocks.MULTIBLOCK_CASING.getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return MetaBlocks.MULTIBLOCK_CASING.getItemVariant(
            BlockMultiblockCasing.MultiblockCasingType.valueOf(this.name), count)
    }

}