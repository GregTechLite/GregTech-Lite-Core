package gregtechlite.gtlitecore.common.block.base

import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.common.blocks.properties.PropertyMaterial
import gregtech.common.creativetab.GTCreativeTabs
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import gregtechlite.gtlitecore.api.extension.toItem
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType
import gregtechlite.gtlitecore.client.model.MaterialBlockStateLoader
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.EnumPushReaction
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.util.NonNullList
import net.minecraft.util.Rotation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.ObfuscationReflectionHelper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("Deprecation") // TODO Used mixins accessor replaced obf helper?
class BlockSheetedFrame(materials: Array<Material>) : Block(net.minecraft.block.material.Material.IRON)
{

    val variantProperty: PropertyMaterial

    /**
     * Modified Material, Harvest Tool and Sound Type by Block#getMaterial(),
     * Block#getHarvestTool() and Block#getSoundType() because we should declare
     * Gregtech material, when it is wooden material, then return axe and wood
     * sound type, otherwise return wrench and metal sound type.
     */
    init
    {
        this.setHardness(3.0f)
        this.setResistance(5.0f)
        this.setTranslationKey("sheeted_frame")
        this.setCreativeTab(GTCreativeTabs.TAB_GREGTECH_MATERIALS)
        this.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)

        this.variantProperty = PropertyMaterial.create("variant", materials)

