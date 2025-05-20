package magicbook.gtlitecore.common.block.blocks;

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
public class BlockAccelerationOrbit extends VariantActiveBlock<BlockAccelerationOrbit.OrbitTier>
{

    public BlockAccelerationOrbit()
    {
        super(Material.IRON);
        this.setTranslationKey("acceleration_orbit");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(OrbitTier.MK1));
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
    public enum OrbitTier implements IStringSerializable
    {
        MK1("mk1"),
        MK2("mk2"),
        MK3("mk3"),
        MK4("mk4"),
        MK5("mk5");

        private final String name;
    }

}
