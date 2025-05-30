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
public class BlockSpacetimeCompressionFieldGenerator extends VariantBlock<BlockSpacetimeCompressionFieldGenerator.FieldGeneratorTier>
{

    public BlockSpacetimeCompressionFieldGenerator()
    {
        super(Material.IRON);
        this.setTranslationKey("spacetime_compression_field_generator");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(FieldGeneratorTier.CRUDE));
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
    public enum FieldGeneratorTier implements IStringSerializable
    {
        CRUDE("crude"),
        PRIMITIVE("primitive"),
        STABLE("stable"),
        ADVANCED("advanced"),
        SUPERB("superb"),
        EXOTIC("exotic"),
        PERFECT("perfect"),
        TIPLER("tipler"),
        GALLIFREYAN("gallifreyan");

        private final String name;
    }

}
