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
public class BlockMetalCasing02 extends VariantBlock<BlockMetalCasing02.MetalCasingType>
{

    public BlockMetalCasing02()
    {
        super(Material.IRON);
        this.setTranslationKey("metal_casing_02");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(MetalCasingType.HSLA_STEEL));
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
        HSLA_STEEL("hsla_steel"),
        KOVAR("kovar"),
        BLACK_STEEL("black_steel"),
        INCOLOY_MA813("incoloy_ma813"),
        MONEL_500("monel_500"),
        INCOLOY_MA956("incoloy_ma956"),
        ZIRCONIUM_CARBIDE("zirconium_carbide"),
        HASTELLOY_C276("hastelloy_c276"),
        HASTELLOY_X("hastelloy_x");

        private final String name;
    }

}
