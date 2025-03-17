package magicbook.gtlitecore.mixin.minecraft;

import gregtech.api.GTValues;
import magicbook.gtlitecore.api.block.IGlassTier;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin({ BlockGlass.class, BlockStainedGlass.class })
public abstract class BlockGlassMixin extends BlockBreakable implements IGlassTier
{

    protected BlockGlassMixin(Material materialIn, boolean ignoreSimilarityIn)
    {
        super(materialIn, ignoreSimilarityIn);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", this.getColoredVoltageNameByTier()));
    }

    @NotNull
    @Override
    public String getName()
    {
        return getRegistryName().toString();
    }

    @Override
    public int getGlassTier()
    {
        return GTValues.LV;
    }

}
