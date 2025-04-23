package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.block.VariantBlock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockMetalCasing03 extends VariantBlock<BlockMetalCasing03.MetalCasingType>
{

    public BlockMetalCasing03()
    {
        super(Material.IRON);
        this.setTranslationKey("metal_casing_03");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(MetalCasingType.COBALT_BRASS));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state,
                                    IBlockAccess world,
                                    BlockPos pos,
                                    EntityLiving.SpawnPlacementType type)
    {
        return false;
    }

    @Getter
    @AllArgsConstructor
    public enum MetalCasingType implements IStringSerializable
    {
        COBALT_BRASS("cobalt_brass"),
        TRINAQUADALLOY("trinaquadalloy"),
        OSMIRIDIUM("osmiridium"),
        NEUTRONIUM("neutronium"),
        NAQUADAH_ALLOY("naquadah_alloy"),
        QUANTUM_ALLOY("quantum_alloy");

        private final String name;
    }

}
