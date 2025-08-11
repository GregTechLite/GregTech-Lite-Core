package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.IPropertyFluidFilter;
import gregtech.common.metatileentities.storage.MetaTileEntityDrum;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = MetaTileEntityDrum.class, remap = false)
public interface AccessorMetaTileEntityDrum
{

    @Accessor("fluidFilter")
    IPropertyFluidFilter<FluidStack> getFluidFilter();

    @Accessor("color")
    int getColor();

    @Accessor("tankSize")
    int getTankSize();

    @Accessor("isAutoOutput")
    boolean isAutoOutput();

}
