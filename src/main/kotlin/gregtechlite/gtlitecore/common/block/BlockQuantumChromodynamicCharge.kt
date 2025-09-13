package gregtechlite.gtlitecore.common.block

import gregtech.common.blocks.explosive.BlockGTExplosive
import gregtech.common.entities.EntityGTExplosive
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.common.entity.EntityQuantumChromodynamicCharge
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockQuantumChromodynamicCharge : BlockGTExplosive(Material.TNT, true, true, 2 * MINUTE)
{

    init
    {
        setHardness(0f)
        setSoundType(SoundType.PLANT)
    }

    override fun createEntity(world: World?, pos: BlockPos, exploder: EntityLivingBase?): EntityGTExplosive
    {
        val x = pos.x + 0.5f
        val y = pos.y.toFloat()
        val z = pos.z + 0.5f
        return EntityQuantumChromodynamicCharge(world, x.toDouble(), y.toDouble(), z.toDouble(), exploder)
    }

    override fun addInformation(stack: ItemStack,
                                world: World?,
                                tooltip: MutableList<String?>,
                                flag: ITooltipFlag)
    {
        super.addInformation(stack, world, tooltip, flag)
        tooltip.add(I18n.format("tile.gtlitecore.quantum_chromodynamic_charge.tooltip.1"))
        tooltip.add(I18n.format("tile.gtlitecore.quantum_chromodynamic_charge.tooltip.2"))
    }

}
