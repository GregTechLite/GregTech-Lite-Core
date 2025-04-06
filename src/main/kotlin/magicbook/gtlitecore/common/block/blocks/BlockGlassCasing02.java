package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.GTValues;
import gregtech.api.block.VariantBlock;
import gregtech.api.block.VariantItemBlock;
import lombok.AllArgsConstructor;
import magicbook.gtlitecore.api.block.IGlassTier;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockGlassCasing02 extends VariantBlock<BlockGlassCasing02.GlassType>
{

    public BlockGlassCasing02()
    {
        super(Material.GLASS);
        this.setTranslationKey("glass_casing_02");
        this.setHardness(5.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.GLASS);
        this.setHarvestLevel("pickaxe", 1);
        this.setDefaultState(this.getState(GlassType.BOROSILICATE));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state,
                                    IBlockAccess world,
                                    BlockPos pos,
                                    EntityLiving.SpawnPlacementType type)
    {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return layer == BlockRenderLayer.TRANSLUCENT;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world,
                                        BlockPos pos, EnumFacing side)
    {
        IBlockState sideState = world.getBlockState(pos.offset(side));
        return sideState.getBlock() == this ? this.getState(sideState) != getState(state) :
                super.shouldSideBeRendered(state, world, pos, side);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               List<String> tooltip,
                               ITooltipFlag advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        VariantItemBlock itemBlock = (VariantItemBlock<BlockGlassCasing02.GlassType, BlockGlassCasing02>) stack.getItem();
        IBlockState stackState = itemBlock.getBlockState(stack);
        BlockGlassCasing02.GlassType glassType =  this.getState(stackState);
        tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", glassType.getColoredVoltageNameByTier()));
    }

    @AllArgsConstructor
    public enum GlassType implements IStringSerializable, IGlassTier
    {
        BOROSILICATE("borosilicate", GTValues.HV),
        TITANIUM_BOROSILICATE("titanium_borosilicate", GTValues.EV),
        TUNGSTEN_STEEL_BOROSILICATE("tungsten_steel_borosilicate", GTValues.IV),
        RHODIUM_PLATED_PALLADIUM_BOROSILICATE("rhodium_plated_palladium_borosilicate", GTValues.LuV),
        OSMIRIDIUM_BOROSILICATE("osmiridium_borosilicate", GTValues.ZPM),
        TRITANIUM_BOROSILICATE("tritanium_borosilicate", GTValues.UV),
        NEUTRONIUM_BOROSILICATE("neutronium_borosilicate", GTValues.UHV);
        // TODO UEV-MAX

        private final String name;
        private final int tier;

        @Override
        public Object getTier()
        {
            return this.tier;
        }

        @Override
        public int getGlassTier()
        {
            return this.tier;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        @Override
        public String toString()
        {
            return this.getName();
        }

    }

}
