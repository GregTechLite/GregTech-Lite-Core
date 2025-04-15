package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.block.IStateHarvestLevel;
import gregtech.api.block.VariantActiveBlock;
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
public class BlockFusionCasing02 extends VariantActiveBlock<BlockFusionCasing02.FusionCasingType>
{

    public BlockFusionCasing02()
    {
        super(Material.IRON);
        this.setTranslationKey("fusion_casing_02");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(FusionCasingType.CRYOSTAT_MK5));
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
    public enum FusionCasingType implements IStringSerializable, IStateHarvestLevel
    {
        CRYOSTAT_MK5("cryostat_mk5", 4),
        DIVERTOR_MK1("divertor_mk1", 4),
        DIVERTOR_MK2("divertor_mk2", 4),
        DIVERTOR_MK3("divertor_mk3", 4),
        DIVERTOR_MK4("divertor_mk4", 4),
        DIVERTOR_MK5("divertor_mk5", 4),
        VACUUM_MK1("vacuum_mk1", 4),
        VACUUM_MK2("vacuum_mk2", 4);

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
