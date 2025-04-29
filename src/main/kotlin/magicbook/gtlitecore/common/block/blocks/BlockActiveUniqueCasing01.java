package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.block.VariantActiveBlock;
import gregtech.client.utils.BloomEffectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockActiveUniqueCasing01 extends VariantActiveBlock<BlockActiveUniqueCasing01.UniqueCasingType>
{

    public BlockActiveUniqueCasing01()
    {
        super(Material.IRON);
        this.setTranslationKey("active_unique_casing_01");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(UniqueCasingType.HEAT_VENT));
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
    public boolean canRenderInLayer( IBlockState state,
                                     BlockRenderLayer layer)
    {
        UniqueCasingType casingType = this.getState(state);
        if (layer == BlockRenderLayer.CUTOUT)
            return true;
        if (this.isBloomEnabled(casingType))
            return layer == BloomEffectUtil.getEffectiveBloomLayer(layer);
        return layer == BlockRenderLayer.CUTOUT;
    }

    @Override
    protected boolean isBloomEnabled(UniqueCasingType casingType)
    {
        return casingType == UniqueCasingType.HEAT_VENT;
    }

    @Getter
    @AllArgsConstructor
    public enum UniqueCasingType implements IStringSerializable
    {
        HEAT_VENT("heat_vent"),
        TEMPERATURE_CONTROLLER("temperature_controller");

        private final String name;
    }

}
