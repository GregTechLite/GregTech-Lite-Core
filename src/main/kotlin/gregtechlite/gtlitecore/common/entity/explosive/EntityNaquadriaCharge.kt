package gregtechlite.gtlitecore.common.entity.explosive

import gregtech.common.entities.EntityGTExplosive
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.world.World

class EntityNaquadriaCharge(world: World?,
                            x: Double, y: Double, z: Double,
                            exploder: EntityLivingBase?) : EntityGTExplosive(world, x, y, z, exploder)
{

    constructor(world: World?) : this(world, 0.0, 0.0, 0.0, null)

    override fun getStrength() = 200.0f

    override fun dropsAllBlocks() = false

    override fun getRange() = 64

    override fun getExplosiveState(): IBlockState = GTLiteMetaBlocks.NAQUADRIA_CHARGE.defaultState

}

