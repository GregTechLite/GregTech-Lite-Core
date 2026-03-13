package gregtechlite.gtlitecore.api.block.variant.types

import gregtech.api.block.VariantActiveBlock
import gregtech.api.block.VariantItemBlock
import gregtech.client.utils.TooltipHelper
import gregtech.common.ConfigHolder
import gregtech.common.metatileentities.multi.electric.MetaTileEntityMultiSmelter
import gregtechlite.gtlitecore.api.block.variant.VariantBlockFactory
import gregtechlite.gtlitecore.common.block.variant.WireCoil
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.EntityLiving
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

fun VariantBlockFactory.makeWireCoilBlock() : VariantActiveBlock<WireCoil>
{
    return object : VariantActiveBlock<WireCoil>(Material.IRON)
    {

        init
        {
            this.setSoundType(SoundType.METAL)
            this.setDefaultState(getState(WireCoil.ADAMANTIUM))
        }

        override fun computeVariants(): Collection<WireCoil> = enumValues<WireCoil>().toList()

        override fun canCreatureSpawn(state: IBlockState,
                                      world: IBlockAccess,
                                      pos: BlockPos,
                                      type: EntityLiving.SpawnPlacementType): Boolean
        {
            return false
        }

        override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.SOLID

        override fun isBloomEnabled(value: WireCoil): Boolean
        {
            return ConfigHolder.client.coilsActiveEmissiveTextures
        }

        @SideOnly(Side.CLIENT)
        override fun addInformation(itemStack: ItemStack,
                                    worldIn: World?,
                                    lines: MutableList<String?>,
                                    tooltipFlag: ITooltipFlag)
        {
            super.addInformation(itemStack, worldIn, lines, tooltipFlag)
            val itemBlock: VariantItemBlock<*, *> = itemStack.item as VariantItemBlock<*, *>
            val stackState = itemBlock.getBlockState(itemStack)
            val coilType = this.getState(stackState)
            lines.add(I18n.format("tile.wire_coil.tooltip_heat", coilType.coilTemperature))
            if (TooltipHelper.isShiftDown())
            {
                val coilTier = coilType.tier
                lines.add(I18n.format("tile.wire_coil.tooltip_smelter"))
                lines.add(I18n.format("tile.wire_coil.tooltip_parallel_smelter", coilType.level * 32))
                val eut = MetaTileEntityMultiSmelter.getEUtForParallel(
                    MetaTileEntityMultiSmelter.getMaxParallel(coilType.level),
                    coilType.energyDiscount)
                lines.add(I18n.format("tile.wire_coil.tooltip_energy_smelter", eut))
                lines.add(I18n.format("tile.wire_coil.tooltip_pyro"))
                lines.add(I18n.format("tile.wire_coil.tooltip_speed_pyro", if (coilTier == 0) 75 else 50 * (coilTier + 1)))
                lines.add(I18n.format("tile.wire_coil.tooltip_cracking"))
                lines.add(I18n.format("tile.wire_coil.tooltip_energy_cracking", 100 - 10 * coilTier))
            }
            else
            {
                lines.add(I18n.format("tile.wire_coil.tooltip_extended_info"))
            }
        }

    }

}