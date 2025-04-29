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
public class BlockNuclearReactorCore02 extends VariantActiveBlock<BlockNuclearReactorCore02.ReactorCoreType>
{

    public BlockNuclearReactorCore02()
    {
        super(Material.IRON);
        this.setTranslationKey("nuclear_reactor_core_02");
        this.setHardness(4.0F);
        this.setResistance(8.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 3);
        this.setDefaultState(this.getState(ReactorCoreType.CALIFORNIUM));
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
    public enum ReactorCoreType implements IStringSerializable
    {
        CALIFORNIUM("californium"),
        EINSTEINIUM("einsteinium"),
        FERMIUM("fermium"),
        MENDELEVIUM("mendelevium");

        private final String name;
    }

}
