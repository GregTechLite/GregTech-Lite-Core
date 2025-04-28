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
public class BlockTurbineCasing02 extends VariantBlock<BlockTurbineCasing02.TurbineCasingType>
{

    public BlockTurbineCasing02()
    {
        super(Material.IRON);
        this.setTranslationKey("turbine_casing_02");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(this.getState(TurbineCasingType.RHODIUM_PLATED_PALLADIUM_GEARBOX));
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
    public enum TurbineCasingType implements IStringSerializable, IStateHarvestLevel
    {
        RHODIUM_PLATED_PALLADIUM_GEARBOX("rhodium_plated_palladium_gearbox", 4);

        private final String name;
        private final int harvestLevel;

        @Override
        public int getHarvestLevel(IBlockState state)
        {
            return getHarvestLevel();
        }

        @Override
        public String getHarvestTool(IBlockState state)
        {
            return "wrench";
        }

    }

}
