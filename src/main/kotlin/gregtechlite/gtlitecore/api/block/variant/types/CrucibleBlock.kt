package gregtechlite.gtlitecore.api.block.variant.types

import gregtech.api.block.VariantBlock
import gregtech.api.util.TextFormattingUtil
import gregtechlite.gtlitecore.api.block.variant.VariantBlockFactory
import gregtechlite.gtlitecore.common.block.variant.Crucible
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.EntityLiving
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

fun VariantBlockFactory.makeCrucibleBlock() : VariantBlock<Crucible>
{
    return object : VariantBlock<Crucible>(Material.IRON)
    {

        init
        {
            this.setSoundType(SoundType.METAL)
            this.setDefaultState(getState(Crucible.BRONZE))
        }

        override fun computeVariants(): Collection<Crucible> = enumValues<Crucible>().toList()

        override fun canCreatureSpawn(state: IBlockState,
                                      world: IBlockAccess,
                                      pos: BlockPos,
                                      type: EntityLiving.SpawnPlacementType): Boolean
        {
            return false
        }

        override fun addInformation(stack: ItemStack,
                                    world: World?,
                                    tooltip: MutableList<String?>,
                                    advanced: ITooltipFlag)
        {
            super.addInformation(stack, world, tooltip, advanced)
            tooltip.add(I18n.format("gregtech.multiblock.blast_furnace.max_temperature",
                TextFormatting.RED.toString() + TextFormattingUtil.formatNumbers(
                    this.getState(stack).temperature.toLong()) + "K"))
        }

    }
}