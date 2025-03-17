package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.GTValues;
import gregtech.common.blocks.BlockGlassCasing;
import magicbook.gtlitecore.api.block.IGlassTier;
import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockGlassCasing.CasingType.class)
public abstract class BlockGlassCasingTypeMixin implements IStringSerializable, IGlassTier
{

    @NotNull
    @Shadow
    public abstract String getName();

    @Override
    public int getGlassTier()
    {
        switch (this.getName())
        {
            case ("fusion_glass"): return GTValues.ZPM;
            case ("laminated_glass"): return GTValues.IV;
            case ("cleanroom_glass"): return GTValues.MV;
            default: return GTValues.HV;
        }
    }

}
