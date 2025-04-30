package magicbook.gtlitecore.common.block.blocks.explosive;

import gregtech.common.blocks.explosive.BlockGTExplosive;
import gregtech.common.entities.EntityGTExplosive;
import magicbook.gtlitecore.common.entity.explosive.EntityLeptonicCharge;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static magicbook.gtlitecore.integration.groovyscript.hooks.GrSRecipeHooks.MINUTE;

public class BlockLeptonicCharge extends BlockGTExplosive
{

    public BlockLeptonicCharge()
    {
        super(Material.TNT, true, true, 1 * MINUTE);
        this.setHardness(0);
        this.setSoundType(SoundType.PLANT);
    }

    @Override
    protected EntityGTExplosive createEntity(World world, BlockPos pos, EntityLivingBase exploder)
    {
        float x = pos.getX() + 0.5F, y = pos.getY(), z = pos.getZ() + 0.5F;
        return new EntityLeptonicCharge(world, x, y, z, exploder);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(I18n.format("tile.leptonic_charge.tooltip.1"));
        tooltip.add(I18n.format("tile.leptonic_charge.tooltip.2"));
    }

}