        val stateContainer = this.createStateContainer()
        ObfuscationReflectionHelper.setPrivateValue(Block::class.java, this,
                                                    stateContainer, 21) // this.stateContainer
        this.defaultState = stateContainer.baseState
            .withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.Y)
    }

    companion object
    {

        val SHEETED_FRAME_AXIS: PropertyEnum<FrameEnumAxis> = PropertyEnum.create("axis", FrameEnumAxis::class.java)

        fun getItem(blockState: IBlockState): ItemStack = blockState.toItem()

    }

    override fun createBlockState() = BlockStateContainer(this)

    private fun createStateContainer() = BlockStateContainer(this, SHEETED_FRAME_AXIS, this.variantProperty)

    override fun canCreatureSpawn(blockState: IBlockState,
                                  worldIn: IBlockAccess,
                                  blockPos: BlockPos,
                                  type: EntityLiving.SpawnPlacementType) = false

    override fun onEntityCollision(worldIn: World,
                                   blockPos: BlockPos,
                                   blockState: IBlockState,
                                   entityIn: Entity
    )
    {
        entityIn.motionX = MathHelper.clamp(entityIn.motionX, -0.15, 0.15)
        entityIn.motionZ = MathHelper.clamp(entityIn.motionZ, -0.15, 0.15)
        entityIn.fallDistance = 0.0f
        if (entityIn.motionY < -0.15) entityIn.motionY = -0.15

        if (entityIn.isSneaking && entityIn.motionY < 0.0) entityIn.motionY = 0.0

        if (entityIn.collidedHorizontally) entityIn.motionY = 0.3
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for
     * adjustments to the IBlockState.
     */

    @Deprecated("Deprecated in Java")
    override fun getStateForPlacement(worldIn: World, blockPos: BlockPos,
                                      facing: EnumFacing,
                                      hitX: Float, hitY: Float, hitZ: Float, meta: Int,
                                      placer: EntityLivingBase
    ): IBlockState
    {
        return this.getStateFromMeta(meta).withProperty(SHEETED_FRAME_AXIS,
            FrameEnumAxis.Companion.fromFacingAxis(facing.axis))
    }

    /**
     * Axis of block is related two 2 most significant bits in first four bits; indexing
     * with (meta % 16) / 4.
     */
    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState
    {
        var meta = meta
        if (meta > 15) meta = 0
        return this.defaultState
            .withProperty(this.variantProperty, this.variantProperty
                .allowedValues[meta and 3])
            .withProperty(SHEETED_FRAME_AXIS,
                FrameEnumAxis.entries[(meta and 15) ushr 2])
    }

    /**
     * Place axis value in top two bits of first four bits of meta:
     * (X: 00, Y: 01, Z: 10, NONE: 11); and place result in lowest two bits.
     */
    override fun getMetaFromState(blockState: IBlockState): Int
    {
        var meta = (blockState.getValue(SHEETED_FRAME_AXIS).ordinal shl 2)
        meta = meta or this.variantProperty.allowedValues
            .indexOf(blockState.getValue(this.variantProperty))
        return meta
    }

    @Deprecated("Deprecated in Java")
    override fun withRotation(blockState: IBlockState, rotation: Rotation): IBlockState
    {
        return when (rotation)
        {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> when (blockState.getValue(SHEETED_FRAME_AXIS))
            {
                FrameEnumAxis.X -> blockState.withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.Z)
                FrameEnumAxis.Z -> blockState.withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.X)
                else -> blockState // Y -> Y
            }
            else -> blockState
        }
    }

    @Deprecated("Deprecated in Java")
    override fun isOpaqueCube(blockState: IBlockState) = false

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer
    {
        return BlockRenderLayer.CUTOUT_MIPPED
    }

    override fun damageDropped(blockState: IBlockState): Int
    {
        return this.getMetaFromState(blockState)
    }


    @Deprecated("Deprecated in Java")
    override fun getMaterial(blockState: IBlockState): net.minecraft.block.material.Material
    {
        val material = blockState.getValue(this.variantProperty)
        return if (ModHandler.isMaterialWood(material))
            net.minecraft.block.material.Material.WOOD
        else
            super.getMaterial(blockState)
    }

    override fun getHarvestTool(blockState: IBlockState): String?
    {
        val material = blockState.getValue(this.variantProperty)
        return if (ModHandler.isMaterialWood(material)) "axe" else "wrench"
    }

    override fun getHarvestLevel(blockState: IBlockState) = 1

    override fun getSoundType(blockState: IBlockState,
                              worldIn: World,
                              blockPos: BlockPos,
                              entity: Entity?): SoundType
    {
        val material = blockState.getValue(this.variantProperty)
        return if (ModHandler.isMaterialWood(material)) SoundType.WOOD else SoundType.METAL
    }

    override fun getSubBlocks(creativeTab: CreativeTabs, stacks: NonNullList<ItemStack?>)
    {
        this.blockState.validStates
            .filter { state ->
                state.getValue(this.variantProperty) !== Materials.NULL
                        && getMetaFromState(state) ushr 2 == 1
            }
            .forEach { state ->
                stacks.add(getItem(state))
            }
    }

    fun getItem(material: Material): ItemStack = getItem(this.defaultState
        .withProperty(this.variantProperty, material)
        .withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.Y))

    fun getBlock(material: Material): IBlockState = this.defaultState
        .withProperty(this.variantProperty, material)
        .withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.Y)

    /**
     * Only bottom two bits are relevant for getting material.
     */
    fun getGTMaterial(meta: Int): Material = this.variantProperty.allowedValues[meta and 3]

    fun getGTMaterial(blockState: IBlockState): Material = getGTMaterial(getMetaFromState(blockState))

    @Deprecated("Deprecated in Java")
    override fun getPushReaction(blockState: IBlockState) = EnumPushReaction.NORMAL

    @Deprecated("Deprecated in Java")
    override fun getCollisionBoundingBox(blockState: IBlockState,
                                         worldIn: IBlockAccess,
                                         blockPos: BlockPos
    ): AxisAlignedBB? = when (getMetaFromState(blockState) ushr 2)
    {
        0 -> AxisAlignedBB(0.05, 0.0, 0.00, 0.95, 1.0, 1.00)
        2 -> AxisAlignedBB(0.00, 0.0, 0.05, 1.0, 1.0, 0.95)
        else -> AxisAlignedBB(0.00, 0.0, 0.00, 1.0, 1.0, 1.0)
    }

    @Deprecated("Deprecated in Java")
    override fun getBlockFaceShape(worldIn: IBlockAccess,
                                   blockState: IBlockState,
                                   blockPos: BlockPos,
                                   face: EnumFacing
    ) = BlockFaceShape.UNDEFINED

    @SideOnly(Side.CLIENT)
    fun onModelRegister()
    {
        val models = Object2ObjectOpenHashMap<IBlockState?, ModelResourceLocation?>()
        for (state in getBlockState().validStates)
        {
            val material = getGTMaterial(state)
            models.put(state, MaterialBlockStateLoader.loadBlockModel(
                GTLiteMaterialIconType.sheetedFrame, material.materialIconSet,
                "axis=" + state.getValue(SHEETED_FRAME_AXIS).getName()))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), getMetaFromState(state),
                MaterialBlockStateLoader.loadItemModel(GTLiteMaterialIconType.sheetedFrame, material.materialIconSet))
        }
        ModelLoader.setCustomStateMapper(this) { _ -> models }
    }

    enum class FrameEnumAxis(val serialName: String) : IStringSerializable
    {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");

        override fun getName(): String = serialName

        companion object
        {
            fun fromFacingAxis(axis: EnumFacing.Axis): FrameEnumAxis = when (axis)
            {
                EnumFacing.Axis.X -> X
                EnumFacing.Axis.Y -> Y
                EnumFacing.Axis.Z -> Z
            }
        }

    }

}