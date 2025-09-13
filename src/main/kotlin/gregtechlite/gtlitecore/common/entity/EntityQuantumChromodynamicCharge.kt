package gregtechlite.gtlitecore.common.entity

import gregtech.common.entities.EntityGTExplosive
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.world.World

class EntityQuantumChromodynamicCharge(world: World?,
                                       x: Double, y: Double, z: Double,
                                       exploder: EntityLivingBase?) : EntityGTExplosive(world, x, y, z, exploder)
{

    constructor(world: World?) : this(world, 0.0, 0.0, 0.0, null)

    override fun getStrength() = 1600.0f

    override fun dropsAllBlocks() = false

    override fun getRange() = 4096

    override fun getExplosiveState(): IBlockState = GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE.defaultState

}
