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
public class BlockMetalCasing01 extends VariantBlock<BlockMetalCasing01.MetalCasingType>
{

    public BlockMetalCasing01()
    {
        super(Material.IRON);
        this.setTranslationKey("metal_casing_01");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(MetalCasingType.MARAGING_STEEL_250));
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
        MARAGING_STEEL_250("maraging_steel_250"),
        INCONEL_625("inconel_625"),
        BLUE_STEEL("blue_steel"),
        STABALLOY("staballoy"),
        TALONITE("talonite"),
        IRIDIUM("iridium"),
        ZERON_100("zeron_100"),
        WATERTIGHT_STEEL("watertight_steel"),
        STELLITE("stellite"),
        TUMBAGA("tumbaga"),
        EGLIN_STEEL("eglin_steel"),
        POTIN("potin"),
        GRISIUM("grisium"),
        BABBIT_ALLOY("babbit_alloy"),
        SILICON_CARBIDE("silicon_carbide"),
        RED_STEEL("red_steel");

        private final String name;
    }

}
