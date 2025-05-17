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
public class BlockMultiblockCasing01 extends VariantBlock<BlockMultiblockCasing01.MultiblockCasingType>
{

    public BlockMultiblockCasing01()
    {
        super(Material.IRON);
        this.setTranslationKey("multiblock_casing_01");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(MultiblockCasingType.SUBSTRATE_CASING));
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
    public enum MultiblockCasingType implements IStringSerializable
    {
        SUBSTRATE_CASING("substrate_casing"),
        ADVANCED_SUBSTRATE_CASING("advanced_substrate_casing"),
        DRILL_HEAD("drill_head"),
        ADVANCED_FILTER_CASING("advanced_filter_casing"),
        REFLECTIVE_SURFACE_CASING("reflective_surface_casing"),
        INFINITY_COOLING_CASING("infinity_cooling_casing"),
        STELLAR_CONTAINMENT_CASING("stellar_containment_casing"),
        HARMONIC_PHONON_TRANSMISSION_CASING("harmonic_phonon_transmission_casing"),
        PARTICLE_CONTAINMENT_CASING("particle_containment_casing"),
        PARTICLE_EXCITATION_WIRE_COIL("particle_excitation_wire_coil"),
        GRAVITY_STABILIZATION_CASING("gravity_stabilization_casing"),
        PROTOMATTER_ACTIVATION_COIL("protomatter_activation_coil"),
        ANTIMATTER_ANNIHILATION_MATRIX("antimatter_annihilation_matrix");

        private final String name;
    }

}
