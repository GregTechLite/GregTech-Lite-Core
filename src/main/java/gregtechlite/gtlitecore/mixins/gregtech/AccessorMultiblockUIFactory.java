package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory;
import gregtechlite.gtlitecore.mixins.Implemented;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2900")
@Mixin(value = MultiblockUIFactory.class, remap = false)
public interface AccessorMultiblockUIFactory
{

    @Accessor("mte")
    MultiblockWithDisplayBase mte();

}
