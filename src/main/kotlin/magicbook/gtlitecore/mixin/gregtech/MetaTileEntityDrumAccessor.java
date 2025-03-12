package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.capability.IPropertyFluidFilter;
import gregtech.common.metatileentities.storage.MetaTileEntityDrum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = MetaTileEntityDrum.class, remap = false)
public interface MetaTileEntityDrumAccessor
{

    @Accessor("fluidFilter")
    IPropertyFluidFilter getFluidFilter();

    @Accessor("color")
    int getColor();

    @Accessor("tankSize")
    int getTankSize();

    @Accessor("isAutoOutput")
    boolean isAutoOutput();

}
