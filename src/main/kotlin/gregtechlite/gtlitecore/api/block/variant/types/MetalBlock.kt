package gregtechlite.gtlitecore.api.block.variant.types

import gregtech.api.block.VariantBlock
import gregtechlite.gtlitecore.api.block.variant.VariantBlockFactory
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLiving
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

inline fun <reified T> VariantBlockFactory.makeMetalBlock() : VariantBlock<T> where T : Enum<T>, T : IStringSerializable
{
    return object: VariantBlock<T>(Material.IRON)
    {

        init
        {
            this.setSoundType(SoundType.METAL)
            this.setDefaultState(getState(VALUES[0]))
        }

        override fun canCreatureSpawn(state: IBlockState,
                                      world: IBlockAccess,
                                      pos: BlockPos,
                                      type: EntityLiving.SpawnPlacementType): Boolean
        {
            return false
        }

    }
}