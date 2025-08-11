package gregtechlite.gtlitecore.api.block.variant.types

import gregtech.api.block.VariantBlock
import gregtechlite.gtlitecore.api.block.variant.VariantBlockFactory
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLiving
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

inline fun <reified T> VariantBlockFactory.makeTransparentBlock() : VariantBlock<T> where T : Enum<T>, T : IStringSerializable
{
    return object : VariantBlock<T>(Material.GLASS)
    {

        init
        {
            this.setSoundType(SoundType.GLASS)
            this.setDefaultState(getState(VALUES[0]))
            this.useNeighborBrightness = true
        }

        override fun canCreatureSpawn(state: IBlockState,
                                      world: IBlockAccess,
                                      pos: BlockPos,
                                      type: EntityLiving.SpawnPlacementType): Boolean
        {
            return false
        }

        override fun getRenderLayer() = BlockRenderLayer.CUTOUT

        override fun canRenderInLayer(state: IBlockState, layer: BlockRenderLayer): Boolean
        {
            return layer == BlockRenderLayer.TRANSLUCENT
        }

        @Deprecated("Deprecated in Java")
        override fun isOpaqueCube(state: IBlockState) = false

        @Deprecated("Deprecated in Java")
        override fun isFullCube(state: IBlockState) = false

        @Suppress("Deprecation")
        @SideOnly(Side.CLIENT)
        override fun shouldSideBeRendered(state: IBlockState,
                                          world: IBlockAccess,
                                          pos: BlockPos,
                                          side: EnumFacing): Boolean
        {
            val sideState = world.getBlockState(pos.offset(side))
            return if (sideState.getBlock() === this)
                this.getState(sideState) != getState(state)
            else
                super.shouldSideBeRendered(state, world, pos, side)
        }

    }
}