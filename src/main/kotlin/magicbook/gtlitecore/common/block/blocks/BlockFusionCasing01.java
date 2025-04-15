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
public class BlockFusionCasing01 extends VariantActiveBlock<BlockFusionCasing01.FusionCasingType>
{

    public BlockFusionCasing01()
    {
        super(Material.IRON);
        this.setTranslationKey("fusion_casing_01");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK4));
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
        FUSION_CASING_MK4("fusion_casing_mk4", 3),
        FUSION_CASING_MK5("fusion_casing_mk5", 3),
        ADVANCED_FUSION_COIL("advanced_fusion_coil", 2),
        ULTIMATE_FUSION_COIL("ultimate_fusion_coil", 2),
        CRYOSTAT_MK1("cryostat_mk1", 4),
        CRYOSTAT_MK2("cryostat_mk2", 4),
        CRYOSTAT_MK3("cryostat_mk3", 4),
        CRYOSTAT_MK4("cryostat_mk4", 4);

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
