package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.properties.PropertyMaterial;
import gregtech.common.creativetab.GTCreativeTabs;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconType;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.client.model.factory.MaterialBlockModelLoader;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockSheetedFrame extends Block
{

    public static final PropertyEnum<FrameEnumAxis> SHEETED_FRAME_AXIS
            = PropertyEnum.create("axis", FrameEnumAxis.class);

    public final PropertyMaterial variantProperty;

    /**
     * Modified Material, Harvest Tool and Sound Type by Block#getMaterial(),
     * Block#getHarvestTool() and Block#getSoundType() because we should declare
     * Gregtech material, when it is wooden material, then return axe and wood
     * sound type, otherwise return wrench and metal sound type.
     */
    @SuppressWarnings("deprecation")
    public BlockSheetedFrame(Material[] materials)
    {
        super(net.minecraft.block.material.Material.IRON);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setTranslationKey("sheeted_frame");
        this.setCreativeTab(GTCreativeTabs.TAB_GREGTECH_MATERIALS);
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE_DECORATION);

        this.variantProperty = PropertyMaterial.create("variant", materials);

        BlockStateContainer stateContainer = this.createStateContainer();
        ObfuscationReflectionHelper.setPrivateValue(Block.class, this,
                stateContainer, 21); // this.stateContainer
        this.setDefaultState(stateContainer.getBaseState()
                .withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.Y));

    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this);
    }

    protected BlockStateContainer createStateContainer()
    {
        return new BlockStateContainer(this, SHEETED_FRAME_AXIS, this.variantProperty);
    }

    @Override
    public boolean canCreatureSpawn(IBlockState blockState,
                                    IBlockAccess worldIn,
                                    BlockPos blockPos,
                                    EntityLiving.SpawnPlacementType type)
    {
        return false;
    }

    @Override
    public void onEntityCollision(World worldIn,
                                  BlockPos blockPos,
                                  IBlockState blockState,
                                  Entity entityIn)
    {
        entityIn.motionX = MathHelper.clamp(entityIn.motionX, -0.15, 0.15);
        entityIn.motionZ = MathHelper.clamp(entityIn.motionZ, -0.15, 0.15);
        entityIn.fallDistance = 0.0F;
        if (entityIn.motionY < -0.15)
            entityIn.motionY = -0.15;

        if (entityIn.isSneaking() && entityIn.motionY < 0.0)
            entityIn.motionY = 0.0;

        if (entityIn.collidedHorizontally)
            entityIn.motionY = 0.3;
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for
     * adjustments to the IBlockState.
     */
    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos blockPos, EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta,
                                            EntityLivingBase placer)
    {
        return this.getStateFromMeta(meta).withProperty(SHEETED_FRAME_AXIS,
                FrameEnumAxis.fromFacingAxis(facing.getAxis()));
    }

    /**
     * Axis of block is related two 2 most significant bits in first four bits; indexing
     * with (meta % 16) / 4.
     */
    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        if (meta > 15)
            meta = 0;
        return this.getDefaultState()
                .withProperty(this.variantProperty, this.variantProperty
                        .getAllowedValues().get(meta & 3))
                .withProperty(SHEETED_FRAME_AXIS,
                        FrameEnumAxis.values()[(meta & 15) >>> 2]);
    }

    /**
     * Place axis value in top two bits of first four bits of meta:
     * (X: 00, Y: 01, Z: 10, NONE: 11); and place result in lowest two bits.
     */
    @Override
    public int getMetaFromState(IBlockState blockState)
    {
        int meta = (blockState.getValue(SHEETED_FRAME_AXIS).ordinal() << 2);
        meta |= this.variantProperty.getAllowedValues()
                .indexOf(blockState.getValue(this.variantProperty));
        return meta;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState withRotation(IBlockState blockState, Rotation rotation)
    {
        switch (rotation)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (blockState.getValue(SHEETED_FRAME_AXIS))
                {
                    case X: // X -> Z
                        return blockState.withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.Z);
                    case Z: // Z -> X
                        return blockState.withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.X);
                    default:
                        return blockState; // Y -> Y
                }
            default:
                return blockState;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState blockState)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public int damageDropped(IBlockState blockState)
    {
        return this.getMetaFromState(blockState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public net.minecraft.block.material.Material getMaterial(IBlockState blockState)
    {
        Material material = blockState.getValue(this.variantProperty);
        return ModHandler.isMaterialWood(material) ? net.minecraft.block.material.Material.WOOD
                : super.getMaterial(blockState);
    }

    @Nullable
    @Override
    public String getHarvestTool(IBlockState blockState)
    {
        Material material = blockState.getValue(this.variantProperty);
        return ModHandler.isMaterialWood(material) ? "axe" : "wrench";
    }

    @Override
    public int getHarvestLevel(IBlockState blockState)
    {
        return 1;
    }

    @Override
    public SoundType getSoundType(IBlockState blockState, World worldIn, BlockPos blockPos,
                                  @Nullable Entity entity)
    {
        Material material = blockState.getValue(this.variantProperty);
        return ModHandler.isMaterialWood(material) ? SoundType.WOOD : SoundType.METAL;
    }

    public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> stacks)
    {
        StreamEx.of(this.blockState.getValidStates())
                .filter(s -> s.getValue(this.variantProperty) != Materials.NULL
                                && this.getMetaFromState(s) >>> 2 == 1)
                .forEach(s -> stacks.add(getItem(s)));
    }

    public static ItemStack getItem(IBlockState blockState)
    {
        return GTLiteUtility.toItem(blockState);
    }

    public ItemStack getItem(Material material)
    {
        return getItem(this.getDefaultState().withProperty(this.variantProperty, material)
                .withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.Y));
    }

    public IBlockState getBlock(Material material)
    {
        return this.getDefaultState().withProperty(this.variantProperty, material)
                .withProperty(SHEETED_FRAME_AXIS, FrameEnumAxis.Y);
    }

    /**
     * Only bottom two bits are relevant for getting material.
     */
    public Material getGtMaterial(int meta)
    {
        return this.variantProperty.getAllowedValues().get((meta & 3));
    }

    public Material getGtMaterial(IBlockState blockState)
    {
        return this.getGtMaterial(this.getMetaFromState(blockState));
    }

    @SuppressWarnings("deprecation")
    @Override
    public EnumPushReaction getPushReaction(IBlockState blockState)
    {
        return EnumPushReaction.NORMAL;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos blockPos)
    {
        AxisAlignedBB AABB;
        switch (this.getMetaFromState(blockState) >>> 2)
        {
            // X
            case (0):
                AABB = new AxisAlignedBB(0.05, 0.0, 0.00, 0.95, 1.0, 1.00);
                break;
            // Z
            case (2):
                AABB = new AxisAlignedBB(0.00, 0.0, 0.05, 1.0, 1.0, 0.95);
                break;
            // NONE (all sided) or y[1] as the climbable axis would be on the top of the block.
            default:
                AABB = new AxisAlignedBB(0.00, 0.0, 0.00, 1.0, 1.0, 1.0);
                break;
        }
        return AABB;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState blockState,
                                            BlockPos blockPos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @SideOnly(Side.CLIENT)
    public void onModelRegister()
    {
        Map<IBlockState, ModelResourceLocation> models = new Object2ObjectOpenHashMap<>();
        for (IBlockState blockState : this.getBlockState().getValidStates())
        {
            Material material = this.getGtMaterial(blockState);
            models.put(blockState, MaterialBlockModelLoader.loadBlockModel(
                    GTLiteMaterialIconType.sheetedFrame, material.getMaterialIconSet(),
                    "axis=" + blockState.getValue(SHEETED_FRAME_AXIS).getName()));
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),
                    this.getMetaFromState(blockState),
                    MaterialBlockModelLoader.loadItemModel(GTLiteMaterialIconType.sheetedFrame,
                            material.getMaterialIconSet()));
        }
        ModelLoader.setCustomStateMapper(this, b -> models);
    }

    @Getter
    @AllArgsConstructor
    public enum FrameEnumAxis implements IStringSerializable
    {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");

        private final String name;

        public static FrameEnumAxis fromFacingAxis(EnumFacing.Axis axis)
        {
            switch (axis)
            {
                case X:
                    return X;
                case Y:
                    return Y;
                case Z:
                    return Z;
                default:
                    return NONE;
            }
        }

    }

}
