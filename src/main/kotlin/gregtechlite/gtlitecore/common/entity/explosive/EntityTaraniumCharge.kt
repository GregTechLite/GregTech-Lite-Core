package gregtechlite.gtlitecore.common.entity.explosive

import gregtech.common.entities.EntityGTExplosive
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.world.World

class EntityTaraniumCharge(world: World?,
                           x: Double, y: Double, z: Double,
                           exploder: EntityLivingBase?) : EntityGTExplosive(world, x, y, z, exploder)
{

    constructor(world: World?) : this(world, 0.0, 0.0, 0.0, null)

    override fun getStrength() = 400.0f

    override fun dropsAllBlocks() = false

    override fun getRange() = 256

    override fun getExplosiveState(): IBlockState = GTLiteBlocks.TARANIUM_CHARGE.defaultState

}
