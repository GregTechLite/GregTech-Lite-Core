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
public class BlockManipulator extends VariantActiveBlock<BlockManipulator.ManipulatorType>
{

    public BlockManipulator()
    {
        super(Material.IRON);
        this.setTranslationKey("manipulator");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 3);
        this.setDefaultState(this.getState(ManipulatorType.NEUTRON_PULSE));
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
        BlockManipulator.ManipulatorType casingType = this.getState(state);
        if (layer == BlockRenderLayer.CUTOUT)
            return true;
        if (this.isBloomEnabled(casingType))
            return layer == BloomEffectUtil.getEffectiveBloomLayer(layer);
        return layer == BlockRenderLayer.CUTOUT;
    }

    @Getter
    @AllArgsConstructor
    public enum ManipulatorType implements IStringSerializable
    {
        NEUTRON_PULSE("neutron_pulse"),
        COSMIC_FABRIC("cosmic_fabric"),
        INFINITY_INFUSED("infinity_infused"),
        SPACETIME_CONTINUUM_RIPPER("spacetime_continuum_ripper");

        private final String name;
    }

}
