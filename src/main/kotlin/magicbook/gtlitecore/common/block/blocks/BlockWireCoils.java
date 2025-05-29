package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.block.VariantActiveBlock;
import gregtech.api.block.VariantItemBlock;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityMultiSmelter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import magicbook.gtlitecore.api.unification.GTLiteMaterials;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
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
public class BlockWireCoils extends VariantActiveBlock<BlockWireCoils.WireCoilType>
{

    public BlockWireCoils()
    {
        super(Material.IRON);
        this.setTranslationKey("wire_coil");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(WireCoilType.ADAMANTIUM));
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
        return BlockRenderLayer.SOLID;
    }

    protected boolean isBloomEnabled(BlockWireCoils.WireCoilType value)
    {
        return ConfigHolder.client.coilsActiveEmissiveTextures;
    }

    @SuppressWarnings("rawtypes, unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack,
                               @Nullable World worldIn,
                               List<String> lines,
                               ITooltipFlag tooltipFlag)
    {
        super.addInformation(itemStack, worldIn, lines, tooltipFlag);
        VariantItemBlock itemBlock = (VariantItemBlock<WireCoilType, BlockWireCoils>) itemStack.getItem();
        IBlockState stackState = itemBlock.getBlockState(itemStack);
        WireCoilType coilType =  this.getState(stackState);
        lines.add(I18n.format("tile.wire_coil.tooltip_heat", coilType.coilTemperature));
        if (TooltipHelper.isShiftDown())
        {
            int coilTier = coilType.getTier();
            lines.add(I18n.format("tile.wire_coil.tooltip_smelter"));
            lines.add(I18n.format("tile.wire_coil.tooltip_parallel_smelter", coilType.level * 32));
            int EUt = MetaTileEntityMultiSmelter.getEUtForParallel(MetaTileEntityMultiSmelter.getMaxParallel(coilType.getLevel()), coilType.getEnergyDiscount());
            lines.add(I18n.format("tile.wire_coil.tooltip_energy_smelter", EUt));
            lines.add(I18n.format("tile.wire_coil.tooltip_pyro"));
            lines.add(I18n.format("tile.wire_coil.tooltip_speed_pyro", coilTier == 0 ? 75 : 50 * (coilTier + 1)));
            lines.add(I18n.format("tile.wire_coil.tooltip_cracking"));
            lines.add(I18n.format("tile.wire_coil.tooltip_energy_cracking", 100 - 10 * coilTier));
        }
        else
        {
            lines.add(I18n.format("tile.wire_coil.tooltip_extended_info"));
        }
    }

    @Getter
    @AllArgsConstructor
    public enum WireCoilType implements IStringSerializable, IHeatingCoilBlockStats
    {
        // UHV: Adamantium, 12600K (10800K + 1800K)
        ADAMANTIUM("adamantium", 12600, 16, 16, GTLiteMaterials.Adamantium),
        // UEV: Infinity, 14400K (12600K + 1800K)
        INFINITY("infinity", 14400, 32, 16, GTLiteMaterials.Infinity),
        // UIV: Halkonite Steel, 16201K (14400K + 1801K)
        HALKONITE_STEEL("halkonite_steel", 16201, 32, 32, GTLiteMaterials.HalkoniteSteel),
        // UXV: Space Time, 18000K (16201K + 1799K)
        SPACE_TIME("space_time", 18000, 64, 32, GTLiteMaterials.SpaceTime),
        // OpV: Eternity, 19800K (18000K + 1800K)
        ETERNITY("eternity", 19800, 64, 64, GTLiteMaterials.Eternity);

        private final String name;
        private final int coilTemperature;
        private final int level;
        private final int energyDiscount;
        private final gregtech.api.unification.material.Material material;

        public int getTier()
        {
            return this.ordinal() + 9; // After Tritanium Wire Coil (8).
        }

        @Override
        public String toString()
        {
            return this.getName();
        }

    }

}
