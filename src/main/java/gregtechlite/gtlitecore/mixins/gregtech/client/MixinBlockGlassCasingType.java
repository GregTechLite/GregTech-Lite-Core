package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.GTValues;
import gregtech.common.blocks.BlockGlassCasing;
import gregtechlite.gtlitecore.api.block.attribute.StateTier;
import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockGlassCasing.CasingType.class)
public abstract class MixinBlockGlassCasingType implements IStringSerializable, StateTier
{

    @NotNull
    @Shadow
    public abstract String getName();

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public int getTier()
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
