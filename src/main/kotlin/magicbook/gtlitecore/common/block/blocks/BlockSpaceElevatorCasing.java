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
public class BlockSpaceElevatorCasing extends VariantBlock<BlockSpaceElevatorCasing.CasingType>
{

    public BlockSpaceElevatorCasing()
    {
        super(Material.IRON);
        this.setTranslationKey("space_elevator_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(BlockSpaceElevatorCasing.CasingType.HIGH_STRENGTH_CONCRETE));
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
    public enum CasingType implements IStringSerializable
    {
        HIGH_STRENGTH_CONCRETE("high_strength_concrete"),
        BASE_CASING("elevator_base_casing"),
        SUPPORT_STRUCTURE_CASING("elevator_support_structure"),
        INTERNAL_STRUCTURE_CASING("elevator_internal_structure");

        private final String name;
    }

}
