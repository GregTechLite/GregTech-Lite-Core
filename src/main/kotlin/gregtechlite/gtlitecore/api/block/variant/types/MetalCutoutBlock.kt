package gregtechlite.gtlitecore.api.block.variant.types

import gregtech.api.block.VariantBlock
import gregtechlite.gtlitecore.api.block.variant.VariantBlockFactory
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLiving
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

inline fun <reified T> VariantBlockFactory.makeMetalCutoutBlock() : VariantBlock<T> where T : Enum<T>, T : IStringSerializable
{
    return object : VariantBlock<T>(Material.IRON)
    {

        init
        {
            this.setSoundType(SoundType.METAL)
            this.setDefaultState(getState(VALUES[0]))
        }

        @Deprecated("Deprecated in Java")
        override fun isOpaqueCube(state: IBlockState) = false

        @Deprecated("Deprecated in Java")
        override fun isFullCube(state: IBlockState) = false

        @SideOnly(Side.CLIENT)
        override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

        override fun canCreatureSpawn(state: IBlockState,
                                      world: IBlockAccess,
                                      pos: BlockPos,
                                      type: EntityLiving.SpawnPlacementType): Boolean
        {
            return false
        }

    }

}