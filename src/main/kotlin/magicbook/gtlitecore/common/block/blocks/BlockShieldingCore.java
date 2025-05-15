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
public class BlockShieldingCore extends VariantActiveBlock<BlockShieldingCore.ShieldingCoreType>
{

    public BlockShieldingCore()
    {
        super(Material.IRON);
        this.setTranslationKey("shielding_core");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 3);
        this.setDefaultState(this.getState(ShieldingCoreType.NEUTRON));
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
    public boolean canRenderInLayer(IBlockState state,
                                    BlockRenderLayer layer)
    {
        BlockShieldingCore.ShieldingCoreType casingType = this.getState(state);
        if (layer == BlockRenderLayer.CUTOUT)
            return true;
        if (this.isBloomEnabled(casingType))
            return layer == BloomEffectUtil.getEffectiveBloomLayer(layer);
        return layer == BlockRenderLayer.CUTOUT;
    }

    @Getter
    @AllArgsConstructor
    public enum ShieldingCoreType implements IStringSerializable
    {
        NEUTRON("neutron"),
        COSMIC_FABRIC("cosmic_fabric"),
        INFINITY_INFUSED("infinity_infused"),
        SPACETIME_BENDING_CORE("spacetime_bending_core");

        private final String name;
    }

}
