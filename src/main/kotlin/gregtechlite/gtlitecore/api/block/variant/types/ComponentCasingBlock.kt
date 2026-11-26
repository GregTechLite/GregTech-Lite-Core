package gregtechlite.gtlitecore.api.block.variant.types

import gregtech.api.block.VariantBlock
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.block.ReTexturableCasingRegistry
import gregtechlite.gtlitecore.api.block.ReTexturableCasing
import gregtechlite.gtlitecore.api.block.variant.VariantBlockFactory
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLiving
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.IStringSerializable
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.property.ExtendedBlockState
import net.minecraftforge.common.property.IExtendedBlockState
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class ComponentCasingBlock<T> : VariantBlock<T>(Material.IRON), ReTexturableCasing
    where T : Enum<T>, T : IStringSerializable
{
    init
    {
        setSoundType(SoundType.METAL)
        setDefaultState(getState(VALUES[0]))
    }

    override val casingModel: ResourceLocation
        get() = GTLiteMod.id("block/${checkNotNull(registryName).path}")

    override fun createBlockState(): BlockStateContainer
    {
        val baseState = super.createBlockState()
        return ExtendedBlockState(this, baseState.properties.toTypedArray(),
                                  arrayOf(ReTexturableCasingRegistry.BASE_TEXTURE))
    }

    override fun getExtendedState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState
    {
        val texture = ReTexturableCasingRegistry.get(pos) ?: return state
        val extendedState = state as? IExtendedBlockState ?: return state
        return extendedState.withProperty(ReTexturableCasingRegistry.BASE_TEXTURE, texture)
    }

    @Deprecated("Deprecated in Java")
    override fun isOpaqueCube(state: IBlockState) = false

    @Deprecated("Deprecated in Java")
    override fun isFullCube(state: IBlockState) = false

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    override fun canRenderInLayer(state: IBlockState, layer: BlockRenderLayer): Boolean
        = layer == BlockRenderLayer.CUTOUT || layer == BlockRenderLayer.TRANSLUCENT

    override fun canCreatureSpawn(state: IBlockState, world: IBlockAccess, pos: BlockPos,
                                  type: EntityLiving.SpawnPlacementType): Boolean = false
}

inline fun <reified T> VariantBlockFactory.makeComponentCasingBlock(): VariantBlock<T>
    where T : Enum<T>, T : IStringSerializable
{
    return object : ComponentCasingBlock<T>()
    {
        override fun computeVariants(): Collection<T> = enumValues<T>().toList()
    }
}
