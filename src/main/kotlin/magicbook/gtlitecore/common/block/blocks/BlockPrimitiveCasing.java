package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.block.IStateHarvestLevel;
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
public class BlockPrimitiveCasing extends VariantBlock<BlockPrimitiveCasing.PrimitiveCasingType>
{

    public BlockPrimitiveCasing()
    {
        super(Material.IRON);
        this.setTranslationKey("primitive_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(this.getState(PrimitiveCasingType.REINFORCED_TREATED_WOOD_WALL));
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
    public enum PrimitiveCasingType implements IStringSerializable, IStateHarvestLevel
    {
        REINFORCED_TREATED_WOOD_WALL("reinforced_treated_wood_wall", 0);
        // TODO adobe bricks.

        private final String name;
        private final int harvestLevel;

        @Override
        public int getHarvestLevel(IBlockState state)
        {
            return this.harvestLevel;
        }

        @Override
        public String getHarvestTool(IBlockState state)
        {
            return "wrench";
        }

    }

}
