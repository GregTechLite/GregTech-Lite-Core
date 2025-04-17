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
public class BlockComponentAssemblyCasing extends VariantBlock<BlockComponentAssemblyCasing.ComponentCasingType>
{

    public BlockComponentAssemblyCasing()
    {
        super(Material.IRON);
        this.setTranslationKey("component_assembly_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(ComponentCasingType.LV));
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
    public enum ComponentCasingType implements IStringSerializable
    {
        LV("lv"),
        MV("mv"),
        HV("hv"),
        EV("ev"),
        IV("iv"),
        LuV("luv"),
        ZPM("zpm"),
        UV("uv"),
        UHV("uhv"),
        UEV("uev"),
        UIV("uiv"),
        UXV("uxv"),
        OpV("opv"),
        MAX("max");

        private final String name;
    }

}
